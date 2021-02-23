package cc1sj.tinyasm;

import static cc1sj.tinyasm.TypeUtils.typeOf;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class Annotation {
	final public String clazz;

	final public Object defaultValue;

	final public String[] names;
	final public Object[] values;

	public Annotation(String clazz, Object defaultValue, String[] names, Object[] values) {
		super();
		this.clazz = clazz;
		this.defaultValue = defaultValue;
		this.names = names;
		this.values = values;
	}

	public Annotation(String clazz, Object defaultValue) {
		super();
		this.clazz = clazz;
		this.defaultValue = defaultValue;
		this.names = new String[0];
		this.values = new Object[0];
	}

	static public Annotation annotation(Annotation annotation) {
		return annotation;
	}

	static public Annotation of(Class<?> clazz) {
		return of(clazz.getName(), null);
	}

	static public Annotation of(String clazz) {
		return of(clazz, null);
	}

	static public Annotation of(Class<?> clazz, Object value) {
		return of(clazz.getName(), value);
	}

	static public Annotation of(String clazz, Object value) {
		return new Annotation(clazz, value);
	}

	static public Annotation of(String clazz, Object value, String[] names, Object[] values) {
		return new Annotation(clazz, value, names, values);
	}

	static public Annotation of(String clazz, String[] names, Object[] values) {
		return of(clazz, null, names, values);
	}

	static public Annotation of(Class<?> clazz, String[] names, Object[] values) {
		return of(clazz.getName(), names, values);
	}

	static public Annotation of(Class<?> clazz, Object value, String[] names, Object[] values) {
		return of(clazz.getName(), value, names, values);
	}

	public String getDescriptor() {
		return typeOf(clazz).getDescriptor();
	}


	public static void visitParameterAnnotation(MethodVisitor mv, int parameter, Annotation annotation) {
		AnnotationVisitor av0 = mv.visitParameterAnnotation(parameter, annotation.getDescriptor(), true);
		if (annotation.defaultValue != null) {
			av0.visit("value", annotation.defaultValue);
		}
		if (annotation.names != null) {
			for (int i = 0; i < annotation.names.length; i++) {
				av0.visit(annotation.names[i], annotation.values[i]);
			}
		}
		av0.visitEnd();
	}
	public static void visitAnnotation(FieldVisitor fv, Annotation annotation) {
		AnnotationVisitor av0 = fv.visitAnnotation(annotation.getDescriptor(), true);
		if (annotation.defaultValue != null) {
			av0.visit("value", annotation.defaultValue);
		}
		if (annotation.names != null) {
			for (int i = 0; i < annotation.names.length; i++) {
				av0.visit(annotation.names[i], annotation.values[i]);
			}
		}
		av0.visitEnd();
	}
	public static void visitAnnotation(ClassVisitor cv, Annotation annotation) {
		AnnotationVisitor av0 = cv.visitAnnotation(annotation.getDescriptor(), true);
		if (annotation.defaultValue != null) {
			av0.visit("value", annotation.defaultValue);
		}
		if (annotation.names != null) {
			for (int i = 0; i < annotation.names.length; i++) {
				av0.visit(annotation.names[i], annotation.values[i]);
			}
		}
		av0.visitEnd();
	}
	public static void visitAnnotation(MethodVisitor mv, Annotation annotation) {
		AnnotationVisitor av0 = mv.visitAnnotation(annotation.getDescriptor(), true);
		if (annotation.defaultValue != null) {
			av0.visit("value", annotation.defaultValue);
		}
		if (annotation.names != null) {
			for (int i = 0; i < annotation.names.length; i++) {
				av0.visit(annotation.names[i], annotation.values[i]);
			}
		}
		av0.visitEnd();
	}

}