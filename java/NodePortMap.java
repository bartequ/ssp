package pl.edu.agh.kt;

import java.util.HashMap;
import java.util.Map;

public class NodePortMap {
	public final static Map<String, Map<String, String>> getFullMap() {
		Map<String, Map<String, String>> portMapping = new HashMap<>();
		portMapping.put("S1", getS1Mapping());
		portMapping.put("S2", getS2Mapping());
		portMapping.put("S3", getS3Mapping());
		portMapping.put("S4", getS4Mapping());
		
		return portMapping;
	}
	
	public final static String getMapping(String sourceNode, String destNode) {
		return getFullMap().get(sourceNode).get(destNode);
	}
	
	private static Map<String, String> getS1Mapping() {
		Map<String, String> node = new HashMap<>();
		node.put("H1", "eth4");
		node.put("S2", "eth1");
		node.put("S3", "eth3");
		node.put("S4", "eth2");
		
		return node;
	}
	
	private static Map<String, String> getS2Mapping() {
		Map<String, String> node = new HashMap<>();
		node.put("S1", "eth1");
		node.put("S3", "eth2");
		
		return node;
	}
	
	private static Map<String, String> getS3Mapping() {
		Map<String, String> node = new HashMap<>();
		node.put("H2", "eth4");
		node.put("S1", "eth1");
		node.put("S2", "eth2");
		node.put("S3", "eth3");
		
		return node;
	}
	
	private static Map<String, String> getS4Mapping() {
		Map<String, String> node = new HashMap<>();
		node.put("S1", "eth1");
		node.put("S3", "eth2");
		
		return node;
	}
}
