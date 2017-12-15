package com.example.diego.inrainbows.data_structures;

import junit.framework.TestCase;
import model.data_structures.LinearProbingHash;

public class LinearProbingHashTest extends TestCase 
{
	LinearProbingHash <Integer, Double> LPH;
	public void setup1(){
		LPH = new LinearProbingHash();
	}

	public void setup2(){
		LPH = new LinearProbingHash<Integer, Double>(100);
		for( int i = 0; i < 100; i++){
			LPH.put( i, i+ 0.5);
		}
	}

	public void setup3(){
		LPH = new LinearProbingHash<Integer, Double>(100);
		for( int i = 0; i < 100; i+=2){
			LPH.put(i, i+0.5);
		}
	}

	public void testPut(){
		setup1();
		LPH.put(100, 100.5);
		assertEquals("El valor no es el esperado", LPH.get(100), 100.5);

		LPH.put(100, 100.6);
		assertEquals("El valor no es el esperado", LPH.get(100), 100.6);

		LPH.put(100, null);
		assertTrue("La lista deber�a estar vac�a", LPH.isEmpty());
	}

	public void testSize(){
		setup2();
		assertEquals("El tama�o no es el esperado", 100, LPH.size());
	}

	public void testIsempty(){
		setup1();
		assertTrue("Deber�a estar vac�o", LPH.isEmpty());
	}

	public void testGet(){
		setup2();
		for( int i = 99; i <= 0; i--){
			assertEquals("El valor no es el esperado", i+0.5, LPH.get(i));
		}
	}

	public void testContains(){
		setup3();
		for( int i = 0; i < 100; i++){
			if( i % 2 == 0)
				assertTrue("Deber�a retornar true", LPH.contains(i));
			else
				assertFalse("Deber�a retornar false", LPH.contains(i));
		}
	}

}
