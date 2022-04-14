package projectEuler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;


public class LongestCollatzSequenceTest {

	@Test
	public void collatzSequence(){
		List<Long> cSequence = new ArrayList<Long>();
		LongestCollatzSequence.collatzSequence(13,cSequence);
		assertEquals(10,cSequence.size());
		assertEquals(40L,cSequence.get(1).longValue());
	}
	
	@Test
	public void collatzSequenceMap(){
		Map<Long,Long> cSequenceMap = new HashMap<Long,Long>();
		LongestCollatzSequence.generateCollatzSqueneceMap(13,cSequenceMap);
		assertEquals(10,cSequenceMap.get(13L).longValue());
	}
	
	@Test
	public void maxLenghtCollatzSequence(){
		assertEquals(3,LongestCollatzSequence.maxLenghtCollatzSequence(4));
		System.out.println(LongestCollatzSequence.maxLenghtCollatzSequence(1000000));
	}
}
