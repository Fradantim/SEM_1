package com.tmi.controllers;


import java.util.List;

import com.tmi.daos.Dao;
import com.tmi.entities.Actividad;
import com.tmi.exceptions.ObjetoInexistenteException;

public class ActividadController {
	private Dao<Actividad> actividadDao = new Dao<Actividad>(Actividad.class);


	public Actividad altaActividad(String nombre, int duracion, String descripcion) {
		Actividad actividad = new Actividad(nombre, duracion, descripcion);
		actividadDao.grabar(actividad);
		return actividad;
	}
	
	public List<Actividad> getAll() {
		return actividadDao.getAll();
	}
	
	public void modificarActividad(Actividad actividad, String nombre, int duracion, String descripcion) {
		actividad.setDescripcion(descripcion);
		actividad.setDuracion(duracion);
		actividad.setNombre(nombre);
		actividadDao.grabar(actividad);
	}
	
	public void modificarActividad(Integer idActividad, String nombre, int duracion, String descripcion) throws ObjetoInexistenteException {
		modificarActividad(actividadDao.getById(idActividad), nombre, duracion, descripcion);
	}	
	
	public void borrarActividad(Actividad actividad) {
		actividadDao.borrar(actividad);
	}
	
	public void borrarActividad(Integer idActividad) throws ObjetoInexistenteException {
		borrarActividad(actividadDao.getById(idActividad));
	}
}
