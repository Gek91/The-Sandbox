package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class LargerPrimeFactorTest {

	
	@Test
	public void primeFactor(){
		assertEquals(2,LargerPrimeFactor.largerPrimeFactor(2));
		assertEquals(2,LargerPrimeFactor.largerPrimeFactor(4));
		assertEquals(5,LargerPrimeFactor.largerPrimeFactor(5));
		assertEquals(13,LargerPrimeFactor.largerPrimeFactor(39));
//		System.out.print(LargerPrimeFactor.largerPrimeFactor(13195));
		long value = 600851475143L;
		System.out.print(LargerPrimeFactor.largerPrimeFactor(value));
	}
}
