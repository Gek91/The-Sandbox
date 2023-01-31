package algorithms.sorting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import dataStructure.heap.Heap;
import dataStructure.heap.Heap.HeapType;
import dataStructure.heap.impl.BinaryHeap;



/**
 * Sorting algorithms
 * 
 * @author Gek
 *
 */
public class Sorting {
	
	/**
	 * Insertion Sort 
	 * 
	 * Sort the array taking one element at time. All the element before the j index are sorted between them.
	 * Put the j index element in the right place between the sortered element shifting all the bigger values. 
	 * 
	 * Optimal case (already ordered array) O(n)
	 * Worst case O(n^2)
	 * 
	 * @param input : List to sort
	 */
	public static <T extends Comparable<T>> void insertionSort(List<T> input){
		
		for (int j = 1 ; j < input.size() ; j++) {
			
			T sortingElement = input.get(j);  //Element to sort
		
			//sorted part of the array <j 
			int i = j - 1;
			while(i >= 0 && input.get(i).compareTo(sortingElement) >= 0 ) {

				input.set(i + 1, input.get(i)); //Shift all bigger element backward in the array
				i = i - 1;
			}

			input.set(i + 1, sortingElement); //Put the element in the right place in the sorted part of the array
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static <T extends Comparable<T>> void heapsort(List<T> input) {
		
		Heap<T> heap = new BinaryHeap<T>(HeapType.MIN); // managed using an heap
		heap.heapify(input);
		int i = 0;
		while(heap.size() > 0) { //empty the heap
			input.set(i, heap.remove());
			i++;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	/**
	 * QuickSort
	 * 
	 * Best case O(n log n)
	 * Worst case O(n^2)
	 * 
	 * Recursive sorts part of the array splitting the array in two array and sorting them by swapping smaller and bigger value of a middle element
	 * 
	 * @param input: Array to sort
	 */
	public static <T extends Comparable<T>> void quickSort(List<T> input) {
		quicksort(input, 0, input.size() - 1);
	}
	
	/**
	 * 
	 * @param input : list to sort
	 * @param startIndex : index where the the sort starts
	 * @param range : length of the part of the array to sort
	 */
	private static <T extends Comparable<T>> void quicksort(List<T> input, int startIndex, int range){
		if (startIndex < range) {	
			//Find the index of the A[startIndex] element 
			int findEndIndex = Partition(input, startIndex, range);
			//Sort all the elements < A[startIndex]
			quicksort(input, startIndex, findEndIndex - 1);
			//Sort all the elements > A[startIndex]
			quicksort(input, findEndIndex + 1, range);
		}
	}
	
	
	/**
	 * Try to sort the elements of a partition of the array putting the smaller element of A[startIndex] before startIndex and the bigger after by swapping them. 
	 * 
	 * @param input : list to sort
	 * @param startIndex : index where the the sort starts
	 * @param range : index where the sort ends
	 * @return index of the of the element with the middle value in the array
	 */
	private static <T extends Comparable<T>> int Partition(List<T> input, int startIndex, int range) {
		
		T pivot = input.get(range);
		
		int i = startIndex - 1;
		for(int j = startIndex ; j < range  ; j++) {
			
			if(input.get(j).compareTo(pivot) < 0) {
				i++;
				swap(input, i, j);
			}
		}
		
		if(input.get(range).compareTo(input.get(i+1)) < 0) {
			swap(input, i+1, range);
		}
		
		return i+1;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * MergeSort
	 * 
	 * @param input: list to sort
	 */
	public static <T extends Comparable<T>> void mergeSort(List<T> input) {
		mergeSort(input, 0, input.size() - 1);
	}
	
	// funzione di supplemente al mergesort, unisce le due parti ordinate ottenute in un unico array ordinato
	/**
	 * Merge the two ordered part in a new ordered array part
	 * 
	 * @param input : List to sort
	 * @param left : left index
	 * @param center : center index
	 * @param right : right index
	 */
	private static <T extends Comparable<T>> void merge(List<T> input, int left, int center, int right){
		List<T> helperList = new ArrayList<>(right - left + 1);

		int i = left;
		int j = center + 1;
		int k = 0;
		
		//merge the two half ordering the elements
		while( (i <= center) && (j <= right) ){ 
			
			if(input.get(i).compareTo(input.get(j)) <= 0) {
				helperList.set(k, input.get(i));
				i++;
			} else {
				helperList.set(k, input.get(j));
				j++;
			}
			
			k++;
		}
		
		//Insert the remaining elements of the first half in the array
		while (i <= center){
			helperList.set(k, input.get(i));
			i++;
			k++;
		}
		
		//Insert the remaining elements of the second half in the array
		while(j <= right) {
			helperList.set(k, input.get(j));
			j++;
			k++;
		}
		
		//Copy the helper vector in the main vector
		for ( k = left ; k <= right ; k++){ 
			input.set(k, helperList.get(k-left));
		}
	}
	
	/**
	 * Recursive Algorithm, splits the array 
	 * sort the splitted part calling the merge function
	 * 
	 * Best case O(n)
	 * Worst case O(n log n)
	 * 
	 * @param input: list to sort
	 * @param left: left index
	 * @param right: right index
	 */
	private static <T extends Comparable<T>> void mergeSort(List<T> input, int left, int right) {
		if (left < right) {
			//Middle index of the vector
			int center = (left + right)/2;
			//Recursive call on the first part
			mergeSort(input, left, center);
			//Recursive call on the second part
			mergeSort(input, center + 1, right);
			//Merge function
			merge(input, left, center, right);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * CountingSort
	 * 
	 * Assumption : all the value in input are < upperBoundValue
	 * 
	 * Worst and Best case O(n)
	 * 
	 * @param input: list to sort
	 * @param upperBoundValue : value bigger than all the value in the array
	 */
	public static void countingSort(List<Integer> input, int upperBoundValue) {
		
		int inputSize = input.size();
		List<Integer> resultList = new ArrayList<>(inputSize);
		int[] C = new int[upperBoundValue]; // C[i] = number of elements < i
		
		//init C
		for(int i = 0 ; i < upperBoundValue ; i++) {
			C[i] = 0; 
		}
		
		//save the count of the elements whit value A[j] for every j < arrayLengh in C[A[j]]
		for(int j = 0 ; j < inputSize ; j++) {
			C[input.get(j)] = C[input.get(j)] + 1;
		}
		
		//Save in C[i] all the elements < i
		for(int i = 1; i < upperBoundValue ; i++) {
			C[i] = C[i] + C[i-1]; 
		}
		
		//Create result array
		for (int j = inputSize - 1; j >= 0 ; j--) {
			resultList.set( C[ input.get(j) ] -1, input.get(j));
			C[ input.get(j) ] = C[ input.get(j) ] - 1;
		}
		
		//copies the result in A
		for (int i = 0; i < inputSize ; i++){
			input.set(i, resultList.get(i));
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Common Function
	
	/**
	 * Switch the values of two variables
	 * 
	 * @param firstVariable : first variable
	 * @params secondVariable : second variable
	 */
	private static <T> void swap(List<T> input, int firstVariable, int secondVariable) {
		
		T helperVariable = input.get(firstVariable);
		input.set(firstVariable, input.get(secondVariable));
		input.set(secondVariable, helperVariable);
	}
}
