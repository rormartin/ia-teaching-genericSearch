package es.urjc.ia.fia.genericSearch.statistics;

import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.State;


public interface Statistics<ACTION extends Action> {

	public void init();
	
	public void addRootState(State<ACTION> _state);
	
	public void addState(ACTION _action, State<ACTION> _parent, State<ACTION> _child);
	
	public void addDuplicateState(ACTION _action, State<ACTION> _parent, State<ACTION> _child);
	
	public void exploreState(State<ACTION> _state);
	
	public void showStatistics();
	
	public void end();
	
}
