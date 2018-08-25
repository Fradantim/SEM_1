package com.tmi.exceptions;

public class SeSuperponenClases extends Exception{

	private static final long serialVersionUID = 1L;

	public SeSuperponenClases() {}
	
    public SeSuperponenClases(String errorMessage) {
        super(errorMessage);
    }
}
