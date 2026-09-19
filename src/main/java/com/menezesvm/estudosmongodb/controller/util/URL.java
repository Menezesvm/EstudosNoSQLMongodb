package com.menezesvm.estudosmongodb.controller.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class URL {

	public static String decodeParam(String text) throws UnsupportedEncodingException {
		return URLDecoder.decode(text, StandardCharsets.UTF_8);
	}
}
