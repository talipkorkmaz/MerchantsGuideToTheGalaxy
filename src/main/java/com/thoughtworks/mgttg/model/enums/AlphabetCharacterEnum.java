package com.thoughtworks.mgttg.model.enums;

/**
 * @author talipk
 *
 */
public enum AlphabetCharacterEnum {

	I('I'),
	V('V'),
	X('X'),
	L('L'),
	C('C'),
	D('D'),
	M('M');

	Character c;

	private AlphabetCharacterEnum(Character c) {
		this.c = c;
	}

	public Character getC() {
		return c;
	}

	public static AlphabetCharacterEnum valueOf(Character c) {
		for(AlphabetCharacterEnum alphabetCharacterEnum : AlphabetCharacterEnum.values()){
			if(alphabetCharacterEnum.getC().equals(c)){
				return alphabetCharacterEnum;
			}
		}
		return null;
	}

}
