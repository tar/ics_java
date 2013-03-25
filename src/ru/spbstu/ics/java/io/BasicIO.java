package ru.spbstu.ics.java.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class BasicIO {
	public static void main(String[] args) {

		File file = new File("firstFile.txt");

		try {
			OutputStream os = new FileOutputStream(file);
			os.write("Hello world".getBytes());
			os.flush();
			os.close();
			InputStream is = new FileInputStream(file);
			byte[] readedBytes = new byte[4];
			while (is.read(readedBytes)!=-1) {
				System.out.print(new String(readedBytes));
				ByteArrayInputStream bais = new ByteArrayInputStream(readedBytes);
			}
			if (file.isDirectory()) {
				for(File tempFile : file.listFiles()){
				}
			}
//			is.read(readedBytes);
//			System.out.println(readedBytes);
			is.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
