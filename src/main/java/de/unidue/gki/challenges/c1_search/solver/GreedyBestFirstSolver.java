package de.unidue.gki.challenges.c1_search.solver;

import de.unidue.gki.challenges.c1_search.core.TSPInstance;

/**
 * Implements the greedy-best first strategy for solving a TSP instance.
 * 
 * @author zesch
 *
 */
public class GreedyBestFirstSolver implements TSPSolver {

	public GreedyBestFirstSolver() {

		// whatever is required for initialization
	}

	boolean[] visited;

	public Integer[] solve(TSPInstance instance) {

		visited = new boolean[instance.getNrOfLocations()];
		Integer[] solution = new Integer[instance.getNrOfLocations() + 1];
		solution[0] = 0;
		visited[0] = true;
		int node = 0;

		for (int i = 1; i < instance.getNrOfLocations(); i++) {

			solution[i] = returnNearestNode(instance, node);
			node = solution[i];
		}

		solution[instance.getNrOfLocations()] = 0;
		// implement me

		return solution;
	}

	public int returnNearestNode(TSPInstance instance, int node) {

		Long[] paths = instance.getPathLengths(node);
		long dst = Long.MAX_VALUE;
		int nearest = 0;
		for (int i = 0; i < paths.length; i++) {
			if (paths[i] < dst && visited[i] == false) {
				dst = paths[i];
				nearest = i;
			}
		}
		visited[nearest] = true;
		return nearest;
	}
}