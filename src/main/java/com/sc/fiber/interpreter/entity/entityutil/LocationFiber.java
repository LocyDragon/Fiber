package com.sc.fiber.interpreter.entity.entityutil;

import com.sc.fiber.interpreter.father.Fiber;
import com.sc.fiber.util.strings.EasyStringUtil;

public class LocationFiber implements Fiber {
	public static String monitorMe(String monitor) {
		monitor = EasyStringUtil.private_Method_II(monitor, "到距离", ".distance");

		monitor = EasyStringUtil.private_Method_II(monitor, "设置世界", ".setWorld");
		monitor = EasyStringUtil.private_Method_II(monitor, "设置横轴", ".setX");
		monitor = EasyStringUtil.private_Method_II(monitor, "设置竖轴", ".setY");
		monitor = EasyStringUtil.private_Method_II(monitor, "设置纵轴", ".setZ");
		monitor = EasyStringUtil.private_Method_II(monitor, "设置yaw", ".setYaw");
		monitor = EasyStringUtil.private_Method_II(monitor, "设置pitch", ".setPitch");

		monitor = EasyStringUtil.private_Method_I(monitor, "方块", "getBlock()");
		monitor = EasyStringUtil.private_Method_I(monitor, "横轴", "getX()");
		monitor = EasyStringUtil.private_Method_I(monitor, "竖轴", "getY()");
		monitor = EasyStringUtil.private_Method_I(monitor, "纵轴", "getZ()");
		monitor = EasyStringUtil.private_Method_I(monitor, "yaw", "getYaw()");
		monitor = EasyStringUtil.private_Method_I(monitor, "pitch", "getPitch()");
		monitor = EasyStringUtil.private_Method_I(monitor, "长度", "length()");
		monitor = EasyStringUtil.private_Method_I(monitor, "所在世界", "getWorld()");
		return monitor;
	}
}
