package com.example.diego.inrainbows.data_structures;
import junit.framework.TestCase;
import model.data_structures.SequentialSearch;

public class SequentialSearchTest extends TestCase{
	SequentialSearch<Integer, Double> ss;
	
	public void setup1(){
		ss = new SequentialSearch<Integer, Double>();
	}
	
	public void setup2(){
		ss = new SequentialSearch<Integer, Double>();
		for( int i = 0; i < 100; i++){
			ss.put( 1, i+ 0.5);
		}
	}
	
	public void setup3(){
		ss = new SequentialSearch<Integer, Double>();
		for( int i = 0; i < 100; i+=2){
			ss.put(i, i+0.5);
		}
	}
	
	public void testPut(){
		setup2();
		ss.put(1, 100.5);
		assertEquals("El valor no es el esperado", ss.get(1), 100.5);
		
		ss.put(1, 100.6);
		assertEquals("El valor no es el esperado", ss.get(1), 100.6);
		
		ss.put(100, null);
		assertTrue("La lista deber�a estar vac�a", ss.isEmpty());
	}
	
	public void testSize(){
		setup2();
		assertEquals("El tama�o no es el esperado", 100, ss.size());
	}
	
	public void testIsempty(){
		setup1();
		assertTrue("Deber�a estar vac�o", ss.isEmpty());
	}
	
	public void testGet(){
		setup2();
		for( int i = 99; i <= 0; i--){
			assertEquals("El valor no es el esperado", i+0.5, ss.get(i));
		}
	}
	
	public void testContains(){
		setup3();
		for( int i = 0; i < 100; i++){
			if( i % 2 == 0)
				assertTrue("Deber�a retornar true", ss.contains(i));
			else
				assertFalse("Deber�a retornar false", ss.contains(i));
		}
	}
}
