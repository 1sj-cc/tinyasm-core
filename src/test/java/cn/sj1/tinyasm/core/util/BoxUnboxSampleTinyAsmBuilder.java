package cn.sj1.tinyasm.core.util;

import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import static org.objectweb.asm.Opcodes.*;

import cn.sj1.tinyasm.core.Annotation;
import cn.sj1.tinyasm.core.ClassBody;
import cn.sj1.tinyasm.core.ClassBuilder;
import cn.sj1.tinyasm.core.Clazz;
import cn.sj1.tinyasm.core.MethodCode;

@SuppressWarnings("unused")
public class BoxUnboxSampleTinyAsmBuilder {

	public static byte[] dump() throws Exception {
		return new BoxUnboxSampleTinyAsmBuilder().build("cn.sj1.tinyasm.core.util.BoxUnboxSample");
	}

	public byte[] build(String className) throws Exception {
		ClassBody classBody = ClassBuilder.class_(className)
				.access(ACC_PUBLIC | ACC_SUPER).body();

		classBody.field("z", Clazz.of(boolean.class));
		classBody.field("b", Clazz.of(byte.class));
		classBody.field("c", Clazz.of(char.class));
		classBody.field("s", Clazz.of(short.class));
		classBody.field("i", Clazz.of(int.class));
		classBody.field("l", Clazz.of(long.class));
		classBody.field("f", Clazz.of(float.class));
		classBody.field("d", Clazz.of(double.class));
		__init_(classBody);
		_exec(classBody);
		_zFunc(classBody);
		_bFunc(classBody);
		_cFunc(classBody);
		_sFunc(classBody);
		_iFunc(classBody);
		_lFunc(classBody);
		_fFunc(classBody);
		_dFunc(classBody);

		return classBody.end().toByteArray();
	}

	protected void __init_(ClassBody classBody) {
		MethodCode code = classBody.public_().method("<init>").begin();

		code.LINE();
		code.LOAD("this");
		code.SPECIAL(Object.class, "<init>").INVOKE();

		code.LINE();
		code.LOAD("this");
		code.LOADConst(1);
		code.PUTFIELD("z", boolean.class);

		code.LINE();
		code.LOAD("this");
		code.LOADConst(1);
		code.PUTFIELD("b", byte.class);

		code.LINE();
		code.LOAD("this");
		code.LOADConst(1);
		code.PUTFIELD("c", char.class);

		code.LINE();
		code.LOAD("this");
		code.LOADConst(1);
		code.PUTFIELD("s", short.class);

		code.LINE();
		code.LOAD("this");
		code.LOADConst(1);
		code.PUTFIELD("i", int.class);

		code.LINE();
		code.LOAD("this");
		code.LOADConst(1L);
		code.PUTFIELD("l", long.class);

		code.LINE();
		code.LOAD("this");
		code.LOADConst(1F);
		code.PUTFIELD("f", float.class);

		code.LINE();
		code.LOAD("this");
		code.LOADConst(1D);
		code.PUTFIELD("d", double.class);
		code.RETURN();

		code.END();
	}

	protected void _exec(ClassBody classBody) {
		MethodCode code = classBody.public_().method("exec").begin();

		code.LINE();
		code.LOAD("this");
		code.LOAD("this");
		code.GETFIELD("z", boolean.class);
		code.BOX_Top();
		code.VIRTUAL("zFunc")
				.return_(boolean.class)
				.parameter(Boolean.class).INVOKE();
		code.POP();

		code.LINE();
		code.LOAD("this");
		code.LOAD("this");
		code.GETFIELD("b", byte.class);
		code.BOX_Top();
		code.VIRTUAL("bFunc")
				.return_(byte.class)
				.parameter(Byte.class).INVOKE();
		code.POP();

		code.LINE();
		code.LOAD("this");
		code.LOAD("this");
		code.GETFIELD("c", char.class);
		code.BOX_Top();
		code.VIRTUAL("cFunc")
				.return_(char.class)
				.parameter(Character.class).INVOKE();
		code.POP();

		code.LINE();
		code.LOAD("this");
		code.LOAD("this");
		code.GETFIELD("s", short.class);
		code.BOX_Top();
		code.VIRTUAL("sFunc")
				.return_(short.class)
				.parameter(Short.class).INVOKE();
		code.POP();

		code.LINE();
		code.LOAD("this");
		code.LOAD("this");
		code.GETFIELD("i", int.class);
		code.BOX_Top();
		code.VIRTUAL("iFunc")
				.return_(int.class)
				.parameter(Integer.class).INVOKE();
		code.POP();

		code.LINE();
		code.LOAD("this");
		code.LOAD("this");
		code.GETFIELD("l", long.class);
		code.BOX_Top();
		code.VIRTUAL("lFunc")
				.return_(long.class)
				.parameter(Long.class).INVOKE();
		code.POP();

		code.LINE();
		code.LOAD("this");
		code.LOAD("this");
		code.GETFIELD("f", float.class);
		code.BOX_Top();
		code.VIRTUAL("fFunc")
				.return_(float.class)
				.parameter(Float.class).INVOKE();
		code.POP();

		code.LINE();
		code.LOAD("this");
		code.LOAD("this");
		code.GETFIELD("d", double.class);
		code.BOX_Top();
		code.VIRTUAL("dFunc")
				.return_(double.class)
				.parameter(Double.class).INVOKE();
		code.POP();

		code.LINE();
		code.RETURN();

		code.END();
	}

	protected void _zFunc(ClassBody classBody) {
		MethodCode code = classBody.method("zFunc")
				.return_(boolean.class)
				.parameter("oz", Boolean.class).begin();

		code.LINE();
		code.LOAD("oz");
		code.UNBOX_Top();
		code.RETURNTop();

		code.END();
	}

	protected void _bFunc(ClassBody classBody) {
		MethodCode code = classBody.method("bFunc")
				.return_(byte.class)
				.parameter("ob", Byte.class).begin();

		code.LINE();
		code.LOAD("ob");
		code.UNBOX_Top();
		code.RETURNTop();

		code.END();
	}

	protected void _cFunc(ClassBody classBody) {
		MethodCode code = classBody.method("cFunc")
				.return_(char.class)
				.parameter("oc", Character.class).begin();

		code.LINE();
		code.LOAD("oc");
		code.UNBOX_Top();
		code.RETURNTop();

		code.END();
	}

	protected void _sFunc(ClassBody classBody) {
		MethodCode code = classBody.method("sFunc")
				.return_(short.class)
				.parameter("os", Short.class).begin();

		code.LINE();
		code.LOAD("os");
		code.UNBOX_Top();
		code.RETURNTop();

		code.END();
	}

	protected void _iFunc(ClassBody classBody) {
		MethodCode code = classBody.method("iFunc")
				.return_(int.class)
				.parameter("oi", Integer.class).begin();

		code.LINE();
		code.LOAD("oi");
		code.UNBOX_Top();
		code.RETURNTop();

		code.END();
	}

	protected void _lFunc(ClassBody classBody) {
		MethodCode code = classBody.method("lFunc")
				.return_(long.class)
				.parameter("ol", Long.class).begin();

		code.LINE();
		code.LOAD("ol");
		code.UNBOX_Top();
		code.RETURNTop();

		code.END();
	}

	protected void _fFunc(ClassBody classBody) {
		MethodCode code = classBody.method("fFunc")
				.return_(float.class)
				.parameter("of", Float.class).begin();

		code.LINE();
		code.LOAD("of");
		code.UNBOX_Top();
		code.RETURNTop();

		code.END();
	}

	protected void _dFunc(ClassBody classBody) {
		MethodCode code = classBody.method("dFunc")
				.return_(double.class)
				.parameter("od", Double.class).begin();

		code.LINE();
		code.LOAD("od");
		code.UNBOX_Top();
		code.RETURNTop();

		code.END();
	}

}
