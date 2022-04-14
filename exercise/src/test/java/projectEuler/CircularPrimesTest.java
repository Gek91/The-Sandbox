package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CircularPrimesTest {

	@Test
	public void numLength(){
		assertEquals(2,CircularPrimes.numlength(10));
		assertEquals(4,CircularPrimes.numlength(1000));
		assertEquals(6,CircularPrimes.numlength(100000));
		assertEquals(7,CircularPrimes.numlength(1000000));
	}
	
	@Test
	public void circularValues(){
		int[] result = CircularPrimes.circularValues(123);
		assertEquals(312,result[0]);
		assertEquals(231,result[1]);
	}
	
	@Test
	public void countCirculaPrimeNumber(){
		assertEquals(13,CircularPrimes.numberOfPrimeCircular(100));
		System.out.println(CircularPrimes.numberOfPrimeCircular(1000000));
	}
}
