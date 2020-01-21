package pl.edu.agh.kt;

import java.util.HashMap;
import java.util.Map;
import org.projectfloodlight.openflow.types.OFPort;


public class NodePortMap {
	public final static Map<String, Map<String, OFPort>> getFullMap() {
		Map<String, Map<String, OFPort>> portMapping = new HashMap<>();
		portMapping.put("S1", getS1Mapping());
		portMapping.put("S2", getS2Mapping());
		portMapping.put("S3", getS3Mapping());
		portMapping.put("S4", getS4Mapping());
		return portMapping;
	}
	
	public final static OFPort getMapping(String sourceNode, String destNode) {
		return getFullMap().get(sourceNode).get(destNode);
	}
	
	private static Map<String, OFPort> getS1Mapping() {
		Map<String, OFPort> node = new HashMap<>();
		node.put("H1", OFPort.of(0));
		node.put("S2", OFPort.of(1));
		node.put("S3", OFPort.of(3));
		node.put("S4", OFPort.of(2));
		return node;
	}
	
	private static Map<String, OFPort> getS2Mapping() {
		Map<String, OFPort> node = new HashMap<>();
		node.put("S1", OFPort.of(1));
		node.put("S3", OFPort.of(2));
		node.put("S4", OFPort.of(3));
		return node;
	}
	
	private static Map<String, OFPort> getS3Mapping() {
		Map<String, OFPort> node = new HashMap<>();
		node.put("H2", OFPort.of(4));
		node.put("S1", OFPort.of(1));
		node.put("S2", OFPort.of(2));
		node.put("S4", OFPort.of(3));
		return node;
	}
	
	private static Map<String, OFPort> getS4Mapping() {
		Map<String, OFPort> node = new HashMap<>();
		node.put("S1", OFPort.of(1));
		node.put("S2", OFPort.of(2));
		node.put("S3", OFPort.of(3));
		return node;
	}
}
