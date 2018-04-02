package com.jl.myproject.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;

public class Serialization {
	public static void main(String...args){
		File file = new File("C:\\Users\\Administrator\\Desktop\\test2.txt");
		SerialExp ex = new SerialExp();
		ex.a =10;
//		序列化为文件
//		serialMethod(ex, fileName);
//		SerialExp ex1 = (SerialExp)deSerial(file);
//		System.out.println(ex1.a);
		
//		序列化为字符串
		String depStr = serialMethod(ex);
		SerialExp ex2 = (SerialExp)deSerial(depStr);
		System.out.println(ex2.a);
	}
	
	/**序列化为文件
	 * @param obj
	 * @param fileName 文件名
	 */
	public static void serialMethod(Object obj,String fileName){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(obj);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**序列化为字符串
	 * @param obj
	 * @param ByteOutputStream
	 */
	public static String serialMethod(Object obj){
		String str=null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			Charset charset =Charset.defaultCharset();
			str = bos.toString("GBK");
			oos.flush();
			oos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	/**将文件反序列化为对象
	 * @param fileName
	 * @return
	 */
	public static Object deSerial(File file){
		Object obj=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			obj =ois.readObject();
			ois.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	/**将字符串反序列化为对象
	 * @param String
	 * @return
	 */
	public static Object deSerial(String depStr){
		Object obj=null;
		
		
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(depStr.getBytes("GBK"));

			ObjectInputStream ois = new ObjectInputStream(bis);
			obj =ois.readObject();
			ois.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}

class SerialExp  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int a;
	int b;
	
}


