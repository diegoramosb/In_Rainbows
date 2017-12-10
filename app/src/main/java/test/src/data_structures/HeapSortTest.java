package test.src.data_structures;

import junit.framework.TestCase;
import model.data_structures.HeapSort;

public class HeapSortTest extends TestCase {

	private Double[] testArray= new Double[100];
	
	public void setup(){

		int range = 10;
		for( int i = 0; i < 100; i++){
			testArray[i] = new Double( Math.random() * range);
		}
	}
	
	public void testSort(){
		setup();

		HeapSort.sort(testArray);

		boolean isOrdered = true;
			for( int i = 1; i < testArray.length; i++){
				if( testArray[i].compareTo(testArray[i-1] ) < 0 )
					isOrdered = false;
			}
			
		assertEquals("El tama�o no es el esperado", 100, testArray.length);
		assertTrue( "La lista no est� ordenada", isOrdered);
	}
}
