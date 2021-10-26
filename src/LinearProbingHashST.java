public class LinearProbingHashST<Key, Value>
{
	private int N; // number of key-value pairs in the table
	private int M ; // size of linear-probing table
	private Key[] keys; // the keys
	private Value[] vals; // the values
	
	
	public int getM() {
		return M;
	}
	public void setM(int m) {
		M = m;
	}
	public LinearProbingHashST(int cap)
	{
		M=cap;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	private int hash(Key key)
	{ return (key.hashCode() & 0x7fffffff) % M; }

	public void put(Key key, Value val)
	{
		
		if (N >= M/2) resize(2*M); // double M (see text)
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key)) { vals[i] = (Value) val; return; }
		keys[i] = (Key) key;
		vals[i] = (Value) val;
		N++;
		System.out.println("nice + "+ key+" + "+hash(key));
	}
	public Value get(Key key)
	{
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	private void resize(int cap)
	{
		System.out.print("resizinggg \t");
		cap=M*2;
		LinearProbingHashST<Key, Value> t;
		t = new LinearProbingHashST<Key, Value>(cap);
		
		System.out.println(t.M);
		for (int i = 0; i < M; i++)
			if (keys[i] != null)
				t.put(keys[i], vals[i]);
		keys = t.keys;
		vals = t.vals;
		M = t.M;
	}
}