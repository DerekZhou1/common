// CompositeMode 聚合模式，当需求中体现部分与整体的结构时，希望用户忽略单个对象和对象整体的时候
//使用聚合模式
//实例： 一个公司有多个分公司，但是分公司和总公司的操作模式相同，要能在表示层忽略总公司和分公司的区别
//它是一个树形结构，顶层是总公司（它是一个聚合节点），下面包含多个部门和多个分公司，多个部门都是总公司的子叶节点，
//分公司是聚合节点，结构与总公司相同，所以理论上可以无限扩展
//
//下面的实例有种弊端，因为把分公司和总公司的部门看做同一种结构，实际上部门是不会用增改部门这个方法的，这个方法没有意义
//但是如果将公司和部门变成两个不同的抽象类，就无法进行统一的操作，树的每个节点不统一，
//代码会更加复杂，各有利弊
package com.jl.myproject.desginPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class CompositeMode {
	public static void main(String...args){
		Company root = new ConcreteCompany("北京总公司");
		root.add(new HumanResourceCompany("北京公司人力资源部"));
		root.add(new SaleCompany("北京公司销售部"));
		Company wuhan =new ConcreteCompany("武汉公司");
		wuhan.add(new HumanResourceCompany("武汉公司人力资源部"));
		wuhan.add(new SaleCompany("武汉公司销售部"));
		Company nanjin =new ConcreteCompany("南京公司");
		nanjin.add(new HumanResourceCompany("南京公司人力资源部"));
		nanjin.add(new SaleCompany("南京公司销售部"));
		root.add(wuhan);
		root.add(nanjin);
		root.duty();
	}
}

abstract class Company{
	String name;
	public Company(String name) {
		this.name =name;
	}
	abstract public void add(Company com);
	abstract public void remove(Company com);
	abstract public void display(int depth);
	abstract public void duty();
}

class ConcreteCompany extends Company{
	
	List<Company> companylist =new ArrayList<Company>();
	public ConcreteCompany(String name) {
		super(name);
	}
	@Override
	public void add(Company com) {
		
		companylist.add(com);
	}

	@Override
	public void remove(Company com) {
		companylist.remove(com);
		
	}

	@Override
	public void display(int depth) {
		char[] chars = new char[depth];
		Arrays.fill(chars, '-');
		String s = new String(chars);
		System.out.println(s+ name);
		for(Company com :companylist){
			com.display(depth+2);
		}
	}
	@Override
	public void duty() {
		for(Company com :companylist){
			com.duty();
		}
	}
}

class HumanResourceCompany extends Company{ //人事部

	public HumanResourceCompany(String name) {
		super(name);
	}
	@Override
	public void add(Company com) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Company com) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(int depth) {
		char[] chars = new char[depth];
		Arrays.fill(chars, '-');
		String s = new String(chars);
		System.out.println(s+ name);
		
	}

	@Override
	public void duty() {
		System.out.printf("%s的职责是招聘",name);
		System.out.println();
	}
}

class SaleCompany extends Company{ //销售部

	public SaleCompany(String name) {
		super(name);
	}
	@Override
	public void add(Company com) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Company com) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(int depth) {
		char[] chars = new char[depth];
		Arrays.fill(chars, '-');
		String s = new String(chars);
		System.out.println(s+ name);
		
	}

	@Override
	public void duty() {
		System.out.printf("%s的职责是销售",name);
		System.out.println();
	}
}
