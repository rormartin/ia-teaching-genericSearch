package es.urjc.ia.fia.genericSearch.strategy;

import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.State;
import es.urjc.ia.fia.genericSearch.strategy.openList.Stack;

public class DepthSearch<ACTION extends Action,
						 STATE extends State<ACTION>> extends
						 GenericAbstractSearch<ACTION, 
							  				   STATE,
							  				   Stack<STATE>> {
	
	@Override
	protected Stack<STATE> initEmptyQueue() {
		return new Stack<STATE>();
	}

	
}
