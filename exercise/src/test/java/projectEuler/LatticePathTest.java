package projectEuler;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class LatticePathTest {

	@Test
	public void factorial(){
		assertEquals(BigInteger.valueOf(2),LatticePath.factorial(2));
		assertEquals(BigInteger.valueOf(6),LatticePath.factorial(3));
		assertEquals(BigInteger.valueOf(24),LatticePath.factorial(4));
	}
	
	@Test
	public void permutationWithRepeat(){
		List<Integer> types = new ArrayList<Integer>();
		types.add(2);
		types.add(2);
		assertEquals(6L,LatticePath.permutationWithRepeat(4,types));
		
		types = new ArrayList<Integer>();
		types.add(3);
		types.add(3);
		assertEquals(20L,LatticePath.permutationWithRepeat(6,types));
	}
	
	@Test
	public void countLatticePath(){
		assertEquals(6L,LatticePath.countLatticePath(2));
		assertEquals(20L,LatticePath.countLatticePath(3));
		System.out.println(LatticePath.countLatticePath(20));
	}
}
