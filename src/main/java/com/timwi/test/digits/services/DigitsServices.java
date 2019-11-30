package com.timwi.test.digits.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timwi.test.digits.useCases.Iget.IgetNextSequence;

@Service
public class DigitsServices {

	IgetNextSequence getNextSequence ;

	@Autowired
	public DigitsServices(IgetNextSequence getNextSequence) {
		super();
		this.getNextSequence = getNextSequence;
	}
	
	public String getSequenceAfterIterations(String input, int times) {
		for (int i = 0; i < times; i++) {
			input = getNextSequence.getNextSequence(input);
		}
		return input ;
	}
	
	
}
