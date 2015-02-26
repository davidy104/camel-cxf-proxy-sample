package nz.co.ws.proxy.integration.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class RawDocProcessBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(RawDocProcessBean.class);

	@SuppressWarnings("restriction")
	public Document process(Document doc) {
		LOGGER.info("RawDocProcessBean process start..");
		LOGGER.info("doc:{} ", doc);
		Node node = doc.getElementsByTagName("role").item(0);
		// String incident = node.getTextContent();
		node.setTextContent("sales");
		return doc;
	}
}
