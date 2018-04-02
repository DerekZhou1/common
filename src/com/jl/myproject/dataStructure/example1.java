//正如标题所述，你需要使用两个栈来实现队列的一些操作。
//
//队列应支持push(element)，pop() 和 top()，其中pop是弹出队列中的第一个(最前面的)元素。
//
//pop和top方法都应该返回第一个元素的值。
//样例
//比如push(1), pop(), push(2), push(3), top(), pop()，你应该返回1，2和2
package com.jl.myproject.dataStructure;

import java.util.Stack;

public class example1 {
	public static void main(String[] args){
		myQueue queue = new myQueue();
		for(String a:args){
		queue.push(Integer.parseInt(a));
		}
		while(queue.top()!=-1){
			System.out.println(queue.pop());
		}
	}
	

}

 class myQueue{
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> temp = new Stack<Integer>();
		public myQueue(){
			
		}
		
		public void push(int a){
			stack.push(a);
		}
		
		public int top(){
			int number;
			if(stack.isEmpty()){
				return -1;
			}
			while(stack.size()>0){
				temp.push(stack.pop());
			}
			number =  temp.peek();
			while(temp.size()>0){
				stack.push(temp.pop());
			}
			return number;
		}
		
		public int pop(){
			int number;
			if(stack.isEmpty()){
				return -1;
			}
			while(stack.size()>0){
				temp.push(stack.pop());
			}
			number =  temp.pop();
			while(temp.size()>0){
				stack.push(temp.pop());
			}
			return number;
		}
	}
