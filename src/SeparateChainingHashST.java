//-----------------------------------------------------
// Title: Separate Chaining Hash Table
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 1
// Description: This method defines Separate Chaining Hash Table
//-----------------------------------------------------

import java.util.ArrayList;


public class SeparateChainingHashST<Key, Value> {
	// number of key-value pairs
	private int N;             
	// hash table size
	private int M;                               
	// array of linked-list symbol tables
	private SequentialSearchST<Key, Value>[] st;  


	// create separate chaining hash table with M lists
	public SeparateChainingHashST(int M) {
		//--------------------------------------------------------
		// Summary: This method takes int value and creates and defines the chain number 
		// of the data structure and creates the linked list number
		// Precondition: takes int as array number
		// Postcondition: creates SequentialSearchST array and then initialize 
		//--------------------------------------------------------
		this.M = M;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for (int i = 0; i < M; i++)
			st[i] = new SequentialSearchST<Key, Value>();
	}

	// resize the hash table to have the given number of chains b rehashing all of the keys
	private void resize(int chains) {
		//--------------------------------------------------------
		// Summary: This method resize the separate chaining with temporary chains
		// uses keys method to get all keys from the previous chains other than that since homework asked for it
		// we need to store repetition integer to new chains. So I created another .put() method to help me
		// Precondition: takes int as new chain number to resize
		// Postcondition: creates SequentialSearchST array and then initialize 
		//--------------------------------------------------------
		SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
		for (int i = 0; i < M; i++) {
			// takes all keys from queue then reput to the new chains while
			//not loosing the repetition info.
			//we also increment N to store distinct key values right.
			for (Key key : st[i].keys()) {
				
				temp.put(key, st[i].get(key), st[i].getRep(key));
				temp.N++;
			}
		}
		//after that we need to initialize it again
		this.M  = temp.M;
		this.N  = temp.N;
		this.st = temp.st;
		//System.out.println("RESIZEDD");
	}

	
	private int hash(Key key) {
		//--------------------------------------------------------
		// Summary: Generic hash method takes key and gives hash code
		// Precondition: takes key to calculate hash
		// Postcondition: The value of the key hash 
		//--------------------------------------------------------
		return (key.hashCode() & 0x7fffffff) % M;
	}


	public int size() {
		//--------------------------------------------------------
		// Summary: Returns Number of key-value pairs in symbol table
		// Postcondition: returns distinct key values. 
		//--------------------------------------------------------
		return N;
	}

	
	public boolean isEmpty() {
		//--------------------------------------------------------
		// Summary: Check if symbol table empty
		// Postcondition: returns boolean based on size
		//--------------------------------------------------------
		return size() == 0;
	}


	
	public void put(Key key, Value val) {
		//--------------------------------------------------------
		// Summary: This method helps to put keys and values into the chain of the
		// separate chaining to do that it uses SequentialSearchST .put method 
		//so this method helps to send key and value data to the inside of the linked list
		// key point is it uses the hash method to select right and specific chain array.
		// Precondition: takes key and value
		// Postcondition: it is a void method but puts key and value to its location
		//--------------------------------------------------------
		
		
		if (val == null) { 
			// if value is null it returns.
			return; 
		}

		// this is our resize condition 
		// so we need to resize if total word number/chain >= 8
		if (N >= 8*M) {
			//we use resize method
			resize(2*M);
		}
		//first hash the key
		int i = hash(key);
		
		//then use SequentialSearchST put() method and we increment N if boolean true
		if(st[i].put(key, val)) {
			N++;
			//			System.out.println(key+" "+i);
			//			System.out.println(N+" "+M);
		}else {
			//System.out.println("repeattt");
		}

	}

	public void put(Key key, Value val,int res) {
		//--------------------------------------------------------
		// Summary: This method helps for the resize case
		// since we need to stroe repeat int inside of the node we need to initialize it also
		// so I used int res as a parameter to store that thata while resizing in to another chain
		// Precondition: takes key, value and repeat integer
		// Postcondition: void method but puts keys to chain
		//--------------------------------------------------------

		int i = hash(key);
		if(st[i].put(key, val, res)) {


			//System.out.println(key+" "+i);
		}

	}

	public int findIndex(Key key) {
		//--------------------------------------------------------
		// Summary: Generic hash method takes key and gives hash code
		// Precondition: takes key to calculate hash
		// Postcondition: The value of the key hash 
		//--------------------------------------------------------
		int i = hash(key);
		int ind=st[i].findIndex(key);

		return ind;
	}


	//I created arrayList to store max repeated 3 Nodes.
	ArrayList<Node> Maxs = new ArrayList<Node>(3);


	public ArrayList<Node> FindMaxs() {
		//--------------------------------------------------------
		// Summary:This method basiclly looks for every Node of the chains and tries to find the biggest
		//repetiton  keys after finding it we use arrayList to store maximum one's.
		// I used arrayList because it is easier to make remove and set operations.
		// AFter  finding the max we use sort method to send main sorted way.
		// Precondition: Needs an arrayList of node
		// Postcondition:  returns the array list
		//--------------------------------------------------------
		
		// with nested loops and with lots of try I created a way to store max value key's
		for (int i = 0; i < M; i++) {
			st[i].current=st[i].first;
			while(st[i].current!=null) {
				//System.out.println(st[i].current.key);
				//System.out.println("New word "+st[i].current.key+" "+st[i].current.repeat);
				if(Maxs.size()<3) {
					//For first 3 we add to array list then we check for bigger ones
					Maxs.add(st[i].current);
					//System.out.println(st[i].current.key+" clueless");
					//System.out.println(st[i].getRep(Maxs.get(t))+" "+st[i].current.key);

				}else {
					//check for bigger ones
					int store=st[i].current.repeat;
					int minIndex;
					
					//	System.out.println(Maxs.get(0).key+" "+Maxs.get(0).repeat);
					//	System.out.println(Maxs.get(1).key+" "+Maxs.get(1).repeat);
					//	System.out.println(Maxs.get(2).key+" "+Maxs.get(2).repeat);


					if(Maxs.get(0).repeat > Maxs.get(1).repeat) {
						if(Maxs.get(1).repeat>Maxs.get(2).repeat){
							//System.out.println(Maxs.get(2).repeat);
							minIndex=2;
						}else {
							//System.out.println(Maxs.get(1).repeat);
							minIndex=1;
						}
					}else {
						if(Maxs.get(0).repeat > Maxs.get(2).repeat){
							//System.out.println(Maxs.get(2).repeat);
							minIndex=2;
						}else {
							//System.out.println(Maxs.get(0).repeat);
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
		
		// after that we need to sort the array before sending it
		sortArr();
		//System.out.println("here");
		//System.out.println( Maxs.get(0).key+"\n"+Maxs.get(1).key+"\n"+Maxs.get(2).key+"\n"+Maxs.get(3).key+"\n");

		return Maxs;
	}




	public void sortArr() {
		//--------------------------------------------------------
		// Summary: To sort the array list I ddidnt want to use sort arraylist method so I used insertion 
		// sort to sor the array. But before that I tried some poor ways
		// Precondition: None
		// Postcondition:  returns the sorted array list
		//--------------------------------------------------------
		
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

		// Poor sorting algorithm but I wanted to show my effort
		//----------------------------------------------------------------------------------
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
		//----------------------------------------------------------------------------------

	}

	// another helper method but it was not a good idea.
	//    public int MinIndex() {
	//    	int t=-1;
	//    	for(int i=0;i<3;i++) {
	//    		st[i].getRep(Maxs.get(t);
	//    	}
	//    }




//  Basic getters and setter method
	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}

}