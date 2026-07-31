package com.example.h2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IndustryJdbcExampleTest {

    @Test
    void runDemoCompletesSuccessfully() throws Exception {
        String output = new IndustryJdbcExample().runDemo();
        assertTrue(output.contains("Transfer complete"));
    }
}
