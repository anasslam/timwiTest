package com.timwi.test.circuit.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timwi.test.circuit.apis.response.ResponseValue;
import com.timwi.test.circuit.useCases.Icircuit;
import com.timwi.test.circuit.useCases.IprocessWiringOperation;
import com.timwi.test.digits.apis.DigitsApi;

@Service
public class CircuitServices {

	Icircuit circuit;
	IprocessWiringOperation proccessWiring;

	private static final Logger LOGGER = LoggerFactory.getLogger(DigitsApi.class);

	@Autowired
	public CircuitServices(Icircuit circuit, IprocessWiringOperation proccessWiring) {
		super();
		this.circuit = circuit;
		this.proccessWiring = proccessWiring;
	}

	public List<ResponseValue> getValueOfWire(String wire) {
		Map<String, String> circuits = new HashMap<>();

		this.circuit.initCircuit(circuits);
		// convert value to 16 bit
		int value = 0xffff & this.circuit.getValue(wire, circuits);

		ResponseValue response = new ResponseValue();
		List<ResponseValue> responses = new ArrayList<>();

		response.setWire_name(wire.charAt(0));
		response.setWire_value(value);
		responses.add(response);

		if (responses.get(0).getWire_value() != 0) {
			LOGGER.info("the value of the wire: Wire={} is value={}", wire, responses.get(0).getWire_value());
			return responses;
		} else {
			responses = circuit.getAllWiresValues(circuits);
			LOGGER.info("The wire is not found : Wire={} is SizeOfData={}", wire, responses.size());
			return responses;
		}
	}
}
