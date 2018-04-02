//使用KMP算法对字符串进行匹配，找出主串中所有与模式串一样的子串
//构造next函数，找到模式串在每个字符处的相同的最长真前缀和最长真后缀
//前缀的概念：，如字符串 ababc 首先，不考虑空字符，所有的前缀有a, ab, aba, abab, ababc，其中真前缀有a, ab, aba, abab
//next函数：获得部分匹配表，"部分匹配值"就是"前缀"和"后缀"的最长的共有元素的长度。以"ABCDABD"为例，
//　　－　"A"的前缀和后缀都为空集，共有元素的长度为0；
//　　－　"AB"的前缀为[A]，后缀为[B]，共有元素的长度为0；
//　　－　"ABC"的前缀为[A, AB]，后缀为[BC, C]，共有元素的长度0；
//　　－　"ABCD"的前缀为[A, AB, ABC]，后缀为[BCD, CD, D]，共有元素的长度为0；
//　　－　"ABCDA"的前缀为[A, AB, ABC, ABCD]，后缀为[BCDA, CDA, DA, A]，共有元素为"A"，长度为1；
//next数组为{0，0，0，0，0,1},next[j]表示从0~j-1字符串中的最长值
// 核心：nexts数组的构造逻辑  已知p[0]~p[k] 和p[j-k]~p[j],如果p[k+1]==p[j+1]
//则next[j+1] =k+1；如果p[k+1]！=p[j+1],则k=next[k],也就是当p[j+1]与p[k+1]匹配失败时，将指针(p[k+1])回退到
//原先已匹配成功的子串的的最长真前缀的后面一个字符，再用这个字符与原先失败的字符进行匹配
//也就是p[next[k]]和p[j+1]


package com.jl.myproject.dataStructure;

import java.util.ArrayList;
import java.util.Random;

import com.sun.corba.se.impl.orbutil.graph.Graph;

public class KMP {
	public static void main(String[] args){
		String mainstr = getString(15);
		String modstr = getString(3);
		ArrayList<Integer> nextstr = next(modstr);//
		//	ArrayList<Integer> nextstr = next("abcdabcd");//000001230
		ArrayList<Boolean> answer  = getIndex(mainstr, modstr, nextstr);
		System.out.println(""+mainstr);
		System.out.println(""+modstr);
		System.out.println(""+nextstr);
		System.out.println(""+answer);
	}

	//生存为小写字母的随机字符串,长度为N
	static String getString(int n){
		StringBuffer str = new StringBuffer();
		while(n-- >0){
			long number = Math.round(Math.random())+97;
			str = str.append((char)number);
		}
		return str.toString();
	}

	//例 模式串abcadabg，中的a[0]=-1,a[3]=0,a[4]=1,a[7]=2
	//自己写的方法，采用嵌套循环，效率低
	static ArrayList<Integer> next1(String modstr){

		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i = 0 ; i<= modstr.length(); i++){
			array.add(i, 0)	;
		}
		for(int i =1 ;i < modstr.length();i++){
			for(int j =0 ;j+i <modstr.length();j++){
				if(modstr.charAt(j)==modstr.charAt(i+j)){
					if(j+1 > array.get(i+j+1)){
						array.set(i+j+1, j+1);;
					}
				}
				else{
					break;
				}
			}
		}
		return array;
		//		
	}

	//网上标准方法，采用递归，效率高
	/**
	 * @param modstr 模式串
	 * @return
	 */
	static ArrayList<Integer> next(String modstr){

		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i = 0 ; i<= modstr.length(); i++){
			array.add(i, 0)	;
		}
		int i =0; 
		int j =1;
	
		while(j<array.size()-1){
			if(modstr.charAt(i)==modstr.charAt(j)){	
				////考虑主串aaacdf,模式串aaaab，优化匹配方式由[0,0,1,2,3,0]变为[0,0,0,0,0,0]
				//但是这样做有个弊端，对于主串aaaaa，模式串aaaa只能找到一个匹配，不利于多个查找,如果要查找多个匹配，
				//一个字符串匹配成功后，接下来要重头开始匹配
				if(modstr.charAt(j)==modstr.charAt(j-1)){
					array.set(++j, array.get(++i));
				}
				else{
					array.set(++j, ++i);	
				}
			}
			else if(i==0){
				j++;
			}
			else{
				i = array.get(i);
			}
		}
		return array;
		//		
	}
	//返回匹配的字符串地址，用bool数组表示，匹配为true
	//**与next1方法匹配，与优化后的next方法不匹配
	static ArrayList<Boolean> getIndex(String mainstr,
			String modstr,ArrayList<Integer> arrays ){

		ArrayList<Boolean> indexs = new ArrayList<Boolean>();
		for(int i =0; i< mainstr.length(); i++){
			indexs.add(false);
		}
		int n =0;
		int m =0;
		do{
			if(mainstr.charAt(n)==modstr.charAt(m)){
				if(m==modstr.length()-1){
					indexs.set(n-modstr.length()+1, true);
					n=n-m+1;
					//m =arrays.get(m+1);
					m=0;
				
				}
				else{
					m++;
					n++;
				}
			}
			else{		
				if(m==0){
					n++;
				}
				else{
					m = arrays.get(m);
				}
			}
		}while(n<mainstr.length()&&m<modstr.length());
		return indexs;
	}
}
