/**
 * Red-Black Tree - A self-balancing Binary Search Tree with color properties.
 *
 * A Red-Black Tree is a BST where each node has a color (red or black) and satisfies:
 *   1. Every node is either red or black
 *   2. The root is always black
 *   3. Every null leaf (NIL) is black
 *   4. If a node is red, both its children must be black (no two consecutive reds)
 *   5. Every path from a node to its descendant NIL nodes has the same number of black nodes
 *
 * These properties guarantee the tree height is at most 2*log2(n+1), ensuring O(log n) operations.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Red-Black Tree
 * ================================================================================================
 *
 * Legend: (B) = Black node, (R) = Red node
 *
 * ----- Insertion Example -----
 *
 * Insert sequence: 10, 20, 30, 15, 25, 5, 1
 *
 *   Insert 10:     10(B)               Root is always black
 *
 *   Insert 20:     10(B)               Red child of black parent -> OK
 *                    \
 *                    20(R)
 *
 *   Insert 30:     10(B)               Red child of red parent -> VIOLATION!
 *                    \                  Uncle is NIL (black)
 *                    20(R)              Case: Left rotation on 10
 *                      \
 *                      30(R)
 *
 *   After fix:     20(B)               Left rotate, recolor
 *                 /    \
 *              10(R)   30(R)
 *
 *   Insert 15:     20(B)               Parent(10) is red, Uncle(30) is red
 *                 /    \                -> Recolor parent & uncle to black,
 *              10(R)   30(R)              grandparent to red (but root stays black)
 *                \
 *                15(R)
 *
 *   After fix:     20(B)               Recolor: 10->B, 30->B, 20 stays B (root)
 *                 /    \
 *              10(B)   30(B)
 *                \
 *                15(R)
 *
 *   Insert 25:     20(B)               Parent(30) is black -> OK
 *                 /    \
 *              10(B)   30(B)
 *                \     /
 *                15(R) 25(R)
 *
 *   Insert 5:      20(B)               Parent(10) is black -> OK
 *                 /    \
 *              10(B)   30(B)
 *             /   \     /
 *           5(R) 15(R) 25(R)
 *
 *   Insert 1:      20(B)               Parent(5) red, Uncle(15) red
 *                 /    \                -> Recolor: 5->B, 15->B, 10->R
 *              10(B)   30(B)
 *             /   \     /
 *           5(R) 15(R) 25(R)
 *          /
 *        1(R)
 *
 *   After fix:     20(B)               Recolor propagation
 *                 /    \               10 becomes red, then check 10's parent (20 is black) -> OK
 *              10(R)   30(B)
 *             /   \     /
 *           5(B) 15(B) 25(R)
 *          /
 *        1(R)
 *
 * ----- Rotation Types (same as AVL) -----
 *
 *   Left Rotation:          Right Rotation:
 *
 *     x                         y
 *      \          -->           / \
 *       y                     x   c
 *      / \        <--          \
 *     b   c                    b
 *
 * ----- Fix-up Cases After Insertion -----
 *
 *   Case 1: Uncle is RED
 *     -> Recolor parent & uncle to BLACK, grandparent to RED
 *     -> Move violation up to grandparent
 *
 *        G(B)              G(R)
 *       / \      -->      / \
 *     P(R) U(R)         P(B) U(B)
 *     /                 /
 *   X(R)              X(R)
 *
 *   Case 2: Uncle is BLACK, node is "inner" child (zig-zag)
 *     -> Rotate parent to make it Case 3
 *
 *        G(B)              G(B)
 *       / \      -->      / \
 *     P(R) U(B)         X(R) U(B)
 *       \               /
 *       X(R)          P(R)
 *
 *   Case 3: Uncle is BLACK, node is "outer" child (zig-zig)
 *     -> Rotate grandparent, swap colors of parent and grandparent
 *
 *        G(B)              P(B)
 *       / \      -->      / \
 *     P(R) U(B)         X(R) G(R)
 *     /                        \
 *   X(R)                      U(B)
 *
 * ================================================================================================
 * Time Complexity:
 *   Search:  O(log n) - Height is at most 2*log2(n+1)
 *   Insert:  O(log n) - At most 2 rotations, O(log n) recoloring
 *   Delete:  O(log n) - At most 3 rotations, O(log n) recoloring
 *
 * Space Complexity: O(n) for storing n nodes
 *
 * Comparison with AVL Tree:
 *   - AVL is more strictly balanced (faster lookups)
 *   - Red-Black has fewer rotations on insert/delete (faster modifications)
 *   - Java's TreeMap and TreeSet use Red-Black Trees
 * ================================================================================================
 */
package com.practice.dsalgo.trees;

/**
 * Red-Black Tree implementation with insert and in-order traversal.
 */
public class RedBlackTree {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class RBNode {
        int key;
        boolean color;
        RBNode left, right, parent;

        RBNode(int key) {
            this.key = key;
            this.color = RED; // new nodes are always red
        }
    }

    private RBNode root;

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        int[] keys = {10, 20, 30, 15, 25, 5, 1};
        System.out.println("Inserting keys: 10, 20, 30, 15, 25, 5, 1\n");

        for (int key : keys) {
            tree.insert(key);
            System.out.println("After inserting " + key + ":");
            System.out.print("  In-order: ");
            tree.inorder();
            System.out.println();
            System.out.println("  Root: " + tree.root.key + "(" + (tree.root.color == BLACK ? "B" : "R") + ")");
        }

        System.out.println("\nFinal tree in-order traversal with colors:");
        tree.inorderWithColor();
        System.out.println();

        System.out.println("\nSearch 15: " + tree.search(15));
        System.out.println("Search 99: " + tree.search(99));
    }

    /** Insert a key into the Red-Black Tree. */
    public void insert(int key) {
        RBNode node = new RBNode(key);

        // Standard BST insert
        if (root == null) {
            root = node;
            root.color = BLACK;
            return;
        }

        RBNode current = root;
        RBNode parent = null;

        while (current != null) {
            parent = current;
            if (key < current.key) {
                current = current.left;
            } else if (key > current.key) {
                current = current.right;
            } else {
                return; // duplicate
            }
        }

        node.parent = parent;
        if (key < parent.key) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        // Fix Red-Black properties
        fixInsert(node);
    }

    /** Fix Red-Black Tree violations after insertion. */
    private void fixInsert(RBNode node) {
        while (node != root && node.parent.color == RED) {
            RBNode parent = node.parent;
            RBNode grandparent = parent.parent;

            if (grandparent == null) break;

            if (parent == grandparent.left) {
                RBNode uncle = grandparent.right;

                if (uncle != null && uncle.color == RED) {
                    // Case 1: Uncle is red -> recolor
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        // Case 2: Uncle is black, node is right child -> left rotate parent
                        node = parent;
                        leftRotate(node);
                        parent = node.parent;
                        grandparent = parent.parent;
                    }
                    // Case 3: Uncle is black, node is left child -> right rotate grandparent
                    parent.color = BLACK;
                    grandparent.color = RED;
                    rightRotate(grandparent);
                }
            } else {
                // Mirror: parent is right child of grandparent
                RBNode uncle = grandparent.left;

                if (uncle != null && uncle.color == RED) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        rightRotate(node);
                        parent = node.parent;
                        grandparent = parent.parent;
                    }
                    parent.color = BLACK;
                    grandparent.color = RED;
                    leftRotate(grandparent);
                }
            }
        }
        root.color = BLACK;
    }

    /** Left rotation. */
    private void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    /** Right rotation. */
    private void rightRotate(RBNode y) {
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != null) x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }

    /** Search for a key. */
    public boolean search(int key) {
        RBNode current = root;
        while (current != null) {
            if (key == current.key) return true;
            current = key < current.key ? current.left : current.right;
        }
        return false;
    }

    /** In-order traversal. */
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(RBNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.key + " ");
            inorderRec(node.right);
        }
    }

    /** In-order traversal showing colors. */
    public void inorderWithColor() {
        inorderColorRec(root);
    }

    private void inorderColorRec(RBNode node) {
        if (node != null) {
            inorderColorRec(node.left);
            System.out.print(node.key + "(" + (node.color == BLACK ? "B" : "R") + ") ");
            inorderColorRec(node.right);
        }
    }
}
