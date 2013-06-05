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
