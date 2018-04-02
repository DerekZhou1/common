package com.jl.myproject.desginPattern;

public class FactoryMethod {
	public static void main(String[] args){
		IFactory factory = new AddFactory();
		Operation1 oper = factory.getOperation();
		oper.setA(1);
		oper.setB(2);
		
		double result =oper.getResult();
		System.out.println(result);
	}
}

interface IFactory{
	public Operation1 getOperation();
}

class AddFactory implements IFactory{
	@Override
	public Operation1 getOperation() {
		return new Operation1Add();
		
	}
}

class DivisFactory implements IFactory{
	@Override
	public Operation1 getOperation() {
		return new Operation1Divis();
	}
}

class SubFactory implements IFactory{
	@Override
	public Operation1 getOperation() {
		return new Operation1Sub();
	}
}

class MultiFactory implements IFactory{
	@Override
	public Operation1 getOperation() {
		return new Operation1Multi();
	}
}



abstract class Operation1{
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

class Operation1Add extends Operation1{

	@Override
	public double getResult() {
		double result=0;
		result = getA()+getB();
		return result;
	}
}

class Operation1Sub extends Operation1{

	@Override
	public double getResult() {
		double result;
		result = getA()-getB();
		return result;
	}
}

class Operation1Divis extends Operation1{
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

class Operation1Multi extends Operation1{

	@Override
	public double getResult() {
		double result;
		result = getA()*getB();
		return result;
	}
}