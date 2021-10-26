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