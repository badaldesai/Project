package edu.virginia.pmc8p.sca;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.Logger;
import org.jdom.JDOMException;

import com.crawljax.core.CrawljaxException;
import com.crawljax.core.configuration.CrawlSpecification;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.core.configuration.InputSpecification;

import edu.virginia.pmc8p.sca.input.InputBuilder;
import edu.virginia.pmc8p.sca.input.LoginBuilder;
import edu.virginia.pmc8p.sca.input.SpecificationBuilder;


public class SCAFinder {
	private static final Logger LOGGER = Logger.getLogger(SCAFinder.class);
	private static String rootURL;
	private static File tagsFile;
	private static int maxStates;
	private static int maxDepth;
	private static File outputDirectory;
	private static int maxRunTime;
	private static int trials;
	private static File filterFile;
	private static File whiteListFile;
	private static int device;
	private static File inputFile;
	private static File preCrawlFile = null;
	public static void main(String[] args) {
		
		if(args.length!=11 && args.length!=12)
		{
			System.out.println("Usage: <Root URL> <Interaction Spec> <Filter File> <Whitelist File> <Max # of States> <Max Depth> <Max Run Time (Seconds)> <Trials> <Device to Monitor> <Output Directory> <Input Spec> [Login Spec]");
			return;
		}
		rootURL = args[0];
		
		tagsFile = new File(args[1]);
		filterFile = new File(args[2]);
		whiteListFile = new File(args[3]);
		maxStates = Integer.parseInt(args[4]);
		maxDepth = Integer.parseInt(args[5]);
		maxRunTime = Integer.parseInt(args[6]);
		trials = Integer.parseInt(args[7]);
		device = Integer.parseInt(args[8]);
		outputDirectory = new File(args[9]);
		inputFile = new File(args[10]);
		if(args.length>11)
		{
			preCrawlFile = new File(args[11]);
		}
		
		DataCollector dataCollector = new DataCollector();

		for(int i = 0; i < trials; i++)
		{
			CrawlSpecification specification = new CrawlSpecification(rootURL);
			
			SpecificationBuilder specificationBuilder = new SpecificationBuilder(specification, tagsFile,60000);
			try {
				specificationBuilder.constructCrawlSpecification();
			} catch (JDOMException e2) {
				LOGGER.info("Could not parse crawl specification XML");
				LOGGER.info(e2.getMessage());
			} catch (IOException e2) {
				LOGGER.info("Could not read crawl specification file");
				LOGGER.info(e2.getMessage());
			}

			specification.setMaximumStates(maxStates);
			specification.setDepth(maxDepth);
			if(maxRunTime != -1)
				specification.setMaximumRuntime(maxRunTime);
			else
				specification.setMaximumRuntime(99999999);
			specification.setWaitTimeAfterEvent(3000);
			specification.setWaitTimeAfterReloadUrl(3000);
			
			specification.setClickOnce(false);
			specification.setRandomInputInForms(false);

			InputBuilder builder = new InputBuilder(inputFile);
			InputSpecification inputTemp = null;
			try {
				inputTemp = builder.constructInputSpecification();
			} catch (JDOMException e1) {
				LOGGER.info("Error parsing input file");
				LOGGER.error(e1);
			} catch (IOException e1) {
				LOGGER.info("Error reading input file");
				LOGGER.error(e1);
			}
				
			specification.setInputSpecification(inputTemp);

			CrawljaxConfiguration configuration = new CrawljaxConfiguration();
			configuration.setCrawlSpecification(specification);
			configuration.setContinueOnClone(false);
			configuration.setPageLoadTimeoutTime(60000L);

			if(preCrawlFile!=null)
			{
				LoginBuilder loginBuilder = new LoginBuilder(preCrawlFile);
				try {
					configuration.addPlugin(loginBuilder.constructLoginPlugin());
				} catch (JDOMException e) {
					LOGGER.info("Could not parse pre-crawl XML file.");
					LOGGER.info(e.getMessage());
				} catch (IOException e) {
					LOGGER.info("Could not read pre-crawl XML file.");
					LOGGER.info(e.getMessage());
				}
			}
			
			File workingOutput = new File(outputDirectory.toString() + File.separator+i);
			workingOutput.mkdirs();
			
			
			try {
				//dataCollector.runTestWithoutNetwork(configuration, workingOutput,filterFile,whiteListFile,device);
				dataCollector.runTest(configuration, workingOutput,filterFile,whiteListFile,device);
			} catch (ConfigurationException e) {
				LOGGER.info("Malformed Crawler Configuration");
				LOGGER.info(e.getMessage());
			} catch (CrawljaxException e) {
				LOGGER.info("Error occured while crawling");
				LOGGER.info(e.getMessage());
			}
			
		}			
	}	
}