package dataStructure.graph.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Undirected graph implementation starting from the directed graph implementation
 * 
 * @author Giacomo Pandini
 *
 * @param <T>
 */
public class UndirectedGraph<T> extends DirectedGraph<T>{

	public UndirectedGraph(){
		super();
	}
	
	@Override
	public void addEdge(T v1, T v2, double weight) throws Exception {
		super.addEdge(v1, v2, weight);
		super.addEdge(v2, v1, weight);
	}
	
	@Override
	public  void removeEdge(T v1, T v2) throws Exception{
		super.removeEdge(v1, v2);
		super.removeEdge(v2, v1);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Retrieves the Minimum Spanning Tree of the graph
	 * @return
	 */
	public UndirectedGraph<T> MSTKruskal(){
		UndirectedGraph<T> graph = new UndirectedGraph<T>();
		List<Set<GraphVertex<T>>> setList = new ArrayList<Set<GraphVertex<T>>>(); //Used in the method
		try {
			//Builds the list of set used in the method. Create a set for every element of the graph
			for( GraphVertex<T> vertex : getVertices()){
					graph.addVertex(vertex.getValue());
					Set<GraphVertex<T>> newSet = new HashSet<GraphVertex<T>>();
					newSet.add(vertex);
					setList.add(newSet);
			}
			//Sort the edge of the graph in ascending order on its weight
			List<GraphEdge<T>> ascendingEdges = ascendingsort();
			//For every edge checks if the two vertices are in the same set. 
			//If not adds the edge to the MST and merge the two set
			for(GraphEdge<T> edge : ascendingEdges){
				Set<GraphVertex<T>> setFrom = null;
				Set<GraphVertex<T>> setTo = null;
				//Checks the sets
				for(Set<GraphVertex<T>> set : setList){
					if(set.contains(edge.getVertexFrom()))
						setFrom = set;
					if(set.contains(edge.getVertexTo()))
						setTo = set;
				}
				//different sets
				if(setFrom != setTo){
					graph.addEdge(edge.getVertexFrom().getValue(), edge.getVertexTo().getValue(), edge.getWeight());
					setFrom.addAll(setTo);
					setList.remove(setTo);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return graph;
	}
	
	/**
	 * Sort in ascending order the edges of the graph
	 * @return
	 */
	private List<GraphEdge<T>> ascendingsort(){
		Set<GraphVertex<T>> checkedVertex = new HashSet<GraphVertex<T>>();
		List<GraphEdge<T>> edgeAcendingList = new LinkedList<GraphEdge<T>>();
		//loops on the vertices of the graph
		for(GraphVertex<T> vertex : getVertices()){
			//Loops on the edge  of the vertex
			for(GraphEdge<T> edge : vertex.getEdges()){
				//Not repeat the edge
				if(!checkedVertex.contains(edge.getVertexTo())){
					//Sorted insertion
					int i = 0;
					double weight = edge.getWeight();
					while(i < edgeAcendingList.size() && edgeAcendingList.get(i).getWeight() < weight){
						i++;
					}
					edgeAcendingList.add(i, edge);
				}
			}
			checkedVertex.add(vertex);
		}
		return edgeAcendingList;
	}
	
}
