public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;



    private int N;                                // number of key-value pairs
    private int M;                                // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables


    // create separate chaining hash table
    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    // create separate chaining hash table with M lists
    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    }

    // resize the hash table to have the given number of chains b rehashing all of the keys
    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.M  = temp.M;
        this.N  = temp.N;
        this.st = temp.st;
        System.out.println("RESIZEDD");
    }

    // hash value between 0 and M-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // return number of key-value pairs in symbol table
    public int size() {
        return N;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // is the key in the symbol table?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // return value associated with key, null if no such key
    public Value get(Key key) {
        int i = hash(key);
        return st[i].get(key);
    }

    // insert key-value pair into the table
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }

        // double table size if average length of list >= 10
        if (N >= 8*M) resize(2*M);

        int i = hash(key);
        if (!st[i].contains(key)) N++;
        if(st[i].put(key, val)) {
        	System.out.println(key+" "+i);
        }else {
        	System.out.println("repeat");
        }
        
    }
    
    public void getMaxs() {
    	
    }

    // delete key (and associated value) if key is in the table
    public void delete(Key key) {
        int i = hash(key);
        if (st[i].contains(key)) N--;
        st[i].delete(key);

        // halve table size if average length of list <= 1
        if (M > INIT_CAPACITY && N <= 2*M) resize(M/2);
    }

    // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }
    

	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}
    
}