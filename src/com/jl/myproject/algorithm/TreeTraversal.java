//层序遍历二叉树,对于没有值的节点设置为0，方便观察树的结构
package com.jl.myproject.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.jl.myproject.dataStructure.BinaryTreeNode;

public class TreeTraversal{


	List<List<Integer>>  list1 = new ArrayList<List<Integer>>();

	/*
	 * @param root: A Tree
	 * @return: Level order a list of lists of integer
	 */
	public List<List<Integer>> levelOrder(BinaryTreeNode root) {
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
	void search(BinaryTreeNode root,int degree){
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
}