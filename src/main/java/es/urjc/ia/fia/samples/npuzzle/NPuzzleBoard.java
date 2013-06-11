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

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class NPuzzleBoard {
	
	private int[][] board = null;
	private int dimX, dimY;
	
	/**
	 * Internal, for clone
	 * @param _dimX
	 * @param _dimY
	 */
	private NPuzzleBoard(int _dimX, int _dimY) {
		this.dimX = _dimX;
		this.dimY = _dimY;
	}
	
	public NPuzzleBoard (int _dimX, int _dimY, int[][] _board) throws NPuzzleBoardException {
		
		this.dimX = _dimX;
		this.dimY = _dimY;
		
		// Test dimensions
		if (_board.length != this.dimY) {
			throw new NPuzzleBoardException("Error in dimensions: " 
					+ dimY + " declared, " 
					+ _board.length + " obtained");
		}
		for (int[] row : _board) {
			if (row.length != this.dimX) {
				throw new NPuzzleBoardException("Error in dimensions: " 
						+ dimX + " declared, " 
						+ row.length + " obtained");
			}
		}
		
		// Test numbers: from 0 (hole) to (dimX*dimY)-1
		Set<Integer> allNumbers = new HashSet<Integer>(dimX*dimY);
		// add numbers to the set
		for (int n = 0; n < dimX*dimY ; n++) {
			allNumbers.add(n);
		}
		
		// Test for numbers
		for (int[] row : _board) {
			for (int n : row) {
				allNumbers.remove(n);
			}
		}
		
		// If remove all numbers, the set will be empty
		if (!allNumbers.isEmpty()) {
			throw new NPuzzleBoardException("Wrong numbers in board: " 
					+ allNumbers.toString());
		}
		
		// All OK
		this.board = _board;
	}

	/**
	 * @return the dimX
	 */
	public int getDimX() {
		return dimX;
	}

	/**
	 * @return the dimY
	 */
	public int getDimY() {
		return dimY;
	}
	
	/**
	 * Return the X position of '0'
	 * @return
	 */
	public int get0X() {
		
		for (int y = 0; y < this.board.length; y++) {
			for (int x = 0; x < this.board[y].length; x++) {
				if (this.board[y][x] == 0) {
					return x;
				}
			}
		}
		// Error
		return Integer.MIN_VALUE;
	}
	
	/**
	 * Return the Y position of '0'
	 * @return
	 */
	public int get0Y() {
		
		for (int y = 0; y < this.board.length; y++) {
			for (int x = 0; x < this.board[y].length; x++) {
				if (this.board[y][x] == 0) {
					return y;
				}
			}
		}
		// Error
		return Integer.MIN_VALUE;
	}
	
	
	public void move0(NPuzzleMoveAction _action) {
		int x = this.get0X();
		int y = this.get0Y();
		int newx = x;
		int newy = y;
		
		switch (_action.getActionMove()) {
		case UP: newy--; break;
		case DOWN: newy++; break;
		case RIGHT: newx++; break;
		case LEFT: newx--; break;
		}
		
		int temp = this.board[newy][newx];
		this.board[newy][newx] = 0;
		this.board[y][x] = temp;
	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String repre = "";

		for (int[] row : this.board) {
			repre += "[";
			for (int n : row) {
				repre += " " + n;
			}
			repre += " ]\n";
		}
		return repre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		NPuzzleBoard newBoard = new NPuzzleBoard(this.dimX, this.dimY);
		newBoard.board = new int[this.dimY][this.dimX];
		
		// Copy array values
		for (int y = 0; y < this.board.length; y++) {
			for (int x = 0; x < this.board[y].length; x++) {
				newBoard.board[y][x] = this.board[y][x];
			}
		}
		
		return newBoard;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NPuzzleBoard) {
			NPuzzleBoard other = (NPuzzleBoard)obj;
			for (int y = 0; y < this.board.length; y++) {
				for (int x = 0; x < this.board[y].length; x++) {
					if (other.board[y][x] != this.board[y][x]) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	
	// For Manhattan distance
	private Point getPosition(int num) {
		for (int y = 0; y < this.board.length; y++) {
			for (int x = 0; x < this.board[y].length; x++) {
				if (this.board[y][x] == num) {
					return new Point(x,y);
				}
			}
		}
		return null;
	}
	
	/**
	 * Calculate the Manhattan distance for all numbers
	 * @param other
	 * @return
	 */
	public int getManhattanDistance(NPuzzleBoard other) {
		
		int total = 0;
		
		for (int y = 0; y < this.board.length; y++) {
			for (int x = 0; x < this.board[y].length; x++) {
				Point p1 = new Point(x,y);
				Point p2 = this.getPosition(this.board[y][x]);
				total += p1.distanceSq(p2);					
			}
		}
		
		return total;
	}
	
	
}
