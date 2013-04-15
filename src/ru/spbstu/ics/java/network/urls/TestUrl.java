package ru.spbstu.ics.java.network.urls;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class TestUrl {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://ya.ru");
			Scanner scanner = new Scanner(url.openStream());
			while (scanner.hasNextLine()) {
				System.out.println(scanner.next());
			}
			scanner.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
