
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinearProbingHashST<String, Integer> t;
		t = new LinearProbingHashST<String, Integer>(16);
		SeparateChainingHashST<String, Integer> st;
		st = new SeparateChainingHashST<String, Integer>(16);
		
		for(int i=0;i<args.length;i++) {  
			//t.put(args[i], args[i].hashCode());
			st.put(args[i], args[i].hashCode());
		} 

//		String test ="world";
//		System.out.println(test.hashCode());
//		t.put(test, test.hashCode());
//		t.put("dog", test.hashCode());
//		t.put("cat", test.hashCode());
//		t.put("car", test.hashCode());
//		t.put("window", test.hashCode());
//		t.put("windows", test.hashCode());
//		t.put("windo", test.hashCode());
//		t.put("ted", test.hashCode());
//		t.put("cmpe", test.hashCode());
//		t.put("242", test.hashCode());
//		t.put("accommodate", test.hashCode());
//		t.put("synonyms", test.hashCode());
//		t.put("accommodating", test.hashCode());
//		t.put("accommodation", test.hashCode());
//		t.put("accordionist", test.hashCode());
//		t.put("accordion", test.hashCode());
//		t.put("pleats", test.hashCode());
//		t.put("accoucheur's", test.hashCode());
//		t.put("hand", test.hashCode());
//		t.put("accoucheuse", test.hashCode());
//		t.put("according", test.hashCode());
//		t.put("as", test.hashCode());
		
		
		System.out.println("max cap1 "+ st.getM());
		System.out.println("max cap "+ t.getM());
		
	}

}
