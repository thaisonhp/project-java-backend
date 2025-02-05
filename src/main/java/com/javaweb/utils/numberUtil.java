package com.javaweb.utils;

public class numberUtil {
    public static boolean isNumber(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false; // Chuỗi null hoặc rỗng không phải là số
        }
        try {
            Long.parseLong(str.trim()); // Xóa khoảng trắng hai đầu trước khi parse
        } catch (NumberFormatException ex) {
            return false; // Không in stack trace trừ khi cần debug
        }
        return true;
    }
}

