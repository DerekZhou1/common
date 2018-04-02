//CommandPattern 命令模式，将一个操作封装为一个对象，从而可以对不同的请求对客户进行参数化。
//对请求排队或记录请求日志，以及支持撤销
//核心：命令模式的目的是保存请求（操作）的记录和顺序
//实例：服务员调用命令，但不直接调用执行者,并且将所有命令接收后一次性调用
package com.jl.myproject.desginPattern;

import java.util.ArrayList;
import java.util.List;

public class CommandPattern {
	public static void main(String...args){
		Operator oper = new Operator();
		OneCommand com1 = new OneCommand(oper);
		TwoCommand com2 = new TwoCommand(oper);
		ThreeCommand com3 = new ThreeCommand(oper);
		Caller cal = new Caller();
		cal.addCommand(com1);
		cal.addCommand(com2);
		cal.addCommand(com3);
		cal.execute();
		cal.remove(com3);
		cal.execute();
	}
}
/**操作者，A调用B时，B是操作者
 * @author Administrator
 *
 */
class Operator{
	public void doOne(){
		System.out.println("1");
	}
	
	public void doTwo(){
		System.out.println("2");
	}
	
	public void doThree(){
		System.out.println("3");
	}
}

/**抽象操作，A调用B的方法1,2,3，command就是所有方法的抽象
 * @author Administrator
 *
 */
abstract class Command{
	Operator oper =new Operator();
	public  Command(Operator oper){
		this.oper = oper;
	}
	
	abstract public void doSomething();
}

class OneCommand extends Command{

	public OneCommand(Operator oper) {
		super(oper);
	}

	@Override
	public void doSomething() {
		oper.doOne();
	}
}

class TwoCommand extends Command{
	
	public TwoCommand(Operator oper) {
		super(oper);
	}
	@Override
	public void doSomething() {
		oper.doTwo();
	}
}

class ThreeCommand extends Command{

	public ThreeCommand(Operator oper) {
		super(oper);
	}
	@Override
	public void doSomething() {
		oper.doThree();
	}
}

/**执行者，A调用B时，A是执行者
 * @author Administrator
 *
 */
class Caller{
	List<Command> commands = new ArrayList<Command>();
	public void addCommand(Command com){
		commands.add(com);
	}
	
	public void remove(Command com){
		commands.remove(com);
	}
	
	public void execute(){
		for(Command com :commands){
			com.doSomething();
		}
	}
}
