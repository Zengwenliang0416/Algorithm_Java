import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 剑指offer刷题
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[] {1,3,5,6},7));
        System.out.println(search(new int[] {-1, 0, 3, 5, 9, 12},3));
        System.out.println(reverseLeftWords("pwwkew",2));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(space2String("We are  happy."));
        }
    /**
     * 力扣 34
     */
    public static int[] searchRange(int[] nums, int target){
        int[] startEnd = {-1,-1};


    }
    /**
     * 力扣 35
     */
    public static int searchInsert(int[] nums, int target){
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = left + ((right - left)>>1);
            if (nums[mid]==target){
                return mid;
            } else if (nums[mid]<target) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }
    /**
     * 力扣 704
     */
    public static int search(int[] nums, int target){
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = left + ((right - left)>>1);
            if (nums[mid]==target){
                return mid;
            } else if (nums[mid]<target) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return -1;
    }

    /**
     * 剑指offer 5
     * @param s
     * @return s
     */
    public static String space2String(String s) {
        // String数据类型方法之一：s.length()，返回字符串的长度，包括空格
        int length  = s.length();

        // 字符数组的创建方式：char[] charArray = new char[数组长度];
        char[] array = new char[length*3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            // String数据类型方法之一：s.charAt(字符下标)，返回某个特定位置的字符
            char c = s.charAt(i);
            // 遍历字符串中的每个字符，将其中的空格转换成题目需要的数据，注意这个过程中我们需要同步获取新的字符串的长度，也就是size
            if (c==' '){
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        // new String()实际上是创建一个String类的对象newStr
        // 根据API文档，array是字符数组，1是从array第几个字符开始创建对象newStr，size表示newStr的长度
        String newStr = new String(array,1,size);
        return newStr;
    }

    /**
     * Letcode 3   无重复字符的最长子串，Hashmap，滑动窗口
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 剑指offer 58
     * @param s
     * @param n
     * @return
     */
    public static String reverseLeftWords(String s, int n) {
        int lenth = s.length();

        // 创建两个数组分别存储n之前的字符和n之后的字符
        char[] array1 = new char[lenth];
        char[] array2 = new char[lenth];

        for (int i = 0; i < n; i++) {
            array1[i] = s.charAt(i);
        }
        for (int i = n; i < lenth ; i++) {
            array2[i] = s.charAt(i);
        }
        String newStr1 = new String(array1,0,n);
        String newStr2 = new String(array2,n,lenth-n);

        return newStr2+newStr1;
    }

    /**
     * 剑指offer 20
     * 解法：有效状态机，Hashmap学习
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        boolean chechNumber = true;
        return chechNumber;
    }


}