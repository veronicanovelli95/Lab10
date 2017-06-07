package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	private PaperIdMap p;
	private AuthorIdMap a;
	private PortoDAO dao = new PortoDAO();
	private SimpleGraph<Author,DefaultEdge> graph = null;
	private SimpleGraph<Paper,DefaultEdge> graph2 = null;
	private List<Author> listaProvvisoria;
	private List<DefaultEdge> pathEdgeList=null;
	
	public Model() {
		p= new PaperIdMap() ;
		a = new AuthorIdMap();
	}

	
	public 	void creaGrafo(){
		graph = new SimpleGraph<Author, DefaultEdge>(DefaultEdge.class);
		
        Graphs.addAllVertices(graph, a.values());
      
		for(Author autore: a.values()){
		    for(Paper pa : autore.getOpere().values()){
               for(Author au: pa.getAutori().values()){
            	   if(!autore.equals(au)){
            	     graph.addEdge(autore, au);}
		       }      
	        }  
		}   
	 }
	    
	//trovo cammino minimo e poi ogni volta che attraverso il grafo controllo l'articolo
	 public List<Paper> cammino(Author partenza, Author arrivo){
		 
		 List<Paper> articoli = new LinkedList<Paper>();
		//sfrutto gli algoritmi per i cammini MINIMI 
	     DijkstraShortestPath<Author, DefaultEdge> d = new DijkstraShortestPath<Author, DefaultEdge>(graph, partenza, arrivo);
	     pathEdgeList = d.getPathEdgeList();
	     
	     Author a1;
	     Author a2; 
	    if( pathEdgeList!= null) {
	    	System.out.println(d.getPathLength());
	     for(DefaultEdge arco: pathEdgeList){
	    	a1= graph.getEdgeSource(arco);
	    	a2 = graph.getEdgeTarget(arco);
	        for(Paper p  : a1.getOpere().values()){
	    	   if(a2.getOpere().containsKey(p.getEprintid())){
	    		  articoli.add(p);
	    		  break;
	    	   }
	        }
	     }
	     
	    }
	    
         return articoli;
    
	 
	 } 
	
	
     public List<Author> trovaCoautori(Author autor){
	    listaProvvisoria = Graphs.neighborListOf(graph, autor);
	    return listaProvvisoria;	
	 }
	    

     public List<Author> popolaSecondaTendina(Author autor){
    	 List<Author> list = new ArrayList(a.values());
    	 list.remove(autor);
    	 list.removeAll(listaProvvisoria);
         return list;
     }

	
	
	public Collection<Paper> getOpere(){
		return p.values();
	}
	
	public Collection<Author> getAutori(){
		return a.values();
	}

	public void popola() {
		dao.getConnessioni(p,a);
		creaGrafo();
		
		
	}
	
	
}
