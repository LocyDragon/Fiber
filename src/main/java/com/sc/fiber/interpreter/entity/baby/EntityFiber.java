package com.sc.fiber.interpreter.entity.baby;

import com.sc.fiber.util.strings.EasyStringUtil;

public class EntityFiber {
	public static String monitorString(String obj) {
		obj = EasyStringUtil.private_Method_II(obj, "增加骑乘者", ".addPassenger");
		return obj;
	}
}
