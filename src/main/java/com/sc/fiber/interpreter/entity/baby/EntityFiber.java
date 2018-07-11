package com.sc.fiber.interpreter.entity.baby;

import com.sc.fiber.util.strings.EasyStringUtil;

public class EntityFiber {
	public static String monitorString(String obj) {
		obj = EasyStringUtil.private_Method_II(obj, "增加骑乘者", ".addPassenger()");
		obj = EasyStringUtil.private_Method_II(obj, "增加标签", ".addScoreboardTag()");
		obj = EasyStringUtil.private_Method_I(obj, "弹出骑乘者", ".eject()");
		obj = EasyStringUtil.private_Method_I(obj, "实体编号", "getEntityId()");
		obj = EasyStringUtil.private_Method_I(obj, "掉落距离", "getFallDistance()");
		obj = EasyStringUtil.private_Method_I(obj, "着火时间", "getFireTicks()");
		obj = EasyStringUtil.private_Method_I(obj ,"身高", "getHeight()");
		//lose one method
		return obj;
	}
}
