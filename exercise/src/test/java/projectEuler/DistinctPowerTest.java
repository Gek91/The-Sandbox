package projectEuler;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;


public class DistinctPowerTest {

	
	@Test
	public void testStringPow(){
		assertEquals("4", DistinctPower.StringPow(2,2));
		assertEquals("27", DistinctPower.StringPow(3,3));
		assertEquals("3125", DistinctPower.StringPow(5,5));
	}
	
	@Test
	public void testGenerateSequence(){
		Set<String> result = DistinctPower.GenerateSequence(5);
		assertEquals(true,result.contains("4"));
		assertEquals(true,result.contains("243"));
		assertEquals(true,result.contains("1024"));
		assertEquals(true,result.contains("3125"));
	}
	
	@Test
	public void testCountGenerateSequenece(){
		assertEquals(15,DistinctPower.countGeneratedSequence(5));
		System.out.println(DistinctPower.countGeneratedSequence(100));
	}
}
