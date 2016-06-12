package com.thoughtworks.mgttg.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.thoughtworks.mgttg.exception.InvalidRepetitionCountException;
import com.thoughtworks.mgttg.model.AlphabetCharacter;
import com.thoughtworks.mgttg.model.factory.AlphabetCharacterFactory;
import com.thoughtworks.mgttg.services.business.CharacterValueCalculatorService;
import com.thoughtworks.mgttg.services.business.InputAnalyzerService;
import com.thoughtworks.mgttg.services.business.InputClassifierService;
import com.thoughtworks.mgttg.services.business.InputReaderService;
import com.thoughtworks.mgttg.services.business.impl.CharacterValueCalculatorServiceImpl;
import com.thoughtworks.mgttg.services.business.impl.InputAnalyzerServiceImpl;
import com.thoughtworks.mgttg.services.business.impl.InputClassifierServiceImpl;
import com.thoughtworks.mgttg.services.business.impl.InputReaderServiceImpl;
import com.thoughtworks.mgttg.util.TestUtil;

/**
 * @author talipk
 *
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class CharacterValueCalculatorServiceTest {

	TestUtil testUtil;
	InputReaderService inputReaderService;
	InputClassifierService inputClassifierService;
	InputAnalyzerService inputAnalyzerService;
	CharacterValueCalculatorService characterValueCalculatorService;

	public CharacterValueCalculatorServiceTest() {
		testUtil = new TestUtil();
		inputReaderService = new InputReaderServiceImpl();
		inputClassifierService = new InputClassifierServiceImpl();
		inputAnalyzerService = new InputAnalyzerServiceImpl();
		characterValueCalculatorService = new CharacterValueCalculatorServiceImpl();
	}

	@Test
	public void test1_calculatorServiceWithIMICharacters() {

		List<AlphabetCharacter> alphabetCharacters = new ArrayList<>();
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("I"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("M"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("I"));

		Exception exceptionOccured = null;
		try{
			Assert.assertEquals(new Integer(1002), characterValueCalculatorService.calculate(alphabetCharacters));
		} catch (InvalidRepetitionCountException e){
			exceptionOccured = e;
		}
		Assert.assertNull(exceptionOccured);

	}

	@Test
	public void test2_calculatorServiceWithMCMIIICharacters() {

		List<AlphabetCharacter> alphabetCharacters = new ArrayList<>();
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("M"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("C"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("M"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("I"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("I"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("I"));

		Exception exceptionOccured = null;
		try{
			Assert.assertEquals(new Integer(1903), characterValueCalculatorService.calculate(alphabetCharacters));
		} catch (InvalidRepetitionCountException e){
			exceptionOccured = e;
		}
		Assert.assertNull(exceptionOccured);

	}

	@Test
	public void test3_calculatorServiceWithXLVICharacters() {

		List<AlphabetCharacter> alphabetCharacters = new ArrayList<>();
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("X"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("L"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("V"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("I"));

		Exception exceptionOccured = null;
		try{
			Assert.assertEquals(new Integer(46), characterValueCalculatorService.calculate(alphabetCharacters));
		} catch (InvalidRepetitionCountException e){
			exceptionOccured = e;
		}
		Assert.assertNull(exceptionOccured);

	}

	@Test
	public void test4_calculatorServiceWithXIIIICharacters() {

		List<AlphabetCharacter> alphabetCharacters = new ArrayList<>();
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("X"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("I"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("I"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("I"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("I"));

		Exception exceptionOccured = null;
		try{
			characterValueCalculatorService.calculate(alphabetCharacters);
		} catch (InvalidRepetitionCountException e){
			exceptionOccured = e;
		}
		Assert.assertNotNull(exceptionOccured);

	}

	@Test
	public void test5_calculatorServiceWithXVVCharacters() {

		List<AlphabetCharacter> alphabetCharacters = new ArrayList<>();
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("X"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("V"));
		alphabetCharacters.add(AlphabetCharacterFactory.getInstance().getAlphabetCharacter("V"));

		Exception exceptionOccured = null;
		try{
			characterValueCalculatorService.calculate(alphabetCharacters);
		} catch (InvalidRepetitionCountException e){
			exceptionOccured = e;
		}
		Assert.assertNotNull(exceptionOccured);

	}

}
