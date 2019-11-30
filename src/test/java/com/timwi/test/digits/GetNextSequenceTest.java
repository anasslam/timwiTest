package com.timwi.test.digits;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.timwi.test.digits.useCases.get.GetNextSequence;

@ExtendWith(SpringExtension.class)
public class GetNextSequenceTest {

	GetNextSequence getNextSequence = new GetNextSequence();

	@Test
	public void testGetGetNextSequence() {
		String nextSequence = "1211";
		assertEquals(nextSequence, getNextSequence.getNextSequence("21"));
	}

}
