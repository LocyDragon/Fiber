package com.sc.fiber.interpreter;

import java.util.List;

public class Part {
	private String head = null;
	private List<String> body = null;
    public String des = "";
    public String methodName = "";
	public Part(String head, List<String> body) {
		this.head = head;
		this.body = body;
	}

	public String getHead() {
		return this.head;
	}

	public List<String> getBody() {
		return this.body;
	}

	public void setBody(List<String> body) {
		this.body = body;
	}

	public void setHead(String head) {
		this.head = head;
	}
}
