package ru.spbstu.ics.java.network.urls;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class TestUrlConnection {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://ya.ru");
			URLConnection connection = url.openConnection();
			URI uri = new URI("");
			uri.toURL();
			Scanner scanner = new Scanner(url.openStream());
			while (scanner.hasNextLine()) {
				System.out.println(scanner.next());
			}
			scanner.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
