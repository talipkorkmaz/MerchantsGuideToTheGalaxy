package com.thoughtworks.mgttg.services.business.impl;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.mgttg.exception.InvalidRepetitionCountException;
import com.thoughtworks.mgttg.model.Answer;
import com.thoughtworks.mgttg.model.Question;
import com.thoughtworks.mgttg.services.business.CharacterValueCalculatorService;
import com.thoughtworks.mgttg.services.business.QuestionAnswerService;

/**
 * @author talipk
 *
 */
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

	private CharacterValueCalculatorService characterValueCalculatorService;

	public QuestionAnswerServiceImpl() {
		characterValueCalculatorService = new CharacterValueCalculatorServiceImpl();
	}

	@Override
	public List<Answer> answerQuestions(List<Question> questions) {

		List<Answer> answers = new ArrayList<>();

		questions.forEach(question -> {
			switch (question.getQuestionTypeEnum()) {
				case Q_CHARACTER_CALCULATION :

					calculateForCharacter(question, answers);

					break;
				case Q_MATERIAL_COST_CALCULATION :

					calculateForMaterialCost(question, answers);

					break;

				case Q_UNDEFINED :

					answers.add(new Answer("I have no idea what you are talking about"));

					break;

				case Q_UNDEFINED_MATERIAL :

					answers.add(new Answer(question.getText().trim() + " - " + "material is unknown"));

					break;
				default :
					break;

			}
		});

		return answers;

	}

	private void calculateForCharacter(Question question, List<Answer> responses) {
		try{
			Integer totalValue = characterValueCalculatorService.calculate(question.getCharacters());
			responses.add(new Answer(question.getText().trim() + " is " + totalValue));
		} catch (InvalidRepetitionCountException e){
			responses.add(new Answer(question.getText().trim() + " - this question is unvalid - " + e.getMessage()));
		}
	}

	private void calculateForMaterialCost(Question question, List<Answer> responses) {
		try{
			// check material 
			if(question.getMaterial() == null){
				responses.add(new Answer(question.getText().trim() + " - material is not defined"));
			} else{
				Integer totalValue = characterValueCalculatorService.calculate(question.getCharacters());
				responses.add(new Answer(question.getText().trim() + " is " + Math.round(totalValue * question.getMaterial().getCharge())));
			}
		} catch (InvalidRepetitionCountException e){
			responses.add(new Answer(question.getText().trim() + " - this question is unvalid - " + e.getMessage()));
		}
	}

}
