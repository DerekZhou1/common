//反射机制详解

package com.jl.myproject.desginPattern;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
public class Reflect {
	public static void main(String[] args){
	Employee e = new Employee(200);
	 Class class1 = getMyClass();
	getName(class1);
	
	 int a=2;
	 System.out.println(a);
	 getObject(class1);
	 getAfield(class1);
	 getAllFields(class1);
	 getMethod(class1);
	 System.out.println("Construct");
	 getConstruct(class1);
//	Class class1 =Class.forName("Employee");
	}
	
	
	
	/**
	 * 获取一个类，有三种方法
	 * public void testGetClass() throws ClassNotFoundException {  
    Class clazz = null;  
  
    //1 直接通过类名.Class的方式得到  
    clazz = Person.class;  
    System.out.println("通过类名: " + clazz);  
  
    //2 通过对象的getClass()方法获取,这个使用的少（一般是传的是Object，不知道是什么类型的时候才用）  
    Object obj = new Person();  
    clazz = obj.getClass();  
    System.out.println("通过getClass(): " + clazz);  
  
    //3 通过全类名获取，用的比较多，但可能抛出ClassNotFoundException异常  
    clazz = Class.forName("com.java.reflection.Person");  
    System.out.println("通过全类名获取: " + clazz);  
	 */
	public static Class getMyClass(){
		Class clazz1;
		Class clazz2;
		Class clazz3;
		//方法1
		 clazz1 = Employee.class;
		//方法2
		 try {
			clazz2 =Class.forName("com.jl.myproject.desginPattern.Employee");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("123");
		}
		 //方法3
		 Employee em = new Employee(300);
		 clazz3 = em.getClass();
		
		 return clazz3;
	}
	
	/**
	 * 获取对象的包名，类名, 
	 * 
	 * 此处输出为：com.jl.myproject.desginPattern.Employee
	 */
	public static void getName(Class clazz){
	
		String name = clazz.getClass().getName();
		System.out.println(name);
	}
	
	public static void getConstruct(Class clazz){
		try {
			Constructor cons = clazz.getConstructor(int.class);
			Object obj =cons.newInstance(449);
			System.out.println(((Employee)obj).getSalary());
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**获取对象，将类实例化，采用Class类的newInstance方法
	 * @param clazz
	 */
	public static void getObject(Class clazz){
		try {
			Object obj = clazz.newInstance();
			System.out.println(((Employee)obj).getSalary());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**获取类的特定的属性并修改,并获得字段的访问修饰符
	 * getDeclaredField 获取类定义的一个属性
	 * getField 获取类具有可访问权限的一个属性
	 * @param clazz
	 */
	public static void getAfield(Class clazz){
		try {
			Field salary = clazz.getField("salary");
			int mod=  salary.getModifiers();
			System.out.println(Modifier.toString(mod));
			//实例化一个对象
			Object obj =clazz.newInstance();
			//打破封装,setAccessible将字段变成可访问权限
			salary.setAccessible(true);
			//修改属性值
			salary.set(obj, 666);
			System.out.println(((Employee)obj).getSalary());
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**获取类的所有属性，但只能获取具有访问权限的属性
	 * * getDeclaredField 获取类所有定义的属性
	 * getField 获取类所有具有可访问权限的属性
	 * @param clazz
	 */
	public static void getAllFields(Class clazz){
		Field[] fields =clazz.getDeclaredFields();
		for(Field a :fields){
			System.out.println(a.getName());
		}
	}
	
	/**获取对象的方法并调用方法
	 * @param clazz
	 */
	public static void getMethod(Class clazz){
		try {
			Method me = clazz.getDeclaredMethod("Method",int.class);
			Object obj = clazz.newInstance();
			//调用方法，第一个参数是对象，后面参数是方法的实参
			Object result =me.invoke(obj,777);
			System.out.println((int)result);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


class Employee{
	public int salary;
	public String name;
	public Employee() {
		this.salary=100;
	}
	public Employee(int salary) {
		this.salary =salary;
	}
	
	public int getSalary(){
		return this.salary;
	}
	
	public int Method(int a){
		return a+this.salary;
	}
}