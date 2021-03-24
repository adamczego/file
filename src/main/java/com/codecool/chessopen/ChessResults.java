package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;



public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName) {

        Map<String, Integer> leaderboard = new HashMap<>();


        try(FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader)) {

            String line = reader.readLine();

            while (line != null) {

                String playerName = line.split(",")[0];
                int playerScoreSum = Arrays.stream(line.split(","))
                    .skip(1)
                    .map(Integer::parseInt)
                    .reduce(0, Integer::sum);

                leaderboard.put(playerName, playerScoreSum);

                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return leaderboard.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

}
