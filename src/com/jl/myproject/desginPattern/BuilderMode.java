//建造者模式： 将一个复杂的对象的构建与它的表示分离，使同样的构建过程有不同的表示
//（适用于那些构建过程稳定的对象）
//这里定义一个过程，表示一个人，先表示头，然后是手，然后是脚，这个过程有不同的输入，
//结果不同，但过程相同
package com.jl.myproject.desginPattern;

public class BuilderMode {
	public static void main(String[] args){
		thinPerson person1 = new thinPerson();
		fatPerson person2 = new fatPerson();
		DirectorPerson director1 = new DirectorPerson(person1);
		DirectorPerson director2 = new DirectorPerson(person2);
		director1.paint();
		director2.paint();
	}
}

class DirectorPerson{
	Person1 person =null;
	public DirectorPerson(Person1 person) {
		this.person = person;
			}
	
	public void paint(){
		person.paintHead();
		person.paintHands();
		person.paintfoot();

	}
}

abstract class Person1{
	abstract public void paintHead();
	abstract public void paintHands();
	abstract public void paintfoot();
}

class thinPerson extends Person1{

	@Override
	public void paintHead() {
		System.out.println("这是瘦子的头");
		
	}

	@Override
	public void paintHands() {
		System.out.println("这是瘦子的手");
		
	}

	@Override
	public void paintfoot() {
		System.out.println("这是瘦子的脚");
		
	}
	
}

class fatPerson extends Person1{

	@Override
	public void paintHead() {
		System.out.println("这是胖子的头");
		
	}

	@Override
	public void paintHands() {
		System.out.println("这是胖子的手");
		
	}

	@Override
	public void paintfoot() {
		System.out.println("这是胖子的脚");
		
	}
	
}