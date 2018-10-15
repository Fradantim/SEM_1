package com.tmi.exceptions;

public class YaExisteElUsuarioException extends Exception{

	private static final long serialVersionUID = 1L;

	public YaExisteElUsuarioException() { }
	  
    public YaExisteElUsuarioException(String errorMessage) {
        super(errorMessage);
    }
}
