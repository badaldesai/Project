package edu.virginia.pmc8p.sca.input;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.crawljax.browser.EmbeddedBrowser;
import com.crawljax.core.CrawljaxException;
import com.crawljax.core.configuration.InputSpecification;
import com.crawljax.core.plugin.PreCrawlingPlugin;
import com.crawljax.core.state.Eventable;
import com.crawljax.core.state.Eventable.EventType;
import com.crawljax.core.state.Identification;
import com.crawljax.core.state.Identification.How;

public class LoginBuilder implements PreCrawlingPlugin {
	private File inputFile;
	private static final Logger LOGGER = Logger.getLogger(LoginBuilder.class);
	private Document doc;
	private EmbeddedBrowser browser;
	public LoginBuilder(File inputFile) {
		super();
		this.inputFile = inputFile;
	}

	public LoginBuilder constructLoginPlugin() throws JDOMException, IOException{

		SAXBuilder parser = new SAXBuilder();

		doc = parser.build(inputFile);

		return this;

	}

	@Override
	public void preCrawling(EmbeddedBrowser arg0) {
		browser = arg0;
		List l = doc.getContent();
		for(Object o : l)
		{
			if(o instanceof Element)
			{
				Element e = (Element)o;
				if(e.getName().equals("preCrawl"))
				{
					processPreCrawl(e);
				}else if(e.getName().equals("randomPreCrawl"))
				{
					processRandomPreCrawl(e);
				}
			}
		}

	}

	private void processRandomPreCrawl(Element e) {
		
		//Count elements
		List l = e.getChildren();
		int count = 0;
		for(Object o : l)
		{
			if(o instanceof Element)
			{
				Element current = (Element) o;
				if(current.getName().equals("preCrawl"))
				count++;
			}
		}
		
		Random rand = new Random();
		int choosen = rand.nextInt(count);
		LOGGER.info("NUMBER OF POSSIBLE LOGINS " + count);
		LOGGER.info("CHOOSING NUMBER " + choosen);
		Element choosenElement = e.getChild("preCrawl");
		l = e.getChildren();
		count = 0;
		for(Object o : l)
		{
			if(o instanceof Element)
			{
				Element current = (Element) o;
				
				if(current.getName().equals("preCrawl"))
				count++;
				
				if(count==choosen)
				{
					choosenElement = current;
					break;
				}
			}
		}
		
		processPreCrawl(choosenElement);
		
		
	}

	private void processPreCrawl(Element parent) {
		List l = parent.getChildren();
		for(Object o : l)
		{
			if(o instanceof Element)
			{
				Element e = (Element) o;
				
				if(e.getName().equals("input"))
				{
					processInput(e);
				}else if(e.getName().equals("click"))
				{
					processClick(e);
				}else if(e.getName().equals("url"))
				{
					processURL(e);
				}
				
			}
		}
		
	}

	private void processURL(Element e) {
	
		String urlText = e.getChildText("value");
		
		if(urlText == null)
			return;
		
		try {
			browser.goToUrl(urlText);
		} catch (CrawljaxException e1) {
			LOGGER.info("Error loading url: "+urlText);
			LOGGER.info(e1.getMessage());
		}
	}

	private void processClick(Element e) {
		Identification identification = getIdentification(e);
		
		if(identification==null)
			return;
				
		try {
			Eventable event = new Eventable(identification,EventType.click);
			browser.fireEvent(event);
			//LOGGER.info("CLICKING: " + event);
		} catch (CrawljaxException e1) {
			LOGGER.info("Error clicking "+identification);
			LOGGER.info(e1.getMessage());
		}
		
	}

	private void processInput(Element e) {
		Identification identification = getIdentification(e);
		
		if(identification==null)
			return;
		
		String inputText = e.getChildText("value");
		
		if(inputText == null)
			return;
		
		try {
			browser.input(identification, inputText);
		} catch (CrawljaxException e1) {
			LOGGER.info("Error typing text \""+inputText+"\" into " + identification);
			LOGGER.info(e1.getMessage());
		}
		
		
		
	}

	private Identification getIdentification(Element e) {
		Identification identification = null;
		if(e.getChild("id")!=null){
			identification = new Identification(How.id,e.getChildText("id"));
		}else if (e.getChild("name")!=null){
			identification = new Identification(How.name,e.getChildText("name"));
		}else if (e.getChild("partialText")!=null){
			identification = new Identification(How.partialText,e.getChildText("partialText"));
		}else if (e.getChild("tag")!=null){
			identification = new Identification(How.tag,e.getChildText("tag"));
		}else if (e.getChild("text")!=null){
			identification = new Identification(How.text,e.getChildText("text"));
		}else if (e.getChild("xpath")!=null){
			identification = new Identification(How.xpath,e.getChildText("xpath"));
		}
		
		
		return identification;
	}

}
