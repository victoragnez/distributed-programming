import java.util.Comparator;
import java.util.Random;

public class BinaryTreeSort extends Sort {
	
	public BinaryTreeSort(){
		super();
	}
	
	public BinaryTreeSort(Integer[] Vet){
		super(Vet);
	}
	
	public BinaryTreeSort(Integer[] Vet, Comparator<Integer> Comp){
		super(Vet, Comp);
	}
	
	public void test() {
		super.smallTests();
		super.bigElementsTests();
		super.bigArrayTests();
		super.bigTests();
	}
	
	@Override
	protected Integer[] sort() {
		if(vet == null || vet.length < 2)
			return vet;
		
		CartesianTree tree = new CartesianTree();
		for(int i = 0; i < vet.length; i++)
			tree.insert(vet[i]);

		vet = tree.traverse();
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
		
		private void split(Node node, int v, Node a, Node b){
			if(node == null){
				a = b = null;
				return;
			}
			if(comp.compare(node.value, v) <= 0){
				split(node.right, v, node.right, b);
				a = node;
				a.updSize();
			}else{
				split(node.left, v, a, node.left);
				b = node;
				b.updSize();
			}
		}
		
		public void insert(Integer val) {
			Node a = null, b = null;
			split(root, val, a, b);
			root = merge(a, merge(new Node(val), b));
		}
		
		public Integer[] traverse() {
			if(root == null)
				return null;
			Integer [] ret = new Integer[root.getSize()];
			traverse(root, ret, 0);
			return ret;
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
