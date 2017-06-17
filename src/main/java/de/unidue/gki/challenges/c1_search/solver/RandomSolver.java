package de.unidue.gki.challenges.c1_search.solver;

import java.util.ArrayList;
import java.util.Collections;

import de.unidue.gki.challenges.c1_search.core.TSPInstance;

/**
 * Creates a randomly generated route.
 *
 * @author zesch
 */
public class RandomSolver
        implements TSPSolver {

    public Integer[] solve(TSPInstance instance) {
        ArrayList<Integer> solution = new ArrayList<Integer>();
        for (int i = 1; i < instance.getNrOfLocations(); i++) {
            solution.add(i);
        }
        Collections.shuffle(solution);

        solution.add(0);
        solution.add(0, 0);

        return solution.toArray(new Integer[0]);
    }
}