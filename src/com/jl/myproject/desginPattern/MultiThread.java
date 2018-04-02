//MultiThread 多线程举例 与lock的区别
//核心：此处主要学习用Runnable实现多线程，使用Thread继承的方法我感觉核心还是以实现Runnable接口
//为基础，只是换了一种写法而已；
//展示了多线程常用的一些方法 如设置优先级priority，睡眠sleep，等待wait，让出yield
//唤醒notify，等待完成后执行join
//同时 这个实例展示了如何对于共享资源进行锁

package com.jl.myproject.desginPattern;

public class MultiThread {
	public static void main(String...args){
		//方法1 继承Thread类
		Thread1 th1 = new Thread1("a");
		Thread1 th2 = new Thread1("b");
		Thread1 th7 = new Thread1("c");
		Thread1 th8 = new Thread1("d");
		//start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），
//		什么时候运行是由操作系统决定的。所以第一个显示的不一定是a
		
		
		
//		对自己定义的线程对象使用start方法重复调用的话，会出现java.lang.IllegalThreadStateException异常
//		如果是采用Runnable接口就可以重复使用,像这样：
//		new Thread(th).start();
//		new Thread(th).start();
//		这是两个线程，并且两个线程的资源是共享的
//		在多线程的用法中，资源共享的意义是很重要的，比如说有三个卖票的窗口，但是他们卖票都是受
//		总票数约束的，不是完全独立的，这种情况就一定要用Runnable接口
		//设置线程的优先级，因为线程个数不多，所以线程的争抢关系不明显，结果不是很显著
		th1.setPriority(Thread.MIN_PRIORITY);
		th2.setPriority(Thread.NORM_PRIORITY);
		th7.setPriority(Thread.MAX_PRIORITY);
		th8.setPriority(Thread.NORM_PRIORITY);
		
		th2.start();
//		try {
//			th2.join();	//使用join方法，使th2线程执行完毕后再继续执行主程序
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		th1.start();
		th7.start();
		th8.start();
		System.out.println("主程序结束");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		
		//方法2 实现Runnable接口
		//Thread2 th3 = new Thread2("c");
		Thread2 th4 = new Thread2();
		//new Thread(th3).start();
		//runnable接口一个对象可以开多个线程，实现资源共享
		new Thread(th4,"A").start();
		new Thread(th4,"B").start();
	}
}

class Thread1 extends Thread{
	private String name;
	public Thread1(String name) {
		this.name = name;
	}
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(name+"运行："+i);
		}
	}
}

class Thread2 implements Runnable{
	private int ticket=10;
	
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			if(ticket==0){
				break;
			}
			System.out.println(Thread.currentThread().getName()+"卖了一张票，剩余票数为："+(--ticket));
		}
		
	}
	
}
