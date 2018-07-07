package com.sc.fiber.interpreter.base;

public class IfInterpreter {
	/**
	 * Not done yet(Now: 2018/7/7);
	 * @param in
	 * @return
	 */
	public static String inter(String in) {
		if (in.contains("!")) {
			in.replace("!", "}");
		} else if (in.startsWith("如果")) {
			in.replace("如果", "if (");
			in += ")";
		}
		return in;
	}
}
