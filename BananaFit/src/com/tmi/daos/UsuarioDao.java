package com.tmi.daos;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tmi.entities.Usuario;
import com.tmi.hbt.HibernateUtil;

public class UsuarioDao extends Dao<Usuario>{

	private static Class<Usuario> CLASE_DAO= Usuario.class;
	
	public UsuarioDao() {
		super(CLASE_DAO);
	}
	
	public boolean existeUsuario(String username) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query query = session.createQuery("from "+getClassName()+" where user = ?")
					.setParameter(0, username);
	    
		return (query.uniqueResult() != null);
	}

}
