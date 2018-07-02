package com.sc.fiber.io;

import com.sc.fiber.interpreter.ScriptFiber;
import com.sc.fiber.io.core.PerLineReader;
import com.sc.fiber.io.exception.NotScriptError;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScriptReader {
	private static final String axx = ".fib";
	private File file;
	private List<String> content = new ArrayList<>();
	private boolean loaded = false;
	public ScriptReader(File file) {
		if (!file.getName().endsWith(axx)) {
			throw new NotScriptError("A script file must be end with .fib !!");
		}
		this.file = file;
		this.loaded = false;
	}
    public boolean loadMe() {
		PerLineReader reader = new PerLineReader(this.file);
		reader.read();
		this.content = reader.getReadContent();
		return true;
	}
	public ScriptFiber toFiber() {
		return new ScriptFiber(this.content, this.file);
	}
}
