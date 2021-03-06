package cc1sj.tinyasm;

import static org.objectweb.asm.Opcodes.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Adv {

	static Logger logger = LoggerFactory.getLogger(Adv.class);

	/**********************************************
	 * Class
	 * 
	 * 
	 * 
	 * 
	 * 
	 ***********************************************/

	static public AfterClassModifier public_() {
		AdvClassBuilderImpl builder = new AdvClassBuilderImpl(_contextThreadLocal);
		builder.access(ACC_PUBLIC);
		return builder;
	}

	static public AfterClassModifier private_() {
		AdvClassBuilderImpl builder = new AdvClassBuilderImpl(_contextThreadLocal);
		builder.access(ACC_PRIVATE);
		return builder;
	}

	static public AfterClassModifier protected_() {
		AdvClassBuilderImpl builder = new AdvClassBuilderImpl(_contextThreadLocal);
		builder.access(ACC_PROTECTED);
		return builder;
	}

	static public AfterClassModifier package_() {
		AdvClassBuilderImpl builder = new AdvClassBuilderImpl(_contextThreadLocal);
		builder.access(0);
		return builder;
	}

	static public AfterClassName class_(String advSample) {
		AdvClassBuilderImpl builder = new AdvClassBuilderImpl(_contextThreadLocal);
		builder.access(0);
		return builder.class_(advSample);
	}

	/**********************************************
	 * enter method code
	 * 
	 * 
	 * 
	 * 
	 * 
	 ***********************************************/
	static private ThreadLocal<Stack<AdvContext>> _contextThreadLocalStack = new ThreadLocal<>();
	static private ThreadLocal<AdvContext> _contextThreadLocal = new ThreadLocal<>();

//	static void execBlock(MethodCode code, ConsumerWithException<MethodCode> block) throws Exception {
//		AdvContext context = enterCode(code);
//		context.execBlock(block);
//		exitCode();
//	}

	static AdvContext enterCode(MethodCode code) {
		AdvContext newContext = new AdvContext(code);
		if (_contextThreadLocal.get() != null) {
			if (_contextThreadLocalStack.get() == null) {
				_contextThreadLocalStack.set(new Stack<>());
			}
			_contextThreadLocalStack.get().push(_contextThreadLocal.get());
		}
		_contextThreadLocal.set(newContext);
		return newContext;
	}

	static void exitCode() {
		AdvContext currentContext = _contextThreadLocal.get();
		currentContext.clear();
		if (_contextThreadLocalStack.get() != null) {
			_contextThreadLocal.set(_contextThreadLocalStack.get().pop());
		} else {
			_contextThreadLocal.set(null);
		}
	}

	/**********************************************
	 * code
	 * 
	 * 
	 * 
	 * 
	 * 
	 ***********************************************/
	public static final String MAGIC_LOCALS_String = "#MAGIC-LOCALS#";
	public static final String MAGIC_CODES_String = "#MAGIC-CODES#";

	public static final byte MAGIC_LOCALS_NUMBER = 100;
	public static final byte MAGIC_LOCALS_MAX = 120;

	public static final byte MAGIC_CODES_NUMBER = 80;
	public static final byte MAGIC_CODES_MAX = 99;

	public static final byte MAGIC_FIELD_NUMBER = 60;
	public static final byte MAGIC_FIELD_MAX = 79;

	public static final byte MAGIC_STATIC_FIELD_NUMBER = 40;
	public static final byte MAGIC_STATIC_FIELD_MAX = 59;

	static public boolean_ cst(boolean value) {
		AdvContext context = _contextThreadLocal.get();
		int magicNUmber = MAGIC_CODES_NUMBER + context.push(boolean.class, c -> {
			c.LOADConst(value);
		});
		return new boolean_Holder(_contextThreadLocal, (byte) magicNUmber);
	}

	static public byte cst(byte value) {
		AdvContext context = _contextThreadLocal.get();
		return (byte) (MAGIC_CODES_NUMBER + context.push(byte.class, c -> {
			c.LOADConst(value);
		}));
	}

	static public char cst(char value) {
		AdvContext context = _contextThreadLocal.get();
		return (char) (MAGIC_CODES_NUMBER + context.push(char.class, c -> {
			c.LOADConst(value);
		}));
	}

	static public short cst(short value) {
		AdvContext context = _contextThreadLocal.get();
		return (short) (MAGIC_CODES_NUMBER + context.push(short.class, c -> {
			c.LOADConst(value);
		}));
	}

	static public int cst(int value) {
		AdvContext context = _contextThreadLocal.get();
		return (int) (MAGIC_CODES_NUMBER + context.push(int.class, c -> {
			c.LOADConst(value);
		}));
	}

	static public long cst(long value) {
		AdvContext context = _contextThreadLocal.get();
		return (long) (MAGIC_CODES_NUMBER + context.push(long.class, c -> {
			c.LOADConst(value);
		}));
	}

	static public float cst(float value) {
		AdvContext context = _contextThreadLocal.get();
		return (float) (MAGIC_CODES_NUMBER + context.push(float.class, c -> {
			c.LOADConst(value);
		}));
	}

	static public double cst(double value) {
		AdvContext context = _contextThreadLocal.get();
		return (double) (MAGIC_CODES_NUMBER + context.push(double.class, c -> {
			c.LOADConst(value);
		}));
	}

//	static public int inc(int l, int r) {
//		TinyAsmBuilderContext _contextThreadLocal = _contextThreadLocalThreadLocal.get();
//		MethodCode code = _contextThreadLocal.code;
//		code.LINE();
//		int locals = l - MAGIC_int;
//		code.IINC(locals, r);
//		return l;
//	}
//
//	static public <T> T getField(String name, Class<T> clazz) {
//		TinyAsmBuilderContext _contextThreadLocal = _contextThreadLocalThreadLocal.get();
//		MethodCode code = _contextThreadLocal.code;
//		code.LINE();
//		code.LOAD_THIS();
//		code.GETFIELD_OF_THIS(name);
//		return storeTopAndRefer(clazz);
//	}

	static public boolean_ param_boolean(String name) {
		AdvContext context = _contextThreadLocal.get();
		int locals = context.getCode().codeLocalGetLocals(name);

		return new boolean_Holder(_contextThreadLocal, (byte) (MAGIC_LOCALS_NUMBER + locals));
	}

	static public Boolean__ param_Boolean(String name) {
		AdvContext context = _contextThreadLocal.get();
		int locals = context.getCode().codeLocalGetLocals(name);

		return new Boolean__Holder(_contextThreadLocal, (byte) (MAGIC_LOCALS_NUMBER + locals));
	}

	static public byte param_byte(String name) {
		AdvContext context = _contextThreadLocal.get();
		int localsIndex = context.getCode().codeLocalGetLocals(name);
		return (byte) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	static public short param_short(String name) {
		AdvContext context = _contextThreadLocal.get();
		int localsIndex = context.getCode().codeLocalGetLocals(name);
		return (short) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	static public int param_int(String name) {
		AdvContext context = _contextThreadLocal.get();
		int localsIndex = context.getCode().codeLocalGetLocals(name);
		return (int) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	static public long param_long(String name) {
		AdvContext context = _contextThreadLocal.get();
		int localsIndex = context.getCode().codeLocalGetLocals(name);
		return (long) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	static public float param_float(String name) {
		AdvContext context = _contextThreadLocal.get();
		int localsIndex = context.getCode().codeLocalGetLocals(name);
		return (float) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	static public double param_double(String name) {
		AdvContext context = _contextThreadLocal.get();
		int localsIndex = context.getCode().codeLocalGetLocals(name);
		return (double) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	@SuppressWarnings("unchecked")
	static public <T> T param(String name, Class<T> t) {
		AdvContext context = _contextThreadLocal.get();
		int localsIndex = context.getCode().codeLocalGetLocals(name);

		if (t == Boolean.class) {
			throw new UnsupportedOperationException("请使用 param_boolean");
		} else if (t == Byte.class) {
			Byte key = (byte) (MAGIC_LOCALS_NUMBER + localsIndex);
			return (T) key;
		} else if (t == Character.class) {
			Character key = (char) (MAGIC_LOCALS_NUMBER + localsIndex);
			return (T) key;
		} else if (t == Short.class) {
			Short key = (short) (MAGIC_LOCALS_NUMBER + localsIndex);
			return (T) key;
		} else if (t == Integer.class) {
			Integer key = (int) (MAGIC_LOCALS_NUMBER + localsIndex);
			return (T) key;
		} else if (t == Long.class) {
			Long key = (long) (MAGIC_LOCALS_NUMBER + localsIndex);
			return (T) key;
		} else if (t == Float.class) {
			Double key = (double) (MAGIC_LOCALS_NUMBER + localsIndex);
			return (T) key;
		} else if (t == Double.class) {
			Double key = (double) (MAGIC_LOCALS_NUMBER + localsIndex);
			return (T) key;
		} else if (t == String.class) {
			String key = String.valueOf(MAGIC_LOCALS_String + localsIndex);
			return (T) key;
		} else {
			byte magicNumber = (byte) (MAGIC_LOCALS_NUMBER + localsIndex);
			T obj = brokerBuilder.buildProxyClass(t, _contextThreadLocal, magicNumber);
			return (T) obj;
//		} else {
//			throw new UnsupportedOperationException("Only accept tinyasm proxy object");
		}
	}

	static public <T> void return_(T left) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> leftEval = context.resolve(left);
		context.push(left.getClass(), c -> {
			leftEval.accept(c);
			c.RETURNTop();
		});
		context.popAndExec();
	}

//
//	static public <T> void setField(String name, T value) {
//		TinyAsmBuilderContext _contextThreadLocal = _contextThreadLocalThreadLocal.get();
//		MethodCode code = _contextThreadLocal.code;
//		code.LINE();
//		code.LOAD_THIS();
//		resolve(code, value);
//		code.PUTFIELD_OF_THIS(name);
//	}
	static final private AdvAsmProxyObjenesisBuilder brokerBuilder = new AdvAsmProxyObjenesisBuilder();
//
//	static public byte cstNull(byte value) {
//		AdvContext context = _contextThreadLocal.get();
//		return (byte)(MAGIC_CODES_NUMBER + context.push(c -> {
//			c.LOADConst(value);
//		}));
//	}

	static public <T> T new_(Class<T> clz) {
		AdvContext context = _contextThreadLocal.get();

		int codeIndex = context.push(clz, c -> {
			c.NEW(clz);
			c.DUP();
			c.SPECIAL(clz, "<init>").INVOKE();
		});

		int magicNumber = MAGIC_CODES_NUMBER + codeIndex;

		T t = brokerBuilder.buildProxyClass(clz, _contextThreadLocal, magicNumber);
		return t;
	}

	static public <T> T new_(Class<T> clz, Object... params) {

		AdvContext context = _contextThreadLocal.get();

		Constructor<?> constructor = matchConstruct(clz, params);
		if (constructor == null) throw new UnsupportedOperationException();
		Class<?>[] paramTypes = constructor.getParameterTypes();

		List<ConsumerWithException<MethodCode>> valueEvals = new ArrayList<>();
		for (int i = 0; i < params.length; i++) {
			valueEvals.add(context.resolve(params[i], paramTypes[i]));
		}

		int codeIndex = context.push(clz, c -> {
//			c.LINE();
			c.NEW(clz);
			c.DUP();
			for (ConsumerWithException<MethodCode> valueEval : valueEvals) {
				valueEval.accept(c);
			}
			c.SPECIAL(clz, "<init>").parameter(constructor.getParameterTypes()).INVOKE();
		});

		int magicNumber = MAGIC_CODES_NUMBER + codeIndex;

		T t = brokerBuilder.buildProxyClass(clz, _contextThreadLocal, magicNumber);
		return t;
	}

	protected static Constructor<?> matchConstruct(Class<?> helloclass, Object... params) {
		Constructor<?> c = null;
		for (Constructor<?> init : helloclass.getConstructors()) {
			if (init.getParameterCount() == params.length) {
				boolean matched = true;
				Class<?>[] definedParams = init.getParameterTypes();
				for (int i = 0; i < params.length; i++) {
					if (definedParams[i].getClass() == params[i].getClass()) {

					} else if (match(definedParams[i], params[i].getClass())) {

					} else {
						matched = false;
						break;
					}
				}
				if (matched) c = init;
			}
		}
		return c;
	}

	private static boolean match(Class<?> l, Class<?> r) {
		if (l.isPrimitive()) {
			return (l == byte.class && r == Byte.class) || (l == char.class && r == Character.class)
					|| (l == short.class && r == Short.class) || (l == int.class && r == Integer.class)
					|| (l == long.class && r == Long.class) || (l == float.class && r == Float.class)
					|| (l == double.class && r == Double.class);
		}
		return false;
	}

	static public ConsumerWithException<MethodCode> nop() {
		return c -> {
		};
	}

	static public void referNothing(Object obj) {
		AdvContext context = _contextThreadLocal.get();

		assert /* (codeIndex == 0) && */ (context.stackSize() == 1) : "堆栈必须只有一个值";
		context.popAndExec();
		context.pop();
	}

	/**
	 * Refer 把当前堆栈顶的值保存在指定的local位置中并返回记录local位置的值。
	 * 
	 * @param magicIndex
	 * @return
	 */
	/**
	 * set
	 * 
	 * @param booleanMagicLocalsIndex
	 * @param magicIndex
	 */
	static public void __(boolean_ booleanMagicLocalsIndex, boolean magicIndex) {
		AdvContext context = _contextThreadLocal.get();

		byte magicLocalsIndex = booleanMagicLocalsIndex.getLocalsIndex();
		assert MAGIC_LOCALS_NUMBER <= magicLocalsIndex && magicLocalsIndex <= MAGIC_LOCALS_MAX : "必须是locals index";
		assert /* (magicIndex == 0) && */ (context.stackSize() == 1) : "堆栈必须只有一个值";

		context.popAndExec();
		context.store(magicLocalsIndex - MAGIC_LOCALS_NUMBER);
	}

	static public void __(Boolean__ booleanMagicLocalsIndex, Boolean v) {
		AdvContext context = _contextThreadLocal.get();

		byte magicLocalsIndex = booleanMagicLocalsIndex.getLocalsIndex();
		assert MAGIC_LOCALS_NUMBER <= magicLocalsIndex && magicLocalsIndex <= MAGIC_LOCALS_MAX : "必须是locals index";
		assert /* (magicIndex == 0) && */ (context.stackSize() == 1) : "堆栈必须只有一个值";

		context.popAndExec();
		context.store(magicLocalsIndex - MAGIC_LOCALS_NUMBER);
	}

	static public void __(byte magicLocalsIndex, byte magicIndex) {
		doSet_(magicLocalsIndex, magicIndex);
	}

	static public void __(short magicLocalsIndex, short magicIndex) {
		doSet_(magicLocalsIndex, magicIndex);

	}

	static public void __(int magicLocalsIndex, int magicIndex) {
		doSet_(magicLocalsIndex, magicIndex);
	}

	protected static void doSet_(int magicLocalsIndex, int magicIndex) {
		AdvContext context = _contextThreadLocal.get();

		assert MAGIC_LOCALS_NUMBER <= magicLocalsIndex && magicLocalsIndex <= MAGIC_LOCALS_MAX : "必须是locals index";
		assert MAGIC_CODES_NUMBER <= magicIndex && magicIndex <= MAGIC_CODES_MAX : "必须是code index";

		ConsumerWithException<MethodCode> ref = context.resolve(magicIndex);

		context.execLine(c -> {
			ref.accept(c);
			c.STORE(magicLocalsIndex - MAGIC_LOCALS_NUMBER);
		});

	}

	static public void __(long magicLocalsIndex, long magicIndex) {
		doSet_((int) magicLocalsIndex, (int) magicIndex);
	}

	static public void __(float magicLocalsIndex, float magicIndex) {
		doSet_((int) magicLocalsIndex, (int) magicIndex);
	}

	static public void __(double magicLocalsIndex, double magicIndex) {
		doSet_((int) magicLocalsIndex, (int) magicIndex);
	}

	/**
	 * Refer 把当前堆栈顶的值保存在新的local位置中并返回记录local位置的值。
	 * 
	 * @param magicIndex
	 * @return
	 */
	static public boolean_ __(String varname, boolean magicIndex) {
		AdvContext context = _contextThreadLocal.get();
		assert /* (codeIndex == 0) && */ (context.stackSize() == 1) : "堆栈必须只有一个值";

		ConsumerWithException<MethodCode> expr = context.getCodeAndPop();
		context.clear();
		context.line();
		context.exec(expr);
		int localsIndex = context.store(varname);

		return new boolean_Holder(_contextThreadLocal, (byte) (MAGIC_LOCALS_NUMBER + localsIndex));
	}

	static public Boolean__ __(String varname, Boolean v) {
		AdvContext context = _contextThreadLocal.get();
		assert /* (codeIndex == 0) && */ (context.stackSize() == 1) : "堆栈必须只有一个值";

		ConsumerWithException<MethodCode> expr = context.getCodeAndPop();
		context.clear();
		context.line();
		context.exec(expr);
		int localsIndex = context.store(varname);

		return new Boolean__Holder(_contextThreadLocal, (byte) (MAGIC_LOCALS_NUMBER + localsIndex));
	}

	static public byte __(String varname, byte magicIndex) {
		int localsIndex = doReferByte(varname, (int) magicIndex);
		return (byte) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	static public short __(String varname, short magicIndex) {
		int localsIndex = doReferByte(varname, (int) magicIndex);
		return (short) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	static public int __(String varname, int magicIndex) {
		int localsIndex = doReferByte(varname, (int) magicIndex);
		return (int) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	static public long __(String varname, long magicIndex) {
		int localsIndex = doReferByte(varname, (int) magicIndex);
		return (long) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	static public float __(String varname, float magicIndex) {
		int localsIndex = doReferByte(varname, (int) magicIndex);
		return (float) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	static public double __(String varname, double magicIndex) {
		int localsIndex = doReferByte(varname, (int) magicIndex);
		return (double) (MAGIC_LOCALS_NUMBER + localsIndex);
	}

	protected static int doReferByte(String varname, int magicIndex) {
		AdvContext context = _contextThreadLocal.get();
//		context.resolve(null)
//		assert MAGIC_CODES_NUMBER <= magicIndex && magicIndex <= MAGIC_CODES_MAX : "必须是code index";
//		int codeIndex = (int) magicIndex - MAGIC_CODES_NUMBER;
//		assert codeIndex == context.stackSize() - 1 : "必须在堆栈顶";

		ConsumerWithException<MethodCode> expr = context.resolve(magicIndex);
		context.clear();
		context.line();
		context.exec(expr);
		int localsIndex = context.store(varname);

		return localsIndex;
	}

	/**
	 * 把当前堆栈顶端的对象存储到locals中
	 * 
	 * @param <T>
	 * @param magicNumber
	 * @return
	 */
	@SuppressWarnings("unchecked")
	static public <T> T __(String varname, T magicNumber) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> expr = context.resolve(magicNumber);
		context.clear();
		context.line();
		context.exec(expr);
		int locals = context.store(varname);

		Class<?> t = magicNumber.getClass();

		if (t == boolean.class) {
			throw new UnsupportedOperationException();
		} else if (t == Byte.class) {
			int codeIndex = ((Byte) magicNumber).intValue() - MAGIC_CODES_NUMBER;
			assert (codeIndex != 0) && (context.stackSize() != 1) : "堆栈必须只有一个值";
			Byte key = (byte) (MAGIC_LOCALS_NUMBER + locals);
			return (T) key;
		} else if (t == Character.class) {
			int codeIndex = ((Character) magicNumber).charValue() - MAGIC_CODES_NUMBER;
			assert (codeIndex != 0) && (context.stackSize() != 1) : "堆栈必须只有一个值";
			Character key = (char) (MAGIC_LOCALS_NUMBER + locals);
			return (T) key;
		} else if (t == Short.class) {
			int codeIndex = ((Short) magicNumber).intValue() - MAGIC_CODES_NUMBER;
			assert (codeIndex != 0) && (context.stackSize() != 1) : "堆栈必须只有一个值";
			Short key = (short) (MAGIC_LOCALS_NUMBER + locals);
			return (T) key;
		} else if (t == Integer.class) {
			int codeIndex = ((Integer) magicNumber).intValue() - MAGIC_CODES_NUMBER;
			assert (codeIndex != 0) && (context.stackSize() != 1) : "堆栈必须只有一个值";
			Integer key = (int) (MAGIC_LOCALS_NUMBER + locals);
			return (T) key;
		} else if (t == Long.class) {
			int codeIndex = ((Long) magicNumber).intValue() - MAGIC_CODES_NUMBER;
			assert (codeIndex != 0) && (context.stackSize() != 1) : "堆栈必须只有一个值";
			Long key = (long) (MAGIC_LOCALS_NUMBER + locals);
			return (T) key;
		} else if (t == Float.class) {
			int codeIndex = ((Float) magicNumber).intValue() - MAGIC_CODES_NUMBER;
			assert (codeIndex != 0) && (context.stackSize() != 1) : "堆栈必须只有一个值";
			Double key = (double) (MAGIC_LOCALS_NUMBER + locals);
			return (T) key;
		} else if (t == Double.class) {
			int codeIndex = ((Byte) magicNumber).intValue() - MAGIC_CODES_NUMBER;
			assert (codeIndex != 0) && (context.stackSize() != 1) : "堆栈必须只有一个值";
			Double key = (double) (MAGIC_LOCALS_NUMBER + locals);
			return (T) key;
		} else if (t == String.class) {
			String key = String.valueOf(MAGIC_LOCALS_String + locals);
			return (T) key;
		} else if (magicNumber instanceof AdvRuntimeReferNameObject) {
			AdvRuntimeReferNameObject obj = ((AdvRuntimeReferNameObject) magicNumber);
			byte codeIndex = obj.get__MagicNumber();

			assert (codeIndex != 0) && (context.stackSize() != 1) : "堆栈必须只有一个值";

			byte localsIndex = (byte) (MAGIC_LOCALS_NUMBER + locals);
			obj.set__MagicNumber(localsIndex);
			return (T) obj;
		} else {
			throw new UnsupportedOperationException("Only accept tinyasm proxy object");
		}
	}

	static public int add(int left, int right) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> rightEval = context.resolve(right);
		ConsumerWithException<MethodCode> leftEval = context.resolve(left);
		return MAGIC_CODES_NUMBER + context.push(int.class, c -> {
			leftEval.accept(c);
			rightEval.accept(c);
			c.ADD();
		});
	}

	// TODO INC 比较特殊 尽量不要使用
	static public void _inc(int left, int right) {
		AdvContext context = _contextThreadLocal.get();
		int magicNumber = left;
		assert (MAGIC_LOCALS_NUMBER <= magicNumber && magicNumber <= MAGIC_LOCALS_MAX) : "第一个参数必须是locals位置";
		int localsIndex = magicNumber - MAGIC_LOCALS_NUMBER;
		context.execLine(c -> {
			c.IINC(localsIndex, right);
		});
	}

	static public AfterIf ifTrue_(boolean beGood) {
		return _if(isTrue(beGood));
	}

	static public AfterIf ifFalse_(boolean beGood) {
		return _if(isFalse(beGood));
	}

	static public AfterFor _for(CompareEval eval, ConsumerWithException<MethodCode> execEveryLoop) {
		AdvContext context = _contextThreadLocal.get();
		context.clear();

		ForBuilder builder = new ForBuilder(_contextThreadLocal, eval, execEveryLoop);
//		context.push(builder);
		return builder;
	}

	static public AfterIf _if(CompareEval eval) {
		AdvContext context = _contextThreadLocal.get();
		context.clear();

		IfBuilder builder = new IfBuilder(_contextThreadLocal, eval);
		context.pushIf(builder);
		return builder;
	}

	static AfterWhile whileTrue_(boolean eval) {
		return _while(isTrue(eval));
	}

	static AfterWhile whileFalse_(boolean eval) {
		return _while(isFalse(eval));
	}

	static public AfterWhile _while(CompareEval eval) {
		WhileBuilder builder = new WhileBuilder(_contextThreadLocal, eval);
//		context.push(builder);
		return builder;
	}

	static public AfterDo _do(ConsumerWithException<MethodCode> block) {
		DoWhileBuilder builder = new DoWhileBuilder(_contextThreadLocal, block);
		return builder;
	}

	static public CompareEval isLessThan(int left, int right) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> rightEval = context.resolve(right);
		ConsumerWithException<MethodCode> leftEval = context.resolve(left);
		return new CompareEval() {
			@Override
			public void prepareData(MethodCode code) throws Exception {
				leftEval.accept(code);
				rightEval.accept(code);
			}

			@Override
			public void gotoWhenSucceed(MethodCode code, Label label) throws Exception {
				code.IF_ICMPLT(label);
			}

			@Override
			public void gotoWhenFail(MethodCode code, Label label) throws Exception {
				code.IF_ICMPGE(label);
			}
		};
	}

	static public CompareEval isGreaterThan(int left, int right) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> rightEval = context.resolve(right);
		ConsumerWithException<MethodCode> leftEval = context.resolve(left);
		return new CompareEval() {
			@Override
			public void prepareData(MethodCode code) throws Exception {
				leftEval.accept(code);
				rightEval.accept(code);
			}

			@Override
			public void gotoWhenSucceed(MethodCode code, Label label) throws Exception {
				code.IF_ICMPGT(label);
			}

			@Override
			public void gotoWhenFail(MethodCode code, Label label) throws Exception {
				code.IF_ICMPLE(label);
			}
		};
	}

	static public CompareEval isEqual(int left, int right) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> rightEval = context.resolve(right);
		ConsumerWithException<MethodCode> leftEval = context.resolve(left);
		return new CompareEval() {
			@Override
			public void prepareData(MethodCode code) throws Exception {
				leftEval.accept(code);
				rightEval.accept(code);
			}

			@Override
			public void gotoWhenSucceed(MethodCode code, Label label) throws Exception {
				code.IF_ICMPEQ(label);
			}

			@Override
			public void gotoWhenFail(MethodCode code, Label label) throws Exception {
				code.IF_ICMPNE(label);
			}
		};
	}

	static public CompareEval isNotEqual(int left, int right) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> rightEval = context.resolve(right);
		ConsumerWithException<MethodCode> leftEval = context.resolve(left);
		return new CompareEval() {
			@Override
			public void prepareData(MethodCode code) throws Exception {
				leftEval.accept(code);
				rightEval.accept(code);
			}

			@Override
			public void gotoWhenSucceed(MethodCode code, Label label) throws Exception {
				code.IF_ICMPNE(label);
			}

			@Override
			public void gotoWhenFail(MethodCode code, Label label) throws Exception {
				code.IF_ICMPEQ(label);
			}
		};
	}

	static public CompareEval isGreaterEqual(int left, int right) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> rightEval = context.resolve(right);
		ConsumerWithException<MethodCode> leftEval = context.resolve(left);
		return new CompareEval() {
			@Override
			public void prepareData(MethodCode code) throws Exception {
				leftEval.accept(code);
				rightEval.accept(code);
			}

			@Override
			public void gotoWhenSucceed(MethodCode code, Label label) throws Exception {
				code.IF_ICMPGE(label);
			}

			@Override
			public void gotoWhenFail(MethodCode code, Label label) throws Exception {
				code.IF_ICMPLT(label);
			}
		};
	}

	static public CompareEval isLessEqual(int left, int right) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> rightEval = context.resolve(right);
		ConsumerWithException<MethodCode> leftEval = context.resolve(left);
		return new CompareEval() {
			@Override
			public void prepareData(MethodCode code) throws Exception {
				leftEval.accept(code);
				rightEval.accept(code);
			}

			@Override
			public void gotoWhenSucceed(MethodCode code, Label label) throws Exception {
				code.IF_ICMPLE(label);
			}

			@Override
			public void gotoWhenFail(MethodCode code, Label label) throws Exception {
				code.IF_ICMPGT(label);
			}
		};

	}

	static public CompareEval isTrue(boolean eval) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> leftEval = context.getCodeAndPop();
		return new CompareEval() {
			@Override
			public void prepareData(MethodCode code) throws Exception {
				leftEval.accept(code);
			}

			@Override
			public void gotoWhenSucceed(MethodCode code, Label label) throws Exception {
				code.IFNE(label);
			}

			@Override
			public void gotoWhenFail(MethodCode code, Label label) throws Exception {
				code.IFEQ(label);
			}
		};
	}

	static public CompareEval isFalse(boolean eval) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> leftEval = context.getCodeAndPop();

		return new CompareEval() {
			@Override
			public void prepareData(MethodCode code) throws Exception {
				leftEval.accept(code);
			}

			@Override
			public void gotoWhenSucceed(MethodCode code, Label label) throws Exception {
				code.IFEQ(label);
			}

			@Override
			public void gotoWhenFail(MethodCode code, Label label) throws Exception {
				code.IFNE(label);
			}
		};
	}

	static public CompareEval isTrue(Boolean__ eval) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> leftEval = context.resolve(eval);
		return new CompareEval() {
			@Override
			public void prepareData(MethodCode code) throws Exception {
				leftEval.accept(code);
			}

			@Override
			public void gotoWhenSucceed(MethodCode code, Label label) throws Exception {
				code.IFNE(label);
			}

			@Override
			public void gotoWhenFail(MethodCode code, Label label) throws Exception {
				code.IFEQ(label);
			}
		};
	}

	static public CompareEval isFalse(Boolean__ eval) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> leftEval = context.resolve(eval);
		return new CompareEval() {
			@Override
			public void prepareData(MethodCode code) throws Exception {
				leftEval.accept(code);
			}

			@Override
			public void gotoWhenSucceed(MethodCode code, Label label) throws Exception {
				code.IFEQ(label);
			}

			@Override
			public void gotoWhenFail(MethodCode code, Label label) throws Exception {
				code.IFNE(label);
			}
		};
	}

	// is NULL
	static public CompareEval isNull(FunctionWithException<MethodCode, Boolean> beGood) {

		return null;
	}

	// is NOT NULL
	static public CompareEval notNull(FunctionWithException<MethodCode, Boolean> beGood) {

		return null;
	}

	public static <T> T buildProxyClass(Class<T> t, byte magicNumber) {
		return brokerBuilder.buildProxyClass(t, _contextThreadLocal, magicNumber);
	}

	public static byte[] dumpClass(AdvClassBuilder classBuilder, Object simpleSampleCodeBuilder) {
		Class<?> clazz = simpleSampleCodeBuilder.getClass();
		try {

			ClassReader cr = new ClassReader(clazz.getName());
			cr.accept(new ClassVisitor(ASM9) {

				@Override
				public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

					try {
						if ((access & ACC_PUBLIC) > 0 && !"dump".equals(name)) {
							Method thisMethod = null;
							Method[] methodes = clazz.getDeclaredMethods();
							for (Method method : methodes) {
								if (method.getName().equals(name)) {
									thisMethod = method;
									break;
								}
							}
//							Method method = clazz.getMethod(name);
							logger.debug(name);
							if (thisMethod != null) {
								buildWithMethod(classBuilder, simpleSampleCodeBuilder, thisMethod);
							}

						}
					} catch (SecurityException e) {
						throw new UnsupportedOperationException(e);
					}
					return null;
				}

			}, ClassReader.SKIP_CODE);
		} catch (Exception e1) {
			throw new UnsupportedOperationException(e1);
		}

		return classBuilder.end().toByteArray();
	}

	protected static void buildWithMethod(AdvClassBuilder classBuilder, Object simpleSampleCodeBuilder, Method method) {
		if (method.getName().startsWith("_dump") && method.getParameters()[0].getType() == AdvClassBuilder.class) {
			try {
				method.invoke(simpleSampleCodeBuilder, classBuilder);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			AdvMethodBuilder methodBuilder = (AdvMethodBuilder) classBuilder.method(method.getModifiers(), method.getName());
			if (method.getReturnType() == Void.class) methodBuilder.return_(method.getReturnType());
			for (Parameter parameter : method.getParameters()) {
				methodBuilder.parameter_(parameter.getName(), parameter.getType());
			}
			Class<?>[] exceptionClasses = method.getExceptionTypes();
			methodBuilder.throws_(exceptionClasses);
			methodBuilder.code(code -> {
				try {
					method.invoke(simpleSampleCodeBuilder);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}
}
