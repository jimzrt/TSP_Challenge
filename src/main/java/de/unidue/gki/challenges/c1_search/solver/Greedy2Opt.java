package de.unidue.gki.challenges.c1_search.solver;

import de.unidue.gki.challenges.c1_search.CheckTspProblem;
import de.unidue.gki.challenges.c1_search.core.TSPInstance;
import de.unidue.gki.challenges.c1_search.core.TSPUtil;
import de.unidue.gki.challenges.c1_search.evaluation.TSPEvaluator;

public class Greedy2Opt implements TSPSolver {


    public Integer[] solve(TSPInstance instance) throws Exception {

        return TwoOpt.twoHalfOpt(instance, TwoOpt.optimize(instance, new GreedyBestFirstSolver().solve(instance), false));
    }


}
