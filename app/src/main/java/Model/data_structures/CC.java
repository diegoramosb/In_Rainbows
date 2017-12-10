package Model.data_structures;

import java.util.NoSuchElementException;

/**
 * Clase para encontrar los componentes conexos de un digrafo
 * Implementaci�n basada en: https://algs4.cs.princeton.edu/41graph/CC.java.html
 *
 * @param <K> Tipo de llave de los v�rtices
 * @param <V  Tipo de valores de los v�rtices
 */
public class CC<K, V> {

	/**
	 * Arreglo que en cada posici�n tiene un boolean para indicar cuando se marca un v�rtice en DFS.
	 */
	private boolean marked[];
	
	/**
	 * Arreglo con el id del componente conexo al que pertenece el v�rtice correspondiente a la posici�n.
	 */
	private int id[];
	
	/**
	 * Tama�o del componente conexo al que pertenece el v�rtice correspondiene a la posici�n.
	 */
	private int size[];
	
	/**
	 * N�mero de componentes conxos del grafo
	 */
	private int count;
	
	/**
	 * Copia de los v�rtices del grafo
	 */
	private Vertex<K, V> vertices[];
	
	/**
	 * Identifica los componentes conexos del grafo, su cantidad y su tama�o
	 * @param graph grafo sobre el que se quieren encontrar los componentes conexos
	 */
	@SuppressWarnings("unchecked")
	public CC( DirectedGraph<K, V> graph){
		int verticesNo = graph.V();
		marked = new boolean[verticesNo];
		id = new int[verticesNo];
		size = new int[verticesNo];
		vertices = (Vertex<K, V>[]) new Vertex[verticesNo];
		copyVertices(graph);
		for( int i = 0; i < verticesNo; i++ ){
			if( !marked[i] ){
				Dfs( graph, vertices[i] );
				count++;
			}
		}
	}
	
	/**
	 * Halla los componentes conexos usando DFS.
	 * @param graph grafo sobre el que se quieren hallar sus componentes conexos
	 * @param vertex v�rtice desde el cual comienza DFS.
	 */
	private void Dfs( DirectedGraph<K, V> graph, Vertex<K, V> vertex ){
		int positionOfVertex = positionOf(vertex);
		marked[positionOfVertex] = true;
		id[positionOfVertex] = count;
		size[count]++;
		for( DirectedEdge<K> currentEdge : graph.adj(vertex.getKey())){
			Vertex<K, V> toVertex = graph.getVertex(currentEdge.toV());
			if( !marked[positionOf(toVertex)] ){
				Dfs( graph, toVertex );
			}
		}
	}
	
	/**
	 * @param vertexKey llave del v�rtice del cu�l se quiere saber el id del componente conexo en el que se encuentra
	 * @return id del componente conexo al que pertenece el v�rtice cuya llave fue ingresada por par�metro
	 * @throws NoSuchElementException si el v�rtice no est� en ning�n componente del grafo. Es decir, si no est� en el grafo
	 */
	public int id( K vertexKey ) throws NoSuchElementException{
		for( int i = 0; i < vertices.length; i++){
			if( vertices[i].getKey().equals(vertexKey) )
				return id[i];
		}
		throw new NoSuchElementException( "El v�rtice con llave " + vertexKey + " no est� en ning�n componente conexo del grafo"  );
	}
	
	/**
	 * @param vertexKey  llave del v�rtice del cu�l se quiere saber el tama�o del componente conexo en el que se encuentra
	 * @return tama�o del componente conexo al que pertenece el v�rtice cuya llave fue ingresada por par�metro
	 * @throws @throws NoSuchElementException si el v�rtice no est� en ning�n componente del grafo. Es decir, si no est� en el grafo
	 */
	public int size( K vertexKey ) throws NoSuchElementException{
		for( int i = 0; i < vertices.length; i++){
			if( vertices[i].getKey().equals(vertexKey) )
				return size[id[i]];
		}
		throw new NoSuchElementException( "El v�rtice con llave " + vertexKey + "no est� en ning�n componente conexo del grafo"  );
	}
	
	/**
	 * @return cantidad de componentes conexos en el grafo.
	 */
	public int count(){
		return count;
	}
	
	/**
	 * @param vertexKey1 llave del primer v�rtice
	 * @param vertexKey2 llave del segundo v�rtice
	 * @return true si los dos v�rtices con llaves ingresadas por par�metro est�n en el mismo componente conexo, false de lo contrario.
	 */
	public boolean connected( K vertexKey1, K vertexKey2 ){
		return id( vertexKey1 ) == id( vertexKey2 );
	}
	
	
	
	/**
	 * Copia los v�rtices del grafo en el arreglo v�rtices
	 * @param graph Grafo del cual se quieren copiar los v�rtices
	 */
	private void copyVertices( DirectedGraph<K, V> graph ){
		int index = 0;
		for( Vertex<K, V> currentVertex : graph.vertices()){
			vertices[index] = currentVertex;
			index++;
		}
	}
	
	/**
	 * Indica la posici�n de la informaci�n del v�rtice en marked, edgeTo, distTo de acuerdo a vertices
	 * @param vertex v�rtice del cual se quiere saber su posici�n
	 * @return posici�n de la informaci�n del v�rtice en todos los arreglos
	 * @throws NoSuchElementException si no encuentra el v�rtice, nunca deber�a lanzar excepci�n
	 */
	private int positionOf( Vertex<K, V> vertex ) throws NoSuchElementException{
		K key = vertex.getKey();
		for( int i = 0; i < vertices.length; i++ ){
			if( vertices[i].getKey().equals(key) )
				return i;
		}
		throw new NoSuchElementException( "El v�rtice no fue encontrado" ); 
	}
}
