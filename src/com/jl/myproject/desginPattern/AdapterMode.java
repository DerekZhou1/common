//用于类接口的转换，对于一个使用正常的类，但是接口方法又不符合，短时间内不能改变的情况
//Target类是目标接口，也就是需要生成的接口，adapter是适配器，adapteree是原接口
//这样做可以保证既不改变原接口，又不改变客户期望的接口，对客户的调用进行完全封装
package com.jl.myproject.desginPattern;

public class AdapterMode {
	public static void main(String...args){
		Target tar = new Adapter();
		tar.execute();
	}
}

class Target{
	public void execute(){
		System.out.println("执行Target方法");
	}
}



class Adapter extends Target{
	private Adapteree ada = new Adapteree();
	@Override
	public void execute(){
		ada.newExecute();
	}
}

class Adapteree {
	public void newExecute(){
		System.out.println("执行Adapter方法");
	}
	
}
