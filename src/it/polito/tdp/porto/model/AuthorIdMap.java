package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class AuthorIdMap {
	private Map<Integer, Author> a;

	public AuthorIdMap() {
		super();
		this.a = new HashMap<Integer,Author>();
	}
	
	
	public Author contiene(Author au){
		if (a.containsKey(au.getId())){
			return a.get(au.getId());
		}
		else{
			a.put(au.getId(), au);	
		return au;}
	}
	
	
	public List<Author> values(){
		List<Author> lista = new LinkedList(a.values());
		return lista;
	}


	@Override
	public String toString() {
		return  a.toString();
	}
	

}
