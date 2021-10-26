

public class SequentialSearchST<Key, Value> {
	private int n;           // number of key-value pairs
	public Node first;      // the linked list of key-value pairs
	public Node current;
	// a helper linked list data type
//	public class Node {
//		public Key key;
//		public Value val;
//		public Node next;
//		public int repeat;
//
//		public Node(Key key, Value val, Node next)  {
//			current=null;
//			this.key  = key;
//			this.val  = val;
//			this.next = next;
//			repeat=1;
//		}
//	}
	
	
	/**
	 * Initializes an empty symbol table.
	 */
	public SequentialSearchST() {
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return n;
	}

	/**
	 * Is this symbol table empty?
	 * @return {@code true} if this symbol table is empty and {@code false} otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Does this symbol table contain the given key?
	 * @param key the key
	 * @return {@code true} if this symbol table contains {@code key} and
	 *     {@code false} otherwise
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 * Returns the value associated with the given key.
	 * @param key the key
	 * @return the value associated with the given key if the key is in the symbol table
	 *     and {@code null} if the key is not in the symbol table
	 */
	public Value get(Key key) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key))
				return (Value) x.val;
		}
		return null;
	}
	
	public int getRep(Key key) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key))
				return x.repeat;
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
		if (val == null) {
			delete(key);
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
					System.out.println("repeat "+x.key+" "+ +x.repeat);
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
					System.out.println("repeat "+x.key+" "+ +x.repeat);
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

	/**
	 * Removes the key and associated value from the symbol table
	 * (if the key is in the symbol table).
	 * @param key the key
	 */
	public void delete(Key key) {
		first = delete(first, key);
	}

	// delete key in linked list beginning at Node x
	// warning: function call stack too large if table is large
	private Node delete(Node x, Key key) {
		if (x == null) return null;
		if (key.equals(x.key)) {
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}


	/**
	 * Returns all keys in the symbol table as an {@code Iterable}.
	 * To iterate over all of the keys in the symbol table named {@code st},
	 * use the foreach notation: {@code for (Key key : st.keys())}.
	 * @return all keys in the symbol table as an {@code Iterable}
	 */
	public Iterable<Key> keys()  {
		Queue<Key> queue = new Queue<Key>();
		for (Node x = first; x != null; x = x.next)
			queue.enqueue((Key) x.key);
		return queue;
	}


	
	
}
