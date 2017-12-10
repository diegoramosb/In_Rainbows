package test.src.data_structures;

import junit.framework.TestCase;
import model.data_structures.Queue;
import model.data_structures.RedBlackBST;

public class RedBlackBSTTest extends TestCase {
	
	private RedBlackBST<Double, Double> bst = new RedBlackBST<>();
	
	public void setup1(){
		for( int i = 0; i < 100; i++){
			Double toAdd = Math.random() * 100;
			bst.put(toAdd, toAdd);
		}
	}
	
	public void setup2(){
		for( int i = 0; i < 100; i++){
			Double toAdd = i + 0.5;
			bst.put(toAdd, toAdd);
		}
	}
	
	public void testSize(){
		assertEquals("El tama�o no es el esperado", 0, bst.size());
		setup1();
		assertEquals("El tama�o no es el esperado", 100, bst.size());
	}
	
	public void testIsEmpty(){
		assertTrue("El �rbol deber�a estar vac�o", bst.isEmpty());
		setup1();
		assertFalse("El �rbol no deber�a estar vac�o", bst.isEmpty());
	}
	
	public void testGet(){
		setup2();
		for( int i = 99; i >= 0; i--){
			assertEquals("El valor no es el esperado", i+0.5, bst.get(i+0.5));
		}
		for( int i = 0; i < 100; i++){
			assertEquals("El valor no es el esperado", i+0.5, bst.get(i+0.5));
		}
	}
	
	public void testContains(){
		setup2();
		assertFalse("No deber�a contener el valor", bst.contains(100.5));
		for( int i = 0; i < 100; i++){
			assertTrue("Deber�a contener el valor", bst.contains(i+0.5));
		}
	}
	
	public void testMin(){
		for( int i = 99; i > 0; i--){
			Double toAdd = i+0.5;
			bst.put(toAdd, toAdd);
			assertEquals(toAdd, bst.min());
		}
	}
	
	public void testMax(){
		for( int i = 0; i < 100; i++){
			Double toAdd = i+0.5;
			bst.put(toAdd, toAdd);
			assertEquals(toAdd, bst.max());
		}
	}
	
	public void testPut(){
		for( int i = 0; i < 100; i++){
			Double toAdd = Math.random() * 100;
			bst.put(toAdd, toAdd);
			assertTrue("Debi� agregar el nodo", bst.contains(toAdd));
		}
	}
	
	public void testHeight(){
		setup1();
		
		// c�lculo de log base 2 tomado de: https://stackoverflow.com/questions/3305059/how-do-you-calculate-log-base-2-in-java-for-integers
		// Seg�n "Algorithms, la altura de un �rbol rojo-negro es como m�ximo 2log2(N)"
		assertTrue("La altura del �rbol no es la esperada", bst.height() < 2 * (Math.log(bst.size()) / Math.log(2)) );
	}
	
	public void testKeys(){
		setup1();
		Queue<Double> keys = bst.keys();
		assertEquals("El tama�o no es el esperado", bst.size(), keys.getSize());
	}
}
