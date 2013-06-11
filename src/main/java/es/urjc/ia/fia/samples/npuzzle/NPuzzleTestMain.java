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

import es.urjc.ia.fia.genericSearch.statistics.JUNGStatistics;
import es.urjc.ia.fia.genericSearch.strategy.AStarSearch;
import es.urjc.ia.fia.genericSearch.strategy.GreedySearch;
import es.urjc.ia.fia.genericSearch.strategy.BreadthSearch;
import es.urjc.ia.fia.genericSearch.strategy.DepthIterativeSearch;
import es.urjc.ia.fia.genericSearch.strategy.DepthSearch;
import es.urjc.ia.fia.genericSearch.strategy.GreedyActionCostSearch;

public class NPuzzleTestMain {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		class NPuzzleBreadthSearch extends BreadthSearch<NPuzzleMoveAction, NPuzzleState> {}
		class NPuzzleDepthSearch extends DepthSearch<NPuzzleMoveAction, NPuzzleState> {}
		class NPuzzleDepthIterativeSearch extends DepthIterativeSearch<NPuzzleMoveAction, NPuzzleState> {}
		class NPuzzleGreedyActionCostSearch extends GreedyActionCostSearch<NPuzzleMoveAction, NPuzzleState> {}
		class NPuzzleGreedy extends GreedySearch<NPuzzleMoveAction, NPuzzleState, NPuzzleHeuristic> {			
			public NPuzzleGreedy(NPuzzleHeuristic heu) {
				super(heu);
			}
		}
		class NPuzzleAStar extends AStarSearch<NPuzzleMoveAction, NPuzzleState, NPuzzleHeuristic> {			
			public NPuzzleAStar(NPuzzleHeuristic heu) {
				super(heu);
			}
		}

		try {
//			int[][] initArray = {{0,3,5},
//					 			 {1,7,2},
//					 			 {8,4,6}};
			int[][] initArray = {{2,0,3},
					 {1,4,5},
					 {7,8,6}};
			int[][] endArray = {{1,2,3},
					            {4,5,6},
					            {7,8,0}};
			NPuzzleBoard init = new NPuzzleBoard(3, 3, initArray);
			NPuzzleBoard end = new NPuzzleBoard(3, 3, endArray);
			
			NPuzzleState state = new NPuzzleState(init, end);
			
			NPuzzleBreadthSearch breadthS = new NPuzzleBreadthSearch();
			NPuzzleDepthSearch depthS = new NPuzzleDepthSearch();
			depthS.setLimitedLevel(20);
			NPuzzleDepthIterativeSearch depthIS = new NPuzzleDepthIterativeSearch();
			NPuzzleGreedyActionCostSearch greedyCostS = new NPuzzleGreedyActionCostSearch();
			NPuzzleGreedy greedyS = new NPuzzleGreedy(new NPuzzleHeuristic(end));
			NPuzzleAStar astarS = new NPuzzleAStar(new NPuzzleHeuristic(end));
			
			System.out.println("Initial board:");
			System.out.println(init);
			System.out.println("Final board:");
			System.out.println(end);
			
			
			System.out.print("BreadthSearchFirst: ");
			breadthS.setStadistics(new JUNGStatistics<NPuzzleMoveAction>());
			System.out.println(breadthS.findFirstSolution(state));
			breadthS.getStatistics().showStatistics();

//			System.out.print("BreadthSearchAll: ");
//			breadthS.setStadistics(new JUNGStatistics<NPuzzleMoveAction>());
//			System.out.println(breadthS.findAllSolutions(state));
//			breadthS.getStatistics().showStatistics();

//			System.out.print("DepthSearchFirst: ");
//			depthS.setStadistics(new JUNGStatistics<NPuzzleMoveAction>());
//			System.out.println(depthS.findFirstSolution(state));
//			depthS.getStatistics().showStatistics();

//			System.out.print("DepthSearchAll: ");
//			depthS.setStadistics(new JUNGStatistics<NPuzzleMoveAction>());
//			System.out.println(depthS.findAllSolutions(state));
//			depthS.getStatistics().showStatistics();
//			
//			System.out.print("DepthIteSearchFirst: ");
//			depthIS.setStadistics(new JUNGStatistics<NPuzzleMoveAction>());
//			System.out.println(depthIS.findFirstSolution(state));
//			depthIS.getStatistics().showStatistics();
//			
//			System.out.print("GreedySearch: ");
//			greedyS.setStadistics(new JUNGStatistics<NPuzzleMoveAction>());
//			System.out.println(greedyS.findFirstSolution(state));
//			greedyS.getStatistics().showStatistics();
//			

//			System.out.print("GreedySearch: ");
//			greedyS.setStadistics(new JUNGStatistics<NPuzzleMoveAction>());
//			System.out.println(greedyS.findFirstSolution(state));
//			greedyS.getStatistics().showStatistics();
			
//			System.out.print("AStarSearch: ");
//			greedyS.setStadistics(new JUNGStatistics<NPuzzleMoveAction>());
//			System.out.println(greedyS.findFirstSolution(state));
//			greedyS.getStatistics().showStatistics();

		
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
	}

}
