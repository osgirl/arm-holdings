package uk.arm.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.arm.string.calc.StringCalculator;

@RestController
public class StringCalculatorResource {
	
	StringCalculator stringCalc = new StringCalculator();
	
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	public int add(@RequestParam(value="numbers", defaultValue="1\n2,3") String numbers) {
		return stringCalc.add(numbers);
	}

}
