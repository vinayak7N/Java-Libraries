package com.socket.learn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient2 {

	public static void main(String[] args) {

		System.out.println("Client started...");
		try {
			Socket socket = new Socket("localhost", 9086);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter a String to be sent over network...");
			String inputString = bufferedReader.readLine();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(inputString);
			
			//Receive data sent from server...
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String received = in.readLine();
			System.out.println(received);
			
			//socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
