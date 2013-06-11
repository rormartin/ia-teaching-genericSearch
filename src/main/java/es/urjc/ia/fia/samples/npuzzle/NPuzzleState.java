
/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package es.urjc.ia.fia.samples.npuzzle;

import es.urjc.ia.fia.genericSearch.data.State;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author rormartin
 *
 */
public class NPuzzleState extends State<NPuzzleMoveAction> {
	
	private NPuzzleBoard initBoard;
	private NPuzzleBoard endBoard;

	
	public NPuzzleState(NPuzzleBoard _initBoard, NPuzzleBoard _endBoard) throws NPuzzleStateException {
		
		if ((_initBoard.getDimX() == _endBoard.getDimX()) &&
				(_initBoard.getDimY() == _endBoard.getDimY())) {
			this.initBoard = _initBoard;
			this.endBoard = _endBoard;
		} else {
			throw new NPuzzleStateException("Different dimensions for initial " 
					+ "board and final board");
		}
		
	}
	

	@Override
	public State<NPuzzleMoveAction> applyAction(NPuzzleMoveAction _action) throws NPuzzleStateException {

		if (!isValidAction(_action)) {
			throw new NPuzzleStateException("Invalid action for the state");
		} else {
			// General schema
			NPuzzleState newState;
			try {
				newState = (NPuzzleState) this.clone();
			} catch (CloneNotSupportedException e) {
				throw new NPuzzleStateException("Can't make a state copy");
			}
			newState.addActionToSolution(_action);
			
			newState.initBoard.move0(_action);
			
			return newState;
		}
	}



	@Override
	public boolean isValidAction(NPuzzleMoveAction _action) {
		
		switch (_action.getActionMove()) {
		case UP: return this.initBoard.get0Y() > 0;
		case DOWN: return this.initBoard.get0Y() < (this.initBoard.getDimY()-1);
		case RIGHT: return this.initBoard.get0X() < (this.initBoard.getDimX()-1);
		case LEFT: return this.initBoard.get0X() > 0;
		}
		return false;
	}



	@Override
	public Collection<NPuzzleMoveAction> getApplicableActions() {
		
		Collection<NPuzzleMoveAction> actions = new ArrayList<NPuzzleMoveAction>();
		
		for (NPuzzleMoveAction.Move actMov : NPuzzleMoveAction.Move.values()) {
			NPuzzleMoveAction action = new NPuzzleMoveAction(actMov);
			if (this.isValidAction(action)) {
				actions.add(action);
			}
		}
		return actions;
	}



	@Override
	public boolean isSolution() {
		return this.initBoard.equals(this.endBoard);
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NPuzzleState) {
			NPuzzleState nps = (NPuzzleState) obj;
			return (this.initBoard.equals(nps.initBoard)) &&
					(this.endBoard.equals(nps.endBoard));
		} else {
			return false;
		}
	}
	
	
	
	/**
	 * Clone state
	 * @throws CloneNotSupportedException 
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		NPuzzleState copy = null;
		try {
			copy = new NPuzzleState(
					(NPuzzleBoard) this.initBoard.clone(), 
					(NPuzzleBoard) this.endBoard.clone());
			copy.copyPartialSolutionFrom(this);
		} catch (NPuzzleStateException e) {
			copy = null;
		}
		return copy;
	}


	/**
	 * 
	 * @return current board
	 */
	public NPuzzleBoard getCurrentBoard() {
		return this.initBoard;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.initBoard.toString();
	}

	
	
}
