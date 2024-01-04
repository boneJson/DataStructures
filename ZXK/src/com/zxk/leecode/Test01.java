package com.zxk.leecode;

//输入：nums = [1,2,3,6]
//输出：1
//解释：满足要求的唯一一个四元组是 (0, 1, 2, 3) 因为 1 + 2 + 3 == 6 。
public class Test01 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 5};
        System.out.println(new Test01().countQuadruplets(nums));
    }

    public int countQuadruplets(int[] nums) {
        int count = 0;
        for(int i=0;i<nums.length;i++){
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    for (int l = k+1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] == nums[l]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
