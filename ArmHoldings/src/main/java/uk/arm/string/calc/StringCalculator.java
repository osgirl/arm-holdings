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
    	
    	String delimiterExpression = ",|\\n";
    	if (numbers.startsWith("//")) {
    		String delimiterLine = numbers.split("\n")[0].substring(2);
    		delimiterExpression = delimiterLine.trim();
    		
    		numbers = numbers.substring(delimiterLine.length() + 3);
    	}
    	
    	try {
    		List<String> numbersAsStrings = Arrays.asList(numbers.split(delimiterExpression));

    		List<Integer> negativeNumbers = new ArrayList<Integer>();
	    	for (String numberAsString : numbersAsStrings) {
	    		int number = numberAsString.trim().equals("") ? 0 : Integer.valueOf(numberAsString.trim());
	    		
	    		if (number < 0) { negativeNumbers.add(number); }
	    		
	    		numberCollection.add(number); 
	    	}
	    	if (!negativeNumbers.isEmpty()) {
	    		throw createNegativesNotAllowedException(negativeNumbers);
	    	}
	    	
    	} catch (NumberFormatException nfe) {
    		throw new IllegalArgumentException(nfe.toString());
    	}
    	return numberCollection;
    }

	private IllegalArgumentException createNegativesNotAllowedException(List<Integer> negativeNumbers) {
		StringBuffer negativesMsg = new StringBuffer("Negatives not allowed: ");
		for (Integer negativeNumber : negativeNumbers) {
			negativesMsg.append(negativeNumber);
		}
		throw new IllegalArgumentException(negativesMsg.toString());
	}

}
