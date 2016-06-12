package com.thoughtworks.mgttg.util;

import java.util.regex.Pattern;

/**
 * @author talipk
 *
 */
public class Constants {

	public static final Pattern P_CHARACTER_DEFINITION = Pattern.compile("^([a-z]+) is ([I|V|X|L|C|D|M])$");
	public static final Pattern P_MATERIAL_DEFINITION = Pattern.compile("((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$");
	public static final Pattern P_QUESTION = Pattern.compile("([h|H]ow\\s\\b(much|many Credits))\\s[a-zA-Z]*\\s([a-zA-Z\\s]{1,})");
	public static final Pattern P_QUESTION_WITH_MATERIAL = Pattern.compile("([h|H]ow\\s\\b(many Credits))\\s[a-zA-Z]*\\s([a-zA-Z\\s]{1,})");

}
