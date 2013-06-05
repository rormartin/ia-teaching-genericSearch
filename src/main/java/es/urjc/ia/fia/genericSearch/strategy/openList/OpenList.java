package es.urjc.ia.fia.genericSearch.strategy.openList;

/**
 * Generic open list interface 
 * @author rormartin
 *
 */
public interface OpenList<E> {

	/**
	 * Add new element to the list
	 * @param _element
	 * @return
	 */
	public void addElement(E _element);
	
	/**
	 * Get a element and remove it from the list
	 * @return
	 */
	public E getElement();
	
	/**
	 * Get a element without remove it from the list
	 * @return
	 */
	public E peekElement();
	
	/**
	 * Empty list
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * Elements in the list
	 * @return
	 */
	public int size();
}
