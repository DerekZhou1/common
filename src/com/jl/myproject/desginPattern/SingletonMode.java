//SingletonMode 单例模式，保证类只有一个实例，并提供一个能够访问它的全局访问点
//核心，具有一个私有的构造函数，使外部不能直接实例化，然后写一个公有的实例化方法来实例化，
//类里面有一个类型为类本身的静态成员变量
package com.jl.myproject.desginPattern;

public class SingletonMode {
	public static void main(String...args){
		Single s = Single.getInstance();
		
		System.out.println(s.a);
		s.a = 10;
		Single s1 = Single.getInstance();
		System.out.println(s1.a);
//		//多线程使用单例模式有可能会实例化多个对象，要对实例化方法加锁
//		for( int i =0;i<10;i++){
//			final int a=i;
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				Single s1 = Single.getInstance();
//				System.out.println(a+s1.toString());
//				
//			}
//		}).start();
//		}
	}
}

class Single{
	private static Single s ;
	int a ;
	private Single() {
		a=1;
	}
	
	public static Single getInstance(){
		if(s==null){
			s= new Single();
		}
		return s;
	}
}
