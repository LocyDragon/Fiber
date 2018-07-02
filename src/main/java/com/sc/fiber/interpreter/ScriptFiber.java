package com.sc.fiber.interpreter;

import com.sc.fiber.exception.ExceptionThrower;
import com.sc.fiber.exception.typic.WhenTimeEnum;
import com.sc.fiber.exception.typic.onload.MeetTailException;
import com.sc.fiber.interpreter.strings.StringUnderstander;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LocyDragon
 */
public class ScriptFiber {
	private static final String end = "$";

	private File scriptLocation = null;

	private List<String> result = new ArrayList<>();
	private List<Part> codeParts = new ArrayList<>();

	private Part nowPart = null;
	private List<String> io = new ArrayList<>();
	private String ioHead = null;
	private boolean ioLock = false;

	public ScriptFiber(Object result, Object result2) {
		this.result = (List<String>)result;
		this.scriptLocation = (File)result2;
	}
	public void load() {
		int now = -1;
		int yet = -1;
		for (int i = 0;i < result.size();i++) {
			now = i;
			yet = now-1;
			String single = result.get(now);
			if (StringUnderstander.isAnnotate(single)) {
				continue;
			} else if (StringUnderstander.isEvent(single)) {
				if (ioLock) {
					ExceptionThrower.throwFiberException(new MeetTailException("上一个事件没有写结尾符号 '$' 就开始了" +
							"下一个事件，这是不被允许的，请在该行的上面一行(单独一行)补上符号 '$'", scriptLocation.getName(), i)
					, WhenTimeEnum.ON_LOAD);
					return;
				}
				this.ioHead = single.substring(1);
				this.ioLock = true;
				continue;
			} else if (ioLock) {
				this.io.add(single);
			} else if (single.equals(end)) {
				this.ioLock = false;
				this.nowPart = new Part(this.ioHead, this.io);
				this.codeParts.add(this.nowPart);
				this.nowPart = null;
				this.io.clear();
				this.ioHead = null;
			}
		}
	}
}
