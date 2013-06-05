package es.urjc.ia.fia.samples.numbers;

import java.util.ArrayList;

import es.urjc.ia.fia.genericSearch.statistics.JUNGStatistics;
import es.urjc.ia.fia.genericSearch.statistics.TextStatistics;
import es.urjc.ia.fia.genericSearch.strategy.AStarSearch;
import es.urjc.ia.fia.genericSearch.strategy.BreadthSearch;
import es.urjc.ia.fia.genericSearch.strategy.DepthIterativeSearch;
import es.urjc.ia.fia.genericSearch.strategy.DepthSearch;
import es.urjc.ia.fia.genericSearch.strategy.GreedyActionCostSearch;
import es.urjc.ia.fia.genericSearch.strategy.GreedySearch;

public class NumbersMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Instanciación de estrategias
		class NumberDepthSearch extends DepthSearch<NumberAction, NumberState> {}
		class NumberBreadthSearch extends BreadthSearch<NumberAction, NumberState> {}
		
		// Para hoja 3
		// Ejercicio 1
		class NumberGreedy extends GreedySearch<NumberAction,NumberState, NumberHeuristic> {			
			public NumberGreedy(NumberHeuristic heu) {
				super(heu);
			}
		}
		// Ejercicio 2
		class NumberAStar extends AStarSearch<NumberAction,NumberState, NumberHeuristic> {			
			public NumberAStar(NumberHeuristic heu) {
				super(heu);
			}
		}

		try {
			
			// Números iniciales
			int[] anums = {25, 7, 3, 9, 6};
			int goalResult = 234;
			
			ArrayList<Integer> nums = new ArrayList<Integer>();
			for (int n : anums) { nums.add(n); }
			
			// Creación de estado inicial
			NumberState state = new NumberState(nums, goalResult);
			
			// Inicialización de estrategia (en profundidad)
			NumberDepthSearch depthS = new NumberDepthSearch();
			depthS.setStadistics(new TextStatistics<NumberAction>());
			System.out.print("DepthSearchFirst: ");
			System.out.println(depthS.findFirstSolution(state));
			depthS.getStatistics().showStatistics();
			
			NumberBreadthSearch breadthS = new NumberBreadthSearch();
			breadthS.setStadistics(new TextStatistics<NumberAction>());
			System.out.print("BreadSearchFirst: ");
			System.out.println(breadthS.findFirstSolution(state));
			breadthS.getStatistics().showStatistics();
			
			// Inicialización de estrategia
			NumberGreedy greedy = new NumberGreedy(new NumberHeuristic());
			greedy.setStadistics(new TextStatistics<NumberAction>());
			//greedy.setStadistics(new JUNGStatistics<NumberAction>());
			System.out.print("GreedySearch: ");
			System.out.println(greedy.findFirstSolution(state));
			greedy.getStatistics().showStatistics();
			
			NumberAStar astar = new NumberAStar(new NumberHeuristic());
			//astar.setStadistics(new JUNGStatistics<NumberAction>());
			astar.setStadistics(new TextStatistics<NumberAction>());
			System.out.print("AStarSearch: ");
			System.out.println(astar.findFirstSolution(state));
			astar.getStatistics().showStatistics();
			
			
			// Inicialización de estadísticas (no obligatorio)
			// - En modo texto
			//depthS.setStadistics(new TextStatistics<NumberAction>());
			// - En modo gráfico
			//depthS.setStadistics(new JUNGStatistics<NumberAction>());
			
			// Ejecutar e imprimir la solución
			
			// Mostrar estadísticas, sólo cuando se han inicializado
			//depthS.getStatistics().showStatistics();
			
			// Ejecutar búsqueda en anchura
			

		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
	}
}
