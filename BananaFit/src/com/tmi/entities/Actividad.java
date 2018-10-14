package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Actividad {
	
	private Integer id;
	
	private String nombre;
	
	/**
	 * En minutos
	 */
	private int duracion;
	
	private String descripcion;

	private List<Clase> clases;

	public Actividad() { }
	
	public Actividad(String nombre, int duracion, String descripcion) {
		super();
		this.clases= new ArrayList<>();
		this.nombre = nombre;
		this.duracion = duracion;
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

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Actividad))return false;
	    Actividad otherMyClass = (Actividad)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
}
