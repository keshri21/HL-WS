package com.hlws.exceptions;

public class DALInitiationException extends Exception {

	private static final long serialVersionUID = 5162710183389022123L;
	
	private static final String MSG = "DAL initiation exception: company id is mandatory to interact with DB";
	public String message = MSG; //default
	
	public DALInitiationException() {
		this(MSG);
	}
	
	public DALInitiationException(String message) {
		super(message);
	}
	
	
	
}
