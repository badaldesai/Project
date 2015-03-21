package edu.virginia.pmc8p.sca.plugins;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import edu.virginia.pmc8p.sca.analysis.Flow;

import jpcap.JpcapCaptor;
import jpcap.JpcapWriter;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;

class PacketWriter implements PacketReceiver {
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	private FileWriter writer;
	
	private ArrayList<Packet> buffer = new ArrayList<Packet>();
	
	boolean printToLog = false;
		
	File whiteList;
	File filter;
	
	public PacketWriter(File output, boolean printToLog, boolean headersOnly, File whiteList, File filter) {
		super();
		this.printToLog = printToLog;
		this.whiteList = whiteList;
		this.filter = filter;
		try {
			writer = new FileWriter(output);
		} catch (IOException e) {
			LOGGER.info("Error creating FileWriter...");
			LOGGER.info(e.getMessage());
		}
	}
	
	
	@Override
	public void receivePacket(Packet arg0) {
		if(printToLog)
		{
			LOGGER.info("Packet intercepted!!");
		}
		buffer.add(buffer.size(),arg0);
	}


	public void flush() {
		LOGGER.info("Flushing");
		try {
			Flow flow = new Flow(buffer, filter, whiteList);
			writer.write(flow.toString());
			
		} catch (IOException e) {
			LOGGER.info("Problem writing packet");
			LOGGER.info(e.getMessage());
		}
		
		buffer.clear();
		
	}
	
	public void clear(){
		
		buffer.clear();
		
	}

	public void setFileOuput(File file)
	{
		try {
			writer.close();
		} catch (IOException e1) {
			LOGGER.info("Error closing previous file");
			LOGGER.info(e1.getMessage());
		}
		try {
			writer = new FileWriter(file);
		} catch (IOException e) {
			LOGGER.info("Error opening new file");
			LOGGER.info(e.getMessage());
		}
	}

	void close() {
		try {
			writer.close();
		} catch (IOException e) {
			LOGGER.info("Error closing FileWriter");
			LOGGER.info(e.getMessage());
		}
	}
	}
