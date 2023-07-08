import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetCode {
    public static void main(String[] args){
        Solution59 maxArea = new Solution59();
        System.out.println(maxArea.generateMatrix(5));
    }
}

class Solution59 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int count = 1;
        int loop = n / 2;
        int startX = 0, startY = 0;
        int offSet = 1;
        while (loop-- > 0){
            int i = startX;
            int j = startY;
            for (j = startY; j < n-offSet; j++) {
                matrix[startX][j] = count++;
            }
            for (i = startX; i < n-offSet; i++) {
                matrix[i][j] = count++;
            }
            for (; j > startY ; j--) {
                matrix[i][j] = count++;
            }
            for (; i > startX; i--) {
                matrix[i][j] = count++;
            }
            startY++;
            startX++;
            offSet++;
        }
        if (n%2!=0){
            matrix[n/2][n/2] = count;
        }
        return matrix;
    }
}
class Solution658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int right = binarySearch(arr, x);
        int left = right - 1;
        while(k-- > 0){
            if(left < 0){
                right++;
            }else if(right >= arr.length){
                left--;
            }else if(x - arr[left] <= arr[right] - x){
                left--;
            }else{
                right++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = left + 1; i < right; i++){
            ans.add(arr[i]);
        }
        return ans;
    }

    private int binarySearch(int[] arr, int x){
        int low = 0, high = arr.length - 1;
        while(low < high){
            int mid = low + (high - low)/2;
            if(arr[mid] >= x){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        return low;
    }
}
class Solution209 {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, sum = 0, lenth = nums.length + 1;
        while (right < nums.length){
            sum += nums[right];
            while (sum >= target){
                lenth = Math.min (right - left+1,  lenth);
                sum -= nums[left++];
            }
            right += 1;
        }
        return lenth == nums.length + 1?0:lenth;
    }
}
//class Solution2465 {
//    public int distinctAverages(int[] nums) {
//
//    }
//}
class Solution969 {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ks = new ArrayList<Integer>();
        for (int k = arr.length; k > 1; k--) {
            int index = 0;
            for (int i = 1; i < k; i++) {
                if (arr[i] > arr[index]){
                    index = i;
                }
            }
            revearse(arr,index);
            ks.add(index + 1);
            revearse(arr,k - 1);
            ks.add(k);
        }
        return ks;
    }
    void revearse(int[] array, int index){
        int left = 0, right = index;
        while (left < right){
            int tmp = array[right];
            array[right--] = array[left];
            array[left++] = tmp;
        }
    }
}

/**
 *
 */
class Solution27 {
    public int removeElement1(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast]!=val){
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    public int removeElement2(int[] nums, int val){
        int left = 0;
        int right = nums.length - 1;
        while(right >= 0 && nums[right] == val) right--; //将right移到从右数第一个值不为val的位置
        while(left <= right) {
            if(nums[left] == val) { //left位置的元素需要移除
                //将right位置的元素移到left（覆盖），right位置移除
                nums[left] = nums[right];
                right--;
            }
            left++;
            while(right >= 0 && nums[right] == val) right--;
        }
        return left;
    }
}


class Solution11 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length-1, maxArea = -1;
        while (left < right){
            int high = (height[left] > height[right] ? height[right] : height[left]);
            int area = high*(right-left);
            maxArea = maxArea > area ? maxArea :area;
            while (height[left] <= high && left < right) left++;
            while (height[right] <= high && left < right) right--;
        }
        return maxArea;
    }
}

class Solution825 {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int left = 0, right = 0, allContact = 0;
        for (int age : ages){
            if (age < 15) continue;

            while (ages[left] <= 0.5*age+7) left++;

            while (right+1 < ages.length&&ages[right+1]<= age) right++;

            allContact += (right - left);
        }
        return allContact;
    }
}