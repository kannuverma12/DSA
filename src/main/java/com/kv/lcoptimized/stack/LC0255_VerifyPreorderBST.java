package com.kv.lcoptimized.stack;

import com.kv.lcoptimized.common.Check;

/**
 * LC 255 · Verify Preorder Sequence in BST (Medium)       [was: lc/P2_CheckIfArrayRepresentPreorderTraversal - empty stub]
 *
 * Pattern : monotonic stack. Walking a preorder, once we move into some node's right subtree
 *           every later value must exceed that node ("lower bound"). The stack holds the
 *           current left path; popping smaller values = moving right, raising the bound.
 * Optimal : O(n) time. O(1) extra space by reusing the input array as the stack (shown below).
 *
 * L6/L7 follow-ups: postorder variant (scan right-to-left with an upper bound); rebuild the BST
 * from preorder in O(n) with the same bound trick (LC 1008).
 */
public class LC0255_VerifyPreorderBST {

    public static boolean verifyPreorder(int[] preorder) {
        int lowerBound = Integer.MIN_VALUE;
        int top = -1;                               // preorder[0..top] reused as the stack
        for (int v : preorder) {
            if (v < lowerBound) {
                return false;
            }
            while (top >= 0 && preorder[top] < v) {
                lowerBound = preorder[top--];
            }
            preorder[++top] = v;
        }
        return true;
    }

    public static void main(String[] args) {
        Check.isTrue(verifyPreorder(new int[] {5, 2, 1, 3, 6}));
        Check.isTrue(!verifyPreorder(new int[] {5, 2, 6, 1, 3}));
        Check.isTrue(verifyPreorder(new int[] {40, 30, 35, 80, 100}));
        Check.isTrue(!verifyPreorder(new int[] {40, 30, 35, 20, 80, 100}));
        Check.isTrue(verifyPreorder(new int[] {}));
    }
}
