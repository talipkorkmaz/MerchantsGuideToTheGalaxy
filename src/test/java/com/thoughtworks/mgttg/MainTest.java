package com.thoughtworks.mgttg;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.thoughtworks.mgttg.services.logger.impl.LoggerImpl;
import com.thoughtworks.mgttg.util.TestUtil;

/**
 * @author talipk
 *
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class MainTest {

	TestUtil testUtil;

	public MainTest() {
		testUtil = new TestUtil();
	}

	@Before
	public void setUpCommandLineParameter() {
		LoggerImpl.getInstance().clearOutput();
	}

	@Test
	public void test1_mainWithNoArgument() {

		Main.main(new String[0]);

		List<String> responses = LoggerImpl.getInstance().getOutput();
		Assert.assertNotNull(responses);
		Assert.assertNotNull(responses.get(0));
		Assert.assertEquals("this program should be called with a file path parameter!", responses.get(0));

	}

	@Test
	public void test2_mainWithUnvalidArgument() {

		String path = testUtil.getUnValidTestFilePath();
		Main.main(path);

		List<String> responses = LoggerImpl.getInstance().getOutput();
		Assert.assertNotNull(responses);
		Assert.assertNotNull(responses.get(0));
		Assert.assertEquals("path " + path + " is invalid!", responses.get(0));

	}

	@Test
	public void test3_mainWithValidPathArgument() {

		Exception anyExceptionOccured = null;

		String path = testUtil.getValidTestFilePath();

		try{
			Main.main(path);
		} catch (Exception e){
			anyExceptionOccured = e;
		}

		Assert.assertEquals(null, anyExceptionOccured);

	}
}
