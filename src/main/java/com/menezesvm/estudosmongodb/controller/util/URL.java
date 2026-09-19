package com.menezesvm.estudosmongodb.controller.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	public static String decodeParam(String text) throws UnsupportedEncodingException {
		return URLDecoder.decode(text, StandardCharsets.UTF_8);
	}
	public static Date convertDate(String textDate, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMt"));
		try {
			return sdf.parse(textDate);
		} catch (Exception e) {
			return defaultValue;
		}
	}
}
