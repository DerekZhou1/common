//VisitorPattern 访问者模式 用于将数据结构和数据结构的操作分离开来
//实例：数据结构有两种稳定结构，男人，女人；操作有失败、成功、恋爱等
package com.jl.myproject.desginPattern;

public class VisitorPattern {
	public static void  main(String...args){
		action success = new Success();
		action failure = new Failure();
		Man man = new Man();
		Woman woman = new Woman();
		man.getManResult(success);
		man.getManResult(failure);
		woman.getWomanResult(success);
		woman.getWomanResult(failure);
	}
}

abstract class Human{
	
	
}

class Man extends Human{
	 public void getManResult(action visitor){
		 visitor.getManResult(this);
	 }
}

class Woman extends Human{
	 public void getWomanResult(action visitor){
		 visitor.getWomanResult(this);
	 }
}

abstract class action{
	abstract public void getManResult(Human human);
	abstract public void getWomanResult(Human human);
}

class Success extends action{

	@Override
	public void getManResult(Human human) {
		System.out.print(human.getClass().getSimpleName());
		System.out.println("成功时，有一个女人");
	}

	@Override
	public void getWomanResult(Human human) {
		System.out.print(human.getClass().getSimpleName());
		System.out.println("成功时，有一个男人人");
	}
}

class Failure extends action{

	@Override
	public void getManResult(Human human) {
		System.out.print(human.getClass().getSimpleName());
		System.out.println("失败时，有两个女人");
	}

	@Override
	public void getWomanResult(Human human) {
		System.out.print(human.getClass().getSimpleName());
		System.out.println("失败时，有两个男人人");
	}
}
