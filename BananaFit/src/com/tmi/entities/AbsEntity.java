package com.tmi.entities;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbsEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="ID")
	protected Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Reimplementado exclusivamente con proposito de test
	 */
	@Override
	public String toString() {
		String out="";
		try {
		for (Method method : this.getClass().getMethods()) {
			if(method.getName().startsWith("get") && method.getParameterTypes().length==0 && isAllowedType(method.getReturnType()))
		         out+="\n\t"+method.getName()+": "+String.valueOf(method.invoke(this))+"; ";
		}
		}
		catch (Exception e) { ; }
		return out;
	}
	
	private boolean isAllowedType(Class<?> clase){
		if(clase.equals(Integer.class) || clase.equals(String.class) || clase.equals(int.class) || clase.equals(Date.class))
			return true;
		return false;
	}
}
