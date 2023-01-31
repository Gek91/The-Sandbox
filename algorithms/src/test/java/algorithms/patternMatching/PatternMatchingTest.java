package algorithms.patternMatching;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class PatternMatchingTest {

	@Test
	public void naiveTest1() {

		String pattern = "world";
		String text = "hello world, welcome world";

		Optional<Integer> optionalResult = PatternMatching.naive(text, pattern);

		Assert.assertTrue(optionalResult.isPresent());
		Assert.assertEquals(Integer.valueOf(6), optionalResult.get());
	}

	@Test
	public void naiveTest2() {

		String pattern = "world!";
		String text = "hello world, welcome world";

		Optional<Integer> optionalResult = PatternMatching.naive(text, pattern);

		Assert.assertFalse(optionalResult.isPresent());
	}

	@Test
	public void naiveTest3() {

		String pattern = "aaiaa";
		String text = "aaiaiaaaiaa";

		Optional<Integer> optionalResult = PatternMatching.naive(text, pattern);

		Assert.assertTrue(optionalResult.isPresent());
		Assert.assertEquals(Integer.valueOf(6), optionalResult.get());
	}

	@Test
	public void knuthMorrisPrattTest1() {

		String pattern = "world";
		String text = "hello world, welcome world";

		List<Integer> results = PatternMatching.knuthMorrisPratt(text, pattern);

		Assert.assertEquals(2, results.size());
		Assert.assertEquals(Integer.valueOf(6), results.get(0));
		Assert.assertEquals(Integer.valueOf(21), results.get(1));

	}

	@Test
	public void knuthMorrisPrattTest2() {

		String pattern = "world!";
		String text = "hello world, welcome world";

		List<Integer> results = PatternMatching.knuthMorrisPratt(text, pattern);

		Assert.assertEquals(0, results.size());
	}

	@Test
	public void knuthMorrisPrattTest3() {

		String pattern = "aaiaa";
		String text = "aaiaiaaaiaa";

		List<Integer> results = PatternMatching.knuthMorrisPratt(text, pattern);

		Assert.assertEquals(1, results.size());
		Assert.assertEquals(Integer.valueOf(6), results.get(0));
	}

	@Test
	public void matchingPrefixTest1() {

		String pattern = "world";
		String text = "hello world, welcome world";

		List<Integer> results = PatternMatching.matchingPrefix(text, pattern);

		Assert.assertEquals(2, results.size());
		Assert.assertEquals(Integer.valueOf(6), results.get(0));
		Assert.assertEquals(Integer.valueOf(21), results.get(1));

	}

	@Test
	public void matchingPrefixTest2() {

		String pattern = "world!";
		String text = "hello world, welcome world";

		List<Integer> results = PatternMatching.matchingPrefix(text, pattern);

		Assert.assertEquals(0, results.size());
	}

	@Test
	public void matchingPrefixTest3() {

		String pattern = "aaiaa";
		String text = "aaiaiaaaiaa";

		List<Integer> results = PatternMatching.matchingPrefix(text, pattern);

		Assert.assertEquals(1, results.size());
		Assert.assertEquals(Integer.valueOf(6), results.get(0));
	}

	@Test
	public void boyerMooreTest1() {

		String pattern = "world";
		String text = "hello world, welcome world";

		List<Integer> results = PatternMatching.boyerMoore(text, pattern);

		Assert.assertEquals(2, results.size());
		Assert.assertEquals(Integer.valueOf(6), results.get(0));
		Assert.assertEquals(Integer.valueOf(21), results.get(1));

	}

	@Test
	public void boyerMooreTest2() {

		String pattern = "world!";
		String text = "hello world, welcome world";

		List<Integer> results = PatternMatching.boyerMoore(text, pattern);

		Assert.assertEquals(0, results.size());
	}

	@Test
	public void boyerMooreTest3() {

		String pattern = "aaiaa";
		String text = "aaiaiaaaiaa";

		List<Integer> results = PatternMatching.boyerMoore(text, pattern);

		Assert.assertEquals(1, results.size());
		Assert.assertEquals(Integer.valueOf(6), results.get(0));
	}

	@Test
	public void shiftAndTest1() {

		String pattern = "world";
		String text = "hello world, welcome world";

		List<Integer> results = PatternMatching.shiftAnd(text, pattern);

		Assert.assertEquals(2, results.size());
		Assert.assertEquals(Integer.valueOf(6), results.get(0));
		Assert.assertEquals(Integer.valueOf(21), results.get(1));

	}

	@Test
	public void shiftAndTest2() {

		String pattern = "world!";
		String text = "hello world, welcome world";

		List<Integer> results = PatternMatching.shiftAnd(text, pattern);

		Assert.assertEquals(0, results.size());
	}

	@Test
	public void shiftAndTest3() {

		String pattern = "aaiaa";
		String text = "aaiaiaaaiaa";

		List<Integer> results = PatternMatching.shiftAnd(text, pattern);

		Assert.assertEquals(1, results.size());
		Assert.assertEquals(Integer.valueOf(6), results.get(0));
	}
}
