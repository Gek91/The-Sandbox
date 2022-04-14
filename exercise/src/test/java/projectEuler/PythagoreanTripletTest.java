package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PythagoreanTripletTest {
	
	@Test
	public void pythagoreanTriplet(){
		int[] triplet = PythagoreanTriplet.pythagoreanTriplet(1,2);
		assertEquals((int) Math.pow(triplet[2],2),(int) Math.pow(triplet[0],2) + (int) Math.pow(triplet[1],2));
		triplet = PythagoreanTriplet.pythagoreanTriplet(1,3);
		assertEquals((int) Math.pow(triplet[2],2),(int) Math.pow(triplet[0],2) + (int) Math.pow(triplet[1],2));
	}
	
	@Test
	public void pythagoreanTripletSum(){
		int[] triplet = PythagoreanTriplet.pythagoreanTripletSum(12);
		assertEquals(3,triplet[0]);
		assertEquals(4,triplet[1]);
		assertEquals(5,triplet[2]);
		triplet = PythagoreanTriplet.pythagoreanTripletSum(40);
		assertEquals(15,triplet[0]);
		assertEquals(8,triplet[1]);
		assertEquals(17,triplet[2]);

	}
	
	@Test
	public void pythagoreanTripletSumProduct(){
		assertEquals(60,PythagoreanTriplet.pythagoreanTripletSumProduct(12));
		assertEquals(2040,PythagoreanTriplet.pythagoreanTripletSumProduct(40));
		System.out.println(PythagoreanTriplet.pythagoreanTripletSumProduct(1000));
	}
}
