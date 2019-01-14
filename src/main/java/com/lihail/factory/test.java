package com.lihail.factory;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class test {
	
	public static void main(String[] args) throws Exception {
		Class<?> proxyClass = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
//		System.out.println(proxyClass.getName());
		
		Constructor<?>[] constructors = proxyClass.getConstructors();
//		for (Constructor<?> constructor : constructors) {
//			System.out.println(constructor.getName());
//			System.out.println(Arrays.toString(constructor.getParameterTypes()));
//			
//		}
//		Method[] methods = proxyClass.getMethods();
//		for (Method method : methods) {
//			System.out.println(method.getName());
//		}
		
		class MyInvocation implements InvocationHandler{

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}
			
		}
		
		Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
		Collection collection1 = (Collection) constructor.newInstance(new MyInvocation());
		
//		System.out.println(collection1);
		
		Collection collection2 = (Collection) constructor.newInstance(new InvocationHandler() {

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
		
		Collection collection3 = (Collection) Proxy.newProxyInstance(Collection.class.getClassLoader(), new Class[]{Collection.class}, new InvocationHandler() {
			ArrayList target = new ArrayList();
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				return method.invoke(target, args);
			}
			
		});
		
		collection3.add("111");
		collection3.add("222");
		
		System.out.println(collection3.getClass().toString());
		System.out.println(collection3.getClass().hashCode());
		System.out.println(collection3.getClass().getName());
		System.out.println(collection3.getClass().equals(collection3));
		
		
		Configuration config = new Configuration();
		
		config.setDirectoryForTemplateLoading(new File("resources/sql"));
		
		Map map = new HashMap();
		
		map.put("test", "11");
		
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		map.put("list", list);
		
		Template template = config.getTemplate("test.ftl");

		Writer writer = new OutputStreamWriter(System.out);
		
		template.process(map, writer);
		
		writer.flush();
		writer.close();
		
	}

}
