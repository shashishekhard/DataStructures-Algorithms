/**
 * Tree Diameter and Height - Measuring tree dimensions.
 *
 * The DIAMETER (or width) of a binary tree is the number of edges on the longest
 * path between any two leaf nodes. This path may or may not pass through the root.
 * The HEIGHT of a tree is the number of edges on the longest downward path from root to a leaf.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Tree Diameter and Height
 * ================================================================================================
 *
 * Example Tree:
 *
 *              1
 *            /   \
 *          2       3
 *        /   \
 *       4     5
 *      /       \
 *     8         6
 *                \
 *                 7
 *
 * ----- HEIGHT Calculation -----
 *
 * Height = longest path from root to any leaf (in edges)
 *
 *              1          height(1) = max(height(2), height(3)) + 1
 *            /   \                 = max(3, 0) + 1 = 4
 *          2       3      height(2) = max(height(4), height(5)) + 1
 *        /   \                     = max(1, 2) + 1 = 3
 *       4     5           height(3) = -1 + 1 = 0 (leaf)
 *      /       \          height(4) = max(height(8), -1) + 1 = 1
 *     8         6         height(5) = max(-1, height(6)) + 1 = 2
 *                \        height(6) = max(-1, height(7)) + 1 = 1
 *                 7       height(7) = 0 (leaf)
 *                         height(8) = 0 (leaf)
 *
 *   Height of tree = 4 (path: 1 -> 2 -> 5 -> 6 -> 7)
 *
 * ----- DIAMETER Calculation -----
 *
 * Diameter = longest path between any two nodes (in edges)
 *
 * Key insight: For each node, the longest path THROUGH that node =
 *              height(left subtree) + height(right subtree) + 2
 *
 * Diameter = max over all nodes of (leftHeight + rightHeight + 2)
 *
 *              1          Through 1: h(left=2) + h(right=3) + 2 = 3 + 0 + 2 = 5
 *            /   \
 *          2       3      Through 2: h(left=4) + h(right=5) + 2 = 1 + 2 + 2 = 5
 *        /   \
 *       4     5           Through 4: h(left=8) + h(right) + 2 = 0 + (-1) + 2 = 1
 *      /       \
 *     8         6         Through 5: h(left) + h(right=6) + 2 = (-1) + 1 + 2 = 2
 *                \
 *                 7
 *
 *   Diameter = 6 (edges)
 *   Longest path: 8 -> 4 -> 2 -> 5 -> 6 -> 7
 *
 *       [8]                          The diameter path:
 *         \                          8 -- 4 -- 2 -- 5 -- 6 -- 7
 *         [4]                        (6 edges, does NOT go through root 1)
 *           \
 *           [2]
 *             \
 *             [5]
 *               \
 *               [6]
 *                 \
 *                 [7]
 *
 * ----- IS BALANCED Check -----
 *
 * A tree is height-balanced if for every node, |height(left) - height(right)| <= 1
 *
 *   Balanced:                 NOT Balanced:
 *        1                         1
 *       / \                       /
 *      2   3                     2
 *     / \                       /
 *    4   5                     3
 *
 *   |h(2)-h(3)| = |1-0| = 1   |h(2)-h(null)| = |2-(-1)| = 3 > 1
 *   BALANCED                   NOT BALANCED
 *
 * ================================================================================================
 * Time Complexity:
 *   Height:     O(n) - Visit each node once
 *   Diameter:   O(n) - Single-pass with height computation
 *   isBalanced: O(n) - Single-pass check
 *
 * Space Complexity: O(h) - Recursive call stack, h = height of tree
 * ================================================================================================
 */
package com.practice.dsalgo.trees;

/**
 * Tree dimension algorithms: height, diameter, and balance check.
 */
public class TreeDiameter {

    private static int maxDiameter;

    public static void main(String[] args) {
        /*
         * Build tree:
         *          1
         *        /   \
         *      2       3
         *    /   \
         *   4     5
         *  /       \
         * 8         6
         *            \
         *             7
         */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.left = new Node(8);
        root.left.right.right = new Node(6);
        root.left.right.right.right = new Node(7);

        System.out.println("Tree:");
        System.out.println("          1");
        System.out.println("        /   \\");
        System.out.println("      2       3");
        System.out.println("    /   \\");
        System.out.println("   4     5");
        System.out.println("  /       \\");
        System.out.println(" 8         6");
        System.out.println("            \\");
        System.out.println("             7");
        System.out.println();

        System.out.println("Height of tree: " + height(root));
        System.out.println("Diameter of tree: " + diameter(root));
        System.out.println("Is balanced: " + isBalanced(root));

        // Test with a balanced tree
        System.out.println("\n--- Balanced Tree ---");
        Node balanced = new Node(1);
        balanced.left = new Node(2);
        balanced.right = new Node(3);
        balanced.left.left = new Node(4);
        balanced.left.right = new Node(5);

        System.out.println("Tree:");
        System.out.println("      1");
        System.out.println("     / \\");
        System.out.println("    2   3");
        System.out.println("   / \\");
        System.out.println("  4   5");
        System.out.println();

        System.out.println("Height: " + height(balanced));
        System.out.println("Diameter: " + diameter(balanced));
        System.out.println("Is balanced: " + isBalanced(balanced));
    }

    /** Calculate the height of the tree (in edges). */
    public static int height(Node node) {
        if (node == null) return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /** Calculate the diameter of the tree (in edges). */
    public static int diameter(Node root) {
        maxDiameter = 0;
        heightForDiameter(root);
        return maxDiameter;
    }

    private static int heightForDiameter(Node node) {
        if (node == null) return -1;

        int leftHeight = heightForDiameter(node.left);
        int rightHeight = heightForDiameter(node.right);

        // Diameter through this node = leftHeight + rightHeight + 2
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight + 2);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /** Check if the tree is height-balanced. */
    public static boolean isBalanced(Node root) {
        return checkBalance(root) != -2;
    }

    /**
     * Returns the height if balanced, or -2 if unbalanced.
     */
    private static int checkBalance(Node node) {
        if (node == null) return -1;

        int leftHeight = checkBalance(node.left);
        if (leftHeight == -2) return -2;

        int rightHeight = checkBalance(node.right);
        if (rightHeight == -2) return -2;

        if (Math.abs(leftHeight - rightHeight) > 1) return -2;

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
