
//-----------------------------------------------------
// Title: Main
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 1
// Description: This is the main class that homework specifications asked for.
//-----------------------------------------------------

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//!! I make my tests, memory usage and time elapsed tests in my Tester.java class.
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {

	public static void main(String[] args) {
		
		//Created my hash tables
		LinearProbingHashST<String, Integer> linearProbing;
		linearProbing = new LinearProbingHashST<String, Integer>(16);
		SeparateChainingHashST<String, Integer> separateChaining;
		separateChaining = new SeparateChainingHashST<String, Integer>(16);
		String[] splited;
		//I used scanner here 

		Scanner myReader = new Scanner(System.in);
		System.out.println("Please  give an input");
		String text;
		text = myReader.nextLine();
		//Then I used split method
		//It is regex space
		splited = text.split("\\s+");

		// We started to inserting here
		
		//While making test for each hash types I commended the other one to find specific 
		//and right values.
		if(!text.isEmpty()) {

			for(int i=0;i<splited.length;i++) {  

				linearProbing.put(splited[i], splited[i].hashCode());
				separateChaining.put(splited[i], splited[i].hashCode());
			}

			//We get the max used 3 nodes using arrayList.
			//I only used this array list to store Nodes there are no usage other than that
			ArrayList<Node> Maxs = new ArrayList<Node>(3);
			System.out.println("Final table sizes for linear probing and separate chaining are "+linearProbing.getM()+" and "+ separateChaining.getM()+".\n"
					+ "Top 3 most used words, their indexes for linear probing, their node indexes for separate chaining and their number of occurrences:");

			Maxs=separateChaining.FindMaxs();
			int m;
			int k;
			
			//At this point I make a search on hash tables.
			//I used array list to get key values of max 3 words. 
			for(int n=0;n<Maxs.size();n++) {
				m=linearProbing.findIndex(Maxs.get(n).key.toString());
				k=separateChaining.findIndex(Maxs.get(n).key.toString());
				System.out.println(Maxs.get(n).key+" "+ m+" "+k+" "+Maxs.get(n).repeat);


			}

		}else {

			System.out.println("Empty Input! Please give another input next time");
			return ;
		}

	}

}
