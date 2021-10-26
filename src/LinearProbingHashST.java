public class LinearProbingHashST<String, Integer>
{
	private int N; // number of key-value pairs in the table
	private int M ; // size of linear-probing table
	private String[] keys; // the keys
	private Integer[] vals; // the values
	
	
	public int getM() {
		return M;
	}
	public void setM(int m) {
		M = m;
	}
	public LinearProbingHashST(int cap)
	{
		M=cap;
		keys = (String[]) new Object[M];
		vals = (Integer[]) new Object[M];
	}
	private int hash(String key)
	{ return (key.hashCode() & 0x7fffffff) % M; }

	public void put(String key, Integer val)
	{
		
		if (N >= M/2) resize(2*M); // double M (see text)
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key)) { vals[i] = (Integer) val; return; }
		keys[i] = (String) key;
		vals[i] = (Integer) val;
		N++;
		System.out.println("nice + "+ key+" + "+hash(key));
	}
	public Integer get(String key)
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
		LinearProbingHashST<String, Integer> t;
		t = new LinearProbingHashST<String, Integer>(cap);
		
		System.out.println(t.M);
		for (int i = 0; i < M; i++)
			if (keys[i] != null)
				t.put(keys[i], vals[i]);
		keys = t.keys;
		vals = t.vals;
		M = t.M;
	}
}