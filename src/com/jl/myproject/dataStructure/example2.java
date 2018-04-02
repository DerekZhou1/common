//给出一棵二叉树，返回其节点值的层次遍历（逐层从左往右访问）
//用list<List<object>>保存
package com.jl.myproject.dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class example2 {
	public static void main(String[] args){

		//生成二叉树 {3,9,20,#,#,15,7}
		TreeNode tree33 = new TreeNode(15, null, null);
		TreeNode tree34 = new TreeNode(7, null, null);
		TreeNode tree22 = new TreeNode(20, tree33, tree34);
		TreeNode tree21 = new TreeNode(9, null, null);
		TreeNode tree1 = new TreeNode(3	, tree21, tree22);
		Solution solution = new Solution();
		List<List<Integer>> list = solution.levelOrder(tree1);
		for(List<Integer> a:list){
			StringBuffer str =new StringBuffer();
			for(Integer b:a){
				str=str.append(b).append(" ");
			}
			System.out.println(str);
		}
	}
}
/**
 * 二叉树节点类，用于生成一个二叉树
 * @author Administrator
 *
 */
class TreeNode{
	public int val;
	public TreeNode left,right;
	public TreeNode(int val,TreeNode left,TreeNode right){
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

/**
 * 层序遍历二叉树
 * @author Administrator
 *
 */
class Solution{


	List<List<Integer>>  list1 = new ArrayList<List<Integer>>();

	/*
	 * @param root: A Tree
	 * @return: Level order a list of lists of integer
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
		if(root ==null)
			return new ArrayList<List<Integer>>();
		else{
			search(root,1);
			return list1;
		}
	}

	/**
	 * 层序遍历二叉树
	 * @param root 要遍历的节点
	 * @param degree 当前节点的度数，用于构造返回list的长度，根节点度数设置为1
	 */
	void search(TreeNode root,int degree){
		if(list1.size()<degree){
			list1.add(degree-1,new ArrayList<Integer> ());
		}
	
		list1.get(degree-1).add(root.val);
		if(root.left!=null){
			search(root.left,degree+1);
		}
		if(root.right!=null){
			search(root.right,degree+1);
		}
	}
	/**
	 * 将最终结果排序进行翻转
	 */
	void converse(){
		int n = (int)Math.floor(list1.size()/2);
		List<Integer> temp = new ArrayList<Integer>();
		for(int i =0 ; i< n ; i++){
			temp =list1.get(i);
			list1.set(i, list1.get(list1.size()-1-i))  ;
			list1.set(list1.size()-1-i,temp) ;
			
		}
	}
}
