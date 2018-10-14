package com.tmi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="NIF")
public class NIF extends AbsEntity{
	
	@Column (name="SIGLA", unique=true)
	private String sigla;
	
	@Column (name="DESCRIPCION", nullable=true)
	private String descripcion;
	
	public NIF() { }
	
	public NIF(String sigla, String descripcion) {
		this.sigla = sigla;
		this.descripcion = descripcion;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof NIF))return false;
	    NIF otherMyClass = (NIF)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
}
