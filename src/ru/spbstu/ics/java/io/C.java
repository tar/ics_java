package ru.spbstu.ics.java.io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public class C implements Externalizable {
	
	int _kitty;
	String _cat;
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(_kitty);
		out.writeUTF(_cat);
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		_kitty = in.readInt();
		_cat = in.readUTF();
	}
}
