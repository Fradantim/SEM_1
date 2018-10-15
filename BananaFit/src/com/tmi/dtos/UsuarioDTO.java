package com.tmi.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioDTO extends DTO{
	
	private String nombre;
	
	private String apellido;

	private String mail;

	private Date ultAptoMedico;

	private String user;
	
	private String pass;

	private String tipo;
	
	private List<RutinaDTO> rutinasCreadas;
	
	private List<RutinaDTO> rutinasAsociadas;
	
	private List<ClaseDTO> clases;
	
	private int nroNIF ;
	
	private String telefono;

	private String domicilio;
	
	private NIFDTO nif;

	public UsuarioDTO() { }
	
	public UsuarioDTO(String nombre, String apellido, String mail, Date ultimaPresentacionAptaMedica, String user, String pass, NIFDTO nif, int nroNIF, String telefono, String direccion) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.ultAptoMedico = ultimaPresentacionAptaMedica;
		this.user = user;
		this.pass = pass;
		this.nif = nif;
		this.nroNIF = nroNIF; 
		this.telefono= telefono;
		this.domicilio= direccion;
		this.rutinasCreadas = new ArrayList<>();
		this.rutinasAsociadas = new ArrayList<>();
		this.clases = new ArrayList<>();
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof UsuarioDTO))return false;
	    UsuarioDTO otherMyClass = (UsuarioDTO)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
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

	public List<RutinaDTO> getRutinasCreadas() {
		return rutinasCreadas;
	}

	public void setRutinasCreadas(List<RutinaDTO> rutinasCreadas) {
		this.rutinasCreadas = rutinasCreadas;
	}

	public List<RutinaDTO> getRutinasAsociadas() {
		return rutinasAsociadas;
	}

	public void setRutinasAsociadas(List<RutinaDTO> rutinasAsociadas) {
		this.rutinasAsociadas = rutinasAsociadas;
	}

	public List<ClaseDTO> getClases() {
		return clases;
	}

	public void setClases(List<ClaseDTO> sesiones) {
		this.clases = sesiones;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getUltAptoMedico() {
		return ultAptoMedico;
	}

	public void setUltAptoMedico(Date ultimaPresentacionAptaMedica) {
		this.ultAptoMedico = ultimaPresentacionAptaMedica;
	}	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public NIFDTO getNif() {
		return nif;
	}

	public void setNif(NIFDTO nif) {
		this.nif = nif;
	}

	public int getNroNIF() {
		return nroNIF;
	}

	public void setNroNIF(int nroNIF) {
		this.nroNIF = nroNIF;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String direccion) {
		this.domicilio = direccion;
	}
}
