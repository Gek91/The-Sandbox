package dataStructure.graph.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import dataStructure.graph.Graph;



/**
 * Directed graph implementation
 * Vertices of the graph are GraphVertex object
 * The vertices of the graph are saved in a set. 
 * 
 * @author Giacomo Pandini
 *
 * @param <T>
 */
public class DirectedGraph<T> extends Graph<T>{
	
	//Contains the data of the Graph
	private Set<GraphVertex<T>> vertices;

	//Constructor
	public DirectedGraph(){
		vertices = new HashSet<GraphVertex<T>>();
	}
	
	//Return the vertices of the graph
	public Set<GraphVertex<T>> getVertices(){
		return vertices;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void addVertex(T elem) throws Exception {
		if(elem != null){
			if(!isVertex(elem)){
				incNodesNumber();
				vertices.add( new GraphVertex<T>(elem) );
			}
		} else
			throw new Exception("The input elem is null");
	}
	
	@Override
	public void removeVertex(T elem) {
		GraphVertex<T> elementToDelete = getVertex(elem);
		if(elementToDelete != null){
			//Remove the vertex from the graph
			vertices.remove(elementToDelete);
			decNodesNumber();
			for(int i=0; i < elementToDelete.getEdges().size() ; i++)
				decEdgesNumber();
			
			//Removes the edges income in the eliminated vertex
			for(GraphVertex<T> vertex : vertices){
				List<GraphEdge<T>> edges = vertex.getEdges();
				int index = edges.indexOf(elementToDelete);
				if(index > -1){
					edges.remove(index);
					decEdgesNumber();
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public boolean isVertex(T elem){
		return getVertex(elem)!=null;
	}
	
	/**
	 * Return the Vertex of the Graph with the elem value
	 * 
	 * @param elem
	 * @return GraphVertex object containing the element if the value is in the graph, null otherwise
	 */
	public GraphVertex<T> getVertex(T elem){
		if(elem != null){
			for(GraphVertex<T> vertex : vertices){
				if(vertex.getValue().equals(elem))
					return vertex;
			}
			return null;
		} else
			return null;
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void addEdge(T v1, T v2, double weight) throws Exception {
		GraphVertex<T> Vertex1 = getVertex(v1); 
		GraphVertex<T> Vertex2 = getVertex(v2);
        if( Vertex1 != null || Vertex2 != null ) {
        	List<GraphEdge<T>> edgesV1 = Vertex1.getEdges();
        	if(!edgesV1.contains(Vertex2)){
        		edgesV1.add(new GraphEdge<T>(Vertex1,Vertex2,weight));
        		incEdgesNumber();
        	} else 
        		throw new Exception("The element already exists in the graph");
        } else {
        	throw new Exception("One of the two node of the element is not in the graph");
        }
	}
	
	@Override
	public  void removeEdge(T v1, T v2) throws Exception{
		GraphVertex<T> Vertex1 = getVertex(v1); 
		GraphVertex<T> Vertex2 = getVertex(v2);
		 if( Vertex1 != null || Vertex2 != null ) {
			 List<GraphEdge<T>> edges = Vertex1.getEdges();
			 for(GraphEdge<T> edge : edges){
				 if(edge.getVertexTo().equals(Vertex2)){
					 edges.remove(edge);
					 decEdgesNumber();
				 }
			 }
        } else {
        	throw new Exception("One of the two node of the edge is not in the graph");
        }
	}
	
	@Override
	public boolean isLinked(T v1, T v2) throws Exception{
		GraphVertex<T> Vertex1 = getVertex(v1); 
		GraphVertex<T> Vertex2 = getVertex(v2);
		 if( Vertex1 != null || Vertex2 != null ) {
        	for(GraphEdge<T> edge : Vertex1.getEdges()){
        		if(edge.getVertexTo().equals(v2))
        			return true;
        	}
        	return false;
        } else {
        	throw new Exception("One of the two node of the edge is not in the graph");
        }
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	@Override
	public List<T> DFS(T s) {
		//Map of colors used in the DFS visit, The value used for the color is  0 = white, 1 = grey , 2 = black
		Map<GraphVertex<T>,Integer> colorsMap = new HashMap<GraphVertex<T>,Integer>(); 
		for(GraphVertex<T> vertex : vertices){
			colorsMap.put(vertex, 0);
		}
		List<T> outList = new ArrayList<T>(getNodesNumber());
		DFSVisit(outList, colorsMap, getVertex(s)); //recursive call
		return outList;	
	}
	
	/**
	 * recursive function used in the DFS
	 * 
	 * @param outList
	 * @param colorsVector
	 * @param elemIndex
	 */
	private void DFSVisit(List<T> outList, Map<GraphVertex<T>,Integer> colorsMap, GraphVertex<T> vertex){
		//colors the node gray and adds it to the outputlist
		colorsMap.put(vertex, 1); 
		outList.add(vertex.getValue());
		for( GraphEdge<T> edge : vertex.getEdges() ){ //visits all the neighbors
			GraphVertex<T> neighborVertex = edge.getVertexTo();
			if( colorsMap.get(neighborVertex).equals(0) ) //If the neighbor is white 
				DFSVisit(outList,colorsMap,neighborVertex); //recursive call
		}
		colorsMap.put(vertex, 2);  //Colors the node black
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public List<T> BFS(T s) {
		Map<GraphVertex<T>,Integer> colorsMap = new HashMap<GraphVertex<T>,Integer>(); 
		for(GraphVertex<T> vertex : vertices){
			colorsMap.put(vertex, 0);
		}
		List<T> outList = new ArrayList<T>(getNodesNumber()); //output list
		Queue<GraphVertex<T>> Q = new LinkedList<GraphVertex<T>>(); //helper queue
		
		GraphVertex<T> vertex =getVertex(s);
		colorsMap.put(vertex, 1);  // colors gray the node
		Q.add(vertex); //add the vertex to the queue
		
		while(!Q.isEmpty()){ 
			vertex = Q.remove(); //remove the first vertex added
			for( GraphEdge<T> edge : vertex.getEdges() ){ //visits all the neighbors
				GraphVertex<T> neighborVertex = edge.getVertexTo();
				if( colorsMap.get(neighborVertex).equals(0) ){ //If the neighbor is white
					colorsMap.put(neighborVertex, 1); //Color the note of gray
					Q.add(neighborVertex); //adds the node to the queue
				}
			}
			colorsMap.put(vertex, 2); //colors the node of black
			outList.add(vertex.getValue()); //add the element to the output list
		}
		return outList;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Stack<T> TS() {
		Stack<GraphVertex<T>> SVertex = TSVertex();
		Stack<T> S = new Stack<T>();
		for( GraphVertex<T> vertex : SVertex)
			S.push(vertex.getValue());
		return S;
	}
	
	private Stack<GraphVertex<T>> TSVertex(){
		Map<GraphVertex<T>,Integer> colorsMap = new HashMap<GraphVertex<T>,Integer>(); // 0 = white, 1 = grey , 2 = black
		for(GraphVertex<T> vertex : vertices){
			colorsMap.put(vertex, 0);
		} 
		Stack<GraphVertex<T>> S = new Stack<GraphVertex<T>>(); //output Stack
		
		for(GraphVertex<T> vertex : vertices){
			if(colorsMap.get(vertex).equals(0)){
				TSVisit(colorsMap,S,vertex);
			}
		}
		return S;
	}
	
	/**
	 * recursive function used in TS 
	 * 
	 * @param colorsVector
	 * @param S
	 * @param elemIndex
	 */
	private void TSVisit(Map<GraphVertex<T>,Integer> colorsMap, Stack<GraphVertex<T>> S, GraphVertex<T> vertex){
		colorsMap.put(vertex, 1); //Colors the vertex of gray
		for( GraphEdge<T> edge : vertex.getEdges() ){ //visits all the neighbors
			GraphVertex<T> neighborVertex = edge.getVertexTo();
			if( colorsMap.get(neighborVertex).equals(0) )
				TSVisit(colorsMap,S,neighborVertex);
		}
		colorsMap.put(vertex, 2); //Colors the node of black
		S.push(vertex); //Add the value of the vertex to the output Stack
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<List<T>> CFC() throws Exception {
		
		//calculates the topologic visit of the graph and the transpose graph
		Stack<T> S = TS();
		Graph<T> transposedGraph = transposeGraph();
		//builds a list of list, each list contains an CFC(components strongly connected)
		return CFCFunc((DirectedGraph<T>)transposedGraph,S);
	}
	
	/**
	 * create an CFC using a DFS visit of the component
	 * 
	 * @param TG
	 * @param S
	 * @return
	 * @throws Exception
	 */
	private List<List<T>> CFCFunc(DirectedGraph<T> TG , Stack<T> S ) throws Exception{
		Map<GraphVertex<T>,Integer> colorsMap = new HashMap<GraphVertex<T>,Integer>(); // 0 = white, 1 = grey , 2 = black
		for(GraphVertex<T> vertex : vertices){
			colorsMap.put(vertex, 0);
		} 
		List<List<T>> listOfComponentList = new ArrayList<List<T>>(); //List of list o the CFC
		
		while(!S.isEmpty()){
			GraphVertex<T> vertex = TG.getVertex(S.pop());
			if(colorsMap.get(vertex).equals(0)){
				List<T> componentList = new ArrayList<T>();
				Set<GraphVertex<T>> transposeGraphList = TG.getVertices();
				CFCFuncVisit(transposeGraphList, colorsMap, vertex,componentList); //recursive call
				listOfComponentList.add(componentList); //adds the list of the CFC to the list of list
			}
		}
		return listOfComponentList;
	}
	
	/**
	 * iterative visit of the graph
	 * 
	 * @param graphlist
	 * @param colorsVector
	 * @param elemIndex
	 */
	private void CFCFuncVisit(Set<GraphVertex<T>> graphlist, Map<GraphVertex<T>,Integer> colorsMap, GraphVertex<T> vertex, List<T> componentList){
		colorsMap.put(vertex, 1);
		componentList.add(vertex.getValue()); //adds the node to the CFC
		for( GraphEdge<T> edge : vertex.getEdges() ){ //visits its neighbors
			GraphVertex<T> neighborVertex = edge.getVertexTo(); 
			if(colorsMap.get(neighborVertex).equals(0)){
				CFCFuncVisit(graphlist,colorsMap,neighborVertex,componentList); //recursive call
			}
		}
		colorsMap.put(vertex, 2);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Graph<T> transposeGraph() throws Exception {
		
		Graph<T> transposedGraph = new DirectedGraph<T>();
		//Adds all the nodes to the transposed graph
		for(GraphVertex<T> vertex : vertices){
			transposedGraph.addVertex(vertex.getValue()); 
		}
	
		//Adds the edges inverted to the transposed graph
		for(GraphVertex<T> vertex : vertices){
			for( GraphEdge<T> edge : vertex.getEdges() ){ //visits all the neighbors
				transposedGraph.addEdge(edge.getVertexTo().getValue(), vertex.getValue(), edge.getWeight());	
			}
		}
		return transposedGraph;
	}

	@Override
	public void printGraph() {
		for(GraphVertex<T> vertex : vertices){
			System.out.print(vertex.getValue() + " : ");
			for(GraphEdge<T> edge : vertex.getEdges()){
				System.out.print("-> " + edge.getVertexTo().getValue() );
			}
			System.out.println("");
		}
	}	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Initialize the structure used for the calculation of the shortest path between vertices
	private Set<ShortestPathElem<T>> initializePathElements(GraphVertex<T> startEdge){
		Set<ShortestPathElem<T>> returnList = new HashSet<ShortestPathElem<T>>(getNodesNumber());
		for(GraphVertex<T> vertex : getVertices()){
			if(vertex != startEdge)
				returnList.add(new ShortestPathElem<T>(vertex,null,Double.MAX_VALUE));
			else
				returnList.add(new ShortestPathElem<T>(vertex,null,0));
		}
		return returnList;
	}
	
	//Relax an edge: test if it's possible improve a shortest path between the two vertices
	private void relax(ShortestPathElem<T> vertexFrom, ShortestPathElem<T> vertexTo){
		//Find edge value
		double edgeValue = Double.MAX_VALUE;
		for(GraphEdge<T> edge : vertexFrom.getVertex().getEdges()){
			if(edge.getVertexFrom() == vertexFrom.getVertex() && edge.getVertexTo() == vertexTo.getVertex()){
				edgeValue = edge.getWeight();
				break;
			}	
		}
		if(vertexTo.getPathWeight() > vertexFrom.getPathWeight() + edgeValue){
			vertexTo.setPathWeight(vertexFrom.getPathWeight() + edgeValue);	
			vertexTo.setPredecessor(vertexFrom);
		}
	}
	
	/**
	 * Resolve the single source shortest path problem in case there are edges with negative weights
	 * Returns if in the graph there is a negative weight cycle reachable from the start edge 
	 * If there is no such cycle the algorithm produces the shortest paths from the start edge to the other edge
	 * 
	 * @param 
	 * @param startEdge
	 * @return
	 */
	private boolean bellmanFord(Set<ShortestPathElem<T>> shortestPaths, GraphVertex<T> startVertex){
		Set<ShortestPathElem<T>> shortestSet = initializePathElements(startVertex);
		
		//Relax all the edge
		for(ShortestPathElem<T> vertex : shortestSet){
			for(GraphEdge<T> edge :vertex.getVertex().getEdges()){
				ShortestPathElem<T> vertexTo = null;
				for(ShortestPathElem<T> elem : shortestSet){
					if(edge.getVertexTo() == elem.getVertex()){
						vertexTo = elem;
						break;
					}
				}
				relax(vertex,vertexTo);
			}
		}
		//Check presence of negative weight cycle
		for(ShortestPathElem<T> vertex : shortestSet){
			for(GraphEdge<T> edge :vertex.getVertex().getEdges()){
				ShortestPathElem<T> vertexTo = null;
				for(ShortestPathElem<T> elem : shortestSet){
					if(edge.getVertexTo() == elem.getVertex()){
						vertexTo = elem;
						break;
					}
				}
				if(vertexTo.getPathWeight() > vertex.getPathWeight() + edge.getWeight())
					return false;
			}
		}
		if(shortestPaths != null)
			shortestPaths.addAll(shortestSet);
		return true;
	}
	
	/**
	 * Calculate the shortest paths in the graph stating in startEdge using topografic sorting before the calculation
	 * @param startEdge
	 * @return Set<ShortestPathElem<T>> containing all the node of the graph and the value
	 */
	private Set<ShortestPathElem<T>> DAGShortestPath(GraphVertex<T> startVertex){
		//Topological sort of the vertex
		Stack<GraphVertex<T>> S = TSVertex();
		Set<ShortestPathElem<T>> shortestSet = initializePathElements(S.peek());
		//takes the vertex in topological order
		while(!S.isEmpty()){
			GraphVertex<T> vertex = S.pop();
			//for every edge from the vertex
			for(GraphEdge<T> edge : vertex.getEdges()){
				ShortestPathElem<T> vertexFrom = null;
				ShortestPathElem<T> vertexTo = null;
				//Find ShortestPathElem objects containing vertices of the edge
				for(ShortestPathElem<T> vertexPath : shortestSet){
					if(edge.getVertexFrom() == vertexPath.getVertex())
						vertexFrom = vertexPath;
					if(edge.getVertexTo() == vertexPath.getVertex())
						vertexTo = vertexPath;
				}
				relax(vertexFrom,vertexTo);
			}
		}
		return shortestSet;
	}
	
	/**
	 * Dijkstra, used for the calculation of the shortest path in the graph starting from starVertex. 
	 * There must be no negative cycles
	 * 
	 * @param startVertex
	 * @return
	 */
	//TODO FIX ME
//	private Set<ShortestPathElem<T>> Dijkstra(GraphVertex<T> startVertex){
//		Set<ShortestPathElem<T>> shortestSet = initializePathElements(startVertex);
//		Set<ShortestPathElem<T>> S = new HashSet<ShortestPathElem<T>>();
//		
//		FibonacciHeap<ShortestPathElem<T>> fibHeap = new FibonacciHeap<ShortestPathElem<T>>();
//		//Fill the fibonacci heap
//		for(ShortestPathElem<T> vertex : shortestSet){
//			fibHeap.insert(vertex, vertex.getPathWeight());
//		} 
//		
//		while(fibHeap.size() > 0){
//			ShortestPathElem<T> vertexFrom = fibHeap.remove();
//			S.add(vertexFrom);
//			for(GraphEdge<T> edge : vertexFrom.getVertex().getEdges()){
//				ShortestPathElem<T> vertexTo = null;
//				for(ShortestPathElem<T> elem : shortestSet){
//					if(elem.getVertex() == edge.getVertexTo()){
//						vertexTo = elem;
//						break;
//					}
//				}
//				relax(vertexFrom,vertexTo);
//			}
//		}
//		return S;
//	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	private fordFulkerson(){
//		Set<MaximumFlowElement<T>> flowEdge = new HashSet<MaximumFlowElement<T>>();
//	}
	
}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Vertex of the graph. it contains the value of the vertex and the list of the outgoing edges
	 * 
	 * @author Giacomo Pandini
	 *
	 * @param <T>
	 */
	class GraphVertex<T>{
		//List of neighbors of the graph node
		private T value;
		private List<GraphEdge<T>> edges; 
		
		//Constructor
		public GraphVertex(T value){
			this.value = value;
			this.edges = new LinkedList<GraphEdge<T>>();
		}
		
		public List<GraphEdge<T>> getEdges() { return edges; }
		public void setEdges(List<GraphEdge<T>> edges) { this.edges = edges; }

		public T getValue(){ return this.value; }
		public void setValue(T value){ this.value = value; }
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	/**
	 * Edge of the graph. It contains the weight of the edge and vertex of arrive
	 * 
	 * @author Giacomo Pandini
	 *
	 * @param <T>
	 */
	class GraphEdge<T> implements Comparable<GraphEdge<T>>{
		private double weight;
		private GraphVertex<T> vertexFrom;
		private GraphVertex<T> vertexTo;
		
		public GraphEdge(GraphVertex<T> vertexFrom, GraphVertex<T> vertexTo){
			this.weight = 1.0;
			this.vertexTo = vertexTo; 
			this.vertexFrom = vertexFrom; 
		}
		
		public GraphEdge( GraphVertex<T> vertexFrom, GraphVertex<T> vertexTo, double weight){
			this.weight = weight;
			this.vertexTo = vertexTo; 
			this.vertexFrom = vertexFrom;  
		}
		
		public double getWeight() { return weight; }
		public void setWeight(double weight) { this.weight = weight; }

		public GraphVertex<T> getVertexFrom() { return vertexFrom; }
		public void setVertexFrom(GraphVertex<T> vertexFrom) { this.vertexFrom = vertexFrom; }

		public GraphVertex<T> getVertexTo() { return vertexTo; }
		public void setVertexTo(GraphVertex<T> vertexTo) { this.vertexTo = vertexTo; }

		@Override
		public int compareTo(GraphEdge<T> edge) {
			if(this.weight < edge.getWeight())
				return -1;
			else if(this.weight > edge.getWeight())
				return 1;
			else
				return 0;
		}	
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Class used for the calculation of the shortest path in the graph
	 * 
	 * @author Giacomo Pandini
	 *
	 * @param <T>
	 */
	class ShortestPathElem<T>{
		private GraphVertex<T> vertex;
		private ShortestPathElem<T> predecessor;
		private double pathWeight;
		
		public ShortestPathElem(GraphVertex<T> vertex, ShortestPathElem<T> predecessor, double pathWeight){
			this.vertex = vertex;
			this.predecessor = predecessor;
			this.pathWeight = pathWeight;
		}
		
		public GraphVertex<T> getVertex() {	return vertex; }
		public void setVertex(GraphVertex<T> vertex) { this.vertex = vertex; }
		
		public ShortestPathElem<T> getPredecessor() { return predecessor; }
		public void setPredecessor(ShortestPathElem<T> predecessor) { this.predecessor = predecessor; }
		
		public double getPathWeight() { return pathWeight; }
		public void setPathWeight(double pathWeight) { this.pathWeight = pathWeight; }

	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	class MaximumFlowElement<T>{
		private GraphEdge<T> edge;
		private double flow;
		
		public MaximumFlowElement(GraphEdge<T> edge, double flow){
			this.edge = edge;
			this.flow = flow;
		}

		public GraphEdge<T> getEdge() { return edge; }
		public void setEdge(GraphEdge<T> edge) { this.edge = edge; }
		
		public double getFlow() { return flow; }
		public void setFlow(double flow) { this.flow = flow; }
	}

