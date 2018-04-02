package com.jl.myproject.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;



public class ClientSocket {
	public static void main(String[] args){
		
		
		try {
			Socket socket =new Socket("192.168.160.130", 13456);
			System.out.println("client start.");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String line ="";
			while((line =br.readLine())!=null){
//				非常重要，客户端从键盘输入后在获取的字符串中是没有换行符的，所以如果
//				不加换行符，如果服务器是按行获取的话，服务器会一直等待输入换行符，陷入锁死
				bw.write(line+"\n");
//				bw.newLine();
				bw.flush();
				if(line.equals("end")){
					break;
				}
			}
			bw.close();
			br.close();
			socket.close();
			System.out.println("client close.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} 
	}	
}
