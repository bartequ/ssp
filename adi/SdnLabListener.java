package pl.edu.agh.kt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFPacketIn;
import org.projectfloodlight.openflow.protocol.OFType;
import org.projectfloodlight.openflow.types.OFPort;

import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;
import net.floodlightcontroller.topology.ITopologyService;
import net.floodlightcontroller.core.IFloodlightProviderService;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SdnLabListener implements IFloodlightModule, IOFMessageListener {

	protected IFloodlightProviderService floodlightProvider;
	protected ITopologyService topologyService;
	protected Graph<String, DefaultEdge> graph;
	protected static Logger logger;
	private String finalNode;

	@Override
	public String getName() {
		return SdnLabListener.class.getSimpleName();
	}

	@Override
	public boolean isCallbackOrderingPrereq(OFType type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCallbackOrderingPostreq(OFType type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public net.floodlightcontroller.core.IListener.Command receive(IOFSwitch sw, OFMessage msg,
			FloodlightContext cntx) {

		logger.info("************* NEW PACKET IN *************");
		PacketExtractor extractor = new PacketExtractor();
		extractor.packetExtract(cntx);

		String startSwName = getSwName(sw);
		if (!finalNode.equals(startSwName) && !finalNode.equals(startSwName)) {
			List<String> graphPath = GraphUtils.calculatePath(graph, startSwName, finalNode);
			if (graphPath.size() > 1) {
				for (int i = 0; i < graphPath.size()-1; i++) {
					if (!graphPath.get(i).equals(finalNode)) {
						OFPort outPort = NodePortMap.getMapping(graphPath.get(i), graphPath.get(i+1));
						logger.debug("From switch" + graphPath.get(i) + "send on port " + outPort);
						OFPacketIn pin = (OFPacketIn) msg;
						String swTmp = graphPath.get(i).toString();
						sw.setAttribute("datapathId", swTmp.substring(swTmp.length()-1));
						Flows.simpleAdd(sw, pin, cntx, outPort);
					}
				}
			}
		}

		// TODO

		return Command.STOP;
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		Collection<Class<? extends IFloodlightService>> l = new ArrayList<Class<? extends IFloodlightService>>();
		l.add(IFloodlightProviderService.class);
		return l;
	}

	@Override
	public void init(FloodlightModuleContext context) throws FloodlightModuleException {
		floodlightProvider = context.getServiceImpl(IFloodlightProviderService.class);
		topologyService = context.getServiceImpl(ITopologyService.class);
		logger = LoggerFactory.getLogger(SdnLabListener.class);
	}

	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {
		floodlightProvider.addOFMessageListener(OFType.PACKET_IN, this);
		logger.info("******************* START **************************");
		graph = NetworkGraphSingleton.getInstance();
		graph = Topology.getGrapgh();
		topologyService.addListener(new SdnLabTopologyListener());
		logger.debug("###################################");
		finalNode = "H2";
	}

	private String getSwName(IOFSwitch sw) {
		return sw.getPort(OFPort.of(1)).getName().split("-")[0].toUpperCase();

	}

	private void addFlows(List<String> path) {
	}
}
