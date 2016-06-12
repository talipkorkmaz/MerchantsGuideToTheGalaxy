package com.thoughtworks.mgttg.services.business.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.mgttg.services.business.InputReaderService;
import com.thoughtworks.mgttg.services.logger.Logger;
import com.thoughtworks.mgttg.services.logger.impl.LoggerImpl;

/**
 * @author talipk
 *
 */
public class InputReaderServiceImpl implements InputReaderService {

	private Logger logger = LoggerImpl.getInstance();

	@Override
	public List<String> readAllLines(String filePath) {

		List<String> allLines = new ArrayList<>();
		try{
			allLines.addAll(Files.readAllLines(Paths.get(filePath)));
		} catch (IOException e){
			logger.error("io exception occured while reading lines of file !", e);
		}

		return allLines;

	}

}
