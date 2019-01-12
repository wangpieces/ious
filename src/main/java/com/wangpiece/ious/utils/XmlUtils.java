package com.wangpiece.ious.utils;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-14 0:36
 */
public class XmlUtils {


    /**
     * 将map转换为微信请求的xml格式
     * @return
     */
    public static String formatMapToXml(Map<String,String> paramMap) {
        StringBuilder result = new StringBuilder("<xml>");
        for(Map.Entry<String, String> map : paramMap.entrySet()) {
            String key = map.getKey();
            String value = map.getValue();
            result = result.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
        }
        result = result.append("</xml>");
        return result.toString();
    }

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param xmlStr
     * @return
     */
    public static  Map<String, String> formatXmlToMap(String xmlStr) {

        if(null == xmlStr || "".equals(xmlStr)) {
            return null;
        }
		/*=============  !!!!注意，修复了微信官方反馈的漏洞，更新于2018-10-16  ===========*/
        InputStream stream = null;
        try {
            Map<String, String> data = new HashMap<String, String>();
            // TODO 在这里更换
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            documentBuilderFactory.setXIncludeAware(false);
            documentBuilderFactory.setExpandEntityReferences(false);

            stream = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilderFactory.newDocumentBuilder().parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }

            return data;
        } catch (Exception ex) {

        }finally {
            try {
                if(stream != null) {
                    stream.close();
                }
            } catch (Exception ex) {

            }
        }
        return null;
    }


}
