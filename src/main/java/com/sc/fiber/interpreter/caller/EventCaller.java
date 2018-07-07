package com.sc.fiber.interpreter.caller;


import com.sc.fiber.FiberMain;
import com.sc.fiber.interpreter.ScriptFiber;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerLoginEvent;

public class EventCaller {
	public static void callEvent(Event event) {
		for (ScriptFiber fiber : FiberMain.scriptFiberList) {
			if (event instanceof PlayerLoginEvent) {
				try {
					fiber.getNeedInvokeMethod("PlayerLoginEvent").invoke(fiber.getInstance(), event);
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		}
	}
}
