package org.tl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Hobbies {


    /**
     * Collect lines from the given file into a list.
     *
     * @param filePath The path to the file to read.
     * @return A list of lines from the file.
     */
    public List<String> createListFromFile(String filePath) {
        List<String> listOfData = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                listOfData.add(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured " + e.getMessage());
        }
        return listOfData;
    }

    /**
     * Create a dictionary of people's hobbies from the given file.
     *
     * @param filePath The path to the file to read.
     * @return A map where each person's name is associated with a list of their hobbies.
     */
    public Map<String, Set<String>> createDictionary(String filePath) {
        Map<String, Set<String>> map = new HashMap<>();
        List<String> list = createListFromFile(filePath);
        Set<String> userHobbies = new HashSet<>();
        for (String data : list) {
            List<String> lineToList = stringToList(data);
            String person = lineToList.get(0);
            userHobbies.add(lineToList.get(1));
            for (String lines : list) {
                List<String> lineToList2 = stringToList(lines);
                if (lineToList2.get(0).equals(person)) {
                    userHobbies.add(lineToList2.get(1));
                }
            }
            if (map.containsKey(person)) {
                map.replace(person, userHobbies);
            } else {
                map.put(person, userHobbies);
            }
            userHobbies = new HashSet<>();
        }

        return map;
    }

    /**
     * Find the person (or people) who have more hobbies than others.
     *
     * @param filePath The path to the file containing hobby data.
     * @return A list of people with the most hobbies.
     */
    public List<String> findPersonWithMostHobbies(String filePath) {
        Map<String, Set<String>> map = createDictionary(filePath);
        List<Integer> qty = map.entrySet().stream().map(value ->
                value.getValue().size()).toList();
        List<String> listOdPersonsWithMostHobbies = new ArrayList<>();
        int maxNumber = Collections.max(qty);
        listOdPersonsWithMostHobbies = map.entrySet().stream()
                .filter(name -> name.getValue().size() == (maxNumber))
                .map(Map.Entry::getKey).toList();
        return listOdPersonsWithMostHobbies;
    }

    /**
     * Find the person (or people) who have fewer hobbies than others.
     *
     * @param filePath The path to the file containing hobby data.
     * @return A list of people with the fewest hobbies.
     */
    public List<String> findPersonWithLeastHobbies(String filePath) {
        Map<String, Set<String>> map = createDictionary(filePath);
        List<Integer> qty = map.entrySet().stream().map(value ->
                value.getValue().size()).toList();
        List<String> listOfPersonsWithLeastHobbies = new ArrayList<>();
        int minNumber = Collections.min(qty);
        listOfPersonsWithLeastHobbies = map.entrySet().stream()
                .filter(name -> name.getValue().size() == (minNumber))
                .map(Map.Entry::getKey).toList();
        return listOfPersonsWithLeastHobbies;
    }


    /**
     * Find the most popular hobby among all people.
     *
     * @param filePath The path to the file containing hobby data.
     * @return The most popular hobby.
     */
    public List<String> findMostPopularHobby(String filePath) {
        Map<String, Integer> countHobbies = countTheHobbis(filePath);
        List<String> listOfMostPopularHobby = new ArrayList<>();
        int finalMaxNumber = Collections.max(countHobbies.entrySet().stream()
                .map(Map.Entry::getValue).toList());
        listOfMostPopularHobby = countHobbies.entrySet().stream()
                .filter(counter -> counter.getValue() == finalMaxNumber)
                .map(Map.Entry::getKey).toList();
        return listOfMostPopularHobby;
    }

    /**
     * Find the least popular hobby among all people.
     *
     * @param filePath The path to the file containing hobby data.
     * @return The least popular hobby.
     */
    public List<String> findLeastPopularHobby(String filePath) {
        Map<String, Integer> countHobbies = countTheHobbis(filePath);
        List<String> listOfLeastPopularHobby = new ArrayList<>();
        int finalMinNumber = Collections.min(countHobbies.entrySet().stream()
                .map(Map.Entry::getValue).toList());
        listOfLeastPopularHobby = countHobbies.entrySet().stream()
                .filter(counter -> counter.getValue() == finalMinNumber)
                .map(Map.Entry::getKey).toList();
        return listOfLeastPopularHobby;
    }

    /**
     * Write correct database to the csv file
     * <p>
     * Since this is a csv file, then the output should be like:
     * Name,Hobbies
     * Bob,skiing
     * Alice,dancing-programming
     * <p>
     * Hobbies have to be sorted in alphabetic order
     *
     * @param filePath    The path to the file containing hobby data
     * @param fileToWrite Path to the new file with correct data
     */
    public void writeCorrectedDatabase(String filePath, String fileToWrite) {
        Map<String,Set<String>> map = createDictionary(filePath);
       try{
           FileWriter writer  = new FileWriter(fileToWrite);
           writer.write("<p>\n"+"Name,Hobbies");
           for (Map.Entry<String ,Set <String>> entry : map.entrySet()) {
               String name = "\n" + entry.getKey() + ",";
               List<String> hobbiesList = entry.getValue().stream().sorted().toList();
               String hobby = hobbiesList.get(0).toLowerCase();
               if (hobbiesList.size() > 1) {
                   for (int i = 1; i < hobbiesList.size(); i++) {
                       hobby = hobby + "-" + hobbiesList.get(i).toLowerCase();
                   }
               }
               writer.write(name + hobby);
           }
           writer.write("\n<p>");
           writer.close();

       }catch (Exception e){
           System.out.println("Error occured " +e.getMessage() );
       }



    }

    public List<String> stringToList(String str) {
        String delimeter = ":"; // Разделитель
        return List.of(str.split(delimeter));

    }

    public Map<String, Integer> countTheHobbis(String filePath) {
        List<String> listFromFile = createListFromFile(filePath);
        List<String> hobbies = listFromFile.stream().map(hobby -> stringToList(hobby).get(1)).toList();
        Map<String, Integer> countHobbies = new HashMap<>();
        for (String hobby : hobbies) {
            if (countHobbies.containsKey(hobby)) {
                countHobbies.replace(hobby, countHobbies.get(hobby) + 1);
            } else {
                countHobbies.put(hobby, 1);
            }

        }
        return countHobbies;
    }
}
