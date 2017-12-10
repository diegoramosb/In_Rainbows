package Model.data_structures;

import java.util.NoSuchElementException;

import api.IRedBlackBST;

/**
 * Implementaci�n de un �rbol rojo negro.
 * Tomada de: http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
 * @param <Key> Tipo de la las llaves del �rbol
 * @param <Value> Tipo de los valores del �rbol
 */
public class RedBlackBST< Key extends Comparable<Key>, Value > implements IRedBlackBST<Key, Value>{

	/**
	 * Constante para indicar que el nodo es rojo
	 */
	private static final boolean RED = true;

	/**
	 * Constante para indicar que el nodo es negro
	 */
	private static final boolean BLACK = false;

	/**
	 * Raiz del �rbol.
	 */
	private Node root;

	/**
	 * Clase privada para los nodos del �rbol
	 */
	private class Node {
		/**
		 * Llave del nodo
		 */
		private Key key;

		/**
		 * Valor del nodo
		 */
		private Value val;

		/**
		 * Links a los nodos derecho e izquierdo.
		 */
		private Node left, right;

		/**
		 * Color del link del padre
		 */
		private boolean color;

		/**
		 * Tama�o del subarbol
		 */
		private int size;

		public Node( Key pKey, Value pVal, boolean pColor, int pSize ){
			key = pKey;
			val = pVal;
			color = pColor;
			size = pSize;
		}
	}

	/**
	 * Inicializa un �rbol vac�o
	 */
	public RedBlackBST(){
	}

	/**
	 * @param pNode Nodo que se quiere saber si es rojo
	 * @return false el nodo no existe o si es negro, true de lo contrario.
	 */ 
	private boolean isRed( Node pNode ){
		if( pNode == null) return false;
		return pNode.color == RED;
	}

	/**
	 * @param pNode Ra�z del sub-�rbol del cual se quiere saber el tama�o.
	 * @return n�mero de nodos en el sub-�rbol, 0 si el nodo es null.
	 */
	private int size( Node pNode ){
		if( pNode == null) return 0;
		return pNode.size;
	}

	/**
	 * @return N�mero de parejas llave-valo del �rbol.
	 */
	public int size(){
		return size(root);
	}

	/**
	 * @return true si el �rbol est� vac�o, false de lo contrario.
	 */
	public boolean isEmpty(){
		return root == null;
	}

	/**
	 * @param pKey llave de la que se quiere ver el valor.
	 * @return valor asociado a la llave que entra por par�metro si est� en el �rbol o null si no est�.
	 * @throws IllegalArgumentException si la llave es null
	 */
	public Value get( Key pKey ){
		if( pKey == null)
			throw new IllegalArgumentException( "El par�metro de get() es null" );
		return get( root, pKey);
	}

	/**
	 * @param pNode ra�z del sub-�rbol donde se va a buscar
	 * @param pKey llave asociada al valor que se retorna
	 * @return Valor asociado a una llave en el sub-�rbol con raiz en pNode
	 */
	private Value get( Node pNode, Key pKey ){
		while( pNode != null){
			int cmp = pKey.compareTo(pNode.key);
			if( cmp < 0 )
				pNode = pNode.left;
			else if( cmp > 0)
				pNode = pNode.right;
			else
				return pNode.val;
		}
		return null;
	}

	/**
	 * @param pKey llave que se desea saber si est� en el �rbol
	 * @return true si el �rbol contiene la llave, false de lo contrario
	 * @throws IllegalArgumentException si la llave es null
	 */
	public boolean contains( Key pKey ){
		return get(pKey) != null;
	}

	/**
	 * Inserta el par llave-valor en el arbol, sobreescribiendo el anterior 
	 * valor con el nuevo valor si el �rbol ya conten�a la llave especificada.
	 * Elimina la llave especificada si pVal es null.
	 * @param pKey Llave asociada al valor que se va a insertar
	 * @param pVal Valor que se va a insertar
	 */
	public void put( Key pKey, Value pVal){
		if( pKey == null )
			throw new IllegalArgumentException("Key in put() is null");
		if( pVal == null){
			delete(pKey);
			return;
		}

		root = put( root, pKey, pVal );
		root.color = BLACK;
		assert check();
	}

	/**
	 * Inserta el par llave-valor en el sub-�rbol con ra�z en pNode
	 * @param pNode ra�z del sub-�rbol
	 * @param pKey Llave asociada al valor que se va a insertar
	 * @param pVal Valor que se va a insertar
	 * @return Nueva ra�z del sub-�rbol
	 */
	private Node put( Node pNode, Key pKey, Value pVal ){
		if( pNode == null )
			return new Node( pKey, pVal, RED, 1);

		int cmp = pKey.compareTo(pNode.key);
		if( cmp < 0 )
			pNode.left = put( pNode.left, pKey, pVal );
		else if ( cmp > 0)
			pNode.right = put(pNode.right, pKey, pVal);
		else
			pNode.val = pVal;

		if( isRed( pNode.right ) && !isRed( pNode.left ) )
			pNode = rotateLeft( pNode );
		if( isRed( pNode.left ) && isRed(pNode.left.left) )
			pNode = rotateRight( pNode );
		if( isRed( pNode.left ) && isRed(pNode.right) )
			flipColors( pNode );
		pNode.size = size(pNode.left) + size(pNode.right) + 1;

		return pNode;
	}

	/**
	 * Elimina la llave m�s peque�a y su valor asociado del �rbol.
	 * @throws NoSuchElementException si el �rbo est� vac�o
	 */
	public void deleteMin(){
		if( isEmpty() ) throw new NoSuchElementException( "Underflow del �rbol" );

		if( !isRed(root.left) && !isRed(root.right) )
			root.color = RED;

		root = deleteMin( root );
		if( !isEmpty() ) root.color = BLACK;
		assert check();
	}

	/**
	 * Elimina la llave m�s peque�a y su valor asociado del sub-�rbol con ra�z en pNode.
	 * @param pNode ra�z del sub-�rbol
	 * @return nuevo sub-�rbol despu�s de eliminar el par.
	 */
	private Node deleteMin( Node pNode ){
		if( pNode.left == null )
			return null;

		if( !isRed(pNode.left) && !isRed(pNode.left.left) )
			pNode = moveRedLeft( pNode );

		pNode.left = deleteMin(pNode.left);
		return balance( pNode );
	}

	/**
	 * Elimina la llave m�s grande y su valor asociado del �rbol.
	 * @throws NosuchElementException si el �rbol est� vac�o.
	 */
	public void deleteMax( ){
		if( isEmpty() ) throw new NoSuchElementException( "Underflow del �rbol" );

		if( !isRed( root.left ) && !isRed(root.right) )
			root.color = RED;

		root = deleteMax( root );
		if( !isEmpty() ) root.color = BLACK;
		assert check();
	}

	/**
	 * Elimina el par llave-valor m�s grande del sub-�rbol con ra�z en pNode
	 * @param pNode ra�z del sub-arbol
	 * @return Nuevo sub�rbol despu�s de eliminar el par.
	 */
	private Node deleteMax( Node pNode ){
		if( isRed(pNode) )
			pNode = rotateRight(pNode);

		if( pNode.right == null )
			return null;

		if( !isRed(pNode.right) && !isRed(pNode.right.left))
			pNode = moveRedRight( pNode );

		pNode.right = deleteMax(pNode.right);

		return balance(pNode);
	}

	/**
	 * Elimina la llave especificada y su valor asociado del �rbol si est� en �l
	 * @param pKey llave a eliminar
	 * @throws IllegalARgumentException si pKey es null
	 */
	public void delete( Key pKey ){
		if( pKey == null )
			throw new IllegalArgumentException("El argumento de delete() es null");
		if( !isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = delete( root, pKey );
		if( !isEmpty()) root.color = BLACK;
		assert check();
	}

	/**
	 * Elimina un par llave-valor del sub-�rbol con ra�z en pNode
	 * @param pNode ra�z del sub-�rbol
	 * @param pKey llave a eliminar
	 * @return Ra�z del nuevo sub-�rbol despu�s de eliminar.
	 */
	private Node delete( Node pNode, Key pKey ){
		if( pKey.compareTo(pNode.key) < 0){
			if( !isRed(pNode.left) && !isRed(pNode.left.left) )
				pNode = moveRedLeft( pNode );
			pNode.left = delete( pNode.left, pKey);
		}
		else{
			if( isRed(pNode.left) )
				pNode = rotateRight(pNode);
			if( pKey.compareTo(pNode.key) == 0 && (pNode.right == null))
				return null;
			if( pKey.compareTo(pNode.key) == 0){
				Node x = min( pNode.right );
				pNode.key = x.key;
				pNode.val = x.val;
				pNode.right = deleteMin(pNode.right);
			}
			else
				pNode.right = delete(pNode.right, pKey);
		}
		return balance(pNode);
	}

	/**
	 * Rota el sub-�rbol con ra�z en pNode a la derecha
	 * @param pNode ra�z del sub-�rbol
	 * @return Ra�z del nuevo �rbol tras la rotaci�n.
	 */
	private Node rotateRight( Node pNode ){
		Node x = pNode.left;
		pNode.left = x.right;
		x.right = pNode;
		x.color = x.right.color;
		x.right.color = RED;
		x.size = pNode.size;
		pNode.size = size(pNode.left) + size(pNode.right) + 1;
		return x;
	}

	/**
	 * Rota el sub-�rbol con ra�z en pNode a la izquierda
	 * @param pNode ra�z del sub-�rbol
	 * @return Ra�z del nuevo �rbol tras la rotaci�n.
	 */
	private Node rotateLeft( Node pNode ){
		Node x = pNode.right;
		pNode.right = x.left;
		x.left = pNode;
		x.color = x.left.color;
		x.left.color = RED;
		x.size = pNode.size;
		pNode.size = size(pNode.left) + size(pNode.right) + 1;
		return x;
	}

	/**
	 * Cambia los colores del nodo y sus hijos
	 * @param pNode Nodo cuyo color va a cambiar.
	 */
	private void flipColors( Node pNode ){
		pNode.color = !pNode.color;
		pNode.left.color = !pNode.left.color;
		pNode.right.color = !pNode.right.color;
	}

	/**
	 * Asumiendo que pNode es rojo y que pNode.left y pNode.left.right son negros,
	 * cambia pNode.left o alguno de sus hijos a rojo.
	 * @param pNode Ra�z del nodo que se quiere cambiar el rojo a la izquierda.
	 * @return ra�z del nuevo sub-�rbol despu�s de los cambios.
	 */
	private Node moveRedLeft( Node pNode ){
		flipColors(pNode);
		if( isRed(pNode.right.left)){
			pNode.right = rotateRight(pNode.right);
			pNode = rotateLeft(pNode);
			flipColors(pNode);
		}
		return pNode;
	}

	/**
	 * Asumiendo que pNode es red y pNode.right y pNode.right.left son negros,
	 * cambia pNode.right o alguno de sus hijos a rojo
	 * @param pNode Ra�z del nodo que se quiere cambiar el rojo a la izquierda.
	 * @return ra�z del nuevo sub-�rbol despu�s de los cambios.
	 */
	private Node moveRedRight( Node pNode ){
		flipColors(pNode);
		if( isRed(pNode.left.left)){
			pNode = rotateLeft(pNode);
			flipColors(pNode);
		}
		return pNode;
	}

	/**
	 * Balancea el �rbol con ra�z en pNode
	 * @param pNode ra�z del �rbol que se va a balancear
	 * @return Ra�z del �rbol balanceado
	 */
	private Node balance( Node pNode ){
		if( isRed(pNode.right) )
			pNode = rotateLeft(pNode);
		if( isRed(pNode.left) && isRed(pNode.left.left) )
			pNode = rotateRight(pNode);
		if( isRed(pNode.left) && isRed(pNode.right) )
			flipColors(pNode);

		pNode.size = size(pNode.left) + size(pNode.right) + 1;
		return pNode;
	}

	/**
	 * @return altura del �rbol
	 */
	public int height(){
		return height(root);
	}

	/**
	 * @param pNode Ra�z del sub-�rbol del cual se quiere saber la altura.
	 * @return Altura del �rbol con ra�z en pNode.
	 */
	private int height( Node pNode ){
		if( pNode == null )
			return -1;
		return 1 + Math.max(height(pNode.left), height(pNode.right));
	}

	/**
	 * @return Llave m�s peque�a del �rbol
	 * @throws NoSuchElementException si el �rbol est� vac�o
	 */
	public Key min(){
		if( isEmpty() )
			throw new NoSuchElementException("Llamada a min() sobre un �rbol vac�o");
		return min(root).key;
	}

	/**
	 * @param pNode Ra�z del sub-�rbol del cual se quiere encontrar el m�nimo
	 * @return Nodo m�s peque�o del sub-�rbol con ra�z en pNode
	 */
	private Node min( Node pNode ){
		if( pNode.left == null )
			return pNode;
		else
			return min( pNode.left );
	}

	/**
	 * @return Llave m�s grande del �rbol
	 * @throws NoSuchElementException si el �rbol est� vac�o
	 */
	public Key max(){
		if( isEmpty() )
			throw new NoSuchElementException("Llamada a max() sobre un �rbol vac�o");
		else
			return max(root).key;
	}


	/**
	 * @param pNode Ra�z del sub-�rbol del cual se quiere encontrar el m�ximo
	 * @return Nodo m�s grande del sub-�rbol con ra�z en pNode
	 */
	private Node max( Node pNode ){
		if( pNode.right == null )
			return pNode;
		else
			return max( pNode.right );
	}

	/**
	 * @return Retorna todas las llaves del �rbol como un Iterable
	 */
	public Queue<Key> keys(){
		if( isEmpty() )
			return new Queue<Key>();
		return keys( min(), max());
	}

	/**
	 * @param lo inicio del rango
	 * @param hi fin del rango
	 * @return Retorna todas las llaves del �rbol en el rango dado
	 */
	public Queue<Key> keys( Key lo, Key hi){
		if( lo == null ) throw new IllegalArgumentException( "La llave menor es null");
		if( hi == null ) throw new IllegalAccessError("La llave mayor es null");

		Queue<Key> queue = new Queue<Key>();

		keys( root, queue, lo, hi);
		return queue;
	}
	

	/**
	 * Agrega las llaves entre lo y hi del sub-�rbol con ra�z en pNode al queue.
	 * @param pNode Ra�z del �rbol
	 * @param pQueue Queue al que se agregan las llaves
	 * @param lo inicio del rango
	 * @param hi fin del rango
	 */
	private void keys( Node pNode, Queue<Key> pQueue, Key lo, Key hi ){
		if( pNode == null )
			return;
		int cmpLo = lo.compareTo(pNode.key);
		int cmpHi = hi.compareTo(pNode.key);
		if( cmpLo < 0 )
			keys( pNode.left, pQueue, lo, hi);
		if( cmpLo <= 0 && cmpHi >= 0) pQueue.enqueue(pNode.key);
		if( cmpHi > 0 )
			keys( pNode.right, pQueue, lo, hi );
	}
	
	/**
	 * @param init inicio del rango
	 * @param end final del rango
	 * @return todas las llaves en el �rbol en el rango dado
	 */
	public Queue<Key> valuesInRange( Key init, Key end ){
		if( init == null)
			throw new IllegalArgumentException("La llave inicial es null");
		if( end == null )
			throw new IllegalArgumentException( "La llave final es null" );
		Queue<Key> queue = new Queue<Key>();
		valuesInRange( root, queue, init, end);
		return queue;
	}
	
	/**
	 * Agrega las llaves del �rbol con ra�z en pNode que son mayores que init y menores que end al queue. 
	 * @param pNode ra�z del �rbol
	 * @param pQueue cola a la que se agregan las llaves
	 * @param init llave m�nima
	 * @param end llave m�xima
	 */
	private void valuesInRange( Node pNode, Queue<Key> pQueue, Key init, Key end ){
		if( pNode == null )
			return;
		int cmpInit = init.compareTo(pNode.key);
		int cmpEnd = end.compareTo(pNode.key);
		if( cmpInit < 0)
			valuesInRange( pNode.left, pQueue, init, end );
		if( cmpInit <= 0 && cmpEnd >= 0)
			pQueue.enqueue(pNode.key);
		if( cmpEnd > 0)
			valuesInRange(pNode.right, pQueue, init, end);
	}
	
	/**
	 * @return true si el �rbol es un �rbol de b�squeda binario y est� balanceado, false de lo contrario.
	 */
	private boolean check(){
		if(!isBST())
			System.out.println("No est� en un orden sim�trico.");
		if( !isBalanced() )
			System.out.println("No est� balanceado.");
		return isBST() && isBalanced();
	}
 
	/**
	 * @return true si el �rbol est� en un orden sim�trico.
	 */
	private boolean isBST(){
		return isBST(root, null, null);
	}
	
	/**
	 * @param pNode ra�z del �rbol
	 * @param min valor m�ximo de las llaves del �rbol.
	 * @param max valor m�nimo de las llaves del �rbol.
	 * @return true si el �rbol con ra�z en pNode tiene todas sus llaves entre min y max,
	 * false de lo contrario.
	 */
	private boolean isBST( Node pNode, Key min, Key max ){
		if( pNode == null )
			return true;
		if( min != null && pNode.key.compareTo(min) <= 0)
			return false;
		if( max != null && pNode.key.compareTo(max) >= 0)
			return false;
		return isBST(pNode.left, min, pNode.key) && isBST(pNode.right, pNode.key, max);
	}

	/**
	 * @return true si todas las hojas desde la ra�z est�n a la misma distancia, false de lo contrario. 
	 */
	private boolean isBalanced(){
		int black = 0;
		Node x = root;
		while( x != null){
			if(!isRed(x))
				black++;
			x = x.left;
		}
		return isBalanced(root, black);
	}

	/**
	 * @param pNode ra�z del �rbol
	 * @param black n�mero de links negros
	 * @return true si todas las hojas del �rbol est�n a la misma distancia de pNode, false de lo contrario.
	 */
	private boolean isBalanced( Node pNode, int black ){
		if( pNode == null )
			return black == 0;
		if( !isRed(pNode) )
			black--;
		return isBalanced( pNode.left, black ) && isBalanced(pNode.right, black);
	}
	
	public String toString(){
		String ans = "";
		for( Key key: keys() ){
			ans = ans + "[ " + key + ", " + get(key) + " ]";
		}
		return ans;
	}
}
