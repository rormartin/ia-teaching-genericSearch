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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DepthIterativeSearch<ACTION extends Action,
								  STATE extends State<ACTION>> 
								  extends DepthSearch<ACTION, STATE> {

	public DepthIterativeSearch() {
		super();
		this.maxLevel = Integer.MAX_VALUE;
	}
	
	@Override
	public List<ACTION> findFirstSolution(STATE _initialState) throws Exception {
	
		initStatistics();
		addRootState(_initialState);
	
		for (int level = 1; level <= this.maxLevel; level++) {
			List<ACTION> solution = findFirstSolutionAux(_initialState, level);
			if ((solution != null) && (!solution.isEmpty())) {
				endStatistics();
				return solution;
			}
		}
		
		endStatistics();
		return null;
	}

	/* (non-Javadoc)
	 * @see es.urjc.ia.fia.genericSearch.strategy.GenericAbstractSearch#findAllSolutions(es.urjc.ia.fia.genericSearch.data.State)
	 */
	@Override
	public Collection<List<ACTION>> findAllSolutions(STATE _initialState)
			throws Exception {		
		
		initStatistics();
		addRootState(_initialState);
		
		Collection<List<ACTION>> solutions = new ArrayList<List<ACTION>>();
		
		for (int level = 1; level <= this.maxLevel; level++) {
			this.setLimitedLevel(level);
			Collection<List<ACTION>> partial = super.findAllSolutionsAux(_initialState, level);
			solutions.addAll(partial);
		}
		
		endStatistics();
		return solutions;	
	}
	
	
	
}
