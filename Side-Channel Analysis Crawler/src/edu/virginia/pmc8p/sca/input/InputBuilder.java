package edu.virginia.pmc8p.sca.input;

import java.awt.CardLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.crawljax.core.configuration.CrawlElement;
import com.crawljax.core.configuration.Form;
import com.crawljax.core.configuration.FormInputField;
import com.crawljax.core.configuration.InputSpecification;
import com.crawljax.core.state.Identification;
import com.crawljax.core.state.Identification.How;

import edu.virginia.pmc8p.sca.SCAFinder;

public class InputBuilder {
	private File inputFile;
	private static final Logger LOGGER = Logger.getLogger(InputBuilder.class);
	
	
	public InputBuilder(File inputFile) {
		super();
		this.inputFile = inputFile;
	}
	
	
	public InputSpecification constructInputSpecification() throws JDOMException, IOException{
		InputSpecification input = new InputSpecification();
		LOGGER.info("Constructing Input Specification");
		SAXBuilder parser = new SAXBuilder();
		
		Document doc = parser.build(inputFile);
		
		List lroot = doc.getContent();
		List l = null;
		for(Object o : lroot)
		{
			if(o instanceof Element)
			{
				Element e = (Element)o;
				if(e.getName().equals("inputSpec"))
				{
					l = e.getContent();
				}
			}
		}
		
		if(l == null)
			return input;
		
		for(Object o : l)
		{
			if(o instanceof Element)
			{
				Element e = (Element)o;
				if(e.getName().equals("form"))
				{
					processForm(e,input);
				}
			}
		}
		return input;
		
	}


	private void processForm(Element e, InputSpecification input) {
		List l = e.getContent();
		Form form = new Form();
		Element beforeClickElement = null;
		LOGGER.info("Processing Form");
		for(Object o : l){
			if(o instanceof Element){
				Element currentElement = (Element)o;
				if(currentElement.getName().equals("beforeClickElement"))
				{
					 beforeClickElement = currentElement;
				}
				else if(currentElement.getName().equals("field"))
				{
					processField(currentElement,form);
				}else if(currentElement.getName().equals("randomField"))
				{
					processRandomField(currentElement,form);
				}
				
			}
		}
		
		linkForm(form,input,beforeClickElement);
	}


	private void processRandomField(Element randomFieldElement, Form form) {
		//double threshold = Double.parseDouble(randomFieldElement.getChild("threshold").getText());
		//int number = randomFieldElement.getChild("threshold").getText();
		//Random random = new Random();
		//for each field
		List randl = randomFieldElement.getContent();
		for(Object rando:randl)
		{
			if(rando instanceof Element){
				Element fieldElement = (Element)rando;
				if(fieldElement.getName().equals("field")){

					//in a field
					ArrayList<Boolean> booleanValues = new ArrayList<Boolean>();
					ArrayList<String> textValues = new ArrayList<String>();
					String id = null;
					Identification identification = null;
					List l = fieldElement.getContent();

					for(Object o: l){
						if(o instanceof Element){
							Element currentElement = (Element)o;
							if(currentElement.getName().equals("id")){
								id = currentElement.getText();
							}else if(currentElement.getName().equals("identification")){
								identification = getIdentification(currentElement);
							}
							else if(currentElement.getName().equals("valueB")){
								boolean val = Boolean.parseBoolean(currentElement.getText());
								booleanValues.add(val);
							}else if(currentElement.getName().equals("value")){
								textValues.add(currentElement.getText());
							}
						}
					}
					
					
					if(booleanValues.size()>textValues.size())
					{
						Collections.shuffle(booleanValues);
						
						//boolean[] temp = new boolean[booleanValues.size()];
						boolean[] temp = new boolean[1];
						//for(int i = 0; i < booleanValues.size(); i++)
						for(int i = 0; i < 1; i++)
							temp[i] = booleanValues.get(i);
						if(identification!=null)
						{
							form.field(identification).setValues(temp);
							
						}else{
							form.field(id).setValues(temp);
							//LOGGER.info("Adding " + temp.length + " values for id: " + id);
						}
					}else{
						Collections.shuffle(textValues);
						
						if(identification!=null)
						{
						//	form.field(identification).setValues((String[]) textValues.toArray());
							form.field(identification).setValue(textValues.get(0));
						}
						else
						{
							//form.field(id).setValues((String[]) textValues.toArray());
							form.field(id).setValue(textValues.get(0));
					//		LOGGER.info("Adding " + textValues.size()+ " values for id: " + id);	
						}
						
					}

				}
			}
		}

	}


	private void linkForm(Form form, InputSpecification input,
			Element beforeClickElement) {
		if(beforeClickElement == null){
			LOGGER.info("Before Click Element Not Found");
			return;
		}
		
		String tag = null;
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		String xPath = null;
		String text = null;
		List l = beforeClickElement.getContent();
		for(Object o: l)
			if(o instanceof Element){
				Element currentElement = (Element)o;
				
				if(currentElement.getName().equals("tag")){
					tag = currentElement.getText();
				}
				else if(currentElement.getName().equals("attribute")){
					names.add(currentElement.getChild("name").getText());
					values.add(currentElement.getChild("value").getText());
				}else if(currentElement.getName().equals("xPath"))
				{
					xPath = currentElement.getChild("expression").getText();
				}else if(currentElement.getName().equals("text"))
				{
					text = currentElement.getChild("value").getText();
				}
				
				
			}
		
		
		CrawlElement crawlElement = input.setValuesInForm(form).beforeClickElement(tag);
		LOGGER.info("Tag:" + tag);
		if(xPath!=null)
		{
		//	LOGGER.info("xPath:" + xPath);
			crawlElement.underXPath(xPath);
		}
		if(text!=null){
			//LOGGER.info("Text:" + text);
			crawlElement.withText(text);
		}
		for(int i = 0; i < names.size(); i++)
		{
			LOGGER.info("Attribute:" + names.get(i) + ", "+values.get(i));
			crawlElement.withAttribute(names.get(i), values.get(i));
		}
		
	}


	private void processField(Element fieldElement, Form form) {
		ArrayList<Boolean> booleanValues = new ArrayList<Boolean>();
		ArrayList<String> textValues = new ArrayList<String>();
		String id = null;
		List l = fieldElement.getContent();
		Identification identification = null;
		for(Object o: l)
			if(o instanceof Element){
				Element currentElement = (Element)o;
				if(currentElement.getName().equals("id")){
					id = currentElement.getText();
				}else if(currentElement.getName().equals("identification")){
					identification = getIdentification(currentElement);
				}
				else if(currentElement.getName().equals("valueB")){
					boolean val = Boolean.parseBoolean(currentElement.getText());
					//LOGGER.info(fieldElement + " "+ currentElement.getText() + " " + val);
					booleanValues.add(val);
				}else if(currentElement.getName().equals("value")){
					textValues.add(currentElement.getText());
				}
			}
		
		if(booleanValues.size()>textValues.size())
		{
			boolean[] temp = new boolean[booleanValues.size()];
			for(int i = 0; i < booleanValues.size(); i++)
				temp[i] = booleanValues.get(i);
			if(identification!=null)
			{
				form.field(identification).setValues(temp);
				
			}else{
			form.field(id).setValues(temp);
			//LOGGER.info("Adding " + temp.length + " values for id: " + id);
			}
		}else{
			String[] temp = new String[textValues.size()];
			for(int i = 0; i < textValues.size(); i++)
				temp[i] = textValues.get(i);
			if(identification!=null)
			{
			//	LOGGER.info("Set identification");
				form.field(identification).setValues(temp);
			}else{
			form.field(id).setValues(temp);
			//LOGGER.info("Adding " + textValues.size()+ " values for id: " + id);
			}
		}
	}
	private Identification getIdentification(Element e) {
		Identification identification = null;
		if (e.getChild("xpath")!=null){
			identification = new Identification(How.xpath,e.getChildText("xpath"));
		}
		
		
		return identification;
	}

}
