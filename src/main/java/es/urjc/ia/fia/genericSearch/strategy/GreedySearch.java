package es.urjc.ia.fia.genericSearch.strategy;

import java.util.Comparator;

import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.Heuristic;
import es.urjc.ia.fia.genericSearch.data.State;
import es.urjc.ia.fia.genericSearch.strategy.openList.SortOpenList;

public class GreedySearch<ACTION extends Action, 
						  STATE extends State<ACTION>,
						  HEURISTIC extends Heuristic<ACTION, STATE>> extends
						  GenericAbstractSearch<ACTION, 
						  					    STATE,
						  					    SortOpenList<STATE>> {
	
	private HEURISTIC heuristic = null;
	
	
	/**
	 * Standard comparator based in the heuristic
	 * @author rormartin
	 *
	 */
	private class HComparator implements Comparator<STATE> {

		@Override
		public int compare(STATE arg0, STATE arg1) {
			if (heuristic.hstart(arg1) > heuristic.hstart(arg0)) {
				return -1;
			} else if (heuristic.hstart(arg0) > heuristic.hstart(arg1)) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
	
	public GreedySearch(HEURISTIC _heuristic) {
		this.heuristic = _heuristic;		
	}
	
	@Override
	protected SortOpenList<STATE> initEmptyQueue() {
		return new SortOpenList<STATE>(new HComparator());
	}

	
}
