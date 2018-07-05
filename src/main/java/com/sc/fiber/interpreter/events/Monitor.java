package com.sc.fiber.interpreter.events;

import com.sc.fiber.interpreter.Part;

public interface Monitor {
	public Part monitorMe(Part before, String fileName);
}
