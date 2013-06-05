package es.urjc.ia.fia.genericSearch.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.State;
import es.urjc.ia.fia.genericSearch.statistics.Statistics;
import es.urjc.ia.fia.genericSearch.strategy.openList.OpenList;

public abstract class GenericAbstractSearch<ACTION extends Action,
											STATE extends State<ACTION>,
											OLIST extends OpenList<STATE>> 
								   			implements Search<ACTION, STATE>  {

	// Level for depth limited strategy
	protected int maxLevel = 0;
	
	// For statistics
	private Statistics<ACTION> statistics = null;

	
	/**
	 * Init the custom queue
	 * @return empty queue
	 */
	protected abstract OLIST initEmptyQueue();
	
	
	
	
	/**
	 * 
	 * @see es.urjc.ia.fia.genericSearch.statistics.Statistics#init()
	 */
	protected void initStatistics() {
		if (this.statistics != null) {
			statistics.init();
		}
	}
	
	

	/**
	 * 
	 * @see es.urjc.ia.fia.genericSearch.statistics.Statistics#end()
	 */
	protected void endStatistics() {
		if (this.statistics != null) {
			statistics.end();
		}
	}




	/**
	 * @param _state
	 * @see es.urjc.ia.fia.genericSearch.statistics.Statistics#addRootState(es.urjc.ia.fia.genericSearch.data.State)
	 */
	protected void addRootState(State<ACTION> _state) {
		if (this.statistics != null) {
			statistics.addRootState(_state);
		}
	}



	/**
	 * @param _action
	 * @param _parent
	 * @param _child
	 * @see es.urjc.ia.fia.genericSearch.statistics.Statistics#addState(es.urjc.ia.fia.genericSearch.data.Action, es.urjc.ia.fia.genericSearch.data.State, es.urjc.ia.fia.genericSearch.data.State)
	 */
	protected void addState(ACTION _action, State<ACTION> _parent,
			State<ACTION> _child) {
		if (this.statistics != null) {
			statistics.addState(_action, _parent, _child);
		}
	}


	/**
	 * @param _action
	 * @param _parent
	 * @param _child
	 * @see es.urjc.ia.fia.genericSearch.statistics.Statistics#addDuplicateState(es.urjc.ia.fia.genericSearch.data.Action, es.urjc.ia.fia.genericSearch.data.State, es.urjc.ia.fia.genericSearch.data.State)
	 */
	protected void addDuplicateState(ACTION _action, State<ACTION> _parent,
			State<ACTION> _child) {
		if (this.statistics != null) {
			statistics.addDuplicateState(_action, _parent, _child);
		}
	}


	/**
	 * @param _state
	 * @see es.urjc.ia.fia.genericSearch.statistics.Statistics#exploreState(es.urjc.ia.fia.genericSearch.data.State)
	 */
	protected void exploreState(State<ACTION> _state) {
		if (this.statistics != null) {
			statistics.exploreState(_state);
		}
	}


	/**
	 * Generate all valid actions for the input state, generate all the valid 
	 * states with this action and put its on the queue
	 * @param _state
	 * @param _openList
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	protected final void expand(State<ACTION> _state, 
						  OLIST _openList,
						  Collection<State<ACTION>> _visited,
						  int _limit) throws Exception {
		
		// For limited search or no limited
		if (((_limit > 0) && (_state.getStateLevel() < _limit)) ||
			(_limit < 1)) {
			Collection<ACTION> alternatives = _state.getApplicableActions();
			for (ACTION act : alternatives) {
				State<ACTION> newState = _state.applyAction(act);
				if (!_visited.contains(newState)) {
					_openList.addElement((STATE) newState);
					// Don't add to visited if the state is a solution state
					if (!newState.isSolution()) {
						_visited.add(newState);
					}
					this.addState(act, _state, newState);
				} else {
					// Store for statistics duplicate states
					this.addDuplicateState(act, _state, newState);
				}
			}		
		} 
		// out of limit, no action
	}

	/**
	 * Aux function for solution
	 * @param _initialState
	 * @return
	 * @throws Exception
	 */
	protected final List<ACTION> findFirstSolutionAux(STATE _initialState, int _level) throws Exception {

		Collection<State<ACTION>> visited = new ArrayList<State<ACTION>>();
		
		OLIST openList = initEmptyQueue();
		openList.addElement(_initialState);
		
		while (!openList.isEmpty()) {
			State<ACTION> currentState = openList.getElement();
			this.exploreState(currentState);
			if (currentState.isSolution()) {
				return currentState.getPartialSolution();
			} else {
				expand(currentState, openList, visited, _level);
			}
		}
		// With no solution
		return null;
	}

	
	@Override
	public List<ACTION> findFirstSolution(STATE _initialState) throws Exception {

		this.initStatistics();
		this.addRootState(_initialState);
		
		List<ACTION> solution = this.findFirstSolutionAux(_initialState, this.maxLevel);
		this.endStatistics();
		return solution;
	}

	/**
	 * Aux function for search
	 * @param _initialState
	 * @return
	 * @throws Exception
	 */
	protected final Collection<List<ACTION>> findAllSolutionsAux(
			STATE _initialState, int _level) throws Exception {
		
		Collection<List<ACTION>> solutions = new ArrayList<List<ACTION>>();
		
		Collection<State<ACTION>> visited = new ArrayList<State<ACTION>>();
		
		OLIST openList = initEmptyQueue();
		openList.addElement(_initialState);
		while (!openList.isEmpty()) {
			STATE currentState = openList.getElement();
			this.exploreState(currentState);
			if (currentState.isSolution()) {
				solutions.add(currentState.getPartialSolution());
			} else {
				expand(currentState, openList, visited, _level);
			}
		}	
		return solutions;	
	}
	
	@Override
	public Collection<List<ACTION>> findAllSolutions(
			STATE _initialState) throws Exception {

		this.initStatistics();
		this.addRootState(_initialState);
		
		Collection<List<ACTION>> solutions = this.findAllSolutionsAux(_initialState, this.maxLevel);

		this.endStatistics();
		return solutions;	
	}

	/**
	 * Set the maximum level to explore. 0 (default) for no limit.
	 * @param _maxLevel
	 */
	public void setLimitedLevel(int _maxLevel) {
		this.maxLevel = _maxLevel;
	}

	/**
	 * @return the statistics
	 */
	public Statistics<ACTION> getStatistics() {
		return statistics;
	}

	/**
	 * @param statistics the statistics to set
	 */
	public void setStadistics(Statistics<ACTION> statistics) {
		this.statistics = statistics;
	}
	
	
		
}
