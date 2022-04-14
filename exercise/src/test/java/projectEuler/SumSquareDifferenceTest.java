package projectEuler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class SumSquareDifferenceTest {

	@Test
	public void sumOfSquare(){
		List<Integer> listOfElement = new ArrayList<Integer>();
		listOfElement.add(2);
		assertEquals(4,SumSquareDifference.sumOfSquare(listOfElement));
		listOfElement.add(3);
		assertEquals(13,SumSquareDifference.sumOfSquare(listOfElement));
		listOfElement.add(5);
		assertEquals(38,SumSquareDifference.sumOfSquare(listOfElement));
	}
	
	@Test
	public void squareOfSum(){
		List<Integer> listOfElement = new ArrayList<Integer>();
		listOfElement.add(2);
		assertEquals(4,SumSquareDifference.squareOfSum(listOfElement));
		listOfElement.add(3);
		assertEquals(25,SumSquareDifference.squareOfSum(listOfElement));
		listOfElement.add(5);
		assertEquals(100,SumSquareDifference.squareOfSum(listOfElement));
	}
	
	@Test
	public void differenceSumOfSquareSquareOfSum(){
		List<Integer> listOfElement = new ArrayList<Integer>();
		listOfElement.add(2);
		assertEquals(0,SumSquareDifference.differenceSumOfSquareSquareOfSum(listOfElement));
		listOfElement.add(3);
		assertEquals(12,SumSquareDifference.differenceSumOfSquareSquareOfSum(listOfElement));
		listOfElement.add(5);
		assertEquals(62,SumSquareDifference.differenceSumOfSquareSquareOfSum(listOfElement));
		listOfElement.add(1);
		listOfElement.add(4);
		listOfElement.add(6);
		listOfElement.add(7);
		listOfElement.add(8);
		listOfElement.add(9);
		listOfElement.add(10);
		assertEquals(2640,SumSquareDifference.differenceSumOfSquareSquareOfSum(listOfElement));
		listOfElement = generateListOf100Elements();
		System.out.println(SumSquareDifference.differenceSumOfSquareSquareOfSum(listOfElement));
		
	}
	
	private List<Integer> generateListOf100Elements(){
		List<Integer> list = new ArrayList<Integer>(100);
		for(int i = 0; i<101;i++){
			list.add(i);
		}
		return list;
	}
}
