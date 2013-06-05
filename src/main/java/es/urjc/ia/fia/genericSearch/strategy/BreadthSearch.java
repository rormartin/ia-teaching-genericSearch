package es.urjc.ia.fia.genericSearch.strategy;

import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.State;
import es.urjc.ia.fia.genericSearch.strategy.openList.Queue;

public class BreadthSearch<ACTION extends Action,
						   STATE extends State<ACTION>> extends
		GenericAbstractSearch<ACTION,
							  STATE,
							  Queue<STATE>> {

	@Override
	protected Queue<STATE> initEmptyQueue() {
		return new Queue<STATE>();
	}

	
}
