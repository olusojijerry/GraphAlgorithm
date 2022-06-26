import sun.security.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataStructuresCourse {
    public int solution(int[] A) {
        // write your code in Java SE 8
        Arrays.sort(A);
        int missing = 0;

        for(int i = 1; i < A.length; i++){
            if(A[i] == A[i - 1] + 1 || A[i] == A[i - 1]  ){
                continue;
            }else{
                if(A[i] > 0){
                    missing = A[i] - 1;
                }else{
                    missing = 1;
                }
                break;
            }
        }

        if(missing == 0){
            missing = A[A.length - 1] + 1;
        }

        return missing;
    }

    public static int solve(String A) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC + 01"));

        String dtStr = "21-3-2022 " + A;

        Date dt = formatter.parse(dtStr);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);

        int secondsCount = 0;

        while (!palindromeDate(formatter.format(dt), formatter.format(dt))){
            calendar.add(Calendar.MINUTE, 1);
            dt = calendar.getTime();
            secondsCount++;
        }

        return secondsCount;
    }

    private static boolean palindromeDate(String hour, String min){

        return hour.charAt(0) == min.charAt(1) && hour.charAt(1) == min.charAt(0);
    }

    public static int solve(String[] A) {
        boolean[] arr = new boolean[26];

        for (String str : A){
            for(int i = 0; i < str.length(); i++){
                char a = str.charAt(i);
                if(isLetter(a)){
                    int k = a - 'a';
                    arr[k] = true;
                }
            }
        }

        for (int j = 0; j < arr.length; j++){
            if(arr[j] == false)
                return 0;
        }

        return 1;
    }

    private static boolean isLetter(char m){
        if(!('a' <= m && m <= 'z'))
            return false;

        return true;
    }

    static String rotationalCipher(String input, int rotationFactor) {
        // Write your code here
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if ('a' <= c && c <= 'z'){
                int firstPos = 'a';
                int lastPos = 'l';
                int mainPos = c % firstPos;
                int k = mainPos + rotationFactor;
                k = k % 26;
                k = 'a' + k;
                sb.append((char)k);
                System.out.println((char)k);
            }else if ('A' <= c && c <= 'Z'){
                int firstPos = 'A';
                int mainPos = c % firstPos;
                int k = mainPos + rotationFactor;
                k = k % 26;
                k = 'A' + k;
                sb.append((char) k);
                System.out.println((char)k);
            }else if('0' <= c && c <= 9){
                int k = Character.getNumericValue(c) + rotationFactor;
                k = k % 10;
                sb.append(k);
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static int[] contiguousArr(int[] arr){
        int start;
        int end;
        int[] res = new int[arr.length];
        int contiguousArr = 0;
        for(int i = 0; i < arr.length; i++){
            start = i;
            end = i;
            contiguousArr++;
            while (start > 0 || end < arr.length){
                start--;
                if(start >= 0){
                    if (arr[i] > arr[start]){
                        contiguousArr++;
                    }else{
                        start = -1;
                    }
                }

                end++;
                if (end < arr.length){
                    if (arr[i] > arr[end]){
                        contiguousArr++;
                    }else{
                        end = arr.length;
                    }
                }
            }

            res[i] = contiguousArr;
            contiguousArr = 0;
        }

        return res;
    }

    int numberOfWays(int[] arr, int k) {
        // Write your code here
        int output = 0;
        for (int i = 0; i < arr.length - 1; i++){
            int intialValue = arr[i];
            for(int j = i + 1; j < arr.length; j++){
                if(intialValue + arr[j] == k){
                    output++;
                }
            }
        }
        return output;
    }

    static boolean areTheyEqual(int[] array_a, int[] array_b) {
        // Write your code here
        if(array_a.length != array_b.length ){
            return false;
        }

        for (int i = 0; i < array_b.length; i++){
            if(array_a[i] == array_b[i]){
                continue;
            }else{
                int[] reversed = reverse(array_a[i], i, array_b);

                if (reversed.length == 0){
                    return false;
                }else{
                    for(int j = i; j < array_a.length; j++){
                        if(array_a[j] != reversed[j]){
                            return false;
                        }
                    }
                }
                break;
            }
        }
        return true;
    }

    static int[] reverse(int value, int start, int[] array_b){
        //find the element index with value
        int index = 0;
        int[] reversed = new int[0];
        for (int i = start; i < array_b.length; i++){
            if(array_b[i] == value){
                index = i;
            }
        }

        if(index != 0){
            int tmp = array_b[index];
            reversed = new int[array_b.length];
            System.arraycopy(array_b, 0, reversed, 0, start);
            for(int k = index, i = 0; k > start; k--, i++){
                reversed[k] = array_b[start + i];
            }
            reversed[start] = array_b[index];
            return reversed;
        }else{
            return reversed;
        }
    }

    int[] findSignatureCounts(int[] arr) {
        // Write your code here
        int[] res = new int[arr.length];
        boolean dontCount = false;

        for(int i = arr.length - 1; i >= 0; i--){
            if(arr[i] == i+1){
                res[i]++;
            }else{
                for(int j = 0; j < res.length; j++){
                    res[j]++;
                }
            }
        }

        return res;

    }

    public static void main(String[] args) throws ParseException {
//        int[] arr = {20, 35, -15, 21, 70, 55};
//        selectionSort(arr);
//        for(int i = 0; i < arr.length; i++){
//            System.out.println(arr[i]);
//        }

//        int[] res = contiguousArr(new int[]{3, 4, 1, 6, 2});
//
//        for(int i: res ){
//            System.out.println(i);
//        }

//        System.out.println(areTheyEqual(new int[]{1, 2, 3, 4}, new int[]{1, 4, 3, 3}));

//        System.out.println(rotationalCipher("abcdefghijklmNOPQRSTUVWXYZ0123456789", 39));

//        String[] A = {"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
//
//        System.out.println(solve(A));

//        lookAndSay();
//        System.out.println(validateOneEdit("cat", "dog"));
//        System.out.println(validateOneEdit("cat", "cats"));
//        System.out.println(validateOneEdit("cat", "cut"));
//        System.out.println(validateOneEdit("cat", "cast"));
//        System.out.println(validateOneEdit("cat", "cass"));
//        System.out.println(validateOneEdit("cat", "at"));
//        System.out.println(validateOneEdit("cat", "act"));
        int[][] arr = createMatrixArray(5);

        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++){
                System.out.print(arr[j][i] + "  ");
            }
            System.out.println("");
        }

    }

    public  static int say(String A){
        String[] ar = A.split(":");

        int hr = Integer.parseInt(ar[0]);
        int min = Integer.parseInt(ar[1]);

        int minAdded = 0;

        while(!palindromeDate(Integer.toString(hr).length() < 2 ? "0" + hr : Integer.toString(hr),
                Integer.toString(min).length() < 2 ? "0" + min: Integer.toString(min))){
            minAdded++;
            min++;

            if (min > 59){
                hr += 1;
                min = 0;
                if(hr > 23){
                    hr = 0;
                }
            }
        }

        return minAdded;
    }
    public static void bubblesort(int[] arr){

        for(int i = (arr.length-1); i > 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                }

            }
        }
    }

    private static void swap(int[] arr, int start, int end){
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }

    public static void selectionSort(int[] arr){
        for (int i = arr.length; i > 0; i--){
            int largestIndex = 0;
            for(int j = 0; j < i; j++){
                if(arr[largestIndex] < arr[j]){
                    largestIndex = j;
                }
            }
            swap(arr, largestIndex, i - 1);
        }
    }

    public static void lookAndSay(){
        int i = 1;

        String sb = "1";

        while (sb.length() < 21){
            System.out.println(sb);
             sb = countExistingValues(sb);
        }

        System.out.println(sb);
    }

    public static String countExistingValues(String str){
        StringBuilder sb = new StringBuilder();
        sb.insert(0, str);

        if (str.length() == 1){
            return sb.insert(0, 1).toString();
        }

        int i = 0;
        int countLoop = 0;
        sb = new StringBuilder();
        while (i < str.length()){

            Character a = str.charAt(i);
            Integer countCharacter = countCharacters(str, a, i);

            String newAppendingStr = countCharacter.toString() + a;

            sb.append(newAppendingStr);

            //sb.replace(countCharacter == 1 && i > 0 ? i + countLoop : i, countCharacter == 1 && i > 0 ? i + countCharacter + countLoop: i + countCharacter, newAppendingStr);
            i += countCharacter;
            countLoop++;
        }

        return sb.toString();
    }

    private static Integer countCharacters(String smc, char value, int start){
        Integer counts = 0;

        while (start < smc.length() && value == smc.charAt(start) ){
            counts++;
            start++;
        }
        return counts;
    }
//    public static int[][] creatingMatrix(int n){
//        int[][] arr = new int[n][n];
//
//        Integer rows = n;
//        Integer columns = n;
//        Integer columnSeq = n;
//        Integer rowSeq = n-1;
//        Integer rowStartSeq = 0;
//        Integer colStartSeq = 0;
//        int serial = 0;
//
//        while (rows >= 0 && columns >= 0){
//            int i = 0;
//            for(i = colStartSeq; i < columnSeq; i++){
//                arr[i][rowStartSeq] = ++serial;
//            }
//            rows++;
//            colStartSeq = i;
//
//            int j = 0;
//            for (j = rowStartSeq; j < rowSeq; j++){
//                arr[colStartSeq][j] = ++serial;
//            }
//            columns++;
//            rowStartSeq = j;
//        }
//
//    }
    public static boolean validateOneEdit(String first, String second){
        int edits = 0;

        if(first.length() < second.length()){
            String tmp = first;
            first = second;
            second = tmp;
        }

        if(Math.abs(first.length() - second.length()) > 1){
            return false;
        }

        boolean isDecrement = false;

        for (int i = 0, j = 0; i < first.length() && j < second.length(); ++i, ++j){
            if (first.charAt(i) != second.charAt(j)){
                edits++;
                if (first.length() > second.length() && !isDecrement){
                    isDecrement = true;
                    --j;
                }
            }

        }
        return edits <= 1;
    }

    public static int[][] createMatrixArray(int n){
        int limit = n*n;
        int[][] arr = new int[n][n];
        int r = 0, c = 0, serial = 0, dir=0;
        int[] dc = {0, 1, 0, -1};
        int[] dr = {1, 0, -1, 0};

        while (serial++ < limit){
            arr[r][c]=serial;
            r += dr[dir];
            c += dc[dir];

            if (isInValid(arr, r, c)){
                r -= dr[dir];
                c -= dc[dir];
                dir = (dir + 1) % 4;
                r += dr[dir];
                c += dc[dir];
            }

        }
        return arr;
    }

    private static boolean isInValid(int[][] arr, int r, int c){
        return r < 0 || c < 0 || r >= arr.length || c >= arr.length || arr[r][c] != 0;
    }
}

class Reader{
    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        String[] toRead = reader.readLine().trim().split("\\s+");
//
//        for (int i = 0; i < toRead.length; i++){
//            System.out.print(Integer.parseInt(toRead[i]) + " ");
//        }

        Scanner scanner = new Scanner(System.in);

        String userString = scanner.nextLine();

        String[] arr = userString.split(" ");

        System.out.println(Integer.parseInt(arr[0]));
        System.out.println(Integer.parseInt(arr[1]));

    }


}

class Solutions {

    public static void main(String[] args){
//        int[][] arr = prettyPrint(3);
//
//        for (int i = 0; i < arr.length; i++){
//            for (int j = 0; j < arr.length; j++){
//                System.out.print(arr[j][i] + "  ");
//            }
//            System.out.println("");
//        }
//        int[] arr = findMaxProduct1(new int[]{1, 2, 3, 4, 5});
//
//        for (int i = 0; i < arr.length; i++){
//            System.out.print(arr[i] + " ");
//        }

//        System.out.println(solveGreater(new int[]{1, 1, 2, 2 }));

//        System.out.println(solvePythagorean(25));

//        int[] arr = findOccurences(new int[]{1, 2, 3, 2, 2, 1, 2, 2, 2, 1});
//
//        for (int i = 0; i < arr.length; i++){
//            System.out.print(arr[i] + " ");
//        }

        System.out.println(setIntersection(new int[][] {{13, 17},{2, 16},{6, 8},{6, 13},{1, 13},{14, 20},{1, 15},{20, 21},{5, 16},{3, 14},{7, 12},{2, 15}}));
//    {3, 9},  {3, 5}, {19, 20},{3, 6},{9, 16},{10, 14},{9, 17},{1, 3},{4, 16
//        {1, 3}, {1, 4}, {2, 5}, {3, 5}
//        {1, 2}, {2, 3}, {2, 4}, {4, 5}
//        {2, 13},
//        {10, 15},
//        {1, 5},
//        {15, 20},
//        {14, 18},
//        {7, 9},
//        {10, 13},
//        {7, 18},
//        {6, 11},
//        {17, 18},
//        {1, 4},
//        {6, 16},
//        {7, 16},
//        {13, 17},
//        {11, 19}

//        System.out.println(maxCandies(new int[]{2, 1, 7, 4, 2}, 3));
    }
    public static int[][] prettyPrint(int A) {
        int length = (A * 2) - 1;
        int[][] arr = new int[length][length];

        int r = 0, c = 0, serial = A, dir=0;
        int[] dc = {0, 1, 0, -1};
        int[] dr = {1, 0, -1, 0};

        boolean attrib = false;
        int count = length * length;

        while (count > 0){

            arr[r][c] = serial;
            r += dr[dir];
            c += dc[dir];

            if(isInValid(arr, r, c)){
                r -= dr[dir];
                c -= dc[dir];
                dir = (dir + 1) % 4;
                if (dir == 3){
                    attrib = true;
                }
                r += dr[dir];
                c += dc[dir];
            }

//            if (r == 6 && c == 6){
//                serial--;
//            }
//

            if(attrib && dir == 0){
                serial--;
                attrib = false;
            }
            count--;
        }

        return arr;
    }

    private static boolean isInValid(int[][] arr, int r, int c){
        return r < 0 || c < 0 || r >= arr.length || c >= arr.length || arr[r][c] != 0;
    }

    public int solve(int[] A, int B) {
        int n = A.length;

        int result = 0;

        for (int i = 0; i < B; i++) {
            result += A[i];
        }

        int sum = result;

        for (int i = 0; i < B; i++) {
            sum -= A[B - 1 - i];
            sum += A[n - 1 - i];

            result = Math.max(result, sum);
        }

        return result;
    }

    static int[] findMaxProduct(int[] arr) {
        // Write your code here
        int[] output = new int[arr.length];
        int result = arr[0];
        output[0] = -1;

        for(int i = 1; i < arr.length; i++){
            result *= arr[i];
            output[i] = result;
            if (i < 2){
                output[i] = -1;
            }
        }

        return output;
    }

     static int[] findMaxProduct1(int[] arr) {
        // Write your code here
        int[] output = new int[arr.length];
        output[0] = -1;

        for(int i = 0; i < arr.length; i++){

            if(i < 2){
                output[i] = -1;
                continue;
            }

            int[] arr1 = new int[i + 1];

            System.arraycopy(arr, 0, arr1, 0, i + 1);
            Arrays.sort(arr1);
            output[i] = arr1[arr1.length - 1];

            for(int k = arr1.length - 2; k >= arr1.length - 3; k--){
                output[i] *= arr1[k];
            }

        }

        return output;
    }

    public static int solveGreater(int[] A) {
        int number = 1;
        for(int i = 1; i < A.length; i++){

            if(isGreater(A, i, A[i])){
                number++;
            }
        }

        return number;
    }

    private static boolean isGreater(int[] arr, int start, int value){
        for(int j = start - 1; j >= 0; j--){
            if(arr[j] >= value){
                return false;
            }
        }
        return true;
    }

    public static int solvePythagorean(int A) {
        int a = 0, b = 0, c = 0;

        int m = 2;
        int count = 0;

//        while (!(b > A || c > A || a > A)) {
//            for (int n = 1; n < m; n++) {
//                a = m * m - n * n;
//                b = 2 * m * n;
//                c = m * m + n * n;
//
//                if (b > A || c > A || a > A)
//                    break;
//
//                System.out.println(a + " " + b + " " + c);
//
//                count++;
//            }
//            m++;
//        }
//
//        if (A>=15&& A<=16){
//            count++;
//        }
        Set<Integer> squarei = new HashSet<>();
        Set<Integer> squarej = new HashSet<>();
        for(int i = 3; i <= A; i++){
            int iSquare = i * i;
            for (int j = 4; j <= A; j++){
                int iSquarePlusJSquared = iSquare + j*j;

                if(checkPerfectFloor(iSquarePlusJSquared) && Math.sqrt(iSquarePlusJSquared) <= A &&
                        (!squarei.contains(j) || !squarej.contains(i))){
                    count++;
                    squarei.add(i);
                    squarej.add(j);
                    System.out.println(i + " " + j + " " + c);
                }
            }
        }

        return count;
    }

    public static boolean checkPerfectFloor(int y){
        double sqrt = Math.sqrt(y);
        return sqrt - Math.floor(sqrt) == 0;
    }

    public int[] solveChipFactoryMovingEmptyArrayToEnd(int[] A) {
        int[] result = new int[A.length];
        int j = 0;

        for(int i = 0; i < A.length; i++){
            if(A[i] != 0){
                result[j] = A[i];
                j++;
            }
        }


        return result;
    }

    public static int[] findOccurences(int[] A) {
        Arrays.sort(A);
        List<Integer> result = new ArrayList<>();
        int counting = 0;
        int currenValues = A[0];

        for(int j = 0; j < A.length; j++){
            if(currenValues == A[j]){
                counting++;
            }else{
                result.add(counting);
                counting = 1;
                currenValues = A[j];
            }

        }
        result.add(counting);

        int[] output = new int[result.size()];
        int i = 0;

        for (Integer a : result){
            output[i] = a;
            i++;
        }


        return output;
    }

    public static int setIntersection(int[][] A){
        List<Integer> sets = new ArrayList<>();

        for(int i = 0; i < A.length; i++){
            addSerial(sets, A[i]);
        }

        Collections.sort(sets);

        Set<Integer> output = new HashSet<>();

        int back = getValWithHighestCount(sets, output);

        output.add(back);
//        System.out.println(back);
        removeAll(sets, back);

        while(!verify(output, A)){
            back = getValWithHighestCount(sets, output);
//            System.out.println(back);
            output.add(back);
            removeAll(sets, back);
        }

        List<Integer> backTracking = new ArrayList<>(output);

        for(int i = backTracking.size()-1; i>=0; i--){
            int tmp = backTracking.get(i);
            backTracking.remove(i);
            if (verify(new HashSet<>(backTracking), A)){
                output.remove(tmp);
            }else{
                backTracking.add(tmp);
            }
        }

        backTracking = new ArrayList<>(output);

        for(int i = 0; i < backTracking.size()-1; i++){
            int tmp = backTracking.get(i);
            backTracking.remove(i);
            if (verify(new HashSet<>(backTracking), A)){
                output.remove(tmp);
            }else{
                backTracking.add(tmp);
            }
        }

        for(Integer i: output){
            System.out.println(i);
        }


        return output.size();

    }

    private static void removeAll(List<Integer> set, int value){
        set.removeIf(n -> Objects.equals(n, value));
    }

    private static int getValWithHighestCount(List<Integer> arr, Set<Integer> highest){
        int currentHighCount = 0;
        int currentHighValue = arr.get(0);
        int counting = 0;
        int value = arr.get(0);


        for(int i = 0; i < arr.size(); i++){
            if(value == arr.get(i)){
                counting++;
            }else {
                if(counting > currentHighCount && !highest.contains(value)){
                    currentHighCount = counting;
                    currentHighValue = value;
                }
                counting = 1;
                value = arr.get(i);
            }
        }

        return currentHighValue;
    }


    public static boolean verify(Set<Integer> values, int[][] A){
        if(values.isEmpty()){
            return false;
        }
        boolean[] res = new boolean[A.length];

        for(int arr = 0; arr < A.length; arr++){
            int[] arr1 = A[arr];

            int counts = 0;
            for(int i = arr1[0]; i <= arr1[arr1.length -1]; i++){
                if(values.contains(i)){
                    counts++;
                }
            }
            if(counts >= 2){
                res[arr] = true;
            }
        }

        for(boolean b: res){
            if (!b)
                return false;
        }

        return true;
    }

    private static void addSerial(List<Integer> list, int[] arr){
        for (int i = arr[0]; i <= arr[1]; i++){
            list.add(i);
        }
    }

    private static int getIntersectionForArray(int[][] a, int[] ints, int startpoint, Set<Integer> values) {
        Integer j = 0;


        for(int i = startpoint; i < a.length; i++){
            int[] arr = a[i];

            if(ints[0] == arr[0] || ints[1] == arr[1] || ints[0] == arr[1] || ints[1] == arr[0]){
                j++;
            }
        }
//
//        for (int i = 0; i < ints.length; i++){
//            int value = ints[i];
//            if(!values.contains(value)) {
//                values.add(value);
//                for (int k = startpoint; k < a.length; k++) {
//                    int[] arr = a[k];
//                    for (int l = 0; l < arr.length; l++) {
//                        if (value == arr[l]) {
//                            j++;
//                            l++;
//                            break;
//                        }
//                    }
//                }
//            }
//        }
        return  j;
    }

    static int maxCandies(int[] arr, int k) {
        int candiesEaten = 0;
        // Write your code here
        int limit = 0;

        while (limit < k){
            int maxVal = arr[0];
            int maxIndex = 0;
            for(int i = 1; i < arr.length; i++){
                if (maxVal < arr[i]){
                    maxVal = arr[i];
                    maxIndex = i;
                }
            }

            candiesEaten += maxVal;
            arr[maxIndex] = maxVal/2;
            limit++;
        }

        return candiesEaten;

    }
}


class Solution {
    public static void main(String[] args){
//        System.out.println(solution(new int[]{2, 2, 3, 4, 3, 3, 2, 2, 1, 1, 2, 5}));
//        int[] arr = findMedian(new int[]{5, 15, 1, 3});
//        for (int i = 0; i< arr.length; i++){
//            System.out.println(arr[i]);
//        }
//        System.out.println(getTotalTime(new int[]{4, 2, 1, 3}));
//        int[] arr = findMinArray(new int[]{5, 3, 1}, 2);
//        for (int i = 0; i< arr.length; i++){
//            System.out.println(arr[i]);
//        }
    }

    public static int solution(int[] A) {
        // write your code in Java SE 8

        int plain = A[0];
        int counting = 0;
        int currentValley = 0;
        int currentHill = 0;

        for (int p = 0; p < A.length - 1; p++){
            if(A[p] < A[p + 1]){
                if (currentHill == 0){
                    counting++;
                    currentValley = A[p];
                }
                if(currentHill - A[p] >= 2){
                    counting++;
                    currentValley = A[p];
                }

            }else if(A[p] > A[p + 1] && (A[p] - currentValley) >= 2){
                counting++;
                currentHill = A[p];
            }else{

            }
        }

        return counting == 0? 1: counting;
    }

    static int[] findMedian(int[] arr) {
        // Write your code here
        int total = 0;
        int count = 1;
        int[] output = new int[arr.length];

        for (int i = 0; i < arr.length; i++){
            if(i == 0){
                output[i] = arr[i];
                continue;
            }

            int j = i + 1;
            int[] newArr = new int[i+1];
            System.arraycopy(arr, 0, newArr, 0, j);
            Arrays.sort(newArr);
            int midPos = i / 2;

            if(j % 2 == 0){
                output[i] = (newArr[midPos] + newArr[midPos + 1])/2;
            }else{
                output[i] = newArr[midPos];
            }

        }

        return output;
    }

    //need to revist this solution
    static int minLengthSubstring(String s, String t) {
        // Write your code here
        if (t.length() == 0) {
            return -1;
        }
        List<Character> charsT = new ArrayList<Character>();
        for (int x=0;x<t.length();x++) {
            charsT.add(t.charAt(x));
        }
        int lastIndex = 0;
        for (int x=0;x<s.length() && charsT.size() > 0;x++) {
            charsT.remove((Character) s.charAt(x));
            lastIndex++;
        }
        if (charsT.size() > 0) {
            return -1;
        }
        return lastIndex;

    }

    static int getTotalTime(int[] arr) {
        // Write your code here
        Arrays.sort(arr);

        int i = arr.length - 1;
        int m = 0;

        int[] output = new int[arr.length - 1];
        int k = 0;

        while (i >= 0){
            if(i == arr.length - 1) {
                m += (arr[i - 1] + arr[i]);
                i--;
            }else{
                m += arr[i];
            }

            output[k] = m;
            k++;
            i--;
        }

        int result = 0;
        for( int l = 0; l < output.length; l++){
            result += output[l];
        }

        return result;

    }

    public static int[] findMinArray(int[] arr, int k) {
        for(int i =0; i < arr.length && k >0; i++) {
            int minIndex = findMinAtDistanceK(arr, i, k);
            //if minimum element is already at position i, nothing to do
            if(minIndex == i) {
                continue;
            }
            swap(arr, i, minIndex);
            // we have used up minindex-i swaps
            k -= minIndex - i;
        }
        return arr;
    }

    private static int findMinAtDistanceK(int [] arr, int start, int k) {
        int index = 0, min = Integer.MAX_VALUE;
        // find minimum element at distance k from start
        int end = 0;
        if(!(start + k >= arr.length)){
            end = start + k;
        }else{
            end = arr.length - 1;
        }

        for(int i=start; i <= end; i++) {
            if(arr[i] < min) {
                min = arr[i];
                index =i;
            }
        }
        return index;
    }

    private static void swap(int [] arr, int start, int end) {
        //move element at position end to start
        while(end > start) {
            int temp = arr[end];
            arr[end] = arr[end-1];
            arr[end-1] = temp;
            end--;
        }
    }

    int minOverallAwkwardness(int[] arr) {
        // Write your code here
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];

        for(int i = 2; i < arr.length; i+=2){
            diff = Math.max(diff, arr[i] - arr[i - 2]);
        }
        for(int i = 3; i < arr.length; i+=2){
            diff = Math.max(diff, arr[i] - arr[i - 2]);
        }

        return Math.max(diff, arr[arr.length - 1] - arr[arr.length - 2]);

    }
}

class Main {

    static class Node {
        int data;
        Node left;
        Node right;

        Node() {
            this.data = 0;
            this.left = null;
            this.right = null;
        }

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Add any helper functions you may need here


    static int visibleNodes(Node root) {
        // Write your code here
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(visibleNodes(root.left), visibleNodes(root.right));
    }

    static int visibleNodesDFS(Node root){
        int treeLengthLeft = 0;
        int treeLengthRight = 0;

        Stack<Node> stack = new Stack<>();

        stack.add(root);

        while(!stack.isEmpty()){
            Node node = stack.pop();

            if(node.left != null){
                stack.add(node.left);
                treeLengthLeft++;
            }

            if(node.right != null){
                stack.add(node.right);
                treeLengthRight++;
            }
        }

        return Math.max(treeLengthLeft, treeLengthRight);
    }

    public static void main(String[] args){
//        Node root_1 = new Node(8);
//        root_1.left = new Node(3);
//        root_1.right = new Node(10);
//        root_1.left.left = new Node(1);
//        root_1.left.right = new Node(6);
//        root_1.right.right = new Node(14);
//        root_1.left.right.left = new Node(4);
//        root_1.left.right.right = new Node(7);
//        root_1.right.right.left = new Node(13);
//
//        Node root_2 = new Node(10);
//        root_2.left = new Node(8);
//        root_2.right = new Node(15);
//        root_2.left.left = new Node(4);
//        root_2.left.left.right = new Node(5);
//        root_2.left.left.right.right = new Node(6);
//        root_2.right.left = new Node(14);
//        root_2.right.right = new Node(16);
//
//        System.out.println(visibleNodesDFS(root_2));
//        System.out.println(findEncryptedWord("facebook"));

        System.out.println(balancedSplitExists(new int[]{12, 7, 6, 7, 6}));
    }


    int[] getMilestoneDays(int[] revenues, int[] milestones) {
        // Write your code here
        int[] result = new int[milestones.length];

        Set<Integer> set = new HashSet<>();

        int output = 0;
        int i = 0;
        int m = 0;

        while(i < revenues.length){
            output += revenues[i];

            for(int j = 0; j < milestones.length; j++){
                if(output >= milestones[j] && !set.contains(milestones[j])){
                    result[j] = i + 1;
                    set.add(milestones[j]);
                }
            }

            i++;
        }

        return result;

    }

    static String findEncryptedWord(String s) {
        // Write your code here
        return encryptWord(s);
    }

    static String encryptWord(String s){
        if (s.length() <= 2){
            return s;
        }

        int mid = s.length()/2;

        if (s.length()%2 == 0){
            mid = mid - 1;
        }

        String splitStr = String.valueOf(s.charAt(mid));
        s = s.replaceFirst(splitStr, "");

        StringBuilder output = new StringBuilder();
        output.append(encryptWord(s.substring(0, mid)));
        output.append(encryptWord(s.substring(mid)));

        return splitStr + output.toString();
    }

    boolean canGetExactChange(int targetMoney, int[] denominations) {
        // Write your code here
        for(int i = denominations.length - 1; i >= 0; i--){
            if(denominations[i] > targetMoney){
                continue;
            }

            if(targetMoney % denominations[i] == 0){
                return true;
            }

            targetMoney = targetMoney % denominations[i];
        }

        return false;
    }

    static boolean balancedSplitExists(int[] arr) {
        // Write your code here
        List<Integer> b = new ArrayList<>();

        Arrays.sort(arr);
        int removeIndex = arr.length - 1;

        while(sum(arr) >= sum(b)){
            if(sum(arr) == sum(b)){
                if(b.contains(arr[removeIndex])){
                    return false;
                }
                return true;
            }

            b.add(arr[removeIndex]);
            arr[removeIndex] = 0;
            removeIndex--;
        }

        return false;

    }

    static int sum(int[] arr){
        int total = 0;
        for (int i = 0; i < arr.length; i++){
            total += arr[i];
        }

        return total;
    }

    static int sum(List<Integer> lst){
        int total = 0;
        for(Integer i : lst){
            total += i;
        }

        return total;
    }
}


class Main2 {
    public static void main(String[] a){
//        ArrayList<Sides> arr_1 = new ArrayList<>();
//        arr_1.add(new Sides(7, 6, 5));
//        arr_1.add(new Sides(5, 7, 6));
//        arr_1.add(new Sides(8, 2, 9));
//        arr_1.add(new Sides(2, 3, 4));
//        arr_1.add(new Sides(2, 4, 3));
//        System.out.println(countDistinctTriangles(arr_1));

//        System.out.println(isBalanced("{(})"));
        System.out.println(numberOfWays(new int[]{1, 5, 3, 3, 3}, 6));
    }

    static class Sides {
        int a;
        int b;
        int c;

        Sides(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Sides)) return false;
            Sides sides = (Sides) o;
            return a == sides.a &&
                    b == sides.b &&
                    c == sides.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
    }

    // Add any helper functions you may need here


    static int countDistinctTriangles(ArrayList<Sides> arr) {
        // Write your code here
        int distinctCount = 0;

        for (int i = 0; i < arr.size(); i++) {
            Sides sde = arr.get(i);

            for (int j = i + 1; j < arr.size(); j++) {
                Sides sde1 = arr.get(j);
                List<Integer> rsd = Arrays.asList(sde1.a, sde1.b, sde1.c);

                if(rsd.containsAll(Arrays.asList(sde.a, sde.b, sde.c))) {
                    distinctCount++;
                }
            }
        }
        return arr.size() - distinctCount;
    }

    static boolean isBalanced(String s) {
        // Write your code here
        Stack<Character> stk = new Stack<>();

        if(s.length() % 2 != 0){
            return false;
        }

        for(char c : s.toCharArray()){
            if(c == '(' || c == '[' || c == '{') {
                stk.add(c);
            }else{
                if (stk.isEmpty()){
                    return false;
                }
                char q = stk.pop();
                if(q == '(' && c != ')'){
                    return false;
                }
                if(q == '[' && c != ']'){
                    return false;
                }
                if(q == '{' && c != '}'){
                    return false;
                }
            }
        }

        return stk.isEmpty();
    }

    private static int removeAttr(List<Character> chars, int i){

        if (chars.get(i) == '('){
            if(chars.get(i+1) == ')'){
                chars.remove(i);
                chars.remove(i);
                i = 0;
            }else{
                i++;
            }
        }else if (chars.get(i) == '{'){
            if(chars.get(i+1) == '}'){
                chars.remove(i);
                chars.remove(i);
                i = 0;
            }else{
                i++;
            }
        }else if (chars.get(i) == '['){
            if(chars.get(i+1) == ']'){
                chars.remove(i);
                chars.remove(i);
                i = 0;
            }else{
                i++;
            }
        }else {
            i++;
        }

        return i;
    }

    static int numberOfWays(int[] arr, int k) {

        // **** initialization ****
        int pairs                               = 0;
        HashMap<Integer, List<Integer>> valLoc  = new HashMap<>();
        List<Integer> locs                      = null;

        // **** populate hashmap of value : location O(n) ****
        for (int i = 0; i < arr.length; i++) {

            // **** get locations for this value ****
            locs = valLoc.get(arr[i]);
            if (locs == null) {
                locs = new ArrayList<Integer>();
                locs.add(i);
                valLoc.put(arr[i], locs);
            } else {
                locs.add(i);
            }
        }

        // ???? ????
        // System.out.println("numberOfWays <<< valLoc: " + valLoc.toString());

        // **** count pairs (pairs will be double counted) ****
        for (int i = 0; i < arr.length; i++) {

            // **** skip this value (if needed) ****
            if (arr[i] > k) continue;

            // **** compute the needed value ****
            int val = k - arr[i];

            // **** look up the value in the hashmap O(n) ****
            locs = valLoc.get(val);
            if (locs != null) {

                // **** double count pairs (a,b) & (b,a) ****
                pairs += locs.size();

                // **** decrement count (a,a) ****
                if (val == arr[i]) pairs--;
            }
        }

        // **** pairs were double counted ****
        return pairs / 2;
    }
}