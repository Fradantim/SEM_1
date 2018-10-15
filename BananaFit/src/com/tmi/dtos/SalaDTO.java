package com.tmi.dtos;

public class SalaDTO extends DTO{

	public SalaDTO() {	}
	
	private String nombre;
	
	private int capacidad;
	
	public SalaDTO(String nombre, int capacidad) {
		super();
		this.nombre = nombre;
		this.capacidad = capacidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof SalaDTO))return false;
	    SalaDTO otherMyClass = (SalaDTO)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
}
