//------------------------------------------------------
// Title: Tester Class
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 1
// Description: In this class I make my tests. I need this class because reading from scanner
// is not enough for big test cases. So I wanted to read from .txt file line by line
// so I created this tester class
//-----------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files



public class Tester {

	public static void main(String[] args) {

		//Created my hash tables
		LinearProbingHashST<String, Integer> linearProbing;
		linearProbing = new LinearProbingHashST<String, Integer>(16);
		SeparateChainingHashST<String, Integer> separateChaining;
		separateChaining = new SeparateChainingHashST<String, Integer>(16);
		String[] splited;
		System.out.println("This is a tester class this class takes inputs from .txt file\n");
		String text;
		text="a";

			if(!text.isEmpty()) {
				
				//To read line by line from .txt file this section starts 
				//After dividing to lines I used .split to insert hash tables.

			try {
				File myObj = new File("input.txt");
				Scanner myReader1 = new Scanner(myObj);
				
				// To check the time and memory I created memory and time starters.
				//and a counter
				long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
				long startTime = System.currentTimeMillis();
				int counter = 0;

				//Until there is no other line
				while (myReader1.hasNextLine()) {
					String data = myReader1.nextLine();
					
					//It is regex space
					splited = data.split("\\s+");
					counter+=splited.length;
					
					// We started to inserting here
					
					//While making test for each hash types I commended the other one to find specific 
					//and right values.
					for(int i=0;i<splited.length;i++) {  

						linearProbing.put(splited[i], splited[i].hashCode());
						separateChaining.put(splited[i], splited[i].hashCode());
					}
				}
				// To check the time and memory I stopped memory and time starters.
				
				long stopTime = System.currentTimeMillis();
				long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
				long elapsedTime = (stopTime - startTime);
				long actualMemUsed=(afterUsedMem-beforeUsedMem)/1000;

				//With the basic subs operation now we can find out memmory and time.
				
				//Now we can print them
				System.out.println("Total word counter :"+counter);
				System.out.println(actualMemUsed+" used memory");
				System.out.println(elapsedTime+" elapsed time");
				System.out.println("Distinct key numbers :"+separateChaining.size()+"\n");


				myReader1.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}


			//We get the max used 3 nodes using arrayList.
			//I only used this array list to store Nodes there are no usage other than that
			ArrayList<Node> Maxs = new ArrayList<Node>(3);
			System.out.println("Final table sizes for linear probing and separate chaining are "+linearProbing.getM()+" and "+ separateChaining.getM()+".\n"
					+ "Top 3 most used words, their indexes for linear probing, their node indexes for separate chaining and their number of occurrences:");

			Maxs=separateChaining.FindMaxs();
			int m;
			int k;
			//To test to search BigO() I used another time start and stop.
			long startTime1 = System.currentTimeMillis();
			
			//At this point I make a search on hash tables.
			//I used array list to get key values of max 3 words. 
			for(int n=0;n<Maxs.size();n++) {
				m=linearProbing.findIndex(Maxs.get(n).key.toString());
				k=separateChaining.findIndex(Maxs.get(n).key.toString());
				System.out.println(Maxs.get(n).key+" "+ m+" "+k+" "+Maxs.get(n).repeat);
			}
			
			//after that we know the elapsed time
			//similarly I used commends to see specific elapsed time again
			long stopTime1 = System.currentTimeMillis();
			long elapsedTime1 = (stopTime1 - startTime1);
			
			System.out.println("\nTo get the key value elapsed time "+elapsedTime1 );

		}else {

			System.out.println("Empty Input! Please give another input next time");
			return ;
		}

	}
	//At the beginning I started my tests with this primitive way
	
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

}
