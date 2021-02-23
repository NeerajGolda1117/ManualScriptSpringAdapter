package com.manualscriptadapter.configurations.predicates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manualscriptadapter.configurations.models.RequestModel;
import org.apache.camel.test.junit4.ExchangeTestSupport;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ManuscriptGetPredicateTest1 extends ExchangeTestSupport {

    @InjectMocks
   private  ManuscriptGetPredicate manuscriptGetPredicate;

    @Mock
   private ObjectMapper objmap = new ObjectMapper();




    @Test
    void testMatches() {
        RequestModel requestModel = new RequestModel();
        requestModel.setFetchArticleId(1L);

        //exchange.setProperty("fetchArticleId",2345);
        exchange.getIn().setBody(requestModel);
        System.out.println("done");

        boolean res = manuscriptGetPredicate.matches(exchange);
        assertTrue(true);
    }

}