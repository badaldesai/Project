package edu.virginia.pmc8p.sca.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import edu.virginia.pmc8p.sca.SCAFinder;

public class CrawlRepairer {
	static List<Pattern> patternList = null;
	private static Map<Pattern,String> patternMap = null;
	private static final Logger LOGGER = Logger.getLogger(CrawlRepairer.class);

	public static void main(String[] args) throws Throwable {
		if(args.length!=2)
		{
			System.out.println("Usage: <Good Crawl Directory> <Bad Crawl Directory>");
			return;
		}
		
		File badCrawl = new File(args[1]);
		File badCarwlHTML = new File(args[1]+File.separator+"mirror");
		File badCarwlTransition = new File(args[1]+File.separator+"packets");
		File goodCrawlHTML = new File(args[0]+File.separator+"mirror");
		File goodCrawlTransition = new File(args[0]+File.separator+"packets");
		
		Set<String> goodCrawlTransitionCollection = new HashSet<String>();
		for(File f : goodCrawlTransition.listFiles()){
			goodCrawlTransitionCollection.add(f.getName());
		}
		
		Set<String> goodCrawlHTMLCollection = new HashSet<String>();
		for(File f : goodCrawlHTML.listFiles()){
			goodCrawlHTMLCollection.add(f.getName());
		}
		
		File outputDirectory = new File(badCrawl.getAbsolutePath()+File.separator+"fixed");
		File outputDirectoryHTML = new File(badCrawl.getAbsolutePath()+File.separator+"fixed"+File.separator+"mirror");
		File outputDirectoryPackets = new File(badCrawl.getAbsolutePath()+File.separator+"fixed"+File.separator+"packets");
		outputDirectory.mkdir();
		outputDirectoryPackets.mkdir();
		outputDirectoryHTML.mkdir();
		Map<String,String> mappingOfBadToGood = new HashMap<String,String>();
		
		//read in good trials
		LOGGER.info("Reading Good Trails");
		Map<String,String> goodTrialMapOfContentsToName = new HashMap<String,String>();
		for(File currentFile : goodCrawlHTML.listFiles()){
			LOGGER.info("Parsing: " + currentFile.getName());
			FileReader fr = new FileReader(currentFile);
			BufferedReader br = new BufferedReader(fr);
			StringBuilder result = new StringBuilder();
			String line;
			 while ((line = br.readLine()) != null) {
				 result.append(line);
			 }
			 String contents = result.toString();
			//LOGGER.info(contents);
			LOGGER.info("Read in file");
			contents = getRegularExpressionVersion(contents);
			LOGGER.info("Regular Expresion Performed");
			goodTrialMapOfContentsToName.put(contents, currentFile.getName().replace(".html", ""));
			
		}
		
		LOGGER.info("Reading Bad Crawl");
		//for each bad, find the good, and get the new name
		for(File currentFile : badCarwlHTML.listFiles()){
			LOGGER.info("Parsing: " + currentFile.getName());
			FileReader fr = new FileReader(currentFile);
			BufferedReader br = new BufferedReader(fr);
			StringBuilder result = new StringBuilder();
			String line;
			 while ((line = br.readLine()) != null) {
				 result.append(line);
			 }
			 String contents = result.toString();
			LOGGER.info("Read in file");
			contents = getRegularExpressionVersion(contents);
			LOGGER.info("Regular Expresion Performed");
			String tempResult = goodTrialMapOfContentsToName.get(contents);
			if(tempResult!=null){
				mappingOfBadToGood.put(currentFile.getName().replace(".html", ""), tempResult);
				LOGGER.info(currentFile.getName().replace(".html", "") + " maps to " + tempResult);
			}else{
				LOGGER.info("No match found");
			}
		}
		
		//do renaming of states and transitions(copying)
		//for each mapped mapping
			//find the file
			//copy it with new name
			//find the transitions
			//copy transitions with new names if they can be found, otherwise leave
		Map<String,File> badCrawlHTMLCollection = new HashMap<String, File>();
		for(File f : badCarwlHTML.listFiles()){
			badCrawlHTMLCollection.put(f.getName().replace(".html", ""),f);
		}
		LOGGER.info("Building Map of Transitions");
		Map<String,Set<File>> badCrawlTransitionCollection = new HashMap<String, Set<File>>();
		for(File f : badCarwlTransition.listFiles()){
			if(f.getName().indexOf('-')!=-1)
			{
				
				String keyname = f.getName().substring(0,f.getName().indexOf('-'));
				LOGGER.info("Linking " + keyname + " and " + f.getName());
				if(badCrawlTransitionCollection.containsKey(keyname))
				{
					Set<File> fileSet = badCrawlTransitionCollection.get(keyname);
					fileSet.add(f);
				}else{
					Set<File> fileSet = new HashSet<File>();
					fileSet.add(f);
					badCrawlTransitionCollection.put(keyname,fileSet);
				}
				
			}
		}
		LOGGER.info("Copying");
		Set<String> createdTransitions = new HashSet<String>();
		Set<String> createdHTML = new HashSet<String>();
		for(String badFileName : mappingOfBadToGood.keySet()){
			LOGGER.info("Looking up : " + badFileName);
			File badFile = badCrawlHTMLCollection.get(badFileName);
			if(badFile==null){
				LOGGER.info("Could not find file for filename " + badFileName);
				continue;
			}
			String newFileName = mappingOfBadToGood.get(badFileName);
			if(newFileName==null)
				continue;
			File newFile = new File(outputDirectoryHTML+File.separator+newFileName+".html");
			createdHTML.add(newFileName+".html");
			LOGGER.info("Copying " + badFile + " to " + newFile);
			copy(badFile, newFile);
			//String keyname = badFile.getName().substring(0,badFile.getName().indexOf('-'));
			String keyname = badFile.getName().replace(".html", "");
			LOGGER.info(keyname);
			if(badCrawlTransitionCollection.get(keyname)==null)
				continue;
			for(File f : badCrawlTransitionCollection.get(keyname))
			{
				LOGGER.info("Trying to find match for: " + f.getName());
				String secondStateName = f.getName().substring(f.getName().indexOf('-')+1,f.getName().length());
				secondStateName = secondStateName.replace(".txt", "");
				LOGGER.info("Finding state for transition destination : " + secondStateName);
				String newSecondStateName = mappingOfBadToGood.get(secondStateName);
				if(newSecondStateName!=null){
					File newTransitionFile = new File(outputDirectoryPackets+File.separator+newFileName+"-"+newSecondStateName+".txt");
					if(goodCrawlTransitionCollection.contains(newFileName+"-"+newSecondStateName+".txt"))
					{
						createdTransitions.add(newFileName+"-"+newSecondStateName+".txt");
						LOGGER.info("Copying " + f + " to " + newTransitionFile);
						copy(f,newTransitionFile);
					}
				}
			}
			//deal with transitions
			
			
		}
		
		for(String goodTransition: goodCrawlTransitionCollection){
			if(!createdTransitions.contains(goodTransition)){
				File f = new File(outputDirectoryPackets+File.separator+goodTransition);
				LOGGER.info("Creating empty " + f.getName());
				f.createNewFile();
			}
		}
		
		for(String goodHTML: goodCrawlHTMLCollection){
			if(!createdHTML.contains(goodHTML)){
				File f = new File(outputDirectoryHTML+File.separator+goodHTML);
				LOGGER.info("Creating empty " + f.getName());
				f.createNewFile();
			}
		}
		//create files that don't exist for states and transitions
		LOGGER.info("Copying Index");
		copy(new File(badCarwlHTML.getAbsolutePath()+File.separator+"index.html"), new File(outputDirectoryHTML+File.separator+"index.html"));
		copy(new File(badCarwlTransition.getAbsolutePath()+File.separator+"index.txt"), new File(outputDirectoryPackets+File.separator+"index.txt"));
		
		//deal with index
		LOGGER.info("Done");
	}
	
	public static String getRegularExpressionVersion(String input) {
			String workingCopy = new String(input.toLowerCase());
			List<Pattern> patterns = getPatternList();
			Map<Pattern,String> replaceMap = getMatchList();
			for(Pattern p : patterns)
			{
				Matcher m = p.matcher(workingCopy);
				if(m.find()){
					//LOGGER.info("Regular Expression Version Failed");
					if(replaceMap.get(p)!=null){
						workingCopy = m.replaceAll(replaceMap.get(p));
					}else{
						workingCopy = m.group();
					}
				}else{
					//LOGGER.info("Regular Expression Version Failed: " + p);
					//LOGGER.info(workingCopy);
					workingCopy = new String(input.toLowerCase());
					return workingCopy;
					
				}
			}
		return workingCopy;
	}

	private static List<Pattern> getPatternList() {
		if(patternList!=null)
			return patternList;
		else{
			patternList = new ArrayList<Pattern>();
			//patternList.add(Pattern.compile("[\\w\\W]*(action=\"\\/checksymptoms\\/sats\\/initialassessment.aspx\")[\\w\\W]*?(>)"));
			//patternList.add(Pattern.compile("(<\\/form>)[\\w\\W]*"));
			patternList.add(Pattern.compile("<script[\\w\\W]*?(<\\/script>)"));
			//patternList.add(Pattern.compile("((<[Aa] class=\"review_answers\" href=\"#previousans\">your answers to previous questions<\\/[Aa]>)|(<div class=\"message\"><label class=\"error\">please select an option<\\/label><\\/div>))"));
			patternList.add(Pattern.compile("<.*?>"));
			return patternList;			
		}
	}


	private static Map<Pattern, String> getMatchList() {
		if(patternMap !=null)
			return patternMap;
		else{
			patternMap = new HashMap<Pattern,String>();
			for(Pattern p : getPatternList())
				patternMap.put(p, "");
			return patternMap;			
		}
	
	}

	 public static void copy(File fromFile, File toFile)
     throws IOException {
   String fromFileName = fromFile.getName();
   String toFileName = toFile.getName();

   if (!fromFile.exists())
     throw new IOException("FileCopy: " + "no such source file: "
         + fromFileName);
   if (!fromFile.isFile())
     throw new IOException("FileCopy: " + "can't copy directory: "
         + fromFileName);
   if (!fromFile.canRead())
     throw new IOException("FileCopy: " + "source file is unreadable: "
         + fromFileName);

   if (toFile.isDirectory())
     toFile = new File(toFile, fromFile.getName());

   
     String parent = toFile.getParent();
     if (parent == null)
       parent = System.getProperty("user.dir");
     File dir = new File(parent);
     if (!dir.exists())
       throw new IOException("FileCopy: "
           + "destination directory doesn't exist: " + parent);
     if (dir.isFile())
       throw new IOException("FileCopy: "
           + "destination is not a directory: " + parent);
     if (!dir.canWrite())
       throw new IOException("FileCopy: "
           + "destination directory is unwriteable: " + parent);
   

   FileInputStream from = null;
   FileOutputStream to = null;
   try {
     from = new FileInputStream(fromFile);
     to = new FileOutputStream(toFile);
     byte[] buffer = new byte[4096];
     int bytesRead;

     while ((bytesRead = from.read(buffer)) != -1)
       to.write(buffer, 0, bytesRead); // write
   } finally {
     if (from != null)
       try {
         from.close();
       } catch (IOException e) {
         ;
       }
     if (to != null)
       try {
         to.close();
       } catch (IOException e) {
         ;
       }
   }

	
}
}