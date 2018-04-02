package com.jl.myproject.algorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

//
//Given an array of integers, return indices of the two numbers such that they add up to a specific target.
//
//You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//Example:
//Given nums = [2, 7, 11, 15], target = 9,
//
//Because nums[0] + nums[1] = 2 + 7 = 9,
//return [0, 1].

public class TwoSum {
	public static void main(String[] args){
		int[] nums = {2,3,1};
		Arrays.sort(nums);
		for(int a:nums){
			System.out.println(a);
		}
	//	System.out.println("1");
	}
	
	
//	solution 1: brute force
//	time complexity  o(n^2)
//	space complexity o(1)
	public int[] twoSum1(int[] nums, int target) {
	
		for(int a =0;a < nums.length ;a++){
			for(int b =a ; b <nums.length; b++){
				if(nums[a] + nums[b] == target){
					return new int[]{a,b};
				}
			}
		}
		throw new IllegalArgumentException("thers is no solution");
	}

		
//	solution 2: hash Table
//	time complexity o(n)
//	space complexity o(n)
	public int[] twoSum2(int[] nums, int target) {
		
		Map<Integer, Integer> map = new HashMap<>();
		for(int a =0;a< nums.length; a++){
//			map.put(nums[a]);
//			dict
			//Dictionary<K, V>
	//	Arrays.
		}
		throw new IllegalArgumentException("thers is no solution");
	}
}
