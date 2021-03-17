package cc1sj.tinyasm.advasmproxy.generic;

import cc1sj.tinyasm.advasmproxy.simple.PojoClassChild;
import cc1sj.tinyasm.advmagicbuilder.WithIdKey;

public class GenericClassWithIdKey<T extends WithIdKey> implements GenericInterfaceWithIdKey<T> {
	T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	@Override
	public PojoClassChild getPojoClassChildSample() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPojoClassChildSample(PojoClassChild classSample) {
		// TODO Auto-generated method stub

	}

}