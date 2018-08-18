package nebula.tinyasm;

import static nebula.tinyasm.util.TypeUtils.typeOf;
import static nebula.tinyasm.util.TypeUtils.typesOf;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import nebula.tinyasm.data.Annotation;
import nebula.tinyasm.data.GenericClazz;
import nebula.tinyasm.data.MethodCaller;
import nebula.tinyasm.data.MethodCode;

public class MethodCodeBuilder implements MethodCode {
	private final MethodVisitor mv;

	final protected LocalsStack locals;
	final Stack<Type> stack = new Stack<>();
	final MethodHeaderBuilder mh;

	int lastLineNumber = 0;

	protected Label labelCurrent;
	protected boolean labelHasDefineBegin;

	public MethodCodeBuilder(MethodVisitor mv, MethodHeaderBuilder mh, LocalsStack locals) {
		this.mv = mv;
		this.mh = mh;
		this.locals = locals;
		this.labelHasDefineBegin = mh.labelHasDefineBegin;
		this.labelCurrent = mh.labelCurrent;
	}

	@Override
	public MethodCode block(Consumer<MethodCode> invocation) {
		invocation.accept(this);
		return this;
	}

	@Override
	public void codeAccessLabel(Label label) {
		labelCurrent = label;
		mv.visitLabel(label);
	}

	@Override
	public void codeAccessLabel(Label label, int line) {
		labelCurrent = label;
		mv.visitLabel(label);
		mv.visitLineNumber(line, label);
	}

	@Override
	public Type codeGetStackType(int i) {
		return stack.get(stack.size() - i - 1);
	}

	@Override
	public int codeLocalGetLocals(String name) {
		return locals.get(name).locals;
	}
//
//	@Override
//	public Type codeLocalGetType(String name) {
//		return locals.get(name).type;
//	}
//
//	@Override
//	public Type codeLocalGetType(int localsIndex) {
//		return locals.getByLocal(localsIndex).type;
//	}

	@Override
	public Type codeLocalLoadAccess(int localsIndex) {
		return locals.accessLoad(localsIndex, labelCurrent).type;
	}

	@Override
	public Type codeLocalStoreAccess(int localsIndex) {
		return locals.accessStore(localsIndex, labelCurrent).type;
	}

	@Override
	public Type codeThisClassFieldType(String name) {
		assert mh.staticFields.containsKey(name) : "field + " + name + " not exist!";
		return mh.staticFields.get(name).type;
	}

	@Override
	public Type codeThisFieldType(String name) {
		assert mh.thisMethod.instanceMethod;
		assert mh.fields.containsKey(name) : "field + " + name + " not exist!";
		return mh.fields.get(name).type;
	}

	@Override
	public Label codeNewLabel() {
		Label label = new Label();
		return label;
	}

	@Override
	public Type codePopStack() {
		Type type = stack.pop();
		printStack(stack);
		return type;
	}

	@Override
	public void codePush(Type type) {
		stack.push(type);
		printStack(stack);
	}

	@Override
	public MethodCode define(String name, GenericClazz clazz) {
		locals.push(name, new LocalsVariable(name, clazz));
		return this;
	}

	@Override
	public MethodCode define(Annotation annotation, String name, GenericClazz clazz) {
		locals.push(name, new LocalsVariable(annotation, name, clazz));
		return this;
	}

	@Override
	public void end() {
		mh.finishMethod();
	}

	public MethodCode line() {
		Label label;
		if (!labelHasDefineBegin) {
			label = new Label();
			labelCurrent = label;
			mv.visitLabel(label);
		} else {
			label = labelCurrent;
		}
		lastLineNumber = lastLineNumber + 1;
		mv.visitLineNumber(lastLineNumber, label);
		return this;
	}

	public MethodCode line(int line) {
		Label label;
		if (!labelHasDefineBegin) {
			label = new Label();
			labelCurrent = label;
			mv.visitLabel(label);
		} else {
			label = labelCurrent;
		}
		lastLineNumber = line;
		mv.visitLineNumber(line, label);
		return this;
	}

	@Override
	public void mvFieldInsn(int opcode, Type ownerType, String fieldName, Type fieldType) {
		mv.visitFieldInsn(opcode, ownerType.getInternalName(), fieldName, fieldType.getDescriptor());
	}

	@Override
	public void mvInst(int opcode) {
		mv.visitInsn(opcode);
	}

	@Override
	public void mvInst(int opcode, int var) {
		mv.visitVarInsn(opcode, var);
	}

	@Override
	public void mvIntInsn(int opcode, int operand) {
		if (opcode == BIPUSH && -1 <= operand && operand <= 5) {
			mv.visitInsn(ICONST_0 + operand);
		} else {
			mv.visitIntInsn(opcode, operand);
		}
	}

	@Override
	public void mvInvoke(int opcode, Type objectType, Type returnType, String methodName, Type... paramTypes) {
		mv.visitMethodInsn(opcode, objectType.getInternalName(), methodName,
				Type.getMethodDescriptor(returnType, paramTypes), opcode == INVOKEINTERFACE);

	}

	@Override
	public void mvJumpInsn(int opcode, Label label) {
		mv.visitJumpInsn(opcode, label);
	}

	@Override
	public void mvLdcInsn(Object cst) {
		mv.visitLdcInsn(cst);
	}

	@Override
	public void mvTypeInsn(int opcode, Type type) {
		mv.visitTypeInsn(opcode, type.getInternalName());
	}

	private void printStack(Stack<Type> stack) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(thisMethod.name).append(" : ");
//		for (Type type : stack) {
//			sb.append(type);
//			sb.append(" > ");
//		}
//		sb.setCharAt(sb.length() - 2, '\n');
//		System.out.println(sb.toString());
	}

	@Override
	public MethodCaller<MethodCode> STATIC(String objectType, String methodName) {
		// TODO Auto-generated method stub
		return new MethodCallerImpl(Opcodes.INVOKESTATIC, GenericClazz.generic(objectType), methodName);
	}

	@Override
	public MethodCaller<MethodCode> INTERFACE(String objectType, String methodName) {
		return new MethodCallerImpl(Opcodes.INVOKEINTERFACE, GenericClazz.generic(objectType), methodName);
	}

	@Override
	public MethodCaller<MethodCode> SPECIAL(String objectType, String methodName) {
		return new MethodCallerImpl(Opcodes.INVOKESPECIAL, GenericClazz.generic(objectType), methodName);
	}

	@Override
	public MethodCaller<MethodCode> VIRTUAL(String objectType, String methodName) {
		return new MethodCallerImpl(Opcodes.INVOKEVIRTUAL, GenericClazz.generic(objectType), methodName);
	}

	class MethodCallerImpl implements MethodCaller<MethodCode> {
		List<GenericClazz> params = new ArrayList<>();
		GenericClazz returnClazz;

		final int opcode;
		final GenericClazz resideClazz;
		final String methodName;

		public MethodCallerImpl(int opcode, GenericClazz resideClazz, String methodName) {
			super();
			this.opcode = opcode;
			this.resideClazz = resideClazz;
			this.methodName = methodName;
		}

		@Override
		public MethodCaller<MethodCode> param(GenericClazz clazz) {
			params.add(clazz);
			return this;
		}

		@Override
		public MethodCaller<MethodCode> reTurn(GenericClazz clazz) {
			returnClazz = clazz;
			return this;
		}

		@Override
		public void INVOKE() {
			MethodCodeBuilder.this.INVOKE(opcode, typeOf(resideClazz), typeOf(returnClazz), methodName,
					typesOf(params));
		}

	}
}