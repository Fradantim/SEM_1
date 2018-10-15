package com.tmi.dtos;

import java.util.ArrayList;
import java.util.List;

public class RutinaDTO extends DTO{

	private String nombre;
	
	private String descripcion;
	
	private Boolean publica;
	
	private String creador;
	
	private int series;
	
	private int duracionEjercicio;
	
	private List<EjercicioDTO> ejercicios;
	
	public RutinaDTO() { }
	
	public RutinaDTO(String nombre, String descripcion, String creador, int series, int duracionEjercicio, Boolean publica) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.creador = creador;
		this.series = series;
		this.duracionEjercicio = duracionEjercicio;
		this.publica=publica;
		this.ejercicios = new ArrayList<>();
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

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getDuracionEjercicio() {
		return duracionEjercicio;
	}

	public void setDuracionEjercicio(int duracionEjercicio) {
		this.duracionEjercicio = duracionEjercicio;
	}

	public List<EjercicioDTO> getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(List<EjercicioDTO> ejercicios) {
		this.ejercicios = ejercicios;
	}

		
	public Boolean getPublica() {
		return publica;
	}

	public void setPublica(Boolean publica) {
		this.publica = publica;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof RutinaDTO))return false;
	    RutinaDTO otherMyClass = (RutinaDTO)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
}
