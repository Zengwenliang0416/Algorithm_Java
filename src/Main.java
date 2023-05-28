/**
 * 剑指offer刷题
 */
public class Main {
    public static void main(String[] args) {

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
        // 根据API文档，
        String newStr = new String(array,0,size);
        return newStr;
    }
}