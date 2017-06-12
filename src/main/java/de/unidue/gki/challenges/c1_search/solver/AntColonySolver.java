package de.unidue.gki.challenges.c1_search.solver;

import de.unidue.gki.challenges.c1_search.CheckTspProblem;
import de.unidue.gki.challenges.c1_search.core.TSPInstance;
import de.unidue.gki.challenges.c1_search.evaluation.TSPEvaluator;

import java.util.Arrays;

public class AntColonySolver implements TSPSolver {

	static final int N_GENERATIONS = 800;
	static final int N_ANTS = 100;
	int N;
	double ALPHA = 2.0;
	double BETA = 6.0;
	double RHO = 0.15;
	double[][] tau;
	double[][] copyTau;
	final Long[][] distance;
	double[] t_prob;

	public AntColonySolver(TSPInstance instance) {
		N = instance.getNrOfLocations();
		distance = instance.getGraph();
	//	tau = new double[N][N];
		copyTau = new double[N][N];
		t_prob = new double[N];

		// set up pheromones
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				copyTau[i][j] = 0.2;
		}

	}

	public Integer[] solve(TSPInstance instance) throws Exception {

		boolean[] visited = new boolean[N];
		Integer[] tour = new Integer[N + 1];
		Integer[] best_tour = new Integer[N + 1];

		long min_tour = Long.MAX_VALUE;




		int generationCounter = 0;
		while (generationCounter < N_GENERATIONS) {

			System.out.println("Generation " + generationCounter);

				for(int i = 0; i < N_ANTS;  i++) {

				// Initialise tour
				for (int j = 0; j < N; j++) {
					visited[j] = false;
					tour[j] = -1;
				}

				tour[0] = 0;
				visited[0] = true;

				for (int j = 1; j < N; j++) {

					// transition probabilities

					double sum = 0.0;
					for (int k = 0; k < N; k++) {
						t_prob[k] = 0.0;
						if (visited[k] == false) {
							t_prob[k] = pow(copyTau[k][tour[j - 1]], ALPHA)
									/ pow(distance[k][tour[j - 1]], BETA);

							sum += t_prob[k];

						}

					}

					// roulette wheel selection
					double rand = Math.random() * sum;
					sum = 0.0;
					int k;
					for (k = 0; k < N - 1; k++) {
						if (!visited[k]) {
							sum += t_prob[k];
							if (rand < sum) {
								break;
							}
						}
					}

					tour[j] = k;
					visited[k] = true;
				}
				tour[N] = 0;

				TwoOpt.optimize(instance, tour, false);


				// Save best tour so far
				long dinstance = TSPEvaluator.getRoundtripDistance(instance,
						tour);
				if (dinstance < min_tour) {


					min_tour = dinstance;
					for (int j = 0; j < N + 1; j++)
						best_tour[j] = tour[j];


					CheckTspProblem.printResults(instance, best_tour);

					//	adjustPheremone(instance, tour);
				}

				adjustPheremone(instance, tour);

			}

			evaporatePheremone();
			// adjust_pheremone (instance,TwoOpt.optimize(instance,tour,true));
			//tau = copyTau.clone();
			generationCounter++;
		}

		adjustPheremone(instance, best_tour);
		//System.out.println(Arrays.deepToString(copyTau));
		return best_tour;

	}
	public static double pow(final double a, final double b) {
		final int x = (int) (Double.doubleToLongBits(a) >> 32);
		final int y = (int) (b * (x - 1072632447) + 1072632447);
		return Double.longBitsToDouble(((long) y) << 32);
	}

	public void adjustPheremone(TSPInstance instance, Integer[] tour) {
		int i;
		double d = TSPEvaluator.getRoundtripDistance(instance, tour);

		for (i = 1; i < N; i++) {
			copyTau[tour[i - 1]][tour[i]] += (3.0 / d);
			copyTau[tour[i]][tour[i - 1]] += (3.0 /d);
		}
	}

	public void evaporatePheremone() {
		int i, j;

		for (i = 0; i < N; i++) {
			for (j = 0; j <= i; j++) {
				copyTau[i][j] = (1 - RHO) * copyTau[i][j];
				copyTau[j][i] = copyTau[i][j];
			}
		}


	}

}
