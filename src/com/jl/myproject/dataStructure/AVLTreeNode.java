package com.jl.myproject.dataStructure;

import java.util.ArrayList;
import java.util.List;

import com.jl.myproject.algorithm.PrintTree;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

/**构造一个平衡二叉树，初始化二叉树节点,可进行初始化，并增删查改
//核心：当树的结构发生变化导致不平衡时，找最小不平衡子树，并对不平衡子树进行旋转
* 所以主要有两个重点，一个是平衡因子的计算方法，一个是树的旋转规则
* 平衡因子概念：一个节点的左子树的度数减去右子树的度数
* 1.平衡因子的修改首先判断当前插入的是左还是右，然后获得父节点的bf值，如果父节点的高度不变，则
* 父节点上面的节点平衡因子不用修改，如果父节点高度改变，如果不平衡（2，-2），此节点就是最小不平衡树，如果平衡
* 则上向递归，循环这个查询对比操作
* 2. 旋转规则：如果平衡因子为2，则右旋，如果平衡因子为-2，则左旋。
* 如果最小不平衡树的根节点与它的子节点符号一致（0不算），则正常旋转，如果符号
* 不一致，则先将子节点为根节点反向旋转，再正向旋转（原因：因为旋转之后其实是子节点作为根节点，
* 如果符号是一正一负，则说明旋转后的左右孩子要么都大于自己，要么都小于自己，不符合二叉树的规则）
* 
* 
* 我用的旋转方法和标准方法不同，区别在于双旋时的处理方法。比如先子节点左旋，然后父节点右旋
* 标准方法有四个，左旋，右旋，子左旋，子右旋，左旋和子左旋是不同的执行方式，这样最多执行两个方法会自动结束
* 我的方式只有一个旋转方法，用一个enum表示左右，当父子节点的符号不一致时，继续调用旋转方法，
* 所以有可能执行多次（大于2次）旋转方法
* @author Administrator
*
*/
public class AVLTreeNode extends BinaryTreeNode {
	
	public  int bf;
	
	public AVLTreeNode left,right,parent;
	public nodeType  nodetype;//因为平衡二叉树进行调整时要根据当前是左还是右孩子进行不同调整
	//所以加上这个字段以后代码更加简洁方便(但是代码量会变多，特别是旋转时，很多节点的nodetype需要改)，不加也可以完成功能
	public AVLTreeNode() {
		// TODO Auto-generated constructor stub
	
	}
	{bf = 0;}   //构造块
	public AVLTreeNode(int val){
		this.val = val;
		
	}
	
	public AVLTreeNode(int val,AVLTreeNode parent,nodeType type){
		this.val = val;
		this.parent = parent;
		this.nodetype = type;
	}
	
	public AVLTreeNode(int val,AVLTreeNode left,AVLTreeNode right){
		this.val = val;
		this.left = left;
		this.right = right;
	}
	

	/**
	 * @param val 要插入节点的权值
	 * @param node 树的根节点
	 * @return 返回已经完成插入操作的树根节点
	 */

	public    AVLTreeNode addNode(int val,AVLTreeNode node){
		if(node.val==0){	//是一个空树
			node.val = val;
			node.nodetype = nodetype.Root;
			return node;
		}
		AVLTreeNode temp =searchNode(val,node) ;
		if(val==temp.val){
			System.out.println("要插入的数据已存在");
		}
		if(val>temp.val){
				temp.right =new AVLTreeNode(val,temp,nodetype.Right);	
				temp = temp.right;
				changeBL(temp,true);
		}
		if(val<temp.val){
				temp.left =new AVLTreeNode(val,temp,nodetype.Left);	
				temp = temp.left;
				changeBL(temp,true);
		}
		return node;
	}

	/**
	 * 删除一个节点，有三种情况
	 * 1 节点为子叶，直接删除 
	 * 2节点只有一个孩子，则删除节点后将节点的孩子作为父节点的孩子
	 * 3节点有两个孩子，删除节点后将该节点中序排序的前驱或者后驱替代当前节点的位置
	 * @param val 要删除的节点的值
	 * @param node1 要删除的节点的树的根节点
	 * 因为在删除前已进行查找，所以删除必然成功，无需返回值
	 * 
	 * ##因为目前设计的是双链表关系，删除只删除父到子的指针，不删除子到父的指针，
	 * 因为删除后要改上层的bl，所有留一个子到父的指针方便往上回溯修改bl
	 *
	 */
	public static boolean  delete(int val,AVLTreeNode node1){
		AVLTreeNode node = searchNode(val,node1);
		if(node.val!=val){ //没有查找到
			return false;
		}
		if(node.left==null&&node.right==null){ 	//表示是子叶
			if(node.nodetype==nodeType.Left){   //不是根节点
				node.parent.left =null;
			}
			else if(node.nodetype==nodeType.Right){  
				node.parent.right =null;
			}
			else if(node.nodetype==nodeType.Root){
				node.val =0;
			}
			changeBL(node, false);
		}
		else if(node.left!=null&&node.right!=null){ //有左右孩子，将中序后继作为本节点
			AVLTreeNode successor = getSuccessor(node);
			int tempval =successor.val;
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
			changeBL(successor, false);
			node.val = tempval;
		}
		else{		//只有一个孩子
			boolean hasLeftchild;
			if(node.left !=null){
				hasLeftchild =true;
			}
			else{
				hasLeftchild =false;
			}
			if(node.nodetype == nodeType.Right){
				if(!hasLeftchild){
					node.parent.right = node.right;
					node.right.parent = node.parent;
				}
				else{
					node.parent.right = node.left;
					node.left.parent = node.parent;
				}
				node.parent.right.nodetype = nodeType.Right;
				changeBL(node.parent.right, false);
			}
			else if(node.nodetype == nodeType.Left){
				if(hasLeftchild){
					node.parent.left =node.left;
					node.left.parent = node.parent;
					
				}
				else{
					node.parent.left =node.right;
					node.right.parent = node.parent;
				}
				node.parent.left.nodetype = nodeType.Left;
				changeBL(node.parent.left, false);
			}
			else if(node.nodetype == nodeType.Root){
				if(hasLeftchild){
					node.left.parent =null;
					node.left.nodetype=nodeType.Root;
					node = node.left;
				}
				else{
					node.right.parent =null;
					node.right.nodetype=nodeType.Root;
					node =node.right;
				}
				
			}
		}
		return true;

	}

	public static void rotate(AVLTreeNode node,direction dir  ){
		if(dir==direction.Right){
			//如果当前节点与子节点的符号不一致，并且子节点有孩子，则将
			//子节点为根再进行旋转，子节点如果没有孩子则没有必要旋转
			if(node.left.bf<0&&
					(node.left.right!=null||node.left.left!=null)){
				rotate(node.left,direction.Left);
			}
			AVLTreeNode tempNode =null;
			if(node.left.right!=null){
				 tempNode =node.left.right;
			}
			if(node.nodetype ==nodeType.Left){
				node.parent.left =node.left;
				node.left.parent = node.parent;
				node.parent.left.nodetype = nodeType.Left;
			}
			else if(node.nodetype ==nodeType.Right){
				node.parent.right =node.left;
				node.left.parent = node.parent;
				node.parent.right.nodetype = nodeType.Right;
			}
			else if(node.nodetype ==nodeType.Root){
				node.left.parent = null;
				node.left.nodetype = nodeType.Root;
			}
			node.parent =node.left;
			node.parent.right = node;
			node.nodetype =nodeType.Right;
			//如果根节点左孩子有右孩子，则将它变成根节点的左孩子
			//// A,B,C,D,E中如果E存在，在C必然存在，不然A的bl就会是3，
			//同理，如果E不存在，则C必然不存在，如果存在a的bl就是1，就不会进入旋转方法
			if(tempNode!=null){
				node.left =tempNode;
				tempNode.parent=node;
				tempNode.nodetype = nodeType.Left;
			}
			else{
				node.left =null;
			}
			//旋转后修改平衡因子
			node.bf=0;
			node.parent.bf =0;		
		}
		else{			//左旋      如果当前节点与子节点的符号不一致，并且子节点有孩子，则将
			//子节点为根再进行旋转，子节点如果没有孩子则没有必要旋转
			if(node.right.bf>0&&		
					(node.right.right!=null||node.right.left!=null)){ 		
				rotate(node.right,direction.Right);
			}
			AVLTreeNode tempNode =null;
			if(node.right.left!=null){
				 tempNode =node.right.left;
			}
			if(node.nodetype ==nodeType.Left){
				node.parent.left =node.right;
				node.right.parent = node.parent;
				node.parent.right.nodetype = nodeType.Right;
			}
			else if(node.nodetype ==nodeType.Right){
				node.parent.right =node.right;
				node.right.parent = node.parent;
				node.parent.right.nodetype = nodeType.Right;
			}
			else if(node.nodetype ==nodeType.Root){
				node.right.parent = null;
				node.right.nodetype = nodeType.Root;
			}
			node.parent =node.right;
			node.parent.left = node;
			node.nodetype =nodeType.Left;
			//如果根节点左孩子有右孩子，则将它变成根节点的左孩子
			//// A,B,C,D,E中如果E存在，在C必然存在，不然A的bl就会是3，
			//同理，如果E不存在，则C必然不存在，如果存在a的bl就是1，就不会进入旋转方法
			if(tempNode!=null){
				node.right =tempNode;
				tempNode.parent=node;
				tempNode.nodetype = nodeType.Right;
			}
			else{
				node.right =null;
			}
			//旋转后修改平衡因子
			node.bf=0;
			node.parent.bf =0;	
		}
	}
	
	/**该方法用于修改增加或者删除一个树节点之后的平衡因子，修改方法为向上递归，
	 * 如果是新增节点则当前节点的度肯定是增加，如果是删除节点，当前节点的度肯定是减小
	 * @param node 当前节点，新增或者删除的节点
	 * @param opertion 判断当前要操作的节点的高度是增加还是减少，true表示高度增加，false表示高度减少
	 * ##因为要知道当前节点是左还是右，需要与父节点进行val的比较，所以传进来的节点必须要要有父节点，即使是
	 * 作删除操作的节点
	 * //修改平衡因子
		方法思路：
	 */
	protected static void changeBL(AVLTreeNode temp,boolean degere){
		if(temp.nodetype!=nodeType.Root){
			AVLTreeNode node= temp.parent;
			if(degere){  //高度增加
				switch(node.bf){
				case -1:{
					if(temp.nodetype==nodeType.Left){
						node.bf =0; //树的高度不变，所以只修改当前节点的平衡因子，不用修改父节点

					}
					else if(temp.nodetype==nodeType.Right){
						node.bf =-2;
						rotate(node,direction.Left);
					}
					break;
				}
				case 0:{
					if(temp.nodetype==nodeType.Left){
						node.bf =1;
						changeBL(node,true);
					}
					else if(temp.nodetype==nodeType.Right){
						node.bf =-1;
						changeBL(node,true);
					}
					break;
				}
				case 1: {
					if(temp.nodetype==nodeType.Left){
						node.bf =2;
						rotate(node,direction.Right);
					}
					else if(temp.nodetype==nodeType.Right){
						node.bf=0;   //树的高度不变
 
					}
					break;
				}
				}
			}
			else{ //高度降低
				switch(node.bf){
				case -1:{
					if(temp.nodetype==nodeType.Left){
						node.bf =-2;
						rotate(node,direction.Left);
					}
					else if(temp.nodetype==nodeType.Right){
						node.bf =0;
						changeBL(node, false);

					}
					break;
				}
				case 0:{
					if(temp.nodetype==nodeType.Left){
						node.bf =-1;
					}
					else if(temp.nodetype==nodeType.Right){
						node.bf =1;
					}
					break;
				}
				case 1: {
					if(temp.nodetype==nodeType.Left){
						node.bf =0;
						changeBL(node, false);
					}
					else if(temp.nodetype==nodeType.Right){
						node.bf=2;
						rotate(node,direction.Right);
					}
					break;
				}
				}
			}
		}
	}

	

	/**
	 * 
	 * @param val 要查找的节点的权值
	 * @param node 要查找的树的根节点
	 * @return 返回查找到的节点，如果查找到就返回查找到的节点，
	 * 如果没有就返回查找的最后一个节点，如果要添加这个节点，那么返回的就是这个要添加节点的父节点；
	 * 
	 */

	public static  AVLTreeNode searchNode(int val,AVLTreeNode node){
		AVLTreeNode temp =node;
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
	 * 查找当前节点的后继节点，就是中序遍历的后继节点，先访问右孩子，如果右孩子有左孩子，就访问左孩子
	 * 一直到最后一个左孩子
	 * @param node 当前节点
	 * @return  找到的后继节点，没有则返回null;
	 */
	public static AVLTreeNode getSuccessor(AVLTreeNode node){
		AVLTreeNode temp =node;
		if(temp.right==null){
			return null;
		}
		else if(temp.right.left==null&&temp.right.right==null){
			return temp.right;
		}
		else{
			temp =temp.right;
		}
		while(temp.left!=null){
			temp =temp.left;
		}
		return temp;
	}

	public static void main(String[] args){
		List<List<Integer>>  list1 = new ArrayList<List<Integer>>();
//		int[] input=new int[50];
//		for(int i =0;i<50;i++){
//			input[i]=i;
//		}
		//这里定义一个预估的高度，用于打印时估算字符间的宽度
		int degree = 6;
		int[] input ={3,2,1,4,5,6,7,10,9,8};
		if(input.length==0){
			System.out.println("请输入参数");
		}
		List<Integer>  arrays=new ArrayList<Integer>(input.length);
		for(int i =0;i< input.length;i++){
			if(input[i]==0){
				System.out.println("输入参数不能为0,0代表空树");
			}
			else{
				arrays.add(Integer.valueOf(input[i]));
			}
		}
		AVLTreeNode tree = new AVLTreeNode(0);//权值等于0表示是个空树
		for(int i=0; i <arrays.size();i++){
			System.out.println("增加新节点" +arrays.get(i));
			tree.addNode(arrays.get(i),tree);
			while(tree.parent!=null){//进过变换后树指针可能不再指向根节点，需要重新定位
				tree = tree.parent;
			}
			PrintTree.print(tree, degree);
		}
		int deleteNode = 1;
		if(AVLTreeNode.delete(deleteNode, tree)){
			System.out.println("删除节点" +deleteNode);
		}
		PrintTree.print(tree, degree);
		
//		//TreeNode subtree=tree.searchNode(11);
//		boolean hasDelete=tree.delete(16);
//		System.out.println(hasDelete);
//		PrintTree.print(tree, 6);
	}
}

/**left表示左旋，right表示右旋
 * @author zl
 *
 */
enum direction{
	Left,Right;  
}

/**
 *  一个节点和父节点的关系有三种状态：
 * 1 是左孩子
 * 2是右孩子
 * 3既不是左也不是右，是根节点
 * @author zl
 *
 */
enum nodeType{
	Left,Right,Root;  
}
