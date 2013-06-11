/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
