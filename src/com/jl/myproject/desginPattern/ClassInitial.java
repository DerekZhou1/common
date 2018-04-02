//ClassInitial：用于展示一个对象在实例化的过程中代码的执行顺序：静态代码块>非静态代码块（构造代码块）>构造方法
package com.jl.myproject.desginPattern;

public class ClassInitial {
	public int age;
	public String name;
	//public int weigh =5; 
	public ClassInitial(int age,String name){
		this.age =age;
		this.name =name;
		System.out.println("构造函数");
	}
	
	//用static修饰的代码块叫做静态代码块
	static {System.out.println("静态代码块");}
	
	//在类中定义的代码块叫做构造代码块
	{System.out.println("构造代码块");
	weigh =4;

	}
	
	public void func(){
		//在方法中定义的代码块叫做普通代码块
		{
			System.out.println("普通代码块");
		}
	}
	
	public void getWeigh(){
		System.out.println(weigh);
	}
	public int weigh =5; 
	public static void main(String[] args){
		ClassInitial c1 = new ClassInitial(2,"2");
		ClassInitial c2 = new ClassInitial(3,"3");
		c1.getWeigh();
	}
	
}
