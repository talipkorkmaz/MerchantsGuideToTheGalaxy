package com.thoughtworks.mgttg.model;

import java.util.List;

import com.thoughtworks.mgttg.model.enums.AlphabetCharacterEnum;

/**
 * @author talipk
 *
 */
public class AlphabetCharacter {

	private AlphabetCharacterEnum alphabetCharacterEnum;
	private Integer value;
	private List<Character> substractables;
	private Integer repetitionCount;

	private AlphabetCharacter(AlphabetCharacterBuilder builder) {
		this.alphabetCharacterEnum = builder.alphabetCharacterEnum;
		this.value = builder.value;
		this.substractables = builder.substractables;
		this.repetitionCount = builder.repetitionCount;
	}

	public AlphabetCharacterEnum getAlphabetCharacterEnum() {
		return alphabetCharacterEnum;
	}

	public Integer getValue() {
		return value;
	}

	public List<Character> getSubstractables() {
		return substractables;
	}

	public Integer getRepetitionCount() {
		return repetitionCount;
	}

	public static class AlphabetCharacterBuilder {

		private AlphabetCharacterEnum alphabetCharacterEnum;
		private Integer value;
		private List<Character> substractables;
		private Integer repetitionCount;

		public AlphabetCharacterBuilder() {
			// builder constructor
		}

		public AlphabetCharacterBuilder alphabetCharacterEnum(AlphabetCharacterEnum alphabetCharacterEnum) {
			this.alphabetCharacterEnum = alphabetCharacterEnum;
			return this;
		}

		public AlphabetCharacterBuilder value(Integer value) {
			this.value = value;
			return this;
		}

		public AlphabetCharacterBuilder substractables(List<Character> substractables) {
			this.substractables = substractables;
			return this;
		}

		public AlphabetCharacterBuilder repetitionCount(Integer repetitionCount) {
			this.repetitionCount = repetitionCount;
			return this;
		}

		public AlphabetCharacter build() {
			return new AlphabetCharacter(this);
		}

	}

	@Override
	public String toString() {
		return "AlphabetCharacter [alphabetCharacterEnum=" + alphabetCharacterEnum + ", value=" + value + ", substractables=" + substractables + ", repetitionCount=" + repetitionCount + "]";
	}

}
