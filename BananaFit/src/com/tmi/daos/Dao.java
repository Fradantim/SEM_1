package com.tmi.daos;

import org.hibernate.SessionFactory;

import com.tmi.entities.AbsEntity;
import com.tmi.exceptions.ObjetoInexistenteException;
import com.tmi.hbt.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import org.hibernate.Session;

public class Dao<T extends AbsEntity> {
	
	protected Class<T> type;
	
	public Dao (Class<T> type){
		this.type = type;
	}

	protected Dao() {}
	
	protected String getClassName() {
		return type.getSimpleName();
	}
	
	public Integer grabar(AbsEntity entity){
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.merge(entity);
		session.getTransaction().commit();
		session.close();
		return entity.getId();
	}
	
	public void grabar(List<? extends AbsEntity> entities) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		for(AbsEntity entity: entities)
			session.merge(entity);
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
	 * */
	@SuppressWarnings("unchecked")
	public List<T> findByAttributes(Map<String,Object> attributes, boolean inclusive) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		String stringQuery = "from "+getClassName(); 
		
		String conector;
		if(inclusive) {
			conector="AND";
		} else {
			conector="OR";
		}
		
		if(attributes.size()>0) {
			stringQuery+=" where";
			for(String key: attributes.keySet()) {
				if(attributes.get(key) instanceof String) {
					stringQuery+=" "+key+" like :"+key+" "+conector;
				} else {
					stringQuery+=" "+key+" = :"+key+" "+conector;
				}
				
			}
			//saco el ultimo " AND" / " OR"
			stringQuery=stringQuery.substring(0, stringQuery.length() - (conector.length()+1) );
		}
		
		Query query = session.createQuery(stringQuery);
		
		for(String key: attributes.keySet()) {
			if(attributes.get(key) instanceof String) {
				query.setParameter(key, "%"+attributes.get(key)+"%");
			} else {
				query.setParameter(key, attributes.get(key));
			}
		}

		List <T> result = query.list();
		if(result == null){
			result = new ArrayList<>();
		}
		return result;
	}
	
	/**
	 * Si no encuentra elementos devuelve una lista vacia
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
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
