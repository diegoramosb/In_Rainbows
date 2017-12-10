package test.src.data_structures;

import junit.framework.TestCase;
import Model.data_structures.LinearProbingHash;
import Model.data_structures.SeparateChainingHash;

public class SeparateChainigHashTest extends TestCase {


	SeparateChainingHash <Integer, Double> SCH;
	public void setup1(){
		SCH = new SeparateChainingHash<Integer,Double>();
	}

	public void setup2(){
		SCH = new SeparateChainingHash<Integer, Double>(100);
		for( int i = 0; i < 100; i++){
			SCH.put( i, i+ 0.5);
		}
	}

	public void setup3(){
		SCH = new SeparateChainingHash<Integer, Double>(100);
		for( int i = 0; i < 100; i+=2){
			SCH.put(i, i+0.5);
		}
	}

	public void testPut(){
		setup1();
		SCH.put(100, 100.5);
		assertEquals("El valor no es el esperado", SCH.get(100), 100.5);

		SCH.put(100, 100.6);
		assertEquals("El valor no es el esperado", SCH.get(100), 100.6);

		SCH.put(100, null);
		assertTrue("La lista deber�a estar vac�a", SCH.isEmpty());
	}

	public void testSize(){
		setup2();
		assertEquals("El tama�o no es el esperado", 100, SCH.size());
	}

	public void testIsempty(){
		setup1();
		assertTrue("Deber�a estar vac�o", SCH.isEmpty());
	}

	public void testGet(){
		setup2();
		for( int i = 99; i <= 0; i--){
			assertEquals("El valor no es el esperado", i+0.5, SCH.get(i));
		}
	}

	public void testContains(){
		setup3();
		for( int i = 0; i < 100; i++){
			if( i % 2 == 0)
				assertTrue("Deber�a retornar true", SCH.contains(i));
			else
				assertFalse("Deber�a retornar false", SCH.contains(i));
		}
	}
}
