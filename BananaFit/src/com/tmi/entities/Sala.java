package com.tmi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmi.exceptions.SeSuperponenClasesException;

@Entity
@Table(name="SALA")
public class Sala extends AbsEntity{

	public Sala() {	}
	
	@Column (name="NOMBRE", nullable=true)
	private String nombre;
	
	@Column (name="CAPACIDAD", nullable=true)
	private int capacidad;
	
	@OneToMany(mappedBy = "sala",fetch=FetchType.LAZY)
	private List<Clase> clases;
	
	public Sala(String nombre, int capacidad) {
		super();
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.clases = new ArrayList<>();
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

	public List<Clase> getSesiones() {
		return clases;
	}

	public void setSesiones(List<Clase> clases) {
		this.clases = clases;
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
	
	public boolean puedeAsignarseClase (Clase sesion) {
		for(Clase clase: clases) {
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
	
	public void asignarClase(Clase sesion) throws SeSuperponenClasesException {
		if(!puedeAsignarseClase(sesion)) {
			throw new SeSuperponenClasesException("La clase que se desea asignar a la sala "+ id +" se superone a otra clase");
		}
		clases.add(sesion);
	}
}
