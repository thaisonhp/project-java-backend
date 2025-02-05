package com.javaweb.utils;

public class StringUtil {
	public static boolean checkNullString(String str) {
		if(str != null && !str.equals("")) {
			return true ;
		}
		return false ;
	}
}
