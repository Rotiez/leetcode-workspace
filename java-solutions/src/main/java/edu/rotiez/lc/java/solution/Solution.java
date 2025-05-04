package edu.rotiez.lc.java.solution;

import edu.rotiez.lc.java.structure.ListNode;
import edu.rotiez.lc.java.structure.TreeNode;
import edu.rotiez.lc.tools.annotation.LeetCodeProblem;
import edu.rotiez.lc.tools.annotation.LeetCodeSolutions;

import java.util.*;

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
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                return new int[]{map.get(target - num), i};
            } else {
                map.put(num, i);
            }
        }
        return new int[] {};
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

    @LeetCodeProblem(id = 1863)
    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int total = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            int xor = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    xor ^= nums[i];
                }
            }
            total += xor;
        }

        return total;
    }

    @LeetCodeProblem(id = 1268)
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();

        Arrays.sort(products);

        String prefix = "";
        for (char c : searchWord.toCharArray()) {
            prefix += c;

            List<String> suggestions = new ArrayList<>();

            for (String product : products) {
                if (product.startsWith(prefix)) suggestions.add(product);
                if (suggestions.size() == 3) break;
            }

            result.add(suggestions);
        }

        return result;
    }

    @LeetCodeProblem(id = 1436)
    public String destCity(List<List<String>> paths) {
        Set<String> departures = new HashSet<>();

        for (List<String> path : paths) {
            var from  = path.get(0);
            departures.add(from);
        }

        for (List<String> path : paths) {
            var to = path.get(1);
            if (!departures.contains(to)) {
                return to;
            }
        }

        return "";
    }

    @LeetCodeProblem(id = 1450)
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int result = 0;
        int i = 0;

        while (i < startTime.length && i < endTime.length) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) result++;
            i++;
        }

        return result;
    }

    @LeetCodeProblem(id = 3375)
    public int minOperations(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (num < k) return -1;
            else if (num > k) set.add(num);
        }

        return set.size();
    }

    @LeetCodeProblem(id = 1455)
    public int isPrefixOfWord(String sentence, String searchWord) {
        var words = sentence.trim().split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) return i+1;
        }

        return -1;
    }

    @LeetCodeProblem(id = 1460)
    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            map.put(target[i], map.getOrDefault(target[i], 0) - 1);
        }
        for (int i : map.keySet()) {
            if (map.get(i) != 0) return false;
        }
        return true;
    }

    @LeetCodeProblem(id = 1464)
    public int maxProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }

        return (max1 - 1) * (max2 - 1);
    }

    @LeetCodeProblem(id = 1470)
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            result[idx] = nums[i];
            result[idx + 1] = nums[i + n];
            idx += 2;
        }
        return result;
    }

    @LeetCodeProblem(id = 2843)
    public int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        for (int i = low; i <= high; i++) {
            if (i < 100 && i % 11 == 0) {
                ans++;
            } else if (i >= 1000 && i < 10000) {
                int left = i / 1000 + (i % 1000) / 100;
                int right = (i % 100) / 10 + i % 10;
                if (left == right) {
                    ans++;
                }
            }
        }
        return ans;
    }

    @LeetCodeProblem(id = 108)
    public TreeNode sortedArrayToBST(int[] nums) {
        return generate(nums,0,nums.length-1);
    }

    private TreeNode generate(int[] arr, int s, int e){
        if(s>e) return null;
        int mid = (s+e)/2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = generate(arr,s,mid-1);
        node.right = generate(arr,mid+1,e);
        return node;
    }

    @LeetCodeProblem(id = 1534)
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (
                        Math.abs(arr[i] - arr[j]) <= a &&
                        Math.abs(arr[j] - arr[k]) <= b &&
                        Math.abs(arr[i] - arr[k]) <= c
                    ) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    @LeetCodeProblem(id = 110)
    public boolean isBalanced(TreeNode root) {
        return isBalancedDfs(root) != -1;
    }

    public int isBalancedDfs(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = isBalancedDfs(node.left);
        if (leftHeight == -1) return -1;

        int rightHeight = isBalancedDfs(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    @LeetCodeProblem(id = 1518)
    public int numWaterBottles(int numBottles, int numExchange) {
        int totalBottles = numBottles;

        while (numBottles >= numExchange) {
            totalBottles += numBottles / numExchange;
            numBottles = (numBottles / numExchange) + (numBottles % numExchange);
        }

        return totalBottles;
    }

    @LeetCodeProblem(id = 1480)
    public int[] runningSum(int[] nums) {
        int increment = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = increment;
            increment += nums[i];
            nums[i] += temp;
        }
        return nums;
    }

    @LeetCodeProblem(id = 1491)
    public double average(int[] salary) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int salarySum = 0;

        for (int sal : salary) {
            if (sal > max) max = sal;
            if (sal < min) min = sal;
            salarySum += sal;
        }

        return (double) (salarySum - (max + min)) / (salary.length - 2);
    }

    @LeetCodeProblem(id = 1528)
    public String restoreString(String s, int[] indices) {
        char[] result = new char[indices.length];
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            result[indices[i]] = arr[i];
        }
        return new String(result);
    }

    @LeetCodeProblem(id = 1523)
    public int countOdds(int low, int high) {
        int diff = high - low + 1;
        if (low % 2 != 0 && high % 2 != 0) return diff / 2 + 1;
        else return diff / 2;
    }

    @LeetCodeProblem(id = 2537)
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> mpp = new HashMap<>();
        long cnt = 0; int left = 0;
        for (int i = 0; i < nums.length; i++) {
            k -= mpp.getOrDefault(nums[i], 0);
            mpp.put(nums[i], mpp.getOrDefault(nums[i], 0) + 1);
            while (k <= 0) {
                mpp.put(nums[left], mpp.get(nums[left]) - 1);
                k += mpp.get(nums[left++]);
            }
            cnt += left;
        }
        return cnt;
    }

    @LeetCodeProblem(id = 2175)
    public int countPairs(int[] nums, int k) {
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (
                    nums[i] == nums[j] &&
                    (i * j) % k == 0
                ) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @LeetCodeProblem(id = 38)
    public String countAndSay(int n) {
        if (n == 1) return "1";

        String prev = countAndSay(n - 1);
        StringBuilder res = new StringBuilder();

        int i = 0;
        while (i < prev.length()) {
            int count = 1;
            while (i + 1 < prev.length() && prev.charAt(i) == prev.charAt(i + 1)) {
                i++;
                count++;
            }
            res.append(count).append(prev.charAt(i));
            i++;
        }

        return res.toString();
    }

    @LeetCodeProblem(id = 1859)
    public String sortSentence(String s) {
        String[] words = s.split(" ");
        StringBuilder res = new StringBuilder();
        Arrays.sort(words, Comparator.comparingInt((word -> (int) word.charAt(word.length() - 1))));
        for (String word : words) {
            res.append(word, 0, word.length() - 1).append(" ");
        }
        return res.toString().trim();
    }

    @LeetCodeProblem(id = 1844)
    public String replaceDigits(String s) {
        char[] arr =s.toCharArray();
        for(int i = 1; i < s.length(); i++)
        {
            if(i % 2 != 0)
            {
                int shift = arr[i] - '0';
                arr[i] = (char) ((arr[i-1] + shift - 'a') % 26 + 'a');
            }
        }
        return new String(arr);
    }

    long countAtLeast(int[] nums, long comp) {
        long ans = 0; int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] >= comp) {
                ans += j - i;
                j--;
            }
            else i++;
        }
        return ans;
    }

    @LeetCodeProblem(id = 2563)
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return countAtLeast(nums, lower) - countAtLeast(nums, upper+1);
    }

    @LeetCodeProblem(id = 2145)
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long sum = 0, maxi = 0, mini = 0;
        for (int x : differences) {
            sum += x;
            maxi = Math.max(maxi, sum);
            mini = Math.min(mini, sum);
        }
        return (int)Math.max(0, upper - lower - maxi + mini + 1);
    }

    @LeetCodeProblem(id = 2799)
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> unique = new HashSet<>();
        for (int num : nums) unique.add(num);
        int required = unique.size();

        Map<Integer, Integer> window = new HashMap<>();
        int left = 0, count = 0, result = 0;

        for (int num : nums) {
            window.put(num, window.getOrDefault(num, 0) + 1);
            if (window.get(num) == 1) count++;

            while (count == required) {
                window.put(nums[left], window.get(nums[left]) - 1);
                if (window.get(nums[left]) == 0) {
                    window.remove(nums[left]);
                    count--;
                }
                left++;
            }
            result += left;
        }
        return result;
    }

    @LeetCodeProblem(id = 2873)
    public long maximumTripletValue(int[] nums) {
        long maxTriplet = 0, maxElement = 0, maxDiff = 0;
        for (int num : nums) {
            maxTriplet = Math.max(maxTriplet, maxDiff * num);
            maxDiff = Math.max(maxDiff, maxElement - num);
            maxElement = Math.max(maxElement, num);
        }
        return maxTriplet;
    }

    @LeetCodeProblem(id = 3396)
    public int minimumOperations(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (!map.containsKey(nums[i])) map.put(nums[i], 1);
            else return (i + 3) / 3;
        }
        return 0;
    }

    @LeetCodeProblem(id = 1945)
    public int getLucky(String s, int k) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = Character.getNumericValue(s.charAt(i)) - 9;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
        }
        for (int i = 1; i < k; i++) {
            int tempSum = 0;
            while (sum > 0) {
                tempSum += sum % 10;
                sum /= 10;
            }
            sum = tempSum;
        }
        return sum;
    }

    @LeetCodeProblem(id = 1967)
    public int numOfStrings(String[] patterns, String word) {
        int counter = 0;
        for (String p : patterns) {
            if (word.contains(p)) counter++;
        }
        return counter;
    }

    @LeetCodeProblem(id = 3392)
    public int countSubarrays(int[] nums) {
        int counter = 0;
        for (int i = 0; i + 2 < nums.length; i++) {
            if ((nums[i] + nums[i + 2]) * 2 == nums[i + 1]) counter++;
        }
        return counter;
    }

    @LeetCodeProblem(id = 2302)
    public long countSubarrays(int[] nums, long k) {
        long count = 0, sum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum * (right - left + 1) >= k) {
                sum -= nums[left++];
            }
            count += right - left + 1;
        }
        return count;
    }

    @LeetCodeProblem(id = 2962)
    public long countSubarrays(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) max = num;
        }

        int left = 0;
        int countMax = 0;
        long result = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == max) countMax++;

            while (countMax >= k) {
                result += nums.length - right;
                if (nums[left] == max) countMax--;
                left++;
            }
        }

        return result;
    }

    @LeetCodeProblem(id = 836)
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(
            rec1[2] <= rec2[0] ||
            rec1[0] >= rec2[2] ||
            rec1[3] <= rec2[1] ||
            rec1[1] >= rec2[3]
        );
    }

    @LeetCodeProblem(id = 1295)
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            int digits = 0;
            while (num > 0) {
                num /= 10;
                digits++;
            }
            if (digits % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    @LeetCodeProblem(id = 819)
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> ban = new HashSet<>();

        for (String bannedWord : banned) {
            ban.add(bannedWord.toLowerCase());
        }

        for (String word : paragraph.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+")) {
            if (!word.isEmpty() && !ban.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        return map.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    @LeetCodeProblem(id = 1200)
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            minDiff = Math.min(minDiff, arr[i] - arr[i - 1]);
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == minDiff) {
                result.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }
        return result;
    }

    @LeetCodeProblem(id = 838)
    public String pushDominoes(String s) {
        s = "L" + s + "R";
        StringBuilder res = new StringBuilder();
        int prev = 0;
        for (int curr = 1; curr < s.length(); ++curr) {
            if (s.charAt(curr) == '.') continue;
            int span = curr - prev - 1;
            if (prev > 0)
                res.append(s.charAt(prev));
            if (s.charAt(prev) == s.charAt(curr)) {
                for (int i = 0; i < span; ++i)
                    res.append(s.charAt(prev));
            } else if (s.charAt(prev) == 'L' && s.charAt(curr) == 'R') {
                for (int i = 0; i < span; ++i)
                    res.append('.');
            } else {
                for (int i = 0; i < span / 2; ++i)
                    res.append('R');
                if (span % 2 == 1)
                    res.append('.');
                for (int i = 0; i < span / 2; ++i)
                    res.append('L');
            }
            prev = curr;
        }
        return res.toString();
    }

    @LeetCodeProblem(id = 1128)
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] mpp = new int[100];
        for (int[] d : dominoes)
            if (d[0] > d[1]) mpp[d[0] * 10 + d[1]]++;
            else mpp[d[1] * 10 + d[0]]++;

        int count = 0;
        for (int freq : mpp)
            count += (freq - 1) * freq / 2;

        return count;
    }
}
