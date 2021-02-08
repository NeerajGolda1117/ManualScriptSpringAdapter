package com.manualscriptadapter.configurations.processors;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceResponseProcessor implements Processor {


    Logger log = LoggerFactory.getLogger(MessageServiceResponseProcessor.class);

    public void process(Exchange exchange) throws Exception {
        log.info( exchange.getIn().getBody(String.class));
    }
}