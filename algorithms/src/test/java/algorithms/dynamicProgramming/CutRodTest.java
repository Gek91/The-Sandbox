package algorithms.dynamicProgramming;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import algorithms.dynamicProgramming.CutRod;


public class CutRodTest {

	@Test
	public void cutRod(){
		assertEquals(0,CutRod.cutRod(0));
		assertEquals(1,CutRod.cutRod(1));
		assertEquals(5,CutRod.cutRod(2));
		assertEquals(8,CutRod.cutRod(3));
		assertEquals(13,CutRod.cutRod(5));
		assertEquals(25,CutRod.cutRod(9));
	}
	
	@Test
	public void memCutRod(){
		assertEquals(0,CutRod.memCutRod(0));
		assertEquals(1,CutRod.memCutRod(1));
		assertEquals(5,CutRod.memCutRod(2));
		assertEquals(8,CutRod.memCutRod(3));
		assertEquals(13,CutRod.memCutRod(5));
		assertEquals(25,CutRod.memCutRod(9));
	}
	
	@Test
	public void bottomUpCutRod(){
		assertEquals(0,CutRod.bottomUpCutRod(0));
		assertEquals(1,CutRod.bottomUpCutRod(1));
		assertEquals(5,CutRod.bottomUpCutRod(2));
		assertEquals(8,CutRod.bottomUpCutRod(3));
		assertEquals(13,CutRod.bottomUpCutRod(5));
//		assertEquals(25,CutRod.bottomUpCutRod(9));
	}
	
	@Test
	public void extendedBottomUpCutRod(){
		int n = 8;
		int[] solutions = CutRod.extendedBottomUpCutRod(n);
		while(n > 0){
			System.out.println(solutions[n]);
			n = n - solutions[n];
		}
	}
}
