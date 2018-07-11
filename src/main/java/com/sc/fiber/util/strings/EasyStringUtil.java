package com.sc.fiber.util.strings;


import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public static String private_Method_II(String AA,String BB, String CC) {
		if (!AA.contains(BB)) {
			return AA;
		}
		if (AA.contains(":")) {
			AA = AA.split(":")[0];
		}
		int location = AA.indexOf(BB);
		AA = AA.replace(BB, CC);
		List<Character> characterList = new ArrayList<>();
		int twice = 0;
		for (char c : AA.toCharArray()) {
			if (c == ' ') {
				if (twice == 0) {
					characterList.add('(');
					twice++;
					continue;
				} else if (twice == 1) {
					characterList.add(')');
					twice++;
					continue;
				} else {
					characterList.add(c);
					continue;
				}
			} else {
				characterList.add(c);
				continue;
			}
		}
		StringBuilder builder = new StringBuilder();
		for (Character c : characterList) {
			builder.append(c);
		}
		return builder.toString();
	}
	public static String private_Method_III(String AA,String BB, String to) {
		if (AA.contains(BB)) {
			AA = AA.replace(BB, to);
		}
		return AA;
	}
}
