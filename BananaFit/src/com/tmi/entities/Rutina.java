package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Rutina {

	private Integer id;
	
	private String nombre;
	
	private String descripcion;
	
	private Persona creador;
	
	private int series;
	
	private int duracionEjercicio;
	
	private List<Ejercicio> ejercicios;
	
	private List<Persona> personas;

	public Rutina() { }
	
	public Rutina(String nombre, String descripcion, Persona creador, int series, int duracionEjercicio) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.creador = creador;
		this.series = series;
		this.duracionEjercicio = duracionEjercicio;
		this.ejercicios = new ArrayList<>();
		this.personas = new ArrayList<>();
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

	public Persona getCreador() {
		return creador;
	}

	public void setCreador(Persona creador) {
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

	public List<Ejercicio> getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(List<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Rutina))return false;
	    Rutina otherMyClass = (Rutina)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
	public void agregarEjercicio (Ejercicio ejercicio) {
		ejercicios.add(ejercicio);
	}
	
	public void removerEjercicio (Ejercicio ejercicio) {
		ejercicios.remove(ejercicio);
	}
}
