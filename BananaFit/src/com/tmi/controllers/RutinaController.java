package com.tmi.controllers;

import java.util.ArrayList;
import java.util.List;

import com.tmi.daos.Dao;
import com.tmi.entities.Ejercicio;
import com.tmi.entities.Rutina;
import com.tmi.entities.Usuario;
import com.tmi.exceptions.ObjetoInexistenteException;

public class RutinaController {
	private Dao<Rutina> daoRutina= new Dao<Rutina>(Rutina.class);
	private Dao<Usuario> daoUsuario= new Dao<Usuario>(Usuario.class);
	private Dao<Ejercicio> daoEjercicio= new Dao<Ejercicio>(Ejercicio.class);
	
	public Rutina altaRutina(Integer idUserCreador, String nombre, String descripcion, int series, int duracionEjercicio) throws ObjetoInexistenteException {
		//Usuario user = daoUsuario.getById(idUserCreador);
		Usuario user = null;
		//TODO descomentar arriba cuando Usuario sea entidad
		return altaRutina(user, nombre, descripcion, series, duracionEjercicio);
	}
	
	public Rutina altaRutina(Usuario userCreador, String nombre, String descripcion, int series, int duracionEjercicio) {
		Rutina r= new Rutina(nombre, descripcion, userCreador, series, duracionEjercicio);
		daoRutina.grabar(r);
		return r;
	}
	
	public void modificarRutina(Integer idRutina, String nombre, String descripcion, int series, int duracionEjercicio) throws ObjetoInexistenteException {
		Rutina r= daoRutina.getById(idRutina);
		modificarRutina(r, nombre, descripcion, series, duracionEjercicio);
	}
	
	public void modificarRutina(Rutina rutina, String nombre, String descripcion, int series, int duracionEjercicio) {
		rutina.setNombre(nombre);
		rutina.setDescripcion(descripcion);
		rutina.setSeries(series);
		rutina.setDuracionEjercicio(duracionEjercicio);
		daoRutina.grabar(rutina);
	}
	
	public void borrarRutina(Integer id) throws ObjetoInexistenteException {
		Rutina r = daoRutina.getById(id);
		daoRutina.borrar(r);
	}
	
	public void agregarEjercicio(Rutina rutina, Ejercicio ejercicio) {
		rutina.agregarEjercicio(ejercicio);
		daoRutina.grabar(rutina);
	}
	
	public void agregarEjercicio(Integer idRutina, Integer idEjercicio) throws ObjetoInexistenteException {
		Ejercicio e = daoEjercicio.getById(idEjercicio);
		Rutina r= daoRutina.getById(idRutina);
		agregarEjercicio(r, e);
	}
	
	public void agregarEjercicios(Rutina rutina, List<Ejercicio> ejercicios) {
		for(Ejercicio ejercicio: ejercicios)
			rutina.agregarEjercicio(ejercicio);
		daoRutina.grabar(rutina);
	}
	
	public void agregarEjercicios(Integer idRutina, List<Integer> idsEjercicio) throws ObjetoInexistenteException {
		List<Ejercicio> ejercicios= new ArrayList<>();
		for(Integer idEjercicio: idsEjercicio) {
			ejercicios.add(daoEjercicio.getById(idEjercicio));
		}
		Rutina r= daoRutina.getById(idRutina);
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
		daoRutina.grabar(rutina);
	}

	/**
	 * Asigna los ejercicios, borra los anteriores
	 * @param rutina
	 * @param ejercicios
	 */
	public void asignarEjercicios(Integer idRutina, List<Integer> idsEjercicio) throws ObjetoInexistenteException {
		List<Ejercicio> ejercicios= new ArrayList<>();
		for(Integer idEjercicio: idsEjercicio) {
			ejercicios.add(daoEjercicio.getById(idEjercicio));
		}
		Rutina r= daoRutina.getById(idRutina);
		agregarEjercicios(r, ejercicios);
	}
	
}
