package com.sc.fiber.interpreter.entity;

import com.sc.fiber.interpreter.entity.baby.EntityFiber;
import com.sc.fiber.interpreter.entity.entityutil.LocationFiber;
import com.sc.fiber.util.strings.EasyStringUtil;

public class EntityMonitor {
	public static String monitorMe(String monitor) {
		monitor = EntityFiber.monitorMe(monitor);
		monitor = LocationFiber.monitorMe(monitor);
		monitor = EasyStringUtil.private_Method_II(monitor, "等同于", ".equals");
		monitor = EasyStringUtil.private_Method_II(monitor, "相似于", ".equalsIgnoreCase");
		return monitor;
	}
}
