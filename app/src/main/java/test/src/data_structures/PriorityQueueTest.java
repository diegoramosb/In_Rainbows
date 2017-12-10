package test.src.data_structures;

import junit.framework.TestCase;
import model.data_structures.MaxPriorityQueue;

/**
 * Pruebas de la cola de prioridad.
 */
public class PriorityQueueTest extends TestCase{
	
	/**
	 * Cola de prioridad para las pruebas.
	 */
	private MaxPriorityQueue<Double> pq;
	
	/**
	 * Crea una nueva cola de prioridad.
	 */
	public void setup(){
		pq = new MaxPriorityQueue<>(100);
	}
	
	/**
	 * Test del m�todo isEmpty.
	 */
	public void testIsEmpty(){
		setup();
		assertEquals("La cola de prioridad deber�a estar vac�a", true, pq.isEmpty());
	}
	
	/**
	 * Test del m�todo max().
	 */
	public void testMax(){
		setup();
		double max = 0;
		for( int i = 0; i < 200; i++){
			pq.insert(new Double(i + 0.5));
			max = i + 0.5;
		}
		
		assertEquals("El m�ximo no es el esperado", max, pq.max());
	}
	
	/**
	 * Test del m�todo insert.
	 */
	public void testInsert(){
		setup();

		boolean lessThanCurrentMax = true;
		int range = 5;
		for( int i = 0; i < 100; i++){
			pq.insert(new Double(Math.random() * range));
		}
		for( int i = 0; i < 100; i++){
			Double currentMax = pq.delMax();
			for( Double element : pq){
				if( element > currentMax )
					lessThanCurrentMax = false;
					break;
			}
		}
		assertTrue("Deber�a ser true", lessThanCurrentMax);
	}
	
	/**
	 * Test del m�todo delMax.
	 */
	public void testDelMax(){
		setup();
		int range = 5;
		for( int i = 0; i < 100; i++){
			pq.insert(new Double(Math.random() * range));
		}
		Double max = pq.delMax();
		boolean lessThanMax = true;
		for( Double element : pq){
			if( element > max)
				lessThanMax = false;
		}
		assertTrue("Deber�a ser true", lessThanMax);
	}
}
