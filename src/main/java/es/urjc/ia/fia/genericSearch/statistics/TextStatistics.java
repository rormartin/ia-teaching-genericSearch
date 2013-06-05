package es.urjc.ia.fia.genericSearch.statistics;

import java.util.Date;

import es.urjc.ia.fia.genericSearch.data.Action;
import es.urjc.ia.fia.genericSearch.data.State;

public class TextStatistics<ACTION extends Action> implements Statistics<ACTION> {
	
	protected long totalStates = 0;
	protected long explorerStates = 0;
	protected long duplicatedStates = 0;
	protected long solutionStates = 0;
	protected Date initTime = null;
	protected Date endTime = null;

	@Override
	public void init() {
		this.totalStates = 0;
		this.explorerStates = 0;
		this.duplicatedStates = 0;
		this.solutionStates = 0;
		this.initTime = new Date();
	}

	@Override
	public void addRootState(State<ACTION> _state) {
		this.totalStates++;
	}

	@Override
	public void addState(ACTION _action, State<ACTION> _parent,
			State<ACTION> _child) {
		this.totalStates++;
	}

	@Override
	public void addDuplicateState(ACTION _action, State<ACTION> _parent,
			State<ACTION> _child) {
		this.duplicatedStates++;
	}

	@Override
	public void exploreState(State<ACTION> _state) {
		this.explorerStates++;
		if (_state.isSolution()) {
			this.solutionStates++;
		}
	}

	@Override
	public void showStatistics() {
		String results = "";
		results += "Total States: " + totalStates;
		results += System.getProperty ( "line.separator" );
		results += "Explored States: " + explorerStates;
		results += System.getProperty ( "line.separator" );
		results += "Duplicated States (detected): " + duplicatedStates;
		results += System.getProperty ( "line.separator" );
		results += "Solution States: " + solutionStates;
		results += System.getProperty ( "line.separator" );
		long totalTime = endTime.getTime() - initTime.getTime();
		results += "Total time: " + totalTime + " milliseconds";
		results += System.getProperty ( "line.separator" );
		
		System.out.println(results);
	}

	@Override
	public void end() {
		this.endTime = new Date();
	}

}
