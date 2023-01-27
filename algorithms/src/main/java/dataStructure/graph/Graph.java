package dataStructure.graph;

import java.util.List;
import java.util.Stack;

public abstract class Graph<T> {
	
	private int edgesNumber = 0;
	private int nodesNumber = 0;
	
	/**
	 * Adds the element to the graphs, check first for the existence
	 * 
	 * @param elem
	 * @throws Exception, if the elem is null
	 */
	public abstract void addVertex(T elem) throws Exception;  
	
	/**
	 * Removes the node with elem value from the graph. Removes all the edges to and from the vertex
	 * 
	 * @param elem
	 */
	public abstract void removeVertex(T elem) throws Exception;
	
	/**
	 * Returns that if the elem is in the graph
	 * 
	 * @param elem
	 * @return true if is contains, else false
	 */
	public abstract boolean isVertex(T elem);
	
	/**
	 * Adds an edge to the graph, from the elem of v1 value to the elem of v2 value with value weight
	 * 
	 * @param v1, edge from
	 * @param v2, edge to
	 * @throws Exception, if one of the element doesn't exist in the graph  or the edge is already present
	 */
	public abstract void addEdge(T v1, T v2, double weight) throws Exception;
	
	/**
	 * Removes the edge from node with value v1 from the edge with value v2
	 * 
	 * @param v1
	 * @param v2
	 * @throws Exception, if one of the element doesn't exist in the graph 
	 */
	public abstract void removeEdge(T v1, T v2) throws Exception;
	
	/**
	 * Returns if there is and edge from node with value v1 to node with value v2
	 * 
	 * @param v1
	 * @param v2
	 * @return true if there is an edge, false elsewhere
	 * @throws Exception, if one of the element doesn't exist in the graph
	 */
	public abstract boolean isLinked(T v1, T v2) throws Exception;
	
	public abstract void printGraph();
	
	/**
	 * Makes a deep first search of the graph starting from the node with the value passed as parameter
	 * 
	 * @param s, value of the node root of the DFS
	 * @return a List containing the values of the nodes ordered using DFS 
	 * @throws Exception, the value passed as input does not exist in the graph
	 */
	public abstract List<T> DFS(T s);
	
	/**
	 * 
	 * @param s
	 * @return a List containing the values of the nodes ordered using BFS
	 * @throws Exception, the value passed as input does not exist in the graph
	 */
	public abstract List<T> BFS(T s);
	
	/**
	 * Creates the topographic visit of the graph
	 * 
	 * @return Stack containing the result
	 */
	public abstract Stack<T> TS();
	
	/**
	 * Creates a list of list containing for each list the component strongly connected in the graph
	 * 
	 * @return List of list containing che CFC
	 * @throws Exception
	 */
	public abstract List<List<T>> CFC() throws Exception ;
	
	/**
	 * Creates the transpose graph
	 * 
	 * @return the transpose graph
	 * @throws Exception
	 */
	public abstract Graph<T> transposeGraph() throws Exception;
	
	//Not abstract Methods
	public int getEdgesNumber(){ return this.edgesNumber;}
	public void incEdgesNumber(){ this.edgesNumber++;}
	public void decEdgesNumber(){ this.edgesNumber--;}
	
	public int getNodesNumber(){ return this.nodesNumber; }
	public void incNodesNumber(){this.nodesNumber++;}
	public void decNodesNumber(){this.nodesNumber--;}
}
