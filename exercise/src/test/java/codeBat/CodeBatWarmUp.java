package codeBat;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CodeBatWarmUp {
	@Test
	public void warmUp1(){
		assertEquals(WarmUp.sleepIn(false, false),true);
		assertEquals(WarmUp.sleepIn(true, false),false);
		assertEquals(WarmUp.sleepIn(false, true),true);
	}
	
	@Test
	public void warmUp2(){
		int n = 1;
		assertEquals(21 - n , WarmUp.diff21(n) );
		n = 31;
		assertEquals(  Math.abs((21 - n)*2)  , WarmUp.diff21(n) );
	}
	
	@Test
	public void warmUp3(){
		assertEquals(true, WarmUp.nearHundred(99) );
		assertEquals(false, WarmUp.nearHundred(49) );
		assertEquals(true, WarmUp.nearHundred(101) );
		assertEquals(true, WarmUp.nearHundred(201) );
		assertEquals(true, WarmUp.nearHundred(199) );
	}

	@Test
	public void warUp4(){
		assertEquals("ktten",WarmUp.missingChar("kitten", 1));
		assertEquals("itten",WarmUp.missingChar("kitten", 0));
		assertEquals("kittn",WarmUp.missingChar("kitten", 4));
	}
	
	@Test
	public void warmUp5(){
		assertEquals("tcatt",WarmUp.backAround("cat"));
		assertEquals("oHelloo",WarmUp.backAround("Hello"));
		assertEquals("aaa",WarmUp.backAround("a"));
	}
	
	@Test
	public void warmUp6(){
		assertEquals(true,WarmUp.starHi("hi there"));
		assertEquals(false,WarmUp.starHi("hello hi"));
		assertEquals(true,WarmUp.starHi("hi"));
	}
	
	@Test
	public void warmUp7(){
		assertEquals(true,WarmUp.hasTeen(13, 13, 13));
		assertEquals(false,WarmUp.hasTeen(20, 20, 20));
		assertEquals(true,WarmUp.hasTeen(13, 20, 10));
		assertEquals(true,WarmUp.hasTeen(10, 20, 13));
	}
	
	@Test
	public void warmUp8(){
		assertEquals(true,WarmUp.mixStart("mix snack"));
		assertEquals(true,WarmUp.mixStart("pix"));
		assertEquals(false,WarmUp.mixStart("miz"));
	}
	
	@Test
	public void warmUp9(){
		assertEquals(8,WarmUp.close10(8, 13));
		assertEquals(8,WarmUp.close10(13, 8));
		assertEquals(0,WarmUp.close10(13, 7));

	}
	
	@Test
	public void warmUp10(){
		assertEquals(true,WarmUp.stringE("hello"));
		assertEquals(true,WarmUp.stringE("heelle"));
		assertEquals(false,WarmUp.stringE("heellele"));
	}
	
	@Test
	public void warmUp11(){
		assertEquals("ciao",WarmUp.everyNth("ciao", 1));
		assertEquals("ca",WarmUp.everyNth("ciao", 2));
		assertEquals("co",WarmUp.everyNth("ciao", 3));
	}
	
	@Test
	public void warmUp12(){
		assertEquals(true,WarmUp.monkeyTrouble(true, true));
		assertEquals(false,WarmUp.monkeyTrouble(true, false));
		assertEquals(true,WarmUp.monkeyTrouble(false, false));
	}
	
	@Test
	public void warmUp13(){
		assertEquals(true,WarmUp.parrotTrouble(true, 6));
		assertEquals(false,WarmUp.parrotTrouble(true, 7));
		assertEquals(false,WarmUp.parrotTrouble(false, 6));
	}
	
	@Test
	public void warmUp14(){
		assertEquals(true,WarmUp.posNeg(-1, 1, false));
		assertEquals(true,WarmUp.posNeg(1, -1, false));
		assertEquals(true,WarmUp.posNeg(-1, -1, true));
	}
	
	@Test
	public void warmUp15(){
		assertEquals("ao", WarmUp.frontBack("oa"));
		assertEquals("a", WarmUp.frontBack("a"));
	}
	
	@Test
	public void warmUp16(){
		assertEquals(true,WarmUp.or35(3));
		assertEquals(true,WarmUp.or35(5));
		assertEquals(false,WarmUp.or35(8));
	}
	
	@Test
	public void warmUp17(){
		assertEquals(true,WarmUp.icyHot(-1, 101));
		assertEquals(true,WarmUp.icyHot(101, -1));
		assertEquals(false,WarmUp.icyHot(1, 101));
	}
	
	@Test
	public void warmUp18(){
		assertEquals(true,WarmUp.loneTeen(1, 13));
		assertEquals(true,WarmUp.loneTeen(13, 1));
		assertEquals(false,WarmUp.loneTeen(13, 13));
	}
	
	@Test
	public void warmUp19(){
		assertEquals("oz",WarmUp.startOz("oz"));
		assertEquals("z",WarmUp.startOz("az"));
		assertEquals("o",WarmUp.startOz("oe"));
		assertEquals("",WarmUp.startOz("ae"));
	}
	
	@Test
	public void warmUp20(){
		assertEquals(true, WarmUp.in3050(31, 32));
		assertEquals(true, WarmUp.in3050(41, 42));
		assertEquals(false, WarmUp.in3050(31, 42));
	}
	
	@Test
	public void warmUp21(){
		assertEquals(true,WarmUp.lastDigit(17, 57));
		assertEquals(false,WarmUp.lastDigit(6, 57));
		assertEquals(true,WarmUp.lastDigit(3, 113));
	}
	
	@Test
	public void warmUp22(){
		assertEquals(4,WarmUp.sumDouble(3, 1));
		assertEquals(8,WarmUp.sumDouble(2, 2));
		assertEquals(-2,WarmUp.sumDouble(-3, 1));
		assertEquals(-8,WarmUp.sumDouble(-2, -2));
	}
	
	@Test
	public void warmUp23(){
		assertEquals(true,WarmUp.makes10(1, 10));
		assertEquals(true,WarmUp.makes10(10, 1));
		assertEquals(true,WarmUp.makes10(5, 5));
		assertEquals(false,WarmUp.makes10(1, 5));
	}
	
	@Test
	public void warmUp24(){
		assertEquals("not ok",WarmUp.notString("ok"));
		assertEquals("notok",WarmUp.notString("notok"));
		assertEquals("not k",WarmUp.notString("k"));
	}
	
	@Test
	public void warmUp25(){
		assertEquals("ciaciacia",WarmUp.front3("ciao"));
		assertEquals("okokok",WarmUp.front3("ok"));
	}
	
	@Test
	public void warmUp26(){
		assertEquals("ciciaoci",WarmUp.front22("ciao"));
		assertEquals("aaa",WarmUp.front22("a"));
	}
	
	@Test
	public void warmUp27(){
		assertEquals(true,WarmUp.in1020(10, 20));
		assertEquals(true,WarmUp.in1020(10, 21));
		assertEquals(true,WarmUp.in1020(9, 20));
		assertEquals(false,WarmUp.in1020(9, 21));
	}
	
	@Test
	public void warmUp28(){
		assertEquals("ciao",WarmUp.delDel("cdeliao"));
		assertEquals("ciao",WarmUp.delDel("ciao"));
		assertEquals("ok",WarmUp.delDel("ok"));
	}
	
	@Test
	public void warmUp29(){
		assertEquals(3,WarmUp.intMax(1, 2, 3));
		assertEquals(3,WarmUp.intMax(3, 2, 1));
		assertEquals(3,WarmUp.intMax(1, 3, 1));
		assertEquals(3,WarmUp.intMax(3, 3, 3));
	}
	
	@Test
	public void warmUp30(){
		assertEquals(20,WarmUp.max1020(10, 20));
		assertEquals(10,WarmUp.max1020(10, 21));
	}
	
	@Test
	public void warmUp31(){
		assertEquals("cIAO",WarmUp.endUp("ciao"));
		assertEquals("CI",WarmUp.endUp("ci"));
	}
}
