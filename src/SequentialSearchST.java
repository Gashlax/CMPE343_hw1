//-----------------------------------------------------
// Title: Sequential Search ST (Linked List)
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 1
// Description: This class is basically stores and defines the  SeparateChainingHashST 
// class chains. So it is basically the chain class
//-----------------------------------------------------


public class SequentialSearchST<Key, Value> {
	// number of key-value pairs
	private int n;           
	// the linked list of key-value pairs
	public Node first;      
	public Node current;


	public SequentialSearchST() {
		//--------------------------------------------------------
		// Summary: empty constrctor.Initializes an empty symbol table. 
		//--------------------------------------------------------
	}

	public int size() {
		//--------------------------------------------------------
		// Summary: Returns the number of key-value pairs in this symbol table
		// with using n integer
		// Postcondition: returns n
		//--------------------------------------------------------
		return n;
	}


	public boolean isEmpty() {
		//--------------------------------------------------------
		// Summary: Checks if its empty or not
		// Postcondition: returns boolean
		//--------------------------------------------------------
		return size() == 0;
	}



	public Value get(Key key) {
		//--------------------------------------------------------
		// Summary: Search for the given key if it finds it returns the value
		// Precondition: takes key as parameter
		// Postcondition: returns value
		//--------------------------------------------------------
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key))
				return (Value) x.val;
		}
		return null;
	}

	public int getRep(Key key) {
		//--------------------------------------------------------
		// Summary: I design this method to get the repetition number
		// it goes the end of the linked list until to find the key and then return the repetition number
		// Precondition: takes key as parameter
		// Postcondition: returns repetition int
		//--------------------------------------------------------
		
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key))
				return x.repeat;
		}
		return -1;
	}

	public int findIndex(Key key) {
		//--------------------------------------------------------
		// Summary: It is basically passes through all nodes until it finds to key
		// since we need the index of the node.When we find key return its index
		// Precondition: takes key as parameter
		// Postcondition: returns index int
		//--------------------------------------------------------
		int ind=0;
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key))
				return ind;
			ind++;
		}

		return -1;
	}



	/**
	 * Inserts the key-value pair into the symbol table, overwriting the old value
	 * with the new value if the key is already in the symbol table.
	 * If the value is {@code null}, this effectively deletes the key from the symbol table.
	 * @param key the key
	 * @param val the value
	 */
	public boolean put(Key key, Value val) {
		//--------------------------------------------------------
		// Summary: It puts the key- value pair to the end of the linked list.
		// Since we need to go end of the linked list at the same time we check for
		// repeat case same time. to Notify the parent separate Chaining class
		// we return boolean 
		// Precondition: it takes key and value
		// Postcondition: returns the validation boolean
		//--------------------------------------------------------
		
		if (val == null) {
			return false;
		}
		if(first==null) {
			first = new Node(key, val, null);
			return true;
		}else {
			Node x ;

			x=first;
			Node pre = null;
			//System.out.println("x Node: asdasd"+x.key);
			while(x!=null) {
				//System.out.println("x Node: "+x);
				if (key.equals(x.key)) {
					x.val = val;
					x.repeat++;
					//System.out.println("repeat "+x.key+" "+ +x.repeat);
					return false;
				}
				pre=x;
				x=x.next;
			}

			//System.out.println("x Node: "+x);
			Node add= new Node(key, val, null);
			pre.next=add;
			n++;
			return true;
		}
	}

	public boolean put(Key key, Value val, int res) {
		//--------------------------------------------------------
		// Summary:It is the same thing with upper put method but I use this for resize case
		// while resizing we need to keep repetition this way we can put old values back to list		
		// It puts the key- value pair to the end of the linked list.
		// Since we need to go end of the linked list at the same time we check for
		// repeat case same time. to Notify the parent separate Chaining class
		// we return boolean 
		// Precondition: it takes key and value, res integer
		// Postcondition: returns the validation boolean
		//--------------------------------------------------------
		

		if(first==null) {
			first = new Node(key, val, null);
			first.repeat=res;
			return true;
		}else {
			Node x ;

			x=first;
			Node pre = null;
			//System.out.println("x Node: asdasd"+x.key);
			while(x!=null) {
				//System.out.println("x Node: "+x);
				if (key.equals(x.key)) {
					x.val = val;
					x.repeat++;
					//System.out.println("repeat "+x.key+" "+ +x.repeat);
					return false;
				}
				pre=x;
				x=x.next;
			}

			//System.out.println("x Node: "+x);
			Node add= new Node(key, val, null);
			add.repeat=res;
			pre.next=add;
			n++;
			return true;
		}
	}


	public Iterable<Key> keys()  {
		//--------------------------------------------------------
		// Summary:Returns all keys in the symbol table as an To iterate over all of the keys 
		// in the symbol table. Also uses queue to keep these keys and values
		// Postcondition: return all keys in the symbol table as an Iterable
		//--------------------------------------------------------
		Queue<Key> queue = new Queue<Key>();
		for (Node x = first; x != null; x = x.next)
			queue.enqueue((Key) x.key);
		return queue;
	}	

}
