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

import java.util.LinkedList;

public class Queue<E> implements OpenList<E> {
	
	private LinkedList<E> stack = null;
	
	public Queue() {
		this.stack = new LinkedList<E>();
	}

	@Override
	public void addElement(E _element) {
		this.stack.addFirst(_element);
	}

	@Override
	public E getElement() {
		return this.stack.removeLast();
	}

	@Override
	public E peekElement() {
		return this.stack.getLast();
	}

	@Override
	public boolean isEmpty() {
		return this.stack.isEmpty();
	}

	@Override
	public int size() {
		return this.stack.size();
	}

}
