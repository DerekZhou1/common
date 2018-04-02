//ObserverMode 观察者模式（发布-订阅模式） 定义了一种一对多的依赖关系，让多个观察者对象
//同时监听某一个主题对象，这个主题对象在状态发生变化时，会通知所有观察者对象，使他们能够更新自己
//观察者模式适用于当一个对象改变时，会有其他多个对象发生改变的状况
//核心：抽象主题类仍然依赖于抽象观察者，并没有完全的解耦，可以使用委托来完成完全解耦（详见delegateMode）
//.net委托实现很简单  zl.update+=new EventHandler(delegatefunc);
//可以将委托看做一种类型，EventHandler是函数的抽象，上面的代码相当于将委托类进行实例化然后绑定到zl的update方法
//当执行update时，delegatefunc会执行
package com.jl.myproject.desginPattern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ObserverMode {
	public static void main(String[] args){
		subject subject1 = new secretary();
		subject subject2 = new boss();
		Observer obser1 =new NBAObserver();
		Observer obser2 = new stockObserver();
		subject1.addObserver(obser1);
		subject1.addObserver(obser2);
		subject2.addObserver(obser1);
		subject2.addObserver(obser2);
		subject1.deleteObserver(obser1);
		subject1.notify1();
		System.out.print("\n");
		subject2.notify1();
	}
}

abstract class subject{
	String subjectname ;
	private List<Observer> Observerlist =new ArrayList<Observer>();
	
	
	public void addObserver(Observer observer){
		Observerlist.add(observer);
	}
	
	public void deleteObserver(Observer observer){
		Observerlist.remove(observer);
	}
	
	 public void notify1(){
		for(Observer observer :this.Observerlist){
			observer.update(this.subjectname);
		}
	}
}

class secretary extends subject {
	public secretary() {
		// TODO Auto-generated constructor stub
		subjectname = "secretary";
		
	}
}

class boss extends subject {
	public boss() {
		// TODO Auto-generated constructor stub
		subjectname = "boss";
	}
}

abstract class Observer{
//	String name;
	
	abstract public void update(String info);
}

class stockObserver extends Observer{
	@Override
	public void update(String info){
		System.out.printf("%s说关闭股票",info);
	}
}
class NBAObserver extends Observer{
	@Override
	public void update(String info){
		System.out.printf("%s说关闭NBA",info);
	}
}


