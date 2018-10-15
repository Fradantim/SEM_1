package com.tmi.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.tmi.daos.Dao;
import com.tmi.daos.UsuarioDao;
import com.tmi.entities.Administrativo;
import com.tmi.entities.Ejercicio;
import com.tmi.entities.NIF;
import com.tmi.entities.Profesor;
import com.tmi.entities.Rutina;
import com.tmi.entities.Socio;
import com.tmi.entities.Usuario;
import com.tmi.entities.Usuario.TipoUsuario;
import com.tmi.exceptions.ObjetoInexistenteException;
import com.tmi.exceptions.TipoDeUsuarioInexistenteException;
import com.tmi.exceptions.YaExisteElUsuarioException;

public class UsuarioController {
	private Dao<Rutina> rutinaDao= new Dao<Rutina>(Rutina.class);
	private UsuarioDao usuarioDao = new UsuarioDao();
	private Dao<Ejercicio> ejercicioDao= new Dao<Ejercicio>(Ejercicio.class);
	private Dao<NIF> NIFDao= new Dao<NIF>(NIF.class);
	
	public boolean existeUsuario(String user) {
		return usuarioDao.existeUsuario(user);
	}
	
	public List<TipoUsuario> getTipoUsuarios(){
		return Arrays.asList(TipoUsuario.values());
	}
	
	public Usuario altaUsuario(String user, String pass, Integer idTipoUsuario, String nombre, String apellido, String mail, boolean presentoAptoMedico, Integer idNIF, int nroNIF, String domicilio, String telefono ) throws ObjetoInexistenteException, YaExisteElUsuarioException, TipoDeUsuarioInexistenteException {
		NIF NIF = NIFDao.getById(idNIF);
		TipoUsuario tipoUsuario = Usuario.TipoUsuario.getById(idTipoUsuario);
		return altaUsuario(user, pass, tipoUsuario, nombre, apellido, mail, presentoAptoMedico, NIF, nroNIF, domicilio, telefono);
	}
	
	public Usuario altaUsuario(String user, String pass, TipoUsuario tipoUsuario, String nombre, String apellido, String mail, boolean presentoAptoMedico, NIF NIF, int nroNIF, String domicilio, String telefono ) throws YaExisteElUsuarioException, TipoDeUsuarioInexistenteException {
		if(existeUsuario(user))
			throw new YaExisteElUsuarioException("Ya existe un usuario con username "+user);
		//sino ...
		Usuario usuario;
		Date fechaPresentacionAptoMedico=null;
		if(presentoAptoMedico) {
			fechaPresentacionAptoMedico= new Date();
		}
		
		switch (tipoUsuario) {
        case ADMINISTRATIVO:
        	usuario = new Administrativo(nombre,apellido,mail,fechaPresentacionAptoMedico,user,pass,NIF,nroNIF,telefono, domicilio);
            break;
        case SOCIO:
        	usuario = new Socio(nombre,apellido,mail,fechaPresentacionAptoMedico,user,pass,NIF,nroNIF,telefono, domicilio);
            break;
        case PROFESOR:
        	usuario = new Profesor(nombre,apellido,mail,fechaPresentacionAptoMedico,user,pass,NIF,nroNIF,telefono, domicilio);
            break;
        default:
            throw new TipoDeUsuarioInexistenteException("Tipo de usuario no reconocido: "+tipoUsuario);
		}
		
		usuarioDao.grabar(usuario);
		return usuario;
	}
	
	public void actualizarAptoMedico(Integer idUsuario) throws ObjetoInexistenteException {
		Usuario usuario = usuarioDao.getById(idUsuario);
		actualizarAptoMedico(usuario);
	}
	
	public void actualizarAptoMedico(Usuario usuario) {
		usuario.setUltAptoMedico(new Date());
		usuarioDao.grabar(usuario);
	}
	
	public void modificarUsuario(Usuario usuario, String pass, TipoUsuario tipoUsuario, String nombre, String apellido, String mail, NIF NIF, int nroNIF, String domicilio, String telefono ) {
		usuario.setPass(pass);
		usuario.setNif(NIF);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setNroNIF(nroNIF);
		usuario.setMail(mail);
		usuario.setTipo(tipoUsuario.getNombre());
		usuarioDao.grabar(usuario);
	}
	
	public void modificarUsuario(Integer idUsuario, String pass, Integer idTipoUsuario, String nombre, String apellido, String mail, Integer idNIF, int nroNIF, String domicilio, String telefono ) throws ObjetoInexistenteException, TipoDeUsuarioInexistenteException {
		NIF NIF = NIFDao.getById(idNIF);
		TipoUsuario tipoUsuario = Usuario.TipoUsuario.getById(idTipoUsuario);
		Usuario usuario = usuarioDao.getById(idUsuario);
		modificarUsuario(usuario, pass, tipoUsuario, nombre, apellido, mail, NIF, nroNIF, domicilio, telefono);
	}
	
	public void asociarRutina (Usuario usuario, Rutina rutina) {
		usuario.agregarRutina(rutina);
		usuarioDao.grabar(usuario);
	}
	
	public void asociarRutina (Integer idUsuario, Integer idRutina) throws ObjetoInexistenteException {
		Usuario usuario = usuarioDao.getById(idUsuario);
		Rutina rutina = rutinaDao.getById(idRutina);
		usuario.agregarRutina(rutina);
		usuarioDao.grabar(usuario);
	}
	
	public void borrarUsuario(Integer id) throws ObjetoInexistenteException {
		Usuario u = usuarioDao.getById(id);
		borrarUsuario(u);
	}
	
	public void borrarUsuario(Usuario usuario){
		usuarioDao.borrar(usuario);
	}
	
}