package pl.edu.agh.kt;

import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.types.EthType;
import org.projectfloodlight.openflow.types.IpProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jnr.netdb.Protocol;
import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IFloodlightProviderService;
import net.floodlightcontroller.packet.ARP;
import net.floodlightcontroller.packet.BasePacket;
import net.floodlightcontroller.packet.Ethernet;
import net.floodlightcontroller.packet.ICMP;
import net.floodlightcontroller.packet.IPv4;
import net.floodlightcontroller.packet.TCP;
import net.floodlightcontroller.packet.UDP;

public class PacketExtractor {
	private static final Logger logger = LoggerFactory.getLogger(PacketExtractor.class);
	private FloodlightContext cntx;
	private OFMessage msg;
	protected IFloodlightProviderService floodlightProvider;
	private Ethernet eth;
	private IPv4 ipv4;
	private ARP arp;
	private TCP tcp;
	private UDP udp;
	
	public void PacketExtractor() {}
	
	public void packetExtract(FloodlightContext cntxt) {
		this.cntx = cntxt;
		extractEth();
	}
	
	
	private void showProtocolInfo() {
		if (ipv4.getProtocol() == IpProtocol.TCP) {
			tcp = (TCP)ipv4.getPayload();
			logger.info(toString(tcp));
		}
		if (ipv4.getProtocol() == IpProtocol.UDP) {
			udp = (UDP)ipv4.getPayload();
			logger.info(toString(udp));
		}
		if (ipv4.getProtocol() == IpProtocol.ICMP) {
			logger.info("ICMP info: " + (String.valueOf((ICMP)ipv4.getPayload())));
		}
	}
	
	private String toString(TCP tcp) {
		return "TCP info:" + tcp.getSourcePort() + " " + tcp.getDestinationPort();
	}
	
	private String toString(UDP tcp) {
		return "UDP info:" + tcp.getSourcePort() + " " + tcp.getDestinationPort();
	}
	
	public void extractEth() {
		eth = IFloodlightProviderService.bcStore.get(cntx, IFloodlightProviderService.CONTEXT_PI_PAYLOAD);
		logger.info("Frame: src mac {}", eth.getSourceMACAddress());
		logger.info("Frame: dst mac {}", eth.getDestinationMACAddress());
		logger.info("Frame: ether_type {}", eth.getEtherType());
		if (eth.getEtherType() == EthType.ARP) {
			arp = (ARP) eth.getPayload();
			extractArp();
		}
		if (eth.getEtherType() == EthType.IPv4) {
			ipv4 = (IPv4) eth.getPayload();
			extractIp();
			showProtocolInfo();
		}
	}
	
	private void extractArp() {
		logger.info(arp.getSenderProtocolAddress().toString());
	}

	private void extractIp() {
		logger.info(ipv4.getDestinationAddress().toString());
	}
	
}