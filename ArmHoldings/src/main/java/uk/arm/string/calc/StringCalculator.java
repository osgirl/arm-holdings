package uk.arm.string.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {
	
	int add(String numbers) {
		int sumTotal = 0;
		
		for (Integer number : parseString(numbers)) {
			sumTotal += number;
		}
		
		return sumTotal;
	}
    
    private List<Integer> parseString(String numbers) throws IllegalArgumentException {
    	List<Integer> numberCollection = new ArrayList<Integer>();
    	
    	String delimiterExpression = ",|\\n";
    	if (numbers.startsWith("//")) {
    		String delimiterLine = numbers.split("\n")[0].substring(2);
    		delimiterExpression = createDelimiterExpression(delimiterLine);
    		
    		numbers = numbers.substring(delimiterLine.length() + 3);
    	}
    	
    	try {
    		List<String> numbersAsStrings = Arrays.asList(numbers.split(delimiterExpression));

    		List<Integer> negativeNumbers = new ArrayList<Integer>();
	    	for (String numberAsString : numbersAsStrings) {
	    		int number = numberAsString.trim().equals("") ? 0 : Integer.valueOf(numberAsString.trim());
	    		
	    		if (number < 0) { negativeNumbers.add(number); }
	    		
	    		numberCollection.add(number > 1000 ? 0 : number);
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

	private String createDelimiterExpression(String delimiterLine) {
		String delimiterExpression = delimiterLine.trim();
		if (delimiterLine.startsWith("[") && delimiterLine.endsWith("]")) {
			delimiterExpression = parseDelimiterLine(delimiterLine.substring(1, delimiterLine.length() - 1));
		}
		return delimiterExpression;
	}

	private String parseDelimiterLine(String delimiterLine) {
		if (delimiterLine.contains("][")) {
			String delimiterExpression = delimiterLine;
			delimiterExpression = delimiterExpression.replace("][", "|");
			delimiterExpression = escapeAllMetaCharacters(delimiterExpression);
			return delimiterExpression;
		}
		return delimiterLine.trim();
	}

	private String escapeAllMetaCharacters(String delimiterExpression) {
		delimiterExpression = delimiterExpression.replace("\\", "\\\\");
		delimiterExpression = delimiterExpression.replace("*", "\\*");
		delimiterExpression = delimiterExpression.replace(".", "\\.");
		delimiterExpression = delimiterExpression.replace("$", "\\$");
		delimiterExpression = delimiterExpression.replace("^", "\\^");
		delimiterExpression = delimiterExpression.replace("{", "\\{");
		delimiterExpression = delimiterExpression.replace("[", "\\[");
		delimiterExpression = delimiterExpression.replace("(", "\\(");
		delimiterExpression = delimiterExpression.replace(")", "\\)");
		delimiterExpression = delimiterExpression.replace("+", "\\+");
		return delimiterExpression;
	}

}
