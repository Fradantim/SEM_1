package com.tmi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import com.tmi.exceptions.SeSuperponenClases;

@Entity
public abstract class Persona {
	
	protected Integer id;
	
	protected String nombre;

	protected Date ultimaPresentacionAptaMédica;
	
	protected String user;
	
	protected String pass;
	
	protected List<Rutina> rutinasCreadas;
	
	protected List<Rutina> rutinasAsociadas;
	
	protected List<Sesion> sesiones;

	public Persona() { }
	
	public Persona(String nombre, Date ultimaPresentacionAptaMédica, String user, String pass) {
		super();
		this.nombre = nombre;
		this.ultimaPresentacionAptaMédica = ultimaPresentacionAptaMédica;
		this.user = user;
		this.pass = pass;
		this.rutinasCreadas = new ArrayList<>();
		this.rutinasAsociadas = new ArrayList<>();
		this.sesiones = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Rutina> getRutinasCreadas() {
		return rutinasCreadas;
	}

	public void setRutinasCreadas(List<Rutina> rutinasCreadas) {
		this.rutinasCreadas = rutinasCreadas;
	}

	public List<Rutina> getRutinasAsociadas() {
		return rutinasAsociadas;
	}

	public void setRutinasAsociadas(List<Rutina> rutinasAsociadas) {
		this.rutinasAsociadas = rutinasAsociadas;
	}

	public List<Sesion> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getUltimaPresentacionAptaMédica() {
		return ultimaPresentacionAptaMédica;
	}

	public void setUltimaPresentacionAptaMédica(Date ultimaPresentacionAptaMédica) {
		this.ultimaPresentacionAptaMédica = ultimaPresentacionAptaMédica;
	}	
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Persona))return false;
	    Persona otherMyClass = (Persona)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
	public boolean puedeAsistirALaClase(Sesion sesion) {
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
		if(!puedeAsistirALaClase(sesion)) {
			throw new SeSuperponenClases("La sesion que se desea asignar a la persona "+ id +" se superone a otra clase");
		}
		sesiones.add(sesion);
	}
	
	public void removerSesion(Sesion sesion) {
		sesiones.remove(sesion);
	}
}
