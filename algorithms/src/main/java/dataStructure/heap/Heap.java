package dataStructure.heap;

import java.util.List;

/**
 * Heap data structure. Heap is a three base data structure that satisfies a particular property:
 *  - If the node A is father of the node B -> Key of A and B is ordered (max/min heap) 
 * 
 * @author Gek
 *
 */
public interface Heap<T extends Comparable<T>> {

	/**
	 * Returns the number of the element in the heap
	 * 
	 *  @return number of the element
	 */
	public int size();
	
	/**
	 * Checks if the heap si empty
	 * 
	 * @return true if the heap is empty, else otherwise
	 */
	public boolean isEmpty();
		
	/**
	 * Inserts a new element
	 *
	 * @return : key of the new element
	 */
	public void insert(T element);
	
	/**
	 * Remove the Min/Max element in the heap
	 * 
	 * @param element
	 */
	public T remove();
	
	/**
	 * Merge another heap in the current heap
	 * 
	 * @param secondHeap
	 * @return
	 */
	public void merge(Heap<T> secondHeap);
	
	/**
	 * Create the heap from the array
	 * 
	 * @param input
	 * @return
	 */
	public void heapify(List<T> input);
	
	public void printHeap();
	
	public enum HeapType { MAX, MIN };
	
	public HeapType getType();
}
