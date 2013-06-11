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
