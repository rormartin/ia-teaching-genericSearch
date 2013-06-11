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

package es.urjc.ia.fia.samples.numbers;

import es.urjc.ia.fia.genericSearch.data.Heuristic;

public class NumberHeuristic implements Heuristic<NumberAction, NumberState> {

	@Override
	public double hstart(NumberState _state) {
		int diff = Integer.MAX_VALUE;
		for (int num : _state.getNumbers()) {
			if (Math.abs(num - _state.getGoalResult()) < diff) {
				diff = Math.abs(num - _state.getGoalResult());
			}
		}
		return diff;
	}

}
