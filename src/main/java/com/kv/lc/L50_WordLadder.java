package com.kv.lc;

import java.util.LinkedList;
import java.util.Set;

/**
 * 
 * @author karanverma
 *
 *  Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *  Only one letter can be changed at a time.
 *  Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *  Note:
 *  Return 0 if there is no such transformation sequence.
 *  All words have the same length.
 *  All words contain only lowercase alphabetic characters.
 *  You may assume no duplicates in the word list.
 *  You may assume beginWord and endWord are non-empty and are not the same.
 *  
 *  Input:
 *  beginWord = "hit",      endWord = "cog",
 *  wordList = ["hot","dot","dog","lot","log","cog"]
 */
public class L50_WordLadder {
    /*
     * So we quickly realize that this is a search problem, and breath-first search guarantees the optimal solution.
     */

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(beginWord, 1));
 
        wordDict.add(endWord);
 
        while(!queue.isEmpty()){
            WordNode top = queue.remove();
            String word = top.word;
 
            if(word.equals(endWord)){
                return top.numSteps;
            }
 
            char[] arr = word.toCharArray();
            for(int i=0; i<arr.length; i++){
                for(char c='a'; c<='z'; c++){
                    char temp = arr[i];
                    if(arr[i]!=c){
                        arr[i]=c;
                    }
 
                    String newWord = new String(arr);
                    if(wordDict.contains(newWord)){
                        queue.add(new WordNode(newWord, top.numSteps+1));
                        wordDict.remove(newWord);
                    }
 
                    arr[i]=temp;
                }
            }
        }
 
        return 0;
    }
    
    static class WordNode{
        String word;
        int numSteps;
     
        public WordNode(String word, int numSteps){
            this.word = word;
            this.numSteps = numSteps;
        }
    }

}
