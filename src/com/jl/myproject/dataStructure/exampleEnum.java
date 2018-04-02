//java enum 与类同级，默认枚举值从0开始，依次递增，通过name访问名称，ordinal访问值
//如果想对枚举值进行自定义赋初值，则需自己构造构造函数,以及属性
package com.jl.myproject.dataStructure;



public class exampleEnum {
	public static void main(String[] args){
		for(myenum n :myenum.values()){
			System.out.println(n.name() +n.getValue()+n.temperature());
		}
	}
}
enum myenum{
	spring,summer(2),autumn(3),winter(4){
		@Override
		public String temperature(){
			 return "cold";
		 }
	};
	 private int value;
	 public int getValue(){
		 return value;
	 }
	 private myenum(){
			
		}
	 private myenum(int value){
		this.value =value;
	}
	 public String temperature(){
		 return "hot";
	 }
	 
	 
}
