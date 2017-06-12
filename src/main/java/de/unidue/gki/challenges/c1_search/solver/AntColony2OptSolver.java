package de.unidue.gki.challenges.c1_search.solver;

import java.util.Scanner;

import de.unidue.gki.challenges.c1_search.CheckTspProblem;
import de.unidue.gki.challenges.c1_search.core.TSPInstance;
import de.unidue.gki.challenges.c1_search.evaluation.TSPEvaluator;

public class AntColony2OptSolver implements TSPSolver {

	Integer[] solution;
	Integer[] bestsolution;
	Long[][] graph;

	public Integer[] solve(TSPInstance instance) throws Exception {
		AntColonySolver ant = new AntColonySolver(instance);

		bestsolution = ant.solve(instance);

		System.out.println("Initial Solution: ");
		CheckTspProblem.printResults(instance, bestsolution);
		System.out.println(" ");
	//	long bestdistance = TSPEvaluator.getRoundtripDistance(instance,
	//			bestsolution);
	//	long distance;
/*
		int i = 0;
		while (i <= 500) {
			i++;


			System.out.println("Durchlauf " + i);
				solution = ant.solve(instance);



			distance = TSPEvaluator.getRoundtripDistance(instance, solution);
			if (distance < bestdistance) {
				bestsolution = solution;
				bestdistance = distance;
				CheckTspProblem.printResults(instance, solution);

			}
		}
*/

		return bestsolution;

	}
}
