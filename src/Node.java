//-----------------------------------------------------
// Title: Node Class
// Author: Gökmen ÇAĞLAR
// ID: 12590403284
// Section: 1
// Assignment: 1
// Description: It is the most basic Node class
//-----------------------------------------------------
//I used to create my linked list 


public class Node<Key, Value> {
	public Key key;
	public Value val;
	public Node next;
	public int repeat;

	public Node(Key key, Value val, Node next)  {

		this.key  = key;
		this.val  = val;
		this.next = next;
		repeat=1;
	}
}