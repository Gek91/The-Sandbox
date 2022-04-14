package projectEuler;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;


public class SumOfPrimesTest {

	@Test
	public void primesBelowN(){
		List<Long> primes = SumOfPrimes.primesBelowN(10L);
		assertEquals(primes.get(0).intValue(),2);
		assertEquals(primes.get(1).intValue(),3);
		assertEquals(primes.get(2).intValue(),5);
		assertEquals(primes.get(3).intValue(),7);
		assertEquals(primes.size(),4);
		
		primes = SumOfPrimes.primesBelowN(20L);
		assertEquals(primes.get(0).intValue(),2);
		assertEquals(primes.get(1).intValue(),3);
		assertEquals(primes.get(2).intValue(),5);
		assertEquals(primes.get(3).intValue(),7);
		assertEquals(primes.get(4).intValue(),11);
		assertEquals(primes.get(5).intValue(),13);
		assertEquals(primes.get(6).intValue(),17);
		assertEquals(primes.get(7).intValue(),19);
		assertEquals(primes.size(),8);
	}
	
	@Test
	public void primeBelowNSum(){
		long sum = SumOfPrimes.primesBelowNSum(10L);
		assertEquals(sum,17);
		sum = SumOfPrimes.primesBelowNSum(20L);
		assertEquals(sum,77);
		System.out.println(SumOfPrimes.primesBelowNSum(2000000));
	}
}
