package com.sc.fiber.io.core;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class PerLineReader {
	private File targetFile;
	private List<String> readList = new ArrayList<>();
	public PerLineReader(File targetFile) {
		this.targetFile = targetFile;
	}
	public boolean read() {
		readList.clear();
		try {
			FileReader reader = new FileReader(this.targetFile);
			LineNumberReader lineReader = new LineNumberReader(reader);
			String eachLine = lineReader.readLine();
			while (eachLine != null) {
				readList.add(eachLine);
				eachLine = lineReader.readLine();
			}
			lineReader.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return true;
		}
	}
	public List<String> getReadContent() {
		return this.readList;
	}
}
