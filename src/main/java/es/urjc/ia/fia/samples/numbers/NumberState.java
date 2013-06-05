package es.urjc.ia.fia.samples.numbers;

import java.util.ArrayList;
import java.util.Collection;

import es.urjc.ia.fia.genericSearch.data.State;

public class NumberState extends State<NumberAction> {
	
	private Collection<Integer> numbers = null;
	private int goalResult;

	
	public NumberState(Collection<Integer> _numbers, int _result) {
		super();
		this.numbers = _numbers;
		this.goalResult = _result;
	}
	
	@Override
	public State<NumberAction> applyAction(NumberAction _action)
			throws Exception {

		NumberState newState = (NumberState) this.clone();
		if (this.isValidAction(_action)) {
			newState.addActionToSolution(_action);

			newState.numbers.remove(_action.getN1());
			newState.numbers.remove(_action.getN2());
			newState.numbers.add(_action.getResult());
		}
		return newState;
	}

	@Override
	public boolean isValidAction(NumberAction _action) {
		switch (_action.getOp()) {
		case SUM: return true;
		case MIN: return _action.getN1() > _action.getN2();
		case MUL: return true;
		case DIV: return (_action.getN2() > 0) && 
						 ((_action.getN1() % _action.getN2()) == 0) &&
						 ((_action.getN1() / _action.getN2()) > 0);
		default: return false;
		}
	}

	@Override
	public Collection<NumberAction> getApplicableActions() {
		
		Collection<NumberAction> alternatives = new ArrayList<NumberAction>();

		Collection<Integer> internal = new ArrayList<Integer>();
		internal.addAll(this.numbers);
		
		NumberAction act = null;
		
		// For + and *
		for (Integer num1 : this.numbers) {
			internal.remove(num1);
			for (Integer num2 : internal) {
				act = new NumberAction(num1, NumberAction.Operator.SUM, num2);
				if (this.isValidAction(act)) {
					alternatives.add(act);
				}
				act = new NumberAction(num1, NumberAction.Operator.MUL, num2);
				if (this.isValidAction(act)) {
					alternatives.add(act);
				}
			}
		}
		
		// For - and /
		for (Integer num1 : this.numbers) {
			internal = new ArrayList<Integer>();
			internal.addAll(numbers);
			internal.remove(num1);
			for (Integer num2 : internal) {
				act = new NumberAction(num1, NumberAction.Operator.MIN, num2);
				if (this.isValidAction(act)) {
					alternatives.add(act);
				}
				act = new NumberAction(num1, NumberAction.Operator.DIV, num2);
				if (this.isValidAction(act)) {
					alternatives.add(act);
				}
			}
		}
		
		return alternatives;
	}

	@Override
	public boolean isSolution() {
		return this.numbers.contains(this.goalResult);
	}

	
	
	
	/**
	 * @return the numbers
	 */
	public Collection<Integer> getNumbers() {
		return numbers;
	}

	/**
	 * @return the goalResult
	 */
	public int getGoalResult() {
		return goalResult;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		NumberState copy = null;
		Collection<Integer> ncopy = new ArrayList<Integer>();
		ncopy.addAll(this.numbers);
		copy = new NumberState(ncopy, this.goalResult);
		copy.copyPartialSolutionFrom(this);

		return copy;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NumberState) {
			NumberState other = (NumberState) obj;
			return (this.goalResult == other.goalResult) &&
					(this.numbers.containsAll(other.numbers) &&
					(other.numbers.containsAll(this.numbers)));
		} else {
			return false;
		}
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "numbers: " + this.numbers + " goal: " + this.goalResult;
	}

	
}
