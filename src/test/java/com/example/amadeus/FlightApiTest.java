package com.example.amadeus;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightApiTest {

    private static final String BASE_ENDPOINT = "https://flights-api.buraky.workers.dev/";

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BASE_ENDPOINT;
    }

    @Test
    public void statusCodeShouldBe200() {
        given()
                .when().get()
                .then().assertThat().statusCode(200);
    }

    @Test
    public void responseBodyShouldMatchStructure() {
        JsonPath jsonPath = given()
                .when().get()
                .then().extract().jsonPath();

        List<Map<String, ?>> flights = jsonPath.getList("data");
        assertFlightsStructure(flights);
    }

    @Test
    public void contentTypeShouldBeJson() {
        given()
                .when().get()
                .then().assertThat().contentType("application/json");
    }
    private void assertFlightsStructure(List<Map<String, ?>> flights) {
        for (Map<String, ?> flight : flights) {
            // Check if each key exists and has appropriate values
            Integer id = (Integer) flight.get("id");
            String from = (String) flight.get("from");
            String to = (String) flight.get("to");
            String date = (String) flight.get("date");

            assertNotNull(id, "Flight ID should not be null");
            assertNotNull(from, "Flight 'from' should not be null");
            assertNotNull(to, "Flight 'to' should not be null");
            assertNotNull(date, "Flight 'date' should not be null");
            assertTrue(date.matches("\\d{4}-\\d{2}-\\d{2}"), "Date does not match the expected format YYYY-MM-DD");
        }
    }
}
