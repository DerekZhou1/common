//ResponsePattern 职责链模式，请求者并不知道自己的请求由哪一个决策者来操作，实现请求和决策的分离
//实例：数字小于10由A操作，10到20由B操作，大于20由C操作，
package com.jl.myproject.desginPattern;

public class ResponsePattern {
	public static void main(String...args){
		HandlerA handlerA = new HandlerA();
		HandlerB handlerB = new HandlerB();
		HandlerC handlerC = new HandlerC();
		handlerA.setSuccessor(handlerB);
		handlerB.setSuccessor(handlerC);
		handlerA.request(31);
	}
}

abstract class Handler{
	Handler handler=null;
	public void setSuccessor(Handler handler){
		this.handler = handler;
	}
	abstract public void request(int number);
}

class HandlerA extends Handler{

	@Override
	public void request(int number) {
		if(number>10){
			handler.request(number);
		}
		else
		{
			System.out.println(number);
		}
	}
}

class HandlerB extends Handler{

	@Override
	public void request(int number) {
		if(number>20){
			handler.request(number);
		}
		else
		{
			System.out.println(10*number);
		}
	}
}

class HandlerC extends Handler{

	@Override
	public void request(int number) {
		if(number>30){
			System.out.println("数字太大");
		}
		else
		{
			System.out.println(100*number);
		}
	}
}