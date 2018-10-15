package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tmi.dtos.ClaseDTO;
import com.tmi.exceptions.DiaInexistenteException;
import com.tmi.exceptions.LaSesionTieneCupoLlenoException;

@Entity
@Table(name="CLASE")
public class Clase extends AbsEntity{

	public static enum Dia {
		LUNES (1,"Lunes"),
		MARTES (2,"Martes"),
		MIERCOES (3,"Miercoles"),
		JUEVES (4,"Jueves"),
		VIERNES (5,"Viernes"),
		SABADO (6,"Sabado"),
		DOMINGO (7, "Domingo");
		
	    private Integer id;
	    private String nombre;
	    
	    Dia(Integer id, String nombre) {
	        this.id = id;
	        this.nombre = nombre;
	    }
	    
	    public Integer getId() { return id; }
	    
	    public String getNombre() { return nombre; }
	    
	    public static Dia getById(Integer id) throws DiaInexistenteException {
			for(Dia dia: Dia.values())
				if(dia.getId().equals(id))
					return dia;
			throw new DiaInexistenteException("No existe un dia con id "+id);
		}
	}
	
	/**
	 * Cantidad de minutos desde la 00hs
	 */
	
	@Column (name="MINUTO_INICIO", nullable=true)
	private int minutoInicio;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROFESOR_ID")
	private Profesor profesor;
	
	@Column (name="DIA", nullable=true)
	private int dia;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ACTIVIDAD_ID")
	private Actividad actividad;
	
	@ManyToMany(mappedBy = "clases", cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Usuario> inscriptos;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SALA_ID")
	private Sala sala;
	
	public Clase () { }
	
	public Clase(int minutoInicio, Profesor profesor, int dia, Actividad actividad, Sala sala) {
		super();
		this.inscriptos= new ArrayList<>();
		this.minutoInicio = minutoInicio;
		this.profesor = profesor;
		this.dia = dia;
		this.actividad = actividad;
		this.sala=sala;
	}	
	
	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
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

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
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
	
	public void inscribirUsuario(Usuario usuario) throws LaSesionTieneCupoLlenoException {
		if (inscriptos.size()==sala.getCapacidad()) {
			throw new LaSesionTieneCupoLlenoException("La clase "+ id + " de la actividad"+ actividad.getId()+" ya posee el cupo completo.");
		} else {
			inscriptos.add(usuario);
		}
	}
	
	public void removerUsuario(Usuario usuario) {
		inscriptos.remove(usuario);
	}
	
	/**
	 * @return suma de minutoInicio + duracion
	 */
	public int getMinutoFin() {
		return minutoInicio+actividad.getDuracion();
	}
	
	public ClaseDTO toDTO() {
		ClaseDTO dto = new ClaseDTO(minutoInicio, profesor.getUser(), dia, actividad.toDTO(), sala.toDTO());
		dto.setInscriptos(inscriptos.stream().map(Usuario::getUser).collect(Collectors.toList()));
		return dto;
	}
}
