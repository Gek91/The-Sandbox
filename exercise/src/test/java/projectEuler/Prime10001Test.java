package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class Prime10001Test {

	@Test
	public void isPrime(){
		assertEquals(true,Prime10001.isPrime1(2));
		assertEquals(true,Prime10001.isPrime1(3));
		assertEquals(true,Prime10001.isPrime1(5));
		assertEquals(true,Prime10001.isPrime1(7));
		assertEquals(false,Prime10001.isPrime1(8));
	}
	
	@Test
	public void nthPrime(){
		assertEquals(2,Prime10001.nthPrime(1));
		assertEquals(3,Prime10001.nthPrime(2));
		assertEquals(5,Prime10001.nthPrime(3));
		assertEquals(7,Prime10001.nthPrime(4));
		assertEquals(11,Prime10001.nthPrime(5));
		assertEquals(13,Prime10001.nthPrime(6));
		assertEquals(17,Prime10001.nthPrime(7));
		System.out.println(Prime10001.nthPrime(10001));
	}
}
