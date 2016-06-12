package com.thoughtworks.mgttg.services.business.impl;

import java.util.List;

import com.thoughtworks.mgttg.model.ClassifiedInput;
import com.thoughtworks.mgttg.services.business.InputClassifierService;
import com.thoughtworks.mgttg.util.Constants;

/**
 * @author talipk
 *
 */
public class InputClassifierServiceImpl implements InputClassifierService {

	@Override
	public ClassifiedInput classify(List<String> allLines) {

		ClassifiedInput classifiedInput = new ClassifiedInput();

		allLines.forEach(line -> {
			if(Constants.P_CHARACTER_DEFINITION.matcher(line).find()){
				classifiedInput.getCharacterDefinitions().add(line.trim());
			} else if(Constants.P_MATERIAL_DEFINITION.matcher(line).find()){
				classifiedInput.getMaterialsWithCharDefinitionsAndValue().add(line.trim());
			} else if(Constants.P_QUESTION.matcher(line).find()){
				classifiedInput.getQuestions().add(line.trim());
			}
		});

		return classifiedInput;
	}

}
