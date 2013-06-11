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

package es.urjc.ia.fia.genericSearch.strategy;

import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.Heuristic;
import es.urjc.ia.fia.genericSearch.data.State;
import es.urjc.ia.fia.genericSearch.strategy.openList.SortOpenList;

import java.util.Comparator;

public class AStarSearch<ACTION extends Action, 
						  STATE extends State<ACTION>,
						  HEURISTIC extends Heuristic<ACTION, STATE>> extends
						  GenericAbstractSearch<ACTION, 
						  					    STATE,
						  					    SortOpenList<STATE>> {
	
	private HEURISTIC heuristic = null;
	
	
	/**
	 * Standard comparator based in the heuristic
	 * @author rormartin
	 *
	 */
	private class HComparator implements Comparator<STATE> {

		@Override
		public int compare(STATE arg0, STATE arg1) {
			double f0 = arg0.getSolutionCost() + heuristic.hstart(arg0);
			double f1 = arg1.getSolutionCost() + heuristic.hstart(arg1);
			
			if (f1 > f0) {
				return -1;
			} else if (f0 > f1) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
	
	public AStarSearch(HEURISTIC _heuristic) {
		this.heuristic = _heuristic;		
	}
	
	@Override
	protected SortOpenList<STATE> initEmptyQueue() {
		return new SortOpenList<STATE>(new HComparator());
	}

	
}
