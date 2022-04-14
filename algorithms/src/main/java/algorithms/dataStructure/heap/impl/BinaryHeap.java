package algorithms.dataStructure.heap.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algorithms.dataStructure.heap.Heap;
import algorithms.dataStructure.heap.Heap.HeapType;


/**
 * Heap implementation using binary three structure. The data are store in an array in which father has an index < of children's index
 * 
 * @author Gek
 *
 * @param <T>
 */
public class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

	private List<T> heap;
	private HeapType type;
	/**
	 * Create an empty heap
	 */
	public BinaryHeap(HeapType type){
		heap = new ArrayList<T>();
		this.type = type;
	}
	
	public BinaryHeap(T[] array, HeapType type) {
		this(type);
		
	}
	
	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	@Override
	public void insert(T element) {
		
		heap.add(element);
		
		int index = heap.size() - 1;
		
		heapify_up(index);
	}

	@Override
	public T remove() {
		if(!isEmpty()) {
			T result = heap.get(0);
			heap.set(0, heap.get(heap.size() - 1));
			heap.remove(heap.get(heap.size() - 1));
			
			for(int i = heap.size()/2 ; i >= 0 ; i-- ) {
				heapify_down(i);
			}
			
			return result;
		}
		
		return null;
		
	}

	@Override
	public void merge(Heap<T> firstHeap) {
		
		if(this.type.equals(firstHeap.getType())) {
			while(firstHeap.size() > 0) {
				insert(firstHeap.remove());
			}
		} else {
			throw new IllegalArgumentException("Passed Heap is of the wrong type");
		}		
		
	}
	
	@Override
	public void heapify(T[] array) {
		
		//WARNING: Arrays.asList create an immutable list
		this.heap = new ArrayList<T>();
		heap.addAll(Arrays.asList(array));
		
		for(int i = heap.size()/2 ; i >= 0 ; i-- ) {
			heapify_down(i);
		}
	}
	
	@Override
	public void printHeap() {
		System.out.println(this.heap.toString());
	}
	
	@Override
	public HeapType getType() {
		return this.type;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//PRIVATE METHODS

	
	/**
	 * Returns father index using child index
	 * 
	 * @param childIndex
	 * @return father index in the heap
	 */
	private int parent(int childIndex) {
		
		if(!checkIndex(childIndex)) {
			throw new IndexOutOfBoundsException("Wrong childIndex index");
		}
		
		return (int) Math.floor(childIndex / 2);
	}
	
	/**
	 * Returns right child index using father index
	 * 
	 * @param fatherIndex
	 * @return left child index in the heap
	 */
	private int getRightChildIndex(int fatherIndex) {
		
		if(!checkIndex(fatherIndex)) {
			throw new IndexOutOfBoundsException("Wrong father index");
		}
		
		return (2 * fatherIndex) + 2;
	}
	
	/**
	 * Returns left child index using father index
	 * 
	 * @param fatherIndex
	 * @return left child index in the heap
	 */
	private int getLeftChildIndex(int fatherIndex) {
		
		if(!checkIndex(fatherIndex)) {
			throw new IndexOutOfBoundsException("Wrong father index");
		}
		
		return (2 * fatherIndex) + 1;
	}
	
	/**
	 * Check if the index value is in the array
	 * 
	 * @param index
	 * @return
	 */
	private boolean checkIndex(int index) {
		return (index >= 0 && index <= heap.size() - 1);
	}
	
	/**
	 * swap two value position
	 * 
	 * @param indexA index first value
	 * @param indexB index second value
	 */
	private void swap(int indexA, int indexB) {
		T local = heap.get(indexA);
		heap.set(indexA, heap.get(indexB));
		heap.set(indexB, local);
	}


	private void heapify_up(int index) {
		
		boolean checkValue = index > 0;
		int parentIndex = parent(index);
		
		//Check if the node at index and its parent violates the heap property
		switch(this.type) {
			case MAX:
				checkValue = checkValue && heap.get(parentIndex).compareTo(heap.get(index)) < 0;
			break;
			
			case MIN:
				checkValue = checkValue && heap.get(parentIndex).compareTo(heap.get(index)) > 0;
			break;
		}
		
		if(checkValue) {
			//Swap if the heap property is violated
			swap(index, parentIndex);
			//Recursive call
			heapify_up(parentIndex);
		}
		
	}
	
	private void heapify_down(int index) {
		
		if(this.size() < 2)
			return;
		
		int left = getLeftChildIndex(index);
		int right = getRightChildIndex(index);
		
		int newIndex = index;
		
		switch(this.type) {
		case MAX:
			if(left < size() && heap.get(left).compareTo(heap.get(index)) > 0) {
				newIndex = left;
			}
			
			if(right < size() && heap.get(right).compareTo(heap.get(newIndex)) > 0) {
				newIndex = right;
			}
		break;
		
		case MIN:
			if(left < size() && heap.get(left).compareTo(heap.get(index)) < 0) {
				newIndex = left;
			}
			
			if(right < size() && heap.get(right).compareTo(heap.get(newIndex)) < 0) {
				newIndex = right;
			}
		break;
	}
		
		if(newIndex != index) {
			swap(index, newIndex);
			
			heapify_down(newIndex);
		}
				
	}
	
}
