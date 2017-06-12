package de.unidue.gki.challenges.c1_search.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class TSPUtil
{

    /**
     * Swaps node i and j in the solution
     */
    public static void swap(Integer[] solution, int i, int j) {
        Integer tmp = solution[i];
        solution[i] = solution[j];
        solution[j] = tmp;
    }
    
    /**
     * Returns the file contents of a map file that represents a TSP instance.
     * 
     * @param path The path to the map file.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String getMapFileContents(String path) throws FileNotFoundException, IOException {
        String contents;
        if (path.endsWith(".gz")) {
            contents = IOUtils.toString(new GZIPInputStream(new FileInputStream(path)));
        }
        else {
            contents = FileUtils.readFileToString(new File(path), "UTF-8");
        }
        
        return contents;
    }
    
    public static Integer[] getDummySolution(TSPInstance instance) {
        // this is only a dummy return - it needs to be replaced with the real output
        Integer[] solution = new Integer[instance.getNrOfLocations()+1];
        for (int i=0; i<instance.getNrOfLocations(); i++) {
            solution[i] = i;
        }
        solution[instance.getNrOfLocations()] = 0;
        
        return solution;
    }
}