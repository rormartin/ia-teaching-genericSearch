package es.urjc.ia.fia.genericSearch.data;

public interface Action {

	/**
	 * Action cost
	 * @return
	 */
	public double cost();
	
	public int hashCode();
	
	public String toString();
	
}
