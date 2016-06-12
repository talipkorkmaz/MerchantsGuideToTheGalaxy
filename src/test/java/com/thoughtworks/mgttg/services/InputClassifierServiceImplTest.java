package com.thoughtworks.mgttg.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.thoughtworks.mgttg.model.ClassifiedInput;
import com.thoughtworks.mgttg.services.business.InputClassifierService;
import com.thoughtworks.mgttg.services.business.InputReaderService;
import com.thoughtworks.mgttg.services.business.impl.InputClassifierServiceImpl;
import com.thoughtworks.mgttg.services.business.impl.InputReaderServiceImpl;
import com.thoughtworks.mgttg.util.TestUtil;

/**
 * @author talipk
 *
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class InputClassifierServiceImplTest {

	TestUtil testUtil;
	InputReaderService inputReaderService;
	InputClassifierService inputClassifierService;

	public InputClassifierServiceImplTest() {
		testUtil = new TestUtil();
		inputReaderService = new InputReaderServiceImpl();
		inputClassifierService = new InputClassifierServiceImpl();
	}

	@Test
	public void test1_classifierServiceWithValidPathArgument() {

		List<String> allLines = inputReaderService.readAllLines(testUtil.getValidTestFilePath());
		ClassifiedInput classifiedInput = inputClassifierService.classify(allLines);
		Assert.assertNotNull(classifiedInput);
		Assert.assertNotEquals(0, classifiedInput.getCharacterDefinitions().size());
		Assert.assertNotEquals(0, classifiedInput.getMaterialsWithCharDefinitionsAndValue().size());
		Assert.assertNotEquals(0, classifiedInput.getQuestions().size());

	}

	@Test
	public void test2_classifierServiceUnknownUnputLine() {

		List<String> allLines = new ArrayList<>();
		allLines.add("this is a unknown input line");
		ClassifiedInput classifiedInput = inputClassifierService.classify(allLines);

		Assert.assertNotNull(classifiedInput);
		Assert.assertEquals(0, classifiedInput.getCharacterDefinitions().size());
		Assert.assertEquals(0, classifiedInput.getMaterialsWithCharDefinitionsAndValue().size());
		Assert.assertEquals(0, classifiedInput.getQuestions().size());

	}

	@Test
	public void test3_classifierServiceCharacterDefinitionLine() {

		List<String> allLines = new ArrayList<>();
		allLines.add("pish is X");
		ClassifiedInput classifiedInput = inputClassifierService.classify(allLines);

		Assert.assertNotNull(classifiedInput);
		Assert.assertEquals(1, classifiedInput.getCharacterDefinitions().size());
		Assert.assertEquals(0, classifiedInput.getMaterialsWithCharDefinitionsAndValue().size());
		Assert.assertEquals(0, classifiedInput.getQuestions().size());

	}

	@Test
	public void test4_classifierServiceMaterialDefinitionLine() {

		List<String> allLines = new ArrayList<>();
		allLines.add("pish pish Iron is 3910 Credits");
		ClassifiedInput classifiedInput = inputClassifierService.classify(allLines);

		Assert.assertNotNull(classifiedInput);
		Assert.assertEquals(0, classifiedInput.getCharacterDefinitions().size());
		Assert.assertEquals(1, classifiedInput.getMaterialsWithCharDefinitionsAndValue().size());
		Assert.assertEquals(0, classifiedInput.getQuestions().size());

	}

	@Test
	public void test5_classifierServiceQuestionLine() {

		List<String> allLines = new ArrayList<>();
		allLines.add("how many Credits is glob prok Iron ?");
		allLines.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
		ClassifiedInput classifiedInput = inputClassifierService.classify(allLines);

		Assert.assertNotNull(classifiedInput);
		Assert.assertEquals(0, classifiedInput.getCharacterDefinitions().size());
		Assert.assertEquals(0, classifiedInput.getMaterialsWithCharDefinitionsAndValue().size());
		Assert.assertEquals(2, classifiedInput.getQuestions().size());

	}
}
