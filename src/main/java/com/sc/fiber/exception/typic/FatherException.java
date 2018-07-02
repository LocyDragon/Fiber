package com.sc.fiber.exception.typic;

/**
 * @author LocyDragon
 */
public class FatherException {
	public String reason;
	public String scriptName;
	public int line;
	public FatherException(String reason, String scriptName, int line) {
		this.reason = reason;
		this.scriptName = scriptName;
		this.line = line;
	}
}
