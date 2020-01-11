package com.dobest.appload.web.service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;

import com.dobest.appload.constant.Constant;
import com.dobest.appload.dao.file.FileManager;
import com.dobest.appload.entity.AppInfor;
import com.dobest.appload.entity.ShareBase;

public class PageManager {

	//private static final Logger LOG = LoggerFactory.getLogger(PageManager.class);

	public AppInfor getAppInfor(ShareBase shareBase) {
		App app = getFile(shareBase, Constant.DEFAULT_CHANNEL);
		if(!app.isOk)
			return null;
		Map<String, String> map = FileManager.getContent(app.file);
		AppInfor appInfor = new AppInfor();
		appInfor.setAppname(app.appName);
		
		if(Constant.DEBUG_MOD)
		{
			switch(shareBase.getOs())
			{
				case "1": appInfor.setDeeplink(map.get("deeplinkAndroid"));break;
				case "2": appInfor.setDeeplink(map.get("deeplinkios"));break;
			}
		}
		else
			appInfor.setDeeplink(Constant.getDeepLink(shareBase.getOs(), shareBase.getPakName(), shareBase.getMsg()));
		appInfor.setShowurl(map.get("showurl"));
		appInfor.setDesc(map.get("desc"));
		appInfor.setDownurl(map.get("downUrl"));
		return appInfor;
	}

	public App getFile(final ShareBase shareBase, String defaultChannel) {
		File file = new File(Constant.APP_INFOR);
		final App app = new App();
		File[] files = file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.startsWith("conf")) {
					String[] buf = name.split("_");
					if (buf.length == 5) {
						if (buf[1].equals(shareBase.getId())
								&& buf[2].equals(shareBase.getOs())) {
							return true;
						}
					}
				}
				return false;
			}
		});
		if((files != null) && (files.length != 0))
		{
			for(int i = 0; i < files.length;i++)
			{
				String[] buf = files[i].getName().split("_");
				if(buf[3].equals(shareBase.getChnl()))
				{
					app.file = files[i];
					break;
				}
			}
			String[] buf = files[0].getName().split("_");
			if(app.file == null){
				String fileName = files[0].getName().replace(buf[3], defaultChannel);
				app.file = new File(files[0].getParent()+"\\"+fileName);
			}
			app.appName = buf[4].substring(0, buf[4].indexOf("."));
			app.isOk = true;
		}
		return app;
	}

	static class App {
		public boolean isOk = false;
		public File file;
		public String appName;
	}

}
