//PorxyPattern 代理模式，为其他对象提供一种代理以控制对这个对象的访问
//相当于对一个类的每一个方法都包装一遍
package com.jl.myproject.desginPattern;

public class ProxyPattern {
	public static void main(String...args){
		Proxy pro = new Proxy();
		pro.functs1();
		pro.functs2();
		pro.functs3();
	}
}

interface AllFunction{
	public void functs1();
	public void functs2();
	public void functs3();
}

class Proxy implements AllFunction{
	Original orig ;
	public Proxy() {
		orig = new Original();
	}
	@Override
	public void functs1() {
		orig.functs1();
		
	}

	@Override
	public void functs2() {
		orig.functs2();
		
	}

	@Override
	public void functs3() {
		orig.functs3();
		
	}
	
}

class Original implements AllFunction{

	@Override
	public void functs1() {
		System.out.println("111");
	}

	@Override
	public void functs2() {
		System.out.println("2222");
		
	}

	@Override
	public void functs3() {
		System.out.println("333");
		
	}
	
}
