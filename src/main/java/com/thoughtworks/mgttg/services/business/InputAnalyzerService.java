package com.thoughtworks.mgttg.services.business;

import com.thoughtworks.mgttg.model.AnalyzedInput;
import com.thoughtworks.mgttg.model.ClassifiedInput;

/**
 * @author talipk
 *
 */
public interface InputAnalyzerService {

	AnalyzedInput analyze(ClassifiedInput classifiedInput);

}
