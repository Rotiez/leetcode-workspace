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

    @LeetCodeProblem(id = 735)
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;

        while (i < asteroids.length) {
            if (stack.isEmpty()) {
                stack.push(asteroids[i]);
                i++;
            } else {
                var before = stack.peek();
                var current = asteroids[i];

                if (before > 0 == current > 0) { // если двигаются в одну сторону
                    stack.push(current);
                    i++;
                } else if (current > 0) { // двигаются в разные, но текущий вправо, значит предыдущий - влево
                    stack.push(current);
                    i++;
                } else if (current < 0) { // двигаются друг на друга
                    var diff = before + current;

                    if (diff > 0) { // предыдущий больше текущего
                        i++;
                    } else if (diff < 0) { // текущий больше предыдущего
                        stack.pop();
                    } else { // астероиды были равны
                        stack.pop();
                        i++;
                    }
                }
            }
        }

        int size = stack.size();
        int[] result = new int[size];

        for (int j = 0; j < size; j++) {
            result[j] = stack.get(j);
        }

        return result;
    }

    @LeetCodeProblem(id = 394)
    public String decodeString(String s) {
        StringBuilder tempNum = new StringBuilder();
        StringBuilder tempStr = new StringBuilder();
        Stack<Integer> stackNum = new Stack<>();
        Stack<StringBuilder> stackStr = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '[') {
                stackNum.push(Integer.parseInt(tempNum.toString()));
                tempNum.setLength(0);
                stackStr.push(tempStr);
                tempStr = new StringBuilder();
            } else if (c == ']') {
                int repeat = stackNum.pop();
                String current = tempStr.toString();
                tempStr = stackStr.pop();
                tempStr.append(current.repeat(Math.max(0, repeat)));
            } else if (Character.isDigit(c)) {
                tempNum.append(c);
            } else {
                tempStr.append(c);
            }
        }
        return tempStr.toString();
    }
}
