package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
public class Rutina extends AbsEntity{

	@Column (name="NOMBRE", unique=true)
	private String nombre;
	
	@Column (name="DESCRIPCION", nullable=true)
	private String descripcion;
	
	@Transient //TODO hacer mapeo
	private Usuario creador;
	
	@Column (name="SERIES", nullable=true)
	private int series;
	
	@Column (name="DURACION_EJERCICIO", nullable=true)
	private int duracionEjercicio;
	
	@ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
    @JoinTable(name = "EJERCICIO_RUTINA",
    	joinColumns = @JoinColumn(name = "RUTINA_ID"),
    	inverseJoinColumns = @JoinColumn(name = "EJERCICIO_ID")
    	)
	private List<Ejercicio> ejercicios;
	
	@Transient //TODO hacer mapeo
	private List<Usuario> personas;

	public Rutina() { }
	
	public Rutina(String nombre, String descripcion, Usuario creador, int series, int duracionEjercicio) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.creador = creador;
		this.series = series;
		this.duracionEjercicio = duracionEjercicio;
		this.ejercicios = new ArrayList<>();
		this.personas = new ArrayList<>();
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

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
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

	public List<Usuario> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Usuario> personas) {
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
