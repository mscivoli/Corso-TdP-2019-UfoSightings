package it.polito.tdp.ufo.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.ufo.db.SightingsDAO;


public class Model {
	
	SightingsDAO dao = new SightingsDAO();
	private Graph<String, DefaultEdge> grafo;
	private List<String> stati;
	private List<String> best;
	

	public void creaGrafo(int anno) {
		this.grafo = new SimpleDirectedGraph<>(DefaultEdge.class);
		this.stati = dao.getStati(anno);
		
		Graphs.addAllVertices(grafo, stati);
		
		for(String s1 : grafo.vertexSet()) {
			for(String s2 : grafo.vertexSet()) {
				if(!s1.equals(s2) && dao.esisteArco(s1, s2, anno)) {
					grafo.addEdge(s1, s2);
				}
			}
		}
		
		
	}


	public Graph<String, DefaultEdge> getGrado() {
		// TODO Auto-generated method stub
		return this.grafo;
	}


	public List<String> ottieniPercorsoMigliore(String string) {
		this.best = new LinkedList<String>();
		List<String> parziale = new LinkedList<String>();
		Set<String> considerati = new HashSet<String>();
		parziale.add(string);
		
		ricorsione(parziale, considerati, string);
		
		return best;
		
	}
	
	
	public void ricorsione(List<String> parziale, Set<String> considerati, String s) {
		
		considerati.add(s);
		
		if(parziale.size()>best.size()) {
			best = new LinkedList<String>(parziale);
		}
		
		List<String> valoriPossibili = new LinkedList<String>();
		for(String stemp : valoriPossibili) {
			if(!considerati.contains(stemp)) {
				//provo ad agiungerlo
				parziale.add(stemp);
				ricorsione(parziale, considerati, stemp);
				parziale.remove(parziale.size()-1);
			}
		}
	}

}
