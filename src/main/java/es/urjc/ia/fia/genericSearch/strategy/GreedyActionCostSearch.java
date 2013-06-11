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
import es.urjc.ia.fia.genericSearch.data.State;
import es.urjc.ia.fia.genericSearch.strategy.openList.SortOpenList;

import java.util.Comparator;

public class GreedyActionCostSearch<ACTION extends Action,
								    STATE extends State<ACTION>> extends
		GenericAbstractSearch<ACTION,
							  STATE,
							  SortOpenList<STATE>> {
	
	/**
	 * Standard comparator based in the state cost
	 * @author rormartin
	 *
	 */
	private class CostComparator implements Comparator<STATE> {

		@Override
		public int compare(STATE arg0, STATE arg1) {
			if (arg1.getSolutionCost() > arg0.getSolutionCost()) {
				return -1;
			} else if (arg1.getSolutionCost() < arg0.getSolutionCost()) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
	
	@Override
	protected SortOpenList<STATE> initEmptyQueue() {
		return new SortOpenList<STATE>(new CostComparator());
	}

	
}
