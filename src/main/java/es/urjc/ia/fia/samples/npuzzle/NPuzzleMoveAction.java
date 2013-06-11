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

import es.urjc.ia.fia.genericSearch.data.Action;

public class NPuzzleMoveAction implements Action {

	public enum Move {UP, DOWN, RIGHT, LEFT};
	
	private Move actionMove = null;
	
	public NPuzzleMoveAction(Move _move) {
		this.actionMove = _move;
	}
	
	@Override
	public double cost() {
		return 1.0;
	}

	/**
	 * @return the actionMove
	 */
	protected Move getActionMove() {
		return actionMove;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.actionMove.name();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.actionMove.hashCode();
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NPuzzleMoveAction)) return false;

        NPuzzleMoveAction that = (NPuzzleMoveAction) o;

        if (actionMove != that.actionMove) return false;

        return true;
    }
}
