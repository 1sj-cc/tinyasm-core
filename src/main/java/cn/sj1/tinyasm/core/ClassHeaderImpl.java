package cn.sj1.tinyasm.core;

import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.objectweb.asm.ClassVisitor;

class ClassHeaderImpl implements ClassHeader {
	final String name;
	Clazz clazz;

	Clazz superClazz;

	int access;
	final ClassVisitor cv;
	final List<Annotation> annotations = new ArrayList<>();

	final List<Clazz> interfaces = new ArrayList<>();
	final List<ClazzFormalTypeParameter> classFormalTypeParameters = new ArrayList<>();

	ClassBodyImpl classBuilderImpl;

	public ClassHeaderImpl(ClassVisitor cv, String name) {
		super();
		this.cv = cv;
		this.clazz = Clazz.of(name);
		this.name = name;
	}

	public ClassHeaderImpl(ClassVisitor cv, String name, Clazz superClazz) {
		super();
		this.cv = cv;
		this.clazz = Clazz.of(name);
		this.name = name;
		this.superClazz = superClazz;
	}

	@Override
	public ClassHeader access(int access) {
		this.access |= access;
		return this;
	}

	@Override
	public ClassHeader annotation(Annotation annotation) {
		annotations.add(annotation);
		return this;
	}

	@Override
	public ClassBody body() {
		makeClassBuilder();
		return classBuilderImpl;
	}

	@Override
	public ClassBuilder body(Consumer<ClassBody> mb) {
		makeClassBuilder();
		mb.accept(classBuilderImpl);
		return classBuilderImpl;
	}

	//	@Override
	//	public ClassHeader subclass(String clazz) {
	//		this.superClazz = new ClazzType(clazz);
	//		return this;
	//	}

	@Override
	public ClassHeader extends_(Clazz clazz) {
		this.superClazz = clazz;
		return this;
	}

	@Override
	public ClassHeader formalTypeParameter(String name, Clazz clazz) {
		classFormalTypeParameters.add(Clazz.formalTypeParameterOf(name, clazz));
		return this;
	}

	@Override
	public ClassHeader implements_(Clazz clazz) {
		interfaces.add(clazz);
		return this;
	}

	//	@Override
	//	public ClassHeader implement(String clazz, String... genericClazz) {
	//		interfaces.add(new ClazzComplex(clazz, genericClazz));
	//		return this;
	//	}

	ClassBuilder makeClassBuilder() {

		if (access == 0) {// ACC_PUBLIC + ACC_SUPER
			access |= ACC_PUBLIC;
			access |= ACC_SUPER;
		}
		if ((access & ACC_INTERFACE) > 0) {

		} else {
			access |= ACC_SUPER;
		}

		if (superClazz == null) {
			this.extends_(Object.class);
		}
		if (classFormalTypeParameters.size() > 0) {
			Clazz[] typeVariable = new Clazz[classFormalTypeParameters.size()];
			for (int i = 0; i < classFormalTypeParameters.size(); i++) {
				typeVariable[i] = Clazz.typeVariableOf(((ClazzFormalTypeParameter) classFormalTypeParameters.get(i)).name);
			}
			this.clazz = Clazz.of((ClazzSimple) this.clazz, typeVariable);
		}

		classBuilderImpl = new ClassBodyImpl(cv, this);
		return classBuilderImpl;
	}

}
