// 使用简单工厂完成计算器功能
//简单工厂模式：工厂就是生产的意思，简单工厂模式就是专门定义个类，这个类用来实例化其他类，
//根据条件的不同实例化不同的类
package com.jl.myproject.desginPattern;

public class Factory {
	public static void main(String[] args){
		Operation1 oper = OperationFactory.CreateOperate("*");
		oper.setA(1);
		oper.setB(2);
		
		double result =oper.getResult();
		System.out.println(result);
	}
}

class OperationFactory{
	
	
	public static Operation1 CreateOperate(String operate) {
		Operation1 oper =null;
		switch(operate){
		case "+":oper= new OperationAdd();break;
		case "-":oper= new OperationSub();break;
		case "*":oper= new OperationMulti();break;
		case "/":oper= new OperationDivis();break;
		}
		return oper;
	}
}

abstract class Operation{
	protected double _numberA;
	protected double _numberB;
	public void setA(int number){
		this._numberA =number;
	}
	public double getA(){
		return this._numberA ;
	}
	public void setB(int number){
		this._numberB =number;
	}
	public double getB(){
		return this._numberB;
	}
	abstract public double getResult( );
}

class OperationAdd extends Operation1{

	@Override
	public double getResult() {
		double result=0;
		result = getA()+getB();
		return result;
	}
}

class OperationSub extends Operation1{

	@Override
	public double getResult() {
		double result;
		result = getA()-getB();
		return result;
	}
}

class OperationDivis extends Operation1{
	@Override
	public double getB(){
		if(this._numberB!=0){
			return this._numberB;
		}
		else{
			return 0;
		}
	}
	@Override
	public double getResult() {
		double result;
		result = getA()/getB();
		return result;
	}
}

class OperationMulti extends Operation1{

	@Override
	public double getResult() {
		double result;
		result = getA()*getB();
		return result;
	}
}
