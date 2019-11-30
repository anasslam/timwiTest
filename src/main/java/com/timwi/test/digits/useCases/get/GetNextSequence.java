package com.timwi.test.digits.useCases.get;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import com.timwi.test.digits.useCases.Iget.IgetNextSequence;

@Repository
public class GetNextSequence implements IgetNextSequence{

	@Override
	public String getNextSequence(String input) {
		StringBuilder nextSequence = new StringBuilder();
		
		Pattern pattern = Pattern.compile("(\\d)\\1*");
		Matcher matcher = pattern.matcher(input);
		while(matcher.find()) {
			nextSequence.append(matcher.group().length());
			nextSequence.append(matcher.group().charAt(0));
		}
		return nextSequence.toString() ;
	}
}
