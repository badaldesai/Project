package edu.virginia.pmc8p.sca;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;

import com.crawljax.core.CrawljaxController;
import com.crawljax.core.CrawljaxException;
import com.crawljax.core.configuration.CrawljaxConfiguration;

import edu.virginia.pmc8p.sca.plugins.CacheManager;
import edu.virginia.pmc8p.sca.plugins.MirrorGenerator;
import edu.virginia.pmc8p.sca.plugins.PacketSniffer;
import edu.virginia.pmc8p.sca.plugins.TimeStamper;

public class DataCollector {
	
	public void runTestWithoutNetwork(CrawljaxConfiguration configuration,File outputDirectory, File filter, File whiteList,int deviceIndex) throws ConfigurationException, CrawljaxException{
		File td = new File(outputDirectory.getAbsolutePath()+File.separator+"mirror");
		td.mkdirs();
		File mirror = new File(outputDirectory.getAbsolutePath()+ File.separator +"mirror"+ File.separator );
		mirror.mkdirs();
		configuration.addPlugin(new MirrorGenerator(mirror));
		File packets = new File(outputDirectory.getAbsolutePath()+ File.separator +"packets"+ File.separator );
		packets.mkdirs();
		
		CacheManager cacheManager = new CacheManager(System.getProperty("java.io.tmpdir"));
		System.out.println("TEMP DIRECTORY: "+System.getProperty("java.io.tmpdir"));
		configuration.addPlugin(cacheManager);		
		
		CrawljaxController crawljax = new CrawljaxController(configuration);
		crawljax.run();		

	}
	
	public void runTest(CrawljaxConfiguration configuration,File outputDirectory, File filter, File whiteList,int deviceIndex) throws ConfigurationException, CrawljaxException{
		File td = new File(outputDirectory.getAbsolutePath()+File.separator+"mirror");
		td.mkdirs();
		File mirror = new File(outputDirectory.getAbsolutePath()+ File.separator +"mirror"+ File.separator );
		mirror.mkdirs();
		configuration.addPlugin(new MirrorGenerator(mirror));
		TimeStamper timeStamper = new TimeStamper();
		File packets = new File(outputDirectory.getAbsolutePath()+ File.separator +"packets"+ File.separator );
		packets.mkdirs();
		PacketSniffer packetSniffer = new PacketSniffer(deviceIndex, timeStamper, packets,filter,whiteList);
		configuration.addPlugin(packetSniffer);
		CacheManager cacheManager = new CacheManager(System.getProperty("java.io.tmpdir"));
		System.out.println("TEMP DIRECTORY: "+System.getProperty("java.io.tmpdir"));
		configuration.addPlugin(cacheManager);
		//configuration.
		//configuration.addPlugin(new FormCrawler());
		
		CrawljaxController crawljax = new CrawljaxController(configuration);
		crawljax.run();		

	}
	
}
