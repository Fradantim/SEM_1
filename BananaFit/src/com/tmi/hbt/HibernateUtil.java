package com.tmi.hbt;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.tmi.entities.Administrativo;
import com.tmi.entities.Ejercicio;
import com.tmi.entities.NIF;
import com.tmi.entities.Profesor;
import com.tmi.entities.Rutina;
import com.tmi.entities.Socio;
import com.tmi.entities.Usuario;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             //config.addAnnotatedClass(ArticuloEntity.class);
        	 //TODO evaluar si se puede agregar dinamicamente las extensiones de Entity
        	 config.addAnnotatedClass(NIF.class);
        	 config.addAnnotatedClass(Ejercicio.class);
        	 config.addAnnotatedClass(Rutina.class);
        	 config.addAnnotatedClass(Usuario.class);
        	 config.addAnnotatedClass(Socio.class);
        	 config.addAnnotatedClass(Administrativo.class);
        	 config.addAnnotatedClass(Profesor.class);
             //TODO Agregar todas las entities ~~~~
             sessionFactory = config.buildSessionFactory();
        }
        catch (Exception e)
        {
            System.err.println("HibernateUtil: fallo la creacion de SessionFactory: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
