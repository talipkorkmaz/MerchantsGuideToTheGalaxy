package com.thoughtworks.mgttg.services.business;

import java.util.List;

import com.thoughtworks.mgttg.model.Answer;
import com.thoughtworks.mgttg.model.Question;

/**
 * @author talipk
 *
 */
public interface QuestionAnswerService {

	List<Answer> answerQuestions(List<Question> questions);

}
