package de.unidue.gki.challenges.c1_search.core;

import java.io.File;
import java.util.Random;

import org.apache.commons.io.FileUtils;

public class TSPInstanceCreator
{

    private static Random rand = new Random();
    
    public static void main(String[] args)
        throws Exception
    {
        int n = 2000;
        
        TSPInstance tsp = new TSPInstance(n);
        
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                tsp.addPath(i, j, getRandomLength());
            }
        }
        
        FileUtils.writeStringToFile(new File("src/main/resources/maps/map."+n), tsp.toString());
    }
    
    public static long getRandomLength() {
        long length = 1;
        
        double random = rand.nextDouble();
        
        length = Math.round(Math.log(1-random) / -0.01) + 1;

        // just to be safe
        if (length <= 0) {
            System.err.println("length <= 0");
            length = 1;
        }
        
        return length;
    }
}