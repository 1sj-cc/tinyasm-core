package cc1sj.tinyasm.sourceconverter;

import static cc1sj.tinyasm.Adv.*;

import cc1sj.tinyasm.AdvClassBuilder;

public class SimpleSampleRefer2MagicBuilder {

	final String name = null;// = clazz.public_().field("name", String.class);

	public void _dump_fields(AdvClassBuilder classBody) {
		classBody.private_().field("name", String.class);
	}

	public void _dump_init(AdvClassBuilder classBody) {
		classBody.public_().method("<init>").code(code -> {
			code.LINE();
			code.LOAD("this");
			code.SPECIAL(classBody.getSuperClazz(), "<init>").INVOKE();
		});
	}

	public void sayHello() { // public void sayHello() {
		int i = __("i", 10); // int i = 10;
		int j = __("j", 20); // int j = 20;
		__(j, add(i, j)); // j = i + j;

		ReferReferedObject referedObject = __("referedObject", new_(ReferReferedObject.class));// ReferedObject referedObject = new
																								// ReferedObject();
		referedObject.sayHello(); // referedObject.sayHello();
		String helloString = __("helloString", referedObject.getHelloString());// String helloString = referedObject.getHelloString();
		referedObject.setHelloString("sayNothing"); // referedObject.setHelloString("sayNothing");
		referedObject.setHelloString(helloString); // referedObject.setHelloString(helloString);
		ReferRefered2Object refered2Object = __("refered2Object", referedObject.getReferRefered2Object());
		refered2Object.sayHello();

		_if(isGreaterThan(j, 10)).then(c -> { // if (j > 10) {
			__(j, add(i, j)); // j = i + j;
		}).else_(c -> { // } else {
			__(j, add(i, j)); // j = i + j;
		}); // }

		__(j, add(i, j)); // j = i + j;

		_while(isGreaterThan(j, 10)).block(c -> { // while (j > 10) {
			__(j, add(i, j)); // j = i + j;
		}); // }

		referedObject.setHelloString(helloString); // referedObject.setHelloString(helloString);

		_do(c -> { // do {
			__(j, add(i, j)); // j = i + j;
		}).while_(isGreaterThan(j, 10)); // } while (j > 10);

		int k = __("k", 0); // int k = 0;
		_for(isGreaterThan(k, 10), c -> _inc(k, 1)).block(c -> { // for (; k > 10; k++) {
			__(j, add(i, j)); // j = i + j;
		}); // } //}

	}

	public static byte[] dump() {
		return dumpClass(public_().class_("cc1sj.tinyasm.sourceconverter.SimpleSampleRefer2").extends_(SuperClass.class)
				.implements_(SuperInterface.class).enterClassBody(), new SimpleSampleRefer2MagicBuilder());
	}
}