//MementoMode 备忘录模式，在不破坏封装的情况下对一个对象进行状态拷贝，用于以后恢复
//实例：拷贝个人信息，做出修改后可以直接恢复,PersonInfo是个人信息，memento用于保存拷贝
//它保存personinfo需要保存的所有信息，并且直接用初始化的方式进行赋值。caretaker用于恢复
package com.jl.myproject.desginPattern;

public class MementoMode {
	public static void main(String...args){
		PersonInfo person = new PersonInfo();
		person.age =10;
		person.name ="123";
		person.show();
		Caretaker care = new Caretaker();
		care.createMem(person);
		person.age =12;
		person.name ="333";
		person.show();
		care.recovery(person);
		person.show();
	}
}

class PersonInfo{
	int age ;
	String name;
	public void show(){
		System.out.printf("%d %s  ",age,name);
		System.out.println();
	}
}

class Memento{
	int age;
	String name;
	public Memento(int age,String name) {
		this.age =age;
		this.name = name;
	}
}

class Caretaker{
	Memento mem ;
	public void createMem(PersonInfo info){
		mem = new Memento(info.age, info.name);
	}
	
	public  void recovery(PersonInfo info){
		info.age = mem.age;
		info.name = mem.name;
	}
}


