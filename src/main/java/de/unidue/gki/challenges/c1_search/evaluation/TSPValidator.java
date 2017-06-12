package de.unidue.gki.challenges.c1_search.evaluation;

import static de.unidue.gki.challenges.c1_search.core.TSPInstance.START_LOCATION;

import java.util.Arrays;

import de.unidue.gki.challenges.c1_search.core.TSPInstance;


/**
 * Validates a TSP instance.
 * 
 * @author zesch
 *
 * Inspired by code from http://www.cs.helsinki.fi/node/66069, but very heavily adapted.
 */
public class TSPValidator {
    
    /**
     * Checks whether a path is valid.
     * 
     * @param instance A TSP instance
     * @param path A path represented as an array of node IDs
     * @return
     */
    public static boolean isValidPath(TSPInstance instance, Integer[] path) {
        if (!visitsAllLocations(instance, path)) {
            System.err.println("Does not visit all locations.");
            return false;
        }

        if (path[0] != START_LOCATION) {
            System.err.println("Wrong start location.");
            return false;
        }
        
        if (path[path.length-1] != START_LOCATION) {
            System.err.println("Wrong end location.");
            return false;
        }

        return checkPath(instance, path);
    }
    
    private static boolean checkPath(TSPInstance instance, Integer[] path) {
        int start = path[0];
        for (int i = 1; i < path.length; i++) {
            int end = path[i];
            if (!instance.existsRoad(start, end)) {
                return false;
            }

            start = end;
        }

        return true;
    }

    private static boolean visitsAllLocations(TSPInstance instance, Integer[] path) {
        Integer[] sortedPath = path.clone();
        Arrays.sort(sortedPath);
                                
        for (int i=1; i<=instance.getNrOfLocations(); i++) {
            if (sortedPath[i] != i-1) {
                return false;
            }
        }
        
        return true;
    }    
}