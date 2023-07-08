
public class Main {

    static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] list = new int[26];
        for (int i = 0; i < s.length(); i++) {
            list[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < t.length(); i++) {

            if (--list[t.charAt(i)-'a']<0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {

        System.out.println(isAnagram("car","rat"));
    }
}
