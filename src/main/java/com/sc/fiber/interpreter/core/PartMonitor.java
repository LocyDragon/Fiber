package com.sc.fiber.interpreter.core;

import com.sc.fiber.interpreter.Part;
import com.sc.fiber.interpreter.events.player.PlayerLoginEventMonitor;

import java.util.ArrayList;
import java.util.List;

public class PartMonitor {
	public static List<Part> monitorMe(List<Part> monitorList, String fileName) {
		List<Part> listParts = new ArrayList<>();
		for (Part eachPart : monitorList) {
			if (eachPart.getHead().trim().equalsIgnoreCase("当玩家登陆") || eachPart.getHead().trim().equalsIgnoreCase("玩家登陆")
					|| eachPart.getHead().trim().equalsIgnoreCase("当玩家进入服务器") ||
					eachPart.getHead().trim().equalsIgnoreCase("玩家进入服务器")) {
				listParts.add(new PlayerLoginEventMonitor().monitorMe(eachPart, fileName));
			}
		}
		return listParts;
	}
}
