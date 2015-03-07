package com.start.util;

import java.util.HashMap;
import java.util.Map;

public class AdapterUtil {
	public static Map<String, Object> createMenuItem(String[]keys, Object[]values){
		Map<String, Object> menuItem = new HashMap<String, Object>();
		int i = 0;
		for(String key : keys){
			menuItem.put(key, values[i++]);
		}
		return menuItem;
	}
}
