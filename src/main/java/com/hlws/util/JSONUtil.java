package com.hlws.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JSONUtil {

	public static <T> String toJson(T type) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			
			return ow.writeValueAsString(type);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
