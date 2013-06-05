package es.urjc.ia.fia.samples.npuzzle;

import es.urjc.ia.fia.genericSearch.data.Heuristic;

/**
 * Based in the Manhattan distance
 * 
 * @author rormartin
 *
 */
public class NPuzzleHeuristic implements Heuristic<NPuzzleMoveAction, NPuzzleState> {
	
	private NPuzzleBoard finalBoard = null;
	
	public NPuzzleHeuristic (NPuzzleBoard _finalBoard) {
		this.finalBoard = _finalBoard;
	}
	
	
	@Override
	public double hstart(NPuzzleState _state) {
		return _state.getCurrentBoard().getManhattanDistance(finalBoard);
	}


}
