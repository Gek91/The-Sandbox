package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class AmicableNumberTest {

	@Test
	public void divisor(){
		assertEquals(1,AmicableNumber.divisors(3).size());
		assertEquals(2,AmicableNumber.divisors(4).size());
		AmicableNumber.divisors(220);
	}
	
	@Test
	public void sumDivisor(){
		assertEquals(1,AmicableNumber.sumDivisor(3));
		assertEquals(3,AmicableNumber.sumDivisor(4));
		assertEquals(284,AmicableNumber.sumDivisor(220));
		assertEquals(220,AmicableNumber.sumDivisor(284));
	}
	
	@Test
	public void amicableNumberListLessThanN(){
		AmicableNumber.amicableNumberListLessThanN(10000);
	}
	
	@Test
	public void sumAmicableNumbersLessthanN(){
		System.out.println(AmicableNumber.sumAmicableNumbersLessthanN(10000));
	}
}
