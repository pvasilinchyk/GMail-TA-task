package com.epam.byta.tests;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

public class Runner {
	
	private static final String PATH_TO_TESTNG = "testng.xml";

	public static void main(String[] args) throws InterruptedException, ParserConfigurationException, SAXException, IOException {
		  TestNG testng = new TestNG();
	        for(XmlSuite suite : new Parser(PATH_TO_TESTNG).parseToList()) {
	            testng.setCommandLineSuite(suite);
	        }
	        testng.run();
	}
}
