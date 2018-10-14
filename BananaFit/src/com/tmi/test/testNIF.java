package com.tmi.test;

import java.util.ArrayList;
import java.util.List;

import com.tmi.daos.Dao;
import com.tmi.entities.NIF;
import com.tmi.exceptions.ObjetoInexistenteException;

public class testNIF {

	public static void main(String args[]) {
		Dao<NIF> dao = new Dao<NIF>();
		
		List<NIF> NIFs = new ArrayList<>();
		
		NIFs.add(new NIF("DNI", "Documento Nacional de Identidad"));
		NIFs.add(new NIF("CUIT", "Codigo Unico de Identificacion Tributaria"));
		NIFs.add(new NIF("CI", "Cedula de Identidad"));
		//NIFs.add(new NIF("", ""));
		
		
		System.out.println("PERSISTENCIA");
		for(NIF nif : NIFs){
			System.out.print("Grabando...\t");
			dao.grabar(nif);
			System.out.println("Grabado! "+nif.toString());
		}
		
		
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		
		NIFs=null;
		
		System.out.println("GET ALL");		
		NIFs = dao.getAll();
		System.out.println(NIFs.size() +" elementos encontrados:");
		for(NIF nif : NIFs){
			System.out.println(nif.toString());
		}
		System.out.println();
		
		System.out.println("FIN");
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		
		Integer id = 2;
		System.out.println("GET BY ID "+id);		
		try {
			NIF nif= dao.getById(id);
			System.out.println("NIF con id "+id+" encontrado: "+ nif.toString());
		} catch (ObjetoInexistenteException e) {
			System.out.println(e.getLocalizedMessage()+ ":(");
		}
		
		System.out.println("FIN");
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
	}
}
