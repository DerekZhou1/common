//3DecoratotMode:装饰模式就是在不改变原来的类文件和使用继承的情况下，动态的扩展一个功能，
//通过创建一个包装对象，来包裹真实的类
//例子：person类是显示当前人的信息，但是现在需要加上人的穿着，这个加的功能因为是个特殊功能，
//不应该因此修改原有类，应该用装饰类将它包装起来
package com.jl.myproject.desginPattern;

public class DecoratorMode {
	public static void main(String[] args){
		Person man = new Person();
		man.name="zl";
		Trouser trouser = new Trouser();
		TShirt tshirt = new TShirt();
		Shoe shoe = new Shoe();
		shoe.Decorator(man);
		tshirt.Decorator(shoe);
		trouser.Decorator(tshirt);
		trouser.show();
	}

}

class Person{
	String name;
	public Person(){};
	
	public void showName(){
		System.out.println("我是"+this.name);
	}
	public void show(){
		System.out.printf("装扮的%s",this.name);
	}
}

 class Finery extends Person{
	Person person =null;

	public void  Decorator(Person person) {
		this.person = person;
	}
	
	 public void show(){
		 if(person!=null){
			 person.show();
		 }
	 }
		
}
class Trouser extends Finery{
	
	
	
	
	@Override
	public void show(){
		System.out.print("破裤子");
		super.show();
	}
}

class TShirt extends Finery{

	@Override
	public void show(){
		System.out.print("好衣服");
		super.show();
	}
}

class Shoe extends Finery{

	@Override
	public void show(){
		System.out.print("脏鞋子");
		super.show();
	}
}



