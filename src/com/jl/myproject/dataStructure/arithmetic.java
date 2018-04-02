//四则运算      ---使用栈
//方法一：先将中缀表达式转换为后缀表达式，再用后缀表达式计算
//方法二：直接计算，使用两个栈，一个操作数栈，一个操作符栈
package com.jl.myproject.dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

public class arithmetic {
	public static void main(String[] args){
		String strings ="";
		//将参数中的空格去掉，有空格回自动识别为两个参数
		//System.out.println(new StringBuilder("123").reverse().toString());
		for(int i=0; i < args.length ; i ++){
			strings= strings.concat(args[i]);
		}
		//System.out.println(String.valueOf(new char[]{'a','b'}));
		ArrayList<String> expression = getList(strings);
		ArrayList<String> midExp = chgExp(expression);
//		for(String str:midExp){
//			System.out.println(str);
//		}
		System.out.println(getValue(midExp));
	}
	
	
	//将输入的字符串转换为由操作数和操作符组成的数组
	static ArrayList<String> getList(String expression){
		ArrayList<String> list = new ArrayList<String>();
		//int marker =-1;
		//boole
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0 ; i < expression.length() ; i++){	
			char  temp = expression.charAt(i);
			if(temp <'0' ||temp > '9'){
				if(stack.isEmpty()){
					list.add(String.valueOf(temp));
				}
				else {
					char[] chars = new char[stack.size()];
					int length =stack.size();
					for(int j =0;j <length; j++){
						chars[length-j-1]=stack.pop();
					}
					list.add(String.valueOf(chars));
					list.add(String.valueOf(temp));
				}
			}
			else if( temp>='0' && temp <='9'){
				 stack.push(temp);
			}
		}
		return list;
	}
	
	
	//
	
	//中缀表达式转换为后缀表达式   ---此算法只支持小括号，不支持中括号和大括号
	static	ArrayList<String> chgExp(ArrayList<String> expression){
		Stack<String> stack1 = new Stack<String>();
		ArrayList<String> tailExp = new ArrayList<String>();
		for(int i =0; i < expression.size() ; i++){
			String temp = expression.get(i);
			if(temp.equals("(") ||temp.equals("*") ||temp.equals("/")){
				stack1.push(temp);
			}
			else if(temp.equals(")")){
				while(!stack1.peek().equals("(")){
					tailExp.add(stack1.pop());
				}
				stack1.pop();
			}
			else if(temp.equals("+") ||temp.equals("-")){
				if(stack1.size()==0){
					stack1.push(temp);
				}
				else if(stack1.peek().equals("*")|| stack1.peek().equals("/")){
					do{
						tailExp.add(stack1.pop());
					}while(stack1.peek().equals("+")||stack1.peek().equals("-")||stack1.size()==0);
				}
				else{
					stack1.push(temp);
				}
			}
			else {
				tailExp.add(temp);
			}
		}
		while(stack1.size()>0){
			tailExp.add(stack1.pop());
		}
		return tailExp;
	}

	//计算中缀表达式
	static int getValue(ArrayList<String> expression){
		Stack<String> stack1 = new Stack<String>();
		String temp = "";
		int operA;
		int operB;
		for(int i = 0 ; i <expression.size(); i++){
			temp = expression.get(i);
			switch(temp){
			case "+":
				operB =Integer.valueOf(stack1.pop());
				operA =Integer.valueOf(stack1.pop());			
				stack1.push(String.valueOf(operA+operB));
				break;
			case "-":
				operB =Integer.valueOf(stack1.pop());
				operA =Integer.valueOf(stack1.pop());			
				stack1.push(String.valueOf(operA-operB));
				break;
			case "*":
				operB =Integer.valueOf(stack1.pop());
				operA =Integer.valueOf(stack1.pop());			
				stack1.push(String.valueOf(operA*operB));
				break;
			case "/":
				operB =Integer.valueOf(stack1.pop());
				operA =Integer.valueOf(stack1.pop());			
				stack1.push(String.valueOf(operA/operB));
				break;
			default:stack1.push(temp);
			}
		}
		return Integer.valueOf(stack1.pop());
	}
}
