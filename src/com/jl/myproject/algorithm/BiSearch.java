//折半查找,默认输入的是有序表，最后一个数字为要查找的值
//若能查找到返回
package com.jl.myproject.algorithm;

import java.util.Arrays;

import javax.naming.directory.SearchControls;

public class BiSearch {
	public static void main(String[] args){
		int[] arrays=new int[args.length];
		for(int i =0;i< args.length;i++){
			arrays[i]=Integer.valueOf(args[i]);
		}
		int key = arrays[arrays.length-1];
		int pointer = search(Arrays.copyOf(arrays, arrays.length-1),key);
		if(pointer==-1){
			System.out.println("没有该数据");
		}
		else{
			//System.out.println(String.valueOf(pointer) + " ：" + String.valueOf(args[pointer]));
			System.out.println(pointer+ " ：" + args[pointer]);
		}
	
	}
	
	 public static int search(int[] arrays,int key){
		int end =arrays.length-1;
		int start = 0;

		while(end-start>=0){
			int mid = Math.round((end + start)/2);
			
			if(key>arrays[mid]){
				start = mid+1;
			}
			else if(key <arrays[mid]){
				end = mid-1;
			}
			else if(key ==arrays[mid]){
				return mid;
			}
		}
//		另一种写法,上面一种写法的优点是最后end，start，和mid值是相等的，对于key值在头或尾不用单独考虑
//		if(key==arrays[end]){
//			return end;
//		}
//		else if(key==arrays[start]){
//			return start;
//		}
//		while(end-start>1){
//			int mid = Math.round((end + start)/2);
//			
//			if(key>arrays[mid]){
//				start = mid;
//			}
//			else if(key <arrays[mid]){
//				end = mid;
//			}
//			else if(key ==arrays[mid]){
//				return mid;
//			}
//		}
		return -1;
	}
}
