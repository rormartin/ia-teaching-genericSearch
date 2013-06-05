package es.urjc.ia.fia.genericSearch.strategy.openList;

import java.util.LinkedList;

public class Stack<E> implements OpenList<E> {
	
	private LinkedList<E> stack = null;
	
	public Stack() {
		this.stack = new LinkedList<E>();
	}

	@Override
	public void addElement(E _element) {
		this.stack.addFirst(_element);
	}

	@Override
	public E getElement() {
		return this.stack.removeFirst();
	}

	@Override
	public E peekElement() {
		return this.stack.getFirst();
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
