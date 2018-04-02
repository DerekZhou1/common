//ResourceSynchronize 多线程对共享资源的访问，共享资源同步的方式 

//java中主要有两种方式Synchronized 和lock
//第一种 关键字synchronized：java为放置资源冲突提供了内置的支持 关键字synchronized，
//当代码执行到被synchronized保护的代码片段的时候，
//它会检查锁是否可用，然后获取锁，释放锁。
//实例：以一个多窗口卖票为实例，票数为共享资源
//Lock： 使用ReentrantLock lock1 = new ReentrantLock(false)；方式实例化一个锁
//启用和解锁分别使用lock1.lock()与lock1.unlock()
//不同实例的lock1与lock2就相当于synchronized（object1）与synchronized（object2）
//lock与synchronized相比可以手动的去加锁和解锁，使用更加灵活，可用范围更广，但没有synchronized
//方便
//共享锁只能通过lock方式实现，synchronized只能实现独占锁
//ReadWriteLock rwl = new ReentrantReadWriteLock();
//rwl.writeLock().lock(); 读锁
//rwl.readLock().lock();	写锁
package com.jl.myproject.desginPattern;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ResourceSynchronize {
	public static void main(String...args){
		MyThread1 th1 = new MyThread1();
		MyThread1 th2 = new MyThread1();
//		new Thread(th,"B").start();
//		new Thread(th,"A").start();
	
//		new Thread(th).start();
//		new Thread(th).start();
//		new Thread(th).start();
		//new Thread(th).start();
//		Thread.y
		
		
		final Object obj =new Object();
		final mytestsyn test = new mytestsyn();
		final mytestsyn test1 = new mytestsyn();
//		test test3 =new test();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				synchronized(obj)
				{
					for(int i =0 ; i<8;i++){
						System.out.println(Thread.currentThread().getName()+i);
					}
				}
				//test.test1();
			}
		},"A");
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				synchronized(obj)
				{
					for(int i = 0 ; i<8;i++){
						System.out.println(Thread.currentThread().getName()+i);
					}
				}
				//test1.test1();
			}
		},"B");
//		t1.start();
//		t2.start();
		
		
		
		
		//其他测试
		MyThread3 th3 = new MyThread3();
		Thread tt1=new Thread(th3,"A");
		Thread tt2=new Thread(th3,"B");
	//	tt1.setPriority(Thread.MIN_PRIORITY);
		//tt1.start();
		//tt2.start();
		
		//lock用法，使用共享锁
		final MyThread2 thread = new MyThread2();
//		for(int i =0;i<3;i++){
//			new Thread(new Runnable() {
//
//				@Override
//				public void run() {
//					//thread.getTicket();
//					thread.setTicket();
//				}
//			}).start();
//		}
//		for(int i =0;i<3;i++){
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				thread.getTicket();
//				//thread.setTicket();
//			}
//		}).start();
		Runnable  thr1 = new Runnable() {
			
			@Override
			public void run() {
				//thread.getTicket();
				thread.setTicket();
			}
		};
		Runnable  thr2 =new Runnable() {

			@Override
			public void run() {
				thread.getTicket();
				//thread.setTicket();
			}
		};
		new Thread(thr2).start();
		new Thread(thr2).start();
		new Thread(thr2).start();
		new Thread(thr1).start();
		new Thread(thr1).start();
		new Thread(thr1).start();
		
		
		
	
		
		
	
	}
}

/**方法1 采用synchronized
 * @author Administrator
 *
 */
 class  MyThread1 implements Runnable{
	private  int tickets;
	

	public MyThread1() {
		this.tickets = 10;
	}
	@Override
	public void run() {
		
		//synchronized()
		{
			for(int i =0 ; i<8;i++){

				if(tickets==0){
					break;
				}

				System.out.println(Thread.currentThread().getName()+"卖出一张票，剩余票数为："+(--tickets));
			}
		}
	}
}
 
 /**通过将synchronize放在不同位置，测试它不同的用法与功能
 * @author zl
 *
 */
class mytestsyn{
	 public   void test1( ){
		 synchronized(this)
		 {
				for(int i =0 ; i<8;i++){
					System.out.println(Thread.currentThread().getName()+i);
				}
			}
	 }
	 
	 public void test2(){
		 //synchronized(this)
		 {
				for(int i =0 ; i<8;i++){
					System.out.println(Thread.currentThread().getName()+i);
				}
			}
	 }
 }

/**方法二 采用lock，lock相比synchronized方法更加完善，下面举例实现共享锁
 * @author Administrator
 *
 */
class MyThread2 {
	//一般使用ReentrantLock，如果需要共享锁就使用ReentrantReadWriteLock
//	共享锁具有writeLock 和readLock方法
	//private ReentrantLock  lock= new ReentrantLock();
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private int tickets;
	public MyThread2() {
		this.tickets =3;
	}
	
//	@Override
//	public void run() {
//		
//		for(int i =0 ; i<8;i++){
//			if(tickets==0){
//				break;
//			}
//			System.out.println("卖出一张票，剩余票数为："+(--tickets));
//		}
//	}
	
	public  void getTicket(){
		
		for(int i =0 ; i<3;i++){
		
			lock.readLock().lock();
			if(tickets<=0){
				i =1000;
			}
			else{
				System.out.println(Thread.currentThread().getName()+"显示票数"+this.tickets);
			}
			lock.readLock().unlock();
			
		}
		
	}
	public void setTicket(){
		
		for(int i =0 ; i<3;i++){
			
			lock.writeLock().lock();
			
			if(tickets<=0){
				i =1000;
			}
			else{
				System.out.println(Thread.currentThread().getName()+"卖出一张票，剩余票数为："+(--tickets));
			}
			lock.writeLock().unlock();
			
		}
	}
}

/**此类用来测试这样一种情况：
 * synchronized（object）{
 * objec.wait();
 * objec.notify();
 * #####
 * }
 * notify方法后面的代码是否会执行
 
 * @author zl
 *
 */
class MyThread3 implements Runnable{
	@Override
	public void run() {
		synchronized(Object.class){
			for(int i =0 ;i<3;i++){
				System.out.println(Thread.currentThread().getName());
				//	System.out.println(Thread.currentThread().getName());
				Object.class.notify();
				try {
					Object.class.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"mmm"+i);
			}
		}
	}
}