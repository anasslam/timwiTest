package com.timwi.test.digits.apis;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timwi.test.digits.apis.response.DigitsResponse;
import com.timwi.test.digits.services.DigitsServices;


@RestController
@Validated 
public class DigitsApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(DigitsApi.class);
	
	@Autowired
	DigitsServices digitsServices ;
	
	@GetMapping("/api/lookandsay/{input}")
	public ResponseEntity<DigitsResponse> getSequenceAfterIterations(
			@PathVariable  @NotBlank  String input ,
			@RequestParam ("times") @Valid
			@Min(value = 0L, message = "The value must be positive")
			 int times) {
		
		LOGGER.info("call getSequenceAfterIterations: input={} , times={}",
				input , times);
		DigitsResponse response = new DigitsResponse();
		
		response.setNext_string(digitsServices.getSequenceAfterIterations(input, times));
		response.setSting_length(response.getNext_string().length());
		
		LOGGER.info("Return Length getSequenceAfterIterations: LENGTH={}" , response.getSting_length());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
