package de.unidue.gki.challenges.c1_search.evaluation;

import de.unidue.gki.challenges.c1_search.core.TSPInstance;

import java.util.Arrays;

public class TSPEvaluator
{
    /**
     * Returns the length of a path.
     * 
     * @param tspInstance A TSP instance
     * @param path A path represented as an array of node IDs
     * @return
     */
    public static long getRoundtripDistance(TSPInstance tspInstance, Integer[] path) {

        // validate that path is valid in this instance
        if (!TSPValidator.isValidPath(tspInstance, path)) {
            System.out.println(Arrays.toString(path));
            throw new RuntimeException("Invalid path.");
        }
        
        int start = path[0];
        long length = 0;

        for (int i=1; i<path.length; i++) {
            int end = path[i];
            length += tspInstance.getDistance(start, end);
            start = end;
        }

        return length;
    }
}