package server;

import java.util.Comparator;
import java.util.Random;
import javafx.util.Pair;

public class BinaryTreeSort extends Sort {
	
	private final Comparator<Integer> comp;
	
	public BinaryTreeSort(Integer[] Vet, Boolean order){
		super(Vet, order);
		
		if(naturalOrder)
			comp = Comparator.<Integer>naturalOrder();
		else 
			comp = Comparator.<Integer>reverseOrder();
	}

	@Override
	public Integer[] sort() {
		
		if(vet == null || vet.length < 2)
			return vet;
		
		CartesianTree tree = new CartesianTree();
		for(int i = 0; i < vet.length; i++)
			tree.insert(vet[i]);

		tree.traverse(vet);
		return vet;
	}
	
	private class CartesianTree{
		private Random rand;
		
		private class Node{
			public Integer value, priority;
			private Integer size;
			public Node left, right;

			Node(int val){
				value = val;
				priority = rand.nextInt();
				size = 1;
				left = null;
				right = null;
			}
			
			public Integer getSize() {
				return size;
			}
			
			public void updSize() {
				size = 1;
				if(left != null) {
					size += left.getSize();
				}
				if(right != null) {
					size += right.getSize();
				}
			}
			
		}
		
		public Node root;
		
		public CartesianTree(){
			rand = new Random();
			root = null;
		}
		
		private Node merge(Node l, Node r){
			if(l == null)
				return r;
			if(r == null)
				return l;
			
			if(l.priority > r.priority){
				l.right = merge(l.right, r);
				l.updSize();
				return l;
			}
			else {
				r.left = merge(l, r.left);
				r.updSize();
				return r;
			}
		}
		
		private Pair<Node,Node> split(Node node, int v){
			if(node == null){
				return new Pair<Node,Node>(null,null);
			}
			if(comp.compare(node.value, v) <= 0){
				Pair<Node,Node> returned = split(node.right, v);
				node.right = returned.getKey();
				node.updSize();
				return new Pair<Node,Node>(node, returned.getValue());
			}
			else{
				Pair<Node,Node> returned = split(node.left, v);
				node.left = returned.getValue();
				node.updSize();
				return new Pair<Node,Node>(returned.getKey(), node);
			}
		}
		
		public void insert(Integer val) {
			Pair<Node,Node> returned = split(root, val);
			root = merge(returned.getKey(), merge(new Node(val), returned.getValue()));
		}
		
		public Integer[] traverse(Integer[] vet) {
			if(root == null)
				return null;
			traverse(root, vet, 0);
			return vet;
		}
		
		private Integer traverse(Node node, Integer[] vet, int pos) {
			if(node == null) {
				return pos;
			}
			pos = traverse(node.left, vet, pos);
			vet[pos++] = node.value;
			return traverse(node.right, vet, pos);
		}
		
	}
}
