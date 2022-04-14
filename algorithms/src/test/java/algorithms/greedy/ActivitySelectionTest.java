package algorithms.greedy;

import java.util.List;

import org.junit.Test;

import algorithms.greedy.ActivitySelection;

public class ActivitySelectionTest {

	@Test
	public void test(){
		List<Integer> result = ActivitySelection.recursiveActivitySelector(0, 12);
		for(int e : result){
			System.out.println(e);
		}
	}
	
	@Test
	public void testIter(){
		List<Integer> result = ActivitySelection.GreedyActivitySelector(12);
		for(int e : result){
			System.out.println(e);
		}
	}
}
