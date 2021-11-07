package com.company;

import java.util.Arrays;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class FindPrimesLessThan {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        double secondsToExecute = 0;
        int i = 0;
        int j = 0;
        int ending_search_number = 0;

        // sanity check because I know Alex will
        // try to break my program.  :-)
        if (args.length != 1) {
            System.out.println("You must provide one parameter - exiting");
            return;
        }

        ending_search_number = Integer.parseInt(args[0]);

        if (ending_search_number < 2) {
            System.out.println("Ending value not valid - exiting");
            return;
        }



	    // create array from 1 to ending_search_number
        // and set all element with a TRUE value
        Boolean[] boolArray = new Boolean[ending_search_number];
        Arrays.fill(boolArray, Boolean.TRUE);

        // loop through array and set all multiple of "i" as false
        // we only need to search to the mid-point of the array
        for (i = 2; i < Math.sqrt(boolArray.length); i++) {
            // check to make sure isn't a factor of some previous
            // number.  For example, if we've already set all
            // multiples of 3 to false, then multiples of 6 don't
            // need to be checked.
            if (boolArray[i]) {
                for(j=i*i; j<boolArray.length; j=j+i) {
                    boolArray[j] = false;
                }
            }
        }

        // loop through the array and print only the
        // elements still set to true -- the primes
        for (i=1; i<boolArray.length; i++) {
            if (boolArray[i]) {
                System.out.println(String.valueOf(i));
            }
        }

        // calculate and print the number of milliseconds the program took to execute
        secondsToExecute = TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
        System.out.println("Seconds to execute: " + String.valueOf(secondsToExecute/1000));

    }
}
