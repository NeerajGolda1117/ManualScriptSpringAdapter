package com.manualscriptadapter.configurations;

import javax.jms.ConnectionFactory;

import com.manualscriptadapter.configurations.processors.MessageRequestProcessor;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class ManualscriptSpringadapterApplication {

	Logger log = LoggerFactory.getLogger(ManualscriptSpringadapterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ManualscriptSpringadapterApplication.class, args);
	}
	
	@Bean(name = "objectMapper")
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		return objectMapper;
	}

	
	@Bean
	public void jmsComponent() 
	{
       
        CamelContext ctx = new DefaultCamelContext();
        
        //configure jms component        
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
        ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        
        
        
		
		  try { 
			 //ctx.addRoutes(routeBuilder);
			  ctx.start(); 
			  
			  }
		  catch (Exception e) {
            log.error("BOOOMMM JMS Connection is failed",e.toString());
			  }
		 
	}

}
