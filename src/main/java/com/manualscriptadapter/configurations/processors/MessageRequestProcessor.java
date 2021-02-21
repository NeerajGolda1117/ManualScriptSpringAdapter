package com.manualscriptadapter.configurations.processors;

import org.apache.camel.Exchange;

import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manualscriptadapter.configurations.models.RequestModel;

@Component
public class MessageRequestProcessor implements Processor {

	Logger log = LoggerFactory.getLogger(MessageRequestProcessor.class);
  
 @Autowired
@Qualifier("objectMapper")
 private ObjectMapper objmap;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		try {
			RequestModel requestModel = objmap.readValue((String)
					exchange
					.getIn()
					.getBody(), RequestModel.class);


		/*	exchange
					.getIn()
					.setHeader("articleId",requestModel.getArticleId());*/
		}
		catch (Exception e)
		{
			log.error( "BOOOMMM! Object type did not Match  " ,e.toString());

		}

		
	}

}
