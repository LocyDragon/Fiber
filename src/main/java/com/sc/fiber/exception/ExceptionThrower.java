package com.sc.fiber.exception;

import com.sc.fiber.exception.typic.FatherException;
import com.sc.fiber.exception.typic.WhenTimeEnum;
import org.bukkit.Bukkit;

public class ExceptionThrower {
	public static void throwFiberException(FatherException exception, WhenTimeEnum whenTimeEnum) {
		Bukkit.getLogger().info("====================Fiber脚本报错信息=====================");
		Bukkit.getLogger().info("糟糕!Fiber插件在运行的时候某个脚本报出了异常!");
		Bukkit.getLogger().info("报错根源脚本名称: "+exception.scriptName);
		Bukkit.getLogger().info("该脚本在运行到第 "+exception.line+" 行\"左右\"时输出改报错.");
		Bukkit.getLogger().info("若行数是 -1 那么该报错行数是未知的.");
		switch (whenTimeEnum) {
			case ON_RUN:
				Bukkit.getLogger().info("报错时机: 当脚本正在运行时报错的.");
				break;
			case ON_LOAD:
				Bukkit.getLogger().info("报错时机: 当脚本被Fiber加载时报错的.");
				break;
			case ON_ENABLE:
				Bukkit.getLogger().info("报错时机: 当脚本被启动时报错的.");
				break;
			case ON_DESTORY:
				Bukkit.getLogger().info("报错时机: 当脚本被卸载时报错的.");
				break;
			default:
				Bukkit.getLogger().info("报错时机: 未知.");
				break;
		}
		Bukkit.getLogger().info("报错原因: "+exception.reason);
		Bukkit.getLogger().info("====================这一个报错信息到此结束=====================");
	}
}
