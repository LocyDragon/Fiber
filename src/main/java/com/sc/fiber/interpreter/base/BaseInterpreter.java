package com.sc.fiber.interpreter.base;

public class BaseInterpreter {
	public static String interThis(String type) {
		if (type.contains(":")) {
			return type;
		}
		String clone = IfInterpreter.inter(type);
		clone = LoopInterpreter.inter(clone);
		return clone;
	}
}
