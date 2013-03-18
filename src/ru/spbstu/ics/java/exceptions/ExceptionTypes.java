package ru.spbstu.ics.java.exceptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class ExceptionTypes{
	
	public static void main(String[] args) throws IOException {
		new RuntimeException("");
		try {
			throw new NumberFormatException("test1");
		} catch (NumberFormatException e) {
			// i have finally ^w^
		} finally {
			try {
				throw new OurException();
			} catch (OurException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void readFile() throws IOException {
		File file = new File("plans.txt");
		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
			fis.read();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				System.out.println("already closed");
			}
		}
	}
}
