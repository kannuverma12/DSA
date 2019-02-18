package com.kv.lc;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 
 * 
 * @author karanverma
 *  
 *  Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *  A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *  
 *  X X X X
 *  X O O X
 *  X X O X
 *  X O X X
 *  
 *  After running your function, the board should be:
 *      X X X X
 *      X X X X
 *      X X X X
 *      X O X X
 *      
 *      Explanation:
 *  Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board 
 *  are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the 
 *  border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected 
 *  horizontally or vertically.
 */
public class SurroundedRegions {
    
    /*
     * This problem is similar to Number of Islands. In this problem, only the cells on the 
     * boarders can not be surrounded. So we can first merge those O's on the boarders like 
     * in Number of Islands and replace O's with '#', and then scan the board and replace all O's left (if any).
     */

    public static void main(String[] args) {
        char[][] board = {  {'X','X', 'X', 'X'},
                            {'X','O', 'O', 'X'},
                            {'X','O', 'X', 'X'},
                            {'X','X', 'X', 'X'}};
        
        System.out.println("Original arr : "+Arrays.deepToString(board));
        solve(board);
        System.out.println("After capturing arr : "+Arrays.deepToString(board));
        

    }
    
    public static void solve(char[][] board) {
        if(board==null || board.length==0 || board[0].length==0)
            return;
     
        int m=board.length;
        int n=board[0].length;
     
     
        for(int j=0; j<n; j++){
            if(board[0][j]=='O'){
                bfs(board, 0, j);
            }
        }
     
        for(int j=0; j<n; j++){
            if(board[m-1][j]=='O'){
                bfs(board, m-1, j);
            }
        }
     
        for(int i=0; i<m; i++){
            if(board[i][0]=='O'){
                bfs(board, i, 0);
            }
        }
     
        for(int i=0; i<m; i++){
            if(board[i][n-1]=='O'){
                bfs(board, i, n-1);
            }
        }
     
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }
                if(board[i][j]=='1'){
                    board[i][j]='O';
                }
            }
        }
    }
     
    public static void bfs(char[][] board, int o, int p){
        int m=board.length;
        int n=board[0].length;
     
        int index = o*n+p;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.offer(index);
        board[o][p]='1';
     
        while(!queue.isEmpty()){
            int top = queue.poll();
            int i=top/n;
            int j=top%n;
     
            if(i-1>=0 && board[i-1][j]=='O'){
                board[i-1][j]='1';
                queue.offer((i-1)*n+j);
            }
            if(i+1<m && board[i+1][j]=='O'){
                board[i+1][j]='1';
                queue.offer((i+1)*n+j);
            }
            if(j-1>=0 && board[i][j-1]=='O'){
                board[i][j-1]='1';
                queue.offer(i*n+j-1);
            }
            if(j+1<n && board[i][j+1]=='O'){
                board[i][j+1]='1';
                queue.offer(i*n+j+1);
            }
        }
    }

}
