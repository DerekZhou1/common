//LockExample 锁的演示，可重入锁（递归锁）和不可重入锁（自旋锁）的实现原理
//不可重入锁如果没有获得资源就不停的获取，直到获得为止，
//可重入锁当资源被锁定时，会查看锁定资源的是不是自己这个线程，如果是自己就直接拿到锁
//可重入指的是线程的“可重入”，也说明要去拿一个锁的主体一定是一个线程，我们研究锁要从线程的角度去看
package com.jl.myproject.desginPattern;

public class LockExample {
	public static void main(String...args) throws InterruptedException{
		final Counter counter = new Counter();
		for(int i=0;i<3;i++){
			new Thread(new Runnable() {

				@Override
				public void run() {
						counter.print();
				}
			}).start();
		}
	  //counter.print();
	}
}

/**不可重入锁，自旋锁，会循环访问持有的变量
 * @author zl
 *
 */
class MyUnReentrantLock{
	private boolean isLocked = false;
	public void lock() throws InterruptedException{
		while(isLocked){
			//这里必须要睡眠，不然重复调用太频繁会堆栈溢出
			Thread.sleep(1);
			lock();
		}
		isLocked = true;
	}
	
	public void unlock(){
		isLocked = false;
		
	}
}

class MyReentrantLock{
	private int Lockedcount = 0; //被锁了几次
	private Thread lockedby = null; //被哪个线程锁住
	public void lock()  {
		if(Lockedcount==0){
			Lockedcount++;
			lockedby = Thread.currentThread();
		}
		else{
			if(lockedby==Thread.currentThread()){
				Lockedcount++;
			}
			else{
				//这里必须要睡眠一下，不然请求频率太频繁，方法重复调用自己造成堆栈溢出
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lock();
			}
		}
	}
	
	public void unlock(){
		if(Thread.currentThread()==this.lockedby){
			Lockedcount--;
			if(Lockedcount==0){
				this.lockedby =null;
			}
		}
	}
}

/**count属于临界资源，这里演示的就是锁的重入，这里使用不可重入锁MyUnReentrantLock是无法完成
 * 工作的，程序在进入doAdd会锁住，无限循环
 * @author zl
 *
 */
class Counter{
	MyUnReentrantLock lock = new MyUnReentrantLock();
	private int count = 0;
	public void print()  {
		try {
			lock.lock();
			doAdd();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
	}
	
	public void doAdd()  {
		System.out.println(Thread.currentThread().getName()+"front"+count);
		try {
			lock.lock();
			count++;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println(Thread.currentThread().getName()+"rear"+count);
		}
		
	}
}
