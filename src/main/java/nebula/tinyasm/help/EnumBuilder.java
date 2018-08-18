package nebula.tinyasm.help;

import static nebula.tinyasm.util.TypeUtils.arrayOf;
import static nebula.tinyasm.util.TypeUtils.typeOf;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import nebula.tinyasm.ClassBuilder;
import nebula.tinyasm.data.ClassBody;

public class EnumBuilder implements Opcodes {

	public static byte[] dump(String objectType, String... names) {
		return build(objectType, names).end().toByteArray();
	}

	public static ClassBody build(String clazz, String... names) {

		ClassBody cb = ClassBuilder.make(clazz)
			.eXtend(Enum.class, clazz)
			.ACC_PUBLIC()
			.ACC_FINAL()
			.ACC_SUPER()
			.ACC_ENUM()
			.body();
		for (String name : names) {
			cb.field(ACC_PUBLIC + ACC_FINAL + ACC_STATIC + ACC_ENUM, name, clazz);
		}
		cb.field(ACC_PRIVATE + ACC_FINAL + ACC_STATIC + ACC_SYNTHETIC, "ENUM$VALUES", clazz, true);

		cb.staticMethod("<clinit>").code(mc -> {
			{
				mc.line(4);

				for (int i = 0; i < names.length; i++) {
					mc.NEW(typeOf(clazz));
					mc.DUP();
					mc.LOADConst(names[i]);
					mc.LOADConstByte(i);
					mc.INVOKESPECIAL(typeOf(clazz), Type.VOID_TYPE, "<init>", typeOf(String.class), Type.INT_TYPE);
					mc.PUTSTATIC(typeOf(clazz), names[i], typeOf(clazz));
				}

				mc.line(3).LOADConstByte(names.length);
				mc.NEWARRAY(typeOf(clazz));

				for (int i = 0; i < names.length; i++) {
					mc.DUP();
					mc.LOADConstByte(i);
					mc.GETSTATIC(typeOf(clazz), names[i], typeOf(clazz));
					mc.ARRAYSTORE();
				}
				mc.PUTSTATIC(typeOf(clazz), "ENUM$VALUES", arrayOf(typeOf(clazz)));
				mc.RETURN();
			}
		});

		cb.privateMethod("<init>").parameter("name", String.class).parameter("value", int.class).code(mc -> {
			mc.line(3).LOADThis();
			mc.LOAD("name");
			mc.LOAD("value");
			mc.INVOKESPECIAL(Enum.class, "<init>", String.class, int.class);
			mc.RETURN();
		});
		cb.staticMethod("values").ACC_STATIC().ACC_PUBLIC().reTurn(clazz,true).code(mc -> {
			mc.define("vs", clazz, true);
			mc.define("length", int.class);
			mc.define("newvs", clazz, true);
			mc.line(1);
			mc.GETSTATIC(typeOf(clazz), "ENUM$VALUES", arrayOf(typeOf(clazz)));
			mc.DUP();
			mc.STORE("vs");

			mc.LOADConstByte(0);
			mc.LOAD("vs");
			mc.ARRAYLENGTH();
			mc.DUP();
			mc.STORE("length");
			mc.NEWARRAY(typeOf(clazz));
			mc.DUP();
			mc.STORE("newvs");

			mc.LOADConstByte(0);
			mc.LOAD("length");

			mc.INVOKESTATIC(System.class, "arraycopy", Object.class, int.class, Object.class, int.class, int.class);
			mc.RETURN("newvs");
		});

		cb.staticMethod("valueOf").ACC_STATIC().ACC_PUBLIC().reTurn(clazz).parameter("name", String.class).code(mc -> {
			mc.line(1);
			mc.LOADConst(typeOf(clazz));
			mc.LOAD("name");
			mc.INVOKESTATIC(Enum.class, Enum.class, "valueOf", Class.class, String.class);
			mc.CHECKCAST(typeOf(clazz));
			mc.RETURNTop();
		});
		return cb;
	}
}