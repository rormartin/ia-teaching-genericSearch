package es.urjc.ia.fia.genericSearch.strategy;

import java.util.Collection;
import java.util.List;

import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.State;

public interface Search<ACTION extends Action, STATE extends State<ACTION>> {
	
	public List<ACTION> findFirstSolution(STATE _initialState) throws Exception;

	public Collection<List<ACTION>> findAllSolutions(STATE _initialState) throws Exception;

}
