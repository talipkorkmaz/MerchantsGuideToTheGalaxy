package com.thoughtworks.mgttg.services.logger.impl;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.mgttg.services.logger.Logger;

/**
 * @author talipk
 *
 */
public class LoggerImpl implements Logger {

	private List<String> output;

	private static volatile LoggerImpl instance = null;

	// private constructor
	private LoggerImpl() {
		output = new ArrayList<>();
	}

	public static LoggerImpl getInstance() {
		if(instance == null){
			synchronized ( LoggerImpl.class ){
				instance = new LoggerImpl();
			}
		}
		return instance;
	}

	@Override
	public void error(String message) {
		output.add(message);
		System.err.println(message);
	}

	@Override
	public void info(String message) {
		output.add(message);
		System.out.println(message);
	}

	@Override
	public void error(String message, Exception e) {
		output.add(message);
		System.err.println(message);
		System.err.println(e);
	}

	@Override
	public List<String> getOutput() {
		return output;
	}

	public void clearOutput() {
		output = new ArrayList<>();
	}

}
