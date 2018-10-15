package com.tmi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import com.tmi.exceptions.SeSuperponenClases;

@Entity
public abstract class Usuario extends AbsEntity{
	
	protected String nombre;

	protected Date ultimaPresentacionAptaMedica;
	
	protected String user;
	
	protected String pass;
	
	protected String tipo;
	
	protected List<Rutina> rutinasCreadas;
	
	protected List<Rutina> rutinasAsociadas;
	
	protected List<Clase> sesiones;

	protected int nroNIF ;
	
	protected NIF nif;

	public Usuario() { }
	
	public Usuario(String nombre, Date ultimaPresentacionAptaMedica, String user, String pass, NIF nif, int nroNIF) {
		super();
		this.nombre = nombre;
		this.ultimaPresentacionAptaMedica = ultimaPresentacionAptaMedica;
		this.user = user;
		this.pass = pass;
		this.nif = nif;
		this.nroNIF = nroNIF; 
		this.rutinasCreadas = new ArrayList<>();
		this.rutinasAsociadas = new ArrayList<>();
		this.sesiones = new ArrayList<>();
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

	public List<Clase> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<Clase> sesiones) {
		this.sesiones = sesiones;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getUltimaPresentacionAptaMedica() {
		return ultimaPresentacionAptaMedica;
	}

	public void setUltimaPresentacionAptaMedica(Date ultimaPresentacionAptaMedica) {
		this.ultimaPresentacionAptaMedica = ultimaPresentacionAptaMedica;
	}	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public NIF getNif() {
		return nif;
	}

	public void setNif(NIF nif) {
		this.nif = nif;
	}

	public int getNroNIF() {
		return nroNIF;
	}

	public void setNroNIF(int nroNIF) {
		this.nroNIF = nroNIF;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Usuario))return false;
	    Usuario otherMyClass = (Usuario)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
	public boolean puedeAsistirALaClase(Clase sesion) {
		for(Clase clase: sesiones) {
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
	
	public void asignarSesion(Clase sesion) throws SeSuperponenClases {
		if(!puedeAsistirALaClase(sesion)) {
			throw new SeSuperponenClases("La sesion que se desea asignar a la persona "+ id +" se superone a otra clase");
		}
		sesiones.add(sesion);
	}
	
	public void removerSesion(Clase sesion) {
		sesiones.remove(sesion);
	}
}
