package algorithms.dataStructure.tree.impl;

	import java.util.ArrayList;
import java.util.List;

import algorithms.dataStructure.tree.Tree;
import algorithms.dataStructure.tree.TreeElem;



	/**
	 * Red Black tree implementation
	 * 
	 * @author Giacomo Pandini
	 *
	 * @param <T>
	 */
	public class RBTree<T extends Comparable<T>> extends Tree<T> {
	    private RBTreeElem<T> root;
	    

	    public RBTree(){
	    	root = null;
	    }
	   
	    @Override
	    public RBTreeElem<T> getRoot() {
	    	return root;
	    } 

	    @Override
		public void printTree() { 
		    inorderThreeWalk(root); //Recursive call
		}
	    
	    /**
		 * Simmetric visit of the tree for prints the node. Recursive
		 * 
		 * @param node
		 */
		private void inorderThreeWalk( RBTreeElem<T> x){ 
		    if(x != null) {
		        inorderThreeWalk(x.getleft()); //Prints the elements of the left subtree
		        System.out.println(x.getValue()); //prints the element
		        inorderThreeWalk(x.getright()); //Prints the elements of the right subtree 
		    }
		}

	///////////////////////////////////////////////////////////////////////////////////////

		@Override
		public boolean contains(T value) {
			if(ricercaVal(value)!=null)
				return true;
			return false;
		}
		
		@Override
		public RBTreeElem<T> ricercaVal(T value) {
			return threeSearch(root,value); //Call threeSearch on the root
		}

		/**
		 *  Returns the node with the passed value y in the tree x, null elsewhere
		 * 
		 * @param x, root of the tree
		 * @param k, value searched in the tree
		 * @return the node with the input value, null elsewhere
		 */
		private RBTreeElem<T> threeSearch( RBTreeElem<T> x,T k){
		    T val=x.getValue();
		    if(x== null || k.equals(val)) //If finds the element or the element is null
		        return x;
		    if (k.compareTo(val) < 0 )  {
		        return threeSearch(x.getleft(),k); //if k is less than the value of the actual node, continues the search in the left subtrees
		    } else { //elsewhere
		        return threeSearch(x.getright(),k); //continues the search in the right subtrees
		    }
		}

	///////////////////////////////////////////////////////////////////////////////////////

		/**
		 * 
		 */
		@Override
		public T getMinValue()  {
		    if(root != null) {
		        return threeMinimum(root).getValue(); //recursive call
		    }
		    return null;
		}
		
		/**
		 * Recursive search of the minimum value in the three
		 * 
		 * @param x
		 * @return the node containing the minimum value
		 */
		private RBTreeElem<T> threeMinimum( RBTreeElem<T> x) { //calculates the minimum of the subtrees with root x
		    while (x.getleft() != null) {
		        x=x.getleft();
		    }
		    return x;
		}	
		
		@Override
		public T getMaxValue() { 
		    if(root != null) {
		        return threeMaximum(root).getValue(); //recursive call
		    }
		    return null;
		}
		
		/**
		 * Recursive search of the maximum value in the three
		 * 
		 * @param x
		 * @return the node containing the maximum value
		 */
		private RBTreeElem<T> threeMaximum(  RBTreeElem<T> x) //calculates the maximum of the subtrees with root x
		{
		    while (x.getright() != null)
		    {
		        x=x.getright();
		    }
		    return x;
		}

	///////////////////////////////////////////////////////////////////////////////////////
			
		/**
		 * Return the node with the successor value of the input value k
		 * 
		 * @param k
		 * @return the node containing the next value after k in the tree, error elsewhere
		 */
		public RBTreeElem<T> successorVal(T k) { 
			try {
		        RBTreeElem<T> x=ricercaVal(k);
		        if(x == null) 
		            throw new Exception();
		        return treeSuccessor(x); //If the value is in the tree
		    } catch (Exception e) {
		            throw new RuntimeException("The element is not present in the tree");
		    }
		}
		
		/**
		 * Recursive search of the successor of x in the tree
		 * 
		 * @param x
		 * @return 
		 */
		private RBTreeElem<T> treeSuccessor(RBTreeElem<T> x)
		{
		    if(x.getright() != null) //If it has a right sub tree, take the minimum value in its
		        return threeMinimum(x.getright());
		    RBTreeElem<T> y=x.getp();
		    while(y != null && x==y.getright()) {//elsewhere searches the first note in the node ancestor that it is not the right child of its father. 
		        x=y;
		        y=y.getp(); //Its father will be the successor
		    }
		    return y;
		}
		
		/**
		 * Return the node with the predecessor value of the input value k
		 * 
		 * @param k
		 * @return the node containing the previous value after k in the tree, error elsewhere
		 */
		public RBTreeElem<T> predecessorVal(T k) { 
		    try {	    
		        RBTreeElem<T> x=ricercaVal(k);
		        if(x == null) //
		            throw new Exception(); //If the value is in the tree
		        return threePredecessor(x); 
		    } catch (Exception e) {
		    	throw new RuntimeException("The element is not present in the tree");
		    }
		}
		
		/**
		 * Recursive search of the predecessor of x in the tree
		 * 
		 * @param x
		 * @return 
		 */
		private RBTreeElem<T> threePredecessor(RBTreeElem<T> x)
		{
		    if(x.getleft() != null)  //If it has a left sub tree, take the maximum value in its
		        return threeMaximum(x.getright());
		    RBTreeElem<T> y=x.getp();
		    while(y != null && x==y.getleft()) { //elsewhere searches the first note in the node ancestor that it is not the left child of its father. 
		        x=y;
		        y=y.getp(); //Its father will be the predecessor
		    }
		    return y;
		}
		
	/////////////////////////////////////////////////////////////////////////////////////////////

		@Override
		public void insertElem(T value) { 
		    try
		    {
		        if(value ==null )
		            throw new Exception();
		        
		        RBTreeElem<T> newNode = new  RBTreeElem<T>(value);
		        RBTreeElem<T> x=root;
		        RBTreeElem<T> y = null;
		        while(x!=null) //Search a node with less than two child
		        {
		            y=x;
		            if(newNode.getValue().compareTo(x.getValue()) < 0) //if the value is  < x continues in the left subtree
		                x=x.getleft();
		            else
		                x=x.getright(); //elsewhere continues in the right subtree
		        }
		        newNode.setp(y); //update father value
		        if(y==null)
		        {
		            root=newNode; //if doesn't have father node, it's the root
		        }
		        else
		        {
		            if(newNode.getValue().compareTo(y.getValue()) < 0) //update child value in the father 
		                y.setleft(newNode);
		            else
		                y.setright(newNode);
		        }
		        
		        
		        newNode.setcolor(true);
		        while(newNode!=root && newNode.getp().getcolor()!=true) {//Searches the violation of the RB Tree properties
		            if(newNode.getp()==newNode.getp().getp().getleft())  {//if the father of the new node is a left child 
		                y=newNode.getp().getp().getright(); // uncle of new node
		                if(y.getcolor()==true) { //if uncle is red
		                	newNode.getp().setcolor(false); //father and uncle will be black
		                    y.setcolor(false); 
		                    newNode.getp().getp().setcolor(true); //grandfather will be red
		                    newNode=newNode.getp().getp(); // update the pointer
		                } else {//elsewhere, uncle is black
		                    if(newNode==newNode.getp().getright()) { //if new node is right child
		                    	newNode=newNode.getp();
		                        left_Rotate(newNode); 
		                    } 
		                    newNode.getp().setcolor(false); //father will be black
		                    newNode.getp().getp().setcolor(true); //grandfather will be red
		                    right_Rotate(newNode.getp().getp());
		                }
		            } else {  //elsewhere, the father is a right child
		           
		                y=newNode.getp().getp().getleft(); //uncle of new node
		                if(y.getcolor()==true)  { //if uncle is red
		                	newNode.getp().setcolor(false); //father and uncle will be black
		                    y.setcolor(false);
		                    newNode.getp().getp().setcolor(true);//grandfather will be red
		                    newNode=newNode.getp().getp(); // update the pointer
		                } else  { //elsewhere, uncle is black
		                    if(newNode==newNode.getp().getleft()) { //if new node is right child
		                    	newNode=newNode.getp();
		                        right_Rotate(newNode);
		                    }
		                    newNode.getp().setcolor(false); //father will be black
		                    newNode.getp().getp().setcolor(true); //grandfather will be red
		                    left_Rotate(newNode.getp().getp());
		                }
		            }
		        }
		    } catch(Exception t) {
		            throw new RuntimeException("The element is null");
		    }
		    incNodesNumber();
		}
		
		/**
		 * Rotation operation, needed to keep the RB structure in the tree after insertion or deletion
		 * 
		 * @param x
		 */
		private void left_Rotate(RBTreeElem<T> x) { 
		    try{	    
		        if(x.getright() == null)
		            throw new Exception();
		        
		        RBTreeElem<T> y=x.getright(); //y <- x.right
		        x.setright(y.getleft()); //x.right <- y.left
		        
		        if(y.getleft() != null)
		            y.getleft().setp(x);  //y.left.p <- x
		        
		        y.setp(x.getp()); //y.p <- x.p
		        if(x.getp() == null)    //x was root
		            setRoot(y); //root <- y
		        else if(x==x.getp().getleft()) //elsewhere if x=x.p.left
		            x.getp().setleft(y); //x.p.left <- y
		        else //elsewhere
		            x.getp().setright(y); //x.p.right <- y
		        
		        y.setleft(x); //y.left <- x
		        x.setp(y);  //x.p <- y
		    }  catch (Exception e)  {
		    	throw  new RuntimeException("Right child null");
		    }
		}
		
		/**
		 * Rotation operation, needed to keep the RB structure in the tree after insertion or deletion
		 * 
		 * @param x
		 */
		private void right_Rotate(RBTreeElem<T> x)
		{
		    try {
		        if(x.getleft()== null)
		            throw new Exception();
		        
		        RBTreeElem<T> y=x.getleft(); //y <- x.left
		        x.setleft(y.getright()); //x.left <- y.right
		        
		        if(y.getright()!= null)
		            y.getright().setp(x);  //y.right.p <- x
		        
		        y.setp(x.getp()); //y.p <- x.p
		        if(x.getp() == null)    //if x was root
		            setRoot(y); //root <- y
		        else if(x==x.getp().getright()) //elsewhere if x=x.p.right
		            x.getp().setright(y); //x.p.right <- y
		        else //elsewhere
		            x.getp().setleft(y); //x.p.left <- y
		        
		        y.setright(x); //y.right <- x
		        x.setp(y);  //x.p <- y
		    }  catch (Exception e) {
		    	throw new RuntimeException("Left child null");
		    }
		}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		@Override
		public void deleteElem(T k){ 
		    RBTreeElem<T> z=ricercaVal(k); //node to delete
		    if(z != null) {
		        RBTreeElem<T> y;
		        RBTreeElem<T> x;
		        //if the node has not a child
		        if(z.getright() == null && z.getleft() == null)
		            y=z;
		        else
		            y=treeSuccessor(z);
		         //if y has a child, x=y.child
		        if (y.getleft() != null)
		            x=y.getleft();
		        else //elsewhere null
		            x=y.getright();
		        //remove the node form the tree. update the pointer
		        if(x != null)
		            x.setp(y.getp()); 
		        
		        if(y.getp() == null) //y was the root of the tree
		            root=x;
		        else { //elsewhere
		        
		            if(y == ( y.getp() ).getleft()) {//update y father with x
		            	 ( y.getp() ).setleft(x);   
		            } else {
		            	 ( y.getp() ).setright(x);
		            }
		        }
		        //if the extracted node is the z successor reinsert the value in place of z
		        if(y!=z)
		            z.setValue(y.getValue());
		        if(y.getcolor() == false)
		            RB_Delete_Fixup(this,x);
		    }
		    decNodesNumber();
		}


		
		/**
		 * Fix the RB tree property after the delection
		 * 
		 * @param A
		 * @param x
		 */
		private void RB_Delete_Fixup(RBTree<T> A, RBTreeElem<T> x)  {
		    while(x != A.getRoot() && x.getcolor()==false)  {
		        RBTreeElem<T> w;
		
		        if(x==x.getp().getleft()) { //if x left child
		            w=x.getp().getright(); //takes the x siblings
		            if(w.getcolor()==true) { // if w is red
		                w.setcolor(false);     //w become black
		                x.getp().setcolor(true); // x become red
		                A.left_Rotate(x.getp()); //left rotation
		                w=x.getp().getright(); //
		            } // w is black
		            if(w.getleft().getcolor()==false && w.getright().getcolor()==false) { //w childres is black
		                w.setcolor(true);     //w become red
		                x=x.getp();
		            } else  {
		                if(w.getright().getcolor()==false) //w left child is red and right child is black
		                {
		                    w.getleft().setcolor(false); //w left child become black
		                    w.setcolor(true); //w become red
		                    A.right_Rotate(w); //right rotation
		                    w=x.getp().getright();
		                }
		                w.setcolor(x.getp().getcolor()); //w become red
		                x.getp().setcolor(false); // x father become black
		                if(w.getright()!=null)
		                    w.getright().setcolor(false); //w right child become black
		                A.left_Rotate(x.getp()); //left rotation
		                x=A.getRoot();
		            }
		        }
		        else //x right child
		        {
		            w=x.getp().getleft();
		            if(w.getcolor()==true)
		            {
		                w.setcolor(false);     //case 1
		                x.getp().setcolor(true);
		                A.right_Rotate(x.getp());
		                w=x.getp().getleft();
		            }
		            if(w.getright().getcolor()==false && w.getleft().getcolor()==false)
		            {
		                w.setcolor(true);     //case 2
		                x=x.getp();
		            }
		            else
		            {
		                if(w.getleft().getcolor()==false)
		                {
		                    w.getright().setcolor(false); //case 3
		                    w.setcolor(true);
		                    A.left_Rotate(w);
		                    w=x.getp().getleft();
		                }
		                w.setcolor(x.getp().getcolor()); //case 4
		                x.getp().setcolor(false);
		                if(w.getleft()!=null)
		                    w.getleft().setcolor(false);
		                A.right_Rotate(x.getp());
		                x=A.getRoot();
		            }
		        }
		    }
		    x.setcolor(false);
		}
		
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * RB tree element
	 * 
	 * @author giacomo
	 *
	 * @param <T>
	 */
	class RBTreeElem<T extends Comparable<T>> extends TreeElem<T>{
	    private RBTreeElem<T> p; //father
	    private RBTreeElem<T> left; //left child
	    private RBTreeElem<T> right; //right child
	    private boolean color; //color 1=red 0=black
	    
	    //Costructor
	    public RBTreeElem(T v){
	    	setValue(v); 
	    	p = null; 
	    	left = null; 
	    	right = null;
	    }
	    
		@Override
		public boolean isLeaf() {
			if(left == null && right == null)
				return true;
			return false;
		}; 
		
		@Override
		protected TreeElem<T> getFather() {
			return p;
		}

		@Override
		protected List<TreeElem<T>> getChildren() {
			List<TreeElem<T>> returnList = new ArrayList<TreeElem<T>>(2);
			returnList.add(left);
			returnList.add(right);
			return null;
		}
	    
	    //Setter
	    public void setleft(RBTreeElem<T> e){left=e;};  
	    public void setright(RBTreeElem<T> e){right=e;}; 
	    public void setp(RBTreeElem<T> e){p=e;}; 
	    public void setcolor(boolean c){color=c;}; 
	    
	    //Getter
	    public RBTreeElem<T> getp() {return p;}; 
	    public RBTreeElem<T> getleft() {return left;}; 
	    public RBTreeElem<T> getright()  {return right;};
	    public boolean getcolor() {return color;} 
}

