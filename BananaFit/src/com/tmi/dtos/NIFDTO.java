package com.tmi.dtos;

public class NIFDTO extends DTO{
	
	private String sigla;
	
	private String descripcion;
	
	public NIFDTO() { }
	
	public NIFDTO(String sigla, String descripcion) {
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
	    if (!(other instanceof NIFDTO))return false;
	    NIFDTO otherMyClass = (NIFDTO)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
}
