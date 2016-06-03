package ex3;

public class Node {
	private String value;
	private Node nextNode;
	
	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	public String getValue() {
		return value;
	}

	public Node(String value, Node nextNode) {
		this.value = value;
		this.nextNode = nextNode;
	}
	
	public String toString(){
		return this.value;
	}

}
