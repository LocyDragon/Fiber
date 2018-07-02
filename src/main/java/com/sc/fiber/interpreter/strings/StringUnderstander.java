package com.sc.fiber.interpreter.strings;

public class StringUnderstander {
	private static final String annotate = "#";
	private static final String event = "@";
	public static boolean isAnnotate(String mine) {
		if (mine.startsWith(annotate)) {
			return true;
		}
		return false;
	}
	public static boolean isEvent(String mine) {
		if (mine.startsWith(event)) {
			return true;
		}
		return false;
	}
}
