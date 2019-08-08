package com.example.anz.core;

import com.example.anz.model.PersonBirthDetails;

import java.util.*;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class CensusCalculator {
    // Using 9999 which is big number to represent the person is not dead
    private final static Integer ALIVE = 9999;

    // This operation takes O(N), as we do one iteration over the list of N items & get a sorted array of years
    public TreeMap<Integer, Integer> createPopulationHistogram(List<PersonBirthDetails> personBirthDetailsList){
        HashMap<Integer, Integer> histogram = new HashMap<>();

        personBirthDetailsList.forEach(personDetails -> {
            // Code handling addition of birth year to the new histogram
            if (histogram.get(personDetails.getBirth()) != null){
                histogram.put(personDetails.getBirth(), histogram.get(personDetails.getBirth())+1);
            }else{
                histogram.put(personDetails.getBirth(),1);
            }

            // Code handling removal of the population count after a person is dead
            if (personDetails.getDeath().intValue() != ALIVE) {
                if (histogram.get(personDetails.getDeath() + 1) != null) {
                    histogram.put(personDetails.getDeath() + 1, histogram.get(personDetails.getDeath().intValue()+1) - 1);
                } else {
                    histogram.put(personDetails.getDeath()+1, -1);
                }
            }
        });

        return getSortedMap(histogram);
    }

    // This operation takes O(N), as we do one iteration over the list of N items & get a list of filtered values
    public List<Integer> getListOfYearsHavingPopulationDecline (TreeMap<Integer, Integer> histogramSorted){
        List<Integer> yearsHavingReducedPopulationThanPrevious = new ArrayList<>();
        histogramSorted.forEach((yearOfBirth, value)->{
            if (value < 0){
                yearsHavingReducedPopulationThanPrevious.add(yearOfBirth);
            }
        });
        return yearsHavingReducedPopulationThanPrevious;
    }

    // This operation takes O(N), as we do one iteration over the list of N items & get a sorted array of years
    private TreeMap<Integer, Integer> getSortedMap (HashMap<Integer, Integer> histogram){
        // TreeMap to store values of HashMap
        TreeMap<Integer, Integer> sortedHistogram = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sortedHistogram.putAll(histogram);

        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Integer> entry : sortedHistogram.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());

        return sortedHistogram;
    }
}
