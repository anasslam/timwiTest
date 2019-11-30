package com.timwi.test.circuit.apis;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timwi.test.circuit.apis.response.ResponseWires;
import com.timwi.test.circuit.services.CircuitServices;

@RestController
@Validated 
public class CircuitApis {

	
	@Autowired
	CircuitServices circuitService ;
	
	@PostMapping(path = "/api/assembly")
	public ResponseEntity<ResponseWires> getValueOfWare(@RequestParam ("wire") @Valid
	 String wire ){
		ResponseWires respones = new ResponseWires();
		respones.setWires(circuitService.getValueOfWire(wire));
		return new ResponseEntity<>(respones , HttpStatus.OK);
		
	}
}
