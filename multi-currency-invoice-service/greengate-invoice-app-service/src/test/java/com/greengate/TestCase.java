package com.greengate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCase {
    String name;
    int statusCode;
    String postBody;
    String responseBody;
}