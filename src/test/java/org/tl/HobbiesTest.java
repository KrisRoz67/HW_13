package org.tl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HobbiesTest {

    Hobbies hobbies;
    String testFilePath;
    @BeforeEach
     void run() {
        hobbies = new Hobbies();
        testFilePath = "src/test/resources/hobbies.txt";
    }

    @Test
    void createListFromFile() {
        List<String> actual = hobbies.createListFromFile(testFilePath);
        assertEquals(15,actual.size());

    }

    @Test
    void createDictionary() {
        Map<String,Set<String>> actual= hobbies.createDictionary(testFilePath);
       Map<String,Set<String>> expected = Map.of("Olivia",Set.of("Chess","Cooking","Hiking"));
        assertEquals(actual.get("Olivia"), expected.get("Olivia"));
    }

    @Test
    void findPersonWithMostHobbies() {
        List<String> actual  = hobbies.findPersonWithMostHobbies(testFilePath).stream().sorted().toList();
        List<String> expected = Stream.of("Olivia","Sophia","Noah").sorted().toList();
        assertEquals(actual, expected);
    }

    @Test
    void findPersonWithLeastHobbies() {
        List<String> actual  = hobbies.findPersonWithLeastHobbies(testFilePath).stream().sorted().toList();
        List<String> expected = Stream.of("Isabella","Jackson").sorted().toList();
        assertEquals(expected,actual);
    }

    @Test
    void findMostPopularHobby() {
        List<String> actual  = hobbies.findMostPopularHobby(testFilePath);
        List<String> expected = Stream.of("Photography").toList();
        assertEquals(expected, actual);
    }

    @Test
    void findLeastPopularHobby() {
        List<String> actual  = hobbies.findLeastPopularHobby(testFilePath).stream().sorted().toList();
        List<String> expected = Stream.of("Traveling", "Knitting", "Singing", "Hiking", "Yoga" , "Cooking", "Cycling" , "Painting" ).sorted().toList();
        assertEquals(expected,actual);
    }
}