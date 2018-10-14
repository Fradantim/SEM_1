package com.tmi.exceptions;

public class ObjetoInexistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public ObjetoInexistenteException() {
	}

	public ObjetoInexistenteException(String message) {
		super(message);
	}
}
