package nebula.tinyasm;

import static nebula.tinyasm.TypeUtils.every;
import static nebula.tinyasm.TypeUtils.typeOf;
import static org.objectweb.asm.Opcodes.ACC_BRIDGE;
import static org.objectweb.asm.Opcodes.ACC_SYNTHETIC;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

class MethodHeaderBuilder implements MethodHeader {
	class ThisMethod {

		String name;

		Type type;
		boolean hasEnded = false;
		boolean instanceMethod = true;
	}

	ThisMethod thisMethod;
	int access;

	final private ClassBodyImpl classVisitor;

	protected Label labelCurrent;

	boolean labelHasDefineBegin = false;

	Type stackTopType;

	final LocalsStack mhLocals = new LocalsStack();
	final List<LocalsVariable> params = new ArrayList<>();
	final List<Annotation> annotations = new ArrayList<>();
	final FieldList fields;
	final FieldList staticFields;

	MethodVisitor mv;

	final List<Clazz> exceptions = new ArrayList<>();
	Clazz returnClazz = null;

	public MethodHeaderBuilder(ClassBodyImpl cv, String className, int access, Clazz returnType, String methodName) {
		this(cv, className, access, methodName);
		this.returnClazz = returnType;
	}

	public MethodHeaderBuilder(ClassBodyImpl cv, String className, int access, String methodName) {
		this.classVisitor = cv;
		thisMethod = new ThisMethod();
		thisMethod.name = methodName;
		this.access = access;
		thisMethod.type = typeOf(className);
		this.fields = cv.fields;
		this.staticFields = cv.staticFields;
	}

//	@Override
//	public MethodHeader annotation(String clazz, Object value) {
//		thisMethod.annotations.add(new ClassAnnotation(type, null, value));
//		return this;
//	}

//	@Override
//	public MethodHeader annotation(String clazz, Object defaultValue, String[] names, Object[] values) {
//		annotations.add(new Annotation(clazz, defaultValue, names, values));
//		return this;
//	}

//	@Override
//	public MethodHeader annotation(Type type, Object value) {
//		thisMethod.annotations.add(new ClassAnnotation(type, null, value));
//		return this;
//	}
//
//	@Override
//	public MethodHeader annotation(Type type, String name, Object value) {
//		thisMethod.annotations.add(new ClassAnnotation(type, name, value));
//		return this;
//	}

	@Override
	public MethodCode begin() {
		prapareMethodDefination();
		mv.visitCode();
		labelCurrent = labelWithoutLineNumber();
		preapareMethodWithClazz();
		preapareMethodWithThis();
		preapareMethodWithParams();

		return makeCode(mv);
	}

	@Override
	public MethodHeader code(Consumer<MethodCode> invocation) {
		MethodCode mc = this.begin();
		invocation.accept(mc);
		this.codeEnd();
		return this;
	}

//	@Override
//	public MethodHeader friendly(Consumer<MethodCodeAdv> invocation) {
//		MethodCode mc = this.begin();
//		invocation.accept((MethodCodeAdv)mc);
//		this.codeEnd();
//		return this;
//	}

	void codeEnd() {
		finishMethod();
	}

	protected void finishMethod() {
		if (thisMethod.hasEnded) return;
		if (!((this.access & ACC_SYNTHETIC) > 0)) {
			Label endLabel = this.labelWithoutLineNumber();
			for (LocalsStack.Var var : mhLocals) {
				if (!((var.access & ACC_SYNTHETIC) > 0)) {
					assert mv != null;
					assert var != null;
					assert var.clazz.getDescriptor() != null;
					Label labelfrom = var.startFrom != null ? var.startFrom : labelCurrent;

					String varname = var.name != null ? var.name : "var" + var.locals;

					mv.visitLocalVariable(varname, var.clazz.getDescriptor(), var.clazz.signatureWhenNeed(), labelfrom, endLabel, var.locals);
				}
			}
		} else if ((this.access & ACC_SYNTHETIC) > 0 && (this.access & ACC_BRIDGE) > 0) {
			Label endLabel = this.labelWithoutLineNumber();
			LocalsStack.Var var = mhLocals.getByLocal(0);
			assert mv != null;
			assert var != null;
			assert var.clazz.getDescriptor() != null;
			Label labelfrom = var.startFrom != null ? var.startFrom : labelCurrent;
			mv.visitLocalVariable(var.name, var.clazz.getDescriptor(), var.clazz.signatureWhenNeed(), labelfrom, endLabel, var.locals);
		}
		mv.visitMaxs(0, 0);
		mv.visitEnd();
		thisMethod.hasEnded = true;
	}

	protected Label labelWithoutLineNumber() {
		Label label = new Label();
		labelCurrent = label;
		mv.visitLabel(label);
		return label;
	}

	MethodCode makeCode(MethodVisitor mv) {
		return new MethodCodeBuilder(mv, this, mhLocals);
	}

	@Override
	public MethodHeader annotation(Annotation annotation) {
		annotations.add(annotation);
		return this;
	}

	@Override
	public MethodHeader parameter(int access, String name, Clazz clazz) {
		LocalsVariable param = new LocalsVariable(access, name, clazz);
		params.add(param);
		return this;
	}

	@Override
	public MethodHeader parameter(Annotation annotation, String name, Clazz clazz) {
		LocalsVariable param = new LocalsVariable(annotation, name, clazz);
		params.add(param);
		return this;
	}

	protected void prapareMethodDefination() {
		{
			int access = this.access;
			String name = thisMethod.name;

			thisMethod.instanceMethod = (access & Opcodes.ACC_STATIC) == 0;

			Type returnType;
			if (returnClazz != null) returnType = returnClazz.getType();
			else returnType = Type.VOID_TYPE;

			Type[] types1 = new Type[params.size()];
			for (int i1 = 0; i1 < params.size(); i1++) {
				types1[i1] = params.get(i1).clazz.getType();
			}

			String desc = Type.getMethodDescriptor(returnType, types1);
			String signature = null;
			boolean needSignature = false;
			{
				StringBuilder sb = new StringBuilder();
				sb.append("(");
				for (ClassField param : params) {
					if (param.clazz.needSignature()) {
						sb.append(param.clazz.signatureAnyway());
						needSignature = true;
					} else {
						sb.append(param.clazz.getDescriptor());
					}
				}
				sb.append(")");
				if (returnClazz != null) {
					needSignature |= returnClazz.needSignature();
					sb.append(returnClazz.signatureAnyway());
				} else {
					sb.append(returnType.getDescriptor());

				}
				String signatureFromParameter = sb.toString();

				if (needSignature) {
					signature = signatureFromParameter;
				}
			}

			this.mv = classVisitor.visitMethod(access, name, desc, signature, every(String.class, exceptions, e -> e.getType().getInternalName()));
		}

		assert this.mv != null;
		for (Annotation annotation : annotations) {
			Annotation.visitAnnotation(this.mv, annotation);
		}
		for (int i = 0; i < params.size(); i++) {
			LocalsVariable param = params.get(i);
			if (param.annotation != null) {
				Annotation.visitParameterAnnotation(this.mv, i, param.annotation);
			}
			mv.visitParameter(param.name, param.access);
		}
	}

	protected void preapareMethodWithClazz() {
	}

	protected void preapareMethodWithParams() {
		for (ClassField field : params) {
			mhLocals.pushParameter(field.name, field.clazz, labelCurrent);
		}
	}

	protected void preapareMethodWithThis() {
		if (thisMethod.instanceMethod) {
			mhLocals.pushParameter("this", thisMethod.type, labelCurrent);
		}
	}

	@Override
	public MethodHeader tHrow(Clazz clazz) {
		exceptions.add(clazz);
		return this;
	}

	@Override
	public MethodHeader access(int access) {
		this.access |= access;
		return this;
	}

	@Override
	public MethodHeader reTurn(Clazz clazz) {
		this.returnClazz = clazz;
		return this;
	}
//
//	
//	@Override
//	public void makeBridgeMathod() {
//
//		MethodHeader mh = this.classVisitor.method(this.access | ACC_BRIDGE + ACC_SYNTHETIC, this.returnClazz.classname,
//				this.thisMethod.name);
//		for (GenericClazz exClazz : exceptions) {
//			mh.tHrow(exClazz);
//		}
//		for (ClassField field : this.params) {
//			mh.parameter(field.name, field.clazz.classname);
//		}
//		mh.code(mv -> {
//			mv.LINE();
//			mv.LOAD(0);
//
//			for (ClassField field : this.params) {
//				mv.LOAD(field.name);
//			}
//			mv.VIRTUAL(this.classVisitor.getName(), this.thisMethod.name)
//				.param(ClassField.genericOf(this.params.list()))
//				.reTurn(this.returnClazz)
//				.INVOKE();
//			if (this.returnClazz != null) {
//				mv.RETURNTop();
//			} else {
//				mv.RETURN();
//			}
//		});
//	}

//
//	@Override
//	public MethodHeader tHrow(String... clazzes) {
//		for (String clazz : clazzes) {
//			exceptions.add(clazz, null));
//		}
//		return this;
//	}
}