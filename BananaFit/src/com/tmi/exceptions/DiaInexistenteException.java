package com.tmi.exceptions;

public class DiaInexistenteException extends Exception{

	private static final long serialVersionUID = 1L;

	public DiaInexistenteException() { }
	  
    public DiaInexistenteException(String errorMessage) {
        super(errorMessage);
    }
}
