package model.data_structures;


import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

import api.data_structures.IStack;

public class Stack<E> implements IStack<E>, Comparable<Stack<E>>{


	/**
	 * Atributo para el primer nodo de la pila
	 */
	private Node<E> firstE;

	/**
	 * Atributo para el numero de elementos en la pila.
	 */
	private int sizeS;

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@NonNull
	@Override
	public Iterator<E> iterator() {
		return null;
	}

	/**
	 * Compares this object with the specified object for order.  Returns a
	 * negative integer, zero, or a positive integer as this object is less
	 * than, equal to, or greater than the specified object.
	 * <p>
	 * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
	 * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
	 * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
	 * <tt>y.compareTo(x)</tt> throws an exception.)
	 * <p>
	 * <p>The implementor must also ensure that the relation is transitive:
	 * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
	 * <tt>x.compareTo(z)&gt;0</tt>.
	 * <p>
	 * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
	 * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
	 * all <tt>z</tt>.
	 * <p>
	 * <p>It is strongly recommended, but <i>not</i> strictly required that
	 * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
	 * class that implements the <tt>Comparable</tt> interface and violates
	 * this condition should clearly indicate this fact.  The recommended
	 * language is "Note: this class has a natural ordering that is
	 * inconsistent with equals."
	 * <p>
	 * <p>In the foregoing description, the notation
	 * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
	 * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
	 * <tt>0</tt>, or <tt>1</tt> according to whether the value of
	 * <i>expression</i> is negative, zero or positive.
	 *
	 * @param pStack the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object
	 * is less than, equal to, or greater than the specified object.
	 * @throws NullPointerException if the specified object is null
	 * @throws ClassCastException   if the specified object's type prevents it
	 *                              from being compared to this object.
	 */
	@Override
	public int compareTo(@NonNull Stack<E> pStack) {
		if( sizeS > pStack.sizeS )
			return 1;
		if( sizeS < pStack.sizeS )
			return -1;
		else
			return 0;
	}

	/**
	 * Clase que modela un nodo de la pila o de la fila
	 * @author hd.castellanos,da.ramons
	 *
	 * @param <E> un elemento
	 */
	public  static class Node<E> {
		/**
		 * Atributo para el elemento del nodo
		 */
		private E element;
		/**
		 * Atributo para el siguiente nodo
		 */
		private Node<E> next;


		/**
		 * Crea un nuevo nodo con el elemento que llega por parametro 
		 * y su nodo siguiente es nulo
		 * @param pElement elemento del nuevo nodo
		 */
		public Node (E pElement){
			element = pElement;
			next = null;
		}




		/**
		 * Cambia el nodo siguiente por el nodo que llega por parametro
		 * @param pNode nuevo nodo siguiente
		 */
		public void changeNext(Node<E> pNode){
			next = pNode;
		}

		/**
		 * @return el nodo siguiente
		 */
		public Node<E> getNext(){
			return next;
		}

		/**
		 * @return el elemento del nodo actual
		 */
		public E getElement(){
			return element;
		}

		/**
		 * Cambia el elemento del nodo por el elemento que llega por parametro
		 * @param pElement Nuevo elemento que tendra el nodo
		 */
		public void changeElemet (E pElement){
			element = pElement;
		}
		
		public String toString(){
			return element + "";
		}
	}
	/**
	 * Crea una nueva pila con su primer nodo nulo
	 * y su tamanio = 0
	 */
	public Stack (){
		firstE = null;
		sizeS = 0;
	}


	/**
	 * @return el tamanio de la pila
	 */
	public Integer getSize(){
		return sizeS;
	}

	/**
	 * @return retorna true si la pila esta vacia , false si tiene almenos un elemento 
	 */
	public boolean isEmpty(){
		return sizeS == 0;
	}

	/**
	 * Agrega un nuevo nodo a la pila con el elemento que llega por parametro,
	 * el nuevo nodo es el nuevo first, aumenta el tamano en 1
	 */
	public void push(E pElement) {
		Node <E> oldFirst = firstE ;
		firstE = new Node<E>(pElement);
		firstE.changeElemet(pElement);;
		firstE.changeNext(oldFirst);
		sizeS++;

	}

	/**
	 * Elimina y retorna el primer elemento de la pila
	 */
	public E pop() {
		if (isEmpty())
			throw new NoSuchElementException("La pila esta vacia");
		E element = firstE.getElement();
		firstE = firstE.getNext();
		sizeS --;
		return element;
	}

	/**
	 * @return retorna el elemento del primer nodo de la pila 
	 */
	public Node<E> peek(){
		if (isEmpty())
			throw new NoSuchElementException();
		return firstE;
	}

	public String toString(){
		String ans = "[";
		Node<E> currentNode = peek();
		while( currentNode != null){
			ans = ans + " " + currentNode.getElement();
			currentNode = currentNode.getNext();
		}
		ans = ans + "]";
		return ans;
	}
}
