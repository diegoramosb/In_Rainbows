

package com.example.diego.inrainbows.data_structures;

import java.util.NoSuchElementException;

import junit.framework.TestCase;
import model.data_structures.DoublyLinkedList;


/**
 * Clase de pruebas para DoubleLinkList.Java
 * @author da.ramos,hd.castellanos
 *
 */
public class DoublyLinkedListTest extends TestCase {

	/**
	 * Atributo que permite probar la clase DoublyLinkedList
	 */
	private DoublyLinkedList<Double> list;

	/**
	 * Crea un escenario con la nueva lista y los 3 nodos
	 */
	public void setup1(){
		try{
			list = new DoublyLinkedList<>();
		} catch( Exception e){
			fail( "No deber�a lanzar excepci�n" );
		}
	}

	/**
	 * Prueba que se cree la lista vacia
	 */
	public void testDoubleLinkedList(){
		setup1();
		assertNotNull("La lista debi� haber sido creada", list);
	}

	/**
	 * Prueba el metodo get size de la clase DoublyLinkedList
	 */
	public void testGetSize(){
		setup1();

		for( int i = 0; i < 100; i++){
			int range =  5 ;     
			list.addAtEnd(new Double((Math.random() * range)));
		}
		assertEquals("El tama�o no es el esperado", 100, list.getSize());
	}

	/**
	 * Prueba el metodo isEmpty() de la clase DoublyLinkedList
	 */
	public void testIsEmpty(){
		setup1();
		assertTrue("La lista deber�a estar vac�a", list.isEmpty());
		list.addAtEnd(new Double(3.4));
		assertFalse("La lista no deber�a estar vac�a", list.isEmpty());
	}
	
	/**
	 * Prueba el m�todo isOrdered() de la clase DoublyLinkedList.
	 */
	public void testIsOrdered(){
		setup1();
		int range =  10 ;     
		for( int i = 0; i < 100; i++){
			list.addAtEnd(new Double((Math.random() * range)));
		}
		assertFalse("La lista no deber�a estar ordenada", list.isOrdered());
		setup1();
		for( int i = 0; i < 100; i++){
			list.addAtEnd(new Double(i + 0.5));
		}
		assertTrue("La lista deber�a estar ordenada", list.isOrdered());
	}

	/**
	 * Prueba el metodo addAtEnd() de la clase DoublyLinkedList
	 */
	public void testAddAtEnd(){
		setup1();

		int range = 4;
		Double random = new Double( Math.random() * range);
		list.addAtEnd(random);
		assertEquals("El �limo nodo no es el esperado", random, list.getLast());
		random = new Double( Math.random() * range);
		list.addAtEnd(random);
		assertEquals( "El �ltimo nodo no es el esperado", random, list.getLast());
	}


	/**
	 * Prueba el metodo addAtK() de la clase DoublyLinkedList
	 * y verifica que el nodo se agregue correctamente en el indice deseado
	 */
	public void testAddAtK(){
		setup1();

		try{
			Double toAdd = new Double( 5.555 );
			list.addAtK(toAdd, 2);
			fail( "No deber�a agregar el nodo" );
		} catch( NoSuchElementException e){
			assertTrue("No se agreg� el nodo", list.isEmpty());
		}

		try{
			list.addAtEnd(5.555);
			list.addAtEnd(5.555);
			list.addAtK(3.5, 3);
			fail( "No deber�a agregar el nodo" );
		} catch( NoSuchElementException e){
			assertEquals( "No se agreg� el nodo", 2, list.getSize() );
		}
		list = new DoublyLinkedList<Double>();
		list.addAtEnd(5.2);
		list.addAtEnd(443.3);
		list.addAtK(53.2, 2);
		assertEquals( "El primer elemento no es el esperado", 5.2 , list.getFirst() );
		assertEquals( "El segundo elemento no es el esperado", 443.3 , list.getElement(1) );
		assertEquals( "El tercer elemento no es el esperado", 53.2, list.getLast() );


		list = new DoublyLinkedList<Double>();
		list.addAtEnd(2.3);
		list.addAtK(1.2, 1);
		assertEquals( "El primer elemento no es el esperado", 2.3, list.getFirst() );
		assertEquals( "El segundo elemento no es el esperado", 1.2, list.getElement(1) );

		list = new DoublyLinkedList<Double>();
		list.addAtEnd(1.5);
		list.addAtEnd(3.36);
		list.addAtK(2.35, 0);
		assertEquals( "El primer elemento no es el esperado", 2.35, list.getFirst() );
		assertEquals( "El segundo elemento no es el esperado", 1.5, list.getElement(1) );
		assertEquals( "El tercer elemento no es el esperado", 3.36, list.getLast() );
	}

	/**
	 * Prueba el metodo getElement() de la clase DoublyLinkedList
	 * y comprueba que recupere el nodo que se encuentra en el indice 
	 * dado por parametro
	 */
	public void testGetElement(){
		setup1();

		assertNull( "Deber�a ser null", list.getElement(1) );

		Double[] doubles = new Double[100];
		int range = 10;
		for( int i = 0; i < doubles.length ; i++ ){
			doubles[i] = Math.random() * range;
			list.addAtEnd(doubles[i]);
		}
		int cnt = 0;
		for( Double element : list){
			assertEquals( "El nodo no es el esperado", doubles[cnt], element );
			cnt++;
		}
		assertNull( "Deber�a ser null", list.getElement(doubles.length) );
	}

	/**
	 * prueba el metodo delete() de la clase DoublyLinkedList
	 * y comprueba que se elimine correctamente el nodo deseado
	 */
	public void testDelete(){
		setup1();

		Double[] doubles = new Double[100];
		for( int i = 0; i < doubles.length ; i++ ){
			doubles[i] = i + 0.5;
			list.addAtEnd(doubles[i]);
		}
		
		try{
			list.delete( 100.5 );
			fail( "No deber�a eliminar el nodo" );
		} catch( NoSuchElementException e){
			assertEquals( "El tama�o de la lista no es el esperado", doubles.length, list.getSize() );
		}

		
		list.delete(doubles[doubles.length-1] );
		assertEquals( "El �ltimo nodo no es el esperado", doubles[98], list.getLast() );
		assertEquals( "El tama�o de la lista no es el esperado", doubles.length - 1, list.getSize());

		
		list.delete(doubles[60]);
		assertEquals( "El nodo no es el esperado", doubles[61], list.getElement(60) );
		assertEquals( "El tama�o de la lista no es el esperado", doubles.length - 2, list.getSize());
		
		list.delete(doubles[0]);
		assertEquals( "El primer nodo no es el esperado", doubles[1], list.getFirst() );
		assertEquals( "El tama�o de la lista no es el esperado", doubles.length - 3, list.getSize());

	}

	/**
	 * prueba el metodo deleteAtK() de la clase DoublyLinkedList
	 * y comprueba que se elimine correctamente el nodo que se encuentra en 
	 * el indice que entra por parametro
	 */
	public void testDeleteAtK(){
		setup1();

		Double[] doubles = new Double[100];
		for( int i = 0; i < doubles.length ; i++ ){
			doubles[i] = i + 0.5;
			list.addAtEnd(doubles[i]);
		}

		try{
			list.deleteAtK(doubles.length);
			fail( "Deber�a lanzar excepci�n" );
		} catch( NoSuchElementException e){
			assertEquals( "El tama�o de la lista no es el esperado", doubles.length, list.getSize() );
		}

		list.deleteAtK(doubles.length - 1);
		assertEquals( "El nodo no es el esperado", doubles[doubles.length - 2], list.getLast() );
		assertEquals( "El tama�o de la lista no es el esperado", doubles.length -1, list.getSize());


		list.deleteAtK(50);
		assertEquals( "El nodo no es el esperado", doubles[51], list.getElement(50));
		assertEquals( "El tama�o de la lista no es el esperado", doubles.length -2, list.getSize());
		list.deleteAtK(0);
		assertEquals( "El nodo no es el esperado", doubles[1], list.getFirst() );
		assertEquals( "El tama�o de la lista no es el esperado", doubles.length -3, list.getSize());
	}

	/**
	 * Prueba el metodo postionOf() de la clase DoublyLinkedList
	 * y comprueba que se retorne la posicion del nodo dado por parametro
	 */
	public void testPositionOf(){
		setup1();

		Double[] doubles = new Double[100];
		for( int i = 0; i < doubles.length ; i++ ){
			doubles[i] = i + 0.5;
			list.addAtEnd(doubles[i]);
		}
		
		try{
			list.positionOf(100.5);
			fail( "Deber�a fallar" );
		} catch( NoSuchElementException e ){
			assertEquals("El tama�o de la lista no es el esperado", doubles.length, list.getSize());
		}

		assertEquals( "La posici�n del nodo no es la esperada", 0, list.positionOf(doubles[0]) );
		assertEquals( "La posici�n del nodo no es la esperada", 55, list.positionOf(doubles[55]) );
		assertEquals( "La posici�n del nodo no es la esperada", doubles.length -1, list.positionOf(doubles[doubles.length -1]) );
	}
	
	/**
	 * Prueba el m�todo replace de DoublyLinkedList
	 */
	public void testReplace(){
		setup1();
		
		Double[] doubles = new Double[100];
		for( int i = 0; i < doubles.length ; i++ ){
			doubles[i] = i + 0.5;
			list.addAtEnd(doubles[i]);
		}
		
		list.replace(5.555, 0);
		list.replace(1.111, 60);
		list.replace(4.444, list.getSize() -1);
		
		assertEquals("El nodo no es el esperado", 5.555, list.getFirst());
		assertEquals("El nodo no es el esperado", 1.111, list.getElement(60));
		assertEquals("El nodo no es el esperado", 4.444, list.getLast());
	}
	
	/**
	 * Prueba el m�todo mergeSort() de DoublyLinkedList.
	 */
	public void testMergeSort(){
		setup1();
		int range =  10 ;     
		for( int i = 0; i < 1000; i++){
			list.addAtEnd(new Double((Math.random() * range)));
		}
		list.mergeSort();
		assertTrue("La lista no est� ordenada", list.isOrdered());
	}
}
