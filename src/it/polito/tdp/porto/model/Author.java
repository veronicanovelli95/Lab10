package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.Map;

public class Author {

	private int id;
	private String lastname;
	private String firstname;
	private Map<Integer, Paper> opere= new HashMap<Integer, Paper>();
		
	public Author(int id) {
		super();
		this.id = id;
	}

	public Author(int id, String lastname, String firstname) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		return id + " " +  firstname + " " + lastname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Map<Integer, Paper> getOpere() {
		return opere;
	}

	public void addOpera(Paper p) {
		if(!opere.containsKey(p.getEprintid()))
		opere.put(p.getEprintid(), p);
	}
	
	
	
}
