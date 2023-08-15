package com.SWAPI.RestAssured;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Hello world!
 *
 */
public class App
{
	final static String BASE_URL = "https://swapi.dev/api";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public static Object[] testPeopleEndpointSuccess() {
        Response response = given().get("/people");
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public static void testPeopleWithHeightGreaterThan200() {
        Response response = given().param("height__gt", 200).get("/people");
        int expectedCount = 10; // change this if the expected count changes
        int actualCount = response.jsonPath().getList("results").size();
        assertEquals(actualCount, expectedCount);
    }

    @Test
    public static void testSpecificIndividuals() {
        String[] expectedNames = {
            "Darth Vader", "Chewbacca", "Roos Tarpals", "Rugor Nass",
            "Yarael Poof", "Lama Su", "Tuan Wu", "Grievous", "Tarfful", "Tion Medon"
        };

        Response response = given().get("/people");
        String[] actualNames = response.jsonPath().getList("results.name").toArray(new String[0]);

        assertArrayEquals(actualNames, expectedNames);
    }

    @Test
    public static void testTotalNumberOfPeople() {
        Response response = given().get("/people");
        int actualTotal = response.jsonPath().getInt("count");
        int expectedTotal = 82; // change this if the expected count changes
        assertEquals(actualTotal, expectedTotal);
    }
    public static void main( String[] args )
    {
    	RestAssured.baseURI = BASE_URL;

        // Run your tests and print the results
        testPeopleEndpointSuccess();
        testPeopleWithHeightGreaterThan200();
        testSpecificIndividuals();
        testTotalNumberOfPeople();
      
        }
}


