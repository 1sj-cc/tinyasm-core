package nebula.tinyasm;

import static nebula.tinyasm.util.TypeUtils.*;

public interface WithAnnotation<B> {

	default B annotation(Class<?> clazz) {
		return annotation(classnameOf(clazz), null);
	}

	default B annotation(String clazz) {
		return annotation(clazz, null);
	}

	default B annotation(Class<?> clazz, Object value) {
		return annotation(classnameOf(clazz), value);
	}

	default B annotation(String clazz, Object value) {
		return annotation(clazz, value, null, null);
	}

	default B annotation(String clazz, Object value, String[] names, Object[] values) {
		return annotation(Annotation.annotation(clazz, value, names, values));
	}

	B annotation(Annotation annotation);

	default B annotation(String clazz, String[] names, Object[] values) {
		return annotation(clazz, null, names, values);
	}

	default B annotation(Class<?> annotationClass, String[] names, Object[] values) {
		return annotation(classnameOf(annotationClass), names, values);
	}

	default B annotation(Class<?> annotationClass, String name, Object value) {
		return annotation(classnameOf(annotationClass), new String[] { name }, new Object[] { value });
	}

	default B annotation(String annotationClass, String name, Object value) {
		return annotation(annotationClass, new String[] { name }, new Object[] { value });
	}

	default B annotation(Class<?> annotationClass, Object value, String[] names, Object[] values) {
		return annotation(classnameOf(annotationClass), value, names, values);
	}

}