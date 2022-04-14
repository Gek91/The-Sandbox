package projectEuler;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;


public class NonAbbundantSumTest {

	
	@Test
	public void isAbbundantNumber(){
		assertEquals(true, NonAbbundantSum.isAbbundantNumber(12) );
	}
	
	@Test
	public void isPerfectNumber(){
		assertEquals(true, NonAbbundantSum.isPerfectNumber(28) );
	}
	
	@Test
	public void isDeficientNumber(){
		assertEquals(true, NonAbbundantSum.isDeficientNumber(14) );
	}
	
	@Test
	public void isSumOfAbbundant(){
		assertEquals(true, NonAbbundantSum.isSumOfAbbundant(24));
		assertEquals(false, NonAbbundantSum.isSumOfAbbundant(23));
		assertEquals(true,NonAbbundantSum.isSumOfAbbundant(30));
		Set<Integer> abbundant = NonAbbundantSum.setOfAbbundantLessThanN(28123);
		assertEquals(NonAbbundantSum.isSumOfAbbundant(30),NonAbbundantSum.isSumOfAbbundant(30,abbundant));
		assertEquals(NonAbbundantSum.isSumOfAbbundant(23),NonAbbundantSum.isSumOfAbbundant(23,abbundant));
	}
	
	@Test
	public void setOfAbbundantLessThanN(){
		assertEquals(0,NonAbbundantSum.setOfAbbundantLessThanN(12).size());
		assertEquals(1,NonAbbundantSum.setOfAbbundantLessThanN(13).size());
		assertEquals(4,NonAbbundantSum.setOfAbbundantLessThanN(30).size());
		System.out.println("List of abbundant:" + NonAbbundantSum.setOfAbbundantLessThanN(28123/2 +1).size());
		System.out.println("List of abbundant:" + NonAbbundantSum.setOfAbbundantLessThanN(28123).size());
	}
	
	@Test
	public void nonAbbundantNumberList(){
		System.out.println("Number of non abbundant:" + NonAbbundantSum.nonAbbundantNumberList(28123/2 +1).size());
		System.out.println("Number of non abbundant:" + NonAbbundantSum.nonAbbundantNumberList(28123).size());
	}
	
	@Test
	public void sumNonAbbundantNumberList(){
		System.out.println(NonAbbundantSum.sumNonAbbundantNumberList());
	}
	
}
