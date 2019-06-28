package com.yatzy.kata.refactoring;

public class InvalidDicesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDicesException() {
		super();
	}
	
	public InvalidDicesException(String message) {
		super(message);
	}
}
