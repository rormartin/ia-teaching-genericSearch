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
