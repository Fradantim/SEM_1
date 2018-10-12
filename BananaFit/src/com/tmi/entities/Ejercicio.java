package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Ejercicio {
	
	private Integer id;
	
	private String nombre;
	
	private String descripcion;

	private List<Rutina> rutinas;
	
	public Ejercicio() { }
	
	public Ejercicio(String nombre, String descripcion) {
		super();
		this.setRutinas(new ArrayList<>());
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Rutina> getRutinas() {
		return rutinas;
	}

	public void setRutinas(List<Rutina> rutinas) {
		this.rutinas = rutinas;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Ejercicio))return false;
	    Ejercicio otherMyClass = (Ejercicio)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
}
