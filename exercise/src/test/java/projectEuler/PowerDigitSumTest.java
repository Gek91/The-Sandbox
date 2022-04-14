package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PowerDigitSumTest {

	@Test
	public void doubleNumberString(){
		assertEquals("4",PowerDigitSum.doubleNumberStrig("2"));
		assertEquals("88",PowerDigitSum.doubleNumberStrig("44"));
		assertEquals("100",PowerDigitSum.doubleNumberStrig("50"));
		assertEquals("11264",PowerDigitSum.doubleNumberStrig("5632"));
	}
	
	@Test
	public void twoPowN(){
		assertEquals("4",PowerDigitSum.twoPowN(2));
		assertEquals("1024",PowerDigitSum.twoPowN(10));
//		System.out.println(PowerDigitSum.twoPowN(1000));
	}
	
	@Test
	public void powDigitSum(){
		assertEquals(4,PowerDigitSum.powDigitSum(2));
		assertEquals(7,PowerDigitSum.powDigitSum(10));
		System.out.println(PowerDigitSum.powDigitSum(1000));
	}
}
