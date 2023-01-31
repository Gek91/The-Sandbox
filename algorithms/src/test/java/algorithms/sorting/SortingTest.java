package algorithms.sorting;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SortingTest {

	@Test
	public void insertionSortTest1() {

		List<Integer> input = new ArrayList<>(Arrays.asList(2,6,8,4,1));

		Sorting.insertionSort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(2), input.get(1));
		Assert.assertEquals(Integer.valueOf(4), input.get(2));
		Assert.assertEquals(Integer.valueOf(6), input.get(3));
		Assert.assertEquals(Integer.valueOf(8), input.get(4));
	}

	@Test
	public void insertionSortTest2() {

		List<Integer> input = new ArrayList<>(Arrays.asList(1,2,3,4,5));

		Sorting.insertionSort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(2), input.get(1));
		Assert.assertEquals(Integer.valueOf(3), input.get(2));
		Assert.assertEquals(Integer.valueOf(4), input.get(3));
		Assert.assertEquals(Integer.valueOf(5), input.get(4));
	}

	@Test
	public void insertionSortTest3() {

		List<Integer> input = new ArrayList<>(Arrays.asList(1,1,1,1,1));

		Sorting.insertionSort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(1), input.get(1));
		Assert.assertEquals(Integer.valueOf(1), input.get(2));
		Assert.assertEquals(Integer.valueOf(1), input.get(3));
		Assert.assertEquals(Integer.valueOf(1), input.get(4));
	}

	@Test
	public void insertionSortTestPerformance() {

		List<Integer> input = new ArrayList<>(100000);

		for(int i = 0 ; i < 100000 ; i++) {
			input.add(ThreadLocalRandom.current().nextInt(1, 99999 + 1));
		}

		Instant startTime = Instant.now();

		Sorting.insertionSort(input);

		Instant endTime = Instant.now();
		System.out.println("insertionSort " + (endTime.toEpochMilli() - startTime.toEpochMilli()));
	}

	@Test
	public void heapSortTest1() {

		List<Integer> input = new ArrayList<>(Arrays.asList(2,6,8,4,1));

		Sorting.heapsort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(2), input.get(1));
		Assert.assertEquals(Integer.valueOf(4), input.get(2));
		Assert.assertEquals(Integer.valueOf(6), input.get(3));
		Assert.assertEquals(Integer.valueOf(8), input.get(4));
	}

	@Test
	public void heapSortTest2() {

		List<Integer> input = new ArrayList<>(Arrays.asList(1,2,3,4,5));

		Sorting.heapsort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(2), input.get(1));
		Assert.assertEquals(Integer.valueOf(3), input.get(2));
		Assert.assertEquals(Integer.valueOf(4), input.get(3));
		Assert.assertEquals(Integer.valueOf(5), input.get(4));
	}

	@Test
	public void heapSortTest3() {

		List<Integer> input = new ArrayList<>(Arrays.asList(1,1,1,1,1));

		Sorting.heapsort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(1), input.get(1));
		Assert.assertEquals(Integer.valueOf(1), input.get(2));
		Assert.assertEquals(Integer.valueOf(1), input.get(3));
		Assert.assertEquals(Integer.valueOf(1), input.get(4));
	}

	@Test
	public void heapSortTestPerformance() {

		List<Integer> input = new ArrayList<>(100000);

		for(int i = 0 ; i < 100000 ; i++) {
			input.add(ThreadLocalRandom.current().nextInt(1, 99999 + 1));
		}

		Instant startTime = Instant.now();

		Sorting.heapsort(input);

		Instant endTime = Instant.now();
		System.out.println("heapSort " + (endTime.toEpochMilli() - startTime.toEpochMilli()));
	}


	@Test
	public void quickSortTest1() {

		List<Integer> input = new ArrayList<>(Arrays.asList(2,6,8,4,1));

		Sorting.quickSort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(2), input.get(1));
		Assert.assertEquals(Integer.valueOf(4), input.get(2));
		Assert.assertEquals(Integer.valueOf(6), input.get(3));
		Assert.assertEquals(Integer.valueOf(8), input.get(4));
	}

	@Test
	public void quickSortTest2() {

		List<Integer> input = new ArrayList<>(Arrays.asList(1,2,3,4,5));

		Sorting.quickSort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(2), input.get(1));
		Assert.assertEquals(Integer.valueOf(3), input.get(2));
		Assert.assertEquals(Integer.valueOf(4), input.get(3));
		Assert.assertEquals(Integer.valueOf(5), input.get(4));
	}

	@Test
	public void quickSortTest3() {

		List<Integer> input = new ArrayList<>(Arrays.asList(1,1,1,1,1));

		Sorting.quickSort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(1), input.get(1));
		Assert.assertEquals(Integer.valueOf(1), input.get(2));
		Assert.assertEquals(Integer.valueOf(1), input.get(3));
		Assert.assertEquals(Integer.valueOf(1), input.get(4));
	}

	@Test
	public void quickSortTestPerformance() {

		List<Integer> input = new ArrayList<>(100000);

		for(int i = 0 ; i < 100000 ; i++) {
			input.add(ThreadLocalRandom.current().nextInt(1, 99999 + 1));
		}

		Instant startTime = Instant.now();

		Sorting.quickSort(input);

		Instant endTime = Instant.now();
		System.out.println("quickSort " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

	}

	@Test
	public void mergeSortTest1() {

		List<Integer> input = new ArrayList<>(Arrays.asList(2,6,8,4,1));

		Sorting.mergeSort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(2), input.get(1));
		Assert.assertEquals(Integer.valueOf(4), input.get(2));
		Assert.assertEquals(Integer.valueOf(6), input.get(3));
		Assert.assertEquals(Integer.valueOf(8), input.get(4));
	}

	@Test
	public void mergeSortTest2() {

		List<Integer> input = new ArrayList<>(Arrays.asList(1,2,3,4,5));

		Sorting.mergeSort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(2), input.get(1));
		Assert.assertEquals(Integer.valueOf(3), input.get(2));
		Assert.assertEquals(Integer.valueOf(4), input.get(3));
		Assert.assertEquals(Integer.valueOf(5), input.get(4));
	}

	@Test
	public void mergeSortTest3() {

		List<Integer> input = new ArrayList<>(Arrays.asList(1,1,1,1,1));

		Sorting.mergeSort(input);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(1), input.get(1));
		Assert.assertEquals(Integer.valueOf(1), input.get(2));
		Assert.assertEquals(Integer.valueOf(1), input.get(3));
		Assert.assertEquals(Integer.valueOf(1), input.get(4));
	}

	@Test
	public void mergeSortTestPerformance() {

		List<Integer> input = new ArrayList<>(100000);

		for(int i = 0 ; i < 100000 ; i++) {
			input.add(ThreadLocalRandom.current().nextInt(1, 99999 + 1));
		}

		Instant startTime = Instant.now();

		Sorting.mergeSort(input);

		Instant endTime = Instant.now();
		System.out.println("mergeSort " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

	}

	@Test
	public void countingSortTest1() {

		List<Integer> input = new ArrayList<>(Arrays.asList(2,6,8,4,1));

		Sorting.countingSort(input, 10);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(2), input.get(1));
		Assert.assertEquals(Integer.valueOf(4), input.get(2));
		Assert.assertEquals(Integer.valueOf(6), input.get(3));
		Assert.assertEquals(Integer.valueOf(8), input.get(4));
	}

	@Test
	public void countingSort2() {

		List<Integer> input = new ArrayList<>(Arrays.asList(1,2,3,4,5));

		Sorting.countingSort(input, 10);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(2), input.get(1));
		Assert.assertEquals(Integer.valueOf(3), input.get(2));
		Assert.assertEquals(Integer.valueOf(4), input.get(3));
		Assert.assertEquals(Integer.valueOf(5), input.get(4));
	}

	@Test
	public void countingSortTest3() {

		List<Integer> input = new ArrayList<>(Arrays.asList(1,1,1,1,1));

		Sorting.countingSort(input, 2);

		Assert.assertEquals(5, input.size());

		Assert.assertEquals(Integer.valueOf(1), input.get(0));
		Assert.assertEquals(Integer.valueOf(1), input.get(1));
		Assert.assertEquals(Integer.valueOf(1), input.get(2));
		Assert.assertEquals(Integer.valueOf(1), input.get(3));
		Assert.assertEquals(Integer.valueOf(1), input.get(4));
	}

	@Test
	public void countingSortTestPerformance() {

		List<Integer> input = new ArrayList<>(100000);

		for(int i = 0 ; i < 100000 ; i++) {
			input.add(ThreadLocalRandom.current().nextInt(1, 99999 + 1));
		}

		Instant startTime = Instant.now();

		Sorting.countingSort(input, 1000001);

		Instant endTime = Instant.now();
		System.out.println("countingSort " + (endTime.toEpochMilli() - startTime.toEpochMilli()));

	}
}
