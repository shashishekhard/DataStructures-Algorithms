/**
 * AVL Tree - A self-balancing Binary Search Tree.
 *
 * An AVL tree maintains the BST property and additionally ensures that for every node,
 * the heights of the left and right subtrees differ by at most 1 (balance factor: -1, 0, +1).
 * When this property is violated after an insertion or deletion, rotations are performed
 * to restore balance.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - AVL Tree Rotations
 * ================================================================================================
 *
 * ----- Balance Factor = height(left) - height(right) -----
 *
 * Balanced (BF = -1, 0, +1):     Unbalanced (BF = +2 or -2):
 *
 *        30 (BF=0)                    30 (BF=+2)   <-- VIOLATION!
 *       / \                          /
 *     20   40                      20
 *                                 /
 *                               10
 *
 * ----- Right Rotation (LL Case) -----
 *
 * When: Left-Left imbalance (inserted into left subtree of left child)
 *
 *       [30] BF=+2                    20
 *       /                            / \
 *     [20] BF=+1       ---->       10   30
 *     /
 *   [10]
 *
 *   Steps:
 *   1. Node 30's left child (20) becomes the new root of this subtree
 *   2. Node 30 becomes the right child of 20
 *   3. If 20 had a right child, it becomes the left child of 30
 *
 * ----- Left Rotation (RR Case) -----
 *
 * When: Right-Right imbalance (inserted into right subtree of right child)
 *
 *   [10] BF=-2                        20
 *      \                             / \
 *     [20] BF=-1      ---->        10   30
 *        \
 *       [30]
 *
 *   Steps:
 *   1. Node 10's right child (20) becomes the new root of this subtree
 *   2. Node 10 becomes the left child of 20
 *   3. If 20 had a left child, it becomes the right child of 10
 *
 * ----- Left-Right Rotation (LR Case) -----
 *
 * When: Left-Right imbalance (inserted into right subtree of left child)
 *
 *       [30] BF=+2                [30]                      25
 *       /                         /                        / \
 *     [20] BF=-1    Left rot    [25]      Right rot      20   30
 *        \          on 20       /         on 30
 *       [25]                  [20]
 *
 *   Steps:
 *   1. Left rotate on the left child (20)
 *   2. Right rotate on the unbalanced node (30)
 *
 * ----- Right-Left Rotation (RL Case) -----
 *
 * When: Right-Left imbalance (inserted into left subtree of right child)
 *
 *   [10] BF=-2                  [10]                       15
 *      \                           \                      / \
 *     [20] BF=+1    Right rot     [15]    Left rot      10   20
 *     /             on 20            \    on 10
 *   [15]                           [20]
 *
 *   Steps:
 *   1. Right rotate on the right child (20)
 *   2. Left rotate on the unbalanced node (10)
 *
 * ----- Full Insertion Example -----
 *
 * Insert sequence: 10, 20, 30, 40, 50, 25
 *
 *   Insert 10:     10
 *
 *   Insert 20:     10           (balanced)
 *                    \
 *                    20
 *
 *   Insert 30:     10  BF=-2    Left rotation      20
 *                    \          ============>      / \
 *                    20                          10   30
 *                      \
 *                      30
 *
 *   Insert 40:        20                           20
 *                    / \                           / \
 *                  10   30  BF=-1                10   30
 *                         \       (balanced)            \
 *                         40                            40
 *
 *   Insert 50:        20  BF=-2 at 30             20
 *                    / \          Left rot        / \
 *                  10   30        on 30         10   40
 *                         \       =====>            / \
 *                         40                      30   50
 *                           \
 *                           50
 *
 *   Insert 25:        20                           20
 *                    / \                           / \
 *                  10   40       (balanced)      10   40
 *                      / \                           / \
 *                    30   50                       30   50
 *                                                 /
 *                                               25
 *
 *   (25 goes left of 30, tree remains balanced)
 *
 * ================================================================================================
 * Time Complexity:
 *   Search:  O(log n) - Always balanced, height = O(log n)
 *   Insert:  O(log n) - At most 2 rotations after insertion
 *   Delete:  O(log n) - At most O(log n) rotations after deletion
 *
 * Space Complexity: O(n) for storing n nodes; O(log n) for recursive operations
 * ================================================================================================
 */
package com.practice.dsalgo.trees;

/**
 * AVL Tree implementation with self-balancing insert and delete.
 */
public class AVLTree {

    /** AVL Node with height field. */
    private static class AVLNode {
        int key, height;
        AVLNode left, right;

        AVLNode(int key) {
            this.key = key;
            this.height = 0;
        }
    }

    private AVLNode root;

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        int[] keys = {10, 20, 30, 40, 50, 25};
        System.out.println("Inserting keys: 10, 20, 30, 40, 50, 25");
        for (int key : keys) {
            tree.insert(key);
        }

        System.out.println("\nIn-order traversal (sorted):");
        tree.inorder();
        System.out.println();

        System.out.println("\nPre-order traversal (shows structure):");
        tree.preorder();
        System.out.println();

        System.out.println("\nTree height: " + tree.getHeight());

        System.out.println("\nDelete 40:");
        tree.delete(40);
        System.out.println("In-order after deleting 40:");
        tree.inorder();
        System.out.println();

        System.out.println("\nDelete 20:");
        tree.delete(20);
        System.out.println("In-order after deleting 20:");
        tree.inorder();
        System.out.println();

        System.out.println("\nPre-order (shows rebalanced structure):");
        tree.preorder();
        System.out.println();
    }

    private int height(AVLNode node) {
        return node == null ? -1 : node.height;
    }

    private int balanceFactor(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private void updateHeight(AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    /** Right rotation (LL case). */
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        updateHeight(y);
        updateHeight(x);
        return x;
    }

    /** Left rotation (RR case). */
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        updateHeight(x);
        updateHeight(y);
        return y;
    }

    /** Rebalance a node if its balance factor is outside [-1, +1]. */
    private AVLNode balance(AVLNode node) {
        int bf = balanceFactor(node);

        // Left-heavy
        if (bf > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left); // LR case
            }
            return rightRotate(node); // LL case
        }

        // Right-heavy
        if (bf < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right); // RL case
            }
            return leftRotate(node); // RR case
        }

        return node;
    }

    /** Insert a key into the AVL tree. */
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private AVLNode insertRec(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);

        if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else if (key > node.key) {
            node.right = insertRec(node.right, key);
        } else {
            return node; // duplicate keys not allowed
        }

        updateHeight(node);
        return balance(node);
    }

    /** Delete a key from the AVL tree. */
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private AVLNode deleteRec(AVLNode node, int key) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = deleteRec(node.left, key);
        } else if (key > node.key) {
            node.right = deleteRec(node.right, key);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            AVLNode successor = findMin(node.right);
            node.key = successor.key;
            node.right = deleteRec(node.right, successor.key);
        }

        updateHeight(node);
        return balance(node);
    }

    private AVLNode findMin(AVLNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /** Search for a key. */
    public boolean search(int key) {
        AVLNode current = root;
        while (current != null) {
            if (key == current.key) return true;
            current = key < current.key ? current.left : current.right;
        }
        return false;
    }

    /** Get tree height. */
    public int getHeight() {
        return height(root);
    }

    /** In-order traversal. */
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(AVLNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.key + " ");
            inorderRec(node.right);
        }
    }

    /** Pre-order traversal. */
    public void preorder() {
        preorderRec(root);
    }

    private void preorderRec(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + "(h=" + node.height + ") ");
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }
}
