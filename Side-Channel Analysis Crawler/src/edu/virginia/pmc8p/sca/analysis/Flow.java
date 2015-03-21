package edu.virginia.pmc8p.sca.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;


import edu.virginia.pmc8p.sca.plugins.PacketReader;
import jpcap.packet.Packet;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;
public class Flow {
	private static final Logger LOGGER = Logger.getLogger(Flow.class);
	private ArrayList<SubFlow> subFlows = new ArrayList<SubFlow>();
	private String filter = "";
	private String whitelist = null;
	File file;
	File fileWhite;
	public Flow(ArrayList<Packet> packets, File filterInput, File whitelist){
		//LOGGER.info("Creating flow...");
		//LOGGER.info("Number of packets: " + packets.size());

		if(packets.size()==0)
			return;

		file = filterInput;
		fileWhite = whitelist;

		String source = null;
		String dest = null;
		//String port = null;
		ArrayList<Packet> workingSet = new ArrayList<Packet>();
		if(!isFiltered(packets.get(0)))
		{
			//LOGGER.info("First packet: " + packets.get(0).toString());
			source = extractSource(packets.get(0));
			//LOGGER.info("Source: " + source);
			dest = extractDest(packets.get(0));
			//LOGGER.info("Destination: " + dest);
			//port = extractPort(packets.get(0));

			workingSet.add(packets.get(0));
		}
		for(int x = 1; x < packets.size(); x++)
		{
			if(source == null)
			{
				//LOGGER.info("First packet was filtered");
				if(!isFiltered(packets.get(x)))
				{
					source = extractSource(packets.get(x));

					dest = extractDest(packets.get(x));
				//	port = extractPort(packets.get(x));
					workingSet.add(packets.get(x));
					//LOGGER.info("Working packet: " + packets.get(x).toString());
				}
			}else{
				//LOGGER.info("Looking at packet: " + packets.get(x));
				if(source.equals(extractSource(packets.get(x))) && dest.equals(extractDest(packets.get(x))))
				{
					if(!isFiltered(packets.get(x)))
					{
						//LOGGER.info("Adding packet to working set");
						workingSet.add(packets.get(x));
					}
				}
				else
				{
					if(!isFiltered(packets.get(x)))
					{
						//LOGGER.info("Creating new subflow...");
						subFlows.add(new SubFlow(source, dest, workingSet));
						//workingSet.clear();
						workingSet = new ArrayList<Packet>();
						workingSet.add(packets.get(x));		
						source = extractSource(packets.get(x));
				//		port = extractPort(packets.get(x));
						dest = extractDest(packets.get(x));
					}
				}
			}
		}

		if(workingSet.size()>0)
		{
			subFlows.add(new SubFlow(source, dest, workingSet));
		}

		//removeDuplicates();
	}




	private String extractPort(Packet packet) {
		if(packet instanceof TCPPacket)
		{
			return ((TCPPacket)packet).dst_port+"";
		}
		return "";
	}




	private void removeDuplicates() {
		//Remove TCP packets with duplicate sequence numbers, in particular remove the first one

		Map<Long,Packet> packets = new HashMap<Long,Packet>();
		ArrayList<SubFlow> emptySubFlows = new ArrayList<SubFlow>();

		//For each created subflow
		for(SubFlow s: subFlows)
		{
			//Find the duplicates
			ArrayList<Packet> toRemove = new ArrayList<Packet>();
			for(Packet p : s.getPackets())
			{
				if(p instanceof TCPPacket)
				{
					TCPPacket currentPacket = (TCPPacket)p;
					if(packets.containsKey(currentPacket.sequence))
					{
						toRemove.add(p);
						packets.remove(currentPacket.sequence);
						packets.put(currentPacket.sequence, p);
					}
				}
			}
			//Remove the duplicates from subflow
			ArrayList<Packet> packetsFromFlow = s.getPackets();
			for(Packet p: toRemove)
			{
				packetsFromFlow.remove(p);
			}

			//Mark empty subflows
			if(packetsFromFlow.size()==0)
				emptySubFlows.add(s);
		}

		//Remove empty subflows
		for(SubFlow s: emptySubFlows)
			subFlows.remove(s);

	}




	private boolean isFiltered(Packet packet) {
		//Don't even consider the small packets
		//LOGGER.info("Filtering packets...");
		//if(packet.len < 70)
		//{
			//	LOGGER.info("Packet too small");
		//	return true;
		//}

		if(filter.equals(""))
		{
			try {

				Scanner scanner = new Scanner(file);
				while(scanner.hasNext())
				{
					filter += scanner.next() + "\n";
				}
			} catch (FileNotFoundException e) {
				LOGGER.info("Filter file not found");
				return false;
			}catch(NullPointerException e){
				LOGGER.info("Error handling File object...");
				return false;
			}
		}


			if(whitelist==null)
			{
				LOGGER.info("Creating whitelist");
				whitelist = "";
				try {
					Scanner scanner = new Scanner(fileWhite);
					while(scanner.hasNext())
					{
						whitelist += scanner.next() + "\n";
					}
					if(whitelist.contains("*"))
					{
						LOGGER.info("Whitelist will be ignored");
						whitelist="";
					}
				} catch (FileNotFoundException e) {
					LOGGER.info("Filter file not found");
				}
			}


			if(whitelist!=null && !whitelist.isEmpty())
			{
				//LOGGER.info("Current packet: " + packet.toString());
				Scanner scanner = new Scanner(whitelist);
				while(scanner.hasNext())
				{
					String temp = scanner.next().trim();
					//	LOGGER.info("Does packet header contain: " + temp);
					if(packet.toString().contains(temp))
						return false;
				}
				return true;
			}

			Scanner scanner = new Scanner(filter);
			while(scanner.hasNext())
			{
				//LOGGER.info("Checking filter");
				String temp = scanner.next();
				if(packet.toString().contains(temp))
				{
					//LOGGER.info("Packet contains following from filter: " + temp);
					return true;
				}
			}

			//LOGGER.info("Packet good");


			return false;
		}

		private String extractDest(Packet packet) {
			if(packet instanceof IPPacket)
			{
				return ((IPPacket)packet).dst_ip.getHostAddress();
			}
			return "";
		}

		private String extractSource(Packet packet) {
			try{if(packet instanceof IPPacket)
			{
				return ((IPPacket)packet).src_ip.getHostAddress();
			}}catch(Exception e){
				return "";
			}
			return "";
		}

		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			LOGGER.info("Number of subflows: " + subFlows.size());
			for(SubFlow subFlow : subFlows)
			{
				stringBuilder.append(subFlow.toString() + "\n");
			}
			return stringBuilder.toString();
		}



	}
