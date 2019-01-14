package com.lihail.factory;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

import com.lihail.minidao.MiniDaoHandler;

public class MiniDaoFactory<T> implements FactoryBean<T> {
	
	private Class<T> doInterface;
	
	private MiniDaoHandler proxy;

	public T getObject() throws Exception {
		return (T) Proxy.newProxyInstance(doInterface.getClassLoader(), new Class[]{doInterface}, proxy);
	}

	public Class<?> getObjectType() {
		return doInterface;
	}

	public boolean isSingleton() {
		return true;
	}

	public Class<T> getDoInterface() {
		return doInterface;
	}

	public void setDoInterface(Class<T> doInterface) {
		this.doInterface = doInterface;
	}

	public MiniDaoHandler getProxy() {
		return proxy;
	}

	public void setProxy(MiniDaoHandler proxy) {
		this.proxy = proxy;
	}

	
}
