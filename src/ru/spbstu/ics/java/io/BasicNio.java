package ru.spbstu.ics.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;

public class BasicNio {
	public static void main(String[] args) {
		try {
			FileChannel fch = new FileInputStream("firstFile.txt").getChannel();
			ByteBuffer bb = ByteBuffer.allocate(8*1024);
			StringBuilder sb = new StringBuilder();
			while (fch.read(bb)!=-1) {
				bb.flip();
				System.out.println(new String(bb.array()));
				bb.clear();
				bb.flip();
			}
			fch.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
