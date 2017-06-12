package de.unidue.gki.challenges.c1_search.solver;

import de.unidue.gki.challenges.c1_search.core.TSPInstance;

public class TwoOpt {

	public static Integer[] optimize(TSPInstance instance, Integer[] solution,
			boolean greedy) {

		Long[][] graph = instance.getGraph();

		long bestGain = -1;

		int bestI = 0;
		int bestJ = 0;

		while (bestGain < 0) {
			bestGain = 0;

			for (int i = 1; i < instance.getNrOfLocations() - 1; i++) {
				for (int j = i + 1; j < instance.getNrOfLocations(); j++) {

					long gain = moveCost(i, j, solution, graph);

					if (gain < bestGain) {
						bestGain = gain;
						bestI = i;
						bestJ = j;
					}

				}
			}
			if (bestGain < 0) {
				swap(solution, bestI, bestJ);

			}
		}
		return solution;
	}

	private static long moveCost(final int i, final int j, Integer[] solution,
			Long[][] graph) {
		int a = solution[i - 1];
		int b = solution[i];

		int c = solution[j];
		int d = solution[j + 1];

		return ((graph[a][c] + graph[b][d]) - (graph[a][b] + graph[c][d]));
	}

	public static void swap(Integer[] solution, int i, int j) {

		Integer[] tmp = new Integer[(j + 1) - i];
		for (int k = i; k <= j; k++) {
			tmp[k - i] = solution[k];
		}
		for (int k = 0; k <= j - i; k++)
			solution[i + k] = tmp[tmp.length - 1 - k];
		// this.solution=solution;
	}

}
