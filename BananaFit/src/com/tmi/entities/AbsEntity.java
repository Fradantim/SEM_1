package com.tmi.entities;

import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
			if(method.getName().startsWith("get") && method.getParameterTypes().length==0)
		         out+="\n\t"+method.getName()+": "+String.valueOf(method.invoke(this))+"; ";
		}
		}
		catch (Exception e) { ; }
		return out;
	}
}
