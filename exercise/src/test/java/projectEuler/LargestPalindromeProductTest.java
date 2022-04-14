package projectEuler;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;


public class LargestPalindromeProductTest {
	@Test
	public void getDigits(){
		List<Integer> result = LargestPalindromeProduct.getDigits(2);
		assertEquals(2,result.get(0).intValue());
		result = LargestPalindromeProduct.getDigits(12);
		assertEquals(1,result.get(1).intValue());
		result = LargestPalindromeProduct.getDigits(123);
		assertEquals(1,result.get(2).intValue());
	}
	
	@Test
	public void isPalindrome(){
		assertEquals(true,LargestPalindromeProduct.isPalindrome(22));
		assertEquals(true,LargestPalindromeProduct.isPalindrome(2));
		assertEquals(true,LargestPalindromeProduct.isPalindrome(232));
		assertEquals(false,LargestPalindromeProduct.isPalindrome(322));
		assertEquals(true,LargestPalindromeProduct.isPalindrome(3223));
	}
	
	@Test
	public void largestPalindromeProduct(){
		assertEquals(9009,LargestPalindromeProduct.calculateLargestPalindromeProduct(2));
		assertEquals(9,LargestPalindromeProduct.calculateLargestPalindromeProduct(1));
		System.out.println(LargestPalindromeProduct.calculateLargestPalindromeProduct(3));
		System.out.println(LargestPalindromeProduct.calculateLargestPalindromeProduct(4));
	}
	
}
