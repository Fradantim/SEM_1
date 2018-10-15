package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tmi.dtos.RutinaDTO;

@Entity
@Table(name="RUTINA")
public class Rutina extends AbsEntity{

	@Column (name="NOMBRE", nullable=true)
	private String nombre;
	
	@Column (name="DESCRIPCION", nullable=true)
	private String descripcion;
	
	@Column (name="PUBLICA", nullable=true)
	private Boolean publica;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_CREADOR_ID")
	private Usuario creador;
	
	@Column (name="SERIES", nullable=true)
	private int series;
	
	@Column (name="DURACION_EJERCICIO", nullable=true)
	private int duracionEjercicio;
	
	@ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
    @JoinTable(name = "RUTINA_EJERCICIO",
    	joinColumns = @JoinColumn(name = "RUTINA_ID"),
    	inverseJoinColumns = @JoinColumn(name = "EJERCICIO_ID")
    	)
	private List<Ejercicio> ejercicios;
	
	@ManyToMany(mappedBy = "rutinasAsociadas", cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Usuario> usuarios;

	public Rutina() { }
	
	public Rutina(String nombre, String descripcion, Usuario creador, int series, int duracionEjercicio, Boolean publica) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.creador = creador;
		this.series = series;
		this.duracionEjercicio = duracionEjercicio;
		this.publica=publica;
		this.ejercicios = new ArrayList<>();
		this.usuarios = new ArrayList<>();
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> personas) {
		this.usuarios = personas;
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
	    if (!(other instanceof Rutina))return false;
	    Rutina otherMyClass = (Rutina)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
	public void agregarEjercicio (Ejercicio ejercicio) {
		if(!ejercicios.contains(ejercicio))
			ejercicios.add(ejercicio);
	}
	
	public void removerEjercicio (Ejercicio ejercicio) {
		ejercicios.remove(ejercicio);
	}
	
	public RutinaDTO toDTO() {
		RutinaDTO dto =new RutinaDTO(nombre, descripcion, creador.getUser(), series, duracionEjercicio, publica); 
		dto.setEjercicios(ejercicios.stream().map(Ejercicio::toDTO).collect(Collectors.toList()));
		return dto;
	}
}
