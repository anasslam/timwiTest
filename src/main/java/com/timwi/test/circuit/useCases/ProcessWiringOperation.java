package com.timwi.test.circuit.useCases;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.timwi.test.communs.helpers.TriFunction;

@Component
public class ProcessWiringOperation implements IprocessWiringOperation{

	@Autowired
	Icircuit circuit ;
	
	//bitwis Logique
	BiFunction<String, Map<String, String>, Integer> notOperation = (s,map) -> (~circuit.getValue(s,map));
	TriFunction<String, String,Map<String, String>, Integer> andOperation = (arg1, arg2, map) -> (circuit.getValue(arg1,map) & circuit.getValue(arg2 , map));
	TriFunction<String, String,Map<String, String>, Integer> orOperation = (arg1, arg2, map) -> (circuit.getValue(arg1, map) | circuit.getValue(arg2, map));
	TriFunction<String, String,Map<String, String>, Integer> lShiftOperation = (arg1, arg2, map) -> (circuit.getValue(arg1, map) << circuit.getValue(arg2, map));
	TriFunction<String, String,Map<String, String>, Integer> rShiftOperation = (arg1, arg2, map) -> (circuit.getValue(arg1, map) >> circuit.getValue(arg2, map));


	@Override
	public  int processWiring(String instruction , Map<String, String> circuit) {
		Matcher matcher = Pattern.compile("(.+) (.+) (.+)").matcher(instruction);
		Matcher matcher1 = Pattern.compile("(.+) (.+)").matcher(instruction);
		int result = 0;
		if (matcher.find()) {
			String arg1 = matcher.group(1);
			String operation = matcher.group(2);
			String arg2 = matcher.group(3);
			switch (operation) {
			case "AND":
				result = andOperation.apply(arg1, arg2, circuit);
				break;
			case "OR":
				result = orOperation.apply(arg1, arg2, circuit);
				break;
			case "LSHIFT":
				result = lShiftOperation.apply(arg1, arg2, circuit);
				break;
			case "RSHIFT":
				result = rShiftOperation.apply(arg1, arg2, circuit);
				break;
			default:
				break;
			}
		} else if (matcher1.find()) {
			String operation = matcher1.group(1);
			String arg1 = matcher1.group(2);
			if (operation.equals("NOT")) {
				result = notOperation.apply(arg1 , circuit);
			}
		} else {
			result = this.circuit.getValue(instruction, circuit);
		}
		return result;
	}
}
