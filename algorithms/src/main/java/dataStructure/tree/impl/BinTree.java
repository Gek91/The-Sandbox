package dataStructure.tree.impl;

import java.util.ArrayList;
import java.util.List;

import dataStructure.tree.Tree;
import dataStructure.tree.TreeElem;



/**
 * 
 * @author Giacomo Pandini
 *
 * @param <T>
 */
public class BinTree<T extends Comparable<T>> extends Tree<T>{

	public BinTree(BinTreeElem<T> root){
		this.setRoot(root);
	}
	
	@Override
	public BinTreeElem<T> getRoot(){
		return (BinTreeElem<T>) super.getRoot();
	}
	
	/////////////////////////////////////////////////
	@Override
	public void printTree(){
		inorderThreeWalk(getRoot());
	}
	
	/**
	 * Simmetric visit of the tree for prints the node. Recursive
	 * 
	 * @param node
	 */
	private void inorderThreeWalk(BinTreeElem<T> node){
		if(node != null){
			inorderThreeWalk(node.getLeft()); //prints all the node of the left subtree
			System.out.println("val: " + node.getValue().toString() +
					" left: " + (node.getLeft() != null ? node.getLeft().getValue().toString() : null) +
					" right: " + (node.getRight() != null ? node.getRight().getValue().toString() : null) ); //prits the value of the node
			inorderThreeWalk(node.getRight()); // prints all the node of the right subtree
		}
	}
	
	//////////////////////////////////////////////
	@Override
	public boolean contains(T value){
		if(ricercaVal(value) != null)
			return true;
		return false;
	}
	
	@Override
	public BinTreeElem<T> ricercaVal(T value){
		if(value == null){
			return null;
		}
		return threeSearch(getRoot(),value);
	}
	
	/**
	 * Recursive search of the value in the tree with node root
	 * 
	 * @param node, root of the tree
	 * @param value
	 * @return the node, null elsewhere
	 */
	private BinTreeElem<T> threeSearch(BinTreeElem<T> node, T value){
		if(node != null){
			T val = node.getValue();
			if(value.equals(val))
				return node;
			if(value.compareTo(val)<0)
				return threeSearch(node.getLeft(),value);
			else 
				return threeSearch(node.getRight(),value);
		} else
			return null;
		
	}
	
	///////////////////////////////////////////////////////////////
	@Override
	public T getMinValue(){
		if(getRoot() != null){
			return threeMinimum(getRoot()).getValue();
		}
		return null;
	}
	
	/**
	 * Calculates the minimum node in the subtree of the node
	 * 
	 * @param node, root of the subtree
	 * @return the minimum node of the subtree
	 */
	private BinTreeElem<T> threeMinimum(BinTreeElem<T> node){
		BinTreeElem<T> leftChild = node;
		while(leftChild.getLeft() != null){
			leftChild = leftChild.getLeft();
		}
		return leftChild;
	}
	
	///////////////////////////////////////////////////////////////
	
	@Override
	public T getMaxValue(){
		if(getRoot() != null){
			return threeMaximum(getRoot()).getValue();
		}
		return null;
	}
	
	/**
	 * Calculates the maximum node of the subtree
	 * 
	 * @param node, root of the subtree
	 * @return
	 */
	private BinTreeElem<T> threeMaximum(BinTreeElem<T> node){
		BinTreeElem<T> rightChild = node;
		while(rightChild.getRight() != null){
			rightChild = rightChild.getRight();
		}
		return rightChild;
	}
	
	//////////////////////////////////////////////////////////////
	/**
	 * Returns the successor of the input value
	 * @param val
	 * @return the node of the successor value
	 * @throws Exception
	 */
	protected BinTreeElem<T> successorVal(T val) throws Exception{
		BinTreeElem<T> node = ricercaVal(val);
		if(node == null){
			throw new Exception("The value is not present in the tree");
		}
		return threeSuccessor(node); //Recursive call
	}
	
	/**
	 * Recursive search of the successor
	 * 
	 * @param node
	 * @return the node of the successor value
	 */
	private BinTreeElem<T> threeSuccessor(BinTreeElem<T> node){
		//If the node has a right subtree, the successor will be there
		if(node.getRight() != null)
			return threeMinimum(node.getRight());
		//Elsewhere, searches the first node ancestor of node that is not right child of its father. The father will be the successor of node
		BinTreeElem<T> padre = node.getFather();
		while( padre != null && node.equals(padre.getRight()) ){
			node = padre;
			padre = padre.getFather();
		}
		return padre;
	}
	
	//////////////////////////////////////////////////////////////

	protected BinTreeElem<T> predecessoreVal(T val) throws Exception {
		BinTreeElem<T> node = ricercaVal(val);
		if(node == null){
			throw new Exception("The value is not present in the tree");
		}
		return threePredecessor(node);
	}
	
	private BinTreeElem<T> threePredecessor(BinTreeElem<T> node){
		//If the node has a left subtree, the successor will be there
		if(node.getLeft() != null)
			return threeMaximum(node.getLeft());
		//Elsewhere, searches the first node ancestor of node that is not left child of its father. The father will be the successor
		BinTreeElem<T> padre = node.getFather();
		while( padre != null && node.equals(padre.getLeft()) ){
			node = padre;
			padre = padre.getFather();
		}
		return padre;
	}
	
	//////////////////////////////////////////////////////////////
	@Override
	public void insertElem(T newValue) throws Exception{
		if(newValue == null)
			throw new Exception("The input element is null");
		
		BinTreeElem<T> binNode = new BinTreeElem<T>(newValue);
		BinTreeElem<T> x = getRoot();
		BinTreeElem<T> y = null;
		//Search the first void node in the tree
		while( x != null ){
			y = x ;
			//If the value of binNode is less than the value of node x, looks in the left subtree
			if(binNode.getValue().compareTo(x.getValue())<0)
				x = x.getLeft();
			else //elsewhere
				x = x.getRight();
		}
		//Insert the value of the node father in the founded position
		binNode.setFather(y);
		//If the node has no father, it become the new root
		if(y == null) 
			setRoot(binNode);
		else //elsewhere, inserts the node as right or left child of his father. It's depends on its value
			if(binNode.getValue().compareTo(y.getValue())<0)
				y.setLeft(binNode);
			else
				y.setRight(binNode);
		incNodesNumber();
	}
	
	///////////////////////////////////////////////////////////////////////////
	@Override
	public void deleteElem(T value) throws Exception{
		BinTreeElem<T> node = ricercaVal(value);
		if(node != null){
			BinTreeElem<T> x;
			BinTreeElem<T> y;
			//If node has a child
			if(node.getLeft() == null &&  node.getRight() == null)
				y = node;
			else //elsewhere
				y = threeSuccessor(node);
			//If the y has child, the value of x is one of them
			if(y.getLeft() != null)
				x = y.getLeft();
			else //Elsewhere null
				x = y.getRight();
			//Extracts y from the tree
			if(x != null)
				x.setFather(y.getFather());
			//y is root
			if(y.getFather() == null)
				setRoot(x);
			else //elsewhere
				if( y.equals( y.getFather().getLeft() ) ) //if left child
					y.getFather().setLeft(x);
				else
					y.getFather().setRight(x);
			//If y is a successor of node, reinserts its value in the tree in place of node
			if(!y.equals(node)){
				node.setValue(y.getValue());
			}
		} else {
			throw new Exception("The value is not contained in the tree");
		}
		decNodesNumber();
	}
	
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * 
 * @author Giacomo Pandini
 *
 * @param <T>
 */

class BinTreeElem<T extends Comparable<T>> extends TreeElem<T>{
	//Pointer to father, child left and child right
	private BinTreeElem<T> father;
	private BinTreeElem<T> left;
	private BinTreeElem<T> right;
	
	//Constructors
	public BinTreeElem(T value){
		this.setValue(value);		
	}
	
	public BinTreeElem(T value, BinTreeElem<T> padre, BinTreeElem<T> left, BinTreeElem<T> right){
		this.setValue(value);
		this.father = padre;
		this.left = left;
		this. right = right;
	}
	
	@Override
	public boolean isLeaf(){
		if(left == null && right == null)
			return true;
		return false;
	}
	
	@Override
	protected BinTreeElem<T> getFather() {
		return this.father;
	}

	@Override
	protected List<TreeElem<T>> getChildren() {
		List<TreeElem<T>> returnList = new ArrayList<TreeElem<T>>(2);
		returnList.add(left);
		returnList.add(right);
		return returnList;
	}
	
	//Setter e Getter

	public void setFather(BinTreeElem<T> padre) {
		this.father = padre;
	}
	public BinTreeElem<T> getLeft() {
		return left;
	}
	public void setLeft(BinTreeElem<T> left) {
		this.left = left;
	}
	public BinTreeElem<T> getRight() {
		return right;
	}
	public void setRight(BinTreeElem<T> right) {
		this.right = right;
	}	
	
}
