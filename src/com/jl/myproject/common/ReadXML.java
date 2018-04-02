//自己用的简单读取xml方法，DOM高度只有1，为方便没有嵌套的情况,也不要记录key相同的标签
package com.jl.myproject.common;


  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
  

  
/** 
 * 用DOM方式读取xml文件 
 * @author lune 
 */  
public  class ReadXML {  
    private static DocumentBuilderFactory dbFactory = null;  
    private static DocumentBuilder db = null;  
    private static Document document = null;  

    static{  
    	
        try {  
            dbFactory = DocumentBuilderFactory.newInstance();  
            db = dbFactory.newDocumentBuilder();  
           
        } catch (ParserConfigurationException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /**
     * 
     * @param key 键名
     * @return
     * @throws Exception
     */
    public static String getValue(String key) throws Exception{  
        //将给定 URI 的内容解析为一个 XML 文档,并返回Document对象  
    	String path =ReadXML.class.getResource("").getPath();
        document = db.parse(path+"config.xml");  
        //按文档顺序返回包含在文档中且具有给定标记名称的所有 Element 的 NodeList  
        NodeList list = document.getElementsByTagName("DB");  
        
        //遍历
        for(int i=0;i<list.getLength();i++){  
            
            //获取第i个book结点  
            org.w3c.dom.Node node = list.item(i);  
            //获取属性  
            if(node.getNodeName()==key)
            	return node.getTextContent();
        }  
        return "0";
    }  
}  