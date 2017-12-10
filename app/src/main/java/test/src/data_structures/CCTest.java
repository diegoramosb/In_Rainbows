package test.src.data_structures;

import junit.framework.TestCase;
import model.data_structures.CC;
import model.data_structures.DirectedGraph;
import model.data_structures.Vertex;

public class CCTest extends TestCase {
	
	private DirectedGraph<Integer, Integer> graph;
	
	private CC<Integer, Integer> cc;
	
	private int modulo = 8;
	
	private int tamanioComponentes = 80;
	
	//Crea un grafo con un n�mero de componentes conexos igual a m�dulo
	public void setup1(){
		
		graph = new DirectedGraph<>(modulo*tamanioComponentes);
		for( int i = 0; i < graph.capacity(); i++ ){
			graph.addVertex(i, i);
		}
		
		for( Vertex<Integer, Integer> currentVertex : graph.vertices()){
			int currentVertexModulo = currentVertex.getKey() % modulo;
			for( Vertex<Integer, Integer> currentVertex2 : graph.vertices()){
				int currentVertex2Modulo = currentVertex2.getKey() % modulo;
				if( currentVertex2Modulo == currentVertexModulo && (!((currentVertex.getKey()).equals(currentVertex2.getKey())))){
					graph.addEdge(currentVertex.getKey(), currentVertex2.getKey(), currentVertex2Modulo);
				}
			}
		}
	}
	
	public void testCC(){
		setup1();
		
		try{
			cc = new CC<>(graph);
		}catch( Exception e){
			fail( "No deber�a fallar" );
		}
		try{
			cc = new CC<>(null);
			fail( "Deber�a fallar" );
		}catch( Exception e){
			
		}
	}
	
	public void testId(){
		setup1();
		cc = new CC<>(graph);
		for( Vertex<Integer, Integer> currentVertex : graph.vertices() ){
			assertEquals("El v�rtice no est� en el componente conexo esperado", currentVertex.getKey() % modulo, cc.id(currentVertex.getKey()));
		}
	}
	
	public void testSize(){
		setup1();
		cc = new CC<>(graph);
		for( Vertex<Integer, Integer> currentVertex : graph.vertices() ){
			assertEquals("El tama�o del componente conexo no es el esperado", tamanioComponentes, cc.size(currentVertex.getKey()));
		}
	}
	
	public void testCount(){
		setup1();
		cc = new CC<>(graph);
		assertEquals("El n�mero de componentes conexos no es el esperado",modulo, cc.count());
	}
	
	public void testConnected(){
		setup1();
		cc = new CC<>(graph);
		
		for( Vertex<Integer, Integer> currentVertex : graph.vertices()){
			int currentVertexModulo = currentVertex.getKey() % modulo;
			for( Vertex<Integer, Integer> currentVertex2 : graph.vertices()){
				int currentVertex2Modulo = currentVertex2.getKey() % modulo;
				if( currentVertex2Modulo == currentVertexModulo && (!((currentVertex.getKey()).equals(currentVertex2.getKey())))){
					assertTrue("Deber�an estar conectados", cc.connected(currentVertex.getKey(), currentVertex2.getKey()));
				}
			}
		}
	}
}
