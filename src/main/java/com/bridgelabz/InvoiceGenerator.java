package com.bridgelabz;

import java.util.HashMap;
import java.util.Map;

public class InvoiceGenerator {
    private Map<Integer, double[]> riderRepository = new HashMap<>();
    private Integer id = 0;

    /**
     * General method to take input frm user and return fares based on their requirement
     * @param customerType
     * @param distance
     * @param time
     * @return
     */
    public double calculateFareForCustomer(String customerType, double distance, int time) {
        if (customerType == "Regular") {
            return calculateFareRegular(distance, time);
        }
        if (customerType == "Premium") {
            return calculateFarePremium(distance, time);
        }
        System.out.println("Invalid customer type!");
        return -1;
    }

    /**
     * @param distance
     * @param time     FOR REGULAR CUSTOMERS ONLY
     * @return
     */
    public double calculateFareRegular(double distance, int time) {
        final double COST_PER_KM = 10;
        final int COST_PER_MIN = 1;
        final double MIN_RIDE_COST = 5;
        return calculateFare(COST_PER_KM, COST_PER_MIN, MIN_RIDE_COST, distance, time);
    }

    /**
     * @param distance
     * @param time     FOR PREMIUM CUSTOMERS ONLY
     * @return
     */
    public double calculateFarePremium(double distance, int time) {
        final double COST_PER_KM = 15;
        final int COST_PER_MIN = 2;
        final double MIN_RIDE_COST = 20;
        return calculateFare(COST_PER_KM, COST_PER_MIN, MIN_RIDE_COST, distance, time);
    }

    /**
     * @param distance
     * @param time     FOR ALL CUSTOMERS
     * @return
     */
    public double calculateFare(double COST_PER_KM, Integer COST_PER_MIN, double MIN_RIDE_COST, double distance, int time) {
        double fare = distance * COST_PER_KM + time * COST_PER_MIN;
        if (fare < MIN_RIDE_COST) {
            return MIN_RIDE_COST;
        }
        return fare;
    }

    /**
     * @param distanceArray
     * @param timeArray
     * @return
     */
    public double calculateFareRegular(double[] distanceArray, int[] timeArray) {
        double avg = 0;
        for (int i = 0; i < distanceArray.length; i++) {
            avg += calculateFareRegular(distanceArray[i], timeArray[i]);
        }
        return avg / distanceArray.length;
    }

    /**
     * @param distanceArray
     * @param timeArray
     * @return
     */
    public double[] calculateFareReturnArray(double[] distanceArray, int[] timeArray) {
        id++;
        double totalFare = 0;
        for (int i = 0; i < distanceArray.length; i++) {
            totalFare += calculateFareRegular(distanceArray[i], timeArray[i]);
        }
        double avg = totalFare / distanceArray.length;
        double[] elements = {distanceArray.length, totalFare, avg};
        riderRepository.put(id, elements);
        return elements;
    }

    public double[] getRepository(int userID) {
        return riderRepository.get(userID);
    }

}