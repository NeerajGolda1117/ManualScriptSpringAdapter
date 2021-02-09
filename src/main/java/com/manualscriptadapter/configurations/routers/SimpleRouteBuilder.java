package com.manualscriptadapter.configurations.routers;

import org.apache.camel.builder.RouteBuilder;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.manualscriptadapter.configurations.predicates.ManuscriptDeletePredicate;
import com.manualscriptadapter.configurations.predicates.ManuscriptGetPredicate;
import com.manualscriptadapter.configurations.predicates.ManuscriptUpdatePredicate;
import com.manualscriptadapter.configurations.processors.ManuscriptSaveProcessor;
import com.manualscriptadapter.configurations.processors.MessageRequestProcessor;
import com.manualscriptadapter.configurations.processors.MessageServiceResponseProcessor;

import org.apache.camel.Exchange;



@Component
public class SimpleRouteBuilder extends RouteBuilder {

	static final Logger log = LoggerFactory.getLogger(SimpleRouteBuilder.class);
   
	
	@Autowired
	MessageRequestProcessor messageRequestProcessor ;
	
	@Autowired
	ManuscriptSaveProcessor manuscriptsaveprocessor;
	
	@Autowired
	MessageServiceResponseProcessor myprocessor;
	
	@Autowired
	ManuscriptUpdatePredicate manuscriptupdatepredicate;
	
	@Autowired
	ManuscriptGetPredicate manuscriptgetpredicate;
	
	@Autowired
	ManuscriptDeletePredicate manuscriptdeletepredicate;

	

	@Override
	public void configure() throws Exception {

		  from("jms:queue:Q.TEST")
		  .process( messageRequestProcessor)
		  .setHeader(Exchange.HTTP_METHOD,constant("POST"))
		  .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		  .choice()
		  .when(manuscriptupdatepredicate)
		  .process(manuscriptsaveprocessor)
		  .log("Save or Update Details")
		  .to("http://localhost:8081/article/savedetails")
		  .process( myprocessor)
		  .when(manuscriptgetpredicate)
		  .log("Fetch Details")
		  .toD("http://localhost:8081/article/details/${header.articleId}")
		  .process(myprocessor)
		  .when(manuscriptdeletepredicate)
		  .log("Delete Details")
		  .toD("http://localhost:8081/article/delete/${header.articleId}")
		  .process(myprocessor)
		  .otherwise()
		  .log("Fetch API") 
		  .to("http://localhost:8081/article/api1")
		  .end();

		 
		
	}

}
