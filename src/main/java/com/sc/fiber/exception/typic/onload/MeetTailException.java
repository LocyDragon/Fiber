package com.sc.fiber.exception.typic.onload;

import com.sc.fiber.exception.typic.FatherException;

public class MeetTailException extends FatherException {
	public MeetTailException(String reason, String scriptName, int line) {
		super(reason, scriptName, line);
	}
}
