package com.tmi.dtos;

import java.util.List;

public class ClaseDTO extends DTO{

	/**
	 * Cantidad de minutos desde la 00hs
	 */
	private int minutoInicio;
	
	private String profesor;
	
	private int dia;

	private ActividadDTO actividad;
	
	private List<String> inscriptos;
	
	private SalaDTO sala;
	
	public ClaseDTO () { }
	
	public ClaseDTO(int minutoInicio, String profesor, int dia, ActividadDTO actividad, SalaDTO sala) {
		super();
		this.minutoInicio = minutoInicio;
		this.profesor = profesor;
		this.dia = dia;
		this.actividad = actividad;
		this.sala=sala;
	}	
	
	public ActividadDTO getActividad() {
		return actividad;
	}

	public void setActividad(ActividadDTO actividad) {
		this.actividad = actividad;
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

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public List<String> getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(List<String> inscriptos) {
		this.inscriptos = inscriptos;
	}

	public SalaDTO getSala() {
		return sala;
	}

	public void setSala(SalaDTO sala) {
		this.sala = sala;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof ClaseDTO))return false;
	    ClaseDTO otherMyClass = (ClaseDTO)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
}
