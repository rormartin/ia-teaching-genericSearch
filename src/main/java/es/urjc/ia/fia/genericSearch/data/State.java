package es.urjc.ia.fia.genericSearch.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class State<ACTION extends Action> {
	
	/**
	 * Partial list of solution
	 */
	private List<ACTION> solution;

	/**
	 * Generic constructor. Just initial the solution list.
	 */
	public State() {
		this.solution = new ArrayList<ACTION>();
	}
	
	
	/**
	 * Generate a new state apply the action to the current state.
	 * You can only use this method if <i>isValidAction</i> returns 
	 * <i>true</i> for the same action.
	 * @param _action action to apply
	 */
	public abstract State<ACTION> applyAction(ACTION _action) throws Exception;
	
	
	/**
	 * Return the partial solution. If <i>isSolution</i> is true, this is the
	 * solution.
	 * @return partial solution
	 */
	public final List<ACTION> getPartialSolution() {
		return this.solution;
	}
	
	/**
	 * Return the cost of the solution
	 * @return
	 */
	public final double getSolutionCost() {
		double cost = 0.0;
		for (Action act : this.solution) {
			cost += act.cost();
		}
		return cost;
	}

	
	/**
	 * Determine if the action is valid for the current state
	 * @param _action
	 * @return
	 */
	public abstract boolean isValidAction(ACTION _action);
	
	
	/**
	 * Return a list of valid actions for the current state. Implements 
	 * generating all possible action and  verify if this action is valid
	 * for the current state with the <i>isValidAction</i> method.
	 * @return set of valid action for the current state
	 */
	public abstract Collection<ACTION> getApplicableActions(); 
	
	
	/**
	 * Determine if the current state is a solution state
	 * @return
	 */
	public abstract boolean isSolution();


	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public abstract Object clone() throws CloneNotSupportedException;

	
	/**
	 * Method to use in the clone specific implementation: 
	 * copy the solution.
	 * @param _state
	 */
	protected void copyPartialSolutionFrom(State<ACTION> _state) {
		this.solution = new ArrayList<ACTION>();
		this.solution.addAll(_state.getPartialSolution());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public abstract boolean equals(Object obj);
	
	/**
	 * 
	 * @return
	 */
	public final int getStateLevel() {
		return this.solution.size();
	}
	
	/**
	 * Add new action to the solution
	 * @param _action
	 * @return
	 */
	protected final boolean addActionToSolution(ACTION _action) {
		return this.solution.add(_action);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hc = 0;
		for (ACTION act : this.solution) {
			hc += act.hashCode();
		}
		return hc;
	}
	
	

}
