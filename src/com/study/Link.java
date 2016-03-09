package com.study;

public class Link {
	class Node{
		private String data;
		public Node(String data){
			this.data = data;
		}
		 
		private Node node;
		
		public Node getNode() {
			return node;
		}

		public void setNode(Node node) {
			this.node = node;
		}
		
		public void addNode(String data){
			Node newNode = new Node(data);
			if(node == null){
				node = newNode;
			}else{
				node.addNode(data);
			}
		}
		
		public void printNode(){
			System.out.print(this.data+"\t");
			if(node != null){
				node.printNode();
			}
		}
		
		public boolean contains(String data){
			if(data.equals(this.data)){
				return true;
			}else{
				if(node != null){
					if(node.data.equals(data)){
						return true;
					}else{
						return node.contains(data);
					}
				}
			}
			return false;
		}
		
		
	}
	
	private Node root;
	
	public void addNode(String data){
		Node newNode = new Node(data);
		if(root == null){
			root = newNode;
		}else{
			root.addNode(data);
		}
	}
	
	public void printNode(){
		if(root != null){
			root.printNode();
		}
	}
	
	public boolean searchNode(String data){
		if(root.contains(data)){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		Link l = new Link();
		l.addNode("A");
		l.addNode("B");
		l.addNode("C");
		l.addNode("D");
		l.addNode("E");
		l.printNode();
		System.out.println("≤È’“‘™Àÿ£∫"+l.searchNode("D"));
	}
}
