package com.greengate;

import java.util.Arrays;
import java.util.List;

public interface TestCasesGenerator {

    default List<TestCase> getTestCases() {
        return Arrays.asList(
                new TestCase("Happy Path", 200,  "{\n" +
                        "  \"invoice\": {\n" +
                        "    \"currency\": \"NZD\",\n" +
                        "    \"date\": \"2020-07-07\",\n" +
                        "    \"lines\": [\n" +
                        "      {\n" +
                        "        \"description\": \"Intel Core i9\",\n" +
                        "        \"currency\": \"USD\",\n" +
                        "        \"amount\": 700\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"description\": \"ASUS ROG Strix\",\n" +
                        "        \"currency\": \"AUD\",\n" +
                        "        \"amount\": 500\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}", "1600.9")
        );
    }
}