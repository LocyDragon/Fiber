package com.sc.fiber;

import com.sc.fiber.interpreter.ScriptFiber;
import com.sc.fiber.interpreter.receiver.EventReceiver;
import com.sc.fiber.io.ScriptReader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LocyDragon
 * Date 2018/7/1
 * Github https://github.com/LocyDragon/Fiber
 * Wiki https://fiber.gitbook.io/fiber/
 */
public class FiberMain extends JavaPlugin {
	public static List<ScriptFiber> scriptFiberList = new ArrayList<>();
	public static final String endsWithScriptFile = ".fib";
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new EventReceiver(), this);
		Thread asyncLoadScript = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.print("尝试加载Fiber的脚本.作者LocyDragon，出自SpicyChicken —— 辣鸡小组");
				File introduceFile = new File(".//plugins//Fiber//Fibers//HelpMe.txt");
				if (!introduceFile.exists()) {
					introduceFile.getParentFile().mkdirs();
					try {
						introduceFile.createNewFile();
					} catch (IOException exc) {
						exc.printStackTrace();
					}
				}
				try {
					FileWriter writer = new FileWriter(introduceFile);
					writer.write("你可以访问Mcbbs获取更多讯息与最新版本\n");
					writer.write("也可以访问wiki来获取如何使用插件 https://fiber.gitbook.io/fiber/ (打不开用科学上网工具)\n");
					writer.close();
				} catch (IOException exc) {
					exc.printStackTrace();
				}
				File location = new File(".//plugins//Fiber//Fibers");
				for (File scriptFile : location.listFiles()) {
					if (scriptFile.getName().trim().endsWith(endsWithScriptFile)) {
						ScriptReader reader = new ScriptReader(scriptFile);
						reader.loadMe();
						ScriptFiber fiber = reader.toFiber();
						fiber.load();
						scriptFiberList.add(fiber);
					}
				}
				System.out.print("脚本已经全部加载完成.");
			}
		});
		asyncLoadScript.start();
	}
}
