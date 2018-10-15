package com.tmi.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMINISTRATIVO")
public class Administrativo extends Usuario {

	public Administrativo () { }
	
	public Administrativo (String nombre, String apellido, String mail, Date ultimaPresentacionAptaMedica, String user, String pass, NIF nif, int nroNIF,String telefono, String domicilio) {
		super(nombre,apellido,mail,ultimaPresentacionAptaMedica,user,pass,nif,nroNIF,telefono, domicilio);
		this.tipo=TipoUsuario.ADMINISTRATIVO.getNombre();
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Administrativo))return false;
	    Administrativo otherMyClass = (Administrativo)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
}
