package com.thoughtworks.mgttg.services.business;

import java.util.List;

import com.thoughtworks.mgttg.exception.InvalidRepetitionCountException;
import com.thoughtworks.mgttg.model.AlphabetCharacter;

/**
 * @author talipk
 *
 */
public interface CharacterValueCalculatorService {

	Integer calculate(List<AlphabetCharacter> alphabetCharacters) throws InvalidRepetitionCountException;

}
