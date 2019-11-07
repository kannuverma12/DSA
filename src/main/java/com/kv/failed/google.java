package com.kv.failed;

public class google {

    /**
     *
     * Please use this Google doc during your interview (your interviewer will see what you write here). To free your hands for typing, we recommend using a headset or speakerphone.
     *
     *
     *     A 0
     *    / \
     *  6/   \3
     *  /     \
     * B       C
     *        /|\
     *      2/ | \4
     *      /  |1 \
     *     D   E   F
     *
     * ----
     * what if we add a connection from A to F?
     *
     * A=>F
     * F=>C
     * ---
     *
     *
     * A - B -> 4
     * A- C -> 3
     * A- F -> 3 + 4
     * Max (A-b, a-d, a-e, a-f )
     * a-f -> 7
     *
     * Max of Root to leaf
     *
     *
     * class Node {
     * 	int data;
     * 	List<Node> children;
     *
     * 	public Node(int data, List<Node> children) {
     * 		this.data = data;
     * 		this.children = children;
     * }
     * }
     *
     * public class FindMaxPath {
     *
     * 	Node root = null;
     * 	Node targetNode = null;
     * 	int maxPathValue = Integer.MIN;
     *
     * 	public static void main(String… args) {
     *
     * 		FindMaxPath findMaxPath = new FindMaxPath();
     * 		List<Node> cNodes = new ArrayList<>();
     * 		cNodes.add(new Node(6));
     * 		cNodes.add(new Node(3));
     * 		findMaxPath.root = new Node(0, cNodes);
     * // add more nodes similarly
     *
     *        }
     *
     * public int findMaxPathValue(Node root) {
     *
     * 		if(root == null) {
     * 			return 0;
     *        }
     *
     * 		getTargetNode(root, maxPathValue, 0);
     * 		return maxPathValue;
     *
     * }
     *
     * public void getTargetNode(Node root, int sum, int currentSum){
     * 	if(root == null) {
     * 		return;
     * }
     *
     * currentSum += root.data;
     *
     * // we have leaf node
     * if(root.children == null){
     * if(currentSum > sum) {
     * 	sum = currentSum;
     * 	maxPathValue = sum;
     * 	targetNode = node;
     * }
     * }
     *
     * for (Node node : root.children) {
     * 	getTargetNode(node, sum, currentSum);
     * }
     *
     *
     * }
     *
     * }
     *
     * Time Complexity :
     * O(NM)
     *
     * N - Number of Nodes
     * M - time to traverse from parent to children Node
     *
     * A - M = 6
     * A - C = 1 * 3
     */
}
