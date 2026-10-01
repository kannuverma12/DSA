# com.kv.lcoptimized

An optimised rewrite of `com.kv.lc`, grouped by **interview pattern** instead of easy/medium/hard, and extended toward a Google L6/L7 DSA loop.

- 139 original files → 116 solution files (duplicates merged, stubs implemented) + 24 new problems + 4 shared helpers.
- Every file starts with: the LeetCode number, the file(s) it replaces, the pattern, the optimal complexity, **what changed and why**, and the follow-ups interviewers usually ask next.
- Every `main()` is a self-test. Java 8 compatible (matches `pom.xml`).

```bash
# The project's Lombok 1.16 does not run on JDK 17, so build with JDK 8:
export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
mvn -o -q compile && java -cp target/classes com.kv.lcoptimized.common.RunAll
# -> Ran 140 programs: 598 checks passed, 0 checks failed, 0 programs broken
```

---

## 1. Bugs found in the original `com.kv.lc` code

These are real correctness bugs, not style issues. Each one is fixed and covered by a test in the new file.

| Original file | Bug | New file |
|---|---|---|
| `DP1_Combinations`, `DP2_CombinationSum`, `DP3_CombinationSum2` | `result.add(list)` without copying → every result is the same list, emptied by backtracking (prints `[[], [], ...]`) | `backtracking/LC0077`, `LC0039` |
| `L56_LongestSubstringWithKUniqueCharacters` | Shrink loop hard-codes `map.size() > 2`, so any `k != 2` is wrong | `slidingwindow/LC0340` |
| `medium/CheckIfStringIsPermutationOfOtherString` | Loop bound `i < s2.len - s1.len` skips the last window: `("ab","ab")` → false | `slidingwindow/LC0567` |
| `L63_RangeSumOfBST.dfs` | `while (root != null)` with no update → infinite loop | `trees/LC0938` |
| `L74_implementStrStr` (KMP) | Prefix table uses `next[i-1]+1` instead of `index+1`; search restarts from `j=0`, so it isn't KMP | `strings/LC0028` |
| `hard/L57_MedianOfTwoSortedArray.getMedian` | Reads `arr1[j++]` instead of `arr2[j++]`; integer division for even totals | `binarysearch/LC0004` |
| `hard/L50_WordLadder` | Adds `endWord` to the dictionary → finds paths that shouldn't exist; mutates the caller's set | `graphs/LC0127` |
| `Q24_2_ImplementTailF` | `break` inside `switch` never exits the loop → returns the **whole file**; one syscall per byte | `design/TailF` |
| `FindTopKOccurancesInALargeFile.getWordCount` | Counts per line, then pushes partial counts into the heap → wrong top-k | `heap/LC0692` |
| `medium/L51_WordSearch` | Returns `true` before restoring the cell → leaves `#` in the caller's board | `backtracking/LC0079` |
| `CheckIfANumberIsPowerOf2` | Returns true for `0` and `Integer.MIN_VALUE` | `bits/BitTricks` |
| `L12_ImplementSqrt` | `mid * mid` overflows int for large inputs | `binarysearch/LC0069` |
| `medium/L11_ImplementPowerOperation` | "Most effective" `pow2` is O(n) recursion (stack overflow for big n); `pow3` ignores negative n | `binarysearch/LC0050` |
| `medium/L38_ConvertStringToInteger.toInt` ("best") | Crashes on `""`, ignores spaces/`+`, silent overflow | `strings/LC0008` |
| `medium/L25_RemoveDuplicatesFromSortedArray` | Spec says "at most twice", recommended method keeps at most once | `arrays/LC0080` |
| `medium/ArrangeNumbersInArrayToFromBiggestNumber` | Comparator never returns 0 (violates the contract; TimSort can throw); int version overflows | `arrays/LC0179` |
| `DP5_MinimumPathSum.main` | Calls a GfG recursion that also allows diagonal moves (different problem), exponential | `dp/LC0064` |
| `DP9_L9_Permutations.main` | Generates 17! permutations → never finishes | `backtracking/LC0046` |
| `DP12_UniqueBinarySearchTrees` | `n = 0` → `ArrayIndexOutOfBoundsException` | `dp/LC0096` |
| `DP6_PalindromePartioning` "DP method" | Returns all palindromic substrings, not partitions | `backtracking/LC0131` |
| `L18_RotateLinkedListRight` | O(k) loop; times out for k up to 2·10⁹ | `linkedlist/LC0061` |

## 2. Complexity upgrades (correct before, but not optimal)

| Problem | Before | After |
|---|---|---|
| Sort List (L54) | new node per merge, recursion - O(n) extra | bottom-up merge, **O(1)** extra |
| Build tree from traversals (L32/L33) | linear root search → O(n²) | index map → **O(n)** |
| Populate next pointers (L44) | two queues, O(n) space | **O(1)** space |
| Flatten tree (L41) | stack, O(h) | Morris-style, **O(1)** |
| Sort colors (L22) | two-pass counting sort | **one-pass** Dutch flag |
| Kth largest (L6) | last-element pivot, O(n²) on sorted input | random pivot + 3-way partition, **O(n) expected** |
| K smallest (L68) | O(n·k) | heap O(n log k) |
| Max product subarray | O(n²) default | **O(n)** |
| Coin change | exponential recursion default | **O(V·k)** DP |
| Valid anagram / first unique char | O(n²) `indexOf`/`substring` | **O(n)** counts |
| Permutation in string | O(n·m) window rebuild | **O(n)** sliding window |
| Largest subarray equal letters/digits (L67) | O(n²) | **O(n)** prefix sums |
| Word break (L48) / Word break II (L49) | loop whole dictionary per index; no memo | bounded by max word length + memo; trie variant |
| Longest valid parentheses (L75) | stack of `int[]` | index stack, plus **O(1)**-space two-pass version |
| Unique paths / min path sum / obstacles | m×n tables | **one row**, plus closed form C(m+n-2, m-1) |
| Decode ways | O(n) array + `parseInt` in loop | **O(1)** space |
| Valid sudoku | 3 passes, 27 boolean arrays | 1 pass, 27 bitmasks |
| Longest substring w/o repeat | `HashMap<Character,Integer>` | `int[128]` |
| Multiply strings | `sb.insert(0, …)` O((m+n)²), debug println | positional array |
| Various | `java.util.Stack` (synchronized, boxing) | `ArrayDeque` / primitive arrays |

## 3. Layout

| Package | Files |
|---|---|
| `arrays` | 1 Two Sum · 15 3Sum · 16 3Sum Closest · 18 4Sum/k-Sum · 27 · 26/80 · 31 Next Permutation · 36 Sudoku · 41 First Missing Positive · 42 Trapping Rain Water · 48 Rotate Image · 53 Kadane · 54/59 Spiral · 55/45 Jump Game · 56 Merge Intervals · 73 · 75 Sort Colors · 134 Gas Station · 152 · 179 Largest Number · **253 Meeting Rooms II** · 525 · 560 |
| `binarysearch` | 4 Median of Two Arrays · 29 Divide · 33/153 Rotated · 34/35 lower-bound template · 50 Pow · 69 Sqrt · 74/240 Matrix · **410 Binary search on answer** |
| `slidingwindow` | 3 · 30 · **76 Min Window** · **239 Window Max (monotonic deque)** · 340 · 567 |
| `strings` | 5 (+Manacher) · 6 · 7 · 8 · 9 · 12/13 · 14 · 28 (KMP + Rabin-Karp) · 38 · 43 · 49 · 242 · 273 · 387 (+stream) · EasyStringOps |
| `stack` | 20 · 32 · 71 · **84/85 Largest Rectangle (monotonic stack)** · 150 · 255 · 1021 |
| `linkedlist` | 2 · 19 · 21 · 23 · 24 · 25 · 61 · 82/83 · 86 · 92/206 · 138 · 142/287 · 147 · 148 · 1721 |
| `trees` | 95 · 98 · 102/103/107 · 105/106 · 108/109 · 112/113/437 · 114 · 116/117 · **124 Max Path Sum** · 129 · 144/94/145 (+Morris) · **236/235 LCA** · **297 Serialize** · 938 |
| `backtracking` | 17 · 22 · 39/40 · 46/47 (+string perms) · 60 · 77 · 78/90 · 79 · 93 · 131/132 · 140 · AllKLengthStrings |
| `dp` | 10 · 44 · 62/63 · 64 · 70 (+matrix power) · **72 Edit Distance** · 91 · 96 · 118/119 · 120 · 139 · **300 LIS** · **312 Burst Balloons** · 322/518 |
| `graphs` | **UnionFind** · 127 (bidirectional BFS) · 130 · 133 · **200/305 Islands** · **207/210 Topo sort** · **269 Alien Dictionary** · **329** · **399 Weighted DSU** · **743 Dijkstra** |
| `heap` | 215 (+k smallest) · 692 (+large file) · 1167 |
| `design` | 208 Trie · MinHeap · TailF · **146 LRU** · **295 Median Finder** · **380 RandomizedSet** · **981 TimeMap** |
| `ds` | **SegmentTree (307)** · **FenwickTree (315)** |
| `bits` | BitTricks · 89 Gray Code · 136/137/260 · 268 (+CtCI bit-fetch) |

**Bold** = new problem that wasn't in `com.kv.lc` but comes up often in Google loops.

---

## 4. Preparing for the Google L6/L7 DSA round

### What's different at L6/L7
At senior levels the loop generally has **fewer coding rounds** and more weight on system design and leadership, but the coding bar is **not** lower. Interviewers expect an optimal, bug-free solution quickly, and then use the remaining time on follow-ups. Confirm the exact loop composition with your recruiter, since it varies by team and changes over time.

Signals interviewers typically write up:
1. **Problem solving**: clarify, find the pattern, reach optimal on your own, reason about trade-offs.
2. **Coding**: clean, idiomatic, compiles in your head, sensible names, no hacks.
3. **Verification**: dry-run with a small example, find your own bugs, cover edge cases.
4. **Communication**: think out loud, state complexity before you're asked.
5. **Seniority**: handle the follow-ups: scale (doesn't fit in memory, streaming, distributed), concurrency, API design.

### A 45-minute template
| Min | Do |
|---|---|
| 0-5 | Restate. Ask about input size, value ranges, duplicates, empty input, sortedness, charset, in-place allowed? Write 1-2 examples, including an edge case. |
| 5-10 | Brute force in one sentence, with its complexity. Then the bottleneck → the pattern → the optimal approach. **Get agreement before coding.** |
| 10-30 | Code. Helper functions with clear names. No premature micro-optimisation. |
| 30-35 | Dry-run on your example. Then edge cases: empty, one element, all equal, overflow, negative. |
| 35-45 | Complexity. Follow-ups (each file's header lists the likely ones). |

### Pattern checklist (map a new problem onto one of these within ~2 minutes)
| Signal in the problem | Pattern | Reference file |
|---|---|---|
| sorted array / "pair with sum" | two pointers | `arrays/LC0015` |
| contiguous subarray/substring with a constraint | sliding window | `slidingwindow/LC0076` |
| subarray sum = k, negatives allowed | prefix sum + hashmap | `arrays/LC0560` |
| "minimise the maximum" / monotone feasibility | binary search on the answer | `binarysearch/LC0410` |
| next greater/smaller, histogram | monotonic stack | `stack/LC0084` |
| window max/min | monotonic deque | `slidingwindow/LC0239` |
| top-k, k-way merge, running median | heap(s) | `heap/LC0215`, `design/LC0295` |
| overlapping intervals, rooms | sort + sweep | `arrays/LC0253` |
| grid/graph connectivity, online merges | BFS/DFS, Union-Find | `graphs/LC0200` |
| dependencies / ordering | topological sort | `graphs/LC0207`, `LC0269` |
| weighted shortest path | Dijkstra | `graphs/LC0743` |
| all combinations / permutations | backtracking | `backtracking/LC0039`, `LC0046` |
| optimal substructure over prefixes/pairs | 1D/2D DP | `dp/LC0072`, `LC0300` |
| choose last action in a range | interval DP | `dp/LC0312` |
| tree answer combines children | post-order "return one, update another" | `trees/LC0124` |
| prefix queries on strings | trie | `design/LC0208` |
| range query + point update | Fenwick / segment tree | `ds/*` |
| O(1) get/put/evict | hashmap + doubly linked list | `design/LC0146` |

### 6-week plan (about 1.5 h/day)
1. **Week 1: Arrays, two pointers, sliding window, prefix sums.** Re-solve the `arrays` and `slidingwindow` files from a blank editor, without looking.
2. **Week 2: Binary search (incl. on answer), stacks, heaps.** Memorise the single `lowerBound` template in `LC0034`.
3. **Week 3: Linked lists, trees.** Do each traversal iteratively and with Morris; practise LCA, serialise, and max path sum.
4. **Week 4: Graphs.** BFS/DFS, Union-Find, topo sort, Dijkstra. Write `UnionFind` from memory in under 3 minutes.
5. **Week 5: DP + backtracking.** For each DP, say the state, the transition and the order out loud before coding; always reduce space.
6. **Week 6: Mock interviews.** Two per week under a 45-min timer, in a plain Google Doc (no autocomplete, no running code). Practise the follow-ups listed in the headers.

Beyond this repo: keep going with recent Google-tagged problems on LeetCode (medium/hard), and do timed mocks with a peer. Being able to explain a solution clearly counts for as much as having solved a lot of problems.
