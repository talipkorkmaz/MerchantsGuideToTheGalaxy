package com.thoughtworks.mgttg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author talipk
 *
 */
public class AnalyzedInput {

	private Map<String, AlphabetCharacter> characterDefinitions;
	private Map<String, Material> materials;
	private List<Question> questions;

	// private constructor
	public AnalyzedInput() {
		characterDefinitions = new HashMap<>();
		materials = new HashMap<>();
		questions = new ArrayList<>();
	}

	public Map<String, AlphabetCharacter> getCharacterDefinitions() {
		return characterDefinitions;
	}

	public Map<String, Material> getMaterials() {
		return materials;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	@Override
	public String toString() {
		return "AnalyzedInput [characterDefinitions=" + characterDefinitions + ", materials=" + materials + ", questions=" + questions + "]";
	}

}
