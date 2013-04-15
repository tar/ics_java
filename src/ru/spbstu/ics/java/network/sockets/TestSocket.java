package ru.spbstu.ics.java.network.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TestSocket {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 10000);
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println("Hello");	
			pw.flush();
			Scanner scanner = new Scanner(socket.getInputStream());
			while(scanner.hasNextLine()){
				System.out.println(scanner.next());
			}
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
