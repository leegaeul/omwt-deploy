/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰

 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : SDPResponse.java
 * DESCRIPTION    : SDP API
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-26      init
 *****************************************************************************/

package com.olleh.webtoon.api.sdp;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SDPUtil {
	
	protected static Log logger = LogFactory.getLog(SDPResponse.class);

	protected static Map<String, Object> parseXML(String body) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
		db.setNamespaceAware(true);
		
		try {
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(body));
			
			DocumentBuilder parser = db.newDocumentBuilder();
			Document doc = parser.parse(is);
			
			NodeList bodyEL = doc.getElementsByTagNameNS("http://schemas.xmlsoap.org/soap/envelope/", "Body");
			map = parseToMap(bodyEL.item(0).getFirstChild());
			
		} catch(Exception e) {
			logger.error(e);
		}

		return map;
	}
	
	protected static Map<String, Object> parseToMap(Node node) {
		Map<String, Object> map = new HashMap<String, Object>();

		for(int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node childNode = node.getChildNodes().item( i );
			String childNodeName = childNode.getNodeName().replace("sdp:", "").replace("_", "").toUpperCase();

			if(childNode.hasChildNodes() && childNode.getChildNodes().item(0).getNodeType() != Node.TEXT_NODE) {
				map.put(childNodeName, parseToMap(childNode));
				
			} else {
				map.put(childNodeName, childNode.getTextContent());
			}
		}
		
		return map;
	}

	protected static Map<String, Object> parseJSON(String body) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		map = gson.fromJson(body, Map.class);
		
		return map;
	}
}
