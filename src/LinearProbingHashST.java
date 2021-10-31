//-----------------------------------------------------
// Title: Linear Probing Hash Table
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 1
// Description: This class defines the Linear probing methods and functions
//-----------------------------------------------------



public class LinearProbingHashST<Key, Value>
{
	private int N; //counts keys
	private int M ; // size of linear-probing table

	private Key[] keys; // the key array
	private Value[] vals; // the value array


	public int getM() {
		return M;
	}
	public void setM(int m) {
		M = m;
	}


	public LinearProbingHashST(int cap){
		//--------------------------------------------------------
		// Summary: Constructor to set arrays and values
		// Precondition: it takes integer to set capacity
		// Postcondition: The value of the variables are set.
		//--------------------------------------------------------
		M=cap;
		N=0;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}

	private int hash(Key key) {
		//--------------------------------------------------------
		// Summary: Generic hash method takes key and gives hash code
		// Precondition: takes key to calculate hash
		// Postcondition: The value of the key hash 
		//--------------------------------------------------------
		return (key.hashCode() & 0x7fffffff) % M; 
	}

	public void put(Key key, Value val) {	
		//--------------------------------------------------------
		// Summary: This method takes key and hash to put keys to spesific location
		// Precondition: takes key and hash value
		// Postcondition: inserts key into arrays therefore hash tables
		//--------------------------------------------------------


		// double table size if 50% full
		if (N >= M/2) 
			resize(2*M);

		int i;
		// It starts from the hash key then it increments until to find a valid location
		//If it finds the same word(repeat case ) it returns
		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	public Value get(Key key) {
		//--------------------------------------------------------
		// Summary: This method takes key and tries to find exact location of the key but in my case 
		//it didnt work so I created findINdex method
		// Precondition: takes key
		// Postcondition:finds the value of the key
		//--------------------------------------------------------
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	public int findIndex(Key key) {
		//--------------------------------------------------------
		// Summary: This method takes key and tries to find exact location of the key 
		// Precondition: takes key
		// Postcondition:finds the value of the key
		// Danger: If the key placed the left side of the hash value due to collisions it may break down
		//--------------------------------------------------------
		for (int i = hash(key); i<M; i++) {
			if (keys[i].equals(key)) {
				return i;
			}
		}
		return -1;
	}

	private void resize(int capacity) {
		//--------------------------------------------------------
		// Summary: This resize function creates another Linear probing to store data then takes all keys and rehash them
		//to place its new places then equalizes with old array. 
		// Precondition: takes new capacity as integer
		// Postcondition: New capacity LinearProbingHash table is ready with data recovered
		//--------------------------------------------------------
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
		for (int i = 0; i < M; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		M    = temp.M;
	}
}