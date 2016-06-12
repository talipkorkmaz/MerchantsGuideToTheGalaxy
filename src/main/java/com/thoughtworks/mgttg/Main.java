package com.thoughtworks.mgttg;

import java.io.File;
import java.util.List;

import com.thoughtworks.mgttg.model.Answer;
import com.thoughtworks.mgttg.services.business.MainApplicationService;
import com.thoughtworks.mgttg.services.business.impl.MainApplicationServiceImpl;
import com.thoughtworks.mgttg.services.logger.Logger;
import com.thoughtworks.mgttg.services.logger.impl.LoggerImpl;

/**
 * @author talipk
 *
 */
public class Main {

	public static void main(String... strings) {

		Logger logger = LoggerImpl.getInstance();

		String fileName = null;

		if(strings.length > 0){
			fileName = strings[0];
		}

		if(fileName == null){
			logger.error("this program should be called with a file path parameter!");
			return;
		} else{
			File file = new File(fileName);
			if(!file.exists()){
				logger.error("path " + fileName + " is invalid!");
				return;
			}
		}

		MainApplicationService mainApplicationService = new MainApplicationServiceImpl();
		List<Answer> responses = mainApplicationService.processInputLines(fileName);

		responses.forEach(response -> {
			logger.info(response.getText());
		});

	}

}
