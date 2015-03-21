package edu.virginia.pmc8p.sca.plugins;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import jpcap.JpcapCaptor;
import jpcap.packet.Packet;

import org.apache.log4j.Logger;

import com.crawljax.core.CrawlSession;
import com.crawljax.core.plugin.PostCrawlingPlugin;


public class PacketReader implements PostCrawlingPlugin{
	private static final Logger LOGGER = Logger.getLogger(PacketReader.class);
	String input;
	boolean writeToConsole = true;
	String output = null;
	
	public PacketReader(String input) {
		super();
		this.input = input;
	}
	@Override
	public void postCrawling(CrawlSession arg0) {
		//open a file to read saved packets
		
		FileWriter writer = null;
		if(output!=null)
		{
		try {
			 
			 writer = new FileWriter(new File(output));
		} catch (FileNotFoundException e) {
			LOGGER.error("File for packet header output not found");
			LOGGER.error(e.getStackTrace());
		} catch (IOException e) {
			LOGGER.error("IO error for packet header output");
			
		}
		}
		
		JpcapCaptor captor = null;
		try {
			captor = JpcapCaptor.openFile(input);
			while(true){
				//LOGGER.info("Reading packet...");
				  //read a packet from the opened file
				  Packet packet=captor.getPacket();
				  //if some error occurred or EOF has reached, break the loop
				  if(packet==null || packet==Packet.EOF) break;
				  //otherwise, print out the packet
				  if(writeToConsole)
				  LOGGER.info(packet);
				  if(output!=null)
				  {
					  writer.write(packet.toString());
					  //writer.write(new String(packet.data.clone()));
					  writer.write('\n');
				  }
				}
		} catch (IOException e) {
			LOGGER.error("Could not open file to read packets");
			LOGGER.error(e.getStackTrace());
		} finally
		{
			if(captor!=null)
			captor.close();
			if(writer!=null)
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					LOGGER.error("Could not close file to write packet headers");
					LOGGER.error(e.getStackTrace());
				}
		}
		
	}
	public PacketReader(String input, boolean writeToConsole, String output) {
		super();
		this.input = input;
		this.writeToConsole = writeToConsole;
		this.output = output;
	}

}
