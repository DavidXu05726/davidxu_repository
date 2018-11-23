package com.isoftstone.sample.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

	/**
	 * validate email format
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		Pattern regex = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

}
