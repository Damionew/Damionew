package com.common.exception;

public class TestException extends RuntimeException{
	
	public TestException(String message) {
		System.out.println(message);
	}
}
