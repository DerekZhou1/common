//内部类实例，测试多种内部类，静态内部类，成员内部类，局部(方法)内部类，匿名内部类
//比较麻烦的是匿名内部类,一般用到会比较少，匿名类是定义类的子类，匿名类中定义的方法必须是父类中已定义的方法
package com.jl.myproject.desginPattern;

public class OuterClass {
	public static void main(String...args){
		InnerClass inner = new InnerClass();
		inner.show();
		inner.showInner();
		inner.showLocalInner();
		inner.showAnonymousClass();
	}
}
class InnerClass{
	private static String varA="varA"; 
	private  String varB="varB"; 
	private static String str ="outerVariable";
	public void show(){
		System.out.println("outer");
	}
	
	public void showInner(){
		MemInnerClass inner = new MemInnerClass();
		inner.innerShow();
		
	}
	
	public static void showStaticInner(){
		staticInnerClass.innerShow();
	}
	

	
	public void showLocalInner(){
		
		 /**
		 * @author zl
		 *局部类（方法类），在方法里面定义类,方法外不能使用
		 */
		class LocalInner{
			private void localInner(){
				System.out.println(varA);
			}
		}
		 
		 LocalInner a = new LocalInner();
		 a.localInner();
	}
	
	/**匿名内部类就是没有名字的局部内部类，不使用关键字class, extends, implements, 没有构造方法。
	　　匿名内部类隐式地继承了一个父类或者实现了一个接口。
	　　匿名内部类使用得比较多，通常是作为一个方法参数。
			 a·只用到类的一个实例。
	　　　　　　b·类在定义后马上用到。
	　　　　　　c·类非常小（SUN推荐是在4行代码以下）
	　　　　　　d·给类命名并不会导致你的代码更容易被理解。
	 * @author 
	 *
	 */
	public void showAnonymousClass(){
		Anonymous class1 =new Anonymous(){
			public void showAnonyFunc(){
//				System.out.println("anonymous");
				super.showAnonyFunc();
			}
		
		};
		
		class1.showAnonyFunc();
	}
	
	/**
	 * @author zl
	 *成员内部类
	 */
	 class MemInnerClass{
		private void innerShow(){
			System.out.println("inner");
		}
	}
	
	/**
	 * @author zl
	 *静态内部类
	 */
	private static class staticInnerClass{
		private static void innerShow(){
			System.out.println(str);
		}
	}
}

class Anonymous{
	protected void showAnonyFunc(){
		System.out.println("aaaaaaaa");
	}
}



