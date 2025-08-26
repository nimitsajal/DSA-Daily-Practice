package problems.Arrays.Easy.remove_duplicates_from_sorted_array;

/*
 * Link: https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/
 */

 class Solution {
    public int removeDuplicates(int[] nums) {
        int unique = 1;
        int n = nums.length;

        for (int i=0, j=1; i<n-1; i++) {
            // System.out.println("[1]: unique: " + unique + ", i: " + i + ", j: " + j + ", nums[i]: " + nums[i] + ", nums[j]: " + nums[j]);
            if (nums[i] == nums[j]) {
                while (nums[i] == nums[j] && j<n-1) {
                    j++;
                }
                nums[i+1] = nums[j];
            }
            if (nums[i] != nums[j])
                    unique++;
            // System.out.println("[2]: unique: " + unique + ", i: " + i + ", j: " + j + ", nums[i]: " + nums[i] + ", nums[j]: " + nums[j]);
            // System.out.println();
        }

        // System.out.println(unique);
        return unique;

    }
}