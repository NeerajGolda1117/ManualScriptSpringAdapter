package com.manualscriptadapter.configurations.processors;

import org.apache.camel.Exchange;

import org.apache.camel.Processor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manualscriptadapter.configurations.models.ManuscriptResponseModel;
import com.manualscriptadapter.configurations.models.RequestModel;

@Component
public class ManuscriptSaveProcessor implements Processor {


	@Autowired
	ObjectMapper objmap;
	
	
	@Override
	public void process(Exchange exchange) throws Exception {


		
		  ManuscriptResponseModel responseModel = objmap.readValue((String)
		  exchange .getIn() .getBody(), ManuscriptResponseModel.class);
	
		
		exchange.getIn().setBody(objmap.writeValueAsString(responseModel));
		
	}

}
