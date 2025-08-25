# Check if Array Is Sorted and Rotated

## Problem Statement
Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.

There may be duplicates in the original array.

**Examples:**
- `[3,4,5,1,2]` → `true` (rotated sorted array `[1,2,3,4,5]`)
- `[2,1,3,4]` → `false` (no valid rotation)
- `[1,2,3]` → `true` (no rotation needed)

## Intuition

### 1. Pivot Point Concept
Since this is a rotated array, it will have a **pivot point** which divides the original array into 2 separate but still sorted arrays. We can detect this pivot point as a "drop" in value where the next element is smaller than the current element while iterating. If there are either 0 or 1 such drops, while the rest of the array maintains sorted order, then this is a valid rotated sorted array.

### 2. Sorted Order Check
To verify sorted order, simply check if the next element is larger than or equal to the current element while iterating over the array. Any violation (where next < current) indicates a drop.

### 3. Edge Case - Wrap Around Drop
The **first element cannot be smaller than the last element**. If that's the case, it represents another drop. 

**Explanation:** When iterating through the middle of the array, we check that the next element must be ≥ current element. If next < current, there's a drop. Similarly, imagine we're at the last element - the "next" element (which wraps to the first element) must be ≥ last element. If `first < last`, there's a drop.

Since we don't get a chance to compare the first element (next of last) with the last element during normal iteration, this drop would be missed. Thus, this check must be done **before** the normal loop runs.

### 4. Early Termination
At each iteration, keep checking the drop count. If it exceeds 1, immediately return `false` since there must be only 0 or 1 drop for a valid rotated sorted array. If the loop completes successfully, return `true`.

## Algorithm
1. Initialize `drop = 0`
2. **Edge case check:** If `nums[0] < nums[n-1]`, increment `drop`
3. Iterate through array comparing adjacent elements
4. If `nums[i+1] < nums[i]`, increment `drop`
5. If `drop > 1` at any point, return `false`
6. Return `true` after loop completion

## Dry Run Examples

### Example 1: Normal Case - `[3,4,5,1,2]`
**Expected Output:** `true`

| Step | Action | Comparison | Drop Count | Notes |
|------|--------|------------|------------|-------|
| 1 | Initialize | - | `drop = 0` | Starting state |
| 2 | Edge case check | `nums[0] < nums[n-1]` → `3 < 2` → `false` | `drop = 0` | No wrap-around drop |
| 3 | i=0 | `nums[1] < nums[0]` → `4 < 3` → `false` | `drop = 0` | No drop |
| 4 | i=1 | `nums[2] < nums[1]` → `5 < 4` → `false` | `drop = 0` | No drop |
| 5 | i=2 | `nums[3] < nums[2]` → `1 < 5` → `true` | `drop = 1` | **Drop found!** |
| 6 | i=3 | `nums[4] < nums[3]` → `2 < 1` → `false` | `drop = 1` | No drop |
| 7 | End | Loop complete, `drop ≤ 1` | - | **Return `true`** |

**Result:** Valid rotated sorted array with exactly 1 drop at the pivot point.

---

### Example 2: Edge Case - `[2,1,3,4]`
**Expected Output:** `false`

| Step | Action | Comparison | Drop Count | Notes |
|------|--------|------------|------------|-------|
| 1 | Initialize | - | `drop = 0` | Starting state |
| 2 | Edge case check | `nums[0] < nums[n-1]` → `2 < 4` → `true` | `drop = 1` | **Wrap-around drop detected!** |
| 3 | i=0 | `nums[1] < nums[0]` → `1 < 2` → `true` | `drop = 2` | **Second drop found!** |
| 4 | Check condition | `drop > 1` → `2 > 1` → `true` | - | **Early termination** |
| 5 | Return | - | - | **Return `false`** |

**Result:** Invalid array with 2 drops - cannot be a rotated sorted array.

## Time & Space Complexity
- **Time:** O(n) - single pass through array
- **Space:** O(1) - only using constant extra space

## Key Insights
- **Pattern Recognition:** Array rotation problems often involve counting discontinuities
- **Wrap-around Logic:** The condition `nums[0] < nums[n-1]` captures the circular nature of rotation
- **Early Exit:** Optimization to return false as soon as more than 1 drop is found