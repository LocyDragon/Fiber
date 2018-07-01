package com.sc.fiber.io;

import com.sc.fiber.io.exception.NotScriptError;

import java.io.File;

public class ScriptReader {
	private static final String axx = ".fib";
	private File file;
	public ScriptReader(File file) {
		if (!file.getName().endsWith(axx)) {
			throw new NotScriptError("A script file must be end with .fib !!");
		}
		this.file = file;
	}

}
