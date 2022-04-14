package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class HighlyDivisibleTriangularNumberTest {

	@Test
	public void trinagularNumber(){
		assertEquals(3,HighlyDivisibleTriangularNumber.triangularNumber(2));
		assertEquals(6,HighlyDivisibleTriangularNumber.triangularNumber(3));
		assertEquals(28,HighlyDivisibleTriangularNumber.triangularNumber(7));
	}
	
	@Test
	public void countPrimeFactorTriangularNumber(){
		assertEquals(2,HighlyDivisibleTriangularNumber.countFactorNumber(HighlyDivisibleTriangularNumber.triangularNumber(2)));
		assertEquals(4,HighlyDivisibleTriangularNumber.countFactorNumber(HighlyDivisibleTriangularNumber.triangularNumber(3)));
		assertEquals(6,HighlyDivisibleTriangularNumber.countFactorNumber(HighlyDivisibleTriangularNumber.triangularNumber(7)));
	}
	
	@Test
	public void triangularNumberWithMoreThanNFactor(){
		assertEquals(6,HighlyDivisibleTriangularNumber.triangularNumberWithMoreThanNFactor(2));
		assertEquals(6,HighlyDivisibleTriangularNumber.triangularNumberWithMoreThanNFactor(3));
		assertEquals(28,HighlyDivisibleTriangularNumber.triangularNumberWithMoreThanNFactor(4));
		System.out.print(HighlyDivisibleTriangularNumber.triangularNumberWithMoreThanNFactor(500));
	}
}
