package algorithms.dataStructure.heap.impl;

import java.util.ArrayList;
import java.util.List;

import algorithms.dataStructure.heap.Heap;
import algorithms.dataStructure.heap.Heap.HeapType;


/**
 * Fibonacci Heap structure. List of heap
 * 
 * @author Giacomo Pandini
 *
 * @param <T>
 */
public class FibonacciHeap<T extends Comparable<T>> implements Heap<T>{
	
	private FibonacciHeapElem<T> root; //key minimum element of the structure
	private int size; //Number of element in the structure
	private HeapType type;
	
	//Constructor
	public FibonacciHeap(HeapType type){
		this.root = null;
		this.size = 0;
		this.type = type;
	}
	
	public FibonacciHeapElem<T> getRoot() {
		return root; 
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Insert an element in the root list of the heap
	 * 
	 * @param x
	 */
	@Override
	public void insert(T value){
		FibonacciHeapElem<T> x = new FibonacciHeapElem<T>(value);

		if(getRoot() == null){
			//First insert
			this.root = x;
			x.setLeft(x);
			x.setRight(x);
		}
		else {
			//Insert the element in the fibonacci heap
			FibonacciHeapElem<T> elem = getRoot();
			FibonacciHeapElem<T> min = elem;
			
			while(elem.getRight() != min) {
				elem = elem.getRight();
			}
			
			//Inserts the element in the tree
			elem.setRight(x);
			min.setLeft(x);
			x.setRight(min);
			x.setLeft(elem);
			
			if(type.equals(HeapType.MIN)) {
				if(x.getValue().compareTo(getRoot().getValue()) < 0 )
					this.root = x;
			} else if(type.equals(HeapType.MAX)) {
				if(x.getValue().compareTo(getRoot().getValue()) > 0 )
					this.root = x;
			}

		}
		
		this.size++;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Extract from the fibonacci heap the min value
	 */
	@Override
	public T remove(){
		
		FibonacciHeapElem<T> z = getRoot();
		
		if(z != null){
			
			if(z.getDegree() != 0){
				//Add z child to root list of the heap and remove z. Directly adds the linked list to the root list updating the pointers
				FibonacciHeapElem<T> firstChild = z.getChild();
				FibonacciHeapElem<T> lastChild = firstChild.getLeft();
				
				z.getLeft().setRight(firstChild);
				firstChild.setLeft(z.getLeft());
				lastChild.setRight(z.getRight());
				z.getRight().setLeft(lastChild);
				
				//Update p point of the children to null
				for(int i = 0 ; i < z.getDegree(); i++){
					firstChild.setP(null);
					firstChild = firstChild.getLeft();
				}
			}
			
			if(z == z.getRight() && z.getDegree() == 0) //one element in the heap
				this.root = null;
			else {
				this.root = z.getRight();//updates min value with the first child of z
				consolidate(); //reduces the number of three in the heap
			}
				
			this.size--;
		}
		return z.getValue();
	}
	
	/**
	 * makes every element in the root list have a distinct degree
	 */
	private void consolidate(){
		List<FibonacciHeapElem<T>> array = new ArrayList<FibonacciHeapElem<T>>(this.size); //Already processed element
		while(array.size() < this.size) array.add(null);
		FibonacciHeapElem<T> elem = getRoot();
		
		do { //Process every elem in the root list
			FibonacciHeapElem<T> x = elem;
			int deg = elem.getDegree();
			
			while(array.get(deg) != null){ //Search element with the same degree, make y child of x (or vice versa)
				
				FibonacciHeapElem<T> y = array.get(deg); //another element with the same degree
				
				if(type.equals(HeapType.MIN)) {
					if(x.getValue().compareTo(y.getValue()) > 0){
						FibonacciHeapElem<T> help = x;
						x = y;
						y = help;	
					}
				} else if(type.equals(HeapType.MAX)) {
					if(x.getValue().compareTo(y.getValue()) < 0){
						FibonacciHeapElem<T> help = x;
						x = y;
						y = help;	
					}
				}
				
				link(x,y); //Make y child of x
				
				array.set(deg, null);
				deg++;
			}
			
			array.set(deg, x);
			elem = elem.getRight();
			
		} while(elem != getRoot());
		
		this.root = null;
		
		//Rebuild the list
		for(FibonacciHeapElem<T> arrayEl : array){
			
			if(arrayEl != null){
				
				if(getRoot() == null) {//create a root list with only arrayEl
					this.root = arrayEl;
				}
				else{ //Insert arrayEl in the root list
					
					getRoot().getLeft().setRight(arrayEl);
					arrayEl.setLeft(getRoot().getLeft());
					getRoot().setLeft(arrayEl);
					arrayEl.setRight(getRoot());
					
					if(type.equals(HeapType.MIN)) {
						if(arrayEl.getValue().compareTo(getRoot().getValue()) < 0) {
							this.root = arrayEl;
						}
					} else if(type.equals(HeapType.MAX)) {
						if(arrayEl.getValue().compareTo(getRoot().getValue()) > 0) {
							this.root = arrayEl;
						}
					}
					
					
				}
			}
			
		}
	}
	
	private void link(FibonacciHeapElem<T> x, FibonacciHeapElem<T> y){
		//Remove y from the root list
		y.getLeft().setRight(y.getRight());
		y.getRight().setLeft(y.getLeft());
		
		//make y child of x
		FibonacciHeapElem<T> child = x.getChild();
		if(child != null) {
			child.getLeft().setRight(y);
			y.setLeft(y.getLeft());
			child.setLeft(y);
			y.setRight(child);
		} else {
			child = y;
		}

		
		//increment degree and make y mark false
		x.setDegree(x.getDegree() + y.getDegree() + 1);
		y.setMark(false);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Decrease the value of of the node x to k applying some cut to make the three right in its form
	 * @param x
	 * @param k
	 */
//	public void decreaseKey(FibonacciHeapElem<T> x, int k)throws Exception{
//		if(k > x.getKey())
//			throw new Exception("The k value is grather than key of x");
//		x.setKey(k);
//		FibonacciHeapElem<T> y = x.getP();
//		if(y != null && x.getKey() < y.getKey()){
//			cut(x,y);
//			cascadingCut(y);
//		}
//		if(x.getKey() < getMin().getKey())
//			setMin(x);
//	}
	
//	/**
//	 * Cuts the link between x and its parent y making x a root
//	 * @param x
//	 * @param y
//	 */
//	private void cut(FibonacciHeapElem<T> x, FibonacciHeapElem<T> y){
//		FibonacciHeapElem<T> child = y.getChild();;
//		//Remove x from y children
//		while(child != x)
//			child.getRight();
//		child.getLeft().setRight(child.getRight());
//		child.getRight().setLeft(child.getLeft());
//		y.setDegree(y.getDegree()-1);
//		//Add x to root list
//		getRoot().getLeft().setRight(x);
//		x.setLeft(getRoot().getLeft());
//		getRoot().setLeft(x);
//		x.setLeft(getRoot());
//		x.setP(null);
//		x.setMark(false);
//	}
//	
//	/**
//	 * Cascade cut operation
//	 * @param y
//	 */
//	private void cascadingCut(FibonacciHeapElem<T> y){
//		FibonacciHeapElem<T> z = y.getP();
//		if(z != null){
//			if(!y.isMark())
//				y.setMark(true);
//			else{
//				cut(y,z);
//				cascadingCut(z);
//			}
//		}
//	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Remove the x elem from the fibonacci heap
	 * @param x
	 * @throws Exception
	 */
//	public void delete(FibonacciHeapElem<T> x) throws Exception{
//		decreaseKey(x, -1);
//		extractMin();
//	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Unite two fibonacci heap h1 and h2 in a new fibonacci heap h
	 * @param h1
	 * @param h2
	 * @return
	 */
	@Override
	public void merge(Heap<T> secondHeap){
		
		if(this.type.equals(secondHeap.getType())) {
		
			FibonacciHeap<T> heap = (FibonacciHeap<T>) secondHeap;
			
			if(this.root != null){
				
				if(heap.getRoot() != null){
					//Concatenate h2 in h
					FibonacciHeapElem<T> hMin = this.root;
					FibonacciHeapElem<T> secondHeapMin = heap.getRoot();
					
					hMin.getLeft().setRight(secondHeapMin);
					secondHeapMin.getLeft().setRight(hMin);
					
					FibonacciHeapElem<T> elem = hMin.getLeft();
					hMin.setLeft(secondHeapMin.getLeft());
					secondHeapMin.setLeft(elem);
					
					//Update min in h
					if(type.equals(HeapType.MIN)) {
						if(heap.getRoot().getValue().compareTo(this.root.getValue()) < 0 )
							this.root = heap.getRoot();
					} else if(type.equals(HeapType.MAX)) {
						if(heap.getRoot().getValue().compareTo(this.root.getValue()) > 0 )
							this.root = heap.getRoot();
					}
					
					
					
					//update the number of nodes
					this.size = this.size + heap.size();
				}
				
			} else if(heap.getRoot() != null){
				//insert h2
				this.root = heap.getRoot();
				this.size = heap.size();
			}
			
		} else {
			throw new IllegalArgumentException("Passed Heap is of the wrong type");
		}
	}
	

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}


	@Override
	public void heapify(T[] array) {
		
		this.root = null;
		
		for(T elem : array) {
			insert(elem);
		}
	}



	@Override
	public void printHeap() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public HeapType getType() {
		return this.type;
	}

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/**
 * Element of the fibonacci Heap
 * 
 * @author Giacomo Pandini
 *
 * @param <T>
 */
class FibonacciHeapElem<T> {
	private T value; //Value of the element
	private FibonacciHeapElem<T> p; //parent element
	private FibonacciHeapElem<T> child; //First child
	private FibonacciHeapElem<T> left; //Left sibling
	private FibonacciHeapElem<T> right; //Right sibling
	private int degree; //number of sibling of the element
	private boolean mark;
	
	//Constructor
	public FibonacciHeapElem(T value){
		this.value = value;
		this.p = null;
		this.child = null;
		this.right = null;
		this.left = null;
		this.degree = 0;
		this.mark = false;
	}
	
	public T getValue(){ return this.value; }
	public void setValue(T value){ this.value = value; }
		
	public FibonacciHeapElem<T> getP() { return p; }
	public void setP(FibonacciHeapElem<T> p) {	this.p = p; }
	
	public FibonacciHeapElem<T> getChild() { return child;	}
	public void setChild(FibonacciHeapElem<T> child) { this.child = child;	}
	
	public FibonacciHeapElem<T> getLeft() { return left;}
	public void setLeft(FibonacciHeapElem<T> left) { this.left = left;	}
	
	public FibonacciHeapElem<T> getRight() { return right;	}
	public void setRight(FibonacciHeapElem<T> right) {	this.right = right;	}
	
	protected int getDegree() { return degree; }
	protected void setDegree(int degree) { this.degree = degree; }
	
	public boolean isMark() { return mark; }
	public void setMark(boolean mark) { this.mark = mark; }
	
}