//delegateMode： 委托模式 实现触发事件和监听事件的完全解耦
//delegateMode： 委托模式 实现触发事件和监听事件的完全解耦。java的委托要利用反射
//一个班级，有两类学生，A类：不学习，玩，但是玩的东西不一样，有的是做游戏，与的是看电视（有点不合理）
//B类：放哨的学生，专门看老师的动向，如果老师进班了就立即通知大家。
//如此就形成了一个需求，放哨的学生要通知所有玩的学生：老师来了，而不同的学生有不同的反应，有的马上把电视关闭，有的停止玩游戏。
//设计的要求如下，让A类学生和B类学生完全解耦，即B类完全不知道A类的学生，却可以通知A类的学生。
//需要构造一个Event类和Eventhandler类，event类保存一个单独的监听对象，evnethandler类
//实现监听列表，并提供监听对外接口，evnethandler主要是因为通常一个事件具有多个监听，
package com.jl.myproject.desginPattern;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class delegateMode {
	public static void main(String...args){
		Listener game = new PlayGame();
		Listener tv = new WatchTV();
		Notify good = new GoodNotify();
		Notify bad = new BadNotify();
		good.addListener(game, "run", "123");
		good.addListener(game, "run", "456");
		bad.addListener(tv, "getCaught");
		bad.addListener(tv, "getCaught");
		good.notify1();
		bad.notify1();
	}
}

class Event{
	private Object obj;
	private String methodName;
	private Object[] params;
	private Class[] paramType;
	public Event(Object obj, String methodName,Object...args) {
		this.obj = obj;
		this.methodName = methodName;
		this.params = args;
		getParamType(args);
	}
	
	private void getParamType(Object...args){
		if(args.length!=0){
			this.paramType =new Class[args.length];
			for(int i=0;i<args.length;i++ ){
				this.paramType[i]=args[i].getClass();
			}
		}
		else{
			this.paramType =null;
		}
	}
	
	public void invoke(){
		try {
			Method method = obj.getClass().getMethod(methodName, paramType);
			method.invoke(obj, params);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class EventHandler{
	 List<Event> events;
	 public EventHandler() {
		 events =new ArrayList<Event>();
	}
	public void addEvent(Object obj, String methodName,Object...args){
		events.add(new Event(obj, methodName, args));
	}
	
	public void removeEvent(Object obj, String methodName,Object...args){
		events.remove(new Event(obj, methodName, args));
	}
}

abstract class Notify{
	protected EventHandler eventhandler = new EventHandler();
	void addListener(Object obj, String methodName,Object...args){
		eventhandler.addEvent(obj, methodName, args);
	}

	void Listener(Object obj, String methodName,Object...args){
		eventhandler.removeEvent(obj, methodName, args);
	}

	public abstract void notify1();
}

class GoodNotify extends Notify{
	@Override
	public  void notify1(){
		for(Event e :eventhandler.events){
			e.invoke();
		}
	}
}

class BadNotify extends Notify{
	@Override
	public  void notify1(){
		for(Event e :eventhandler.events){
			e.invoke();
		}
	}
}

abstract class Listener{
	public abstract void run(String name);
	public abstract void getCaught();
}

class PlayGame extends Listener{
	@Override
	public  void run(String name){
		System.out.println("runGame:" +name);
	}
	
	@Override
	public void getCaught(){
		System.out.println("GamegotCaught");
	}
}

class WatchTV extends Listener{
	@Override
	public  void run(String name){
		System.out.println("runTV:" +name);
	}
	
	@Override
	public void getCaught(){
		System.out.println("TVgotCaught");
	}
}
