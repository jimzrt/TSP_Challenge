package de.unidue.gki.challenges.c1_search.core;

import java.util.Scanner;


public class TSPParser {
    
    public static TSPInstance parse(String data) {
        Scanner scanner = new Scanner(data);

        int nrOfLocations = Integer.parseInt(scanner.nextLine());
        int nrOfRoads = Integer.parseInt(scanner.nextLine());
        
        TSPInstance tsp = new TSPInstance(nrOfLocations);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");

            if (parts.length != 3) {
                throw new RuntimeException("Invalid line: " + line);
            }
            
            tsp.addPath(Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Long.parseLong(parts[2]));
        }

        return tsp;
    }
}