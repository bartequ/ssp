package pl.edu.agh.kt;

import java.util.HashMap;
import java.util.Map;

public class NodePortMap {
	public final static Map<String, Map<String, String>> getFullMap() {
		Map<String, Map<String, String>> portMapping = new HashMap<>();
		portMapping.put("s1", getS1Mapping());
		portMapping.put("s2", getS2Mapping());
		portMapping.put("s3", getS3Mapping());
		portMapping.put("s4", getS4Mapping());
		
		return portMapping;
	}
	
	public final static String getMapping(String sourceNode, String destNode) {
		return getFullMap().get(sourceNode).get(destNode);
	}
	
	private static Map<String, String> getS1Mapping() {
		Map<String, String> node = new HashMap<>();
		node.put("h1", "eth4");
		node.put("s2", "eth1");
		node.put("s3", "eth3");
		node.put("s4", "eth2");
		
		return node;
	}
	
	private static Map<String, String> getS2Mapping() {
		Map<String, String> node = new HashMap<>();
		node.put("s1", "eth1");
		node.put("s3", "eth2");
		
		return node;
	}
	
	private static Map<String, String> getS3Mapping() {
		Map<String, String> node = new HashMap<>();
		node.put("h2", "eth4");
		node.put("s1", "eth1");
		node.put("s2", "eth2");
		node.put("s3", "eth3");
		
		return node;
	}
	
	private static Map<String, String> getS4Mapping() {
		Map<String, String> node = new HashMap<>();
		node.put("s1", "eth1");
		node.put("s3", "eth2");
		
		return node;
	}
}
