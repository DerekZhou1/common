//StateMode 用于当一个对象的状态转换的表达式过于复杂时（比如很多ifelse），把
//对象的判断逻辑移到表示一系列不同状态的类当中
//实例：根据输入的时间不同，输入不同的内容 早上工作（12点之前），中午休息（12-~14）
//，下午工作（14-18），晚上睡觉（>18）
package com.jl.myproject.desginPattern;

public class StateMode {
	public static void main(String[] args){
		Showstate s = new Showstate();
		s.setHour(22);
		s.doSomething();
		s.setHour(8);
		s.doSomething();
	}
}


abstract class State{
	abstract public void handler(Showstate s);
	abstract public void show();
}

class MorningState extends State{
	@Override
	public void handler(Showstate s){
		if(s.getHour()>12){
			s.state = new NoonState();
			s.state.handler(s);
		}
	}
	
	@Override
	public void show(){
		System.out.println("早上工作");
	}
}

class NoonState extends State{
	@Override
	public void handler(Showstate s){
		if(s.getHour()>14){
			s.state = new AfternoonState();
			s.state.handler(s);
		}
	}
	
	@Override
	public void show(){
		System.out.println("休息");
	}
}

class AfternoonState extends State{
	@Override
	public void handler(Showstate s){
		if(s.getHour()>18){
			s.state = new NightState();
			s.state.handler(s);
		}
		
	}
	
	@Override
	public void show(){
		System.out.println("下午工作");
	}
}

class NightState extends State{
	@Override
	public void handler(Showstate s){
	}
	
	@Override
	public void show(){
		System.out.println("晚上睡觉");
	}
}

class Showstate{
	State state;
	private int hour;
	public int getHour(){
		return hour;
	}
	public void setHour(int hour){
		this.hour = hour;
		this.state = new MorningState();
		this.state.handler(this);
	}
	public Showstate() {
		 hour =9;
		 this.state =new MorningState();
		 
	}
	public void doSomething(){
		state.show();
	}
}

