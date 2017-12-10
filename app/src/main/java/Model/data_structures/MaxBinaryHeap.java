package Model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Implementaci�n de un heap binario orientado al m�ximo
 * Basado en la implementaci�n de: https://courses.cs.washington.edu/courses/cse373/11wi/homework/5/BinaryHeap.java
 * @param <T> Tipo de datos que va a almacenar el heap.
 */
public class MaxBinaryHeap<T extends Comparable<T>> implements Iterable<T>{

	private T[] array;

	private int size;

	@SuppressWarnings("unchecked")
	public MaxBinaryHeap( int initCapacity ){
		array = (T[]) new Comparable[initCapacity + 1];
		size = 0;
	}

	public int size(){
		return size;
	}

	public void add( T pValue ){
		if( size >= array.length - 1)
			resize( 2 * size );

		size++;
		int index = size;
		array[index] = pValue;

		swim();
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public boolean contains( T pElement ){
		for( T element : this ){
			if( element.equals(pElement) )
				return true;
		}
		return false;
	}

	public boolean isMaxHeap() {
		return isMaxHeap(1);
	}

	/**
	 * Revisa si el sub heap con raiz en K es un heap.
	 * @param k Posici�n de la raiz del sub heap.
	 * @return true si el sub heap es un heap, false de lo contrario.
	 */
	private boolean isMaxHeap(int k) {
		if (k > size) 
			return true;
		if (leftIndex(k)  <= size && array[k].compareTo(array[leftIndex(k)]) < 0)  
			return false;
		if (rightIndex(k) <= size && array[k].compareTo(array[rightIndex(k)]) < 0) 
			return false;
		return isMaxHeap(leftIndex(k)) && isMaxHeap(rightIndex(k));
	}

	public T peek(){
		if( isEmpty() )
			throw new IllegalStateException( "El heap est� vac�o" );
		return array[1];
	}

	public T remove(){
		T result = peek();

		array[1] = array[size];
		array[size] = null;
		size--;

		sink();
		return result;
	}

	private void sink(){
		int index = 1;
		while( hasLeftChild(index) ){
			int smallerChild = leftIndex(index);

			if( hasRightChild(index) && array[leftIndex(index)].compareTo(array[rightIndex(index)]) < 0)
				smallerChild = rightIndex(index);

			if( array[index].compareTo(array[smallerChild]) < 0 )
				swap( index, smallerChild);
			else
				break;

			index = smallerChild;
		}
	}

	private void swim(){
		int index = size;
		while( hasParent(index) ){
			if( (array[index].compareTo(parent(index)) > 0) )
				swap(parentIndex(index), index );
			index = parentIndex(index);
		}
	}

	private boolean hasParent(int index){
		return index > 1 && array[parentIndex(index)] != null;
	}

	private int leftIndex( int index){
		return  index * 2; 
	}

	private int rightIndex( int index ){
		return ( index * 2 ) + 1;
	}

	private boolean hasLeftChild( int index ){
		return leftIndex( index ) <= size;
	}

	private boolean hasRightChild( int index ){
		return rightIndex(index) <= size; 
	}

	private T parent( int index ){
		return array[parentIndex(index)];
	}

	private int parentIndex( int index){
		return (index / 2);
	}

	@SuppressWarnings("unchecked")
	private void resize( int capacity ){
		assert capacity > size;
		T[] temp = (T[]) new Comparable[capacity];
		for( int i = 1; i <= size; i++){
			temp[i] = array[i];
		}

		array = temp;
	}

	public String toString(){
		String ans = "[ ";
		for( T element : this ){
			ans = ans + " " + element;
		}
		ans = ans + " ]";
		return ans;
	}

	private void swap( int pIndex1, int pIndex2 ){
		T temp = array[pIndex1];
		array[pIndex1] = array[pIndex2];
		array[pIndex2] = temp;
	}

	/**
	 * Iterador sobre el arreglo
	 * Tomado de: http://www.java2s.com/Tutorial/Java/0140__Collections/ImplementsanjavautilIteratoroveranyarray.htm
	 */
	private class maxHeapIterator implements Iterator<T>{

		private int endIndex = size;

		private int index = 1;


		@Override
		public boolean hasNext(){
			return (index <= endIndex);
		}

		@Override
		public T next() {
			if( !hasNext() )
				throw new NoSuchElementException();
			return array[index++];
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new maxHeapIterator();
	}
}
