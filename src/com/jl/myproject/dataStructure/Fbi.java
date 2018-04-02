//斐波那契数列,计算第n个数字的值  ,返回斐波那契数组   ---最基础递归调用

//0,1,1,2,3,5,8....



package com.jl.myproject.dataStructure;

public class Fbi {
	public  static void main(String[]  args){
		int n = Integer.parseInt(args[0])  ;
		int[] array =new int[n+1];
		int c =getFbi(array,n);
		for(Integer a :array){
			System.out.println(a);
		}
	}
	
	
	static int getFbi(int[] array,int n){
		if(n<3){
			array[n]=1;
			return 1;
		}
		else{
			array[n]=getFbi(array,n-1)+getFbi(array,n-2);
			return array[n];
		}
	}
}
