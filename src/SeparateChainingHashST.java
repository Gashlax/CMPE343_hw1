import java.util.ArrayList;




public class SeparateChainingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 4;
	ArrayList<Node> Maxs = new ArrayList<Node>(3);


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
				temp.put(key, st[i].get(key), st[i].getRep(key));
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
			System.out.println("repeattt");
		}

	}

	public void put(Key key, Value val,int res) {

		int i = hash(key);
		if(st[i].put(key, val, res)) {
			System.out.println(key+" "+i);
		}

	}


	public ArrayList<Node> FindMaxs() {
		for (int i = 0; i < M; i++) {
			st[i].current=st[i].first;
			while(st[i].current!=null) {
				//System.out.println(st[i].current.key);
				System.out.println("New word "+st[i].current.key+" "+st[i].current.repeat);
				if(Maxs.size()<=3) {
					Maxs.add(st[i].current);
					System.out.println(st[i].current.key+" clueless");
					//System.out.println(st[i].getRep(Maxs.get(t))+" "+st[i].current.key);

				}else {
					int store=st[i].current.repeat;
					int minIndex;

					System.out.println(Maxs.get(0).key+" "+Maxs.get(0).repeat);
					System.out.println(Maxs.get(1).key+" "+Maxs.get(1).repeat);
					System.out.println(Maxs.get(2).key+" "+Maxs.get(2).repeat);
					System.out.println(Maxs.get(3).key+" "+Maxs.get(3).repeat);
					
					
					if(Maxs.get(0).repeat > Maxs.get(1).repeat) {
						if(Maxs.get(1).repeat>Maxs.get(2).repeat){
							System.out.println(Maxs.get(2).repeat);
							minIndex=2;
						}else {
							System.out.println(Maxs.get(1).repeat);
							minIndex=1;
						}
					}else {
						if(Maxs.get(0).repeat > Maxs.get(2).repeat){
							System.out.println(Maxs.get(2).repeat);
							minIndex=2;
						}else {
							System.out.println(Maxs.get(0).repeat);
							minIndex=0;
						}
					}

					if(store>Maxs.get(minIndex).repeat){
						Maxs.set(minIndex, st[i].current);
					}

				}
				st[i].current=st[i].current.next;
			}
		}
		sortArr();
		System.out.println("asdasdasdasd");
		System.out.println( Maxs.get(0).key+"\n"+Maxs.get(1).key+"\n"+Maxs.get(2).key+"\n"+Maxs.get(3).key+"\n");

		return Maxs;
	}

	public void sortArr() {
		Node tmp;
		int i, j;
		for (i = 1; i < Maxs.size(); i++) {
			tmp = Maxs.get(i);
			j = i;
			while ((j > 0) && (Maxs.get(j - 1).repeat < tmp.repeat)) {
				Maxs.set(j, Maxs.get(j - 1));
				j--;
			}
			Maxs.set(j, tmp);
		}




		//		
		//		if(Maxs.get(1).repeat>Maxs.get(0).repeat) {
		//			Node temp;
		//			temp=Maxs.get(0);
		//			Maxs.set(0,Maxs.get(1));
		//			Maxs.set(1,temp);
		//
		//
		//		}

		//
		//		if (Maxs.get(0).repeat >= Maxs.get(1).repeat){ //In the three responses below, y is always before x.  
		//			if (Maxs.get(1).repeat >= Maxs.get(2).repeat)
		//				System.out.print("In order " + z + " "+ y + " " + x);
		//
		//			else if  (Maxs.get(2).repeat >= Maxs.get(0).repeat) {
		//				temp=Maxs.get(2);
		//				Maxs.set(2,Maxs.get(1));
		//				Maxs.set(1,temp);
		//				
		//				temp=Maxs.get(1);
		//				Maxs.set(1,Maxs.get(0));
		//				Maxs.set(0,temp);
		//				
		//				
		//			}else if (Maxs.get(0).repeat > Maxs.get(2).repeat)
		//				System.out.print("In order " + y + " " + z + " " + x);
		//		}
		//
		//		if (Maxs.get(1).repeat > Maxs.get(0).repeat){// In the three responses below, x is always before y
		//			if (Maxs.get(2).repeat >= Maxs.get(1).repeat)
		//				System.out.print("In order " + x + " " + y + " "+ z);
		//			else if (Maxs.get(2).repeat >= Maxs.get(0).repeat)
		//				//System.out.print("In order " + y + " " + x + " " + z); //In this case, z has to be smaller than y.  The order was off
		//				System.out.print("In order " + x + " " + z + " " + y);
		//			else if (Maxs.get(0).repeat > Maxs.get(2).repeat)
		//				//System.out.print("In order " + y + " " + z + " " + x);
		//				System.out.print("In order " + z + " " + x + " " + y); //Y is the biggest.  The order here was off.  
		//		}



	}


	//    public int MinIndex() {
	//    	int t=-1;
	//    	for(int i=0;i<3;i++) {
	//    		st[i].getRep(Maxs.get(t);
	//    	}
	//    }

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