package com.timwi.test.circuit.useCases;

import java.util.List;
import java.util.Map;

import com.timwi.test.circuit.apis.response.ResponseValue;

public interface Icircuit {

	Map<String, String> initCircuit(Map<String, String> circuit);

	int getValue(String arg, Map<String, String> circuit);

	List<ResponseValue> getAllWiresValues(Map<String, String> circuit);


}
