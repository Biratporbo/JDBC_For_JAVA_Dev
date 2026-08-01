package com.example.h2;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class MoreH2ExamplesTest {

    @Test
    void runCrudDemoReturnsProductRows() throws Exception {
        String output = new MoreH2Examples().runCrudDemo();
        assertTrue(output.contains("Laptop"));
        assertTrue(output.contains("Smartphone"));
    }

    @Test
    void runBatchDemoShowsInsertedRows() throws Exception {
        String output = new MoreH2Examples().runBatchDemo();
        assertTrue(output.contains("Inserted 3 rows"));
    }

    @Test
    void runJoinDemoShowsCustomerOrders() throws Exception {
        String output = new MoreH2Examples().runJoinDemo();
        assertTrue(output.contains("Alice"));
        assertTrue(output.contains("Laptop"));
    }
}
