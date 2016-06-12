package com.thoughtworks.mgttg.model;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.thoughtworks.mgttg.model.factory.AlphabetCharacterFactory;
import com.thoughtworks.mgttg.util.TestUtil;

/**
 * @author talipk
 *
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class AlphabetCharacterFactoryTest {

	TestUtil testUtil;

	public AlphabetCharacterFactoryTest() {
		testUtil = new TestUtil();
	}

	@Test
	public void test1_characterFactoryValidArgument() {

		AlphabetCharacter alphabetCharacter = AlphabetCharacterFactory.getInstance().getAlphabetCharacter('I');
		Assert.assertNotNull(alphabetCharacter);

	}

	@Test
	public void test2_characterFactoryUnValidArgument() {

		AlphabetCharacter alphabetCharacter = AlphabetCharacterFactory.getInstance().getAlphabetCharacter('G');
		Assert.assertNull(alphabetCharacter);

	}
}
