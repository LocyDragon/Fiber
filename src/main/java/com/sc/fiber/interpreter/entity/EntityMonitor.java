package com.sc.fiber.interpreter.entity;

import com.sc.fiber.interpreter.entity.baby.EntityFiber;

public class EntityMonitor {
	public static String monitorMe(String monitor) {
		monitor = EntityFiber.monitorString(monitor);

		return monitor;
	}
}
