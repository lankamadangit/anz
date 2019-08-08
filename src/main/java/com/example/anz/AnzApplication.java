package com.example.anz;

import com.example.anz.core.CensusCalculator;
import com.example.anz.model.PersonBirthDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class AnzApplication {
	// Using 9999 which is big number to represent the person is not dead
	private final static Integer ALIVE = 9999;

	public static void main(String[] args) {
		CensusCalculator censusCalculator = new CensusCalculator();

		List<PersonBirthDetails> populationMap = new ArrayList<>();

		populationMap.add(new PersonBirthDetails(1902, 1991));
		populationMap.add(new PersonBirthDetails(1941, 1978));
		populationMap.add(new PersonBirthDetails(2004, ALIVE));
		populationMap.add(new PersonBirthDetails(1957, ALIVE));
		populationMap.add(new PersonBirthDetails(1989, 2008));
		populationMap.add(new PersonBirthDetails(1909, 2005));
		populationMap.add(new PersonBirthDetails(1918, ALIVE));
		populationMap.add(new PersonBirthDetails(1913, 2010));
		populationMap.add(new PersonBirthDetails(1979, ALIVE));
		populationMap.add(new PersonBirthDetails(1961, 2002));
		populationMap.add(new PersonBirthDetails(1977, 2003));
		populationMap.add(new PersonBirthDetails(1909, 1991));

		// This operation takes O(N)
		TreeMap<Integer, Integer> histogramMap = censusCalculator.createPopulationHistogram(populationMap);

		// This operation takes O(N)
		List<Integer> yearsHavingReducedPopulationThanPrevious =
				censusCalculator.getListOfYearsHavingPopulationDecline(histogramMap);

		// This operation takes O(N)
		yearsHavingReducedPopulationThanPrevious.stream().forEach(year -> System.out.println(year));

		// The solution is implemented using O(N) + O(N) + O(N) = constant + O(N), which is O(N)
	}

}
