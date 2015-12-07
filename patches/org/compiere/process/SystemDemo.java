package org.compiere.process;

public class SystemDemo {
	public native void displayHelloWorld();

	static {
		System.loadLibrary("hello");
	}
}
