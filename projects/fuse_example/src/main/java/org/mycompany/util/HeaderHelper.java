package org.mycompany.util;

import java.util.Map;

import org.apache.camel.Exchange;

public class HeaderHelper {

	public static Map<String, Object> addHttpResponseCodeToHeaders(Map<String, Object> headers, int code) {
		headers.put(Exchange.HTTP_RESPONSE_CODE, code);
		return headers;
	}
	
}
