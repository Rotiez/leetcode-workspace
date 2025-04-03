package edu.rotiez.lc.java.solution;

import edu.rotiez.lc.java.structure.ListNode;
import edu.rotiez.lc.java.structure.TreeNode;
import edu.rotiez.lc.tools.annotation.LeetCodeProblem;
import edu.rotiez.lc.tools.annotation.LeetCodeSolutions;
import kotlin.collections.EmptyList;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.min;

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

    @LeetCodeProblem(id = 199)
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            TreeNode tempNode = queue.peek();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (node != null) {
                    tempNode = node;
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            }

            result.add(tempNode.val);
        }

        return result;
    }

    @LeetCodeProblem(id= 1161)
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        int maxSum = Integer.MIN_VALUE, minRow = 0, currentRow = 0;

        queue.add(root);

        while (!queue.isEmpty()) {
            int rowSize = queue.size();
            int rowSum = 0;
            currentRow++;

            for (int i = 0; i < rowSize; i++) {
                TreeNode node = queue.poll();

                if (node != null) {
                    rowSum += node.val;
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            }

            if (rowSum > maxSum) {
                minRow = currentRow;
                maxSum = rowSum;
            }
        }

        return minRow;
    }

    @LeetCodeProblem(id = 700)
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        if (root.val > val) return searchBST(root.left, val);
        return searchBST(root.right, val);
    }

    @LeetCodeProblem(id = 450)
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) root.left = deleteNode(root.left, key);
        else if (key > root.val) root.right = deleteNode(root.right, key);
        else {
            if (root.left == null && root.right == null) return null;

            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            TreeNode successor = findMin(root.right);
            root.val = successor.val;
            root.right = deleteNode(root.right, successor.val);
        }

        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    @LeetCodeProblem(id = 841)
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        visited[0] = true;
        queue.add(0);

        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();
            for (int key : rooms.get(currentRoom)) {
                if (!visited[key]) {
                    visited[key] = true;
                    queue.add(key);
                }
            }
        }

        for (boolean roomVisited : visited) {
            if (!roomVisited) return false;
        }
        return true;
    }

    @LeetCodeProblem(id = 443)
    public int compress(char[] chars) {
        int ans = 0;

        for (int i = 0; i < chars.length;) {
            final char letter = chars[i];
            int count = 0;

            while (i < chars.length && chars[i] == letter) {
                count++;
                i++;
            }

            chars[ans++] = letter;

            if (count > 1) {
                for (final char c : String.valueOf(count).toCharArray()) {
                    chars[ans++] = c;
                }
            }
        }

        return ans;
    }

    @LeetCodeProblem(id = 1004)
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0, zeroCount = 0;

        while (right < nums.length) {
            if (nums[right] == 0) zeroCount++;
            right++;
            if (zeroCount > k) {
                if (nums[left] == 0) zeroCount--;
                left++;
            }
        }

        return right - left;
    }

    @LeetCodeProblem(id = 1493)
    public int longestSubarray(int[] nums) {
        int left = 0, right = 0, zeroCount = 0;

        while (right < nums.length) {
            if (nums[right] == 0) zeroCount++;
            right++;
            if (zeroCount > 1) {
                if (nums[left] == 0) zeroCount--;
                left++;
            }
        }

        return right - left - 1;
    }

    @LeetCodeProblem(id = 2352)
    public int equalPairss(int[][] grid) {
        Map<List<Integer>, Integer> rowCounts = new HashMap<>();

        for (int i = 0; i < grid.length; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < grid[i].length; j++) {
                row.add(grid[i][j]);
            }
            rowCounts.put(row, rowCounts.getOrDefault(row, 0) + 1);
        }

        int count = 0;

        for (int j = 0; j < grid[0].length; j++) {
            List<Integer> column = new ArrayList<>();
            for (int i = 0; i < grid.length; i++) {
                column.add(grid[i][j]);
            }
            if (rowCounts.containsKey(column)) {
                count += rowCounts.get(column);
            }
        }

        return count;
    }

    @LeetCodeProblem(id = 236)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    @LeetCodeProblem(id = 374)
    public int guessNumber(int n) {
        int left = 0, right = n;

        while (left <= right) {
            var mid = left + (right - left) / 2;
            var result = guess(mid);

            if (result == -1) {
                right = mid - 1;
            } else if (result == 1) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private int guess(int n) {
        Random rdm = new Random();
        return rdm.nextInt(n);
    }

    @LeetCodeProblem(id = 2300)
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] result = new int[spells.length];
        Arrays.sort(potions);

        for (int i = 0; i < spells.length; i++) {
            int currSpell = spells[i];
            int leftIdx = 0, rightIdx = potions.length - 1;
            int potionsCount = 0;

            while (leftIdx <= rightIdx) {
                int midIdx = leftIdx + (rightIdx - leftIdx) / 2;
                long product = (long) potions[midIdx] * currSpell;

                if (product >= success) {
                    potionsCount = potions.length - midIdx;
                    rightIdx = midIdx - 1;
                } else {
                    leftIdx = midIdx + 1;
                }
            }

            result[i] = potionsCount;
        }
        return result;
    }

    @LeetCodeProblem(id = 338)
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = Integer.bitCount(i);
        }
        return result;
    }

    @LeetCodeProblem(id = 136)
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) result ^= num;
        return result;
    }

    @LeetCodeProblem(id = 17)
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;

        final Map<Character, List<String>> strMap = new HashMap<>() {{
            put('2', List.of("a", "b", "c"));
            put('3', List.of("d", "e", "f"));
            put('4', List.of("g", "h", "i"));
            put('5', List.of("j", "k", "l"));
            put('6', List.of("m", "n", "o"));
            put('7', List.of("p", "q", "r", "s"));
            put('8', List.of("t", "u", "v"));
            put('9', List.of("w", "x", "y", "z"));
        }};

        backtrack(digits, 0, new StringBuilder(), result, strMap);

        return result;
    }

    private void backtrack(String digits, int idx, StringBuilder sb, List<String> result, Map<Character, List<String>> strMap) {
        if (idx == digits.length()) {
            result.add(sb.toString());
            return;
        }

        List<String> letters = strMap.get(digits.charAt(idx));
        for (String letter : letters) {
            sb.append(letter);
            backtrack(digits, idx + 1, sb, result, strMap);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    @LeetCodeProblem(id = 739)
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Arrays.fill(result, 0);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }

        return result;
    }

    @LeetCodeProblem(id = 111)
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int depth = 1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left == null && node.right == null) return depth;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                depth++;
            }
        }

        return depth;
    }

    @LeetCodeProblem(id = 94)
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> result = new ArrayList<>();

        result.addAll(inorderTraversal(root.left));
        result.add(root.val);
        result.addAll(inorderTraversal(root.right));

        return result;
    }

    @LeetCodeProblem(id = 203)
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        ListNode newHead = new ListNode();
        ListNode cur = newHead;

        while (head != null) {
            if (head.val != val) {
                cur.next = new ListNode(head.val);
                cur = cur.next;
            }
            head = head.next;
        }
        return newHead.next;
    }

    @LeetCodeProblem(id = 414)
    public static int thirdMax(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int n : nums) s.add(n);

        if (s.size() < 3) return Collections.max(s);

        s.remove(Collections.max(s));
        s.remove(Collections.max(s));

        return Collections.max(s);
    }

    @LeetCodeProblem(id = 637)
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            result.add((double) sum / size);
        }

        return result;
    }

    @LeetCodeProblem(id = 215)
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int result = Integer.MIN_VALUE;

        for (int n : nums) pq.add(n);
        for (int i = 0; i < k; i++) result = pq.poll();

        return result;
    }

    @LeetCodeProblem(id = 657)
    public boolean judgeCircle(String moves) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : moves.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        return
                (Objects.equals(map.getOrDefault('R', 0), map.getOrDefault('L', 0))
                && Objects.equals(map.getOrDefault('U', 0), map.getOrDefault('D', 0)));
    }

    @LeetCodeProblem(id = 821)
    public int[] shortestToChar(String s, char c) {
        int[] result = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            int nextIdx = Integer.MAX_VALUE;
            int prevIdx = Integer.MAX_VALUE;

            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == c) {
                    nextIdx = j;
                    break;
                }
            }
            for (int k = i; k >= 0; k--) {
                if (s.charAt(k) == c) {
                    prevIdx = k;
                    break;
                }
            }

            result[i] = min(abs(i - prevIdx), abs(nextIdx - i));
        }

        return result;
    }

    @LeetCodeProblem(id = 876)
    public ListNode middleNode(ListNode head) {
        int length = 0;
        var node = head;

        while (node != null) {
            length++;
            node = node.next;
        }

        for (int i = 0; i < length / 2; i++) {
            head = head.next;
        }

        return head;
    }

    @LeetCodeProblem(id = 944)
    public int minDeletionSize(String[] strs) {
        int result = 0;

        for (int i = 0; i < strs[0].length(); i++) {
            char prev = 'a';

            for (String s : strs) {
                if (prev > s.charAt(i)) {
                    result++;
                    break;
                }
                prev = s.charAt(i);
            }
        }

        return result;
    }

    @LeetCodeProblem(id = 961)
    public int repeatedNTimes(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == nums.length / 2) return num;
        }

        return 0;
    }

    @LeetCodeProblem(id = 976)
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        for(int i = nums.length-1; i > 1; i--){
            if(nums[i] < nums[i-1] + nums[i-2])
                return  nums[i] + nums[i-1]+ nums[i-2];
        }

        return 0;
    }

    @LeetCodeProblem(id = 1025)
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }

    @LeetCodeProblem(id = 1318)
    public int minFlips(int a, int b, int c) {
        int count = 0;

        for(int i = 0; i < 32; i++){
            int aCurr = a & 1 , bCurr = b & 1 , cCurr = c & 1;
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
            if((aCurr|bCurr) != cCurr){
                if(aCurr == 1 && bCurr == 1) count++;
                count++;
            }
        }
        return count;
    }

    @LeetCodeProblem(id = 349)
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int p1 = 0, p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] > nums2[p2]) {
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                set.add(nums1[p1]);
                p1++;
                p2++;
            }
        }

        int[] result = new int[set.size()];
        int index = 0;
        for (int num : set) {
            result[index++] = num;
        }

        return result;
    }

    @LeetCodeProblem(id = 350)
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int p1 = 0, p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] > nums2[p2]) {
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                list.add(nums1[p1]);
                p1++;
                p2++;
            }
        }

        int[] result = new int[list.size()];
        int index = 0;
        for (int num : list) {
            result[index++] = num;
        }

        return result;
    }

    @LeetCodeProblem(id = 383)
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] charsA = ransomNote.toCharArray();
        char[] charsB = magazine.toCharArray();
        Map<Character, Integer> tempMap = new HashMap<>();

        for (char b : charsB) {
            tempMap.put(b, tempMap.getOrDefault(b, 0) + 1);
        }

        for (char a : charsA) {
            var value = tempMap.getOrDefault(a, 0);
            if (value > 0) {
                tempMap.put(a, value - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    @LeetCodeProblem(id = 387)
    public int firstUniqChar(String s) {
        var arr = s.toCharArray();
        var tempMap = new HashMap<Character, Integer>();

        for (char c : arr) {
            tempMap.put(c, tempMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < arr.length; i++) {
            if (tempMap.get(arr[i]) == 1) return i;
        }

        return -1;
    }

    @LeetCodeProblem(id = 500)
    public String[] findWords(String[] words) {
        String row1 = "qwertyuiop", row2 = "asdfghjkl", row3 = "zxcvbnm";

        List<String> result = new ArrayList<>();

        for (String word : words) {
            String lowerWord = word.toLowerCase();
            char firstChar = lowerWord.charAt(0);

            String targetRow = "";
            if (row1.indexOf(firstChar) != -1) targetRow = row1;
            else if (row2.indexOf(firstChar) != -1) targetRow = row2;
            else if (row3.indexOf(firstChar) != -1) targetRow = row3;

            boolean valid = true;
            for (char c : lowerWord.toCharArray()) {
                if (targetRow.indexOf(c) == -1) {
                    valid = false;
                    break;
                }
            }

            if (valid) result.add(word);
        }

        return result.toArray(new String[0]);
    }

    @LeetCodeProblem(id = 506)
    public String[] findRelativeRanks(int[] score) {
        int[] originalScore = Arrays.copyOf(score, score.length);
        String[] result = new String[score.length];
        Map<Integer, String> map = new HashMap<>();

        Arrays.sort(score);

        int rank = 1;
        for (int i = score.length - 1; i >= 0; i--) {
            String medal;
            if (rank == 1) {
                medal = "Gold Medal";
            } else if (rank == 2) {
                medal = "Silver Medal";
            } else if (rank == 3) {
                medal = "Bronze Medal";
            } else {
                medal = String.valueOf(rank);
            }

            map.put(score[i], medal);
            rank++;
        }

        for (int j = 0; j < originalScore.length; j++) {
            result[j] = map.get(originalScore[j]);
        }

        return result;
    }

    @LeetCodeProblem(id = 812)
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;
        int n = points.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    double area = calculateTriangleArea(
                            points[i],
                            points[j],
                            points[k]
                    );
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private double calculateTriangleArea(int[] A, int[] B, int[] C) {
        return 0.5 * Math.abs(
                (B[0] - A[0]) * (C[1] - A[1]) -
                        (C[0] - A[0]) * (B[1] - A[1])
        );
    }

    @LeetCodeProblem(id = 824)
    public String toGoatLatin(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        var wordNum = 1;
        var vowels = "aeiouAEIOU";
        var ma = "ma";
        var a = "a";
        var space = ' ';

        for (String word : words) {
            if (word.length() == 1 || vowels.indexOf(word.charAt(0)) != -1) {
                result.append(word);
            } else {
                result.append(word.substring(1));
                result.append(word.charAt(0));
            }
            result.append(ma);
            result.append(a.repeat(wordNum));
            result.append(space);
            wordNum++;
        }

        return result.toString().trim();
    }

    @LeetCodeProblem(id = 1122)
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int length = 0;
        for (int j : arr1) length = Math.max(length, j);
        int[] count = new int[length+1];
        for (int j : arr1) count[j]++;

        int[] ans = new int[arr1.length];
        int index = 0;
        for (int j : arr2) {
            while (count[j] > 0) {
                ans[index] = j;
                index++;
                count[j]--;
            }
        }
        for(int i = 0; i < count.length; i++){
            while(count[i]>0){
                ans[index] = i;
                index++;
                count[i]--;
            }
        }
        return ans;
    }

    @LeetCodeProblem(id = 884)
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        var words1 = s1.trim().split(" ");
        var words2 = s2.trim().split(" ");
        for (String word : words1) map.put(word, map.getOrDefault(word, 0) + 1);
        for (String word : words2) map.put(word, map.getOrDefault(word, 0) + 1);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) list.add(entry.getKey());
        }

        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }

    @LeetCodeProblem(id = 908)
    public int smallestRangeI(int[] nums, int k) {
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            } else if (nums[i] > max) {
                max = nums[i];
            }
        }
        return Math.max((max - k) - (min + k), 0);
    }

    @LeetCodeProblem(id = 942)
    public int[] diStringMatch(String s) {
        int[] result = new int[s.length() + 1];
        int left = 0, right = s.length();
        int idx = 0;

        for (char c : s.toCharArray()) {
            if (c == 'I') {
                result[idx] = left;
                left++;
            } else {
                result[idx] = right;
                right--;
            }
            idx++;
        }
        result[idx] = left;
        return result;
    }

}
