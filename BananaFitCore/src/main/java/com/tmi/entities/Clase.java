package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Clase {
	
	private Integer id;
	
	private String nombre;
	
	/**
	 * En minutos
	 */
	private int duracion;
	
	private String descripcion;

	private List<Sesion> sesiones;
	
	public Clase() { }
	
	public Clase(String nombre, int duracion, String descripcion) {
		super();
		this.sesiones= new ArrayList<>();
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

	public List<Sesion> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Clase))return false;
	    Clase otherMyClass = (Clase)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
	
}
