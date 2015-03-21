package edu.virginia.pmc8p.sca.plugins;

import java.io.File;
import java.io.FileWriter;

import com.crawljax.core.CrawlSession;
import com.crawljax.core.plugin.OnNewStatePlugin;

public class MirrorGenerator implements OnNewStatePlugin {
	File dir;
	
	public MirrorGenerator(File dir) {
		super();
		this.dir = dir;
	}

	@Override
	public void onNewState(CrawlSession session) {
		try {
			String fileName = session.getCurrentState().getName() + ".html";
			dir.mkdirs();
			FileWriter fw = new FileWriter(dir.getAbsolutePath()+File.separator+fileName, false);			
			String dom = session.getCurrentState().getDom();
			fw.write(dom);
			
			
			
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}