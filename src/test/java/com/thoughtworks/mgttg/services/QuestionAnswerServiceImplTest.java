package com.thoughtworks.mgttg.services;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.thoughtworks.mgttg.model.AnalyzedInput;
import com.thoughtworks.mgttg.model.Answer;
import com.thoughtworks.mgttg.model.ClassifiedInput;
import com.thoughtworks.mgttg.services.business.InputAnalyzerService;
import com.thoughtworks.mgttg.services.business.InputClassifierService;
import com.thoughtworks.mgttg.services.business.InputReaderService;
import com.thoughtworks.mgttg.services.business.QuestionAnswerService;
import com.thoughtworks.mgttg.services.business.impl.InputAnalyzerServiceImpl;
import com.thoughtworks.mgttg.services.business.impl.InputClassifierServiceImpl;
import com.thoughtworks.mgttg.services.business.impl.InputReaderServiceImpl;
import com.thoughtworks.mgttg.services.business.impl.QuestionAnswerServiceImpl;
import com.thoughtworks.mgttg.util.TestUtil;

/**
 * @author talipk
 *
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class QuestionAnswerServiceImplTest {

	TestUtil testUtil;
	QuestionAnswerService questionAnswerService;
	InputReaderService inputReaderService;
	InputClassifierService inputClassifierService;
	InputAnalyzerService inputAnalyzerService;

	public QuestionAnswerServiceImplTest() {
		testUtil = new TestUtil();
		questionAnswerService = new QuestionAnswerServiceImpl();
		inputReaderService = new InputReaderServiceImpl();
		inputClassifierService = new InputClassifierServiceImpl();
		inputAnalyzerService = new InputAnalyzerServiceImpl();
	}

	@Test
	public void test1_answerServiceWithValidClassifiedInput() {

		List<String> allLines = inputReaderService.readAllLines(testUtil.getValidTestFilePath());
		ClassifiedInput classifiedInput = inputClassifierService.classify(allLines);
		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);
		List<Answer> answers = questionAnswerService.answerQuestions(analyzedInput.getQuestions());

		Assert.assertNotNull(answers);
		Assert.assertFalse(answers.isEmpty());

	}

	@Test
	public void test2_answerServiceWithUnknownQuestion() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is V");
		classifiedInput.getMaterialsWithCharDefinitionsAndValue().add("glob glob Silver is 34 Credits");
		classifiedInput.getQuestions().add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);
		List<Answer> answers = questionAnswerService.answerQuestions(analyzedInput.getQuestions());

		Assert.assertFalse(answers.isEmpty());
		Assert.assertEquals("I have no idea what you are talking about", answers.get(0).getText());

	}

	@Test
	public void test3_answerServiceWithValidSilverQuestion() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is V");
		classifiedInput.getMaterialsWithCharDefinitionsAndValue().add("glob glob Silver is 34 Credits");
		classifiedInput.getQuestions().add("how many Credits is glob prok Silver ?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);
		List<Answer> answers = questionAnswerService.answerQuestions(analyzedInput.getQuestions());

		Assert.assertFalse(answers.isEmpty());
		Assert.assertEquals("glob prok Silver is 68", answers.get(0).getText());

	}

	@Test
	public void test4_answerServiceWithValidGoldQuestion() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is V");
		classifiedInput.getMaterialsWithCharDefinitionsAndValue().add("glob prok Gold is 57800 Credits");
		classifiedInput.getQuestions().add("how many Credits is glob prok Gold ?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);
		List<Answer> answers = questionAnswerService.answerQuestions(analyzedInput.getQuestions());

		Assert.assertFalse(answers.isEmpty());
		Assert.assertEquals("glob prok Gold is 57800", answers.get(0).getText());

	}

	@Test
	public void test5_answerServiceWithValidIronQuestion() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is V");
		classifiedInput.getCharacterDefinitions().add("pish is X");
		classifiedInput.getMaterialsWithCharDefinitionsAndValue().add("pish pish Iron is 3910 Credits");
		classifiedInput.getQuestions().add("how many Credits is glob prok Iron ?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);
		List<Answer> answers = questionAnswerService.answerQuestions(analyzedInput.getQuestions());

		Assert.assertFalse(answers.isEmpty());
		Assert.assertEquals("glob prok Iron is 782", answers.get(0).getText());

	}

	@Test
	public void test6_answerServiceWithInValidQuestionCharacterKeys() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is V");
		classifiedInput.getCharacterDefinitions().add("pish is X");
		classifiedInput.getMaterialsWithCharDefinitionsAndValue().add("pish pish Iron is 3910 Credits");
		classifiedInput.getQuestions().add("how many Credits is glob test example prok Iron ?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);
		List<Answer> answers = questionAnswerService.answerQuestions(analyzedInput.getQuestions());

		Assert.assertFalse(answers.isEmpty());
		Assert.assertEquals("glob test example prok Iron is 782", answers.get(0).getText());

	}

	@Test
	public void test7_answerServiceWithCharacterQuestion() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is V");
		classifiedInput.getCharacterDefinitions().add("pish is X");
		classifiedInput.getCharacterDefinitions().add("tegj is L");
		classifiedInput.getQuestions().add("how much is pish tegj glob glob ?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);
		List<Answer> answers = questionAnswerService.answerQuestions(analyzedInput.getQuestions());

		Assert.assertFalse(answers.isEmpty());
		Assert.assertEquals("pish tegj glob glob is 42", answers.get(0).getText());

	}

	@Test
	public void test8_answerServiceWithInvalidCharacterQuestionForIRepetition() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is V");
		classifiedInput.getCharacterDefinitions().add("pish is X");
		classifiedInput.getCharacterDefinitions().add("tegj is L");
		classifiedInput.getQuestions().add("how much is pish tegj glob glob glob glob ?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);
		List<Answer> answers = questionAnswerService.answerQuestions(analyzedInput.getQuestions());

		Assert.assertFalse(answers.isEmpty());
		Assert.assertEquals("pish tegj glob glob glob glob - this question is unvalid - I character is not valid with 4 repetition!", answers.get(0).getText());

	}

	@Test
	public void test9_answerServiceWithInvalidCharacterQuestionForDRepetition() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is D");
		classifiedInput.getCharacterDefinitions().add("pish is X");
		classifiedInput.getCharacterDefinitions().add("tegj is L");
		classifiedInput.getQuestions().add("how much is pish prok tegj prok prok?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);
		List<Answer> answers = questionAnswerService.answerQuestions(analyzedInput.getQuestions());

		Assert.assertFalse(answers.isEmpty());
		Assert.assertEquals("pish prok tegj prok prok - this question is unvalid - D character is not valid with 2 repetition!", answers.get(0).getText());

	}

	@Test
	public void test9_answerServiceWithCharacterQuestionForSubstractables() {

		ClassifiedInput classifiedInput = new ClassifiedInput();
		classifiedInput.getCharacterDefinitions().add("glob is I");
		classifiedInput.getCharacterDefinitions().add("prok is D");
		classifiedInput.getCharacterDefinitions().add("pish is X");
		classifiedInput.getCharacterDefinitions().add("tegj is L");
		classifiedInput.getCharacterDefinitions().add("yetf is C");
		classifiedInput.getCharacterDefinitions().add("fuyh is M");
		classifiedInput.getCharacterDefinitions().add("tetr is V");
		classifiedInput.getQuestions().add("how much is fuyh yetf fuyh pish tegj glob tetr?");

		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);
		List<Answer> answers = questionAnswerService.answerQuestions(analyzedInput.getQuestions());

		Assert.assertFalse(answers.isEmpty());
		Assert.assertEquals("fuyh yetf fuyh pish tegj glob tetr is 1944", answers.get(0).getText());

	}

}
