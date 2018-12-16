/**
 * 
 */
package com.practice.dsalgo.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author shashishekhar
 *
 */

class Node {
	int key;
	Node left,right;
	
	public Node(int key){
		this.key = key;
		left = right = null;
	}
	
	public void print(){
		System.out.println(this.key);
	}
}
public class BinaryTree {
	
	Node root;
	
	public BinaryTree(){
		root = null;
	}
	
	public BinaryTree(int key){
		root = new Node(key);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
		
		//tree.root.print();tree.root.left.print();tree.root.right.print();
		
		System.out.println("Pre Order:");
		tree.printPreorder();
		System.out.println("\nPost Order:");
		tree.printPostorder();
		System.out.println("\nin Order:");
		tree.printInorder();
		
		//System.out.println("\nBreadth First Traversal:");
		//tree.bft();
		
	}
	
	/* Given a binary tree, print its nodes according to the
    "bottom-up" postorder traversal. */
  void printPostorder(Node node)
  {
      if (node == null)
          return;

      // first recur on left subtree
      printPostorder(node.left);

      // then recur on right subtree
      printPostorder(node.right);

      // now deal with the node
      System.out.print(node.key + " ");
  }

  /* Given a binary tree, print its nodes in inorder*/
  void printInorder(Node node)
  {
      if (node == null)
          return;

      /* first recur on left child */
      printInorder(node.left);

      /* then print the data of node */
      System.out.print(node.key + " ");

      /* now recur on right child */
      printInorder(node.right);
  }

  /* Given a binary tree, print its nodes in preorder*/
  void printPreorder(Node node)
  {
      if (node == null)
          return;

      /* first print data of node */
      System.out.print(node.key + " ");

      /* then recur on left sutree */
      printPreorder(node.left);

      /* now recur on right subtree */
      printPreorder(node.right);
  }

  // Wrappers over above recursive functions
  void printPostorder()  {     printPostorder(root);  }
  void printInorder()    {     printInorder(root);   }
  void printPreorder()   {     printPreorder(root);  }
  
  /*public void bft(){
	  Queue<Node> queue = new LinkedList<Node>();
	  queue.add(root);
	  
	  while(!queue.isEmpty()){
		  Node node = queue.poll();
		  
		  System.out.print(node.key + " ");
		  
		  if(node.left != null) queue.add(node.left);
		  if(node.right != null) queue.add(node.right);
	  }
	  
  }*/

}
