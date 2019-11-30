package com.timwi.test.circuit.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.timwi.test.circuit.apis.response.ResponseValue;
import com.timwi.test.communs.helpers.IreadFile;

@Component
public class Circuit implements Icircuit{


	@Autowired
	IreadFile readFile ;
	
	@Autowired
	IprocessWiringOperation processWiringOperation ;
	
	@Override
	public  Map<String, String> initCircuit(Map<String, String> circuit) {
		circuit.clear();
		readFile.readFile("assembly-instructions.txt")
				.map(str -> str.split(" -> "))
				.forEach(str -> circuit.put(str[1], str[0]));
		
		return circuit;
	}
	
	@Override
	public  int getValue(String arg , Map<String, String> circuit) {
		int value = 0;
		if (arg.matches("\\d+")) {
			value = Integer.valueOf(arg);
		} else {
			value = processWiringOperation.processWiring(circuit.get(arg),circuit);
			circuit.put(arg, String.valueOf(value));
		}
		return value;
	}
	
	@Override
	public List<ResponseValue> getAllWiresValues(Map<String, String> circuit) {
		List<ResponseValue> responses = new ArrayList<>();
		for(char lettre = 'a'; lettre <='z'; lettre++ ) {
			ResponseValue response = new ResponseValue();
			response.setWire_name(lettre);
			response.setWire_value(0xffff & getValue(lettre+"" , initCircuit(circuit)));
			if(response.getWire_value() != 0) 
				responses.add(response);
			}
		return responses ;
	}
}
