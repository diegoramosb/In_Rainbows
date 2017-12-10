package test.src.data_structures;

import java.util.NoSuchElementException;

import junit.framework.TestCase;
import model.data_structures.DirectedGraph;
import model.data_structures.BFSPaths;
import model.data_structures.DirectedEdge;
import model.data_structures.Vertex;

public class BFSPathsTest extends TestCase {
	private DirectedGraph<Integer, Integer> graph;

	private BFSPaths<Integer, Integer> BFSPaths;

	public void setup1(){
		graph = new DirectedGraph<>(100);
		for( int i = 0; i < graph.capacity(); i++ ){
			graph.addVertex(i, i);
		}
		for( int i = 0; i < graph.capacity() - 1; i++ ){
			graph.addEdge(i, i+1, 10.0);
		}
	}

	public void setup2(){
		graph = new DirectedGraph<>(100);
		for( int i = 0; i < graph.capacity(); i++){
			graph.addVertex(i, i);
		}
		for( int i = 2; i < graph.capacity() - 1; i = i + 2 ){
			if( graph.containsVertex(i+2)){
				graph.addEdge(i, i+2, 10.0);
			}
		}
		graph.addEdge(0, 2, 0.5);
		graph.addEdge(98, 0, 98.5);
		for( int i = 1; i < graph.capacity() - 1; i = i + 2 ){
			if( graph.containsVertex(i+2)){
				graph.addEdge(i, i+2, 10.0);
			}
		}	
		graph.addEdge(99, 1, 99.5);
	}

	public void testPathsOneSource(){
		setup1();
		try{
			BFSPaths = new BFSPaths<>(graph, graph.getVertex(0));
		}catch( Exception e){
			fail( "No deber�a lanzar excepci�n" );
		}
	}

	public void testPathsMultipleSources(){

		setup1();
		try{
			BFSPaths = new BFSPaths<>(graph, graph.vertices());
		}catch( Exception e){
			fail( "No deber�a lanzar excepci�n" );
		}
	}


	public void testHasPathTo(){
		setup1();
		//Probando que los v�rtices no tengan caminos "hacia atr�s"
		BFSPaths = new BFSPaths<>(graph, graph.vertices());
		for( Vertex<Integer, Integer> currentVertex : graph.vertices()){
			BFSPaths = new BFSPaths<>(graph, currentVertex);
			int currentKey = currentVertex.getKey();
			for( int i = 1; i < graph.V(); i++){
				int previousKey = currentKey - i;
				if( graph.containsVertex(previousKey) ){
					Vertex<Integer, Integer> currentVertex2 = graph.getVertex(currentKey - i);
					assertFalse( "El v�rtice " + currentVertex + " no deber�a tener un camino hasta " + currentVertex2, BFSPaths.hasPathTo(currentVertex2));
				}
			}
		}

		//Probando que haya un camino para llegar a todos los v�rtices.
		graph.addEdge(99, 0, 99.0);
		BFSPaths = new BFSPaths<>(graph, graph.vertices());
		for( Vertex<Integer, Integer> currentVertex : graph.vertices()){
			assertTrue("Todos los nodos del grafo deber�an tener un camino para llegar a los dem�s", BFSPaths.hasPathTo(currentVertex));
		}

		//Probando que los v�rtices pares est�n conectando s�lo a los v�rtices pares y los impares est�n conectados s�lo a los impares.
		setup2();
		for( Vertex<Integer, Integer> currentVertex : graph.vertices() ){
			BFSPaths = new BFSPaths<>(graph, currentVertex);
			if( currentVertex.getKey() % 2 == 0 ){
				for( Vertex<Integer, Integer> currentVertex2 : graph.vertices()){
					if( currentVertex2.getKey() % 2 == 0 ){
						assertTrue( "El v�rtice " + currentVertex + " deber�a tener un camino hasta " + currentVertex2, BFSPaths.hasPathTo(currentVertex2));
					}
					else{
						assertFalse( "El v�rtice " + currentVertex + " no deber�a tener un camino hasta " + currentVertex2, BFSPaths.hasPathTo(currentVertex2));
					}
				}
			}
			else{
				for( Vertex<Integer, Integer> currentVertex2 : graph.vertices()){
					if( currentVertex2.getKey() % 2 == 0 ){
						assertFalse( "El v�rtice " + currentVertex + " deber�a tener un camino hasta " + currentVertex2, BFSPaths.hasPathTo(currentVertex2));
					}
					else{
						assertTrue( "El v�rtice " + currentVertex + " no deber�a tener un camino hasta " + currentVertex2, BFSPaths.hasPathTo(currentVertex2));
					}
				}
			}
		}
	}

	public void testDistTo(){
		setup1();
		for( Vertex<Integer, Integer> currentVertex : graph.vertices() ){
			BFSPaths = new BFSPaths<>(graph, currentVertex);
			for( Vertex<Integer, Integer> currentVertex2 : graph.vertices() ){
				try{
					double expectedDist = (currentVertex2.getKey() - currentVertex.getKey()) * 10.0;
					assertEquals("La distancia desde " + currentVertex + " a " + currentVertex2 + " no es la esperada", expectedDist, BFSPaths.distTo(currentVertex2));
				}catch( NoSuchElementException e){
					
				}
			}
		}
	}

	public void testPathTo(){
		setup1();

		BFSPaths = new BFSPaths<>(graph, graph.getVertex(0));
		for( Vertex<Integer, Integer> currentVertex2 : graph.vertices() ){
			if( !BFSPaths.hasPathTo(currentVertex2) ){
				assertNull("No deber�a haber un camino desde" + graph.getVertex(0) + " a " + currentVertex2, BFSPaths.pathTo(currentVertex2));
			}
			else{
				int i = 1;
				for( DirectedEdge<Integer> currentEdgeInPath : BFSPaths.pathTo(currentVertex2)){
					assertEquals("El v�rtice de origen no es el esperado", new Integer(i-1), currentEdgeInPath.fromV());
					assertEquals("El v�rtice de destino no es el esperado", new Integer(i), currentEdgeInPath.toV());
					assertEquals("El peso no es el esperado", 10.0, currentEdgeInPath.weight());

					i++;
				}
			}
		}
	}
}
