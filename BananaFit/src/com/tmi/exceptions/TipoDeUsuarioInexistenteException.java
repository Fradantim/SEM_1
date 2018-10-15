package com.tmi.exceptions;

public class TipoDeUsuarioInexistenteException extends Exception{

	private static final long serialVersionUID = 1L;

	public TipoDeUsuarioInexistenteException() { }
	  
    public TipoDeUsuarioInexistenteException(String errorMessage) {
        super(errorMessage);
    }
}
