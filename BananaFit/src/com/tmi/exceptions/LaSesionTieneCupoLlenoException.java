package com.tmi.exceptions;

public class LaSesionTieneCupoLlenoException extends Exception{

	private static final long serialVersionUID = 1L;

	public LaSesionTieneCupoLlenoException() { }
	  
    public LaSesionTieneCupoLlenoException(String errorMessage) {
        super(errorMessage);
    }
}
