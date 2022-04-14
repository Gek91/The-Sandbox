package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class LargestProductTest {
	
	@Test
	public void largestProduct(){
		assertEquals(81,LargestProduct.largestProduct(2));
		assertEquals(648,LargestProduct.largestProduct(3));
		assertEquals(5832,LargestProduct.largestProduct(4));
		System.out.println(LargestProduct.largestProduct(13));
	}

}
