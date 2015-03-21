//Peter Chapman
//pchapman@cs.virginia.edu
package edu.virginia.pmc8p.sca.plugins;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

import org.apache.log4j.Logger;

import com.crawljax.browser.EmbeddedBrowser;
import com.crawljax.core.CandidateElement;
import com.crawljax.core.CrawlSession;
import com.crawljax.core.plugin.OnCloneStatePlugin;
import com.crawljax.core.plugin.OnNewStatePlugin;
import com.crawljax.core.plugin.OnRevisitStatePlugin;
import com.crawljax.core.plugin.OnUrlLoadPlugin;
import com.crawljax.core.plugin.PostCrawlingPlugin;
import com.crawljax.core.plugin.PreCrawlingPlugin;
import com.crawljax.core.plugin.PreStateCrawlingPlugin;
import com.crawljax.core.state.StateVertix;

/**
* The PacketSniffer class is a plugin for the Crawljax architecture that
* intercepts and logs network transfers between state transitions of the
* generated finite-state machine. This code requires Jpcap to be installed.
*
* @author Peter Chapman (pchapman@cs.virginia.edu)
*
*/
public class PacketSniffer implements PreCrawlingPlugin, PostCrawlingPlugin,
          PreStateCrawlingPlugin, OnUrlLoadPlugin, OnNewStatePlugin,
          OnCloneStatePlugin, OnRevisitStatePlugin {

     /**
     * Instance of log4j.
     */
     private static final Logger LOGGER = Logger.getLogger(PacketSniffer.class);

     private JpcapCaptor captor;

    
     NetworkInterface[] devices;

     /**
     * The index of the NetworkInterface array holding the network device to be monitored.
     */
     private final int deviceIndex;

     File outputDirectory;
     /**
     * The filter {@link File}. This file contains a list of IP addresses that will not be included in the output. Useful for removing code from analytic services and ads
     */
     File filter;
    
     /**
     * If the whiteList {@link File} is non-empty, the filter file is ignored and only addresses in the white list file are used written to the output.
     */
     File whiteList;

     /**
     * The previous {@link StateVertix} of the Crawljax state machine. This is used to easily monitor transitions. A null previous state is indicative of the index.
     */
     private StateVertix previousState = null;

     /**
     * Used to track previously visited URLs. Necessary in order to ensure that the previous state can be visited through an URLLoad command.
     */
     HashMap<String, StateVertix> storedURLS = new HashMap<String, StateVertix>();

     /**
     * Currently unused. Could be used to add timestamps to logged network transfers.
     */
     TimeStamper timeStamper;

     /**
     * Jpcap object used for retrieving monitored network traffic.
     */
     private PacketWriter writer;

     /**
     * Simple constructor.
     * @param networkDeviceIndex
     */
     public PacketSniffer(int networkDeviceIndex) {
          super();
          deviceIndex = networkDeviceIndex;
     }

     /**
     * Complex constructor that utilizes all the features of the PacketSniffer.
     * @param networkDeviceIndex The network device to be monitored.
     * @param timeStamper This passed parameter is not used for anything meaningful, but a non-null object needs to be passed in to prevent a {@link NullPointerException}.
     * @param out The folder to which the output will be written.
     * @param filter The file with the filter data.
     * @param whiteList The file with the white list, if non-empty the filter file is ignored.
     */
     public PacketSniffer(int networkDeviceIndex, TimeStamper timeStamper,
               File out, File filter, File whiteList) {
          super();
          this.filter = filter;
          this.whiteList = whiteList;
          deviceIndex = networkDeviceIndex;
          outputDirectory = out;
          this.timeStamper = timeStamper;
     }

     @Override
     public void onCloneState(CrawlSession arg0, StateVertix arg1) {
          //Called whenever a previously seen state is reached.
          LOGGER.info("Clone state");
          captor.updateStat();
          LOGGER.info("Dropped Packets: " + captor.dropped_packets);
          LOGGER.info("Time : " + System.currentTimeMillis());
          captor.processPacket(-1, writer); //Retrieve the captured packets
          LOGGER.info("Current State: " + arg0.getCurrentState().getName());
          LOGGER.info("Previous state: " + previousState.getName());         
         
          if(!new File(outputDirectory.getAbsolutePath() + File.separator
                    + previousState.getName() + "-"
                    + arg0.getCurrentState().getName() + ".txt").exists()){
               writer.setFileOuput(new File(outputDirectory.getAbsolutePath() + File.separator
                         + previousState.getName() + "-"
                         + arg0.getCurrentState().getName() + ".txt"));
               writer.flush();}
          else
               writer.clear();
         
          previousState = arg0.getCurrentState();
          if (!storedURLS.containsKey(previousState.getUrl())) {
               storedURLS.put(previousState.getUrl(), previousState);
          }
          
          writer.close();

     }

     @Override
     public void onNewState(CrawlSession arg0) {
          // writer.flush();
          LOGGER.info("New State");
          captor.updateStat();
          LOGGER.info("Dropped Packets: " + captor.dropped_packets);
          LOGGER.info("Time : " + System.currentTimeMillis());
          captor.processPacket(-1, writer);
          LOGGER.info("Current State: " + arg0.getCurrentState().getName());
          if (previousState != null) {
               LOGGER.info("Previous state: " + previousState.getName());
          }
          if (!arg0.getCurrentState().getName()
                    .equals(arg0.getInitialState().getName())) {
               writer.setFileOuput(new File(outputDirectory.getAbsolutePath() + File.separator
                         + previousState.getName() + "-"
                         + arg0.getCurrentState().getName() + ".txt"));
          } else {
               writer.setFileOuput(new File(outputDirectory.getAbsolutePath()
                         + File.separator +"index.txt"));
          }
          writer.flush();
          previousState = arg0.getCurrentState();

          if (!storedURLS.containsKey(previousState.getUrl())) {
               storedURLS.put(previousState.getUrl(), previousState);
          }
          writer.close();

     }

     @Override
     public void onRevisitState(CrawlSession arg0, StateVertix arg1) {
          LOGGER.info("Revisted state...throwing out packets");
          captor.updateStat();
          LOGGER.info("Dropped Packets: " + captor.dropped_packets);
          LOGGER.info("Time : " + System.currentTimeMillis());
          captor.processPacket(-1, writer);
         
          previousState = arg1;
          writer.clear();
     }

     @Override
     public void onUrlLoad(EmbeddedBrowser arg0) {
          LOGGER.info("URL load.");
          captor.updateStat();
          LOGGER.info("Dropped Packets: " + captor.dropped_packets);
          LOGGER.info("Time : " + System.currentTimeMillis());
       captor.processPacket(-1, writer);
          if (previousState != null) {
               LOGGER.info("Clearing buffer");
               writer.clear();
          }
          if (storedURLS.containsKey(arg0.getCurrentUrl())) {
               previousState = storedURLS.get(arg0.getCurrentUrl());
          }
     }

     @Override
     public void postCrawling(CrawlSession arg0) {
          LOGGER.info("Crawing done.");
          captor.updateStat();
          LOGGER.info("Dropped Packets: " + captor.dropped_packets);
          LOGGER.info("Time : " + System.currentTimeMillis());
          captor.processPacket(-1, writer);
          //captor.
          LOGGER.info("Closing packet sniffer...");
          // captor.breakLoop();
          captor.close();
          writer.flush();
          writer.close();
          LOGGER.info("Packet sniffer closed...");
          if (captor.dropped_packets > 0) {
               LOGGER.info("Dropped packets detected. " + captor.dropped_packets
                         + " dropped packets.");
          }
     }

     @Override
     public void preCrawling(EmbeddedBrowser arg0) {
          timeStamper.updateTime();

          // TODO Auto-generated method stub
          devices = JpcapCaptor.getDeviceList();

          // for each network interface
          for (int i = 0; i < devices.length; i++) {
               // print out its name and description
               LOGGER.info(i + ": " + devices[i].name + "("
                         + devices[i].description + ")");

               // print out its datalink name and description
               LOGGER.info(" datalink: " + devices[i].datalink_name + "("
                         + devices[i].datalink_description + ")");
          }

          try {
               //captor = JpcapCaptor.openDevice(devices[deviceIndex], Integer.MAX_VALUE, false, Integer.MAX_VALUE);
               captor = JpcapCaptor.openDevice(devices[deviceIndex], 1000, false, 500);
               
               //captor.setNonBlockingMode(false);
               //LOGGER.info(captor.isNonBlockinMode());
               // captor = JpcapCaptor.openDevice(devices[index], 65535, true, 0);
               outputDirectory.mkdirs();
               if (outputDirectory != null) {
                    final File output = new File(outputDirectory.getAbsolutePath()
                              + File.separator + "index.txt");
                    writer = new PacketWriter(output, false, true, whiteList,
                              filter);
                    // captor.loopPacket(-1, writer);
                    // captor.processPacket(-1, writer);
               }
            //   captor.loopPacket(-1, writer);

          } catch (final IOException e) {
               LOGGER.error("Could not initialize packet sniffer...");
               // TODO Auto-generated catch block
               e.printStackTrace();
          }

     }

     @Override
     public void preStateCrawling(CrawlSession arg0, List<CandidateElement> arg1) {
          // writer.clear();
          // LOGGER.info("Candidates:");
          // for(CandidateElement element: arg1)
          // LOGGER.info(element);

     }

}