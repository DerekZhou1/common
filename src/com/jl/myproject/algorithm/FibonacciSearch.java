//斐波那契查找,相当于将斐波那契数列的值作为查找表的中位置变换方式逻辑，
//例：end-start==f[k],mid=start+f[k-2]
//斐波那契查找的原理是根据斐波那契数列来确定下一个比较值的index ，{1,1,2,3,5,8,13,21，}
//跟中值比较后，如果大于这个值，就将起始点后移，start =mid+1;如果小于，
//结束点前移 end=mid-1，然后计算中值，进入下一次比较
package com.jl.myproject.algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.directory.SearchControls;

public class FibonacciSearch {
	public static void main(String[] args){
		List<Integer> arrays=new ArrayList(args.length);
		for(int i =0;i< args.length;i++){
			arrays.add(Integer.valueOf(args[i]));
		}
		int key = arrays.remove(arrays.size()-1);
		int[] Arrayfbi = new int[40];
		getFbi(Arrayfbi);
		int pointer = search(arrays,key,Arrayfbi);
		if(pointer==-1){
			System.out.println("没有该数据");
		}
		else{
			//System.out.println(String.valueOf(pointer) + " ：" + String.valueOf(args[pointer]));
			System.out.println(pointer+ " ：" + args[pointer]);
		}
	
	}
	
	static int search(List<Integer> arrays,int key,int[] FbiArray){
		int n=arrays.size()-1;
		int end =n;
		int start = 0;
		int k=FbiArray.length-1;
		while(end<FbiArray[k]){
			k--;
		}
		//padding  arrays， make sure the length of arrays equal F[k]
		for(int i=end+1;i<FbiArray[k+1] ; i++){
			arrays.add(arrays.get(end));
		}
		while(end-start>=0){
			if(k<0)k=0;
			int mid = start+FbiArray[k];
			int temp = arrays.get(mid);
			if(key>temp){
				start = mid+1;
				k-=2;
				
			}
			else if(key <temp){
				end = mid-1;
				k-=1;
			}
			else if(key ==temp){
				if(mid>n){
					return n;
				}
				return mid;
			}
		}
		return -1;
	}

	
	/**
	 * 获得斐波那契数列，从0开始
	 * @param array
	 */
	static void getFbi(int[] array){
		int n =array.length;
		for(int i=0;i<n;i++){
			if(i==0||i==1){
				array[i]=i;
			}
			else{
				array[i]=array[i-1]+array[i-2];
			}
		}
	}
}