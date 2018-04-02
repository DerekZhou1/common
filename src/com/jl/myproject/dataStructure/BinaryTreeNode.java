//BinaryTreeNode :  树节点,用于构造树，具有树的增删查改;BinaryTreeNode是二叉排序树
//AVLTreeNode平衡二叉树
package com.jl.myproject.dataStructure;

import java.util.ArrayList;
import java.util.List;

import com.jl.myproject.algorithm.PrintTree;

import javafx.scene.control.cell.TreeItemPropertyValueFactory;


public class BinaryTreeNode  {

	//int degree =0;
	public int val=0;

	public BinaryTreeNode left,right,parent;		
	public BinaryTreeNode(){}

	public BinaryTreeNode(int val){
		this.val =val;
	}
	
	public BinaryTreeNode(int val,BinaryTreeNode parent){
		this.val = val;
		this.parent = parent;
	}

	public BinaryTreeNode(int val,BinaryTreeNode left,BinaryTreeNode right){
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
	

	/**
	 * @param node 要添加的节点的根节点
	 * @param val 要添加的节点值
	 * @return 返回添加完成后的树
	 */
	public   BinaryTreeNode addNode(int val,BinaryTreeNode node){
		if(node.val==0){	//是一个空树
			node.val = val;
			return node;
		}
		BinaryTreeNode temp = searchNode(val, node) ;
			if(val==temp.val){
				System.out.println("要插入的数据已存在");
			}
			else if(val>temp.val){			
					temp.right =new BinaryTreeNode(val,temp);								
			}
			else if(val<temp.val){
					temp.left =new BinaryTreeNode(val,temp);
			}
		return node;
	}

	/**
	 * 
	 * @param val 要查找的节点的权值
	 * @param node 要查找的树的根节点
	 * @return 返回查找到的节点，如果查找到就返回查找到的节点，
	 * 如果没有就返回查找的最后一个节点，如果要添加这个节点，那么返回的就是这个要添加节点的父节点；
	 */
	public static BinaryTreeNode searchNode( int val,BinaryTreeNode node){
		BinaryTreeNode temp =node;
		while(1==1){
			if(val==temp.val ){
				return temp;
			}
			else if(val>temp.val){
				if(temp.right!=null){
					temp = temp.right;
				}
				else{
					return temp;
				}
			}
			else if(val<temp.val){
				if(temp.left!=null){
					temp = temp.left;
				}
				else{
					return temp;
				}
			}
		}
	}

	/**
	 * 删除一个节点，有三种情况
	 * 1 节点为子叶，直接删除
	 * 2节点只有一个孩子，则删除节点后将节点的孩子作为父节点的孩子
	 * 3节点有两个孩子，删除节点后将该节点中序排序的前驱或者后驱替代当前节点的位置
	 * @param node 要删除的节点
	 * 因为在删除前已进行查找，所以删除必然成功，无需返回值
	 * 
	 * bug：无法删除根节点，未修正，正确方法参照AVLTreeNode类
	 */
	public boolean delete(int val,BinaryTreeNode node1){
		BinaryTreeNode node = searchNode(val ,node1);
		if(node.val!=val){
			return false;
		}
		boolean isLeft;
		if(node.parent.left!=null&&node.parent.left.val==node.val){
			isLeft = true;
		}
		else{
			isLeft= false;
		}

		if(node.left==null&&node.right==null){
			if(node.parent==null){ //表示是根节点
				node.val=0;
			}
			else if(!isLeft){   //不是根节点，查看是当前节点是左还是右孩子
				node.parent.right =null;
			}
			else {  
				node.parent.left =null;
			}
		}
		else if(node.left!=null&&node.right!=null){ //有左右孩子，将中序后继作为本节点
			BinaryTreeNode successor = getSuccessor(node);
			// 有两种情况，如果根节点和后继节点是父子关系，则后继节点为右孩子
			// 如果不是父子关系，则后继节点因为是中序必然是左孩子
			if(node.right.val==successor.val){  //后继是右孩子
				if(successor.right!=null){
					successor.parent.right = successor.right;
					successor.right.parent = successor.parent;
				}
				else{
					successor.parent.right =null;
				}
			}
			else{		//后继是左孩子
				if(successor.right!=null){
					successor.parent.left = successor.right;
					successor.right.parent = successor.parent;
				}
				else{
					successor.parent.left =null;
				}
			}
			node.val = successor.val;
		}
		else{
			if(!isLeft){
				if(node.right!=null){
					node.parent.right = node.right;
					node.right.parent = node.parent;
				}
				else{
					node.parent.right = node.left;
					node.left.parent = node.parent;
				}
			}
			else{
				if(node.right!=null){
					node.parent.left =node.right;
					node.right.parent =node.parent;
				}
				else{
					node.parent.left =node.left;
					node.left.parent = node.parent;
				}
			}
		}
		return true;

	}

	/**
	 * 查找当前节点的后继节点，就是中序遍历的后继节点，先访问右孩子，如果右孩子有左孩子，就访问左孩子
	 * 一直到最后一个左孩子
	 * @param node 当前节点
	 * @return  找到的后继节点，没有则返回null;
	 */
	public BinaryTreeNode getSuccessor(BinaryTreeNode node){
		//TreeNode tempNode = Object
		BinaryTreeNode temp =node;
		if(temp.right==null){
			return null;
		}
		else if(temp.right.left==null&&temp.right.right==null){
			return temp.right;
		}
		else{
			temp =node.right;
		}
		while(temp.left!=null){
			temp =temp.left;
		}
		return temp;
	}

	public static void main(String[] args){
		List<List<Integer>>  list1 = new ArrayList<List<Integer>>();
		int[] input ={7 ,3 ,11 ,1 ,5, 9, 15, 4 ,6 ,8, 10 ,12 , 14 ,16};
		if(input.length==0){
			System.out.println("请输入参数");
		}
		List<Integer>  arrays=new ArrayList<Integer>(input.length);
		for(int i =0;i< input.length;i++){
			arrays.add(Integer.valueOf(input[i]));
		}
		BinaryTreeNode tree = new BinaryTreeNode(arrays.get(0));
		for(int i=1; i <arrays.size();i++){
			tree.addNode(arrays.get(i),tree);
		}
		PrintTree.print(tree, 6);
		//TreeNode subtree=tree.searchNode(11);
		boolean hasDelete=tree.delete(16,tree);
		System.out.println(hasDelete);
		PrintTree.print(tree, 6);
	}
}



