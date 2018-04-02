//AbstractFactoryMode 抽象工厂模式
//实例 数据访问可以在不同数据库之间进行切换
//有两个数据库，oracle 和sqlserver，有多张表 ：user表和department表,对不同的表有不同的操作
//有oracle和sqlserver两个系列，然后每个系列下有相同的产品，都是user和department，
//不同产品之间的功能不同，user是insert和delete，department是search和update
package com.jl.myproject.desginPattern;

import com.jl.myproject.common.ReadXML;

/**只用切换database实例化时的方法，就可以完成从一个数据库到另一个数据库的无缝切换
 * @author Administrator
 *
 */
public class AbstractFactoryMode {
	public static void main(String...args){
		//IFactory1 database = new Oracle(); 
		
		IFactory1 database;
		try {
			String db =ReadXML.getValue("DB");
			database = (IFactory1)Class.forName(db).newInstance();
			IUser user = database.getUser();
			user.delete();
			user.insert();
			IDepartment depart = database.getDepart();
			depart.update();
			depart.search();
		} catch (Exception   e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
}


/**定义工厂类系列的产品
 * @author Administrator
 *
 */
interface IFactory1{
	  IUser getUser();
	  IDepartment getDepart();
}

class Oracle implements IFactory1{

	@Override
	public IUser getUser() {
		IUser user =new OralceUser();
		return user;
	}

	@Override
	public IDepartment getDepart() {
		IDepartment dpt =new OracleDepart();
		return dpt;
	}
}

class SqlServer implements IFactory1{

	@Override
	public IUser getUser() {
		IUser user =new SqlserverUser();
		return user;
	}

	@Override
	public IDepartment getDepart() {
		IDepartment dpt =new SqlserverDepart();
		return dpt;
	}
}


interface IUser  {
	 void insert();
	 void delete();
}


class OralceUser  implements IUser{

	@Override
	public void insert() {
		System.out.println("Oralce:insert");
	}

	@Override
	public void delete() {
		System.out.println("Oralce:delete");
	}
	
}

class SqlserverUser  implements IUser{

	@Override
	public void insert() {
		System.out.println("Sqlserver:insert");
	}

	@Override
	public void delete() {
		System.out.println("Sqlserver:delete");
	}
}

interface IDepartment {
	 void search();
	 void update();
}

class SqlserverDepart  implements IDepartment{

	@Override
	public void search() {
		System.out.println("Sqlserver:search");
	}

	@Override
	public void update() {
		System.out.println("Sqlserver:update");
	}
}

class OracleDepart  implements IDepartment{

	@Override
	public void search() {
		System.out.println("Oracle:search");
	}

	@Override
	public void update() {
		System.out.println("Oracle:update");
	}
}
