package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class NumberLetterCountTest {

	@Test
	public void fist20(){
		assertEquals("two",NumberLetterCount.first20(2));
		assertEquals("ten",NumberLetterCount.first20(10));
	}
	
	@Test
	public void from20To99(){
		assertEquals("twentytwo",NumberLetterCount.from20To99(22));
		assertEquals("ninetynine",NumberLetterCount.from20To99(99));
		assertEquals("fortyseven",NumberLetterCount.from20To99(47));
	}
	
	@Test
	public void hundreds(){
		assertEquals("two",NumberLetterCount.hundreds(2));
		assertEquals("ten",NumberLetterCount.hundreds(10));
		assertEquals("twenty",NumberLetterCount.hundreds(20));
		assertEquals("twohundred",NumberLetterCount.hundreds(200));
		assertEquals("twohundredandfour",NumberLetterCount.hundreds(204));
		assertEquals("twohundredandforty",NumberLetterCount.hundreds(240));
		assertEquals("twentytwo",NumberLetterCount.hundreds(22));
		assertEquals("ninetynine",NumberLetterCount.hundreds(99));
		assertEquals("fortyseven",NumberLetterCount.hundreds(47));
		assertEquals("twohundredandtwentytwo",NumberLetterCount.hundreds(222));
		assertEquals("ninehundredandfiftyfour",NumberLetterCount.hundreds(954));
		assertEquals("onehundredandeleven",NumberLetterCount.hundreds(111));
		assertEquals("ninehundredandninetynine",NumberLetterCount.hundreds(999));
		
		assertEquals(23,NumberLetterCount.hundreds(342).length());
		assertEquals(20,NumberLetterCount.hundreds(115).length());
	}
	
	@Test
	public void countLetterFromOne(){
		assertEquals(19,NumberLetterCount.countLetterFromOne(5));
		assertEquals(22,NumberLetterCount.countLetterFromOne(6));
		assertEquals(27,NumberLetterCount.countLetterFromOne(7));
		assertEquals(32,NumberLetterCount.countLetterFromOne(8));
		assertEquals(36,NumberLetterCount.countLetterFromOne(9));
		assertEquals(39,NumberLetterCount.countLetterFromOne(10));
		System.out.println( ("onethousand".length()));
		System.out.println(NumberLetterCount.countLetterFromOne(999));
		System.out.println(NumberLetterCount.countLetterFromOne(999) + ("onethousand".length()));
	}
}
