package com.thoughtworks.mgttg.services;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.thoughtworks.mgttg.services.business.InputReaderService;
import com.thoughtworks.mgttg.services.business.impl.InputReaderServiceImpl;
import com.thoughtworks.mgttg.util.TestUtil;

/**
 * @author talipk
 *
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class InputReaderServiceImplTest {

	TestUtil testUtil;
	InputReaderService inputReaderService;

	public InputReaderServiceImplTest() {
		testUtil = new TestUtil();
		inputReaderService = new InputReaderServiceImpl();
	}

	@Test
	public void test1_readerServiceWithValidPathArgument() {

		List<String> strings = inputReaderService.readAllLines(testUtil.getValidTestFilePath());
		Assert.assertNotNull(strings);
		Assert.assertEquals(12, strings.size());
	}

}
