package com.thoughtworks.mgttg.model.factory;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.mgttg.model.AlphabetCharacter;
import com.thoughtworks.mgttg.model.enums.AlphabetCharacterEnum;

/**
 * @author talipk
 *
 */
public class AlphabetCharacterFactory {

	private static final Integer WEIGHT_ONE = 1;
	private static final Integer WEIGHT_FIVE = 5;
	private static final Integer WEIGHT_TEN = 10;
	private static final Integer WEIGHT_FIFTY = 50;
	private static final Integer WEIGHT_HUNDRED = 100;
	private static final Integer WEIGHT_HALF_THOUSAND = 500;
	private static final Integer WEIGHT_THOUSAND = 1000;

	private static final Integer REPETITION_THREE = 3;
	private static final Integer REPETITION_NONE = 0;

	private static volatile AlphabetCharacterFactory instance = null;

	// private constructor
	private AlphabetCharacterFactory() {

	}

	public static AlphabetCharacterFactory getInstance() {
		if(instance == null){
			synchronized ( AlphabetCharacterFactory.class ){
				instance = new AlphabetCharacterFactory();
			}
		}
		return instance;
	}

	public AlphabetCharacter getAlphabetCharacter(String character) {

		AlphabetCharacterEnum alphabetCharacterEnum = AlphabetCharacterEnum.valueOf(character);
		if(alphabetCharacterEnum == null){
			return null;
		}

		return getAlphabetCharacter(alphabetCharacterEnum);

	}

	public AlphabetCharacter getAlphabetCharacter(Character character) {

		AlphabetCharacterEnum alphabetCharacterEnum = AlphabetCharacterEnum.valueOf(character);
		if(alphabetCharacterEnum == null){
			return null;
		}

		return getAlphabetCharacter(alphabetCharacterEnum);
	}

	private AlphabetCharacter getAlphabetCharacter(AlphabetCharacterEnum alphabetCharacterEnum) {
		switch (alphabetCharacterEnum) {
			case I :
				return createIChar(alphabetCharacterEnum);
			case V :
				return createVChar(alphabetCharacterEnum);
			case X :
				return createXChar(alphabetCharacterEnum);
			case L :
				return createLChar(alphabetCharacterEnum);
			case C :
				return createCChar(alphabetCharacterEnum);
			case D :
				return createDChar(alphabetCharacterEnum);
			case M :
				return createMChar(alphabetCharacterEnum);
			default :
				break;
		}
		return null;
	}

	private AlphabetCharacter createIChar(AlphabetCharacterEnum alphabetCharacterEnum) {
		List<Character> substractables = new ArrayList<>();
		substractables.add('V');
		substractables.add('X');

		return new AlphabetCharacter.AlphabetCharacterBuilder().alphabetCharacterEnum(alphabetCharacterEnum).value(WEIGHT_ONE).substractables(substractables).repetitionCount(REPETITION_THREE).build();
	}

	private AlphabetCharacter createVChar(AlphabetCharacterEnum alphabetCharacterEnum) {

		return new AlphabetCharacter.AlphabetCharacterBuilder().alphabetCharacterEnum(alphabetCharacterEnum).value(WEIGHT_FIVE).substractables(new ArrayList<Character>())
				.repetitionCount(REPETITION_NONE).build();
	}

	private AlphabetCharacter createXChar(AlphabetCharacterEnum alphabetCharacterEnum) {
		List<Character> substractables = new ArrayList<>();
		substractables.add('L');
		substractables.add('C');

		return new AlphabetCharacter.AlphabetCharacterBuilder().alphabetCharacterEnum(alphabetCharacterEnum).value(WEIGHT_TEN).substractables(substractables).repetitionCount(REPETITION_THREE).build();
	}

	private AlphabetCharacter createLChar(AlphabetCharacterEnum alphabetCharacterEnum) {

		return new AlphabetCharacter.AlphabetCharacterBuilder().alphabetCharacterEnum(alphabetCharacterEnum).value(WEIGHT_FIFTY).substractables(new ArrayList<Character>())
				.repetitionCount(REPETITION_NONE).build();
	}

	private AlphabetCharacter createCChar(AlphabetCharacterEnum alphabetCharacterEnum) {
		List<Character> substractables = new ArrayList<>();
		substractables.add('D');
		substractables.add('M');

		return new AlphabetCharacter.AlphabetCharacterBuilder().alphabetCharacterEnum(alphabetCharacterEnum).value(WEIGHT_HUNDRED).substractables(substractables).repetitionCount(REPETITION_THREE)
				.build();
	}

	private AlphabetCharacter createDChar(AlphabetCharacterEnum alphabetCharacterEnum) {

		return new AlphabetCharacter.AlphabetCharacterBuilder().alphabetCharacterEnum(alphabetCharacterEnum).value(WEIGHT_HALF_THOUSAND).substractables(new ArrayList<Character>())
				.repetitionCount(REPETITION_NONE).build();
	}

	private AlphabetCharacter createMChar(AlphabetCharacterEnum alphabetCharacterEnum) {

		return new AlphabetCharacter.AlphabetCharacterBuilder().alphabetCharacterEnum(alphabetCharacterEnum).value(WEIGHT_THOUSAND).substractables(new ArrayList<Character>())
				.repetitionCount(REPETITION_THREE).build();
	}
}
