package uk.arm.string.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {
	
	public int add(String numbers) {
		int sumTotal = 0;
		
		for (Integer number : parseString(numbers)) {
			sumTotal += number;
		}
		
		return sumTotal;
	}
    
    public List<Integer> parseString(String numbers) throws IllegalArgumentException {
    	List<Integer> numberCollection = new ArrayList<Integer>();
    	
    	try {
    		List<String> numbersAsStrings = Arrays.asList(numbers.split(","));

	    	for (String numberAsString : numbersAsStrings) {
	    		int number = numberAsString.trim().equals("") ? 0 : Integer.valueOf(numberAsString.trim());
	    		numberCollection.add(number); 
	    	}
    	} catch (NumberFormatException nfe) {
    		throw new IllegalArgumentException(nfe.toString());
    	}
    	return numberCollection;
    }

}
