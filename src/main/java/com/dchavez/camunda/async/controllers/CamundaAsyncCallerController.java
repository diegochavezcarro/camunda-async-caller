package com.dchavez.camunda.async.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dchavez.camunda.async.events.services.Producer;

@RestController
public class CamundaAsyncCallerController {

	private static final Logger logger = LoggerFactory.getLogger(CamundaAsyncCallerController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Producer producer;

	@GetMapping("/camundaAsync")
	@ExceptionHandler({ Exception.class })
	public void callCamundaAsync() {
		logger.debug("===============================");
		logger.debug("Inside Camunda Async Caller");
		producer.publishCamundaAsyncCaller("comenzar", "123");

		logger.debug("despues de llamar async");

	}

}
