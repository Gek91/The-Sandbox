package projectEuler;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;


public class SmallestMultipleTest {

	@Test
	public void primeFactorsMap(){
		Map<Long,Integer> result = SmallestMultiple.primeFactorsMap(2);
		assertEquals(1,result.get(2L).intValue());
		result = SmallestMultiple.primeFactorsMap(6);
		assertEquals(1,result.get(2L).intValue());
		assertEquals(1,result.get(3L).intValue());
		result = SmallestMultiple.primeFactorsMap(8);
		assertEquals(3,result.get(2L).intValue());
	}
	
	@Test
	public void MCM(){
		List<Integer> listValue = new ArrayList<Integer>();
		listValue.add(1);
		listValue.add(2);
		listValue.add(3);
		listValue.add(4);
		listValue.add(5);
		listValue.add(6);
		listValue.add(7);
		listValue.add(8);
		listValue.add(9);
		listValue.add(10);
		assertEquals(2520,SmallestMultiple.MCM(listValue));
		listValue.add(11);
		listValue.add(12);
		listValue.add(13);
		listValue.add(14);
		listValue.add(15);
		listValue.add(16);
		listValue.add(17);
		listValue.add(18);
		listValue.add(19);
		listValue.add(20);
		System.out.println(SmallestMultiple.MCM(listValue));
	}
}
