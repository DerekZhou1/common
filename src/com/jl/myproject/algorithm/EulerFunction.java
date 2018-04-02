package com.jl.myproject.algorithm;
//1不是质数也不是合数
//线性筛选和普通筛选有一个很重要的特征：普通筛选开始循环时子循环次数多，越到后面子循环次数越少，
//因为开始是循环2,2的倍数最多，查到数字最多；线性筛选是根据已查到的质数数组进行循环，
//所以越到后面循环次越多

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EulerFunction {
	public static void main(String...args){
		//设置欧拉函数的n值
		int  n = 10000;
		List<Integer> array = new ArrayList<Integer>();
		while(n>0){
		array.add(0);
			n--;
		}
//		array=getNumber_normal(array);
		array=getNumber_linear(array);
		int count =0;
		for (int i =1;i<array.size();i++) {
			if(array.get(i)==0){
				count++;
				System.out.println(i+1+"   ");
			}
		}
		System.out.println();
		System.out.println("count:"+count);
	}
	
	/**linear sieve 
	 * 一个质数和一个合数相乘，必定能分解为一个更小的质数和一个更大的合数；
	 * 利用这个特性，我们对已查询到的质数数组由小到大进行检索，假设目前循环到i，
	 * i%prime[j]==0就表示i是合数，并且prime[j]就是i的最小质因数。假设此时i=prime[j]*k,
	 * 如果继续往后循环，n=i*prime[n]=prime[j]*(k*prime[n])   ,n>k,此时n的最小质因数
	 * 仍然是prime[j],所以当i%prime[j]==0时就没有必要往后面循环，直接break,这样就筛选掉了对一个值重复
	 * 设置的可能。每个值只被设置一次。
	 * 
	 * 网上方法都是搜索1~n，我这里只搜索1~n/2，循环次数更少！
	 * 因为默认都是质数，只有合数才设置，在 n/2~n范围之间的所有的合数都已经
	 * 被搜索过了，我们的最小质数是2，而n如果是合数，那么它的最小质因数肯定是在（2~n/2）之间。
	 * 所以1~n/2之间就一定能搜索到它的质因数，也就能够标记它。这个很好证明。
	 * 
	 * @param array
	 * @return
	 */
	static List<Integer>  getNumber_linear(List<Integer> array){
		int length = (int)Math.ceil(array.size()/2);
		List<Integer> prime= new ArrayList<Integer>();
		int times =0;
		for(int i=2;i<length;i++){
			if(array.get(i-1)==0){
				prime.add(i);
			}
			for(int j=0;j<prime.size();j++){
				if(i*prime.get(j)>array.size()){
					break;
				}
				array.set(i*prime.get(j)-1, 1);
				times++;
				if(i%prime.get(j)==0){
					break;
				}	
			}
		}
		System.out.println("times:"+times);
		return array;
	}
	
	/**nomarl mehtod o(n)=nlognlogn
	 * when we check the number,if it is a prime ,we set the value which index of 2*prime,3*prime ...
	 * to 1,means it's a composite
	 * @param array  
	 * @return
	 */
	static List<Integer> getNumber_normal(List<Integer> array){
		int times=0;
		int count = (int)Math.ceil(array.size()/2);
		for(int i = 2;i<=count;i++){
			if(array.get(i-1)==0){
				for(int k=2*i;k<=array.size();k+=i){
//					if(k*k>array.size())
//						break;
					array.set(k-1, 1);
					times++;
				}
			}
		}
		System.out.println("Cycle times " + times);
		return array;
	}
}
