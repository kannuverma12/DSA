package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.TreeNode;

/**
 * LC 297 · Serialize and Deserialize Binary Tree (Hard)   [NEW - design-flavoured, common at Google]
 *
 * Pattern : preorder with a null marker. Preorder + nulls determines the tree uniquely, so
 *           deserialisation is one recursive pass with a cursor.
 * Optimal : O(n) time and space both ways.
 *
 * Discussion points that separate L6+ answers:
 *  - Format: delimiter choice, escaping, versioning; binary encoding for size.
 *  - Recursion depth on a degenerate tree (1e5 nodes) -> iterative version / BFS format.
 *  - BST only (LC 449): nulls aren't needed, since bounds recover structure -> more compact.
 */
public class LC0297_SerializeDeserializeBinaryTree {

    private static final String NULL = "#";
    private static final char SEP = ',';

    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        write(root, sb);
        return sb.toString();
    }

    private static void write(TreeNode n, StringBuilder sb) {
        if (sb.length() > 0) {
            sb.append(SEP);
        }
        if (n == null) {
            sb.append(NULL);
            return;
        }
        sb.append(n.val);
        write(n.left, sb);
        write(n.right, sb);
    }

    public static TreeNode deserialize(String data) {
        return read(data.split(String.valueOf(SEP)), new int[] {0});
    }

    private static TreeNode read(String[] tokens, int[] cursor) {
        String t = tokens[cursor[0]++];
        if (t.equals(NULL)) {
            return null;
        }
        TreeNode n = new TreeNode(Integer.parseInt(t));
        n.left = read(tokens, cursor);
        n.right = read(tokens, cursor);
        return n;
    }

    public static void main(String[] args) {
        TreeNode t = TreeNode.of(1, 2, 3, null, null, 4, 5, -6);
        String s = serialize(t);
        Check.eq(s, "1,2,#,#,3,4,-6,#,#,#,5,#,#");
        Check.eq(TreeNode.toLevelOrder(deserialize(s)), TreeNode.toLevelOrder(t));
        Check.isTrue(deserialize(serialize(null)) == null);
    }
}
