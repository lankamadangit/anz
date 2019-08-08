package com.example.anz;

import com.example.anz.core.CensusCalculator;
import com.example.anz.model.PersonBirthDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AnzApplicationTests {
	// Using 9999 which is big number to represent the person is not dead
	private final static Integer ALIVE = 9999;

	private CensusCalculator censusCalculator;

	@Before
	public void setup() {
		censusCalculator = new CensusCalculator();
	}

	@Test
	public void givenEmptyDataThenReturnEmptyList() {
		//Given
		List<PersonBirthDetails> personBirthDetailsList = new ArrayList<>();

		// When
		TreeMap<Integer, Integer> censusHistogram = censusCalculator.createPopulationHistogram(personBirthDetailsList);

		//Then
		assertEquals(censusHistogram.size(), 0);
	}


	@Test
	public void givenValidDataThenReturnProperList() {
		//Given
		List<PersonBirthDetails> personBirthDetailsList = new ArrayList<>();

		personBirthDetailsList.add(new PersonBirthDetails(1902, 1991));
		personBirthDetailsList.add(new PersonBirthDetails(1941, 1978));
		personBirthDetailsList.add(new PersonBirthDetails(2004, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1957, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1989, 2008));
		personBirthDetailsList.add(new PersonBirthDetails(1909, 2005));
		personBirthDetailsList.add(new PersonBirthDetails(1918, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1913, 2010));
		personBirthDetailsList.add(new PersonBirthDetails(1979, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1961, 2002));
		personBirthDetailsList.add(new PersonBirthDetails(1977, 2003));
		personBirthDetailsList.add(new PersonBirthDetails(1909, 1991));


		// When
		TreeMap<Integer, Integer> censusHistogram = censusCalculator.createPopulationHistogram(personBirthDetailsList);

		//Then
		assertEquals(censusHistogram.size(), 16);
	}

	@Test
	public void givenValidDataThenReturnListOfYearsHavingReducedYears() {
		//Given
		List<PersonBirthDetails> personBirthDetailsList = new ArrayList<>();

		personBirthDetailsList.add(new PersonBirthDetails(1902, 1991));
		personBirthDetailsList.add(new PersonBirthDetails(1941, 1978));
		personBirthDetailsList.add(new PersonBirthDetails(2004, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1957, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1989, 2008));
		personBirthDetailsList.add(new PersonBirthDetails(1909, 2005));
		personBirthDetailsList.add(new PersonBirthDetails(1918, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1913, 2010));
		personBirthDetailsList.add(new PersonBirthDetails(1979, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1961, 2002));
		personBirthDetailsList.add(new PersonBirthDetails(1977, 2003));
		personBirthDetailsList.add(new PersonBirthDetails(1909, 1991));


		// When
		TreeMap<Integer, Integer> histogramMap = censusCalculator.createPopulationHistogram(personBirthDetailsList);

		List<Integer> yearsHavingReducedPopulationThanPrevious =
				censusCalculator.getListOfYearsHavingPopulationDecline(histogramMap);

		//Then
		assertEquals(yearsHavingReducedPopulationThanPrevious.size(), 5);
	}

	@Test
	public void givenAllAliveDataThenReturnListOfYearsHavingNoReducedYears() {
		//Given
		List<PersonBirthDetails> personBirthDetailsList = new ArrayList<>();

		personBirthDetailsList.add(new PersonBirthDetails(1902, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1941, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(2004, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1957, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1989, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1909, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1918, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1913, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1979, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1961, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1977, ALIVE));
		personBirthDetailsList.add(new PersonBirthDetails(1909, ALIVE));


		// When
		TreeMap<Integer, Integer> histogramMap = censusCalculator.createPopulationHistogram(personBirthDetailsList);

		List<Integer> yearsHavingReducedPopulationThanPrevious =
				censusCalculator.getListOfYearsHavingPopulationDecline(histogramMap);

		//Then
		assertEquals(yearsHavingReducedPopulationThanPrevious.size(), 0);
	}

	@Test
	public void givenAllDeadInSameYearDataThenReturnListOfYearsHavingOneReducedYearsResponse() {
		//Given
		List<PersonBirthDetails> personBirthDetailsList = new ArrayList<>();

		personBirthDetailsList.add(new PersonBirthDetails(1902, 2005));
		personBirthDetailsList.add(new PersonBirthDetails(1941, 2005));
		personBirthDetailsList.add(new PersonBirthDetails(1956, 2005));


		// When
		TreeMap<Integer, Integer> histogramMap = censusCalculator.createPopulationHistogram(personBirthDetailsList);

		List<Integer> yearsHavingReducedPopulationThanPrevious =
				censusCalculator.getListOfYearsHavingPopulationDecline(histogramMap);

		//Then
		assertEquals(yearsHavingReducedPopulationThanPrevious.size(), 1);
	}
}
