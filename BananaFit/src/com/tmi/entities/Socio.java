package com.tmi.entities;

import javax.persistence.Entity;

@Entity
public class Socio extends Usuario{

	public Socio () { }
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Socio))return false;
	    Socio otherMyClass = (Socio)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
}
