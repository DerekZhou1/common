//PrintABC 使用三线程打印ABC
//建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，
//要求线程同时运行，交替打印10次ABC
//核心 ：这里的逻辑很简单，每个线程执行完成之后，唤醒下一个线程，然后让自己处理等待状态
//关键在于要理解java的monitor逻辑，了解nofity和wait正在做的是什么？
//object.notify的含义是在对象object监视器中的等待队列中随机挑选一个线程将它移入到
//锁池队列（当线程将要运行，准备获取同步锁时，同步锁被别人拿走，这个线程就会进入锁池队列）
//object.wait的含义是拿着这个对象锁的线程的同步块执行完毕后，将线程移入等待队列

package com.jl.myproject.desginPattern;

public class PrintABC {
	public static void main(String...args) throws Exception{
		myObject a= new myObject("A");
		myObject b= new myObject("B");
		myObject c= new myObject("C");
		Print printA = new Print("A", c, a);
		Print printB = new Print("B", a, b);
		Print printC = new Print("C", b, c);

		new Thread(printA).start();
		//这里的sleep是必须要加的，不然有可能会产生死锁，资源相互争抢。因为每个线程获取共享资源的顺序不一样
		Thread.sleep(1);
		new Thread(printB).start();
		Thread.sleep(1);
		new Thread(printC).start();
		

				
	}
}

class Print implements Runnable{
	String name ;
	Object prev;
	Object self;
	public Print(String name ,Object prev,Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			//这种写法比较简单，但有个很大的问题，每个线程抓取临界资源的顺序不一样，有可能死锁
		synchronized (prev) {
			synchronized (self) {
		//{{
					System.out.print(this.name);
					self.notify();
					
			}
			try {
//				self.wait();
				prev.wait();
			//	System.out.print(this.name+i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		}
	}
	
}

class myObject {
	String name;
	public myObject(String name) {
		this.name= name;
	}
}