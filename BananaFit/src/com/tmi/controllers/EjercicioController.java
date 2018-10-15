package com.tmi.controllers;

import com.tmi.daos.Dao;
import com.tmi.entities.Ejercicio;
import com.tmi.exceptions.ObjetoInexistenteException;

public class EjercicioController {
	private Dao<Ejercicio> daoEjercicio = new Dao<Ejercicio>(Ejercicio.class);
	
	public Ejercicio altaEjercicio(String nombre, String descripcion) {
		Ejercicio e = new Ejercicio(nombre, descripcion);
		daoEjercicio.grabar(e);
		return e;
	}
	
	public void modificarEjercicio(Ejercicio ejercicio, String nuevoNombre, String nuevaDescripcion) {
		ejercicio.setNombre(nuevoNombre);
		ejercicio.setDescripcion(nuevaDescripcion);
		daoEjercicio.grabar(ejercicio);
	}
	
	public void modificarEjercicio(Integer id, String nuevoNombre, String nuevaDescripcion) throws ObjetoInexistenteException {
		Ejercicio e = daoEjercicio.getById(id);
		modificarEjercicio(e, nuevoNombre, nuevaDescripcion);
	}
	
	public void borrarEjercicio(Integer id) throws ObjetoInexistenteException {
		Ejercicio e = daoEjercicio.getById(id);
		borrarEjercicio(e);
	}
	
	public void borrarEjercicio(Ejercicio ejercicio) {
		daoEjercicio.borrar(ejercicio);
	}
	
}
