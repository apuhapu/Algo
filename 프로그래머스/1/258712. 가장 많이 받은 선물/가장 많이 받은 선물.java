import java.util.*;
import java.io.*;

class Solution {
    
    HashMap<String,Integer> friendsIdxMap = new HashMap<>();
    
    public int solution(String[] friends, String[] gifts) {
        int numOfFriends = friends.length;
        int[][] board = new int[numOfFriends+1][numOfFriends+1];
        for (int i=0; i<numOfFriends; i++) {
            friendsIdxMap.put(friends[i], i);
        }
        for (String gift : gifts) {
            String[] fromTo = gift.split(" ");
            int from = friendsIdxMap.get(fromTo[0]);
            int to = friendsIdxMap.get(fromTo[1]);
            board[from][to]++;
            board[from][numOfFriends]++;
            board[numOfFriends][to]++;
        }
        int[] nextMonth = new int[numOfFriends];
        for (int a=0; a<numOfFriends; a++) {
            for (int b=a+1; b<numOfFriends; b++) {
                if (board[a][b] > board[b][a]) {
                    nextMonth[a]++;
                    continue;
                } else if (board[a][b] < board[b][a]) {
                    nextMonth[b]++;
                    continue;
                }
                int scoreA = board[a][numOfFriends] - board[numOfFriends][a];
                int scoreB = board[b][numOfFriends] - board[numOfFriends][b];
                if (scoreA == scoreB) {
                    continue;
                }
                if (scoreA > scoreB) {
                    nextMonth[a]++;
                    continue;
                } 
                nextMonth[b]++;
            }
        }
        int answer = 0;
        for (int next : nextMonth) {
            answer = Math.max(answer, next);
        }
        return answer;
    }
}
