package com.tmi.test;

import java.util.Arrays;

import com.tmi.controllers.EjercicioController;
import com.tmi.controllers.RutinaController;
import com.tmi.daos.Dao;
import com.tmi.entities.Ejercicio;
import com.tmi.entities.Rutina;
import com.tmi.exceptions.ObjetoInexistenteException;

public class TestControllers {
	
	public static void main (String[] args) throws ObjetoInexistenteException {
		EjercicioController ec = new EjercicioController();
		RutinaController rc = new RutinaController();

		
		System.out.println("PERSISTENCIA EJERCICIOS");
		
		ec.altaEjercicio("Ejer A", "DESC EJER A");
		ec.altaEjercicio("Ejer B", "DESC EJER B");
		ec.altaEjercicio("Ejer C", "DESC EJER C");
		ec.altaEjercicio("Ejer D", "DESC EJER D");
		ec.altaEjercicio("Ejer E", "DESC EJER E");
		ec.altaEjercicio("Ejer F", "DESC EJER F");
		ec.altaEjercicio("Ejer G", "DESC EJER G");
	
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		
		System.out.println("PERSISTENCIA RUTINAS");
		
		rc.altaRutina(0, "Rut A", "Desc Rut A", 4, 40);
		rc.altaRutina(0, "Rut B", "Desc Rut B", 4, 40);
		rc.altaRutina(0, "Rut C", "Desc Rut C", 4, 40);
		
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		
		System.out.println("ASIGNACION RUTINAS-EJERCICIOS");
		
		rc.agregarEjercicio(1, 1);
		rc.agregarEjercicio(1, 2);
		rc.agregarEjercicio(1, 3);
		rc.agregarEjercicio(2, 4);
		rc.agregarEjercicio(2, 5);
		rc.agregarEjercicio(2, 6);
		rc.asignarEjercicios(3, Arrays.asList(new Integer[] { 1,2,3,4,5,6,7}) );
		

		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		
		System.out.println("Ejercicios");
		for(Ejercicio ej: new Dao<Ejercicio> (Ejercicio.class).getAll()) {
			System.out.println("\t" + ej.toString() + " "+ej.getRutinas().size() +" rutinas");
		}
		/*
		System.out.println("Rutinas");
		for(Rutina ru: new Dao<Rutina> (Rutina.class).getAll()) {
			System.out.println("\t" + ru.toString() + " "+ru.getEjercicios().size() +" ejercicios");
		}
		*/
		//System.out.println(new Dao<Ejercicio> (Ejercicio.class).getAll().size());
	}
	
}

