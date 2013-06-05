package es.urjc.ia.fia.samples.numbers;

import java.util.ArrayList;

import es.urjc.ia.fia.genericSearch.statistics.JUNGStatistics;
import es.urjc.ia.fia.genericSearch.strategy.BreadthSearch;
import es.urjc.ia.fia.genericSearch.strategy.DepthIterativeSearch;
import es.urjc.ia.fia.genericSearch.strategy.DepthSearch;
import es.urjc.ia.fia.genericSearch.strategy.GreedyActionCostSearch;
import es.urjc.ia.fia.genericSearch.strategy.GreedySearch;

public class NumberTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		class NumberBreadthSearch extends BreadthSearch<NumberAction, NumberState> {}
		class NumberDepthSearch extends DepthSearch<NumberAction, NumberState> {}
		class NumberDepthIterativeSearch extends DepthIterativeSearch<NumberAction, NumberState> {}
		class NumberGreedySearch extends GreedyActionCostSearch<NumberAction, NumberState> {}
		class NumberAStar extends GreedySearch<NumberAction,NumberState, NumberHeuristic> {			
			public NumberAStar(NumberHeuristic heu) {
				super(heu);
			}
		}

		try {
			
			int[] anums = {6, 7, 3, 5};
			int goalResult = 10;
			
			ArrayList<Integer> nums = new ArrayList<Integer>();
			for (int n : anums) { nums.add(n); }
			
			NumberState state = new NumberState(nums, goalResult);
			
			NumberBreadthSearch breadthS = new NumberBreadthSearch();
			NumberDepthSearch depthS = new NumberDepthSearch();
			NumberDepthIterativeSearch depthIS = new NumberDepthIterativeSearch();
			NumberGreedySearch greedyS = new NumberGreedySearch();
			NumberAStar astarS = new NumberAStar(new NumberHeuristic());
			
			System.out.println(state);
			
//			System.out.print("BreadthSearchFirst: ");
//			breadthS.setStadistics(new JUNGStatistics<NumberAction>());
//			System.out.println(breadthS.findFirstSolution(state));
//			System.out.println(breadthS.findAllSolutions(state));
//			breadthS.getStatistics().showStatistics();
//			
//			System.out.print("DepthSearchFirst: ");
//			depthS.setStadistics(new JUNGStatistics<NumberAction>());
//			System.out.println(depthS.findFirstSolution(state));
////			depthS.getStatistics().showStatistics();
//
//			System.out.print("DepthSearchAll: ");
//			depthS.setStadistics(new JUNGStatistics<NumberAction>());
//			System.out.println(depthS.findAllSolutions(state));
////			depthS.getStatistics().showStatistics();
////			
//			System.out.print("DepthIteSearchFirst: ");
//			depthIS.setStadistics(new JUNGStatistics<NumberAction>());
//			System.out.println(depthIS.findFirstSolution(state));
////			depthIS.getStatistics().showStatistics();
////			
//			System.out.print("GreedySearch: ");
//			greedyS.setStadistics(new JUNGStatistics<NumberAction>());
//			System.out.println(greedyS.findFirstSolution(state));
//			System.out.println(greedyS.findAllSolutions(state));
////			greedyS.getStatistics().showStatistics();
//			

			System.out.print("AStartSearch: ");
			astarS.setStadistics(new JUNGStatistics<NumberAction>());
			System.out.println(astarS.findFirstSolution(state));
			//System.out.println(astarS.findAllSolutions(state));
			astarS.getStatistics().showStatistics();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
	}
}
