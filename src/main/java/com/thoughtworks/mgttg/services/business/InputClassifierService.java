package com.thoughtworks.mgttg.services.business;

import java.util.List;

import com.thoughtworks.mgttg.model.ClassifiedInput;

/**
 * @author talipk
 *
 */
public interface InputClassifierService {

	ClassifiedInput classify(List<String> allLines);

}
