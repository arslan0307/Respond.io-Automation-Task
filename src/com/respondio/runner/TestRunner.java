package com.respondio.runner;

import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add("testng.xml");
        testng.setTestSuites(suites);
        testng.run();
    }
}
