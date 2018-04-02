//InputOutput java IO流详解及演示
//FileReader,FileWrite用于文件（字符流），InputStream、outputStream用于媒体文件（字节流）
package com.jl.myproject.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.Charset;

public class InputOutput {
	public static void main(String...args){
		String fileName = "C:\\Users\\Administrator\\Desktop\\test1.txt";
		
//		字符串文件
		
		//IOOpertion.writefile(fileName);
		//IOOpertion.readFile(fileName);
		//		IOOpertion.BufferWriter(fileName);
//		IOOpertion.bufferRead(fileName);
		//IOOpertion.myInputStream(fileName);
		
//		媒体文件
		
//		String srcFilePath ="C:\\Users\\Administrator\\Desktop\\年会活动资料\\项目部-明天会更好(伴奏) .mp3";
//		String desFilePath ="C:\\Users\\Administrator\\Desktop\\项目部-明天会更好(伴奏) .mp3";
//		IOOpertion.copyMediaFile(srcFilePath,desFilePath);
//		IOOpertion.bufferCopyMediaFile(srcFilePath, desFilePath);
		
//		流操作，从键盘读取，从控制台输出
		//IOOpertion.InOutDemo();
		
//		指定编码格式
		IOOpertion.setEncode();
	}
}

class IOOpertion{
	/**文件写入，不使用缓冲区
	 * @param fileName
	 */
	public static void writefile(String fileName){
		try{
			//			//如果没有找到文件会新建   
			//			加布尔参数，表示是在文件文本后添加，而不是覆盖
			//			FileWriter fw = new FileWriter(fileName,true);
			FileWriter fw = new FileWriter(fileName);
			fw.write("aaa");
			fw.write("ccc");
			fw.flush();
			fw.close();


		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**文件写入，使用缓冲区
	 * @param fileName
	 */
	public static void BufferWriter(String fileName){
		long start = System.currentTimeMillis();
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter br = new BufferedWriter(fw);

			for(int i =0;i<5900000;i++){
				//			br.write("1");
				fw.write("1");
			}
			fw.flush();
			fw.close();
			//			br.flush();
			//			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}

	/**读取文件,不使用缓冲区
	 * @param fileName
	 */
	public static void readFile(String fileName){
		try {
			FileReader fr = new FileReader(fileName);

			//int [] buf = new int[10];
			char [] buf = new char[10];
			int num =0;
			int index =0;
			//			fr.read()方法读取一个字符，并返回这个字符,比如读取一个字符a，则返回a的ascii，就是 97
			//			fr.read(char[] buf)方法将流输出到buf，直到buf填满，并返回字符串长度
			while((num= fr.read())!=-1){
				buf[index++]=(char)num;
				//				buf[index++]=num;
				//System.out.println(new String(buf,0,num));
			}
			System.out.println(buf);
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**读取文件,使用缓冲区
	 * @param fileName
	 */
	public static void bufferRead(String fileName){
		long start = System.currentTimeMillis();
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);

			char[] buf = new char[5900000];
			int num =0;
			int index =0;

			//		while((num= br.readLine()!=-1){
			//			buf[index++]=(char)num;
			//			buf[index++]=num;
			//System.out.println(new String(buf,0,num));
			//System.out.println("aaa");
			//		}
			String s=null;
			int i =0;
			do{
				s =br.readLine();

				if(s==null)break;
				System.out.println(s);
			}while(1==1);


			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		//	System.out.println(end-start);
	}
	
	/**使用 inputStream 对文件进行读取，读取字节流
	 * @param fileName
	 */
	public static void myInputStream(String fileName){
		try {
			FileInputStream fis = new FileInputStream(fileName);
			byte[] buf =new byte[10];
//			buf[0]=1000;
			int num=0;
			//read(buf)会将buf一次性塞满，如果满了后，
//			就算这一次的读取结束，返回的num就是buf的长度
			while((num=fis.read(buf))!=-1){
				System.out.println("a");
			}
			System.out.println(buf);
			fis.close();
		} catch (  IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	public static void copyMediaFile(String srcFilePath,String desFilePath){
		long start = System.currentTimeMillis();
		try {
			FileInputStream fis = new FileInputStream(srcFilePath);
			FileOutputStream fos = new FileOutputStream(desFilePath);
			byte[] copy = new byte[1024];
			int len =0;
			while((len=fis.read(copy))!=-1){
				fos.write(copy,0,len);
			}
			fos.flush();
			fos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
/**使用缓冲区对媒体文件进行字节读取
 * @param srcFilePath
 * @param desFilePath
 */
public static void bufferCopyMediaFile(String srcFilePath,String desFilePath){
	long start = System.currentTimeMillis();
		try {
			FileInputStream fis = new FileInputStream(srcFilePath);
			FileOutputStream fos = new FileOutputStream(desFilePath);
			BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedOutputStream bos =new BufferedOutputStream(fos);
			byte[] copy = new byte[1024];
			int len =0;
			while((len=bis.read(copy))!=-1){
				bos.write(copy,0,len);
			}
			bos.close();
			bis.close();
			
			fos.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}

	/**从键盘输入，然后打印到控制台
	 * System.in 是标准输入 从键盘获取，System.out是标准输出，从控制台输出
	 * System.in 和System.out都是字节流，
	 * 需要使用InputStreamReader和 OutputStreamWriter转换
	 */
	public static void InOutDemo(){
		
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\test1.txt",true));
			while((line = br.readLine())!=null){
				if(!line.equals("over")){
					bw.write(line.toUpperCase());
					bw.flush();
				}
				else{
					break;
				}
			}
			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
/**在文件转换过程中指定编码格式
 * 
 * 
 */
public static void setEncode(){
		
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			txt 支持ANSI Unicode UTF-8
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test1.txt",false),Charset.forName("Unicode")));
			while((line = br.readLine())!=null){
				if(!line.equals("over")){
					bw.write(line.toUpperCase());
					bw.flush();
				}
				else{
					break;
				}
			}
			br.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


