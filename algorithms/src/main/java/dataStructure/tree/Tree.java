package dataStructure.tree;
/**
 * 
 * @author Giacomo Pandini
 *
 * @param <T>
 */
public abstract class Tree<T> {

	//Root of the Tree
	private TreeElem<T> root;
	private int nodesNumber=0;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Prints the values in the tree following its structure
	 */
	public abstract void printTree();
	
	/**
	 * Returns if the value is contained in the tree
	 * 
	 * @param value
	 * @return true if the value is contained in the tree, false elsewhere
	 */
	public abstract boolean contains(T value);
	
	/**
	 * Return the node containing the searched value
	 * 
	 * @param value
	 * @return the node containing the value, null elsewhere
	 */
	public abstract TreeElem<T> ricercaVal(T value);
	
	/**
	 * Searches the minimum value in the tree
	 * 
	 * @return the minimum value in the tree
	 */
	public abstract T getMinValue();
	
	/**
	 * Searches the maximum value in the tree
	 * 
	 * @return the maximum value in the tree
	 */
	public abstract T getMaxValue();
	
	/**
	 * Inserts the element in the tree
	 * @param value
	 * @throws Exception
	 */
	public abstract void insertElem(T node) throws Exception;
	
	/**
	 * Deletes the element from the tree
	 * 
	 * @param value
	 * @throws Exception
	 */
	public abstract void deleteElem(T value) throws Exception;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Setter Getter
	protected TreeElem<T> getRoot() {
		return root;
	}

	protected void setRoot(TreeElem<T> root) {
		this.root = root;
	}

	public int getNodesNumber() {
		return nodesNumber;
	}
	
	public void incNodesNumber(){ this.nodesNumber++;}
	public void decNodesNumber(){ this.nodesNumber--;}
}
