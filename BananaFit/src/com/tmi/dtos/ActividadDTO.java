package com.tmi.dtos;

public class ActividadDTO extends DTO{
	
	private String nombre;
	
	/**
	 * En minutos
	 */
	private int duracion;
	
	private String descripcion;

	public ActividadDTO() { }
	
	public ActividadDTO(String nombre, int duracion, String descripcion) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.descripcion = descripcion;
	}

	public String geNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return duracion de la Clase en minutos
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion de la Clase en minutos
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
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
	    if (!(other instanceof ActividadDTO))return false;
	    ActividadDTO otherMyClass = (ActividadDTO)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
}
