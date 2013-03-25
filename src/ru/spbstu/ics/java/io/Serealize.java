package ru.spbstu.ics.java.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serealize {

	public void test() {
		C c = new C();
		c._cat = "Рыжик";
		c._kitty = 3;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(c);
			oos.flush();
			oos.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(
					baos.toByteArray());
			ObjectInputStream ios = new ObjectInputStream(bais);
			C c1 = (C) ios.readObject();
			ios.close();
			System.out.println(c1._cat);
			System.out.println(c1._kitty);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Serealize().test();
	}
}
