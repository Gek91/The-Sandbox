package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ConsecutivePrimeSumTest {

	
	 @Test
	 public void consecutivePrimeSum(){
		 assertEquals(6,ConsecutivePrimeSum.consecutivePrimeSum(100));
		 assertEquals(21,ConsecutivePrimeSum.consecutivePrimeSum(1000));
		 ConsecutivePrimeSum.consecutivePrimeSum(10000);
//		 ConsecutivePrimeSum.consecutivePrimeSum(100000);
//		 ConsecutivePrimeSum.consecutivePrimeSum(1000000);
	 }
}
