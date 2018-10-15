package com.tmi.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tmi.controllers.EjercicioController;
import com.tmi.controllers.RutinaController;
import com.tmi.controllers.UsuarioController;
import com.tmi.daos.Dao;
import com.tmi.entities.NIF;
import com.tmi.entities.Usuario;
import com.tmi.exceptions.ObjetoInexistenteException;
import com.tmi.exceptions.TipoDeUsuarioInexistenteException;
import com.tmi.exceptions.YaExisteElUsuarioException;

public class TestControllers {
	
	public static void main (String[] args) throws ObjetoInexistenteException, YaExisteElUsuarioException, TipoDeUsuarioInexistenteException {
		
		
		/*
		LAMBDA
		List<String> namesList = personList.stream()
                                   .map(Person::getName)
                                   .collect(Collectors.toList());
	
		 */
		EjercicioController ec = new EjercicioController();
		RutinaController rc = new RutinaController();
		UsuarioController uc = new UsuarioController();
		Dao<NIF> daoNIF = new Dao<NIF>(NIF.class);
		
		System.out.println("PERSISTENCIA NIFs");
		List<NIF> NIFs = new ArrayList<>();
		
		NIFs.add(new NIF("DNI", "Documento Nacional de Identidad"));
		NIFs.add(new NIF("CUIT", "Codigo Unico de Identificacion Tributaria"));
		NIFs.add(new NIF("CI", "Cedula de Identidad"));
				
		daoNIF.grabar(NIFs);
				
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		
		
		
		System.out.println("PERSISTENCIA SOCIOS");
		
		uc.altaUsuario("userA", "1234", 1, "Nombre A", "Apellido A", "MAIL A", true, 1, 12345678, "Domicilio A", "Telefono A");
		uc.altaUsuario("userB", "1234", 1, "Nombre B", "Apellido B", "MAIL B", false, 1, 12345678, "Domicilio B", "Telefono B");
		uc.altaUsuario("userC", "1234", 1, "Nombre C", "Apellido C", "MAIL C", true, 1, 12345678, "Domicilio C", "Telefono C");
		uc.altaUsuario("userD", "1234", 1, "Nombre D", "Apellido D", "MAIL D", false, 1, 12345678, "Domicilio D", "Telefono D");
		uc.altaUsuario("userE", "1234", 1, "Nombre E", "Apellido E", "MAIL E", false, 1, 12345678, "Domicilio E", "Telefono E");
		
		uc.altaUsuario("1045690", "1045690", 1, "Matias Ignacio", "Brabermoin", "mbrabermoin@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1067749", "1067749", 1, "Agustin Gabriel", "Chiodi Acosta", "achiodiacosta@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1066058", "1066058", 1, "Ezequiel", "Cufre", "ecufre@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1079741", "1079741", 1, "German", "De Pina", "gdepina@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1047038", "1047038", 1, "Diaz", "Julian", "juldiaz@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1072974", "1072974", 1, "Leonardo David", "Ibañez", "leibanez@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1060475", "1060475", 1, "Belen Maria", "Lago", "blago@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1074499", "1074499", 1, "Eduardo Andres", "Lecca", "elecca@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1083638", "1083638", 1, "Lucio Javier", "Mancebo", "lmancebo@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1050495", "1050495", 1, "Matias Nahuel", "Menis", "mamenis@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1085065", "1085065", 1, "Ezequiel", "Porras", "eporras@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1057181", "1057181", 1, "Sebastian Martin", "Roidzaid", "sroidzaid@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1081982", "1081982", 1, "Christian Jorge", "Russo", "chrrusso@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1032818", "1032818", 1, "Alan Edgar", "Souto", "asouto@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1020847", "1020847", 1, "Martin Alejandro", "Taboada", "martaboada@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1052849", "1052849", 1, "Franco Daniel", "Timpone", "ftimpone@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1028898", "1028898", 1, "Lucio", "Tzikas", "ltzikas@uade.edu.ar", true, 1, 0, "", "");
		uc.altaUsuario("1040787", "1040787", 1, "Braian Ezequiel", "Venieri", "bvenieri@uade.edu.ar", true, 1, 0, "", "");
		
		uc.altaUsuario("adminA", "1234", 1, "ANombre A", "AApellido A", "AMAIL A", false, 1, 12345678, "ADomicilio A", "ATelefono A");
		uc.altaUsuario("adminB", "1234", 1, "ANombre B", "AApellido B", "AMAIL B", false, 1, 12345678, "ADomicilio B", "ATelefono B");
		uc.altaUsuario("adminC", "1234", 1, "ANombre C", "AApellido C", "AMAIL C", false, 1, 12345678, "ADomicilio C", "ATelefono C");
		uc.altaUsuario("adminD", "1234", 1, "ANombre D", "AApellido D", "AMAIL D", false, 1, 12345678, "ADomicilio D", "ATelefono D");
		uc.altaUsuario("adminE", "1234", 1, "ANombre E", "AApellido E", "AMAIL E", false, 1, 12345678, "ADomicilio E", "ATelefono E");
		
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");

		
		
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
		
		rc.altaRutina(1, "Rut A", "Desc Rut A", 4, 40,true);
		rc.altaRutina(1, "Rut B", "Desc Rut B", 4, 40,false);
		rc.altaRutina(1, "Rut C", "Desc Rut C", 4, 40,true);
		
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		
		System.out.println("ASIGNACION RUTINAS-EJERCICIOS");
		
		rc.agregarEjercicio(1, 1);
		rc.agregarEjercicio(1, 2);
		rc.agregarEjercicio(1, 3);
		rc.agregarEjercicio(2, 4);
		rc.agregarEjercicio(2, 5);
		rc.agregarEjercicio(2, 6);
		rc.asignarEjercicios(3, Arrays.asList(new Integer[] {1,2,3,4,5,6,7}) );
		
		/*
		System.out.println("Ejercicios -------------");
		for(Ejercicio ej: new Dao<Ejercicio> (Ejercicio.class).getAll()) {
			System.out.println(ej.toString() + "\t"+ej.getRutinas().size() +" rutinas");
			System.out.println("\t-------------");
		}
		
		System.out.println("Rutinas -------------");
		for(Rutina ru: new Dao<Rutina> (Rutina.class).getAll()) {
			System.out.println(ru.toString() + "\t"+ru.getEjercicios().size() +" ejercicios");
			System.out.println("\t-------------");
		}
		*/

		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		
		System.out.println("ASIGNACION USUARIO-RUTINAS");
		
		uc.asociarRutina(2, 1);	
		uc.asociarRutina(2, 2);
		uc.asociarRutina(3, 2);
		uc.asociarRutina(3, 3);
		uc.asociarRutina(4, Arrays.asList(new Integer[] {1,2,3}));
		
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		
		/*
		System.out.println("TEST BUSQUEDA POR PARAMETROS");
		Map<String, Object> params = new HashMap<>();
		params.put("nombre", "Ma");
		params.put("apellido", "Brabermoin");
		
		System.out.println("INCLUSIVES:");
		for(Usuario usuario: uc.findByAttrubutes(params, true)) {
			System.out.println(usuario.toString());
		}
		System.out.println("EXCLUSIVES:");
		for(Usuario usuario: uc.findByAttrubutes(params, false)) {
			System.out.println(usuario.toString());
		}
		
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		*/
		
		
		
	}
	
}

