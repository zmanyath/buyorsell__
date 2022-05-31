package com.wethinkcode.buyorsell.exceptions;

public class UserException extends Exception{

    private static final long serialVersionUID = 1L;

	public UserException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String email) {
		return "User with "+ email +" not found!";
	}
	
	public static String UserAlreadyExists() {
		return "User with given email already exists";
	}
}
