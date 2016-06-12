package com.thoughtworks.mgttg.services.business;

import java.util.List;

import com.thoughtworks.mgttg.model.Answer;

/**
 * @author talipk
 *
 */
public interface MainApplicationService {

	List<Answer> processInputLines(String fileName);

}
