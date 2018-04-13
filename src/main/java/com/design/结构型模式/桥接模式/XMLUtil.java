package com.design.结构型模式.桥接模式;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhubo
 * Date: 2018-04-12
 * Time: 9:20
 */
public class XMLUtil {

    //该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
    public static Object getBean(String args) {
        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            // 重要  解析 挡墙执行上下文根路径下的 config.xml文件
            String configPath = XMLUtil.class.getResource("/").getPath() + File.separator + "bridge/config.xml";
            doc = builder.parse(configPath);

            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("className");

            Node classNode=null;
            if(args.equals("image")) {
                //获取第一个包含类名的节点，即扩充抽象类
                classNode=nl.item(0).getFirstChild();

            }
            else if(args.equals("os")) {
                //获取第二个包含类名的节点，即具体实现类
                classNode=nl.item(1).getFirstChild();
            }
            String cName=classNode.getNodeValue();

            //通过类名生成实例对象并将其返回
            Class c=Class.forName(cName);
            Object obj=c.newInstance();
            return obj;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
