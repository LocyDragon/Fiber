package com.sc.fiber.util.strings;


import org.apache.commons.lang.StringUtils;

public class EasyStringUtil {
	public static boolean isBlank(String obj) {
		return StringUtils.isBlank(obj);
	}
	public static String private_Method_I(String obj, String another, String anotherTo) {
		if (obj.contains(another)) {
			if (obj.contains(":")) {
				String parts = obj.split(":")[0];
				String copy = parts;
				parts = parts.replace(another, anotherTo);
				obj = obj.replace(copy, parts);
			} else {
				obj = obj.replace(another, anotherTo);
			}
		}
		return obj;
	}
}
