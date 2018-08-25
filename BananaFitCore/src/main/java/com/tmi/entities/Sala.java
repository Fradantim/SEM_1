package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.tmi.exceptions.SeSuperponenClases;

@Entity
public class Sala {

	private Integer id;
	
	private String nombre;
	
	private int capacidad;
	
	private List<Sesion> sesiones;
	
	public Sala(String nombre, int capacidad) {
		super();
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.sesiones = new ArrayList<>();
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

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
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
	    if (!(other instanceof Sala))return false;
	    Sala otherMyClass = (Sala)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
	public boolean puedeAsignarseSesion (Sesion sesion) {
		for(Sesion clase: sesiones) {
			if(sesion.getDia()==clase.getDia()) {
				//Si empieza durante otra clase que ya da
				if(sesion.getMinutoInicio()>=clase.getMinutoInicio() && sesion.getMinutoInicio()<=clase.getMinutoFin())
					return false;
				//Si termina durante otra clase que ya da
				if(sesion.getMinutoFin()<=clase.getMinutoFin() && sesion.getMinutoFin()>=clase.getMinutoInicio())
					return false;
				//Si durante el lapso de la clase existe otra clase que ya da
				if(sesion.getMinutoInicio()<=clase.getMinutoInicio() && sesion.getMinutoFin()>=clase.getMinutoFin())	
					return false;				
			}
		}
		return true;
	}
	
	public void asignarSesion(Sesion sesion) throws SeSuperponenClases {
		if(!puedeAsignarseSesion(sesion)) {
			throw new SeSuperponenClases("La sesion que se desea asignar a la sala "+ id +" se superone a otra clase");
		}
		sesiones.add(sesion);
	}
}
