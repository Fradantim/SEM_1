package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="EJERCICIO")
public class Ejercicio extends AbsEntity{
	
	@Column (name="NOMBRE", unique=true)
	private String nombre;
	
	@Column (name="DESCRIPCION", nullable=true)
	private String descripcion;

	@Transient
	private List<Rutina> rutinas;
	
	public Ejercicio() { }
	
	public Ejercicio(String nombre, String descripcion) {
		super();
		this.setRutinas(new ArrayList<>());
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
