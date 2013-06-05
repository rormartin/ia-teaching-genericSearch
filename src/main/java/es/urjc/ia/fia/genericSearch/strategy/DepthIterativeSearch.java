package es.urjc.ia.fia.genericSearch.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.State;

public class DepthIterativeSearch<ACTION extends Action,
								  STATE extends State<ACTION>> 
								  extends DepthSearch<ACTION, STATE> {

	public DepthIterativeSearch() {
		super();
		this.maxLevel = Integer.MAX_VALUE;
	}
	
	@Override
	public List<ACTION> findFirstSolution(STATE _initialState) throws Exception {
	
		initStatistics();
		addRootState(_initialState);
	
		for (int level = 1; level <= this.maxLevel; level++) {
			List<ACTION> solution = findFirstSolutionAux(_initialState, level);
			if ((solution != null) && (!solution.isEmpty())) {
				endStatistics();
				return solution;
			}
		}
		
		endStatistics();
		return null;
	}

	/* (non-Javadoc)
	 * @see es.urjc.ia.fia.genericSearch.strategy.GenericAbstractSearch#findAllSolutions(es.urjc.ia.fia.genericSearch.data.State)
	 */
	@Override
	public Collection<List<ACTION>> findAllSolutions(STATE _initialState)
			throws Exception {		
		
		initStatistics();
		addRootState(_initialState);
		
		Collection<List<ACTION>> solutions = new ArrayList<List<ACTION>>();
		
		for (int level = 1; level <= this.maxLevel; level++) {
			this.setLimitedLevel(level);
			Collection<List<ACTION>> partial = super.findAllSolutionsAux(_initialState, level);
			solutions.addAll(partial);
		}
		
		endStatistics();
		return solutions;	
	}
	
	
	
}
