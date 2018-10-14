package com.tmi.test;

import java.util.ArrayList;
import java.util.List;

import com.tmi.daos.Dao;
import com.tmi.entities.Ejercicio;
import com.tmi.exceptions.ObjetoInexistenteException;

public class testEjercicio {

	public static void main(String args[]) {
		Dao<Ejercicio> dao = new Dao<Ejercicio>(Ejercicio.class);
		
		List<Ejercicio> entities = new ArrayList<>();
		
		entities.add(new Ejercicio("EjercicioA ","DescA "));
		entities.add(new Ejercicio("EjercicioB ","DescB "));
		entities.add(new Ejercicio("EjercicioC ","DescC "));
		
		System.out.println("PERSISTENCIA");
		for(Ejercicio entity: entities){
			System.out.print("Grabando...\t");
			dao.grabar(entity);
			System.out.println("Grabado! "+entity.toString());
		}
		
		
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		
		entities=null;
		
		System.out.println("GET ALL");		
		entities = dao.getAll();
		System.out.println(entities.size() +" elementos encontrados:");
		for(Ejercicio entity : entities){
			System.out.println(entity.toString());
		}
		System.out.println();
		
		System.out.println("FIN");
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		
		Integer id = 2;
		System.out.println("GET BY ID "+id);		
		try {
			Ejercicio entity= dao.getById(id);
			System.out.println("Entidad con id "+id+" encontrada: "+ entity.toString());
		} catch (ObjetoInexistenteException e) {
			System.out.println(e.getLocalizedMessage()+ ":(");
		}
		
		System.out.println("FIN");
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
	}
}
