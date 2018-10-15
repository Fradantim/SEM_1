package com.tmi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tmi.daos.Dao;
import com.tmi.dtos.SalaDTO;
import com.tmi.entities.Clase;
import com.tmi.entities.Sala;
import com.tmi.entities.Clase.Dia;
import com.tmi.exceptions.DiaInexistenteException;
import com.tmi.exceptions.ObjetoInexistenteException;

public class SalaController {
	private Dao<Sala> salaDao = new Dao<Sala>(Sala.class);

	public SalaDTO altaSala(String nombre, int capacidad) {
		Sala sala = new Sala(nombre, capacidad);
		salaDao.grabar(sala);
		return sala.toDTO();
	}
	
	public void modificarSala(Sala sala, String nombre, int capacidad) {
		sala.setCapacidad(capacidad);
		sala.setNombre(nombre);
		salaDao.grabar(sala);
	}
	
	public void modificarSala(Integer idSala, String nombre, int capacidad) throws ObjetoInexistenteException {
		modificarSala(salaDao.getById(idSala), nombre, capacidad);
	}
	
	public void borrarSala(Sala sala) {
		salaDao.borrar(sala);
	}
	
	public void borrarSala(Integer idSala) throws ObjetoInexistenteException {
		salaDao.borrar(salaDao.getById(idSala));
	}
	
	public List<SalaDTO> getSalasDisponibles(Dia dia, int minuroInicio, int duracion) {
		List<Sala> result = new ArrayList<Sala>();
		Clase auxClase = new Clase(minuroInicio, null, dia.getId(), null, null);
		for(Sala sala : salaDao.getAll()) {
			if(sala.puedeAsignarseClase(auxClase)) {
				result.add(sala);
			}
		}
		return result.stream().map(Sala::toDTO).collect(Collectors.toList());
	}
	
	public List<SalaDTO> getSalasDisponibles(Integer idDia, int minuroInicio, int duracion) throws DiaInexistenteException {
		Dia dia = Dia.getById(idDia);
		return getSalasDisponibles(dia, minuroInicio, duracion);
	}
	
	
	
	
}
