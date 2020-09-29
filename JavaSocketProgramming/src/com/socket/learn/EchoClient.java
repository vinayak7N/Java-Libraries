package com.socket.learn;
import java.io.IOException;
import java.net.Socket;

public class EchoClient {

	public static void main(String[] args) {

		System.out.println("Client started...");
		try {
			Socket socket = new Socket("localhost",9086);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
