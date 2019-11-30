package com.timwi.test.communs.helpers;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class ReadFile implements IreadFile{

	@Override
	public  Stream<String> readFile(String filename) {
		Stream<String> outputStream = null;
		try {
			URL url = ReadFile.class.getClassLoader().getResource(filename);
			outputStream = Files.lines(Paths.get(url.toURI()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputStream;
	} 
}
