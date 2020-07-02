package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class Main {

	public static void main(String[] args) throws RuntimeException {
		String s = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" + 
				"<DesKeyNotifyRequest>" +  
				"	<TransRefGUID>0000000001</TransRefGUID>" +  
				"	<TransExeDate>2002-03-31</TransExeDate>" +  
				"	<TransExeTime>13:52:30</TransExeTime>" +  
				"	<TransNo>0001</TransNo>" +  
				"	<CarrierCode>001</CarrierCode>" +  
				"	<BankCode>0001</BankCode>" +  
				"	<DesKey></DesKey>" + 
				"</DesKeyNotifyRequest>";


		System.out.println(s.length());

		try {
			Socket socket = new Socket("localhost", 8888);
			OutputStream outputStream = socket.getOutputStream();
			String content = "000297" + "0001" + "123456" + s;
			System.out.println(content.length());

			outputStream.write(content.getBytes());
			
			InputStream inputStream = socket.getInputStream();
			int i = inputStream.available();

			byte[] bytes = new byte[i];
			inputStream.read(bytes);
			
			System.out.println(new String(bytes));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
