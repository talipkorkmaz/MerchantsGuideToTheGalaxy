package com.thoughtworks.mgttg.services.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import com.thoughtworks.mgttg.exception.InvalidRepetitionCountException;
import com.thoughtworks.mgttg.model.AlphabetCharacter;
import com.thoughtworks.mgttg.model.AnalyzedInput;
import com.thoughtworks.mgttg.model.ClassifiedInput;
import com.thoughtworks.mgttg.model.Material;
import com.thoughtworks.mgttg.model.Question;
import com.thoughtworks.mgttg.model.enums.QuestionTypeEnum;
import com.thoughtworks.mgttg.model.factory.AlphabetCharacterFactory;
import com.thoughtworks.mgttg.services.business.CharacterValueCalculatorService;
import com.thoughtworks.mgttg.services.business.InputAnalyzerService;
import com.thoughtworks.mgttg.services.logger.Logger;
import com.thoughtworks.mgttg.services.logger.impl.LoggerImpl;
import com.thoughtworks.mgttg.util.Constants;

/**
 * @author talipk
 *
 */
public class InputAnalyzerServiceImpl implements InputAnalyzerService {

	private Logger logger = LoggerImpl.getInstance();

	private CharacterValueCalculatorService characterValueCalculatorService;

	public InputAnalyzerServiceImpl() {
		characterValueCalculatorService = new CharacterValueCalculatorServiceImpl();
	}

	@Override
	public AnalyzedInput analyze(ClassifiedInput classifiedInput) {

		AnalyzedInput analyzedInput = new AnalyzedInput();

		analyzeCharacterDefinitions(classifiedInput, analyzedInput);

		analyzeMaterialDefinitions(classifiedInput, analyzedInput);

		analyzeQuestions(classifiedInput, analyzedInput);

		return analyzedInput;
	}

	private void analyzeQuestions(ClassifiedInput classifiedInput, AnalyzedInput analyzedInput) {

		if(classifiedInput != null && classifiedInput.getQuestions() != null){

			classifiedInput.getQuestions().forEach(questionForMaterialCalculation -> {
				Matcher matcher = Constants.P_QUESTION.matcher(questionForMaterialCalculation);
				if(matcher.find()){

					List<String> characterKeys = Arrays.asList(matcher.group(3).split(" "));
					List<AlphabetCharacter> alphabetCharacters = convertToAlphabetCharacters(characterKeys, analyzedInput.getCharacterDefinitions());

					Material material = analyzedInput.getMaterials().get(characterKeys.get(characterKeys.size() - 1));
					Question question;
					if(material != null){
						question = new Question(QuestionTypeEnum.Q_MATERIAL_COST_CALCULATION, matcher.group(3), material, alphabetCharacters);
					} else{
						if(alphabetCharacters.isEmpty()){
							question = new Question(QuestionTypeEnum.Q_UNDEFINED, matcher.group(3), alphabetCharacters);
						} else{

							Matcher materialMatcher = Constants.P_QUESTION_WITH_MATERIAL.matcher(questionForMaterialCalculation);
							if(materialMatcher.find()){
								question = new Question(QuestionTypeEnum.Q_UNDEFINED_MATERIAL, matcher.group(3), alphabetCharacters);
							} else{
								question = new Question(QuestionTypeEnum.Q_CHARACTER_CALCULATION, matcher.group(3), alphabetCharacters);
							}
						}

					}

					analyzedInput.getQuestions().add(question);

				}
			});
		}

	}

	private void analyzeMaterialDefinitions(ClassifiedInput classifiedInput, AnalyzedInput analyzedInput) {

		if(classifiedInput != null && classifiedInput.getMaterialsWithCharDefinitionsAndValue() != null){
			// parse materials

			classifiedInput.getMaterialsWithCharDefinitionsAndValue().forEach(materialWithCharsAndValueDefinition -> {
				Matcher matcher = Constants.P_MATERIAL_DEFINITION.matcher(materialWithCharsAndValueDefinition);
				if(matcher.find()){

					List<String> characterKeys = Arrays.asList(matcher.group(1).split(" "));

					List<AlphabetCharacter> alphabetCharacters = convertToAlphabetCharacters(characterKeys, analyzedInput.getCharacterDefinitions());

					try{
						Integer totalValueOfCharacters = characterValueCalculatorService.calculate(alphabetCharacters);

						String materialName = matcher.group(2);
						String charge = matcher.group(3);

						Float realChargeOfMaterial = (Float.valueOf(charge) / totalValueOfCharacters);

						Material material = new Material(materialName, realChargeOfMaterial);
						analyzedInput.getMaterials().put(materialName, material);

					} catch (InvalidRepetitionCountException e){
						logger.error(materialWithCharsAndValueDefinition + " - " + e.getMessage());
					}

				}
			});

		}

	}

	// parse character definitions
	private void analyzeCharacterDefinitions(ClassifiedInput classifiedInput, AnalyzedInput analyzedInput) {

		//		Predicate<String> characterDefinitionFilter = (l) -> (Constants.P_CHARACTER_DEFINITION.matcher(l).find());
		//		classifiedInput.getCharacterDefinitions().stream().filter(characterDefinitionFilter).forEach(characterDefinition -> {

		classifiedInput.getCharacterDefinitions().forEach(characterDefinition -> {
			Matcher matcher = Constants.P_CHARACTER_DEFINITION.matcher(characterDefinition);
			if(matcher.find()){
				String specialKeyOfCharacter = matcher.group(1);
				String character = matcher.group(2);
				AlphabetCharacter alphabetCharacter = AlphabetCharacterFactory.getInstance().getAlphabetCharacter(character);

				analyzedInput.getCharacterDefinitions().put(specialKeyOfCharacter, alphabetCharacter);

			}
		});

	}

	private List<AlphabetCharacter> convertToAlphabetCharacters(List<String> characterKeys, Map<String, AlphabetCharacter> allCharacters) {
		List<AlphabetCharacter> alphabetCharacters = new ArrayList<AlphabetCharacter>();

		characterKeys.forEach(characterKey -> {
			AlphabetCharacter alphabetCharacter = allCharacters.get(characterKey);
			if(alphabetCharacter != null){
				alphabetCharacters.add(alphabetCharacter);
			}
		});

		return alphabetCharacters;
	}

}
