package com.thoughtworks.mgttg.model;

import java.util.List;

import com.thoughtworks.mgttg.model.enums.QuestionTypeEnum;

/**
 * @author talipk
 *
 */
public class Question {

	private QuestionTypeEnum questionTypeEnum;
	private Material material;
	private String text;
	private List<AlphabetCharacter> characters;

	public Question(QuestionTypeEnum questionTypeEnum, String text, List<AlphabetCharacter> characters) {
		this.questionTypeEnum = questionTypeEnum;
		this.text = text;
		this.characters = characters;
	}

	public Question(QuestionTypeEnum questionTypeEnum, String text, Material material, List<AlphabetCharacter> characters) {
		this.questionTypeEnum = questionTypeEnum;
		this.text = text;
		this.material = material;
		this.characters = characters;
	}

	public QuestionTypeEnum getQuestionTypeEnum() {
		return questionTypeEnum;
	}

	public List<AlphabetCharacter> getCharacters() {
		return characters;
	}

	public Material getMaterial() {
		return material;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "Question [questionTypeEnum=" + questionTypeEnum + ", material=" + material + ", text=" + text + ", characters=" + characters + "]";
	}

}
