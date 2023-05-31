import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.xml.stream.FactoryConfigurationError;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.logging.Level;

public class Algorithm {
    public static void main(String[] args){
        Solution456 sulution = new Solution456();
        System.out.println(sulution.find132pattern(new int[] {1,2,3,4}));
//        Solution74 searchMatrix = new Solution74();
//        System.out.println(searchMatrix.searchMatrix(new int[][] {{1}},0));
//        Solution34 letcode = new Solution34();
//        System.out.println(letcode.searchRange(new int[]{5,7,7,8,8,10},8));
    }
}
class Solution1482 {
    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length/k < m) return -1;
        int left = Arrays.stream(bloomDay).min().getAsInt(), right = Arrays.stream(bloomDay).max().getAsInt();
        while (left < right){
            int mid = (left + right) / 2;
            if (findDay(bloomDay,mid,m,k)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }
    boolean findDay(int[] bloomDay, int mid, int m, int k){
        int flowerNums = 0;
        int bouquet = 0;
        for (int i = 0; (i < bloomDay.length) & (bouquet < m); i++) {
            if (bloomDay[i] <= mid){
                flowerNums++;
                if (flowerNums == k){
                    bouquet++;
                    flowerNums=0;
                }
            }else {
                flowerNums = 0;
            }
        }
        return bouquet >= m;
    }
}


/**
 * letcode 456题
 * 使用的枚举方法，需要结合TreeMap()。
 */
class Solution456 {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        // 左侧最小值
        int leftMin = nums[0];
        // 右侧所有元素
        TreeMap<Integer, Integer> rightAll = new TreeMap<Integer, Integer>();

        for (int k = 2; k < n; ++k) {
            rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
        }

        for (int j = 1; j < n - 1; ++j) {
            if (leftMin < nums[j]) {
                Integer next = rightAll.ceilingKey(leftMin + 1);
                if (next != null && next < nums[j]) {
                    return true;
                }
            }
            leftMin = Math.min(leftMin, nums[j]);
            rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
            if (rightAll.get(nums[j + 1]) == 0) {
                rightAll.remove(nums[j + 1]);
            }
        }

        return false;
    }
}

/**
 * letcode 153题
 */
class Solution153 {
    /**
     * 找到环形数组中的最小值。
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        /*
        只要有序数组发生挪动，那么最右边的值肯定是小于最左边的数组
        此时可以假设最右边的值为整个数组中的最小值
        */
        int min = nums[right];
        /*
        确立好边界条件，我们采用的是左闭右闭形式
         */
        while (left <= right){
            // 二分查找符合条件的下标
            int mid = (left + right) / 2;
            // 当某个值小于min，需要更新最小值，同时，更新区间的右边界，如果这个值大于或者等于min，更新左边界
            if(nums[mid] < min){
                min = nums[mid];
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        // 当left>right时，就找到了这个最小值，位置应该是right+1
        return min;
    }
}

/**
 * letcode 81题
 */
class Solution81 {
    /**
     * 寻找非降序环形数组中是否存在某个值
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        if (nums.length ==0){
            return false;
        }
        if (nums.length == 1){
            return target == nums[0];
        }
        while (left <= right){
            /*
            这个错误的差别需要手算去理解一下
            错误2:(left + right) / 2写成了left + (right - left) >> 1
             */
            int mid = (left + right) >> 1;
            if (nums[mid] == target){
                return true;
            }
            // 用于判断mid前后的值是否与mid保持一致，如果值相同，说明需要对区间进行缩减，确保该区间中存在单调性
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                ++left;
                --right;
            } else if (nums[left] <= nums[mid]){
                if ((nums[left] <= target) && (target < nums[mid])){
                    right = mid -1;
                }else {
                    left = mid + 1;
                }
            }else {
                // 错误一：target <= nums[right]写成了target < nums[right]
                if ((nums[mid] < target) && (target <= nums[right])){
                    left = mid + 1;
                }else {
                    right = mid -1;
                }
            }
        }
        return false;
    }
}

/**
 * letcode 74题
 */
class Solution74 {
    /**
     * 搜索二维矩阵中是否存在某个值
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = searchRow(matrix, target);
        if (row < 0){
            return false;
        }else {
            return searchColumn(matrix,target,row);
        }
    }

    /**
     * 找到target所属列
     * @param matrix
     * @param target
     * @param row
     * @return
     */
    boolean searchColumn(int[][] matrix, int target, int row){
        int left = 0, right = matrix[row].length;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if (matrix[row][mid] == target){
                return true;
            } else if (matrix[row][mid] < target) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return false;
    }

    /**
     * 找到target所属行
     * @param matrix
     * @param target
     * @return
     */
    int searchRow(int[][] matrix,int target){
        int low = 0, high = matrix.length;
        while (low < high){
            int mid = low + ((high-low)>>1);
            if (matrix[mid][0] == target){
                return mid;
            }else if (matrix[mid][0] < target){
                low = mid + 1;
            }else {
                high = mid;
            }
        }
        return low-1;
    }
}

/**
 * 力扣 33题
 * 当给到的数组是无序时，找到数组的有序部分二分查找。
 */
class Solution33 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        if (nums.length ==0){
            return -1;
        }
        if (nums.length == 1){
            return target == nums[0]? 0:-1;
        }
        while (left <= right){
            // 错误2:(left + right) / 2写成了left + (right - left) >> 1
            int mid = (left + right) / 2;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[0] <= nums[mid]){
                if ((nums[0] <= target) && (target < nums[mid])){
                        right = mid -1;
                    }else {
                        left = mid + 1;
                    }
            }else {
                // 错误一：target <= nums[right]写成了target < nums[right]
                if ((nums[mid] < target) && (target <= nums[right])){
                    left = mid + 1;
                }else {
                    right = mid -1;
                }
            }
        }
        return -1;
    }
}


/**
 * 力扣 34题，这里发现的问题：
 * n为非负数时，>> 1和/ 2的结果是一样的
 * n为负数且还是偶数时，>> 1和/ 2的结果是一样的
 * n为负数且还是奇数时，>> 1和/ 2的结果是不一样的
 */
class Solution34{
    int[] searchRange(int[] nums, int target){
        int leftIdx = searchLeft(nums,target);
        int rightIdx = searchRight(nums, target);
        if ((leftIdx == -2) || (rightIdx == -2)) return new int[] {-1,-1};
        if ((rightIdx - leftIdx) > 1) return new int[]{leftIdx+1,rightIdx-1};
        return new int[]{-1,-1};
    }

    int searchLeft(int[] nums, int target){
        int left = 0, right = nums.length - 1, leftIdx = -2;
        while (left <= right){
            int mid  = left + (right - left) /2;
            if (nums[mid] >= target){
                right = mid -1;
                leftIdx = right;
            }else {
                left = mid + 1;
            }
        }
        return leftIdx;
    }

    int searchRight(int[] nums, int target){
        int left = 0, right = nums.length - 1, rightIdx = -2;
        while (left <= right){
            int mid = left + ((right - left) / 2);
            if (nums[mid] <= target){
                left = mid + 1;
                rightIdx = left;
            }else {
                right = mid -1;
            }
        }
        return rightIdx;
    }
}
