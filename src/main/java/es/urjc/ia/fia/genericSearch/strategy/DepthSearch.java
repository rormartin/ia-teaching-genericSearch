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
import es.urjc.ia.fia.genericSearch.strategy.openList.Stack;

public class DepthSearch<ACTION extends Action,
						 STATE extends State<ACTION>> extends
						 GenericAbstractSearch<ACTION, 
							  				   STATE,
							  				   Stack<STATE>> {
	
	@Override
	protected Stack<STATE> initEmptyQueue() {
		return new Stack<STATE>();
	}

	
}
