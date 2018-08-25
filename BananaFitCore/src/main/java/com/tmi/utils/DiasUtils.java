package com.tmi.utils;

public class DiasUtils {
	
	public static enum Dia {
		LUNES (1,"Lunes"),
		MARTES (2,"Martes"),
		MIERCOES (3,"Miercoles"),
		JUEVES (4,"Jueves"),
		VIERNES (5,"Viernes"),
		SABADO (6,"Sabado"),
		DOMINGO (7, "Domingo");
		
	    private final int orden;
	    private final String nombre;
	    
	    Dia(int orden, String nombre) {
	        this.orden = orden;
	        this.nombre = nombre;
	    }
	    
	    public int getOrden() { return orden; }
	    
	    public String getNombre() { return nombre; }
	}
}
