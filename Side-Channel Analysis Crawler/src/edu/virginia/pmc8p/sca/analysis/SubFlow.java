package edu.virginia.pmc8p.sca.analysis;
import java.util.ArrayList;

import jpcap.packet.Packet;


public class SubFlow {


	public SubFlow(String source, String dest, int len) {
		super();
		this.source = source;
		this.dest = dest;
		this.len = len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	ArrayList<Packet> packets = new ArrayList<Packet>();
	
	String source;
	String dest;
	String port=null;
	int len = 0;
	
	@Override
	public String toString() {
		if(port!=null){
			return source + " -> " + dest + " " + len + " bytes" + " on port " + port;
		}
		return source + " -> " + dest + " " + len + " bytes " + packets.get(0).sec+"."+packets.get(0).usec;
	}

	public SubFlow(String source, String dest, ArrayList<Packet> input)
	{
		this.source = source;
		this.dest = dest;
		//this.len = len;
		
		for(Packet packet : input)
		{
			packets.add(packet);
			len+=packet.len;
		}
	}

	public SubFlow(String source2, String dest2, ArrayList<Packet> input,
			String port) {
		this.source = source2;
		this.dest = dest2;
		this.port = port;
		//this.len = len;
		
		for(Packet packet : input)
		{
			packets.add(packet);
			len+=packet.len;
		}
	}

	public ArrayList<Packet> getPackets() {
		return packets;
	}

	public String getSource() {
		return source;
	}

	public String getDest() {
		return dest;
	}

	public int getLen() {
		return len;
	}
	
}
