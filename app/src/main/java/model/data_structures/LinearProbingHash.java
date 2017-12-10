package model.data_structures;

import api.IHashTable;

/**
 * Implementaci�n de una tabla de hash con linear probing
 * Basada en: http://algs4.cs.princeton.edu/34hash/LinearProbingHashST.java.html
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHash<Key,Value> implements IHashTable<Key, Value> {

	private static final int INIT_CAPACITY = 4;

	private int pairs;           // numero de parejas key-value 
	private int size;           // tamano de la tabla 
	private Key[] keys;      // arreglo de las llaves
	private Value[] vals;    // arreglo con los valores 


	/**
	 * Inicializa una tabla vacia 
	 */
	public LinearProbingHash() {
		this(INIT_CAPACITY);
	}

	/**
	 * Inicializa una tabla con la capacidad ingresada por parametro .
	 *
	 * @param capacity the initial capacity
	 */
	public LinearProbingHash(int capacity) {
		size = capacity;
		pairs = 0;
		keys = (Key[])   new Object[size];
		vals = (Value[]) new Object[size];
	}

	/**
	 * @return el numero de parejas key-Valor de la tabla
	 */
	public int size() {
		return pairs;
	}


	/**
	 * @return true si la tabla esta vacia , false de lo contrario  
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	/**
	 * 
	 * @param  key la key 
	 * @return  true si la tabla contiene la key ingersada por parametro
	 *         code false de lo contrario
	 * @throws IllegalArgumentException si la key es null
	 */
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % size;
	}

	private void resize(int capacity) {
		LinearProbingHash<Key, Value> temp = new LinearProbingHash<Key, Value>(capacity);
		for (int i = 0; i < size; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		size    = temp.size;
	}

	/**
	 *  inserta el key-value especificado en la tabla, sobre escribiendo el valor
	 *   antiguo con el nuevo valor si la tabla ya contenia la key especificada
	 *borra el key-value especificado si el value ingresado es null 
	 *
	 * @param  key the key
	 * @param  val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");

		if (val == null) {
			delete(key);
			return;
		}

		if (pairs >= size/2) resize(2*size);

		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % size) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		pairs++;
	}

	/**
	 * @param key la llave de la que se desea saber el valor 
	 * @return retorna el valor asociado a la key ingresada por parametro 
	 *         null si no existe tal valor 
	 * @throws IllegalArgumentException si la key es null 
	 */
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		for (int i = hash(key); keys[i] != null; i = (i + 1) % size)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	/**
	 * remueve la key ingresada por parametro y su valor si la key existe en la tabla 
	 * @param  key la key que se desea eliminar 
	 * @throws IllegalArgumentException si la key es null 
	 */
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key)) return;

		int i = hash(key);
		while (!key.equals(keys[i])) {
			i = (i + 1) % size;
		}

		keys[i] = null;
		vals[i] = null;

		i = (i + 1) % size;
		while (keys[i] != null) {
			Key   keyToRehash = keys[i];
			Value valToRehash = vals[i];
			keys[i] = null;
			vals[i] = null;
			pairs--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % size;
		}

		pairs--;

		if (pairs > 0 && pairs <= size/8) resize(size/2);

		assert check();
	}

	/**

	 * @return todas las llaves de esta tabla como un iterable 
	 */
	public Queue<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < size; i++)
			if (keys[i] != null) queue.enqueue(keys[i]);
		return queue;
	}

	public String toString(){
		String ans = "[";
		for( Key key : keys() ){
			ans = ans + "(" + key + ", " + get(key) + ") ";
		}
		return ans + "]";
	}


	/**
	 * Revisa que la tabla est� a menos del 50% de su capacidad y que todas las llaves se puedan hallar con get()
	 * @return
	 */
	private boolean check() {
		if (size < 2*pairs) {
			System.err.println("Hash table size m = " + size + "; array size n = " + pairs);
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (keys[i] == null) continue;
			else if (get(keys[i]) != vals[i]) {
				System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
				return false;
			}
		}
		return true;

	}
}
