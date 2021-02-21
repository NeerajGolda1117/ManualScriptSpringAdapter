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
	private MessageRequestProcessor messageRequestProcessor ;
	
	@Autowired
	private ManuscriptSaveProcessor manuscriptSaveProcessor;
	
	@Autowired
	private MessageServiceResponseProcessor messageServiceResponseProcessor;
	
	@Autowired
	private ManuscriptUpdatePredicate manuscriptUpdatePredicate;
	
	@Autowired
	private ManuscriptGetPredicate manuscriptGetPredicate;
	
	@Autowired
	private ManuscriptDeletePredicate manuscriptDeletePredicate;

	

	@Override
	public void configure() throws Exception {

		  from("jms:queue:Q.TEST")
		  .process( messageRequestProcessor)
		  .setHeader(Exchange.HTTP_METHOD,constant("POST"))
		  .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		  .choice()
		  .when(manuscriptUpdatePredicate)
		  .process(manuscriptSaveProcessor)
		  .log(" Update Details")
		  .to("http://localhost:8081/article/savedetails")
		  .process( messageServiceResponseProcessor)
		  .when(manuscriptGetPredicate)
		  .log("Fetch Details")
		  .toD("http://localhost:8081/article/details/${header.articleId}")
		  .process(messageServiceResponseProcessor)
		  .when(manuscriptDeletePredicate)
		  .log("Delete Details")
		  .toD("http://localhost:8081/article/delete/${header.articleId}")
		  .process(messageServiceResponseProcessor)
		  .otherwise()
		  .log("Save Details")
				  .process(manuscriptSaveProcessor)
		  .to("http://localhost:8081/article/savedetails")
				  .process(messageServiceResponseProcessor)
		  .end();

		 
		
	}

}
