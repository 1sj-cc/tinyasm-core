package cn.sj1.tinyasm.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.signature.SignatureReader;

import cn.sj1.tinyasm.core.util.PojoSample;

public class ClassSignatureDefaultVisitorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// <T:Ljava/lang/Object;>Ljava/lang/Object;
	// (TT;)V
	@Test
	public void test_typeParamenterClazzes() {
		String signature = "<T:Ljava/lang/Object;>Ljava/lang/Object;";
		ClassSignatureDefaultVisitor classSignaturewwww = new ClassSignatureDefaultVisitor(Opcodes.ASM8);
		SignatureReader sr = new SignatureReader(signature);
		sr.accept(classSignaturewwww);
		classSignaturewwww.finish();

		assertEquals("Ljava/lang/Object;", classSignaturewwww.superClazz.toString());
		assertEquals("T:Ljava/lang/Object;", classSignaturewwww.typeParamenterClazzes[0].toString());

		assertEquals(null, classSignaturewwww.returnClazz);
		assertEquals(0, classSignaturewwww.paramsClazzes.length);
		assertEquals(0, classSignaturewwww.interfaceClazzes.length);
		//		assertEquals(null, classSignaturewwww.superClazz);
		//		assertEquals(0, classSignaturewwww.typeParamenterClazzes.length);
	}

	@Test
	public void test_typesssParameterClass() {
		String signature = "Ljava/lang/Object;Lcn/sj1/tinyasm/core/hero/helperclass/GenericInterface<Lcn/sj1/tinyasm/core/hero/helperclass/PojoClassSample;Lcn/sj1/tinyasm/core/hero/helperclass/PojoClassSample;>;";
		ClassSignatureDefaultVisitor classSignaturewwww = new ClassSignatureDefaultVisitor(Opcodes.ASM8);
		SignatureReader sr = new SignatureReader(signature);
		sr.accept(classSignaturewwww);
		classSignaturewwww.finish();
		assertEquals(
				"Lcn/sj1/tinyasm/core/hero/helperclass/GenericInterface<Lcn/sj1/tinyasm/core/hero/helperclass/PojoClassSample;Lcn/sj1/tinyasm/core/hero/helperclass/PojoClassSample;>;",
				classSignaturewwww.interfaceClazzes[0].toString());
		assertEquals("Ljava/lang/Object;", classSignaturewwww.superClazz.toString());

		assertEquals(null, classSignaturewwww.returnClazz);
		assertEquals(0, classSignaturewwww.paramsClazzes.length);
		//		assertEquals(0, classSignaturewwww.interfaceClazzes.length);
		//		assertEquals(null, classSignaturewwww.superClazz);
		assertEquals(0, classSignaturewwww.typeParamenterClazzes.length);
	}

	@Test
	public void test_paramsClazzes() {
		String signature = "(TT;)V";
		ClassSignatureDefaultVisitor classSignaturewwww = new ClassSignatureDefaultVisitor(Opcodes.ASM8);
		SignatureReader sr = new SignatureReader(signature);
		sr.accept(classSignaturewwww);
		classSignaturewwww.finish();
		assertEquals("TT;", classSignaturewwww.paramsClazzes[0].toString());

		assertEquals("V", classSignaturewwww.returnClazz.toString());
		//		assertEquals(0, classSignaturewwww.paramsClazzes.length);
		assertEquals(0, classSignaturewwww.interfaceClazzes.length);
		assertEquals(null, classSignaturewwww.superClazz);
		assertEquals(0, classSignaturewwww.typeParamenterClazzes.length);
	}

	@Test
	public void test_paramsClazzes2() {
		String signature = "(TT;Ljava/lang/String;)[Ljava/lang/String;";
		ClassSignatureDefaultVisitor classSignaturewwww = new ClassSignatureDefaultVisitor(Opcodes.ASM8);
		SignatureReader sr = new SignatureReader(signature);
		sr.accept(classSignaturewwww);
		classSignaturewwww.finish();
		assertEquals("[Ljava/lang/String;", classSignaturewwww.returnClazz.toString());
		assertEquals("TT;", classSignaturewwww.paramsClazzes[0].toString());
		assertEquals("Ljava/lang/String;", classSignaturewwww.paramsClazzes[1].toString());

		//		assertEquals(null, classSignaturewwww.returnClazz);
		//		assertEquals(0, classSignaturewwww.paramsClazzes.length);
		assertEquals(0, classSignaturewwww.interfaceClazzes.length);
		assertEquals(null, classSignaturewwww.superClazz);
		assertEquals(0, classSignaturewwww.typeParamenterClazzes.length);
	}

	@Test
	public void test_return() {
		String signature = "()TT;";
		ClassSignatureDefaultVisitor classSignaturewwww = new ClassSignatureDefaultVisitor(Opcodes.ASM8);
		SignatureReader sr = new SignatureReader(signature);
		sr.accept(classSignaturewwww);
		classSignaturewwww.finish();
		assertEquals("TT;", classSignaturewwww.returnClazz.toString());

		//		assertEquals(null, classSignaturewwww.returnClazz);
		assertEquals(0, classSignaturewwww.paramsClazzes.length);
		assertEquals(0, classSignaturewwww.interfaceClazzes.length);
		assertEquals(null, classSignaturewwww.superClazz);
		assertEquals(0, classSignaturewwww.typeParamenterClazzes.length);

	}

	@Test
	public void test_generic_method() {
		String signature = "<T:Ljava/lang/Object;>([TT;)[TT;";
		ClassSignatureDefaultVisitor classSignaturewwww = new ClassSignatureDefaultVisitor(Opcodes.ASM8);
		SignatureReader sr = new SignatureReader(signature);
		sr.accept(classSignaturewwww);
		classSignaturewwww.finish();
		assertEquals("[TT;", classSignaturewwww.returnClazz.toString());

		//		assertEquals(null, classSignaturewwww.returnClazz);
		assertEquals("[TT;", classSignaturewwww.paramsClazzes[0].toString());
		assertEquals(0, classSignaturewwww.interfaceClazzes.length);
		assertEquals(null, classSignaturewwww.superClazz);
		assertEquals("T:Ljava/lang/Object;", classSignaturewwww.typeParamenterClazzes[0].toString());
	}

	@Test
	public void test_generic_user_method() {
		String signature = "<S:Lcn/sj1/nebula/data/support/User;>(Ljava/lang/Iterable<TS;>;)Ljava/lang/Iterable<TS;>;";
		ClassSignatureDefaultVisitor classSignaturewwww = new ClassSignatureDefaultVisitor(Opcodes.ASM8);
		SignatureReader sr = new SignatureReader(signature);
		sr.accept(classSignaturewwww);
		classSignaturewwww.finish();
		assertEquals("Ljava/lang/Iterable<TS;>;", classSignaturewwww.returnClazz.toString());

		//		assertEquals(null, classSignaturewwww.returnClazz);
		assertEquals("Ljava/lang/Iterable<TS;>;", classSignaturewwww.paramsClazzes[0].toString());
		assertEquals(0, classSignaturewwww.interfaceClazzes.length);
		assertEquals(null, classSignaturewwww.superClazz);
		assertEquals("S:Lcn/sj1/nebula/data/support/User;", classSignaturewwww.typeParamenterClazzes[0].toString());
	}

	@Test
	public void test_basetype() {
		String signature = "(Ljava/util/Collection<*>;)Z";
		ClassSignatureDefaultVisitor classSignaturewwww = new ClassSignatureDefaultVisitor(Opcodes.ASM8);
		SignatureReader sr = new SignatureReader(signature);
		sr.accept(classSignaturewwww);
		classSignaturewwww.finish();
		assertEquals("Z", classSignaturewwww.returnClazz.toString());

		//		assertEquals(null, classSignaturewwww.returnClazz);
		assertEquals("Ljava/util/Collection<*>;", classSignaturewwww.paramsClazzes[0].toString());
		assertEquals("Ljava/util/Collection<*>;", classSignaturewwww.paramsClazzes[0].signatureOf());
		assertEquals(0, classSignaturewwww.interfaceClazzes.length);
		assertEquals(null, classSignaturewwww.superClazz);
		assertEquals(0, classSignaturewwww.typeParamenterClazzes.length);
	}
	// <T:Ljava/lang/Object;>([TT;)[TT;
	//	

	@Test
	public void test_basetypessssss() {
		String signature = "(ILjava/util/Collection<+Lcn/sj1/tinyasm/core/hero/helperclass/PojoClassSample;>;)Z";
		ClassSignatureDefaultVisitor classSignaturewwww = new ClassSignatureDefaultVisitor(Opcodes.ASM8);
		SignatureReader sr = new SignatureReader(signature);
		sr.accept(classSignaturewwww);
		classSignaturewwww.finish();
		assertEquals("Z", classSignaturewwww.returnClazz.toString());

		//		assertEquals(null, classSignaturewwww.returnClazz);
		assertEquals("I", classSignaturewwww.paramsClazzes[0].toString());
		assertEquals("Ljava/util/Collection<+Lcn/sj1/tinyasm/core/hero/helperclass/PojoClassSample;>;",
				classSignaturewwww.paramsClazzes[1].toString());
		assertTrue(classSignaturewwww.paramsClazzes[1] instanceof ClazzWithTypeArguments);
		assertTrue(((ClazzWithTypeArguments) classSignaturewwww.paramsClazzes[1]).getTypeArguments()[0] instanceof ClazzTypeArgument);
		assertTrue(((ClazzTypeArgument) ((ClazzWithTypeArguments) classSignaturewwww.paramsClazzes[1])
				.getTypeArguments()[0]).clazz instanceof ClazzSimple);
		//		assertEquals("Ljava/util/Collection<*>;", classSignaturewwww.paramsClazzes[0].signatureOf());
		assertEquals(0, classSignaturewwww.interfaceClazzes.length);
		assertEquals(null, classSignaturewwww.superClazz);
		assertEquals(0, classSignaturewwww.typeParamenterClazzes.length);
	}

	@Test
	public void test_basetypessssss_() {
		String signature = "(Ljava/util/function/Consumer<-TE;>;)V";
		ClassSignatureDefaultVisitor classSignaturewwww = new ClassSignatureDefaultVisitor(Opcodes.ASM8);
		SignatureReader sr = new SignatureReader(signature);
		sr.accept(classSignaturewwww);
		classSignaturewwww.finish();
		assertEquals("V", classSignaturewwww.returnClazz.toString());

		List<ClazzFormalTypeParameter> clazzFormalTypeParameters = new ArrayList<>();
		ClazzFormalTypeParameter f = new ClazzFormalTypeParameter("E", Clazz.of(Object.class));
		f.setActualTypeArgument(Clazz.of(PojoSample.class));
		clazzFormalTypeParameters.add(f);

		//		assertEquals(null, classSignaturewwww.returnClazz);
		assertEquals("Ljava/util/function/Consumer;", classSignaturewwww.paramsClazzes[0].getDescriptor());
		assertTrue(classSignaturewwww.paramsClazzes[0] instanceof ClazzWithTypeArguments);
		assertEquals("Ljava/util/function/Consumer<-TE;>;", classSignaturewwww.paramsClazzes[0].signatureOf());
		assertEquals("Ljava/util/function/Consumer<-Lcn/sj1/tinyasm/core/util/PojoSample;>;",
				classSignaturewwww.paramsClazzes[0].signatureOf(clazzFormalTypeParameters));
		//		assertEquals("Ljava/util/Collection<*>;", classSignaturewwww.paramsClazzes[0].signatureOf());
		assertEquals(0, classSignaturewwww.interfaceClazzes.length);
		assertEquals(null, classSignaturewwww.superClazz);
		assertEquals(0, classSignaturewwww.typeParamenterClazzes.length);
	}

	@Test
	public void test_basetypessddddssss_() {
		String signature = "()Ljava/util/Spliterator<TT;>;";
		ClassSignatureDefaultVisitor classSignaturewwww = new ClassSignatureDefaultVisitor(Opcodes.ASM8);
		SignatureReader sr = new SignatureReader(signature);
		sr.accept(classSignaturewwww);
		classSignaturewwww.finish();
		assertEquals("Ljava/util/Spliterator<TT;>;", classSignaturewwww.returnClazz.toString());
		assertTrue(classSignaturewwww.returnClazz instanceof ClazzWithTypeArguments);
		assertTrue(((ClazzWithTypeArguments) classSignaturewwww.returnClazz).getTypeArguments()[0] instanceof ClazzTypeArgument);
		assertTrue(((ClazzTypeArgument) ((ClazzWithTypeArguments) classSignaturewwww.returnClazz)
				.getTypeArguments()[0]).clazz instanceof ClazzVariable);

		List<ClazzFormalTypeParameter> clazzFormalTypeParameters = new ArrayList<>();
		ClazzFormalTypeParameter f = new ClazzFormalTypeParameter("E", Clazz.of(Object.class));
		f.setActualTypeArgument(Clazz.of(PojoSample.class));
		clazzFormalTypeParameters.add(f);

		//		assertEquals(null, classSignaturewwww.returnClazz);
		assertEquals(0, classSignaturewwww.paramsClazzes.length);
		assertEquals(0, classSignaturewwww.interfaceClazzes.length);
		assertEquals(null, classSignaturewwww.superClazz);
		assertEquals(0, classSignaturewwww.typeParamenterClazzes.length);
	}

}
