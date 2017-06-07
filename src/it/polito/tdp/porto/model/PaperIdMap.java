package it.polito.tdp.porto.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class PaperIdMap {
	
	private Map<Integer, Paper> p= new HashMap<Integer, Paper>();

	public PaperIdMap() {
		p= new HashMap<Integer, Paper>();
	}
	
	
	
	public Paper contiene(Paper pa){
		if (p.containsKey(pa.getEprintid())){
			return p.get(pa.getEprintid());
		}
		else{
			p.put(pa.getEprintid(),pa);	
		    return pa;
		    }    
	}
	
	
	public List<Paper> values(){
		List<Paper> lista = new LinkedList(p.values());
		//lista.sort(c);
		return lista;
	}



	@Override
	public String toString() {
		return "PaperIdMap [p=" + p + "]";
	}




	
	
}
