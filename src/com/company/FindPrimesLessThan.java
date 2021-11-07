package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class FindPrimesLessThan {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int ending_search_number = 0;
        ArrayList<Boolean> boolArrayList;

        if(parameterIsValid(args)) {
            ending_search_number = Integer.parseInt(args[0]);
            boolArrayList = create_array_list(ending_search_number);
            find_non_primes(2, boolArrayList);
            print_execution_time(startTime, "finished finding prime numbers to: " + ending_search_number);
            print_array_list(0, boolArrayList);
            print_execution_time(startTime, "total execution time");
        }
    }

    // traverse arrayList to find all non-prime numbers
    private static void find_non_primes(int currentIndex, ArrayList<Boolean> boolArrayList) {
        if (currentIndex < Math.sqrt(boolArrayList.size())) {
            // check to make sure current isn't a factor of some previous number
            // For example, if all multiples of 3 are set to false, then multiples of 6 don't need to be checked.
            if (boolArrayList.get(currentIndex)) {
                set_non_primes_to_false(currentIndex, 0, boolArrayList);
            }
            find_non_primes(currentIndex + 1, boolArrayList);
        }
    }

    // traverse the array and set all non-primes to false
    private static void set_non_primes_to_false(int startingAt, int multiple, ArrayList<Boolean> boolArray) {
        // recursive case
        int nonPrimeNumber = (startingAt * startingAt) + (multiple * startingAt);
        if (nonPrimeNumber < boolArray.size()) {
            boolArray.set(nonPrimeNumber, false);
            set_non_primes_to_false(startingAt, multiple + 1, boolArray);
        }
        // base case return automatically
    }

    // create array list from 1 to maximum_search_number and set all elements with a TRUE value
    private static ArrayList<Boolean> create_array_list(int maximum_search_number) {
        Boolean[] boolArray = new Boolean[maximum_search_number];
        Arrays.fill(boolArray, Boolean.TRUE);
        return new ArrayList<Boolean>(Arrays.asList(boolArray));
    }

    // traverse array and print only the primes (the elements still set to true)
    private static void print_array_list(int currentIndex, ArrayList<Boolean> boolArray) {
        // recursive case
        if (currentIndex < boolArray.size()) {
            if (boolArray.get(currentIndex) && currentIndex != 0) {
                System.out.println(String.valueOf(currentIndex));
            }
            print_array_list(currentIndex + 1, boolArray);
        }
        // base case automatically returns
    }


    // calculate and print the number of milliseconds the program took to execute
    private static void print_execution_time(long start_time, String description) {
        long elapsedNanoSeconds = System.nanoTime() - start_time;
        double elapsedTime = TimeUnit.MILLISECONDS.convert(elapsedNanoSeconds, TimeUnit.NANOSECONDS);
        System.out.println(description + ": " + String.valueOf(elapsedTime/1000));
    }

    // sanity check because I know Alex will try to break my program.  :-)
    private static boolean parameterIsValid(String[] args) {

        // TODO - check parameter is an integer

        if (args.length != 1) {
            System.out.println("You must provide one parameter - exiting");
            return false;
        }

        if (Integer.parseInt(args[0]) < 2) {
            System.out.println("Ending value not valid - exiting");
            return false;
        }

        return true;
    }
}
