package nebula.tinyasm.util;
import org.objectweb.asm.Label;
import nebula.tinyasm.ClassBody;
import nebula.tinyasm.ClassBuilder;
import nebula.tinyasm.MethodCode;
import org.objectweb.asm.Type;
import static org.objectweb.asm.Opcodes.*;
import nebula.tinyasm.Clazz;
@SuppressWarnings("unused")
public class SimpleSampleTinyAsmDump {

public static byte[] dump () throws Exception {

ClassBody classWriter = ClassBuilder.make("nebula.tinyasm.util.SimpleSample").body();

classWriter.field(ACC_PRIVATE, "i", Clazz.of(int.class));
classWriter.method(ACC_PUBLIC, "<init>").code(code -> {

	code.LINE(6);
	code.LOAD("this");
	code.SPECIAL(java.lang.Object.class, "<init>").INVOKE();

	code.LINE(4);
	code.LOAD("this");
	code.LOADConst(0);
	code.PUTFIELD("i", int.class);

	code.LINE(8);
	code.RETURN();
});
classWriter.method(ACC_PUBLIC, "dd").code(code -> {

	code.LINE(11);
	code.LOADConst(1);
	code.STORE("j",int.class);

	code.LINE(12);
	code.LOAD("this");
	code.LOADConst(1);
	code.PUTFIELD("i", int.class);

	code.LINE(13);
	code.LOAD("this");
	code.LOAD("j");
	code.LOADConst(10);
	code.ADD();
	code.PUTFIELD("i", int.class);

	code.LINE(14);
	code.LOAD("this");
	code.GETFIELD("i", int.class);
	code.LOADConst(10);
	code.ADD();
	code.STORE("j");

	code.LINE(15);
	code.RETURN();
});
classWriter.method(ACC_PUBLIC, "methodWith1Param").parameter("i",int.class).code(code -> {

	code.LINE(18);
	code.LOAD("this");
	code.LOAD("i");
	code.PUTFIELD("i", int.class);

	code.LINE(19);
	code.RETURN();
});
return classWriter.end().toByteArray();
}
}
