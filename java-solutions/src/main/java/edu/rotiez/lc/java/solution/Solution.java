package edu.rotiez.lc.java.solution;

import edu.rotiez.lc.java.structure.ListNode;
import edu.rotiez.lc.java.structure.TreeNode;
import edu.rotiez.lc.tools.annotation.LeetCodeProblem;
import edu.rotiez.lc.tools.annotation.LeetCodeSolutions;
import java.util.*;

@LeetCodeSolutions(logInfo = false)
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

    @LeetCodeProblem(id = 1768)
    public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        var arr1 = word1.toCharArray();
        var arr2 = word2.toCharArray();

        var maxArr = Math.max(arr1.length, arr2.length);

        for (int i = 0; i < maxArr; i++) {
            if (i < arr1.length) {
                result.append(arr1[i]);
            }
            if (i < arr2.length) {
                result.append(arr2[i]);
            }
        }

        return result.toString();
    }

    @LeetCodeProblem(id = 649)
    public String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();

        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') radiant.add(i);
            else dire.add(i);
        }

        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int rIndex = radiant.poll();
            int dIndex = dire.poll();

            if (rIndex < dIndex)  radiant.add(rIndex + senate.length());
            else dire.add(dIndex + senate.length());
        }

        return radiant.isEmpty() ? "Dire" : "Radiant";
    }

    @LeetCodeProblem(id = 2095)
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;

        return head;
    }

    @LeetCodeProblem(id = 328)
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        boolean oddFlag = true;
        ListNode odd = head;
        ListNode oddHead = odd;
        ListNode even = head.next;
        ListNode evenHead = even;

        ListNode current = head.next.next;

        while (current != null) {
            if (oddFlag) {
                odd.next = current;
                odd = odd.next;
            } else {
                even.next = current;
                even = even.next;
            }

            oddFlag = !oddFlag;
            current = current.next;
        }

        even.next = null;
        odd.next = evenHead;

        return oddHead;
    }

    @LeetCodeProblem(id = 206)
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    @LeetCodeProblem(id = 2130)
    public int pairSum(ListNode head) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();

        ListNode curr = head;

        while (curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            var temp = stack.pop() + curr.val;
            if (temp > max) max = temp;
            curr = curr.next;
        }

        return max;
    }

    @LeetCodeProblem(id = 104)
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    @LeetCodeProblem(id = 872)
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        collectLeaves(root1, list1);

        List<Integer> list2 = new ArrayList<>();
        collectLeaves(root2, list2);

        return list1.equals(list2);
    }

    private void collectLeaves(TreeNode node, List<Integer> leaves) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            leaves.add(node.val);
        }

        collectLeaves(node.left, leaves);
        collectLeaves(node.right, leaves);
    }

    @LeetCodeProblem(id = 1448)
    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
        return processNode(root.left, root.val) + processNode(root.right, root.val) + 1;
    }

    private int processNode(TreeNode node, int max) {
        if (node == null) return 0;

        var temp = 0;

        if (max <= node.val) {
            max = node.val;
            temp += 1;
        }

        temp += processNode(node.left, max);
        temp += processNode(node.right, max);

        return temp;
    }

    @LeetCodeProblem(id = 437)
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        var total = 0;

        total += countFromPath(root, targetSum);

        total += pathSum(root.left, targetSum);
        total += pathSum(root.right, targetSum);

        return total;
    }

    private int countFromPath(TreeNode root, long targetSum) {
        if (root == null) return 0;
        int tempCount = 0;

        if (root.val == targetSum) tempCount++;

        tempCount += countFromPath(root.left, targetSum - root.val);
        tempCount += countFromPath(root.right, targetSum - root.val);

        return tempCount;
    }

}
