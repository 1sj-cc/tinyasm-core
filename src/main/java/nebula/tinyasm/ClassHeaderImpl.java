package nebula.tinyasm;

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

	ClassBodyImpl classBuilderImpl;

	public ClassHeaderImpl(ClassVisitor cv, String name) {
		super();
		this.cv = cv;
		this.name = name;
	}

	public ClassHeaderImpl(ClassVisitor cv, String name, Class<?> superClazz) {
		super();
		this.cv = cv;
		this.name = name;
		this.superClazz = Clazz.of(superClazz);
	}

	public ClassHeaderImpl(ClassVisitor cv, String name, String superClazz) {
		super();
		this.cv = cv;
		this.name = name;
		String[] genericParameterClazz = {};
		this.superClazz = Clazz.of(superClazz, genericParameterClazz);
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
	public ClassHeader eXtend(Clazz clazz) {
		this.superClazz = clazz;
		return this;
	}

	@Override
	public ClassHeader implement(Clazz clazz) {
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
			this.ACC_PUBLIC();
			this.ACC_SUPER();
		}

		if (superClazz == null) {
			this.eXtend(Object.class);
		}

		classBuilderImpl = new ClassBodyImpl(cv, this);
		return classBuilderImpl;
	}

}
