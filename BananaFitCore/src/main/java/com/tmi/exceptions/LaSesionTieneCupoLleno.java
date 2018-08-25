package com.tmi.exceptions;

public class LaSesionTieneCupoLleno extends Exception{

	private static final long serialVersionUID = 1L;

	public LaSesionTieneCupoLleno() { }
	  
    public LaSesionTieneCupoLleno(String errorMessage) {
        super(errorMessage);
    }
}
