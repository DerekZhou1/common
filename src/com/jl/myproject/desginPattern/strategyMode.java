//策略模式 策略模式定义了算法家族，分别封装起来，让他们之间相互替换，此模式让算法的变化，
//不会影响到使用算法的客户
//例：商场收银系统经常有打折，促销，满减的活动，要设计一种模式可以对这个活动进行灵活调整
//核心 ：策略类的主要功能是分离客户端和算法类，也就是让客户端访问尽量少的类。
//相比较工厂模式，main函数要访问工厂类和算法类；策略模式只访问一个类，进一步解耦
//个人觉得是对简单工厂的优化
package com.jl.myproject.desginPattern;

public class strategyMode {
	public static void main(String[] args){
		strategyContext str = new strategyContext("normal");
		double cash =str.getCash(1000);
		System.out.println(cash);
	}
}

class strategyContext{
	private strategy stra = null;
	public strategyContext(String method) {
		switch(method){
		case "normal":this.stra =new cashNormal();break;
		case "rebate":this.stra =new cashRebate();break;
		case "return":this.stra =new cashReturn();break;
	
		}
	}
	
	public double getCash(double cash){
		return stra.getCash(cash);
	}
}



abstract class strategy{
	abstract public double getCash(double cash);
}

class cashRebate extends strategy{

	@Override
	public double getCash(double cash) {
		double rebate = 0.3;
		return cash*rebate;
	}
}

class cashReturn extends strategy{

	@Override
	public double getCash(double cash) {
		if(cash>=500){
			return cash-100;
		}
		else{
			return cash;
		}
	}
}

class cashNormal extends strategy{

	@Override
	public double getCash(double cash) {
		
			return cash;
		
	}
}