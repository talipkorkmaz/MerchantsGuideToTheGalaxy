package com.thoughtworks.mgttg.util;

import java.net.URL;

public class TestUtil {

	public String getValidTestFilePath() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("input.txt");
		return url.getPath().replaceFirst("^/(.:/)", "$1");
	}

	public String getUnValidTestFilePath() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("input.txt");
		return url.getPath().replaceFirst("^/(.:/)", "$1") + "_unexistingfile";
	}

}
