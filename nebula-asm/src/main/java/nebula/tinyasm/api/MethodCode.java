package nebula.tinyasm.api;

import java.util.function.Consumer;
import java.util.function.Function;

import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

import nebula.tinyasm.SmartOpcode;

import static nebula.tinyasm.api.TypeUtils.*;

public interface MethodCode<M, C extends MethodCode<M, C>> extends SmartOpcode {

	C accessLabel(Label label);

	C accessLabel(Label label, int line);

	C block(Consumer<C> invocation);

	C code();

	default C def(Field field) {
		return def(field.name, field.type);
	}

	default C def(String fieldName, Class<?> clz) {
		return def(fieldName, typeOf(clz));
	}

	default C def(String fieldName, Class<?> clz, Class<?>... signatureClasses) {
		return def(fieldName, typeOf(clz), typeOf(signatureClasses));
	}

	default C def(String fieldName, Class<?> clz, String signature) {
		return def(fieldName, typeOf(clz), signature);
	}

	default C def(String fieldName, Class<?> clz, Type... signatureTypes) {
		return def(fieldName, typeOf(clz), signatureTypes);
	}

	default C def(String fieldName, Type fieldType, boolean array) {
		return def(fieldName, arrayOf(fieldType, array));
	}

	C def(String fieldName, Type fieldType, String signature);

	default C def(String fieldName, Type fieldType, Type... signatureTypes) {
		String signature = null;
		if (signatureTypes != null && signatureTypes.length > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("L");
			sb.append(fieldType.getInternalName());
			sb.append("<");
			for (Type signatureType : signatureTypes) {
				sb.append(signatureType.getDescriptor());
			}
			sb.append(">;");
			signature = sb.toString();
		}
		return def(fieldName, fieldType, signature);
	}

	@Deprecated
	M depetatedUse(String... varNames);

	void end();

	C line(int line);

	C line();

	Label newLabel();


	@Deprecated
	Type thisType();

	@Deprecated
	default Instance<M, C> type(Class<?> objectClass) {
		return type(typeOf(objectClass));
	}

	@Deprecated
	Instance<M, C> type(Type objectType);

	@Deprecated
	default Instance<M, C> typeThis() {
		return type(thisType());
	};

	@Deprecated
	default M use(Function<C, ToType> func) {
		ToType toType = func.apply(code());
		return useStackTop(toType.getStackTopType());
	}

	@Deprecated
	M useStackTop(Type type);
}