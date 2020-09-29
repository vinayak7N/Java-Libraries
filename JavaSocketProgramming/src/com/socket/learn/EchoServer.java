package com.socket.learn;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Waiting for clients...");
		ServerSocket serverSocket = new ServerSocket(9086);
		Socket socket = serverSocket.accept();
		System.out.println("Connectio established..."+socket);
		serverSocket.close();
	}

}
