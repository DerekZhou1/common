//Association 关联举例，在uml结构图中，横向关系可细化为关联，组合，聚合，依赖等。各项举例
//实例中列举了各种关系的具体代码示例
// 关系强度依次为依赖，关联，聚合，组合（dependency， association，aggregation，composition）
package com.jl.myproject.desginPattern;

import java.util.ArrayList;
import java.util.List;

public class Association {

}

//依赖  一个类A当执行某些操作时需要另一个类B，这个B类作为参数，返回值或者局部变量存在于A类的方法中，
//就是依赖关系
//比如动物新城代谢需要氧气
class Animal{
	public void breath(Oxygen ox){
		
	}
	//或者
	public Oxygen breath1(){
		return new Oxygen();
	}
}

class Oxygen{
	
}
//关联：当一个类知道另一个类时，用关联关系，比如企鹅需要知道气候
class Penguin{
	private Climate climate;
}

class Climate{
	
}

//聚合：一种弱拥有关系。A对象可以包含B对象，但B对象不是A的一部分，可以理解为没有B对象A也能存在，
//比如大雁和雁群
class	WideGooseAggregate{
	private List<WideGoose> geese =new ArrayList<WideGoose>();
}
class WideGoose{
	
}

//组合: 一种强的拥有关系，严格体现了整体与局部的关系，共享生命周期
//比如鸟和翅膀的关系
class Bird{
	Wing wing ;
	public Bird() {
		wing =new Wing();
	}
}
class Wing{
	
}
