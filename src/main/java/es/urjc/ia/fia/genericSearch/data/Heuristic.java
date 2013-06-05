package es.urjc.ia.fia.genericSearch.data;


public interface Heuristic<ACTION extends Action, STATE extends State<ACTION>> {
	
	public double hstart(STATE _state);
	
}
