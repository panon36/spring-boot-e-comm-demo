package com.example.demo.util;

public class ExecutionTime {

    public static double elapsedProcessTimeSeconds(Long startTime) throws InterruptedException {

        long endTime = System.currentTimeMillis();

        // Calculate elapsed time in milliseconds
        long elapsedTime = endTime - startTime;

        // Optionally, convert milliseconds to seconds:
        double elapsedSeconds = (double) elapsedTime / 1000;

        // Return the elapsed time
        return elapsedSeconds; // or elapsedSeconds if you want seconds

    }

}
