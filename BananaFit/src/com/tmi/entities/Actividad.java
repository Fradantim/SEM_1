package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmi.dtos.ActividadDTO;

@Entity
@Table(name="ACTIVIDAD")
public class Actividad extends AbsEntity{
	
	@Column (name="NOMBRE", nullable=true)
	private String nombre;
	
	/**
	 * En minutos
	 */
	@Column (name="DURACION", nullable=true)
	private int duracion;
	
	@Column (name="DESCRIPCION", nullable=true)
	private String descripcion;

	@OneToMany(mappedBy = "actividad",fetch=FetchType.LAZY)
	private List<Clase> clases;

	public Actividad() { }
	
	public Actividad(String nombre, int duracion, String descripcion) {
		super();
		this.clases= new ArrayList<>();
		this.nombre = nombre;
		this.duracion = duracion;
		this.descripcion = descripcion;
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

	public ActividadDTO toDTO () {
		return new ActividadDTO(nombre, duracion, descripcion);
	}
}
