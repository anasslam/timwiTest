package com.timwi.test.circuit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.timwi.test.circuit.useCases.Circuit;
import com.timwi.test.circuit.useCases.ProcessWiringOperation;

@RunWith(MockitoJUnitRunner.class)
public class CircuitTest {

	private Map<String, String> circuits = new HashMap<>();
	
	@InjectMocks
	ProcessWiringOperation processWiringOperation;
	
	@InjectMocks
	Circuit circuit = new Circuit() ;
	

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		}

		@Test
		public void testProcessWiring() {
			String[] input = { "123 -> x", "456 -> y", "x AND y -> d", "x OR y -> e", "x LSHIFT 2 -> f", "y RSHIFT 2 -> g",
					"NOT x -> h", "NOT y -> i" };
			Arrays.stream(input)
					.map(str -> str.split(" -> "))
					.forEach(str -> circuits.put(str[1], str[0]));
			
			//testing the circuit in the project description
			assertEquals(72, circuit.getValue("d" , circuits));
			assertEquals(507, circuit.getValue("e" , circuits));
			assertEquals(492, circuit.getValue("f" , circuits));
			assertEquals(114, circuit.getValue("g" , circuits));
			assertEquals(65412, circuit.getValue("h" , circuits));
			assertEquals(65079, circuit.getValue("i" , circuits));
			assertEquals(123, circuit.getValue("x" , circuits));
			assertEquals(456, circuit.getValue("y", circuits));
		}
}
