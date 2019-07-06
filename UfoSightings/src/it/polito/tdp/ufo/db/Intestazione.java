package it.polito.tdp.ufo.db;

public class Intestazione {
	private int anno;
	private int avvistamenti;
	public Intestazione(int anno, int avvistamenti) {
		super();
		this.anno = anno;
		this.avvistamenti = avvistamenti;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public int getAvvistamenti() {
		return avvistamenti;
	}
	public void setAvvistamenti(int avvistamenti) {
		this.avvistamenti = avvistamenti;
	}
	@Override
	public String toString() {
		return "Intestazione [anno=" + anno + ", avvistamenti=" + avvistamenti + "]";
	}
	
	

}
