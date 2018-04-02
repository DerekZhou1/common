package com.jl.myproject.common;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.SocketSecurityException;
import java.util.Locale;





/**socket服务端，开启后可将socket接收的数据输出到前台，接收end时，关闭socket
 * @author zl
 *
 */
public class WebServer {
	public static void main(String[] args) {
//		//创建一个socketserver
//		MyServerSocket serverSocket = new MyServerSocket(); //socket
//		serverSocket.one
		//这里是设置java虚拟机的locale
		
		try {
			ServerSocket server = new ServerSocket(13456);
			System.out.println("server started");
			Socket socket= server.accept();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			String line="";
			while((line=br.readLine())!=null){
				bw.write(line+"\n");
				bw.flush();
				if(line.equals("end")){
					break;
				}
			}
			bw.close();
			br.close();
			socket.close();
			server.close();
			System.out.println("socket close.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	
}


