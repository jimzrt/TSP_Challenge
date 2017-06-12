package de.unidue.gki.challenges.c1_search.solver;

import java.util.ArrayList;
import java.util.List;

import de.unidue.gki.challenges.c1_search.core.TSPInstance;
import de.unidue.gki.challenges.c1_search.core.TSPUtil;
import de.unidue.gki.challenges.c1_search.evaluation.TSPEvaluator;

/**
 * Tries to solve a TSP instance by generating all possible permutations of routes.
 * 
 * Fast implementation.
 * 
 * @author zesch
 *
 */
public class BruteForceSolver
    implements TSPSolver
{

    public Integer[] solve(TSPInstance instance)
    {
    	long minDistance = Long.MAX_VALUE;
    	Integer[] solution=null;
    	
    	for(Integer[]perm : getPermutations(instance)){
    		long distance = TSPEvaluator.getRoundtripDistance(instance, perm);
    		if(distance < minDistance){
    			minDistance = distance;
    			solution = perm;
    		}
    	}
    	

        // implement a brute force solve using the provided getPermutations() method
        return solution;
    }
    
    private List<Integer[]> getPermutations(TSPInstance instance) {
        List<Integer[]> permutations = new ArrayList<Integer[]>();

        Integer[] solution = new Integer[instance.getNrOfLocations()+1];
        for (int i=1; i<instance.getNrOfLocations(); i++) {
            solution[i] = i;
        }
        
        permute(instance, permutations, solution, solution.length-1);
        
        return permutations;
    }
    
    private void permute(TSPInstance instance, List<Integer[]> permutations, Integer[] solution, int k) {
 
        if (k == 2) {
            addSolution(permutations, solution);
        }
        
        for (int i=1; i < k; i++) {
            TSPUtil.swap(solution, i, k-1);
            permute(instance, permutations, solution, k-1);      
            TSPUtil.swap(solution, i, k-1);
        }   
    }
    
    private void addSolution(List<Integer[]> permutations, Integer[] solution) {
        Integer[] solutionCopy = new Integer[solution.length];
        System.arraycopy(solution, 0, solutionCopy, 0, solution.length);

        solutionCopy[0] = 0;
        solutionCopy[solution.length-1] = 0;
        
        permutations.add(solutionCopy);        
    }
}