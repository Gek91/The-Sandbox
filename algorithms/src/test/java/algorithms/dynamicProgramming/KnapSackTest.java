package algorithms.dynamicProgramming;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import algorithms.dynamicProgramming.Knapsack;

public class KnapSackTest {

	@Test
	public void testDividiEtImpera() {
		List<Boolean> used = new ArrayList<>();
		used.add(false);
		used.add(false);
		used.add(false);
				
		int result = Knapsack.knapsack(50, used);
		assertEquals(result, 220);
		System.out.println(result);
	}
	
	@Test
	public void DynamicWithMemo() {
		List<Boolean> used = new ArrayList<>();
		used.add(false);
		used.add(false);
		used.add(false);
		
		int result = Knapsack.memKnapsack(50, used);
		assertEquals(result, 220);
		System.out.println(result);
	}
}
