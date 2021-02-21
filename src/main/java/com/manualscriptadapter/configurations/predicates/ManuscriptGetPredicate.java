package com.manualscriptadapter.configurations.predicates;

import org.apache.camel.Exchange;

import org.apache.camel.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.manualscriptadapter.configurations.models.RequestModel;

@Component
public class ManuscriptGetPredicate implements Predicate  {


	Logger log = LoggerFactory.getLogger(ManuscriptGetPredicate.class);
	
	@Autowired
	ObjectMapper objmap;
	
	@Override
	public boolean matches(Exchange exchange) {
		
		
		
	try {
			RequestModel 	requestModel = objmap.readValue((String) exchange
				      .getIn()
				      .getBody(), RequestModel.class);
			if (requestModel.getFetchArticleId()!=null) {

				exchange
						.getIn()
						.setHeader("articleId",requestModel.getFetchArticleId());
				return true;
			}
		}  catch (Exception e) {

			log.error("BOOOMMM Exception" + e.toString());

		}
		return false;



		

	}

}
