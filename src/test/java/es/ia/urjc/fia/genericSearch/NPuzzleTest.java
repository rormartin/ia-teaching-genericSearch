package es.ia.urjc.fia.genericSearch;

import es.urjc.ia.fia.genericSearch.strategy.*;
import es.urjc.ia.fia.samples.npuzzle.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test based in NPuzzle sample
 */
public class NPuzzleTest {

    // init data
    private int[][] initArray = {{2,0,3},
                                 {1,4,5},
                                 {7,8,6}};
    private int[][] endArray = {{1,2,3},
                                {4,5,6},
                                {7,8,0}};


    private NPuzzleBoard init;
    private NPuzzleBoard end;
    private List<NPuzzleMoveAction> good_solution;


    @Before
    public void initData() throws NPuzzleBoardException {

        init = new NPuzzleBoard(3, 3, initArray);
        end = new NPuzzleBoard(3, 3, endArray);

        good_solution = new ArrayList<NPuzzleMoveAction>();
        good_solution.add(new NPuzzleMoveAction(NPuzzleMoveAction.Move.LEFT));
        good_solution.add(new NPuzzleMoveAction(NPuzzleMoveAction.Move.DOWN));
        good_solution.add(new NPuzzleMoveAction(NPuzzleMoveAction.Move.RIGHT));
        good_solution.add(new NPuzzleMoveAction(NPuzzleMoveAction.Move.RIGHT));
        good_solution.add(new NPuzzleMoveAction(NPuzzleMoveAction.Move.DOWN));

    }



    @Test
    public void testBreadthSearch() throws Exception {

        NPuzzleState state = new NPuzzleState(init, end);
        class NPuzzleBreadthSearch extends BreadthSearch<NPuzzleMoveAction, NPuzzleState> {}
        NPuzzleBreadthSearch search = new NPuzzleBreadthSearch();
        List<NPuzzleMoveAction> solution = search.findFirstSolution(state);
        assertEquals(good_solution, solution);
    }

    @Test
    public void testDepthSearch() throws Exception {

        NPuzzleState state = new NPuzzleState(init, end);
        class NPuzzleDepthSearch extends DepthSearch<NPuzzleMoveAction, NPuzzleState> {}
        NPuzzleDepthSearch search = new NPuzzleDepthSearch();
        List<NPuzzleMoveAction> solution = search.findFirstSolution(state);
        assertEquals(good_solution, solution);
    }


    @Test
       public void testDepthIterativeSearch() throws Exception {

        NPuzzleState state = new NPuzzleState(init, end);
        class NPuzzleDepthIterativeSearch extends DepthIterativeSearch<NPuzzleMoveAction, NPuzzleState> {}
        NPuzzleDepthIterativeSearch search = new NPuzzleDepthIterativeSearch();
        List<NPuzzleMoveAction> solution = search.findFirstSolution(state);
        assertEquals(good_solution, solution);
    }

    @Test
    public void testGreedyActionCostSearch() throws Exception {

        NPuzzleState state = new NPuzzleState(init, end);
        class NPuzzleGreedyActionCostSearch extends GreedyActionCostSearch<NPuzzleMoveAction, NPuzzleState> {}
        NPuzzleGreedyActionCostSearch search = new NPuzzleGreedyActionCostSearch();
        List<NPuzzleMoveAction> solution = search.findFirstSolution(state);
        assertEquals(good_solution, solution);
    }


    @Test
    public void testGreedySearch() throws Exception {

        NPuzzleState state = new NPuzzleState(init, end);
        class NPuzzleGreedy extends GreedySearch<NPuzzleMoveAction, NPuzzleState, NPuzzleHeuristic> {
            public NPuzzleGreedy(NPuzzleHeuristic heu) {
                super(heu);
            }
        }
        NPuzzleGreedy search = new NPuzzleGreedy(new NPuzzleHeuristic(end));
        List<NPuzzleMoveAction> solution = search.findFirstSolution(state);
        assertEquals(good_solution, solution);
    }


    @Test
    public void testAStarSearch() throws Exception {

        NPuzzleState state = new NPuzzleState(init, end);
        class NPuzzleAStar extends AStarSearch<NPuzzleMoveAction, NPuzzleState, NPuzzleHeuristic> {
            public NPuzzleAStar(NPuzzleHeuristic heu) {
                super(heu);
            }
        }
        NPuzzleAStar search = new NPuzzleAStar(new NPuzzleHeuristic(end));
        List<NPuzzleMoveAction> solution = search.findFirstSolution(state);
        assertEquals(good_solution, solution);
    }
}
