package com.kv.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author karanverma
 *
 *  Given the head of a graph, return a deep copy (clone) of the graph. Each node in the graph 
 *  contains a label (int) and a list (List[UndirectedGraphNode]) of its neighbors. There is an 
 *  edge between the given node and each of the nodes in its neighbors.
 *  
 *  leetcode - https://leetcode.com/problems/clone-graph/
 */
public class CloneGraph {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)
            return null;
 
        LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = 
                                   new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
 
        UndirectedGraphNode newHead = new UndirectedGraphNode(node.label);
 
        queue.add(node);
        map.put(node, newHead);
 
        while(!queue.isEmpty()){
            UndirectedGraphNode curr = queue.pop();
            List<UndirectedGraphNode> currNeighbors = curr.neighbors; 
 
            for(UndirectedGraphNode aNeighbor: currNeighbors){
                if(!map.containsKey(aNeighbor)){
                    UndirectedGraphNode copy = new UndirectedGraphNode(aNeighbor.label);
                    map.put(aNeighbor,copy);
                    map.get(curr).neighbors.add(copy);
                    queue.add(aNeighbor);
                }else{
                    map.get(curr).neighbors.add(map.get(aNeighbor));
                }
            }
 
        }
        return newHead;
    }
    
    static class UndirectedGraphNode {
        int label;
         List<UndirectedGraphNode> neighbors;
         UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
     };

}
