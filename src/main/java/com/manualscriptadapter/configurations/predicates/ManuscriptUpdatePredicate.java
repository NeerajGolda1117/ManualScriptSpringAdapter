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
public class ManuscriptUpdatePredicate implements Predicate {

	@Autowired
	ObjectMapper objmap;
	
	Logger log = LoggerFactory.getLogger(ManuscriptDeletePredicate.class);

    @Override
    public boolean matches(Exchange exchange) {


        try {
            RequestModel requestModel = objmap.readValue((String) exchange
                    .getIn()
                    .getBody(), RequestModel.class);

            return requestModel.getAction().equals("POST");
        } catch (Exception e) {

        	log.error("BOOOMMM Exception" + e.toString());
        }


        return false;
    }

}
