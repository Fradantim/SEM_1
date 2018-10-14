package com.tmi.hbt;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.tmi.entities.Ejercicio;
import com.tmi.entities.NIF;

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
        	 //config.addAnnotatedClass(NIF.class);
        	 config.addAnnotatedClass(Ejercicio.class);
             //TODO Agregar todas las entities----
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
