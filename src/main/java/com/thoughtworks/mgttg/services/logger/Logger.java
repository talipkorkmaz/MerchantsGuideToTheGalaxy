package com.thoughtworks.mgttg.services.logger;

import java.util.List;

/**
 * @author talipk
 *
 */
public interface Logger {

	void error(String message);

	void error(String message, Exception e);

	void info(String message);

	List<String> getOutput();

	void clearOutput();

}
