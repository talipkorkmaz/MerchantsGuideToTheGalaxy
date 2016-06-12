package com.thoughtworks.mgttg.services.business.impl;

import java.util.List;

import com.thoughtworks.mgttg.model.AnalyzedInput;
import com.thoughtworks.mgttg.model.Answer;
import com.thoughtworks.mgttg.model.ClassifiedInput;
import com.thoughtworks.mgttg.services.business.InputAnalyzerService;
import com.thoughtworks.mgttg.services.business.InputClassifierService;
import com.thoughtworks.mgttg.services.business.InputReaderService;
import com.thoughtworks.mgttg.services.business.MainApplicationService;
import com.thoughtworks.mgttg.services.business.QuestionAnswerService;

/**
 * @author talipk
 *
 */
public class MainApplicationServiceImpl implements MainApplicationService {

	private InputReaderService inputReaderService;
	private InputClassifierService inputClassifierService;
	private InputAnalyzerService inputAnalyzerService;
	private QuestionAnswerService questionAnswerService;

	public MainApplicationServiceImpl() {
		inputReaderService = new InputReaderServiceImpl();
		this.inputClassifierService = new InputClassifierServiceImpl();
		this.inputAnalyzerService = new InputAnalyzerServiceImpl();
		this.questionAnswerService = new QuestionAnswerServiceImpl();
	}

	@Override
	public List<Answer> processInputLines(String fileName) {

		List<String> allLines = inputReaderService.readAllLines(fileName);

		// classify input lines
		ClassifiedInput classifiedInput = inputClassifierService.classify(allLines);

		// analyze input lines , convert to object model and generate questions
		AnalyzedInput analyzedInput = inputAnalyzerService.analyze(classifiedInput);

		// answer questions
		return questionAnswerService.answerQuestions(analyzedInput.getQuestions());

	}

}
