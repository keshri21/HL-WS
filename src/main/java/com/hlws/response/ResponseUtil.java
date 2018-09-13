package com.hlws.response;

public class ResponseUtil {
	
	public static <T> APIResponse<T> createSuccessResponse(String message, T data) {
		APIResponse<T> response = new APIResponse<T>();
		response.setSuccess(true);
		response.setMessage(message);
		response.setData(data);
		return response;
	}
	
	public static <T> APIResponse<T> createFailedResponse(String message, T data) {
		APIResponse<T> response = new APIResponse<T>();
		response.setSuccess(false);
		response.setMessage(message);
		response.setData(data);
		return response;
	}

}
