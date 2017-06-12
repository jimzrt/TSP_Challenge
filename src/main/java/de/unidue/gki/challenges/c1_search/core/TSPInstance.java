package de.unidue.gki.challenges.c1_search.core;

import java.util.HashSet;
import java.util.Set;



/**
 * Stores a TSP instance.
 * 
 * @author zesch
 *
 * Inspired by code from http://www.cs.helsinki.fi/node/66069, but heavily adapted.
 */
public class TSPInstance {

    public static final Integer START_LOCATION = 0;

    private Long[][] graph;

    public Long[][] getGraph() {
		return graph;
	}

	private Set<Integer> locations;
    private int nrOfRoads;
    
    public TSPInstance(int n) {
        locations = new HashSet<Integer>();
        nrOfRoads = 0;
        
        graph = new Long[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                graph[i][j] = Long.MAX_VALUE;
            }
        }
    }
    
    public void addPath(Integer start, Integer end, long length) {
        if (start > end) {
            _addPath(end, start, length);
        }
        else {
            _addPath(start, end, length);
        }        
    }
    
    public long getDistance(Integer start, Integer end) {
        if (start > end) {
            return computeDistance(end, start);
        }
        else {
            return computeDistance(start, end);
        }
    }

    public boolean existsRoad(Integer start, Integer end) {
        if (start > end) {
            return _existsRoad(end, start);
        }
        else {
            return _existsRoad(start, end);
        }
    }

    public int getNrOfLocations() {
        return locations.size();
    }
    
    public int getNrOfRoads() {
        return nrOfRoads;
    }
    
    public Long[] getPathLengths(int from) {
        return graph[from];
    }

    private long computeDistance(Integer start, Integer end) {
        if (!existsRoad(start, end)) {
            throw new RuntimeException("No path from " + start + " to " + end);
        }

        return graph[start][end];
    }
    
    private void _addPath(int start, int end, long length) {
        nrOfRoads++;
        locations.add(start);
        locations.add(end);

        graph[start][end] = length;
        graph[end][start] = length;
    }
    
    private boolean _existsRoad(Integer start, Integer end) {
        return graph[start][end] != 0;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getNrOfLocations());
        sb.append("\n");
        sb.append(getNrOfRoads());
        sb.append("\n");
        
        for (int i=0; i<getNrOfLocations(); i++) {
            for (int j=i+1; j<getNrOfLocations(); j++) {
                sb.append(i + "\t" + j + "\t" + graph[i][j]);
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }
}