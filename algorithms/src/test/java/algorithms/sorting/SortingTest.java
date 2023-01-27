package algorithms.sorting;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SortingTest {

	@Test
	public void insertionSortTest1() {

		Integer[] input = {2,6,8,4,1};

		Sorting.insertionSort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(2), input[1]);
		Assert.assertEquals(new Integer(4), input[2]);
		Assert.assertEquals(new Integer(6), input[3]);
		Assert.assertEquals(new Integer(8), input[4]);
	}

	@Test
	public void insertionSortTest2() {

		Integer[] input = {1,2,3,4,5};

		Sorting.insertionSort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(2), input[1]);
		Assert.assertEquals(new Integer(3), input[2]);
		Assert.assertEquals(new Integer(4), input[3]);
		Assert.assertEquals(new Integer(5), input[4]);
	}

	@Test
	public void insertionSortTest3() {

		Integer[] input = {1,1,1,1,1};

		Sorting.insertionSort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(1), input[1]);
		Assert.assertEquals(new Integer(1), input[2]);
		Assert.assertEquals(new Integer(1), input[3]);
		Assert.assertEquals(new Integer(1), input[4]);
	}

	@Test
	public void insertionSortTestPerformance() {

		Integer[] input = new Integer[100000];

		for(int i = 0 ; i < 100000 ; i++) {
			input[i] = ThreadLocalRandom.current().nextInt(1, 99999 + 1);
		}

		Instant startTime = Instant.now();

		Sorting.insertionSort(input);

		Instant endTime = Instant.now();
		System.out.println("insertionSort " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

	}


	@Test
	public void heapSortTest1() {

		Integer[] input = {2,6,8,4,1};

		Sorting.heapsort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(2), input[1]);
		Assert.assertEquals(new Integer(4), input[2]);
		Assert.assertEquals(new Integer(6), input[3]);
		Assert.assertEquals(new Integer(8), input[4]);
	}

	@Test
	public void heapSortTest2() {

		Integer[] input = {1,2,3,4,5};

		Sorting.heapsort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(2), input[1]);
		Assert.assertEquals(new Integer(3), input[2]);
		Assert.assertEquals(new Integer(4), input[3]);
		Assert.assertEquals(new Integer(5), input[4]);
	}

	@Test
	public void heapSortTest3() {

		Integer[] input = {1,1,1,1,1};

		Sorting.heapsort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(1), input[1]);
		Assert.assertEquals(new Integer(1), input[2]);
		Assert.assertEquals(new Integer(1), input[3]);
		Assert.assertEquals(new Integer(1), input[4]);
	}

	@Test
	public void heapSortTestPerformance() {

		Integer[] input = new Integer[100000];

		for(int i = 0 ; i < 100000 ; i++) {
			input[i] = ThreadLocalRandom.current().nextInt(1, 99999 + 1);
		}

		Instant startTime = Instant.now();

		Sorting.heapsort(input);

		Instant endTime = Instant.now();
		System.out.println("heapSort " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

	}


	@Test
	public void quickSortTest1() {

		Integer[] input = {2,6,8,4,1};

		Sorting.quickSort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(2), input[1]);
		Assert.assertEquals(new Integer(4), input[2]);
		Assert.assertEquals(new Integer(6), input[3]);
		Assert.assertEquals(new Integer(8), input[4]);
	}

	@Test
	public void quickSortTest2() {

		Integer[] input = {1,2,3,4,5};

		Sorting.quickSort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(2), input[1]);
		Assert.assertEquals(new Integer(3), input[2]);
		Assert.assertEquals(new Integer(4), input[3]);
		Assert.assertEquals(new Integer(5), input[4]);
	}

	@Test
	public void quickSortTest3() {

		Integer[] input = {1,1,1,1,1};

		Sorting.quickSort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(1), input[1]);
		Assert.assertEquals(new Integer(1), input[2]);
		Assert.assertEquals(new Integer(1), input[3]);
		Assert.assertEquals(new Integer(1), input[4]);
	}

	@Test
	public void quickSortTestPerformance() {

		Integer[] input = new Integer[100000];

		for(int i = 0 ; i < 100000 ; i++) {
			input[i] = ThreadLocalRandom.current().nextInt(1, 99999 + 1);
		}

		Instant startTime = Instant.now();

		Sorting.quickSort(input);

		Instant endTime = Instant.now();
		System.out.println("quickSort " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

	}

	@Test
	public void mergeSortTest1() {

		Integer[] input = {2,6,8,4,1};

		Sorting.mergeSort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(2), input[1]);
		Assert.assertEquals(new Integer(4), input[2]);
		Assert.assertEquals(new Integer(6), input[3]);
		Assert.assertEquals(new Integer(8), input[4]);
	}

	@Test
	public void mergeSortTest2() {

		Integer[] input = {1,2,3,4,5};

		Sorting.mergeSort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(2), input[1]);
		Assert.assertEquals(new Integer(3), input[2]);
		Assert.assertEquals(new Integer(4), input[3]);
		Assert.assertEquals(new Integer(5), input[4]);
	}

	@Test
	public void mergeSortTest3() {

		Integer[] input = {1,1,1,1,1};

		Sorting.mergeSort(input);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(1), input[1]);
		Assert.assertEquals(new Integer(1), input[2]);
		Assert.assertEquals(new Integer(1), input[3]);
		Assert.assertEquals(new Integer(1), input[4]);
	}

	@Test
	public void mergeSortTestPerformance() {

		Integer[] input = new Integer[100000];

		for(int i = 0 ; i < 100000 ; i++) {
			input[i] = ThreadLocalRandom.current().nextInt(1, 99999 + 1);
		}

		Instant startTime = Instant.now();

		Sorting.mergeSort(input);

		Instant endTime = Instant.now();
		System.out.println("mergeSort " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

	}

	@Test
	public void countingSortTest1() {

		Integer[] input = {2,6,8,4,1};

		Sorting.countingSort(input,10);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(2), input[1]);
		Assert.assertEquals(new Integer(4), input[2]);
		Assert.assertEquals(new Integer(6), input[3]);
		Assert.assertEquals(new Integer(8), input[4]);
	}

	@Test
	public void countingSort2() {

		Integer[] input = {1,2,3,4,5};

		Sorting.countingSort(input, 10);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(2), input[1]);
		Assert.assertEquals(new Integer(3), input[2]);
		Assert.assertEquals(new Integer(4), input[3]);
		Assert.assertEquals(new Integer(5), input[4]);
	}

	@Test
	public void countingSortTest3() {

		Integer[] input = {1,1,1,1,1};

		Sorting.countingSort(input, 2);

		Assert.assertEquals(5, input.length);

		Assert.assertEquals(new Integer(1), input[0]);
		Assert.assertEquals(new Integer(1), input[1]);
		Assert.assertEquals(new Integer(1), input[2]);
		Assert.assertEquals(new Integer(1), input[3]);
		Assert.assertEquals(new Integer(1), input[4]);
	}

	@Test
	public void countingSortTestPerformance() {

		Integer[] input = new Integer[100000];

		for(int i = 0 ; i < 100000 ; i++) {
			input[i] = ThreadLocalRandom.current().nextInt(1, 99999 + 1);
		}

		Instant startTime = Instant.now();

		Sorting.countingSort(input, 1000001);

		Instant endTime = Instant.now();
		System.out.println("countingSort " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

	}
}
