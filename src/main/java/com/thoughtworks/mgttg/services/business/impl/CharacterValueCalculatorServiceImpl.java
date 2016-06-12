package com.thoughtworks.mgttg.services.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.mgttg.exception.InvalidRepetitionCountException;
import com.thoughtworks.mgttg.model.AlphabetCharacter;
import com.thoughtworks.mgttg.model.enums.AlphabetCharacterEnum;
import com.thoughtworks.mgttg.services.business.CharacterValueCalculatorService;

/**
 * @author talipk
 *
 */
public class CharacterValueCalculatorServiceImpl implements CharacterValueCalculatorService {

	@Override
	public Integer calculate(List<AlphabetCharacter> alphabetCharacters) throws InvalidRepetitionCountException {

		validateRepetitionCountsOfCharacters(alphabetCharacters);

		return calculateAlphabetCharacters(alphabetCharacters);

	}

	private void validateRepetitionCountsOfCharacters(List<AlphabetCharacter> alphabetCharacters) throws InvalidRepetitionCountException {

		Map<AlphabetCharacterEnum, Integer> repetitionCountsOfCharacters = new HashMap<>();
		for(int i = 0; i < alphabetCharacters.size() - 1; i++){

			AlphabetCharacter first = alphabetCharacters.get(i);
			AlphabetCharacter second = alphabetCharacters.get(i + 1);

			if(first.getAlphabetCharacterEnum().equals(second.getAlphabetCharacterEnum())){

				Integer count = repetitionCountsOfCharacters.get(first.getAlphabetCharacterEnum());
				if(count == null){
					count = 1;
				} else{
					count++;
				}

				if(first.getRepetitionCount() <= count){
					throw new InvalidRepetitionCountException(first.getAlphabetCharacterEnum().toString() + " character is not valid with " + (count + 1) + " repetition!");
				}
				repetitionCountsOfCharacters.put(first.getAlphabetCharacterEnum(), count);

			} else if(!repetitionCountsOfCharacters.isEmpty()){
				repetitionCountsOfCharacters.clear();
			}

		}

	}

	private Integer calculateAlphabetCharacters(List<AlphabetCharacter> alphabetCharacters) {

		Integer totalValueOfCharacters = 0;
		for(int i = 0; i < alphabetCharacters.size(); i++){

			AlphabetCharacter first = alphabetCharacters.get(i);
			AlphabetCharacter second = null;
			if(i + 1 < alphabetCharacters.size()){
				second = alphabetCharacters.get(i + 1);
			}

			if(second != null && first.getSubstractables().contains(second.getAlphabetCharacterEnum().getC())){
				totalValueOfCharacters += (second.getValue() - first.getValue());
				i++;
			} else{
				totalValueOfCharacters += first.getValue();
			}

		}

		return totalValueOfCharacters;
	}

}
