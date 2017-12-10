package test.src.data_structures;

import junit.framework.TestCase;
import model.data_structures.MaxBinaryHeap;

public class MaxBinaryHeapTest extends TestCase {
	
	private MaxBinaryHeap<Double> maxHeap = new MaxBinaryHeap<>(5);
	
	public void setup1(){
		
		for( int i = 0; i < 200; i++){
			maxHeap.add( Math.random() * 10);
		}
	}
	
	public void setup2(){
		for( int i = 0; i < 200; i++){
			double toAdd = i + 0.5;
			maxHeap.add(toAdd);
		}
	}
	
	public void testIsMaxHeap(){
		setup2();
		assertTrue(maxHeap.isMaxHeap());
	}
	
	public void testContains(){
		assertFalse("El heap no deber�a tener el elemento.", maxHeap.contains(10.5));
		setup2();
		assertTrue("El heap deber�a tener el elemento", maxHeap.contains(10.5));
		assertFalse("El heap no deber�a tener el elemento", maxHeap.contains(205.5));
	}
	
	public void testSize(){
		assertEquals("El tama�o no es 0", 0, maxHeap.size());
		setup1();
		assertEquals( "El tama�o no es el esperado", 200, maxHeap.size());
	}
	
	public void testAdd(){
		
		setup1();

		Double[] copy = new Double[maxHeap.size()];
		int heapSize = maxHeap.size();
		for( int i = 1; i < heapSize; i++){
			copy[i] = maxHeap.remove();
		}

		for( int i = 1; i < copy.length; i++ ){
			int leftChildIndex = 2*i;
			int rightChildIndex = ( 2 * i ) + 1;
			if( leftChildIndex < copy.length )
				assertTrue( copy[i].compareTo(copy[leftChildIndex]) > 0 );
			if( rightChildIndex < copy.length )
				assertTrue( copy[i].compareTo(copy[rightChildIndex]) > 0);
		}
	}
	
	public void testRemove(){
		setup1();
		
		Double lastRemoved = maxHeap.remove();
		int heapSize = maxHeap.size();
		for( int i = 1; i < heapSize -1; i++){
			assertTrue( "El �ltimo objeto removido es mayor que el removido antes que �l", lastRemoved.compareTo(maxHeap.remove()) > 0 );
		}
	}
	
	public void testIsEmpty(){
		assertTrue("El heap no est� vac�o", maxHeap.isEmpty());
		setup1();
		assertFalse("El heap no deber�a estar vac�o", maxHeap.isEmpty());
	}
}
