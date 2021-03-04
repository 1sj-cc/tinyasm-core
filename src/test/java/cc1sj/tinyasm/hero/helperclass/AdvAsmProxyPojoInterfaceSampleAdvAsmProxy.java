package cc1sj.tinyasm.hero.helperclass;

import static cc1sj.tinyasm.Adv.MAGIC_CODES_NUMBER;
import static cc1sj.tinyasm.Adv.MAGIC_CODES_String;

import cc1sj.tinyasm.AdvContext;
import cc1sj.tinyasm.AdvRuntimeReferNameObject;
import cc1sj.tinyasm.ConsumerWithException;
import cc1sj.tinyasm.MethodCode;

public class AdvAsmProxyPojoInterfaceSampleAdvAsmProxy implements AdvAsxProxyPojoInterfaceSample, AdvRuntimeReferNameObject {
	private byte _magicNumber;
	private ThreadLocal<AdvContext> _contextThreadLocal;

	@Override
	public byte get__MagicNumber() {
		return this._magicNumber;
	}

	@Override
	public void set__MagicNumber(byte magicNumber) {
		this._magicNumber = magicNumber;
	}

	@Override
	public void set__Context(ThreadLocal<AdvContext> _contextThreadLocal, byte magicNumber) {
		this._contextThreadLocal = _contextThreadLocal;
		this._magicNumber = magicNumber;
	}

	@Override
	public String getName() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(String.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getName").reTurn(String.class).INVOKE();
		});
		return new StringBuilder( MAGIC_CODES_String).append(codeIndex).toString(); // String.class);
	}

	@Override
	public boolean isAgeBoolean() {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.push(boolean.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "isAgeBoolean").reTurn(boolean.class).INVOKE();
		});
		return false;
	}

	@Override
	public byte getAgeByte() {
	
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(byte.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeByte").reTurn(byte.class).INVOKE();
		});
		return (byte) (MAGIC_CODES_NUMBER + codeIndex); // byte.class);
	}

	@Override
	public char getAgeChar() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(char.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeChar").reTurn(char.class).INVOKE();
		});
		return (char) (MAGIC_CODES_NUMBER + codeIndex);// char.class);
	}

	@Override
	public short getAgeShort() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(short.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeShort").reTurn(short.class).INVOKE();
		});
		return (short) (MAGIC_CODES_NUMBER + codeIndex); // short.class);
	}

	@Override
	public int getAgeInt() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(int.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeInt").reTurn(int.class).INVOKE();
		});
		return MAGIC_CODES_NUMBER + codeIndex; // int.class);
	}

	@Override
	public long getAgeLong() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(long.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeLong").reTurn(long.class).INVOKE();
		});
		return MAGIC_CODES_NUMBER + codeIndex; // long.class);
	}

	@Override
	public float getAgeFloat() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(float.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeFloat").reTurn(float.class).INVOKE();
		});
		return MAGIC_CODES_NUMBER + codeIndex; // float.class);
	}

	@Override
	public double getAgeDouble() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(double.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeDouble").reTurn(double.class).INVOKE();
		});
		return MAGIC_CODES_NUMBER + codeIndex; // double.class);
	}

	@Override
	public Boolean getAgeBoolean2() {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.push(Boolean.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeBoolean2").reTurn(Boolean.class).INVOKE();
		});
		return false;
	}

	@Override
	public Byte getAgeByte2() {
	
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Byte.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeByte2").reTurn(Byte.class).INVOKE();
		});
		return (byte) (MAGIC_CODES_NUMBER + codeIndex); // Byte.class);
	}

	@Override
	public Character getAgeCharacter() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Character.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeCharacter").reTurn(Character.class).INVOKE();
		});
		return (char) (MAGIC_CODES_NUMBER + codeIndex); // Character.class);
	}

	@Override
	public Short getAgeShort2() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Short.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeShort2").reTurn(Short.class).INVOKE();
		});
		return (short) (MAGIC_CODES_NUMBER + codeIndex);// Short.class);
	}

	@Override
	public Integer getAgeInteger() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Integer.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeInteger").reTurn(Integer.class).INVOKE();
		});
		return MAGIC_CODES_NUMBER + codeIndex; // Integer.class);
	}

	@Override
	public Long getAgeLong2() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Long.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeLong2").reTurn(Long.class).INVOKE();
		});
		return (long) (MAGIC_CODES_NUMBER + codeIndex);// Long.class);
	}

	@Override
	public Float getAgeFloat2() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Float.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeFloat2").reTurn(Float.class).INVOKE();
		});
		return (float) (MAGIC_CODES_NUMBER + codeIndex); // Float.class);
	}

	@Override
	public Double getAgeDouble2() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Double.class, c -> {
			objEval.accept(c);
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "getAgeDouble2").reTurn(Double.class).INVOKE();
		});
		return (double) (MAGIC_CODES_NUMBER + codeIndex); // Double.class);
	}

	@Override
	public void setName(String value) {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setName").parameter(String.class).INVOKE();
		});
	}

	@Override
	public void setAgeBoolean(boolean value) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objValue = context.getCodeAndPop();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeBoolean").parameter(boolean.class).INVOKE();
		});
	}

	@Override
	public void setAgeByte(byte value) {
	
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);
	
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeByte").parameter(byte.class).INVOKE();
		});
	
	}

	@Override
	public void setAgeChar(char value) {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeChar").parameter(char.class).INVOKE();
		});
	}

	@Override
	public void setAgeShort(short value) {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeShort").parameter(short.class).INVOKE();
		});

	}

	@Override
	public void setAgeInt(int value) {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeInt").parameter(int.class).INVOKE();
		});

	}

	@Override
	public void setAgeLong(long value) {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeLong").parameter(long.class).INVOKE();
		});

	}

	@Override
	public void setAgeFloat(float value) {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeFloat").parameter(float.class).INVOKE();
		});

	}

	@Override
	public void setAgeDouble(double value) {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeDouble").parameter(double.class).INVOKE();
		});

	}

	@Override
	public void setAgeBoolean2(Boolean value) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objValue = context.getCodeAndPop();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeBoolean2").parameter(Boolean.class).INVOKE();
		});

	}

	@Override
	public void setAgeByte2(Byte value) {
	
		AdvContext context = _contextThreadLocal.get();
	
		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);
	
			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeByte2").parameter(Byte.class).INVOKE();
		});
	
	}

	@Override
	public void setAgeCharacter(Character value) {

		AdvContext context = _contextThreadLocal.get();

		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeCharacter").parameter(Character.class).INVOKE();
		});

	}

	@Override
	public void setAgeShort2(Short value) {

		AdvContext context = _contextThreadLocal.get();

		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeShort2").parameter(Short.class).INVOKE();
		});

	}

	@Override
	public void setAgeInteger(Integer value) {
		AdvContext context = _contextThreadLocal.get();

		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeInteger").parameter(Integer.class).INVOKE();
		});

	}

	@Override
	public void setAgeLong2(Long value) {

		AdvContext context = _contextThreadLocal.get();

		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeLong2").parameter(Long.class).INVOKE();
		});

	}

	@Override
	public void setAgeFloat2(Float value) {

		AdvContext context = _contextThreadLocal.get();

		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeFloat2").parameter(Float.class).INVOKE();
		});

	}

	@Override
	public void setAgeDouble2(Double value) {

		AdvContext context = _contextThreadLocal.get();

		ConsumerWithException<MethodCode> objValue = context.resolve(value);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			objValue.accept(c);

			c.INTERFACE(AdvAsxProxyPojoInterfaceSample.class, "setAgeDouble2").parameter(Double.class).INVOKE();
		});

	}

}