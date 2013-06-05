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
