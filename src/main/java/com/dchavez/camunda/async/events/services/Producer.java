package com.dchavez.camunda.async.events.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.dchavez.camunda.async.events.model.CamundaStartModel;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class Producer {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	//@Autowired
	//private KafkaTemplate<String, OrganizationChangeModel> kafkaTemplate;
	
	@Autowired
	private Source source;

    public void publishCamundaAsyncCaller(String action,String pid){
        logger.debug("Sending Kafka message {} for Id: {}", action, pid);
         CamundaStartModel change =  new CamundaStartModel(
        		 CamundaStartModel.class.getTypeName(),
                 action,
                 pid);
         Message<CamundaStartModel> message = MessageBuilder
                 .withPayload(change)
                 .build();
         logger.info(String.format("$$ -> Producing message --> %s", message));
         source.output().send(message);
     }

}
