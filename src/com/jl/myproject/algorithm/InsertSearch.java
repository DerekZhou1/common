//插值查找，生成插值公式，
//原公式mid=(low+high)/2=low +(high-low)/2
//插值公式 将(high-low)/2 换成 (key-a[low])/(a[high]-a[low])*(high-low)
package com.jl.myproject.algorithm;

import java.util.Arrays;

import javax.naming.directory.SearchControls;

public class InsertSearch {
	public static void main(String[] args){
		if(args.length <3){
			System.out.println("请输入查找表及查找值");
			return;
		}
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
	
	static int search(int[] arrays,int key){
		int end =arrays.length-1;
		int start = 0;
		if(key==arrays[end]){
			return end;
		}
		else if(key==arrays[start]){
			return start;
		}
		while(end-start>1){
			int mid = Math.round(start + (key-arrays[start])/(arrays[end]-arrays[start])*(end-start));
			
			if(key>arrays[mid]){
				start=start == mid? ++start:mid; //key值靠近表头或者表尾，则mid可能与start相等
			}
			else if(key <arrays[mid]){
				end =end== mid?--end:mid;
			}
			else if(key ==arrays[mid]){
				return mid;
			}
		}
		return -1;
		
		
	}
}