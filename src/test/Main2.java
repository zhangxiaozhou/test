package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

public class Main2 {

	public static void main(String[] args) throws RuntimeException {
		String s = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" + "<Insure>" + "	<TransRefGUID>0000000001</TransRefGUID>"
				+ "	<TransExeDate>2002-03-31</TransExeDate>" + "	<TransExeTime>13:52:30</TransExeTime>"
				+ "	<HOAppFormNumber>0001</HOAppFormNumber>" + "	<GrossPremAmtITD>001</GrossPremAmtITD>"
				+ "</Insure>";

		System.out.println(s.length());

		try {
			Socket socket = new Socket("localhost", 8888);
			OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();

			String content = "000251" + "0002" + "123456" + s;
			System.out.println(content.length());

			outputStream.write(content.getBytes());
			outputStream.flush(); 
			socket.shutdownOutput();
			
			int readLen;
		
			byte[] buf = new byte[1024];
			
			while( (readLen=inputStream.read(buf)) != -1) {
				System.out.println(readLen);
				System.out.println(new String(buf));
			}
			socket.shutdownInput();
			
			outputStream.close();
			inputStream.close();
			socket.close();
			
			System.out.println("app end");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
