package com.tmi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tmi.exceptions.SeSuperponenClasesException;
import com.tmi.exceptions.TipoDeUsuarioInexistenteException;

@Entity
@Table(name="USUARIO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO")
public abstract class Usuario extends AbsEntity{
	
	public static enum TipoUsuario {
	    SOCIO   (1, "SOCIO"),
	    PROFESOR (2,"PROFESOR"),
	    ADMINISTRATIVO   (3, "ADMINISTRATIVO");

		private Integer id;
		private String nombre;
		TipoUsuario(Integer id,String nombre) {
			this.id=id;
			this.nombre=nombre;
	    }
		public Integer getId() {
			return id;
		}
		public String getNombre() {
			return nombre;
		}
		
		public static TipoUsuario getById(Integer id) throws TipoDeUsuarioInexistenteException {
			for(TipoUsuario tipoUsuario: TipoUsuario.values())
				if(tipoUsuario.getId().equals(id))
					return tipoUsuario;
			throw new TipoDeUsuarioInexistenteException("No existe un tipo de usuario con id "+id);
		}
	}
	
	@Column (name="NOMBRE", nullable=true)
	protected String nombre;
	
	@Column (name="APELLIDO", nullable=true)
	protected String apellido;

	@Column (name="MAIL", nullable=true)
	protected String mail;

	@Column (name="ULTIMO_APTO_MEDICO", nullable=true)
	protected Date ultAptoMedico;
	
	@Column (name="USERNAME", unique=true)
	protected String user;
	
	@Column (name="PASS", nullable=true)
	protected String pass;
	
	@Column (name="TIPO", nullable=false, insertable=false, updatable=false)
	protected String tipo;
	
	@OneToMany(mappedBy = "creador",fetch=FetchType.LAZY)
	protected List<Rutina> rutinasCreadas;
	
	@ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
    @JoinTable(name = "USUARIO_RUTINA",
    	joinColumns = @JoinColumn(name = "USUARIO_ID"),
    	inverseJoinColumns = @JoinColumn(name = "RUTINA_ID")
    	)
	protected List<Rutina> rutinasAsociadas;
	
	@ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
    @JoinTable(name = "USUARIO_CLASE",
    	joinColumns = @JoinColumn(name = "USUARIO_ID"),
    	inverseJoinColumns = @JoinColumn(name = "CLASE_ID")
    	)
	protected List<Clase> clases;
	
	@Column (name="NRO_NIF", nullable=true)
	protected int nroNIF ;
	
	@Column (name="TELEFONO", nullable=true)
	protected String telefono;

	@Column (name="DOMICILIO", nullable=true)
	protected String domicilio;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NIF_ID")
	protected NIF nif;

	public Usuario() { }
	
	public Usuario(String nombre, String apellido, String mail, Date ultimaPresentacionAptaMedica, String user, String pass, NIF nif, int nroNIF, String telefono, String direccion) {
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
	    if (!(other instanceof Usuario))return false;
	    Usuario otherMyClass = (Usuario)other;
	    if(otherMyClass.getId()== this.getId()) return true;
	    return false;
	}
	
	public boolean puedeAsistirALaClase(Clase sesion) {
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
	
	public void asignarClase(Clase clase) throws SeSuperponenClasesException {
		if(!puedeAsistirALaClase(clase)) {
			throw new SeSuperponenClasesException("La Clase que se desea asignar a la persona "+ id +" se superone a otra clase");
		}
		clases.add(clase);
	}
	
	public void removerSesion(Clase sesion) {
		clases.remove(sesion);
	}
	
	
	public void agregarRutina(Rutina rutina) {
		rutinasAsociadas.add(rutina);
	}
	
	public void removerRutina(Rutina rutina) {
		rutinasAsociadas.remove(rutina);
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

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> sesiones) {
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
