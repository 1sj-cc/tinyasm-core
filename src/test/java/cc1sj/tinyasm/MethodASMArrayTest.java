package cc1sj.tinyasm;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Label;

import cc1sj.tinyasm.util.MethodASMArraySample;
import cc1sj.tinyasm.util.TinyAsmTestUtils;

public class MethodASMArrayTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMath() throws Exception {
		String clazz = MethodASMArraySample.class.getName();
		ClassBody cw = ClassBuilder.make(clazz).body();

		cw.field("ba", byte.class, true);

		cw.field("ca", char.class, true);

		cw.field("sa", short.class, true);

		cw.field("ia", int.class, true);

		cw.field("la", long.class, true);

		cw.field("fa", float.class, true);

		cw.field("da", double.class, true);

		cw.field("stra", String.class, true);

		cw.method("<init>").code(mv -> {
			Label l0 = mv.codeNewLabel();
			mv.LINE();
			mv.visitLabel(l0);
			mv.LOAD(mv._THIS);
			mv.SPECIAL(Object.class, "<init>").INVOKE();
			mv.RETURN();
			Label l1 = mv.codeNewLabel();
			mv.visitLabel(l1);
		});
//
		cw.method("init").code(mv -> {
			Label l0 = mv.codeNewLabel();
			mv.visitLabel(l0);

			mv.LINE();
			mv.LOAD(0);
			mv.LOADConstByte(10);
			mv.NEWARRAY(byte.class);
			mv.PUTFIELD_OF_THIS("ba");
			Label l1 = mv.codeNewLabel();
			mv.visitLabel(l1);

			mv.LINE();
			mv.LOAD(0);
			mv.LOADConstByte(10);
			mv.NEWARRAY(char.class);
			mv.PUTFIELD_OF_THIS("ca");
			Label l2 = mv.codeNewLabel();
			mv.visitLabel(l2);

			mv.LINE();
			mv.LOAD(0);
			mv.LOADConstByte(10);
			mv.NEWARRAY(short.class);
			mv.PUTFIELD_OF_THIS("sa");
			Label l3 = mv.codeNewLabel();
			mv.visitLabel(l3);

			mv.LINE();
			mv.LOAD(0);
			mv.LOADConstByte(10);
			mv.NEWARRAY(int.class);
			mv.PUTFIELD_OF_THIS("ia");
			Label l4 = mv.codeNewLabel();
			mv.visitLabel(l4);

			mv.LINE();
			mv.LOAD(0);
			mv.LOADConstByte(10);
			mv.NEWARRAY(long.class);
			mv.PUTFIELD_OF_THIS("la");
			Label l5 = mv.codeNewLabel();
			mv.visitLabel(l5);

			mv.LINE();
			mv.LOAD(0);
			mv.LOADConstByte(10);
			mv.NEWARRAY(float.class);
			mv.PUTFIELD_OF_THIS("fa");
			Label l6 = mv.codeNewLabel();
			mv.visitLabel(l6);

			mv.LINE();
			mv.LOAD(0);
			mv.LOADConstByte(10);
			mv.NEWARRAY(double.class);
			mv.PUTFIELD_OF_THIS("da");
			Label l7 = mv.codeNewLabel();
			mv.visitLabel(l7);

			mv.LINE();
			mv.LOAD(0);
			mv.LOADConstByte(10);
			mv.NEWARRAY("java/lang/String");
			mv.PUTFIELD_OF_THIS("stra");
			Label l8 = mv.codeNewLabel();
			mv.visitLabel(l8);

			mv.LINE();
			mv.RETURN();
			Label l9 = mv.codeNewLabel();
			mv.visitLabel(l9);
		});

		cw.method("setArrayValue").code(mv -> {
			Label l0 = mv.codeNewLabel();
			mv.visitLabel(l0);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("ba");
			mv.LOADConstByte(0);
			mv.LOADConstByte(100);
			mv.ARRAYSTORE();
			Label l1 = mv.codeNewLabel();
			mv.visitLabel(l1);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("ca");
			mv.LOADConstByte(0);
			mv.LOADConstShort(1000);
			mv.ARRAYSTORE();
			Label l2 = mv.codeNewLabel();
			mv.visitLabel(l2);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("sa");
			mv.LOADConstByte(0);
			mv.LOADConstShort(10000);
			mv.ARRAYSTORE();
			Label l3 = mv.codeNewLabel();
			mv.visitLabel(l3);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("ia");
			mv.LOADConstByte(0);
			mv.LOADConst(Integer.valueOf(100000));
			mv.ARRAYSTORE();
			Label l4 = mv.codeNewLabel();
			mv.visitLabel(l4);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("la");
			mv.LOADConstByte(0);
			mv.LOADConst(Long.valueOf(1000000L));
			mv.ARRAYSTORE();
			Label l5 = mv.codeNewLabel();
			mv.visitLabel(l5);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("fa");
			mv.LOADConstByte(0);
			mv.LOADConst(Float.valueOf("1.0E7"));
			mv.ARRAYSTORE();
			Label l6 = mv.codeNewLabel();
			mv.visitLabel(l6);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("da");
			mv.LOADConstByte(0);
			mv.LOADConst(Double.valueOf("1.0E8"));
			mv.ARRAYSTORE();
			Label l7 = mv.codeNewLabel();
			mv.visitLabel(l7);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("stra");
			mv.LOADConstByte(0);
			mv.LOADConst("1000000000s");
			mv.ARRAYSTORE();
			Label l8 = mv.codeNewLabel();
			mv.visitLabel(l8);
			mv.LINE();
			mv.RETURN();
			Label l9 = mv.codeNewLabel();
			mv.visitLabel(l9);
		});

		cw.method("getArrayValue").code(mv -> {
			Label l0 = mv.codeNewLabel();
			mv.visitLabel(l0);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("ba");
			mv.LOADConst(1);
			mv.LOAD(0);
			mv.GET_THIS_FIELD("ba");
			mv.LOADConstByte(0);
			mv.ARRAYLOAD();
			mv.ARRAYSTORE();
			Label l1 = mv.codeNewLabel();
			mv.visitLabel(l1);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("ca");
			mv.LOADConst(1);
			mv.LOAD(0);
			mv.GET_THIS_FIELD("ca");
			mv.LOADConstByte(0);
			mv.ARRAYLOAD();
			mv.ARRAYSTORE();
			Label l2 = mv.codeNewLabel();
			mv.visitLabel(l2);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("sa");
			mv.LOADConst(1);
			mv.LOAD(0);
			mv.GET_THIS_FIELD("sa");
			mv.LOADConstByte(0);
			mv.ARRAYLOAD();
			mv.ARRAYSTORE();
			Label l3 = mv.codeNewLabel();
			mv.visitLabel(l3);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("ia");
			mv.LOADConst(1);
			mv.LOAD(0);
			mv.GET_THIS_FIELD("ia");
			mv.LOADConstByte(0);
			mv.ARRAYLOAD();
			mv.ARRAYSTORE();
			Label l4 = mv.codeNewLabel();
			mv.visitLabel(l4);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("la");
			mv.LOADConst(1);
			mv.LOAD(0);
			mv.GET_THIS_FIELD("la");
			mv.LOADConstByte(0);
			mv.ARRAYLOAD();
			mv.ARRAYSTORE();
			Label l5 = mv.codeNewLabel();
			mv.visitLabel(l5);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("fa");
			mv.LOADConst(1);
			mv.LOAD(0);
			mv.GET_THIS_FIELD("fa");
			mv.LOADConstByte(0);
			mv.ARRAYLOAD();
			mv.ARRAYSTORE();
			Label l6 = mv.codeNewLabel();
			mv.visitLabel(l6);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("da");
			mv.LOADConst(1);
			mv.LOAD(0);
			mv.GET_THIS_FIELD("da");
			mv.LOADConstByte(0);
			mv.ARRAYLOAD();
			mv.ARRAYSTORE();
			Label l7 = mv.codeNewLabel();
			mv.visitLabel(l7);
			mv.LINE();
			mv.LOAD(0);
			mv.GET_THIS_FIELD("stra");
			mv.LOADConst(1);
			mv.LOAD(0);
			mv.GET_THIS_FIELD("stra");
			mv.LOADConstByte(0);
			mv.ARRAYLOAD();
			mv.ARRAYSTORE();
			Label l8 = mv.codeNewLabel();
			mv.visitLabel(l8);
			mv.LINE();
			mv.RETURN();
			Label l9 = mv.codeNewLabel();
			mv.visitLabel(l9);
		});

		String codeActual = TinyAsmTestUtils.toString(cw.end().toByteArray());
		String codeExpected = TinyAsmTestUtils.toString(clazz);
		assertEquals("Code", codeExpected, codeActual);
	}

//	@Test
//	public void printClass() throws IOException {
//		System.out.println(refineCode(toString(MethodASMArraySample.class.getName())));
//	}
}
