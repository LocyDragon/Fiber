package com.sc.fiber.interpreter.receiver;

import com.sc.fiber.interpreter.caller.EventCaller;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class EventReceiver implements Listener {
	@EventHandler
	public void onPlayerLoginToCall(PlayerLoginEvent e) {
		EventCaller.callEvent(e);
	}
}
