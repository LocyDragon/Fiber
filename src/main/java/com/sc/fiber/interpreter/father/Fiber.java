package com.sc.fiber.interpreter.father;

public interface Fiber {
	static String monitorMe(String needMonitor){
		throw new RuntimeException("You are not able to access this method!");
	}
}
