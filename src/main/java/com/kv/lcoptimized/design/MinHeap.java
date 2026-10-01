package com.kv.lcoptimized.design;

import com.kv.lcoptimized.common.Check;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Binary min-heap from scratch                            [was: lc/Q24_1_HeapImplementation - empty stub]
 *
 * Array layout: children of i are 2i+1 and 2i+2, parent is (i-1)/2.
 *   push / pop : O(log n) via sift-up / sift-down.
 *   heapify    : O(n) bottom-up (sift down from the last internal node) - NOT O(n log n);
 *                the reason (most nodes are near the leaves) is a common interview question.
 *   peek       : O(1).
 * Interview extensions: decrease-key needs an index map (value -> position), which is how an
 * indexed priority queue for Dijkstra works; a max-heap is the same code with the comparison
 * flipped.
 */
public class MinHeap {

    private int[] a;
    private int size;

    public MinHeap() {
        a = new int[16];
    }

    /** O(n) heapify. */
    public MinHeap(int[] values) {
        a = Arrays.copyOf(values, Math.max(16, values.length));
        size = values.length;
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    public void push(int v) {
        if (size == a.length) {
            a = Arrays.copyOf(a, size * 2);
        }
        a[size] = v;
        siftUp(size++);
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return a[0];
    }

    public int pop() {
        int top = peek();
        a[0] = a[--size];
        siftDown(0);
        return top;
    }

    public int size() {
        return size;
    }

    private void siftUp(int i) {
        int v = a[i];
        while (i > 0 && a[(i - 1) / 2] > v) {
            a[i] = a[(i - 1) / 2];                   // shift parent down, place v once at the end
            i = (i - 1) / 2;
        }
        a[i] = v;
    }

    private void siftDown(int i) {
        int v = a[i];
        int half = size / 2;                          // nodes >= half are leaves
        while (i < half) {
            int child = 2 * i + 1;
            if (child + 1 < size && a[child + 1] < a[child]) {
                child++;
            }
            if (a[child] >= v) {
                break;
            }
            a[i] = a[child];
            i = child;
        }
        a[i] = v;
    }

    public static void main(String[] args) {
        MinHeap h = new MinHeap();
        for (int v : new int[] {5, 3, 8, 1, 9, 2, 7}) {
            h.push(v);
        }
        int[] out = new int[h.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = h.pop();
        }
        Check.eq(out, new int[] {1, 2, 3, 5, 7, 8, 9});

        int[] random = new java.util.Random(42).ints(1000, -500, 500).toArray();
        MinHeap h2 = new MinHeap(random);
        int[] sorted = new int[random.length];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = h2.pop();
        }
        Arrays.sort(random);
        Check.eq(sorted, random);
    }
}
