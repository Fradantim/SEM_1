package com.tmi.dtos;

public class EjercicioDTO extends DTO{
	
	private String nombre;

	private String descripcion;

	public EjercicioDTO() { }
	
	public EjercicioDTO(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof EjercicioDTO))return false;
	    EjercicioDTO otherMyClass = (EjercicioDTO)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
}
