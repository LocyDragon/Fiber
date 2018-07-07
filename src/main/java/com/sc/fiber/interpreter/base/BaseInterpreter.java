package com.sc.fiber.interpreter.base;

public class BaseInterpreter {
	public static String interThis(String type) {
		String clone = IfInterpreter.inter(type);
		return clone;
	}
}
