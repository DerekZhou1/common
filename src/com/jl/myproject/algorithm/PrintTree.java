package com.jl.myproject.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jl.myproject.dataStructure.AVLTreeNode;
import com.jl.myproject.dataStructure.BinaryTreeNode;

public  class PrintTree {
	 static int treedegree,treelength;
	//List<List<Integer>> list= new ArrayList<List<Integer>>();
	static int[][] myArrays;
	static BinaryTreeNode myTree;
	/**
	 * 
	 * @param list
	 * @param degree 打印的树的前导空格数量由degree决定，所以需要传入一个大概的degree值
	 * 假设树节点的每一个值不超过3个字符
	 */
	public    PrintTree(){
	
	}
	
	public static void print(BinaryTreeNode tree,int degree){
		treedegree =degree;
		treelength = (int)Math.pow(2, degree);
		myArrays =new  int[degree][treelength];
		myTree =tree;
		for(int i =0 ; i< myArrays.length;i++){
			Arrays.fill(myArrays[i],0);
			}
			printNode(tree,0,(int)Math.ceil(treelength/2));
			for(int m=0;m<myArrays.length;m++){
				System.out.println("");
				for(int n=0;n<myArrays[0].length;n++){
					if(myArrays[m][n]==0){
						System.out.print(" ");
					}
					else{
						System.out.print(myArrays[m][n]);
					}
				}
			}
	}
	
	/**
	 * 打印二叉树
	 * @param root
	 * @param degree
	 */
	static void printNode(BinaryTreeNode root,int row,int col){
	
		if(row<treedegree){
			myArrays[row][col] =root.val;
		}		
		if(root.left!=null){
			printNode(root.left,row+1,(int)(col-treelength/Math.pow(2,row+2 )));
		}
		if(root.right!=null){
			printNode(root.right,row+1,(int)(col+treelength/Math.pow(2,row+2 )));
		}
	}
	
	public static void print(AVLTreeNode tree,int degree){
		treedegree =degree;
		treelength = (int)Math.pow(2, degree);
		myArrays =new  int[degree][treelength];
		myTree =tree;
		for(int i =0 ; i< myArrays.length;i++){
			Arrays.fill(myArrays[i],-1);
			}
			printNode(tree,0,(int)Math.ceil(treelength/2));
			for(int m=0;m<myArrays.length;m++){
				System.out.println("");
				for(int n=0;n<myArrays[0].length;n++){
					if(myArrays[m][n]==-1){
						System.out.print(" ");
					}
					else{
						System.out.print(myArrays[m][n]);
					}
				}
			}
	}
	
	/**
	 * 打印二叉树
	 * @param root
	 * @param degree
	 */
	static void printNode(AVLTreeNode root,int row,int col){
	
		if(row<treedegree){
			myArrays[row][col] =root.val;
		}		
		if(root.left!=null){
			printNode(root.left,row+1,(int)(col-treelength/Math.pow(2,row+2 )));
		}
		if(root.right!=null){
			printNode(root.right,row+1,(int)(col+treelength/Math.pow(2,row+2 )));
		}
	}
}
