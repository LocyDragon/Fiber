package com.sc.fiber.interpreter;

import java.util.ArrayList;
import java.util.List;

public class ScriptFiber {
	private List<String> result = new ArrayList<>();
	private List<Part> codeParts = new ArrayList<>();
	public ScriptFiber(Object result) {
		this.result = (List<String>)result;
	}
	public void load() {
		int now = -1;
		int yet = -1;
		for (int i = 0;i < result.size();i++) {
			now = i;
			yet = now-1;
			String single = result.get(now);

		}
	}
}
