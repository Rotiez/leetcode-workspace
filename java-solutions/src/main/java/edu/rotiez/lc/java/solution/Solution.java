package edu.rotiez.lc.java.solution;

import edu.rotiez.lc.tools.annotation.LeetCodeProblem;
import edu.rotiez.lc.tools.annotation.LeetCodeSolutions;
import java.util.*;

@LeetCodeSolutions
public class Solution {

    @LeetCodeProblem(id = 2529)
    public int equalPairs(int[][] grid) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            var list = new ArrayList<Integer>();
            for (int j = 0; j < grid[i].length; j++) {
                list.add(grid[i][j]);
            }
            map.put(list, map.getOrDefault(list, 0) + 1);
        }

        for (int i = 0; i < grid[i].length; i++) {
            var list = new ArrayList<Integer>();
            for (int j = 0; j < grid.length; j++) {
                list.add(grid[i][j]);
            }

            if (map.containsKey(list)) {
                result += 1;
            }
        }

        return result;
    }

    @LeetCodeProblem(id = 1)
    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        for (int i =0; i < nums.length; i++) {
            for (int j =i+1; j < nums.length; j++) {
                if ((nums[i] + nums[j] == target)) {
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return arr;
    }

    @LeetCodeProblem(id = 2390)
    public String removeStars(String s) {
        StringBuilder result = new StringBuilder();
        var deque = new ArrayDeque<Character>();

        for (char c : s.toCharArray()) {
            if (c == '*') {
                deque.pollLast();
            } else {
                deque.offerLast(c);
            }
        }
        while (!deque.isEmpty()) result.append(deque.pollFirst());

        return result.toString();
    }

}
