//模板方法模式：模板方法主要在一个方法中大部分的功能一致，但在少数细节方面有不同，就抽象出一个模板类，
//将需要做的功能的主要逻辑和结构组成放在抽象类的方法中，不同的地方通过用不同的子类继承，然后重写细节来完成
//这里用打印文档来举例，文档里面绝大部分内容相同，只有少部分内容不同
package com.jl.myproject.desginPattern;

public class ModelMode {
	public static void main(String[] args){
		SubClass1 class1 = new SubClass1();
		SubClass2 class2 = new SubClass2();
		class1.print();
		class2.print();
	}
}

abstract class Model{
	public void print(){
		System.out.println("aaaaaaaaaaaaaaaaaaaa"+getString());
	}
	
	abstract public String  getString();
}

class SubClass1 extends Model{
	@Override
	public String getString() {
		String str = "bbbbbbbbbbb";
		return str;
	}
}

class SubClass2 extends Model{
	@Override
	public String getString() {
		String str = "cccccccccccccc";
		return str;
	}
}
