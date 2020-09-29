package com.socket.learn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer3 {

	public static void main(String[] args) throws IOException {

		System.out.println("Waiting for clients...");
		ServerSocket serverSocket = new ServerSocket(9086);
		Socket socket = serverSocket.accept();
		System.out.println("Connectio established..." + socket);

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		int inputReceived = Integer.parseInt(bufferedReader.readLine());
		System.out.println("Input received over socket is: " + inputReceived);

		int fact = 1;
		for (int i = inputReceived; i >= 1; i--) {
			fact *= i;
		}

		// Received String is sent back...
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("Server says factorial is: " + fact);

		// out.close();
		// serverSocket.close();
	}

}
