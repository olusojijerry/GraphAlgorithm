import java.util.*;

public class StudyingAlgor {
    public static void main(String[] args){
        Node root = new Node(4);
        root.setLeft(new Node(7));
        root.setRight(new Node(9));
        root.getLeft().setLeft(new Node(10));
        root.getLeft().setRight(new Node(2));
        root.getRight().setRight(new Node(6));
        root.getLeft().getRight().setRight(new Node(6));
        root.getLeft().getRight().getRight().setLeft(new Node(2));

//        int[] arr = getAllLevel(root);

//        for (int i = 0; i < arr.length; i++){
//            System.out.println(arr[i]);
//        }

        System.out.println(compare("tacocats"));
    }

    private static int compare(String s){
        int invalids = 0;
        for (int i = 0, j = s.length()-1; i < s.length() && j >= 0; i++, j--){
            if(s.charAt(i) != s.charAt(j)){
                invalids++;
                if(s.charAt(i) == s.charAt(j-1) ) {
                    j--;
                }else{
                    i++;
                }
            }
        }
        return invalids;
    }

    public static int[] getAllLevel(Node root){
        HashMap<Integer, List<Integer>> maps = new HashMap<>();

        trackLevels(root, maps, 0);

        int[] arr = new int[maps.size()];

        for(int i = 0; i < maps.size(); i++){
            arr[i] = maps.get(i).get(1)/maps.get(i).get(0);
        }

        return arr;
    }

    private static void trackLevels(Node root, HashMap<Integer, List<Integer>> maps, int counter){
        if (root == null){
            return;
        }

        List<Integer> lst = maps.get(counter);

        if(lst == null){
            lst = new ArrayList<>();
            lst.add(0, 1);
            lst.add(1, root.getData());
        }else{
            lst.set(0, lst.get(0) + 1);
            lst.set(1, lst.get(1) + root.getData());
        }
        maps.put(counter, lst);

        if(root.getLeft() != null)
            trackLevels(root.getLeft(), maps, counter+1);
        if(root.getRight() != null)
            trackLevels(root.getRight(), maps, counter+1);
    }
}

class Node{
    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

class purpose{
    public static  void main(String[] args) throws Exception {

        shortenPath("/../../foo/bar/baz");

//        Loses loses = new Loses();
//
//        loses.put("a", 1);
//        loses.put("b", 2);
//
//        System.out.println(loses.last());
//        loses.delete("b");
//        System.out.println(loses.last());
//        loses.delete("a");
//        System.out.println(loses.last());
    }

    public static String shortenPath(String path) {

        Boolean isAbsolute = path.charAt(0) == '/' ? true : false;

        path = path.replace("//", "/");

        String separator = "/";
        String parentDirectory = "..";
        String currentDirectory = ".";

        StringBuilder shortenPath = new StringBuilder();

        String[] arr = path.split("/");

        int i = 0;
        if(isAbsolute) {
            shortenPath.append("/");
            i = 1;
        }

        for(int j = i; j < arr.length; j++){
            String a = arr[j];
            if(isAbsolute){
                if(a.equalsIgnoreCase(parentDirectory)){
                    if(!arr[1].equalsIgnoreCase(parentDirectory)) {
                        shortenPath = new StringBuilder("/");
                        shortenPath.append(arr[1]);
                    }
                }else if (a.equalsIgnoreCase(currentDirectory)){

                }else{
                    shortenPath.append(a);
                }
            }else{
                if(a.equalsIgnoreCase(parentDirectory)){
                    shortenPath = new StringBuilder(arr[0]);
                }else if (a.equalsIgnoreCase(currentDirectory)){

                }else{
                    shortenPath.append(a);

                }
            }
            if(j < arr.length-1)
                shortenPath.append("/");
        }

        return shortenPath.toString();
    }
}

class Loses{
    HashMap<String, Integer> map = new HashMap<>();
    List<String> lst = new ArrayList<>();

    public void put(String key, Integer val){
        if(!map.containsKey(key)) {
            map.put(key, val);
        }
        if(lst.contains(key)){
            lst.remove(key);
            lst.add(key);
        }else{
            lst.add(key);
        }

    }

    public Integer get(String key){
        if(lst.contains(key)){
            lst.remove(key);
            lst.add(key);
        }else{
            lst.add(key);
        }

        return map.get(key);
    }

    public void delete(String key){
        map.remove(key);
        lst.remove(key);
    }

    public Integer last() throws Exception {
        if(lst.isEmpty() && map.isEmpty())
            throw new Exception("No item available");

        return map.get(lst.get(lst.size() - 1));
    }


}

class Solution4 {

    public static void main(String[] a){
//        System.out.println(solution(new int[]{1, 3, 6, 4, 1, 2}));
        System.out.println(numRange(new int[]{80, 97, 78, 45, 23, 38, 38, 93, 83, 16, 91, 69, 18, 82, 60, 50, 61, 70, 15, 6, 52, 90},
                99, 269));
    }

        public static int numRange(ArrayList<Integer> A, int B, int C) {
            int i = 0, j = 0, freeze = 0, count = 0;
            int len = A.size();
            boolean incJ = false;
            while (i < len) {
                int sum = getSumInRange(A, j, i);
                if (!incJ) {
                    if (sum >= B && sum <= C) {
                        count++;
                        freeze = j;
                        j++;
                        incJ = true;
                    } else if (sum > C) {
                        j++;
                        if (j > i) {
                            i++;
                        }
                    } else {
                        i++;
                    }
                } else {
                    if (sum < B) {
                        j = freeze;
                        i++;
                        incJ = false;
                    } else {
                        count++;
                        j++;
                    }
                }
            }

            return count;
        }

        private static int getSumInRange(ArrayList<Integer> A, int sIdx, int eIdx) {
            int sum = 0;
            if (sIdx == eIdx) {
                return A.get(sIdx);
            }

            for (int i = sIdx; i <= eIdx; i++) {
            sum += A.get(i);
        }
        return sum;
    }
    public static int solution(int[] A) {
        // write your code in Java SE 8
        int smallest = 0;

        Arrays.sort(A);

        for (int i = 0; i < A.length; i++){
            if(A[i] > 0){
                if(A[i] != A[i - 1] + 1){
                    if(A[i] != i){
                        return i+1;
                    }
                }

            }

            if(A[i] > 0 && i == A.length - 1){
                return i+1;
            }
        }

        return 1;
    }

    public static int numRange(int[] A, int B, int C) {
        List<int[]> res = new ArrayList<>();
        for(int i =0; i < A.length; i++){
            if(A[i] > C){
                continue;
            }
            //76, 22, 81, 77, 95, 23, 27, 35, 24, 38, 15, 90, 19, 46, 53, 6, 77, 96, 100, 85, 43, 16, 73, 18, 7, 66
            checkSum(res, A, B, C, i, A[i]);
        }

        return res.size();
    }

    private static int checkSum(List<int[]> res, int[] A, int B, int C,
                          int start, int value){
        int counter = 0;
        int[] result = null;
        int output = 0;
        for ( int i = start; i < A.length; i++){
            int length = (i - (start)) + 1;
            result = new int[length];
            output += A[i];
            if(output <= C && output >= B){
                System.arraycopy(A, start, result, 0, length);
                res.add(result);
                counter++;
            }

            if(output > C){
                break;
            }
        }
        return counter;
    }

    public int[] nextGreater(int[] A) {
        int[] output = new int[A.length];

        for( int i= 0; i < A.length; i++){
            output[i] = getTotalNumbers(A, A[i], i + 1);
        }

        return output;
    }

    public int getTotalNumbers(int[] A, int value, int startIndex){

        for(int i = startIndex; i < A.length; i++){
            if(A[i] > value){
                return A[i];
            }
        }

        return -1;
    }
}

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
class SolutionMidPoint {
    static class ListNode {
        public int val;
        public ListNode next;
        ListNode(int x) { val = x; next = null; }
    }

    static ListNode push(ListNode head_ref, int new_data)
    {

        // allocate node
        ListNode new_node = new ListNode(new_data);

        // link the old list at the end of the new node
        new_node.next = head_ref;

        // move the head to point to the new node
        head_ref = new_node;
        return head_ref;
    }

    // function to print the linked list
    static void printList(ListNode head)
    {
        if (head == null)
        {
            return;
        }

        while (head.next != null)
        {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.print(head.val + "\n");
    }

    public static void main(String[] ars){
        ListNode head = null;

        head = push(head, 24);
        head = push(head, 99);
        head = push(head, 92);
        head = push(head, 93);
        head = push(head, 45);
        head = push(head, 4);
        head = push(head, 38);
        head = push(head, 3);
        head = push(head, 41);
        head = push(head, 17);
        head = push(head, 23);
        head = push(head, 67);
        head = push(head, 21);
        head = push(head, 34);
        head = push(head, 88);
        head = push(head, 64);
        head = push(head, 41);
        head = push(head, 14);
        head = push(head, 74);
        head = push(head, 8);
        head = push(head, 29);
        head = push(head, 39);
        head = push(head, 31);
        head = push(head, 16);
        head = push(head, 26);
        head = push(head, 59);
        head = push(head, 95);

        subtract(head);
        printList(head);
    }
    public static ListNode subtract(ListNode A) {

        String c = "adigitz";
        String d = "AffZ";

        for(char ch : c.toCharArray()){
            StringBuilder sb =new StringBuilder();
            sb.append(ch);
        }

        for(char ch : d.toCharArray()){
            StringBuilder sb =new StringBuilder();
            sb.append(ch);
        }


//        ListNode mid = findMidPoint(A);
        modifyList(A);

        return A;
    }

    private static ListNode modifyList(ListNode A){
        Stack<Integer> st = new Stack<>();

        ListNode k = A;
        ListNode temp = A;

        while (k != null){
            st.add(k.val);
            k = k.next;
        }

        Integer len = st.size()/2;
        int counter = 0;
        while (!st.isEmpty() && counter < len){
            if(counter < len){
                temp.val =  st.peek() - temp.val;
            }
            temp = temp.next;
            st.pop();
            counter++;
        }

        return temp;
    }

    private ListNode findMidPoint(ListNode A){
        ListNode temp = A, fast = A, slow = A;

        while( fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast != null){
            slow = slow.next;
        }

        return slow;
    }

    private void modifyList(ListNode A, ListNode mid){
        Stack<Integer> st = new Stack<>();

        ListNode temp = A;

        while ( mid != null){
            st.add(mid.val);
            mid = mid.next;
        }

        while (!st.isEmpty()){
            temp.val = st.peek() - temp.val;
            temp = temp.next;
            st.pop();
        }
    }
}


//  Definition for binary tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class SolutionTreeCreation {
    public ArrayList<TreeNode> generateTrees(int a) {
        ArrayList<TreeNode> output = createTree(1, a);

        for (int i = 0; i < output.size(); i++){
            preorder(output.get(i));
        }

        return output;
    }

    private void preorder(TreeNode root){
        if(root != null){
            preorder(root.left);
            preorder(root.right);
        }
    }

    private ArrayList<TreeNode> createTree(int root, int end){
        ArrayList<TreeNode> lst = new ArrayList<>();

        if(root > end){
            lst.add(null);
            return lst;
        }

        for(int i = root; i <= end; i++){
            ArrayList<TreeNode> leftSubTree = createTree(root, i - 1);

            ArrayList<TreeNode> rightSubTree = createTree(i + 1, end);

            for(int j = 0; j < leftSubTree.size(); j++){
                TreeNode left = leftSubTree.get(j);
                for(int k = 0; k < rightSubTree.size(); k++){
                    TreeNode right = rightSubTree.get(k);
                    TreeNode temp = new TreeNode(i);
                    temp.left = left;
                    temp.right = right;
                    lst.add(temp);
                }
            }
        }

        return lst;
    }
}





