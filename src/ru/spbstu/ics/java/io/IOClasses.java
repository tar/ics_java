package ru.spbstu.ics.java.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class IOClasses {
	public static void main(String[] args) {

		try {
			InputStream is = new FileInputStream("firstFile.txt");
			Scanner sc = new Scanner(is);
			while (sc.hasNext()) {
				System.out.println(sc.nextLine());
			}
			
			BufferedInputStream bis = new BufferedInputStream(is);
			
			RandomAccessFile randomFile;
			
			ByteArrayInputStream bais = new ByteArrayInputStream(new byte[4]);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			BufferedReader bur=new BufferedReader(new InputStreamReader(bis));
			
			PipedInputStream pis = new PipedInputStream();
			PipedOutputStream pos = new PipedOutputStream(pis);
			
			
		
		} catch (FileNotFoundException e) {
			System.err.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void printStream(InputStream is){
		//
	}
}
