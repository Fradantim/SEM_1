package com.tmi.entities;

import javax.persistence.Entity;

@Entity
public class Administrativo extends Usuario {

	public Administrativo () { }
	
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
