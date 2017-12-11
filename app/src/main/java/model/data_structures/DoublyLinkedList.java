package model.data_structures;


import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import api.IList;

/**
 * Implementaci�n de una lista doblemente encadenada con referencias a su primer y �ltimo elemento.
 * Se empiezan a contar los nodos desde 0.
 * Implementaci�n basada en: http://algs4.cs.princeton.edu/13stacks/DoublyLinkedList.java.html
 * @author da.ramos, hd.castellanos
 * @param <T> Tipo de datos que va a manejar la lista.
 */
public class DoublyLinkedList<T extends Comparable<T>> implements IList<T>, Iterable<T>, Comparator<T> {

	/**
	 * Primer nodo de la lista.
	 */
	private Node<T> first;

	/**
	 * �ltimo nodo de la lista.
	 */
	private Node<T> last;

	/**
	 * Tama�o de la lista.
	 */
	private int size;

	private Comparator<T> comp;

	/**
	 * Crea una lista vac�a.
	 */
	public DoublyLinkedList(){
		first = null;
		last = null;
		size = 0;
		comp = new listComparator<>();
	}

	private class listComparator<T extends Comparable<T>> implements Comparator<T>{
		@Override
		public int compare(T o1, T o2) {
			return o1.compareTo(o2);
		}
	}

	/**
	 * Retorna el tama�o de la lista.
	 * @return Tama�o de la lista.
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 * Indica si la lista est� vac�a.
	 * @return true si la lista est� vac�a, false de lo contrario.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Retorna el primer elemento de la lista.
	 * @return Primer elemento de la lista.
	 */
	public T getFirst(){
		return first.data;
	}

	/**
	 * Retorna el �ltimo elemento de la lista.
	 * @return �ltimo elemento de la lista.
	 */
	public T getLast(){
		return last.data;
	}


	/**
	 * Agrega un elemento en el �ltimo lugar de la lista encadenada.
	 * @param pToAdd informaci�n que se va a agregar.
	 */
	@Override
	public void addAtEnd(T pToAdd) {
		insertAfter(last, pToAdd);
	}

	/**
	 * Agrega un elemento el el primer lugar de la lista encadenada.
	 * @param pToAdd Informaci�n que se va a agregar.
	 */
	public void addAtBeginning( T pToAdd ){
		Node<T> toAdd = new Node<>();
		toAdd.data = pToAdd;

		if( isEmpty()){
			first = toAdd;
			last = toAdd;
		}
		else{
			first.previous = toAdd;
			toAdd.next = first;
			first = toAdd;
		}
	}

	/**
	 * Inserta un nuevo nodo despu�s de otro nodo en la lista.
	 * @param pPrevious Nodo al que se le va a agregar despu�s el nuevo nodo.
	 * @param pData Informaci�n que se va a agregar.
	 */
	private void insertAfter( Node<T> pPrevious, T pData ){
		Node<T> toAdd = new Node<>();
		toAdd.data = pData;

		if( isEmpty()){
			first = toAdd;
			last = toAdd;
		}
		else if( last == pPrevious ){
			pPrevious.next = toAdd;
			toAdd.previous = pPrevious;
			last = toAdd;
		}
		else{
			toAdd.next = pPrevious.next;
			toAdd.previous = pPrevious;
			pPrevious.next.previous = toAdd;
			pPrevious.next = toAdd;
		}
		size++;
	}

	/**
	 * Agrega un nodo en una posici�n de la lista.
	 * @param pToAdd Informaci�n que se va a agregar.
	 * @param pIndex �ndice de la lista donde se va a agregar el nodo.
	 */
	@Override
	public void addAtK(T pToAdd, int pIndex) {
		if( pIndex < size ){
			if( pIndex == 0){
				addAtBeginning(pToAdd);
			}
			else
				insertAfter(getNode(pIndex), pToAdd);
		}
		else if( pIndex == size)
			addAtEnd(pToAdd);
		else
			throw new NoSuchElementException( "El �ndice ingresado no existe" );
	}

	/**
	 * Reemplaza el valor del nodo con el �ndice ingresado por par�metro por la informaci�n ingresada por par�metro.
	 * @param pData Informaci�n que se va a reemplazar en el nodo.
	 * @param pIndex �ndice del nodo en el que se quiere ingresar la informaci�n.
	 */
	public void replace( T pData, int pIndex ){
		Node<T> current = null;
		if( !isEmpty()){
			current = first;
			for( int i = 0; i < pIndex; i++){
				current = current.next;
			}
			current.data = pData;
		}
		else{
			addAtEnd(pData);
		}
	}


	/**
	 * Retorna el elemento en el �ndice ingresado por par�metro.
	 * Tomado de "A practical guide to data structures and algorithms using Java"
	 * @param pIndex �ndice del elemento.
	 * @return Elemento ubicado en el �ndice que entra por par�metro.
	 */
	@Override
	public T getElement(int pIndex) {
		Node<T> current = null;
		if( !isEmpty() && pIndex < size && pIndex >= 0){
			current = first;
			for( int i = 0; i < pIndex; i++){
				current = current.next;
			}
			return current.data;
		}
		return null;
	}

	/**
	 * Retorna el nodo en el �ndice ingresado por par�metro.
	 * @param pIndex �ndice de la posici�n donde se va a agregar el elemento.
	 * @return Nodo ubicado en el �ndice que entra por par�metro.
	 */
	private Node<T> getNode(int pIndex) {
		Node<T> current = null;
		if( !isEmpty() && pIndex < size && pIndex >= 0){
			current = first;
			for( int i = 0; i < pIndex; i++){
				current = current.next;
			}
			return current;
		}
		return null;
	}

	/**
	 * Elimina un nodo de la lista.
	 * @param pNode elimina el nodo que se le pasa por par�metro.
	 */
	@Override
	public void delete( T pNode ) {
		int position = positionOf( pNode );
		Node<T> toDelete = getNode( position );
		if( toDelete == null ){
			throw new NoSuchElementException( "El nodo no existe." );
		}
		else if( toDelete == first ){
			Node<T> next = getNode( positionOf ( toDelete.data ) + 1 );
			next.previous = null;
			first = next;
			toDelete.next = null;
		}
		else if( toDelete == last ){
			Node<T> previous = getNode( positionOf ( toDelete.data ) - 1 );
			previous.next = null;
			last = previous;
			toDelete.previous = null;
		}
		else{
			Node<T> next = getNode(positionOf(toDelete.data) + 1 );
			Node<T> previous = getNode(positionOf(toDelete.data) - 1 );
			previous.next = next;
			next.previous = previous;
			toDelete.next = null;
			toDelete.previous = null;
		}
		size--;
	}

	/**
	 * Elimina un nodo de la posici�n que entra por par�metro.
	 */
	@Override
	public void deleteAtK(int pIndex) {
		delete(getElement(pIndex));
	}

	/**
	 * Retorna la posici�n del elemento ingresado por par�metro.
	 * M�todo tomado de "A practical guide to algorithms and data structures using java"
	 * @return Posici�n del elemento ingresado por par�metro.
	 */
	@Override
	public int positionOf(T pData) {
		Node<T> current;
		Integer pos;
		for( pos = 0, current = first; pos < size; pos++, current = current.next)
			if( current.data.equals(pData) )
				return pos;

		throw new NoSuchElementException();
	}

	
	public boolean contains( T pData ){
		for( T element : this ){
			if( element.equals(pData))
				return true;
		}
		return false;
	}
	/**
	 * @return true si la lista est� ordenada de menor a mayor, false de lo contrario.
	 */
	public boolean isOrdered(){
		for( int i = 1; i < size; i++){
			if( getElement(i).compareTo(getElement(i-1) ) < 0 )
				return false;
		}
		return true;
	}

	/**
	 * Ordena la lista de menor a mayor usando mergeSort. 
	 * Implementaci�n tomada de "A practical guide to algorithms and data structures using java"
	 */
	public void mergeSort(){
		mergeSort( comp );
	}

	public void mergeSort( Comparator<T> comp){
		if( size > 1){
			first = mergeSortImpl( comp, size, getNode(0));
		}
	}

	protected Node<T> mergeSortImpl( Comparator<T> comp, int n, Node<T> ptr ){
		Node<T> ptrLeft = ptr;
		int numLeft = n/2;
		for( int i = 0; i < numLeft -1; i++)
			ptr = ptr.next;
		Node<T> ptrRight = ptr.next;
		ptr.next = null;
		if( numLeft > 1)
			ptrLeft = mergeSortImpl(comp, numLeft, ptrLeft);
		if( n-numLeft > 1)
			ptrRight = mergeSortImpl(comp, n - numLeft, ptrRight);
		return merge( comp, ptrLeft, ptrRight );
	}


	private Node<T> merge(Comparator<T> comp, Node<T> ptr1, Node<T> ptr2) {
		Node<T> retVal = ptr1;
		if( comp.compare(ptr1.data, ptr2.data) <= 0){
			ptr1 = ptr1.next;
		}
		else{
			retVal = ptr2;
			ptr2 = ptr2.next;
		}
		Node<T> ptr = retVal;
		while( ptr1 != null && ptr2 != null ){
			if( comp.compare(ptr1.data, ptr2.data) <= 0 ){
				if( ptr.next != ptr1)
					ptr.next = ptr1;
				ptr1 = ptr1.next;
			}
			else{
				if( ptr.next != ptr2)
					ptr.next = ptr2;
				ptr2 = ptr2.next;
			}
			ptr = ptr.next;
		}
		if( ptr2 != null)
			ptr.next = ptr2;
		else{
			ptr.next = ptr1;
			while( ptr.next != null)
				ptr = ptr.next;
			last = ptr;
			last.next = null;
		}
		return retVal;
	}

	public void heapSort( Comparator<T> comp ){
		//		heapSortImpl(getSorter(comp));
	}

	/**
	 * Imprime en consola la informacion de los nodos de la lista
	 */
	public String toString(){
		String ans = "[";
		for( T element : this ){
			ans = ans + element + " " + ",";
		}
		ans = ans + "]";
		return ans;
	}
	
	public Comparable<T>[] toArray(){
		Comparable<T>[] ans = new Comparable[size];
		int i = 0;
		for( T element : this){
			ans[i] = element;
			i++;
		}
		return ans;
	}

	/**
	 * Retorna el iterador de la lista.
	 */
	@Override
	public Iterator<T> iterator() {
		return new DLLIterator();
	} 

	/**
	 * Cada nodo contiene un elemento.
	 * Clase tomada de 
	 * @param <T> Tipo de elemento que tendr� el nodo
	 */
	private class Node<T>{

		/**
		 * Informaci�n contenida por el nodo.
		 */
		private T data;

		/**
		 * Apuntador al siguiente nodo.
		 */
		private Node<T> next;

		/**
		 * Apuntador al nodo anterior.
		 */
		private Node<T> previous;

		/**
		 * Imprime la informaci�n del nodo en la consola.
		 */
		public String toString(){
			return data + ",";
		}
	}

	/**
	 * Iterador de la lista.
	 */
	private class DLLIterator implements Iterator<T>{
		/**
		 * Elemento en el que se encuentra el iterador.
		 * Inicialmente es el primero.
		 */
		private Node<T> current = first;

		/**
		 * �ltimo elemento en el que estuvo el iterador.
		 */
		private Node<T> lastAccessed;

		/**
		 * �ndice del elemento en el que se encuentra el iterador.
		 */
		private int index = 0;


		/**
		 * @return true si el elemento actual tiene un elemento siguiente, false de lo contrario.
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * @return elemento siguiente al actual, si existe.
		 */
		@Override
		public T next() {
			if( !hasNext())
				throw new NoSuchElementException();
			else{
				lastAccessed = current;
				T item = current.data;
				current = current.next;
				index++;
				return item;
			}
		}
	}

	@Override
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}
}
