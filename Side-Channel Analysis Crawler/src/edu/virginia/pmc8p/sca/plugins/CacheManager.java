package edu.virginia.pmc8p.sca.plugins;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.crawljax.browser.EmbeddedBrowser;
import com.crawljax.core.plugin.OnUrlLoadPlugin;

public class CacheManager implements OnUrlLoadPlugin {

	String tempAppDataDirectory;
	File cacheDirectory = null;
	public CacheManager(String tempAppDataDirectory) {
		this.tempAppDataDirectory = tempAppDataDirectory;
	}

	@Override
	public void onUrlLoad(EmbeddedBrowser browser) {
		File f = new File(tempAppDataDirectory+"/CLEARCACHE");
		FileWriter fw;
		try {
			fw = new FileWriter(f);
		
		fw.append(' ');
		fw.flush();
		fw.close();
		} catch (IOException e) {
			System.out.println("ERROR CREATING CLEARCACHE FILE");
		}
		/*//Let's clear the cache!
		//Find directory
		if(cacheDirectory==null){
			File appData = new File(tempAppDataDirectory);
			//Find newest created directory
			List<File> files = new ArrayList<File>();
			File[] fileArray = (appData.listFiles(new FileFilter() {

				@Override
				public boolean accept(File f) {
					if(!f.isDirectory())
						return false;
					if(f.getName().contains("webdriver"))
						return true;
					return false;
				}
			}));

			for(File f : fileArray)
				files.add(f);

			Collections.sort(files, new Comparator<File>() {

				@Override
				public int compare(File o1, File o2) {
					return new Long(o1.lastModified()).compareTo(new Long(o2.lastModified()));
				}
			});

			File profile = files.get(files.size()-1);
			for(File f : profile.listFiles()){
				if(f.getName().equals("Cache"))
					cacheDirectory = f;
			}



		}
		
		for(File f : cacheDirectory.listFiles()){
			if(f.getName().contains("_"))
				continue;
			f.delete();
		}


		//Delete files
		//Profit!!!
		 */
	}

}
