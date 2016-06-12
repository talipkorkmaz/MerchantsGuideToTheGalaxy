package com.thoughtworks.mgttg.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author talipk
 *
 */
public class ClassifiedInput {

	private List<String> characterDefinitions;
	private List<String> materialsWithCharDefinitionsAndValue;
	private List<String> questions;

	public ClassifiedInput() {
		characterDefinitions = new ArrayList<>();
		materialsWithCharDefinitionsAndValue = new ArrayList<>();
		questions = new ArrayList<>();
	}

	public List<String> getCharacterDefinitions() {
		return characterDefinitions;
	}

	public List<String> getMaterialsWithCharDefinitionsAndValue() {
		return materialsWithCharDefinitionsAndValue;
	}

	public List<String> getQuestions() {
		return questions;
	}

	@Override
	public String toString() {
		return "ClassifiedInput [characterDefinitions=" + characterDefinitions + ", materialsWithCharDefinitionsAndValue=" + materialsWithCharDefinitionsAndValue + ", questions=" + questions + "]";
	}

}
