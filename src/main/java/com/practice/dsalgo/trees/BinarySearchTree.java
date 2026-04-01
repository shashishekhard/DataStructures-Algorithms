/**
 * Binary Search Tree (BST) - A sorted binary tree for efficient searching.
 *
 * A BST is a binary tree where for each node:
 *   - All keys in the left subtree are less than the node's key
 *   - All keys in the right subtree are greater than the node's key
 *   - Both left and right subtrees are also BSTs
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Binary Search Tree Operations
 * ================================================================================================
 *
 * ----- INSERT Operation -----
 *
 * Inserting keys: 50, 30, 70, 20, 40, 60, 80
 *
 *   Insert 50:        50
 *
 *   Insert 30:        50
 *                    /
 *                  30
 *
 *   Insert 70:        50
 *                    / \
 *                  30   70
 *
 *   Insert 20:        50
 *                    / \
 *                  30   70
 *                 /
 *               20
 *
 *   Insert 40:        50
 *                    / \
 *                  30   70
 *                 / \
 *               20  40
 *
 *   Insert 60:        50
 *                    / \
 *                  30   70
 *                 / \   /
 *               20  40 60
 *
 *   Insert 80:        50
 *                    / \
 *                  30   70
 *                 / \   / \
 *               20  40 60  80
 *
 * ----- SEARCH Operation -----
 *
 * Search for key 40 in the BST:
 *
 *          [50]          40 < 50, go LEFT
 *          / \
 *       [30]  70         40 > 30, go RIGHT
 *        / \   / \
 *      20 [40] 60  80    40 == 40, FOUND!
 *
 *   Path: 50 -> 30 -> 40 (3 comparisons)
 *
 * Search for key 55 (not in tree):
 *
 *          [50]          55 > 50, go RIGHT
 *          / \
 *        30  [70]        55 < 70, go LEFT
 *        / \   / \
 *      20  40[60] 80    55 < 60, go LEFT
 *                /
 *             null       NOT FOUND
 *
 * ----- DELETE Operation -----
 *
 * Case 1: Delete leaf node (20)
 *
 *   Before:        50              After:         50
 *                 / \                            / \
 *               30   70                        30   70
 *              / \   / \                        \   / \
 *           [20] 40 60  80                      40 60  80
 *
 *   Simply remove the node.
 *
 * Case 2: Delete node with one child (70, after removing 80)
 *
 *   Before:        50              After:         50
 *                 / \                            / \
 *               30  [70]                       30   60
 *                \   /                          \
 *                40 60                          40
 *
 *   Replace node with its child.
 *
 * Case 3: Delete node with two children (50)
 *
 *   Before:        [50]            Find inorder successor: 60
 *                 / \
 *               30   70           Replace 50 with 60, delete old 60
 *                \   / \
 *                40 60  80
 *
 *   After:         60
 *                 / \
 *               30   70
 *                \     \
 *                40    80
 *
 *   Replace with inorder successor (smallest in right subtree), then delete successor.
 *
 * ----- In-order Traversal of BST gives SORTED output -----
 *
 *          50
 *         / \
 *       30   70          In-order: 20, 30, 40, 50, 60, 70, 80
 *      / \   / \
 *    20  40 60  80
 *
 * ================================================================================================
 * Time Complexity:
 *   Search:  O(h) where h = height; O(log n) average, O(n) worst (skewed)
 *   Insert:  O(h) where h = height; O(log n) average, O(n) worst (skewed)
 *   Delete:  O(h) where h = height; O(log n) average, O(n) worst (skewed)
 *
 * Space Complexity: O(n) for storing n nodes; O(h) for recursive operations
 * ================================================================================================
 */
package com.practice.dsalgo.trees;

/**
 * Binary Search Tree implementation with insert, search, delete, and traversal.
 */
public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert keys
        int[] keys = {50, 30, 70, 20, 40, 60, 80};
        System.out.println("Inserting keys: 50, 30, 70, 20, 40, 60, 80");
        for (int key : keys) {
            bst.insert(key);
        }

        System.out.println("\nIn-order traversal (sorted order):");
        bst.inorder();
        System.out.println();

        // Search
        System.out.println("\nSearch for 40: " + (bst.search(40) ? "Found" : "Not Found"));
        System.out.println("Search for 55: " + (bst.search(55) ? "Found" : "Not Found"));

        // Delete leaf
        System.out.println("\nDelete 20 (leaf node):");
        bst.delete(20);
        bst.inorder();
        System.out.println();

        // Delete node with one child
        System.out.println("\nDelete 70 (node with two children):");
        bst.delete(70);
        bst.inorder();
        System.out.println();

        // Delete node with two children
        System.out.println("\nDelete 50 (root, node with two children):");
        bst.delete(50);
        bst.inorder();
        System.out.println();

        // Find min and max
        System.out.println("\nMinimum value: " + bst.findMin());
        System.out.println("Maximum value: " + bst.findMax());
    }

    /** Insert a key into the BST. */
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else if (key > node.key) {
            node.right = insertRec(node.right, key);
        }
        return node;
    }

    /** Search for a key in the BST. */
    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(Node node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        if (key < node.key) return searchRec(node.left, key);
        return searchRec(node.right, key);
    }

    /** Delete a key from the BST. */
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node node, int key) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = deleteRec(node.left, key);
        } else if (key > node.key) {
            node.right = deleteRec(node.right, key);
        } else {
            // Node found - handle three cases
            // Case 1 & 2: No child or one child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Case 3: Two children - find inorder successor
            Node successor = findMinNode(node.right);
            node.key = successor.key;
            node.right = deleteRec(node.right, successor.key);
        }
        return node;
    }

    /** Find minimum value in the BST. */
    public int findMin() {
        if (root == null) throw new RuntimeException("Tree is empty");
        return findMinNode(root).key;
    }

    /** Find maximum value in the BST. */
    public int findMax() {
        if (root == null) throw new RuntimeException("Tree is empty");
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }

    private Node findMinNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /** In-order traversal (prints sorted order). */
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.key + " ");
            inorderRec(node.right);
        }
    }
}
