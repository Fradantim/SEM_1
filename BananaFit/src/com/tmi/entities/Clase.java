package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.tmi.exceptions.LaSesionTieneCupoLlenoException;

@Entity
public class Clase {

	private Integer id;
	
	/**
	 * Cantidad de minutos desde la 00hs
	 */
	private int minutoInicio;
	
	private Profesor docente;
	
	private int dia;

	private Actividad actividad;
	
	private List<Usuario> inscriptos;
	
	private Sala sala;
	
	public Clase () { }
	
	public Clase(int minutoInicio, Profesor docente, int dia, Actividad actividad, Sala sala) {
		super();
		this.inscriptos= new ArrayList<>();
		this.minutoInicio = minutoInicio;
		this.docente = docente;
		this.dia = dia;
		this.actividad = actividad;
		this.setSala(sala);
	}	
	
	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Cantidad de minutos desde la 00hs
	 */
	public int getMinutoInicio() {
		return minutoInicio;
	}

	/**
	 * @param minutoInicio Cantidad de minutos desde la 00hs
	 */
	public void setMinutoInicio(int minutoInicio) {
		this.minutoInicio = minutoInicio;
	}

	public Profesor getDocente() {
		return docente;
	}

	public void setDocente(Profesor docente) {
		this.docente = docente;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public List<Usuario> getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(List<Usuario> inscriptos) {
		this.inscriptos = inscriptos;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
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
	
	public void inscribirPersona(Usuario persona) throws LaSesionTieneCupoLlenoException {
		if (inscriptos.size()==sala.getCapacidad()) {
			throw new LaSesionTieneCupoLlenoException("La Sesion "+ id + " de la Clase "+ actividad.getId()+" ya posee el cupo completo.");
		} else {
			inscriptos.add(persona);
		}
	}
	
	public void removerPersona(Usuario persona) {
		inscriptos.remove(persona);
	}
	
	/**
	 * @return suma de minutoInicio + duracion
	 */
	public int getMinutoFin() {
		return minutoInicio+actividad.getDuracion();
	}
}
