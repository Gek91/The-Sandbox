package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class Multiple3or5Test {

	@Test
	public void test10(){
		int result = Multiple3or5.sumOfmultiple(10);
		assertEquals(23,result);
		int result1 = Multiple3or5.sumOfmultiple2(10);
		assertEquals(result1,result);
	}
	
	@Test
	public void  test11(){
		int result = Multiple3or5.sumOfmultiple(11);
		assertEquals(33,result);
		int result1 = Multiple3or5.sumOfmultiple2(11);
		assertEquals(result1,result);
	}
	
	@Test
	public void test13(){
		int result = Multiple3or5.sumOfmultiple(13);
		assertEquals(45,result);
		int result1 = Multiple3or5.sumOfmultiple2(13);
		assertEquals(result1,result);
	}
	
	@Test
	public void test1000(){
		int result = Multiple3or5.sumOfmultiple(1000);
		System.out.print(result);
		int result1 = Multiple3or5.sumOfmultiple2(1000);
		assertEquals(result1,result);
	}
}


