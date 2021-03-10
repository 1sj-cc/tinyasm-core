package cc1sj.tinyasm.hero.advasmproxy;

import static cc1sj.tinyasm.util.TinyAsmTestUtils.dumpTinyAsm;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import cc1sj.tinyasm.hero.helperclass.PojoInterfaceChildSample;
import cc1sj.tinyasm.hero.helperclass.PojoInterfaceSample;
import cc1sj.tinyasm.hero.helperclass.ReferSimplePojoClassOnlySample;
import cc1sj.tinyasm.hero.helperclass.ReferSimplePojoClassSample;
import cc1sj.tinyasm.hero.helperclass.SimplePojoClassSample;
import cc1sj.tinyasm.hero.helperclass.UsingGenericClass;
import cc1sj.tinyasm.hero.helperclass.UsingGenericInterfaceImplSample;
import cc1sj.tinyasm.hero.helperclass.UsingGenericMethodInterfaceSample;
import cc1sj.tinyasm.hero.helperclass.UsingIterable;
import cc1sj.tinyasm.hero.helperclass.UsingList;
import cc1sj.tinyasm.AdvAsmProxyInterfaceAdvAsmBuilder;
import cc1sj.tinyasm.AdvAsmProxyClassAdvAsmBuilder;
import cc1sj.tinyasm.AdvAsmProxyGenericInterfaceAdvAsmBuilder;
import cc1sj.tinyasm.hero.helperclass.ArraySample;
import cc1sj.tinyasm.hero.helperclass.GenericInterfaceImplSample;
import cc1sj.tinyasm.hero.helperclass.GenericMethodInterface;
import cc1sj.tinyasm.hero.helperclass.PageList;
import cc1sj.tinyasm.hero.helperclass.GenericInterface;
import cc1sj.tinyasm.hero.helperclass.PojoClassChildSample;
import cc1sj.tinyasm.hero.helperclass.PojoClassSample;
import cc1sj.tinyasm.sourceconverter.SimplePojoClassSampleObjenesisAdvAsmProxy__;
import cc1sj.tinyasm.util.TinyAsmTestUtils;

public class AdvAsmProxyASMifierTest {

	@Test
	public void test_PojoClassSampleAdvAsmProxy_dump() throws Exception {
		Class<?> expectedClazz = PojoClassSampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_PojoClassSampleAdvAsmProxy_Builder() throws Exception {
		Class<?> expectedClazz = PojoClassSampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(),
				AdvAsmProxyClassAdvAsmBuilder.dump2(PojoClassSample.class, PojoClassSampleAdvAsmProxy.class.getName()));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_PojoInterfaceSampleAdvAsmProxy_Builder() throws Exception {
		Class<?> expectedClazz = PojoInterfaceSampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(),
				AdvAsmProxyInterfaceAdvAsmBuilder.dump2(PojoInterfaceSample.class, PojoInterfaceSampleAdvAsmProxy.class.getName()));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_PojoClassChildSampleAdvAsmProxy_Builder() throws Exception {
		Class<?> expectedClazz = PojoClassChildSampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(),
				AdvAsmProxyClassAdvAsmBuilder.dump2(PojoClassChildSample.class, PojoClassChildSampleAdvAsmProxy.class.getName()));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_PojoInterfaceChildSample_Dump() throws Exception {
		Class<?> expectedClazz = PojoInterfaceChildSample.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_PojoInterfaceChildSampleAdvAsmProxy_Dump() throws Exception {
		Class<?> expectedClazz = PojoInterfaceChildSampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_UsingObjectWithTwoParametersSample_Dump() throws Exception {
		Class<?> expectedClazz = UsingObjectWithTwoParametersSample.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_UsingObjectWithTwoParametersSample_Builder() throws Exception {
		Class<?> expectedClazz = UsingObjectWithTwoParametersSample.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), UsingObjectWithTwoParametersSampleAdvAsmBuilder.dump());

		assertEquals("Code", codeExpected, codeActual);

	}
//
//	@Test
//	public void test_AdvJavaSourceCodeConverterSampleReferedObject_dump() throws Exception {
//		Class<?> expectedClazz = AdvJavaSourceCodeConverterSampleReferedObjectObjenesisAdvAsmProxy.class;
//		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);
//
//		
//			String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));
//
//			assertEquals("Code", codeExpected, codeActual);
//		
//
//			
//
//		}
//	}

	@Test
	public void test_AdvJavaSourceCodeConverterSampleReferedObject_builder() throws Exception {
		Class<?> expectedClazz = SimplePojoClassSampleObjenesisAdvAsmProxy__.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), AdvAsmProxyClassAdvAsmBuilder
				.dump2(SimplePojoClassSample.class, SimplePojoClassSampleObjenesisAdvAsmProxy__.class.getName()));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_ReferSimplePojoClassSampleAdvAsmProxy_dump() throws Exception {
		Class<?> expectedClazz = ReferSimplePojoClassOnlySampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_ReferSimplePojoClassSampleAdvAsmProxy_Builder() throws Exception {
		Class<?> expectedClazz = ReferSimplePojoClassOnlySampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), AdvAsmProxyClassAdvAsmBuilder
				.dump2(ReferSimplePojoClassOnlySample.class, ReferSimplePojoClassOnlySampleAdvAsmProxy.class.getName()));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_ArraySampleAdvAsmProxy_Dump() throws Exception {
		Class<?> expectedClazz = ArraySampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_ArraySampleAdvAsmProxy_Builder() throws Exception {
		Class<?> expectedClazz = ArraySampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(),
				AdvAsmProxyClassAdvAsmBuilder.dump2(ArraySample.class, ArraySampleAdvAsmProxy.class.getName()));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_GenericClassSample_Dump() throws Exception {
		Class<?> expectedClazz = GenericInterfaceImplSample.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_UsingGenericClass_Dump() throws Exception {
		Class<?> expectedClazz = UsingGenericClass.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_GenericInterfaceSampleAdvAsmProxy_Dump() throws Exception {
		Class<?> expectedClazz = GenericInterfaceSampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_GenericInterfaceSampleAdvAsmProxy_Builder() throws Exception {
		Class<?> expectedClazz = GenericInterfaceSampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), AdvAsmProxyGenericInterfaceAdvAsmBuilder
				.dump2(GenericInterface.class, PojoClassSample.class, GenericInterfaceSampleAdvAsmProxy.class.getName()));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_UsingGenericInterfaceImplSample_Dump() throws Exception {
		Class<?> expectedClazz = UsingGenericInterfaceImplSample.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_GenericInterfaceImplSampleAdvAsmProxy_Builder() throws Exception {
		Class<?> expectedClazz = GenericInterfaceImplSampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), AdvAsmProxyClassAdvAsmBuilder
				.dump2(GenericInterfaceImplSample.class, GenericInterfaceImplSampleAdvAsmProxy.class.getName()));

		assertEquals("Code", codeExpected, codeActual);

	}

	@Test
	public void test_PageListAdvAsmProxy_Dump() throws Exception {
		Class<?> expectedClazz = PageListAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_PageList_Builder() throws Exception {
		Class<?> expectedClazz = PageListAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(),
				AdvAsmProxyGenericInterfaceAdvAsmBuilder.dump2(PageList.class, PojoClassSample.class, PageListAdvAsmProxy.class.getName()));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_UsingGenericMethodInterfaceSample_Dump() throws Exception {
		Class<?> expectedClazz = UsingGenericMethodInterfaceSample.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_GenericMethodInterfaceSampleAdvAsmProxy_Dump() throws Exception {
		Class<?> expectedClazz = GenericMethodInterfaceSampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_GenericMethodInterfaceSampleAdvAsmProxy_Dump2() throws Exception {
		Class<?> expectedClazz = GenericMethodInterfaceSampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), GenericMethodInterfaceSampleAdvAsmProxyTinyAsmDump.dump());

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_GenericMethodInterfaceSampleAdvAsmProxy_Build() throws Exception {
		Class<?> expectedClazz = GenericMethodInterfaceSampleAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);
		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(),
				AdvAsmProxyGenericInterfaceAdvAsmBuilder.dump2(GenericMethodInterface.class, PojoClassSample.class, expectedClazz.getName()));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_IteratorAdvAsmProxy_Dump() throws Exception {
		Class<?> expectedClazz = IteratorAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_IteratorAdvAsmProxy_Build() throws Exception {
		Class<?> expectedClazz = IteratorAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);
		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(),
				AdvAsmProxyGenericInterfaceAdvAsmBuilder.dump2(Iterator.class, PojoClassSample.class, IteratorAdvAsmProxy.class.getName()));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_IterableAdvAsmProxy_Dump() throws Exception {
		Class<?> expectedClazz = IterableAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_UsingIterable_Dump() throws Exception {
		Class<?> expectedClazz = UsingIterable.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_UsingList_Dump() throws Exception {
		Class<?> expectedClazz = UsingList.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);

		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(), dumpTinyAsm(expectedClazz));

		assertEquals("Code", codeExpected, codeActual);
	}

	@Test
	public void test_IterableAdvAsmProxy_Build() throws Exception {
		Class<?> expectedClazz = IterableAdvAsmProxy.class;
		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);
		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(),
				AdvAsmProxyGenericInterfaceAdvAsmBuilder.dump2(Iterable.class, PojoClassSample.class, IterableAdvAsmProxy.class.getName()));

		assertEquals("Code", codeExpected, codeActual);
	}

//
//	@Test
//	public void test_ArraySampleAdvAsmProxy_Builder() throws Exception {
//		Class<?> expectedClazz = ArraySampleAdvAsmProxy.class;
//		String codeExpected = TinyAsmTestUtils.toString(expectedClazz);
//
//		String codeActual = TinyAsmTestUtils.toString(expectedClazz.getName(),
//				AdvAsmProxyClassAdvAsmBuilder.dump2(ArraySample.class, ArraySampleAdvAsmProxy.class.getName()));
//
//		assertEquals("Code", codeExpected, codeActual);
//
//	}
}
