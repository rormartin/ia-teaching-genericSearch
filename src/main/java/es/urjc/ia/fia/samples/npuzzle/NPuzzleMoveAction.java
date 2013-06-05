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
