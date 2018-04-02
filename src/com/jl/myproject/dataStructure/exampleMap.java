//exampleMap：演示map的用法，map和list是最常用的两种集合
package com.jl.myproject.dataStructure;

import java.util.*;


public class exampleMap {
	public static void main(String...args){
//		初始化
		Map<String,String> maps = new HashMap<String,String>();
//		插入元素
		maps.put("1", "10");
		maps.put("2", "10");
//		获取元素
		String a = maps.get("1");
		System.out.println(a);
		System.out.println(maps.size());
//		元素遍历
//		maps.values() 返回值集合
//		maps.keySet() 返回键集合
//		maps.keySet() 返回键值集合，使用getKey()，getValue()获取
		for(String key :maps.keySet()){
			System.out.println(key+":"+maps.get(key));
		}
//		移除元素
		maps.remove("1");
		System.out.println(maps.size());
//		清空
		maps.clear();
		System.out.println(maps.size());
	
	}
}
