package com.thoughtworks.mgttg.services.business;

import java.util.List;

/**
 * @author talipk
 *
 */
public interface InputReaderService {

	List<String> readAllLines(String filePath);

}
