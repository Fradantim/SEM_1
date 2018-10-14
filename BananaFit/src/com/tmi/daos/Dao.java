package com.tmi.daos;

import org.hibernate.SessionFactory;

import com.tmi.entities.AbsEntity;
import com.tmi.exceptions.ObjetoInexistenteException;
import com.tmi.hbt.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

public class Dao<T extends AbsEntity> {
	
	private Class<T> type;
	
	public Dao (Class<T> type){
		this.type = type;
	}

	private String getClassName() {
		return type.getSimpleName();
	}
	
	public Integer grabar(AbsEntity entity){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		return entity.getId();
	}
	
	public void grabar(List<? extends AbsEntity> entities) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		for(AbsEntity entity: entities)
			session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public T getById(Integer id) throws ObjetoInexistenteException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		T entity = (T) session.createQuery("from "+getClassName()+" where id = ?")
					.setParameter(0, id).uniqueResult();
		
		if(entity != null){
			return entity;
		}
		else 
			throw new ObjetoInexistenteException("No existe un "+getClassName()+" con id "+ id);
	}
	
	/**
	 * Si no encuentra elementos devuelve una lista vacia
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		System.out.println(getClassName());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		List<T> list = session.createQuery("from "+getClassName()).list();

		if(list == null) {
			return new ArrayList<>();
		}
		return list;
	}
	
	/**
	 * Borrado fisico
	 * @param entity
	 */
	public void borrar(AbsEntity entity){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}
}
