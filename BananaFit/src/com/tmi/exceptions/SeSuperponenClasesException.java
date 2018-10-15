package com.tmi.exceptions;

public class SeSuperponenClasesException extends Exception{

	private static final long serialVersionUID = 1L;

	public SeSuperponenClasesException() {}
	
    public SeSuperponenClasesException(String errorMessage) {
        super(errorMessage);
    }
}
