package Model.data_structures;

import java.util.NoSuchElementException;

public class EdgeWeightedGraph<K, V> {
	/**
	 * N�mero de v�rtices del grafo
	 */
	private int V;
	
	/**
	 * N�mero de ejes del grafo
	 */
	private int E;
	
	/**
	 * Lista de adyacencia
	 */
	private Bag<Edge<K>>[] adj;
	
	/**
	 * Arreglo de v�rtices
	 */
	private Vertex<K, V>[] vertices;
	
	/**
	 * Capacidad del grafo
	 */
	private int capacity;
	
	private int freeVertexIndex;

	
	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph( int V ) throws IllegalArgumentException{
		if( V <= 0 )
			throw new IllegalArgumentException("La capacidad inicial debe ser un n�mero positivo");
		this.V = 0;
		E = 0;
		capacity = V;
		adj = (Bag<Edge<K>>[]) new Bag[V];
		for( int v = 0; v < V; v++){
			adj[v] = new Bag<Edge<K>>();
		}
		vertices = (Vertex<K, V>[]) new Vertex[V];
		freeVertexIndex = 0;
	}
	
	/**
	 * @param key llave del v�rtice 
	 * @return Iterable de ejes que salen del v�rtice de llave key.
	 */
	public Iterable<Edge<K>> adj( K key ){
		return adj[positionOf(key)];
	}
	
	/**
	 * @return true si no tiene v�rtices, false de lo contrario
	 */
	public boolean isEmpty(){
		return V == 0;
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public int capacity() {
		return capacity;
	}

	/**
	 * @param key llave del v�rtice
	 * @return true si el grafo contien el v�rtice de llave K, false de lo contrario
	 */
	public boolean containsVertex( K key ){
		if( !isEmpty() ){
			for( int i = 0; i < V; i++){
				Vertex<K, V> currentVertex = vertices[i];
				if( key.equals(currentVertex.getKey())){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param edge eje que se quiere comprobar si es� en el grafo
	 * @return treu si el eje existe en el grafo, false de lo contrario
	 */
	public boolean containsEdge( Edge<K> edge ){
		if( E > 0 ){
			for( Edge<K> currentEdge : edges() ){
				K currentEither = currentEdge.either();
				K edgeEither = edge.either();
				K currentOther = currentEdge.other(currentEither);
				K edgeOther = edge.other(edgeEither);
				boolean eithersEqual = currentEither.equals(edgeEither);
				boolean othersEqual = currentOther.equals(edgeOther);
				boolean otherEitherEqual = currentOther.equals(edgeEither);
				boolean eitherOtherEqual = currentEither.equals(edgeOther);
				boolean weightEqual = Double.compare(currentEdge.weight(), edge.weight()) == 0;
				if( weightEqual ){
					if( eithersEqual && othersEqual )
						return true;
					else if( otherEitherEqual && eitherOtherEqual )
						return true;
				}
			}
		}
		return false;
	}
	

	/**
	 * Agrega un eje entre dos v�rtices
	 * @param v llave del v�rtice origen
	 * @param w llave del v�rtice destino
	 * @param weight peso del v�rtice
	 */
	public void addEdge( K v, K w, double weight ) throws IllegalStateException{
		Edge<K> toAdd = new Edge<K>(v, w, weight);
		if( !containsEdge(toAdd) ){
			adj[positionOf(v)].addAtEnd(toAdd);
			adj[positionOf(w
					)].addAtEnd(toAdd);
			E++;
		}
		else{
			throw new IllegalStateException( "El eje " + toAdd + " ya est� en el grafo" );
		}
	}
	
	/**
	 * Agrega un nuevo v�rtice al grafo
	 * @param id llave del v�rtice
	 * @param info informaci�n del v�rtice
	 */
	public void addVertex(K id, V info) {
		if( V + 1 <= capacity ){
			if( !containsVertex(id) ){
				vertices[freeVertexIndex] = new Vertex<>(id, info);
				freeVertexIndex++;
				V++;
			}
			else{
				vertices[positionOf(id)].setValue(info);
			}
		}
		else{
			resize(capacity * 2);
			addVertex(id, info);
		}
	}
	
	/**
	 * Elimina un eje
	 * @param fromV llave del v�rtice origen
	 * @param toV llave del v�rtice destino
	 * @param weight peso del v�rtice
	 */
	public void deleteEdge( Edge<K> edge ) throws NoSuchElementException{
		try{
			adj[positionOf(edge.either())].delete( edge );
			adj[positionOf(edge.other(edge.either()))].delete(edge);
		}catch (NoSuchElementException e ){
			throw new NoSuchElementException( "El eje " + edge + " no existe" );
		}
		E--;
	}

	/**
	 * Elimina un eje
	 * @param fromV llave del v�rtice origen
	 * @param toV llave del v�rtice destino
	 * @param weight peso del v�rtice
	 */
	public void deleteEdge( K fromV, K toV, double weight ) throws NoSuchElementException{
		deleteEdge(new Edge<K>(fromV, toV, weight));
	}


	/**
	 * Elimina un v�rtice del grafo
	 * @param id llave del v�rtice
	 * @param info informaci�n del v�rtice
	 */
	public void deleteVertex(K id) throws NoSuchElementException{
		if( containsVertex(id) ){
			for( int i = 0; i < V; i++ ){
				if( vertices[i] != null && !adj[i].isEmpty() ){
					for( Edge<K> currentEdge : adj[i] ){
						if( currentEdge.either().equals(id) )
							deleteEdge(currentEdge);
						else if(currentEdge.other(currentEdge.either()).equals(id))
							deleteEdge(currentEdge);
					}
				}
			}
			for( int i = positionOf(id); i < V-1; i++){
				vertices[i] = vertices[i+1];
				adj[i] = adj[i+1];
				freeVertexIndex--;
			}
			V--;
		}
		else{
			throw new NoSuchElementException("El v�rtice no se encuentra en el grafo");
		}
		if( V - 1 < capacity / 2 ){
			resize(capacity /2);
		}
	}
	
	public int degree( K key ){
		return adj[positionOf(key)].getSize();
	}
	
	/**
	 * @return iterable con todos los ejes del grafo
	 */
	public Iterable<Edge<K>> edges(){
		Bag<Edge<K>> ans = new Bag<>();
		for( int i = 0; i < V; i++ ){
			if( adj[i] != null ){
				for( Edge<K> currentEdge : adj[i] ){
					if( !ans.contains(currentEdge) )
						ans.addAtEnd(currentEdge);
				}
			}
		}
		return ans;
	}
	
	/**
	 * @return iterable con todos los v�rtices del grafo
	 */
	public Iterable<Vertex<K, V>> vertices(){
		Queue<Vertex<K, V>> ans = new Queue<>();
		for( Vertex<K, V> currentVertex : vertices ){
			if( currentVertex != null )
				ans.enqueue(currentVertex);
		}
		return ans;
	}
	
	/**
	 * Aumenta el tama�o de los arreglos a la nueva capacidad, para que el grafo pueda tener m�s v�rtices
	 * @param newSize Nuevo tama�o de los arreglos
	 */
	@SuppressWarnings("unchecked")
	private void resize( int newSize ){
		Bag<Edge<K>>[] auxAdj = (Bag<Edge<K>>[]) new Bag[newSize];
		Vertex<K, V>[] auxVertices = (Vertex<K, V>[]) new Vertex[newSize];
		for( int i = 0; i < V; i++ ){
			auxAdj[i] = adj[i];
			auxVertices[i] = vertices[i];
		}
		capacity = newSize;
		adj = auxAdj;
		vertices = auxVertices;
	}
	
	/**
	 * M�todo para saber la posici�n en el arreglo de un v�rtice co llave K
	 * @param key llave del v�rtice 
	 * @return posici�n del v�rtice en el arreglo 
	 * @throws NoSuchElementException si el v�rtice no se encuentra (no deber�a lanzarce nunca)
	 */
	private int positionOf( K key ) throws NoSuchElementException{
		for( int i = 0; i < vertices.length; i++ ){
			if( vertices[i].getKey().equals(key) )
				return i;
		}
		throw new NoSuchElementException( "El v�rtice con la llave " + key + " no se encuentra en el grafo" );
	}
	
	/**
     * Returns a string representation of the edge-weighted graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge<K> e : adj[v]) {
                s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
