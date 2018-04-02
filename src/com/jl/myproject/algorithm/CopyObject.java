//一般的OBJECT A=B是传递引用，无法达到复制的效果。
//这个例子是对对象进行浅复制和深复制，利用重新clone方法，调用超类Object的克隆方法
//浅复制是复制对象时，对象的字段为引用类型（对象），仍然是传递的引用，而深复制对象的引用类型也会进行复制


//如果是对象内部只有少数引用对象，可以用重写clone方法解决.
//如果引用类型较多，或者引用类型内部还包含引用类型,用clone就很麻烦，建议使用序列化的方式来实现
//序列化就是将对象写到流的过程，写到流中的对象是原有对象的一个拷贝，
//而原对象仍然存在于内存中。通过序列化实现的拷贝不仅可以复制对象本身，
//而且可以复制其引用的成员对象，因此通过序列化将对象写到一个流中，再从流里将其读出来，
//可以实现深克隆。需要注意的是能够实现序列化的对象其类必须实现Serializable接口，
//否则无法实现序列化操作。

package com.jl.myproject.algorithm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class CopyObject {
	private static final long serial=1L;
	
	public static void main(String[] args){
		
//		使用clone进行复制
		Node nodeRoot = new Node(1);
		Node nodeChild = new Node(2);
		Node nodeTempRoot = new Node(4);
		nodeRoot.setChild(nodeChild);
		nodeChild.setParent(nodeRoot);
		Node temp = new Node(3);
		temp.setParent(nodeTempRoot);
		//System.out.println(temp.parent.getVal());
		temp = nodeChild.clone();
		temp.setVal(10);
		temp.parent.setVal(18);
		System.out.println(nodeChild.parent.getVal());
		
//		使用serialization进行深度复制
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(nodeRoot);
			oos.flush();
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);
	
			Node serialNode =(Node)ois.readObject();
			//	nodeChild.setVal(10);
			System.out.println(serialNode.child.val);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @author zl
	 *方法一：使对象继承cloneable接口，使clone方法调用object的clone方法
	 *方法二：使对象继承Serializable接口，序列化再反序列化，从而生产新对象
	 */
	public static class Node implements Cloneable,Serializable{
		private Node parent,child;
		private int val =0;
		public  int getVal(){
			return val;
		};
		
		public void setVal(int value){
			val = value;
		}
			
		
		public Node(){};
		
		public Node(int val){
			this.val =val;
		}
		
		
		public void setParent(Node node ){
			this.parent = node;
		}
		
		public void setChild(Node node ){
			this.child = node;
		}
		
		@Override
		public Node clone(){
			Node node = null;
			try{
				node = (Node)super.clone();
				//node.parent =((Object)node.parent.getClass().getSuperclass());
				//node.child =(Node)node.child.clone();
			}catch(CloneNotSupportedException e){
				e.printStackTrace();
			}
			return node;
		}
	}
}
 


