package es.urjc.ia.fia.samples.numbers;

import es.urjc.ia.fia.genericSearch.data.Action;

public class NumberAction implements Action {

	public enum Operator { SUM, MIN, MUL, DIV}; // + - * /
	
	private int n1, n2;
	private Operator op = null;
	
	public NumberAction(int _n1, Operator _op, int _n2) {
		this.n1 = _n1;
		this.n2 = _n2;
		this.op = _op;
	}
	

	@Override
	public double cost() {
		//return 1.0;
		return Math.min(this.n1, this.n2);
	}

	/**
	 * @return the n1
	 */
	public int getN1() {
		return n1;
	}

	/**
	 * @return the n2
	 */
	public int getN2() {
		return n2;
	}

	/**
	 * @return the op
	 */
	public Operator getOp() {
		return op;
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		switch (this.op) {
		case SUM: return n1 + n2;
		case MIN: return n1 - n2;
		case MUL: return n1 * n2;
		case DIV: return n1 / n2;
		default: return Integer.MIN_VALUE;
		}
	}

	@Override
	public String toString() {
		String opC = "";
		switch (this.op) {
		case SUM: opC = "+"; break;
		case MIN: opC = "-"; break;
		case MUL: opC = "*"; break;
		case DIV: opC = "/"; break;
		}
		return this.getN1() + opC + this.getN2() + "=" + this.getResult(); 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.n1 + this.n2 + this.getResult();
	}
	
	
	
}
