package com.tmi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.tmi.exceptions.SeSuperponenClasesException;

@Entity
@DiscriminatorValue("PROFESOR")
public class Profesor extends Usuario{

	@OneToMany(mappedBy = "profesor",fetch=FetchType.LAZY)
	private List<Clase> clasesDictadas;

	public Profesor() {}
	
	public Profesor(String nombre, String apellido, String mail, Date ultimaPresentacionAptaMedica, String user, String pass, NIF nif, int nroNIF,String telefono, String domicilio) {
		super(nombre, apellido, mail, ultimaPresentacionAptaMedica, user, pass, nif, nroNIF,telefono, domicilio);
		this.tipo=TipoUsuario.PROFESOR.getNombre();
		clasesDictadas= new ArrayList<>();
	}
	
	public List<Clase> getClasesDictadas() {
		return clasesDictadas;
	}

	public void setClasesDictadas(List<Clase> sesionesDictadas) {
		this.clasesDictadas = sesionesDictadas;
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
	
	public void dictarClase(Clase sesion) throws SeSuperponenClasesException {
		if(!puedeAsistirALaClase(sesion)) {
			throw new SeSuperponenClasesException("La clase "+sesion.getId() +" que se desea asignar al docente "+ id +" se superone a otra clase");
		}
		
		clasesDictadas.add(sesion);
	}
	
	@Override
	public boolean puedeAsistirALaClase(Clase sesion) {
		for(Clase clase: clasesDictadas) {
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
}
