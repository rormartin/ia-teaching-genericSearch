package es.urjc.ia.fia.genericSearch.strategy;

import java.util.Comparator;

import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.State;
import es.urjc.ia.fia.genericSearch.strategy.openList.SortOpenList;

public class GreedyActionCostSearch<ACTION extends Action,
								    STATE extends State<ACTION>> extends
		GenericAbstractSearch<ACTION,
							  STATE,
							  SortOpenList<STATE>> {
	
	/**
	 * Standard comparator based in the state cost
	 * @author rormartin
	 *
	 */
	private class CostComparator implements Comparator<STATE> {

		@Override
		public int compare(STATE arg0, STATE arg1) {
			if (arg1.getSolutionCost() > arg0.getSolutionCost()) {
				return -1;
			} else if (arg1.getSolutionCost() < arg0.getSolutionCost()) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
	
	@Override
	protected SortOpenList<STATE> initEmptyQueue() {
		return new SortOpenList<STATE>(new CostComparator());
	}

	
}
