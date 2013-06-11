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

package es.urjc.ia.fia.genericSearch.statistics;

import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.State;


public interface Statistics<ACTION extends Action> {

	public void init();
	
	public void addRootState(State<ACTION> _state);
	
	public void addState(ACTION _action, State<ACTION> _parent, State<ACTION> _child);
	
	public void addDuplicateState(ACTION _action, State<ACTION> _parent, State<ACTION> _child);
	
	public void exploreState(State<ACTION> _state);
	
	public void showStatistics();
	
	public void end();
	
}
