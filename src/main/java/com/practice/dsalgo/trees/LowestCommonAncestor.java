/**
 * Lowest Common Ancestor (LCA) - Finding the deepest common parent of two nodes.
 *
 * The Lowest Common Ancestor of two nodes p and q in a tree is the deepest node
 * that is an ancestor of both p and q. A node can be an ancestor of itself.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Lowest Common Ancestor
 * ================================================================================================
 *
 * Example Tree:
 *
 *                  3
 *                /   \
 *              5       1
 *            /   \   /   \
 *           6    2  0     8
 *               / \
 *              7   4
 *
 * ----- LCA(5, 1) = 3 -----
 *
 *                 [3]    <-- LCA: both 5 and 1 are in different subtrees of 3
 *                /   \
 *             [5]    [1]
 *            /   \   / \
 *           6    2  0   8
 *
 *   Path to 5: 3 -> 5
 *   Path to 1: 3 -> 1
 *   Paths diverge at: 3  => LCA = 3
 *
 * ----- LCA(5, 4) = 5 -----
 *
 *                  3
 *                /   \
 *             [5]      1      <-- LCA: 5 is ancestor of both 5 and 4
 *            /   \   /   \
 *           6   [2] 0     8
 *               / \
 *              7  [4]
 *
 *   Path to 5: 3 -> 5
 *   Path to 4: 3 -> 5 -> 2 -> 4
 *   Last common node: 5  => LCA = 5
 *
 * ----- LCA(6, 4) = 5 -----
 *
 *                  3
 *                /   \
 *             [5]      1      <-- LCA: 6 is in left, 4 is in right subtree of 5
 *            /   \   /   \
 *          [6]  [2] 0     8
 *               / \
 *              7  [4]
 *
 *   Path to 6: 3 -> 5 -> 6
 *   Path to 4: 3 -> 5 -> 2 -> 4
 *   Last common node: 5  => LCA = 5
 *
 * ----- LCA(7, 8) = 3 -----
 *
 *                 [3]         <-- LCA: 7 is in left subtree, 8 is in right subtree
 *                /   \
 *             [5]    [1]
 *            /   \   / \
 *           6   [2] 0  [8]
 *               / \
 *             [7]  4
 *
 * ----- Algorithm (Recursive) -----
 *
 *   For each node:
 *     1. If node is null, return null
 *     2. If node == p or node == q, return node (found one target)
 *     3. Recurse on left subtree  -> left result
 *     4. Recurse on right subtree -> right result
 *     5. If both left and right are non-null: current node is LCA
 *     6. Otherwise, return whichever is non-null
 *
 *   Example trace for LCA(6, 4):
 *
 *   lca(3, 6, 4):
 *     left = lca(5, 6, 4):
 *       left = lca(6, 6, 4):
 *         6 == 6, return 6        <-- found p
 *       right = lca(2, 6, 4):
 *         left = lca(7, 6, 4): return null
 *         right = lca(4, 6, 4):
 *           4 == 4, return 4      <-- found q
 *         return 4
 *       left=6, right=4 => both non-null => return 5 (this is LCA!)
 *     right = lca(1, 6, 4):
 *       left = lca(0, 6, 4): return null
 *       right = lca(8, 6, 4): return null
 *       return null
 *     left=5, right=null => return 5
 *   Answer: LCA(6, 4) = 5
 *
 * ================================================================================================
 * Time Complexity:  O(n) - Visit each node at most once
 * Space Complexity: O(h) - Recursive call stack, h = height of tree
 * ================================================================================================
 */
package com.practice.dsalgo.trees;

/**
 * Lowest Common Ancestor implementation for binary trees.
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        /*
         * Build tree:
         *          3
         *        /   \
         *      5       1
         *    /   \   /   \
         *   6    2  0     8
         *       / \
         *      7   4
         */
        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.right.left = new Node(0);
        root.right.right = new Node(8);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);

        System.out.println("Tree:");
        System.out.println("          3");
        System.out.println("        /   \\");
        System.out.println("      5       1");
        System.out.println("    /   \\   /   \\");
        System.out.println("   6    2  0     8");
        System.out.println("       / \\");
        System.out.println("      7   4");
        System.out.println();

        // LCA queries
        System.out.println("LCA(5, 1) = " + findLCA(root, 5, 1));
        System.out.println("LCA(5, 4) = " + findLCA(root, 5, 4));
        System.out.println("LCA(6, 4) = " + findLCA(root, 6, 4));
        System.out.println("LCA(7, 8) = " + findLCA(root, 7, 8));
        System.out.println("LCA(7, 4) = " + findLCA(root, 7, 4));
        System.out.println("LCA(6, 2) = " + findLCA(root, 6, 2));
        System.out.println("LCA(3, 4) = " + findLCA(root, 3, 4));
    }

    /**
     * Find LCA of two nodes with given values.
     * Returns the value of the LCA node, or -1 if not found.
     */
    public static int findLCA(Node root, int p, int q) {
        Node lca = lcaRec(root, p, q);
        return lca != null ? lca.key : -1;
    }

    /**
     * Recursive LCA finder.
     * Returns the LCA node if both p and q are present in the subtree,
     * otherwise returns whichever node (p or q) is found.
     */
    private static Node lcaRec(Node node, int p, int q) {
        if (node == null) return null;

        // If current node matches either target, return it
        if (node.key == p || node.key == q) return node;

        // Recurse on left and right subtrees
        Node leftResult = lcaRec(node.left, p, q);
        Node rightResult = lcaRec(node.right, p, q);

        // If both sides returned non-null, this node is the LCA
        if (leftResult != null && rightResult != null) return node;

        // Otherwise, return whichever side found a target
        return leftResult != null ? leftResult : rightResult;
    }
}
