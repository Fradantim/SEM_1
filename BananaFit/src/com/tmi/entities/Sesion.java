package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.tmi.exceptions.LaSesionTieneCupoLleno;

@Entity
public class Sesion {

	private Integer id;
	
	/**
	 * Cantidad de minutos desde la 00hs
	 */
	private int minutoInicio;
	
	private Profesor docente;
	
	private int dia;

	private Clase clase;
	
	private List<Persona> inscriptos;
	
	private Sala sala;
	
	public Sesion () { }
	
	public Sesion(int minutoInicio, Profesor docente, int dia, Clase clase, Sala sala) {
		super();
		this.inscriptos= new ArrayList<>();
		this.minutoInicio = minutoInicio;
		this.docente = docente;
		this.dia = dia;
		this.clase = clase;
		this.setSala(sala);
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

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public List<Persona> getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(List<Persona> inscriptos) {
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
	    if (!(other instanceof Sesion))return false;
	    Sesion otherMyClass = (Sesion)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
	public void inscribirPersona(Persona persona) throws LaSesionTieneCupoLleno {
		if (inscriptos.size()==sala.getCapacidad()) {
			throw new LaSesionTieneCupoLleno("La Sesion "+ id + " de la Clase "+ clase.getId()+" ya posee el cupo completo.");
		} else {
			inscriptos.add(persona);
		}
	}
	
	public void removerPersona(Persona persona) {
		inscriptos.remove(persona);
	}
	
	/**
	 * @return suma de minutoInicio + duracion
	 */
	public int getMinutoFin() {
		return minutoInicio+clase.getDuracion();
	}
}
