package com.example.demo.util;

public class ExecutionTime {

    public static double elapsedProcessTimeSeconds(Long startTime) throws InterruptedException {

        long endTime = System.currentTimeMillis();

        // Calculate elapsed time in milliseconds
        long elapsedTime = endTime - startTime;

        // Optionally, convert milliseconds to seconds:

        // Return the elapsed time
        return (double) elapsedTime / 1000; // or elapsedSeconds if you want seconds

    }

}
