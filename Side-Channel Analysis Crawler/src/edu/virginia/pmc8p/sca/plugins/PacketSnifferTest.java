package edu.virginia.pmc8p.sca.plugins;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.crawljax.browser.EmbeddedBrowser;
import com.crawljax.condition.invariant.Invariant;
import com.crawljax.core.CandidateElement;
import com.crawljax.core.CrawlSession;
import com.crawljax.core.CrawljaxException;
import com.crawljax.core.configuration.ProxyConfiguration;
import com.crawljax.core.plugin.OnInvariantViolationPlugin;
import com.crawljax.core.plugin.OnNewStatePlugin;
import com.crawljax.core.plugin.OnRevisitStatePlugin;
import com.crawljax.core.plugin.PreCrawlingPlugin;
import com.crawljax.core.plugin.PreStateCrawlingPlugin;
import com.crawljax.core.plugin.ProxyServerPlugin;
import com.crawljax.core.state.StateVertix;

import jpcap.NetworkInterface;
import jpcap.JpcapCaptor;
import jpcap.PacketReceiver;

import jpcap.NetworkInterfaceAddress;

import jpcap.JpcapWriter;


public class PacketSnifferTest implements PreCrawlingPlugin {
	private static final Logger LOGGER = Logger.getLogger(PacketSnifferTest.class);

	@Override
	public void preCrawling(EmbeddedBrowser arg0) {
		//Obtain the list of network interfaces
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();

		//for each network interface
		for (int i = 0; i < devices.length; i++) {
		  //print out its name and description
		  LOGGER.info(i+": "+devices[i].name + "(" + devices[i].description+")");

		  //print out its datalink name and description
		  LOGGER.info(" datalink: "+devices[i].datalink_name + "(" + devices[i].datalink_description+")");

		  //print out its MAC address
		  LOGGER.info(" MAC address:");
		  for (byte b : devices[i].mac_address)
			  LOGGER.info(Integer.toHexString(b&0xff) + ":");
		  LOGGER.info("");

		  //print out its IP address, subnet mask and broadcast address
		  for (NetworkInterfaceAddress a : devices[i].addresses)
			  LOGGER.info(" address:"+a.address + " " + a.subnet + " "+ a.broadcast);
		}

		int index= 1;  // set index of the interface that you want to open.

		//Open an interface with openDevice(NetworkInterface intrface, int snaplen, boolean promics, int to_ms)
		try {
			JpcapCaptor captor=JpcapCaptor.openDevice(devices[index], 65535, false, 20);
			
			JpcapWriter writer=JpcapWriter.openDumpFile(captor,"C:\\Users\\Peter2\\Documents\\Eclipse Workspace\\SCA Project\\output\\packets.txt");
			//captor.processPacket(-1,(PacketReceiver) new PacketWriter(writer));
		} catch (IOException e) {
			LOGGER.error("Could not initialize packet sniffer...");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	


	
	




	
}