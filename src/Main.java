/**
 * 剑指offer刷题
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(reverseLeftWords("pwwkew",2));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(space2String("We are  happy."));
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
     * Letcode 3
     *
     */
    public static int lengthOfLongestSubstring(String s) {
        int len = 1;
        int maxLen = 0;
        int left = 0;
        int right = left + 1;
        while (right < s.length()) {
            if (s.length() == 1) {
                return 1;
            }else {
                if (s.charAt(right) != s.charAt(left)) {
                    right += 1;
                    len += 1;
                }else {
                    if (len > maxLen){
                        maxLen = len;
                    }else {
                        left += 1;
                        right += 1;
                    }
                }
            }
        }
        return maxLen;
    }

    /**
     * 剑指offer 58
     * @param s
     * @param n
     * @return
     */
    public static String reverseLeftWords(String s, int n) {
        int lenth = s.length();
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
     * @param s
     * @return
     */
    public boolean isNumber(String s) {

        return chechNumber;
    }
}