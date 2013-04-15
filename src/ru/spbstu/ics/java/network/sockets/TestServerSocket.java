package ru.spbstu.ics.java.network.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestServerSocket {

	private static ExecutorService service = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(10000);
			while (true) {
				Socket socket = serverSocket.accept();
				service.execute(new Runnable() {
					
					@Override
					public void run() {
						
					}
				})
				Scanner scanner = new Scanner(socket.getInputStream());
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				while (scanner.hasNextLine()) {
					String line = scanner.next();
					System.out.println(line);
					pw.println(line);
					pw.flush();
				}
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
