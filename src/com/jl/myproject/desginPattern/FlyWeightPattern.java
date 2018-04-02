//25 FlyWeightPattern享元模式 对于一个类的大量的相似的对象实例，
//使用享元模式共享代码，减少对象数量，提升性能
//实例:演示实例化多个对象，每个对象只有一个字段有区别，可以对这个字段进行散列，减少需要实例化的对象个数
package com.jl.myproject.desginPattern;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class FlyWeightPattern {
	public static void main(String...args){
		
		FlyWeight obj1 =FlyWeightFactory.add("123");
		FlyWeight obj2 =FlyWeightFactory.add("234");
		FlyWeight obj3 =FlyWeightFactory.add("123");
		System.out.println(obj1==obj2);
		System.out.println(obj1==obj3);
		
	}
}

abstract class FlyWeight{
	abstract public void doSomethings();
}

class Concrete1 extends FlyWeight{
	public String name;
	public Concrete1(String name) {
		this.name = name;
	}
	@Override
	public void doSomethings() {
		System.out.println(this.name);
	}
}

class FlyWeightFactory{
	static Map<String,FlyWeight> maps =new Hashtable<String,FlyWeight >();
	public static FlyWeight add(String name){
		if(maps.containsKey(name)){
			return maps.get(name);
		}
		FlyWeight obj = new  Concrete1(name);
		maps.put(name,obj );
		return obj;
	}
}



