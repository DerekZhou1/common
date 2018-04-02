//IteratorMode 迭代器模式，用于对一个集合进行遍历
//实例 用数组现实一个mylist类，可以对一个数组进行各项操作
//first，isFinish，next，currentItem   指针指向第一个，是否在队尾，指针向下一个，返回当前值

//迭代方式的代码在ConcreteIterator中实现，采用不同的迭代方式（正序，返序）可以添加多个ConcreteIterator类
//调用的时候使用Iterator调用，可以屏蔽不同的迭代方式
//ConcreteAggregate用来保存数据集合
package com.jl.myproject.desginPattern;

import java.util.ArrayList;
import java.util.List;

public class IteratorMode {
	public static void main(String...args){
		ConcreteAggregate list = new ConcreteAggregate();
		ConcreteAggregate a =new ConcreteAggregate();
		ConcreteAggregate b =new ConcreteAggregate();
		ConcreteAggregate c =new ConcreteAggregate();
		list.add(a);
		list.add(b);
		list.add(c);
		
		Iterator ite = list.CreateIterator();
		ite.first();
		while(!ite.isFinish()){
			System.out.printf("%s你好",ite.currentItem());
			ite.next();
		}
	}
}

abstract class Iterator{
	abstract public  void  first();
	abstract public  Boolean  isFinish();
	abstract public  void  next();
	abstract public  Aggregate  currentItem();
}

class ConcreteIterator extends Iterator{
	List<Aggregate> objs = new ArrayList<Aggregate>();
	int current =0;
	public ConcreteIterator(List<Aggregate> objs) {
		this.objs =objs;
	}
	@Override
	public void first() {
		current =0;
	}

	@Override
	public Boolean isFinish() {
		return objs.size()<current+1?true:false;
	}

	/* (non-Javadoc)
	 * next方法将指针往下一个
	 * @see com.jl.myproject.desginPattern.Iterator#next()
	 */
	@Override
	public void next() {
		 current++;
	}

	@Override
	public Aggregate currentItem() {
		return objs.get(current);
	}
}

abstract class Aggregate{
	abstract public Iterator CreateIterator();
}

class ConcreteAggregate extends Aggregate{

	List<Aggregate> objs ;
	public ConcreteAggregate() {
		objs = new ArrayList<Aggregate>();
	}
	@Override
	public Iterator CreateIterator() {
		
		return new ConcreteIterator(objs);
	}
	
	public void add(Aggregate obj){
		objs.add(obj);
	}
	
}
