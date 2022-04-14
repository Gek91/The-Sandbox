package algorithms.dataStructure.tree;

import java.util.List;

/**
 * 
 * @author Giacomo Pandini
 *
 * @param <T>
 */
public abstract class TreeElem<T> {
	
	private T value;

	/**
	 * Check if the node is a leaf of the tree
	 * 
	 * @return true if the node is a leaf, false elsewhere
	 */
	public abstract boolean isLeaf();
	
	protected abstract TreeElem<T> getFather();
	
	protected abstract List<TreeElem<T>> getChildren();
	
	//Getter Setter
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
