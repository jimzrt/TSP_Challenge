package de.unidue.gki.challenges.c1_search.solver;

import de.unidue.gki.challenges.c1_search.core.TSPInstance;

public interface TSPSolver
{
    /**
     * Creates a solution for a TSP problem in form of a route.
     * 
     * @param instance A TSP problem
     * @return The route through all nodes as a list of node IDs
     * @throws Exception
     */
    public Integer[] solve(TSPInstance instance) throws Exception;
}
