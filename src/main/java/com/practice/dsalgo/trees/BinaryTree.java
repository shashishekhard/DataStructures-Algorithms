/**
 * Binary Tree - A fundamental tree data structure.
 *
 * A Binary Tree is a hierarchical data structure where each node has at most two children,
 * referred to as the left child and the right child. Unlike BSTs, there is no ordering
 * constraint on the values.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Binary Tree Structure and Traversals
 * ================================================================================================
 *
 * Example Tree:
 *
 *            1
 *           / \
 *          2   3
 *         / \
 *        4   5
 *
 * ----- Pre-order Traversal (Root -> Left -> Right) -----
 *
 *   Visit order: Process root first, then traverse left subtree, then right subtree.
 *
 *            [1]  <-- visit 1st
 *           / \
 *         [2]  [3]  <-- visit 2nd, then 5th
 *         / \
 *       [4] [5]  <-- visit 3rd, then 4th
 *
 *   Step 1: Visit 1         Output: 1
 *   Step 2: Visit 2         Output: 1 2
 *   Step 3: Visit 4         Output: 1 2 4
 *   Step 4: Visit 5         Output: 1 2 4 5
 *   Step 5: Visit 3         Output: 1 2 4 5 3
 *
 *   Result: [1, 2, 4, 5, 3]
 *
 * ----- In-order Traversal (Left -> Root -> Right) -----
 *
 *   Visit order: Traverse left subtree first, then process root, then right subtree.
 *
 *            1   <-- visit 3rd
 *           / \
 *          2   3  <-- visit 2nd, then 5th
 *         / \
 *        4   5   <-- visit 1st, then 4th
 *
 *   Step 1: Visit 4         Output: 4
 *   Step 2: Visit 2         Output: 4 2
 *   Step 3: Visit 5         Output: 4 2 5
 *   Step 4: Visit 1         Output: 4 2 5 1
 *   Step 5: Visit 3         Output: 4 2 5 1 3
 *
 *   Result: [4, 2, 5, 1, 3]
 *
 * ----- Post-order Traversal (Left -> Right -> Root) -----
 *
 *   Visit order: Traverse left subtree, then right subtree, then process root.
 *
 *            1   <-- visit 5th (last)
 *           / \
 *          2   3  <-- visit 3rd, then 4th
 *         / \
 *        4   5   <-- visit 1st, then 2nd
 *
 *   Step 1: Visit 4         Output: 4
 *   Step 2: Visit 5         Output: 4 5
 *   Step 3: Visit 2         Output: 4 5 2
 *   Step 4: Visit 3         Output: 4 5 2 3
 *   Step 5: Visit 1         Output: 4 5 2 3 1
 *
 *   Result: [4, 5, 2, 3, 1]
 *
 * ================================================================================================
 * KEY PROPERTIES
 * ================================================================================================
 *
 * - Maximum nodes at level l: 2^l (root is level 0)
 * - Maximum nodes in tree of height h: 2^(h+1) - 1
 * - Minimum height for n nodes: ceil(log2(n+1)) - 1
 * - Number of leaf nodes = Number of degree-2 nodes + 1
 *
 * Time Complexity (for traversals):
 *   All traversals: O(n) - Visit each node exactly once
 *
 * Space Complexity:
 *   Recursive traversals: O(h) - Call stack depth equals tree height
 *   where h = O(log n) for balanced tree, O(n) for skewed tree
 *
 * ================================================================================================
 */
package com.practice.dsalgo.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Node class representing a single node in a binary tree.
 */
class Node {
    int key;
    Node left, right;

    public Node(int key) {
        this.key = key;
        left = right = null;
    }
}

/**
 * BinaryTree with basic traversal operations and level-order (BFS) traversal.
 */
public class BinaryTree {

    Node root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(int key) {
        root = new Node(key);
    }

    /**
     * Demonstrates all tree traversals on a sample binary tree.
     */
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("Binary Tree:");
        System.out.println("        1");
        System.out.println("       / \\");
        System.out.println("      2   3");
        System.out.println("     / \\");
        System.out.println("    4   5");
        System.out.println();

        System.out.println("Pre-order Traversal (Root-Left-Right):");
        tree.printPreorder();
        System.out.println();

        System.out.println("\nIn-order Traversal (Left-Root-Right):");
        tree.printInorder();
        System.out.println();

        System.out.println("\nPost-order Traversal (Left-Right-Root):");
        tree.printPostorder();
        System.out.println();

        System.out.println("\nLevel-order Traversal (BFS):");
        tree.printLevelOrder();
        System.out.println();

        System.out.println("\nTree Height: " + tree.height(tree.root));
        System.out.println("Node Count: " + tree.countNodes(tree.root));
    }

    /* Post-order traversal: Left -> Right -> Root */
    void printPostorder(Node node) {
        if (node == null)
            return;
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.key + " ");
    }

    /* In-order traversal: Left -> Root -> Right */
    void printInorder(Node node) {
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.key + " ");
        printInorder(node.right);
    }

    /* Pre-order traversal: Root -> Left -> Right */
    void printPreorder(Node node) {
        if (node == null)
            return;
        System.out.print(node.key + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }

    /* Level-order traversal (Breadth-First Search) */
    void printLevelOrder() {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.key + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }

    /* Calculate height of the tree */
    int height(Node node) {
        if (node == null)
            return -1;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /* Count total nodes in the tree */
    int countNodes(Node node) {
        if (node == null)
            return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // Wrappers over above recursive functions
    void printPostorder() { printPostorder(root); }
    void printInorder()   { printInorder(root); }
    void printPreorder()  { printPreorder(root); }
}
