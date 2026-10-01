package com.kv.lcoptimized.trees;

import com.kv.lcoptimized.common.Check;
import com.kv.lcoptimized.common.ListNode;
import com.kv.lcoptimized.common.TreeNode;

import java.util.Arrays;

/**
 * LC 108 · Sorted Array to BST (Easy) + LC 109 · Sorted List to BST (Medium)
 *   [was: lc/L36_ConvertSortedArrayToBST, lc/L37_ConvertSortedListToBST]
 *
 * Array : middle element as root, recurse on halves. O(n) time, O(log n) stack.
 * List  : simulate an inorder traversal. Build left subtree of size k, take the current list
 *         node as root, advance, build the right. O(n) time, O(log n) stack, with no
 *         repeated slow/fast middle finding (that approach is O(n log n)).
 * Changed : the list version kept its cursor in a STATIC field, so it isn't re-entrant or
 *           thread-safe. The cursor is now a local holder. (lo + hi) >>> 1 avoids overflow.
 */
public class LC0108_SortedToBST {

    public static TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private static TreeNode build(int[] a, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int mid = (lo + hi) >>> 1;
        TreeNode root = new TreeNode(a[mid]);
        root.left = build(a, lo, mid - 1);
        root.right = build(a, mid + 1, hi);
        return root;
    }

    public static TreeNode sortedListToBST(ListNode head) {
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) {
            n++;
        }
        ListNode[] cursor = {head};
        return inorderBuild(cursor, n);
    }

    private static TreeNode inorderBuild(ListNode[] cursor, int size) {
        if (size == 0) {
            return null;
        }
        TreeNode left = inorderBuild(cursor, size / 2);
        TreeNode root = new TreeNode(cursor[0].val);
        cursor[0] = cursor[0].next;
        root.left = left;
        root.right = inorderBuild(cursor, size - size / 2 - 1);
        return root;
    }

    public static void main(String[] args) {
        Check.eq(TreeNode.toLevelOrder(sortedArrayToBST(new int[] {-10, -3, 0, 5, 9})),
                Arrays.asList(0, -10, 5, null, -3, null, 9));
        Check.eq(LC0144_BinaryTreeTraversals.inorder(sortedListToBST(ListNode.of(-10, -3, 0, 5, 9))),
                Arrays.asList(-10, -3, 0, 5, 9));
        Check.eq(TreeNode.toLevelOrder(sortedListToBST(ListNode.of(-10, -3, 0, 5, 9))),
                Arrays.asList(0, -3, 9, -10, null, 5));
    }
}
