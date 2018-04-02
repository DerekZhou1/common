package com.jl.myproject.desginPattern;

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.Arrays;

import com.jl.myproject.desginPattern.ClassInitial;
public class test   {
	public int c;
	
	public static void main(String[] args){
//		char[] chars = new char[4];
//		Arrays.fill(chars, '-');
//		System.out.println(new String(chars));
//		String path =AbstractFactoryMode.class.getResource("/").getPath();
//		System.out.println(path);
//		path =AbstractFactoryMode.class.getResource("").getPath();
//		System.out.println(path);
//		mytest1 j =new mytest1();
//		j =new mytest1();
//		mytest1 j = new mytest1();
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("1");
//				
//			}
//		}).start();
//new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("1");
//				
//			}
//		}).start();
		mytest1 test = new mytest1();
		new Thread(test).start();
		test.number =4;
		test.ready = true;
	}
	//java.util.concurrent.atomic.
	
}


class mytest1 implements Runnable{
	public static boolean ready;
	public static int number ;
	public  void  myfunc() throws InterruptedException{
		
		myfunc();
	}

	@Override
	public void run() {
		while(!ready){
			Thread.yield();
		}
		System.out.println(this.ready);
		System.out.println(this.number);
	}
}
