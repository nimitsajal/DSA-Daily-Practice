package problems.Arrays.Easy.check_if_array_is_sorted_and_rotated;

/*
 * Link: https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/
 */

class Solution {
    public boolean check(int[] nums) {
        int drop = 0;
        int n = nums.length;

        if (nums[0] < nums[n-1]) {
            drop ++;
        }
        for (int i=0; i<n-1; i++) {
            if (nums[i+1] < nums[i]) {
                drop++;
            }
            if (drop > 1) {
                return false;
            }
        }

        return true;

    }
}
