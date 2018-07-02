package com.sc.fiber.ScriptFiber;

public class A1 {
	private static A1 ourInstance = new A1();

	public static A1 getInstance() {
		return ourInstance;
	}

	private A1() {
	}
}
