package com.thoughtworks.mgttg.services;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.thoughtworks.mgttg.model.AnalyzedInput;
import com.thoughtworks.mgttg.model.ClassifiedInput;
import com.thoughtworks.mgttg.services.business.InputAnalyzerService;
import com.thoughtworks.mgttg.services.business.InputClassifierService;
import com.thoughtworks.mgttg.services.business.InputReaderService;
import com.thoughtworks.mgttg.services.business.impl.InputAnalyzerServiceImpl;
import com.thoughtworks.mgttg.services.business.impl.InputClassifierServiceImpl;
import com.thoughtworks.mgttg.services.business.impl.InputReaderServiceImpl;
import com.thoughtworks.mgttg.util.TestUtil;

/**
 * @author talipk
 *
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class InputAnalyzerServiceImplTest {

	TestUtil testUtil;
	InputReaderService inputReaderService;
	InputClassifierService inputClassifierService;
	InputAnalyzerService inputAnalyzerService;

	public InputAnalyzerServiceImplTest() {
		testUtil = new TestUtil();
		inputReaderService = new InputReaderServiceImpl();
		inputClassifierService = new InputClassifierServiceImpl();
		inputAnalyzerService = new InputAnalyzerServiceImpl();
	}

	@Test
	public void test1_analyzerServiceWithValidClassifiedInput() {

		List<String> allLines = inputReaderService.readAllLines(testUtil.getValidTestFilePath());
		ClassifiedInput classifiedInput = inputClassifierService.classify(allLines);
		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);

		Assert.assertNotNull(analyzedInput);
		Assert.assertNotEquals(0, analyzedInput.getCharacterDefinitions().size());
		Assert.assertNotEquals(0, analyzedInput.getMaterials().size());
		Assert.assertNotEquals(0, analyzedInput.getQuestions().size());

		Assert.assertEquals(4, analyzedInput.getCharacterDefinitions().size());
		Assert.assertEquals(3, analyzedInput.getMaterials().size());
		Assert.assertEquals(5, analyzedInput.getQuestions().size());

	}

	@Test
	public void test2_analyzerServiceWithGlobKeyOfICharacter() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);

		Assert.assertNotNull(analyzedInput);
		Assert.assertEquals(1, analyzedInput.getCharacterDefinitions().size());
		Assert.assertNotNull(analyzedInput.getCharacterDefinitions().get("glob"));
		Assert.assertEquals(new Integer(1), analyzedInput.getCharacterDefinitions().get("glob").getValue());

	}

	@Test
	public void test3_analyzerServiceWithSilverMaterial() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getMaterialsWithCharDefinitionsAndValue().add("glob glob Silver is 34 Credits");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);

		Assert.assertNotNull(analyzedInput);
		Assert.assertEquals(1, analyzedInput.getCharacterDefinitions().size());
		Assert.assertEquals(1, analyzedInput.getMaterials().size());
		Assert.assertNotNull(analyzedInput.getMaterials().get("Silver"));
		Assert.assertEquals(new Float(17), analyzedInput.getMaterials().get("Silver").getCharge());

	}

	@Test
	public void test4_analyzerServiceWithSilverQuestion() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is V");
		classifiedInput.getMaterialsWithCharDefinitionsAndValue().add("glob glob Silver is 34 Credits");
		classifiedInput.getQuestions().add("how many Credits is glob prok Silver ?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);

		Assert.assertNotNull(analyzedInput);
		Assert.assertEquals(2, analyzedInput.getCharacterDefinitions().size());
		Assert.assertEquals(1, analyzedInput.getMaterials().size());
		Assert.assertEquals(1, analyzedInput.getQuestions().size());
		Assert.assertEquals("Silver", analyzedInput.getQuestions().get(0).getMaterial().getName());

	}

	@Test
	public void test5_analyzerServiceWithSilverGlobProkQuestion() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is V");
		classifiedInput.getMaterialsWithCharDefinitionsAndValue().add("glob glob Silver is 34 Credits");
		classifiedInput.getQuestions().add("how many Credits is glob prok Silver ?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);

		Assert.assertNotNull(analyzedInput);
		Assert.assertEquals(2, analyzedInput.getCharacterDefinitions().size());
		Assert.assertEquals(1, analyzedInput.getMaterials().size());
		Assert.assertEquals(1, analyzedInput.getQuestions().size());
		Assert.assertEquals("Silver", analyzedInput.getQuestions().get(0).getMaterial().getName());

	}

	@Test
	public void test6_analyzerServiceWithUndefinedCharacter() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is Y");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);

		Assert.assertNotNull(analyzedInput);
		Assert.assertEquals(0, analyzedInput.getCharacterDefinitions().size());

	}

	@Test
	public void test7_analyzerServiceWithDefiningMaterialWithUndefinedCharacterKey() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getMaterialsWithCharDefinitionsAndValue().add("glob test Silver is 34 Credits");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);

		Assert.assertNotNull(analyzedInput);
		Assert.assertEquals(1, analyzedInput.getCharacterDefinitions().size());
		Assert.assertEquals(1, analyzedInput.getMaterials().size());
		Assert.assertNotNull(analyzedInput.getMaterials().get("Silver"));
		Assert.assertEquals(new Float(34), analyzedInput.getMaterials().get("Silver").getCharge());

	}

	@Test
	public void test8_analyzerServiceWithDefiningCharacterQuestion() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is V");
		classifiedInput.getQuestions().add("how much is glob prok ?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);

		Assert.assertNotNull(analyzedInput);
		Assert.assertEquals(2, analyzedInput.getCharacterDefinitions().size());
		Assert.assertEquals(0, analyzedInput.getMaterials().size());
		Assert.assertEquals(1, analyzedInput.getQuestions().size());

	}

}
