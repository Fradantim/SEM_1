package com.tmi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import com.tmi.exceptions.SeSuperponenClases;

@Entity
public class Profesor extends Usuario{

	private List<Clase> sesionesDictadas;

	public Profesor() {}
	
	public Profesor(String nombre, Date ultimaPresentacionAptaMedica, String user, String pass, NIF nif, int nroNIF) {
		super(nombre, ultimaPresentacionAptaMedica, user, pass, nif, nroNIF);
		sesionesDictadas= new ArrayList<>();
	}

	public List<Clase> getSesionesDictadas() {
		return sesionesDictadas;
	}

	public void setSesionesDictadas(List<Clase> sesionesDictadas) {
		this.sesionesDictadas = sesionesDictadas;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Profesor))return false;
	    Profesor otherMyClass = (Profesor)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
	public void asignarClase(Clase sesion) throws SeSuperponenClases {
		if(!puedeAsistirALaClase(sesion)) {
			throw new SeSuperponenClases("La sesion "+sesion.getId() +" que se desea asignar al docente "+ id +" se superone a otra clase");
		}
		
		sesionesDictadas.add(sesion);
	}
	
	@Override
	public boolean puedeAsistirALaClase(Clase sesion) {
		for(Clase clase: sesionesDictadas) {
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
}
