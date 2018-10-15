package com.tmi.controllers;


import com.tmi.daos.Dao;
import com.tmi.entities.Actividad;
import com.tmi.entities.Clase;
import com.tmi.entities.Profesor;
import com.tmi.entities.Sala;
import com.tmi.entities.Usuario;
import com.tmi.exceptions.LaSesionTieneCupoLlenoException;
import com.tmi.exceptions.ObjetoInexistenteException;
import com.tmi.exceptions.SeSuperponenClasesException;

public class ClaseController {
	private Dao<Usuario> usuarioDao = new Dao<Usuario>(Usuario.class);
	private Dao<Clase> claseDao = new Dao<Clase>(Clase.class);
	private Dao<Profesor> profesorDao = new Dao<Profesor>(Profesor.class);
	private Dao<Actividad> actividadDao = new Dao<Actividad>(Actividad.class);
	private Dao<Sala> salaDao = new Dao<Sala>(Sala.class);

	public Clase altaClase(int minutoInicio, Profesor profesor, int dia, Actividad actividad, Sala sala) throws SeSuperponenClasesException {
		Clase clase = new Clase(minutoInicio, profesor, dia, actividad, sala);
		profesor.dictarClase(clase);
		sala.asignarClase(clase);
		claseDao.grabar(clase);
		return clase;
	}

	public Clase altaClase(int minutoInicio, Integer idProfesor, int dia, Integer idActividad, Integer idSala) throws ObjetoInexistenteException, SeSuperponenClasesException {
		Profesor profesor = profesorDao.getById(idProfesor);
		Actividad actividad = actividadDao.getById(idActividad);
		Sala sala = salaDao.getById(idSala);
		return altaClase(minutoInicio, profesor, dia, actividad, sala);
	}

	public void agregarUsuario(Clase clase, Usuario usuario) throws LaSesionTieneCupoLlenoException, SeSuperponenClasesException {
		usuario.asignarClase(clase);
		clase.inscribirUsuario(usuario);
		claseDao.grabar(usuario);
	}
	
	public void agregarUsuario(Integer idClase, Integer idUsuario) throws LaSesionTieneCupoLlenoException, ObjetoInexistenteException, SeSuperponenClasesException {
		Clase clase = claseDao.getById(idClase);
		Usuario usuario = usuarioDao.getById(idUsuario);
		agregarUsuario(clase, usuario);
	}
	
	public void removerUsuario(Clase clase, Usuario usuario) {
		clase.removerUsuario(usuario);
		claseDao.grabar(usuario);
	}
	
	public void removerUsuario(Integer idClase, Integer idUsuario) throws ObjetoInexistenteException {
		Clase clase = claseDao.getById(idClase);
		Usuario usuario = usuarioDao.getById(idUsuario);
		removerUsuario(clase, usuario);
	}
	
	public void modificarClase(Clase clase, int minutoInicio, Profesor profesor, int dia, Actividad actividad, Sala sala) throws SeSuperponenClasesException {
		clase.setDia(dia);
		clase.setMinutoInicio(minutoInicio);
		clase.setActividad(actividad);
		if(!clase.getProfesor().equals(profesor)) {
			profesor.dictarClase(clase);
			clase.setProfesor(profesor);
		}
		if(!clase.getSala().equals(sala)) {
			sala.asignarClase(clase);
			clase.setSala(sala);
		}
		claseDao.grabar(clase);
	}
	
	public void modificarClase(Integer idClase, int minutoInicio, Integer idProfesor, int dia, Integer idActividad, Integer idSala) throws SeSuperponenClasesException, ObjetoInexistenteException {
		modificarClase(claseDao.getById(idClase), minutoInicio, profesorDao.getById(idProfesor), dia, actividadDao.getById(idActividad), salaDao.getById(idSala));
	}
}
