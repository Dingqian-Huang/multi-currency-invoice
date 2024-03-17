package com.greengate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MultiCurrencyInvoiceTest {
    private static final String BASE_URL = "http://localhost:8080";
    private static final String ENDPOINT = "/invoice/total";

    public static Stream<Arguments> generateTestCases() {
        TestCasesGenerator generator = new TestCasesGenerator(){};
        List<TestCase> testCases = generator.getTestCases();
        return testCases
                .stream()
                .sorted(Comparator.comparing(TestCase::getName))
                .map((testCase)-> Arguments.of(testCase.getName(), testCase.getStatusCode(),
                        testCase.getPostBody(), testCase.getResponseBody()));
    }

    @ParameterizedTest
    @MethodSource("generateTestCases")
    public void testSuccessfulAPI(String name, int statusCode, String postBody, String expectedOutput) {
        given().body(postBody)
                .contentType("application/json")
                .baseUri(BASE_URL)
                .when()
                .post(ENDPOINT)
                .then()
                .statusCode(statusCode)
                .contentType("plain/text")
                .body(equalTo(expectedOutput));
    }
}
