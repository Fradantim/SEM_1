package com.tmi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tmi.daos.Dao;
import com.tmi.daos.RutinaDao;
import com.tmi.entities.Ejercicio;
import com.tmi.entities.Rutina;
import com.tmi.entities.Usuario;
import com.tmi.exceptions.ObjetoInexistenteException;

public class RutinaController {
	private RutinaDao rutinaDao= new RutinaDao();
	private Dao<Usuario> usuarioDao= new Dao<Usuario>(Usuario.class);
	private Dao<Ejercicio> ejercicioDao= new Dao<Ejercicio>(Ejercicio.class);
	
	public List<Rutina> getRutinasPublicas(Map<String, Object> attributes, boolean inclusive){
		if(attributes==null) {
			attributes= new HashMap<>();
		}
		return rutinaDao.findRutinasPublicasByAttributes(attributes, inclusive);
	}
	
	public List<Rutina> getRutinasCreadas(Usuario usuario){
		return usuario.getRutinasCreadas();
	}
	
	public List<Rutina> getRutinasCreadas(Integer idUsuario) throws ObjetoInexistenteException{
		Usuario usuario = usuarioDao.getById(idUsuario);
		return getRutinasCreadas(usuario);
	}
	
	public List<Rutina> getRutinasAsociadas(Usuario usuario) {
		return usuario.getRutinasAsociadas();
	}
	
	public List<Rutina> getRutinasAsociadas(Integer idUsuario) throws ObjetoInexistenteException{
		Usuario usuario = usuarioDao.getById(idUsuario);
		return getRutinasAsociadas(usuario);
	}
	
	public Rutina altaRutina(Integer idUserCreador, String nombre, String descripcion, int series, int duracionEjercicio, Boolean publica) throws ObjetoInexistenteException {
		Usuario user = usuarioDao.getById(idUserCreador);
		return altaRutina(user, nombre, descripcion, series, duracionEjercicio,publica);
	}
	
	public Rutina altaRutina(Usuario userCreador, String nombre, String descripcion, int series, int duracionEjercicio, Boolean publica) {
		Rutina r= new Rutina(nombre, descripcion, userCreador, series, duracionEjercicio,publica);
		rutinaDao.grabar(r);
		return r;
	}
	
	public void modificarRutina(Integer idRutina, String nombre, String descripcion, int series, int duracionEjercicio) throws ObjetoInexistenteException {
		Rutina r= rutinaDao.getById(idRutina);
		modificarRutina(r, nombre, descripcion, series, duracionEjercicio);
	}
	
	public void modificarRutina(Rutina rutina, String nombre, String descripcion, int series, int duracionEjercicio) {
		rutina.setNombre(nombre);
		rutina.setDescripcion(descripcion);
		rutina.setSeries(series);
		rutina.setDuracionEjercicio(duracionEjercicio);
		rutinaDao.grabar(rutina);
	}
	
	public void borrarRutina(Integer id) throws ObjetoInexistenteException {
		Rutina r = rutinaDao.getById(id);
		borrarRutina(r);
	}
	
	public void borrarRutina(Rutina rutina){
		rutinaDao.borrar(rutina);
	}
	
	public void agregarEjercicio(Rutina rutina, Ejercicio ejercicio) {
		rutina.agregarEjercicio(ejercicio);
		rutinaDao.grabar(rutina);
	}
	
	public void agregarEjercicio(Integer idRutina, Integer idEjercicio) throws ObjetoInexistenteException {
		Ejercicio e = ejercicioDao.getById(idEjercicio);
		Rutina r= rutinaDao.getById(idRutina);
		agregarEjercicio(r, e);
	}
	
	public void agregarEjercicios(Rutina rutina, List<Ejercicio> ejercicios) {
		for(Ejercicio ejercicio: ejercicios)
			rutina.agregarEjercicio(ejercicio);
		rutinaDao.grabar(rutina);
	}
	
	public void agregarEjercicios(Integer idRutina, List<Integer> idsEjercicio) throws ObjetoInexistenteException {
		List<Ejercicio> ejercicios= new ArrayList<>();
		for(Integer idEjercicio: idsEjercicio) {
			ejercicios.add(ejercicioDao.getById(idEjercicio));
		}
		Rutina r= rutinaDao.getById(idRutina);
		agregarEjercicios(r, ejercicios);
	}
	
	
	/**
	 * Asigna los ejercicios, borra los anteriores
	 * @param rutina
	 * @param ejercicios
	 */
	public void asignarEjercicios(Rutina rutina, List<Ejercicio> ejercicios) {
		while(!rutina.getEjercicios().isEmpty())
			rutina.getEjercicios().remove(0);
		for(Ejercicio ejercicio: ejercicios)
			rutina.agregarEjercicio(ejercicio);
		rutinaDao.grabar(rutina);
	}

	/**
	 * Asigna los ejercicios, borra los anteriores
	 * @param rutina
	 * @param ejercicios
	 */
	public void asignarEjercicios(Integer idRutina, List<Integer> idsEjercicio) throws ObjetoInexistenteException {
		List<Ejercicio> ejercicios= new ArrayList<>();
		for(Integer idEjercicio: idsEjercicio) {
			ejercicios.add(ejercicioDao.getById(idEjercicio));
		}
		Rutina r= rutinaDao.getById(idRutina);
		agregarEjercicios(r, ejercicios);
	}
	
}
