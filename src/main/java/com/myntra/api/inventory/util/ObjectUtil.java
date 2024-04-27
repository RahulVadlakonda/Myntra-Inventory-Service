package com.myntra.api.inventory.util;

import org.apache.commons.lang3.StringUtils;

import jakarta.persistence.Tuple;

public final class ObjectUtil {

	public static boolean isLongPositive(String value) {
		return StringUtils.isNumeric(value)
				&& Long.parseLong(value) > 0;
	}
	
	public static Long getLongValue(Tuple tuple, String columnName) {
		if (null != tuple.get(columnName)) {
			return Long.parseLong(tuple.get(columnName).toString());
		}
		return null;
	}
	
	public static String getStringValue(Tuple tuple, String columnName) {
		if (null != tuple.get(columnName)) {
			return tuple.get(columnName).toString();
		}
		return null;
	}
}
