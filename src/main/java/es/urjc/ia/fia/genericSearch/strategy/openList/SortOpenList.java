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

import java.util.Comparator;
import java.util.PriorityQueue;

public class SortOpenList<E> implements OpenList<E> {

	private PriorityQueue<E> internalQueue = null;

	public SortOpenList(Comparator<? super E> comparator) {
		super();
		// 32? ... 
		this.internalQueue = new PriorityQueue<E>(32, comparator);		
	}
	
	@Override
	public int size() {
		return this.internalQueue.size();
	}

	@Override
	public void addElement(E _element) {
		this.internalQueue.offer(_element);
	}

	@Override
	public E getElement() {
		return this.internalQueue.poll();
	}

	@Override
	public E peekElement() {
		return this.internalQueue.peek();
	}

	@Override
	public boolean isEmpty() {
		return this.internalQueue.isEmpty();
	}
}
