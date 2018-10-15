package com.tmi.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tmi.entities.Rutina;
import com.tmi.hbt.HibernateUtil;

public class RutinaDao extends Dao<Rutina>{

	private static Class<Rutina> CLASE_DAO= Rutina.class;
	
	public RutinaDao() {
		super(CLASE_DAO);
	}
	
	/**
	 * Si no encuentra elementos devuelve una lista vacia
	 * fuerza en la busqueda publico = true
	 * */
	@SuppressWarnings("unchecked")
	public List<Rutina> findRutinasPublicasByAttributes(Map<String,Object> attributes, boolean inclusive) {
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
			stringQuery+=" where (";
			for(String key: attributes.keySet()) {
				if(attributes.get(key) instanceof String) {
					stringQuery+=" "+key+" like :"+key+" "+conector;
				} else {
					stringQuery+=" "+key+" = :"+key+" "+conector;
				}
				
			}
			//saco el ultimo " AND" / " OR"
			stringQuery=stringQuery.substring(0, stringQuery.length() - (conector.length()+1) );
			stringQuery+=") AND publica = :publica"; 
		}
		
		Query query = session.createQuery(stringQuery);
		
		for(String key: attributes.keySet()) {
			if(attributes.get(key) instanceof String) {
				query.setParameter(key, "%"+attributes.get(key)+"%");
			} else {
				query.setParameter(key, attributes.get(key));
			}
		}
		query.setParameter("publica", true);

		List <Rutina> result = query.list();
		if(result == null){
			result = new ArrayList<>();
		}
		return result;
	}

}
