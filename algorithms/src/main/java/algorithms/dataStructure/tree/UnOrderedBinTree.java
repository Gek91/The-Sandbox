package algorithms.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class UnOrderedBinTree<T> extends Tree<T>{

	@Override
	public void printTree() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TreeElem<T> ricercaVal(T value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getMinValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getMaxValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertElem(T node) throws Exception {
		if(getRoot() == null)
			setRoot(new UnOrderedBinTreeElem<T>(node));
		else{
			Queue<TreeElem<T> > queue = new LinkedBlockingQueue<TreeElem<T>>();
			TreeElem<T> treeElem = getRoot();
			queue.add(getRoot());
			while(treeElem.getChildren().size() == 2){
				queue.add(treeElem.getChildren().get(0));
				queue.add(treeElem.getChildren().get(1));
				treeElem = queue.poll();
			}
			UnOrderedBinTreeElem<T> unOrderedTreeElem = (UnOrderedBinTreeElem<T>) treeElem;
			if(unOrderedTreeElem.getChildren().size() == 0)
				unOrderedTreeElem.setRight(new UnOrderedBinTreeElem<T>(node,unOrderedTreeElem,null,null));
			else
				unOrderedTreeElem.setLeft(new UnOrderedBinTreeElem<T>(node,unOrderedTreeElem,null,null));
		}
	}

	@Override
	public void deleteElem(T value) throws Exception {
		// TODO Auto-generated method stub
		
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

class UnOrderedBinTreeElem<T> extends TreeElem<T>{
	//Pointer to father, child left and child right
	private UnOrderedBinTreeElem<T> father;
	private UnOrderedBinTreeElem<T> left;
	private UnOrderedBinTreeElem<T> right;
	
	//Constructors
	public UnOrderedBinTreeElem(T value){
		this.setValue(value);		
	}
	
	public UnOrderedBinTreeElem(T value, UnOrderedBinTreeElem<T> padre, UnOrderedBinTreeElem<T> left, UnOrderedBinTreeElem<T> right){
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
	protected UnOrderedBinTreeElem<T> getFather() {
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

	public void setFather(UnOrderedBinTreeElem<T> padre) {
		this.father = padre;
	}
	public UnOrderedBinTreeElem<T> getLeft() {
		return left;
	}
	public void setLeft(UnOrderedBinTreeElem<T> left) {
		this.left = left;
	}
	public UnOrderedBinTreeElem<T> getRight() {
		return right;
	}
	public void setRight(UnOrderedBinTreeElem<T> right) {
		this.right = right;
	}	
	
}