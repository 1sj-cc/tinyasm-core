package cc1sj.tinyasm.sample.ClassBody;

import java.util.ArrayList;
import java.util.List;

import cc1sj.tinyasm.Annotation;

@SuppressWarnings("serial")
public class ParameterGenericSample extends ArrayList<Annotation> implements TestInerface, List<Annotation> {

	@SuppressWarnings("unused")
	private List<String> annotation;

	@SuppressWarnings("unused")
	private List<String> annotationMethod() {
		return null;
	}

	public void method(List<String> annotation) {
		this.annotation = annotation;
	}

	@SuppressWarnings("unused")
	public void methodGenericVar(List<String> annotation) {
		@TestAnnotation List<String> thisannotation = annotation;
		this.annotation = annotation;
	}

}
