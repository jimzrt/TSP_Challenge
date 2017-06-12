package de.unidue.gki.challenges.c1_search;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.unidue.gki.challenges.c1_search.core.TSPInstance;
import de.unidue.gki.challenges.c1_search.core.TSPParser;
import de.unidue.gki.challenges.c1_search.core.TSPUtil;
import de.unidue.gki.challenges.c1_search.evaluation.TSPEvaluator;
import de.unidue.gki.challenges.c1_search.evaluation.TSPValidator;
import de.unidue.gki.challenges.c1_search.solver.AntColony2OptSolver;
import de.unidue.gki.challenges.c1_search.solver.BruteForceSolver;
import de.unidue.gki.challenges.c1_search.solver.Greedy2Opt;
import de.unidue.gki.challenges.c1_search.solver.GreedyBestFirstSolver;
import de.unidue.gki.challenges.c1_search.solver.RandomSolver;
import mdsj.MDSJ;

public class CheckTspProblem {

	public static void main(String[] args) throws Exception {

		// get an instance of a traveling salesperson map
		List<TSPInstance> tspInstances = new ArrayList<TSPInstance>();
		// tspInstances.add(TSPParser.parse(TSPUtil.getMapFileContents("src/main/resources/maps/uebung.5")));
		//tspInstances.add(TSPParser.parse(TSPUtil.getMapFileContents("src/main/resources/maps/map.10")));
	//	tspInstances.add(TSPParser.parse(TSPUtil
	//			.getMapFileContents("src/main/resources/maps/map.20")));
	//	tspInstances.add(TSPParser.parse(TSPUtil
//				.getMapFileContents("src/main/resources/maps/map.100")));
		tspInstances.add(TSPParser.parse(TSPUtil
				.getMapFileContents("src/main/resources/maps/map.500.gz")));
		//tspInstances.add(TSPParser.parse(TSPUtil
	//			.getMapFileContents("src/main/resources/maps/map.2000.gz")));

		for (TSPInstance tspInstance : tspInstances) {


			Path path = Paths.get("tsp.txt");
			try (BufferedWriter writer = Files.newBufferedWriter(path)) {
				for(Long[] row : tspInstance.getGraph()){
					for(int i = 0; i < row.length -1; i++){
						writer.write(row[i] + " ");

					}
					writer.write(row[row.length-1] + "\r\n");

				}
			}



			System.out.println("++++++++++ Map size: "
					+ tspInstance.getNrOfLocations() + " ++++++++++");
			System.out.println();








			System.out.println("Random");
			printResults(tspInstance, new RandomSolver().solve(tspInstance));

			if (tspInstance.getNrOfLocations() < 15) {
				System.out.println("Brute-force");
				printResults(tspInstance,
						new BruteForceSolver().solve(tspInstance));
			}

			System.out.println("Greedy");
			printResults(tspInstance,
					new GreedyBestFirstSolver().solve(tspInstance));

			System.out.println("Greedy-2Opt");
			printResults(tspInstance, new Greedy2Opt().solve(tspInstance));

			// System.out.println("Ant Colony");
			// printResults(tspInstance, new
			// AntColonySolver().solve(tspInstance));

			System.out.println("Ant Colony 2Opt");
			printResults(tspInstance,
					new AntColony2OptSolver().solve(tspInstance));





		}
	}

	public static void printResults(TSPInstance instance, Integer[] solution) {
		System.out.println(Arrays.toString(solution));

		System.out.println("Total locations: " + instance.getNrOfLocations());
		System.out.println("Total visits: " + solution.length);
		System.out.println("Valid path? "
				+ TSPValidator.isValidPath(instance, solution));
		System.out.println("Path length: "
				+ TSPEvaluator.getRoundtripDistance(instance, solution));
		System.out.println();
	}
}