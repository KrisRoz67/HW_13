package org.tl;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Hobbies hobbies = new Hobbies();
        final List<String> lines = hobbies.createListFromFile("src/main/resources/hobbies.txt");
        System.out.println("--------Print list from file------");
        System.out.println(lines);
        final Map<String, Set<String>> map = hobbies
                .createDictionary("src/main/resources/hobbies.txt");
        System.out.println("--------Print map of person and hobbies------");
        System.out.println(map);
        final List<String> findPersonWithMostHobbies = hobbies.
                findPersonWithMostHobbies("src/main/resources/hobbies.txt");
        System.out.println("--------Print persons with most hobbies------");
        System.out.println(findPersonWithMostHobbies);
        final List<String> findPersonWithLeastHobbies = hobbies.
                findPersonWithLeastHobbies("src/main/resources/hobbies.txt");
        System.out.println("--------Print persons with least hobbies------");
        System.out.println(findPersonWithLeastHobbies);
        final List<String> listOfMostPopularHobby = hobbies.
              findMostPopularHobby("src/main/resources/hobbies.txt");
        System.out.println("--------Print most popular hobbies------");
        System.out.println(listOfMostPopularHobby);
        System.out.println("--------Print least popular hobbies------");
        final List<String> listOfLeastPopularHobby = hobbies.
                findLeastPopularHobby("src/main/resources/hobbies.txt");
        System.out.println(listOfLeastPopularHobby);
        hobbies.writeCorrectedDatabase("src/main/resources/hobbies.txt",
                "src/main/resources/hobbiesCorrect.csv");
    }
}
