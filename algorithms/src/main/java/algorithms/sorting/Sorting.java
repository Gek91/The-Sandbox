package algorithms.sorting;

import java.lang.reflect.Array;

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
	 * @param A : Array to sort
	 */
	public static <T extends Comparable<T>> void insertionSort(T A[]){
		
		for (int j = 1 ; j < A.length ; j++) {
			
			T sortingElement = A[j];  //Element to sort
		
			//sorted part of the array <j 
			int i = j - 1;
			while(i >= 0 && A[i].compareTo(sortingElement) >= 0 ) {
				
				A[i+1] = A[i]; //Shift all bigger element backward in the array 
				i = i - 1;
			}
			
			A[i+1] = sortingElement; //Put the element in the right place in the sorted part of the array
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//costruisce un heap con buildHeap, poi riordina l'heap invertendo l'ultima posizione con la prima dell'array e decrementando la lunghezza dell'array di uno. 
	//Ad ogni passo esegue heapify in modo da ottenere il valore più alto tra quelli rimasti in A[1] in modo che dopo venga posizionato in fodo all'array preso in considerazione (decrementato)
	public static <T extends Comparable<T>> void heapsort(T A[]) {
		
		Heap<T> H = new BinaryHeap<T>(HeapType.MIN); // utilizza un oggetto Heap per gestire il tutto
		H.heapify(A);
		int i = 0;
		while(H.size() > 0) { //ripete l'esecuzione dell'algoritmo
			A[i] = H.remove();
			
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
	 * @param A: Array to sort
	 */
	public static <T extends Comparable<T>> void quickSort(T A[]) {
		quicksort(A, 0, A.length - 1);
	}
	
	/**
	 * 
	 * @param A : Array to sort
	 * @param startIndex : index where the the sort starts
	 * @param range : length of the part of the array to sort
	 */
	private static <T extends Comparable<T>> void quicksort(T A[],int startIndex, int range){
		if (startIndex < range) {	
			//Find the index of the A[startIndex] element 
			int findendedIndex = Partition(A, startIndex, range); 
			//Sort all the elements < A[startIndex]
			quicksort(A, startIndex, findendedIndex - 1); 
			//Sort all the elements > A[startIndex]
			quicksort(A, findendedIndex + 1, range); 
		}
	}
	
	
	/**
	 * Try to sort the elements of a partition of the array putting the smaller element of A[startIndex] before startIndex and the bigger after by swapping them. 
	 * 
	 * @param A : Array to sort
	 * @param startIndex : index where the the sort starts
	 * @param range : index where the sort ends
	 * @return index of the of the element with the middle value in the array
	 */
	private static <T extends Comparable<T>> int Partition(T A[], int startIndex, int range) {
		
		T pivot = A[range];
		
		int i = startIndex - 1;
		for(int j = startIndex ; j < range  ; j++) {
			
			if(A[j].compareTo(pivot) < 0) {
				i++;
				swap(A, i, j);
			}
		}
		
		if(A[range].compareTo(A[i+1]) < 0) {
			swap(A, i+1, range);
		}
		
		return i+1;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * MergeSort
	 * 
	 * @param A: Array to sort
	 */
	public static <T extends Comparable<T>> void mergeSort(T A[]) {
		mergeSort(A, 0, A.length - 1);
	}
	
	// funzione di supplemente al mergesort, unisce le due parti ordinate ottenute in un unico array ordinato
	/**
	 * Merge the two ordered part in a new ordered array part
	 * 
	 * @param A : Array to sort
	 * @param left : left index
	 * @param center : center index
	 * @param right : right index
	 */
	private static <T extends Comparable<T>> void merge(T A[], int left, int center, int right){ 
		@SuppressWarnings("unchecked")
		T[] B = (T[]) Array.newInstance(A[0].getClass(), right - left + 1);
		
		int i = left;
		int j = center + 1;
		int k = 0;
		
		//merge the two half ordering the elements
		while( (i <= center) && (j <= right) ){ 
			
			if(A[i].compareTo(A[j]) <= 0) {
				
				B[k] = A[i];
				i++;
				
			} else {	
				
				B[k]=A[j];
				j++;
			}
			
			k++;
		}
		
		//Insert the remaining elements of the first half in the array
		while (i <= center){
			
			B[k] = A[i];
			i++;
			k++;
		}
		
		//Insert the remaining elements of the second half in the array
		while(j <= right) {
			
			B[k] = A[j];
			j++;
			k++;
		}
		
		//Copy the helper vector in the main vector
		for ( k = left ; k <= right ; k++){ 
			A[k] = B[k-left];
		}
	}
	
	
	/**
	 * Recursive Algorithm, splits the array 
	 * sort the splitted part calling the merge function
	 * 
	 * Best case O(n)
	 * Worst case O(n log n)
	 * 
	 * @param A: Array to sort
	 * @param left: left index
	 * @param right: right index
	 */
	private static <T extends Comparable<T>> void mergeSort(T A[], int left, int right) { //l indice sinistro, r indice destro
		if (left < right) {
			//Middle index of the vector
			int center = (left + right)/2;
			//Recursive call on the first part
			mergeSort(A, left, center); 
			//Recursive call on the second part
			mergeSort(A, center + 1, right);
			//Merge function
			merge(A, left, center, right); 
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * CountingSort
	 * 
	 * Assumption : all the value in A are < upperBoundValue
	 * 
	 * Worst and Best case O(n)
	 * 
	 * @param A: Array to sort
	 * @param upperBoundValue : value bigger than all the value in the array
	 */
	public static <T extends Comparable<T>> void countingSort(Integer A[], int upperBoundValue) { //k valore maggiorante degli elementi dell'array
		
		int arrayLength = A.length;
		int[] B = new int[arrayLength]; //result Array
		int[] C = new int[upperBoundValue]; // C[i] = number of elements < i
		
		//init C
		for(int i = 0 ; i < upperBoundValue ; i++) {
			C[i] = 0; 
		}
		
		//save the count of the elements whit value A[j] for every j < arrayLengh in C[A[j]]
		for(int j = 0 ; j < arrayLength ; j++) {
			C[A[j]] = C[A[j]] + 1; 
		}
		
		//Save in C[i] all the elements < i
		for(int i = 1; i < upperBoundValue ; i++) {
			C[i] = C[i] + C[i-1]; 
		}
		
		//Create result array
		for (int j = arrayLength - 1; j >= 0 ; j--) {
			B[ C[ A[j] ] -1 ] = A[j];  
			C[ A[j] ] = C[ A[j] ] - 1;
		}
		
		//copies the result in A
		for (int i = 0; i < arrayLength ; i++){ 
			A[i]=B[i];
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
	private static <T> void swap(T A[], int firstVariable, int secondVariable) {
		
		T helperVariable = A[firstVariable];
		A[firstVariable] = A[secondVariable];
		A[secondVariable] = helperVariable;
	}
}
