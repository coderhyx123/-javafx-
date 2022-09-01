package com.hyx.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Downmusic {

	public File down(String path, String web, String fileName) {

		FileOutputStream fos;
		try {
			URL url = new URL(web);
			URLConnection opnn = url.openConnection();
			opnn.addRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
			InputStream is = opnn.getInputStream();
			String nowzifu2;
			if (fileName.indexOf("(") != -1) {
				String beidelete = fileName.substring(fileName.indexOf("("));
				String nowzifu = fileName.replace(beidelete, "");// ÏÖÔÚµÄ×Ö·û
				nowzifu2 = nowzifu.replace(" ", "");
			} else {
				nowzifu2 = fileName.replace(" ", "");
			}
			String s = path + "\\" + nowzifu2 + ".mp3";
			File files = new File(s);
			fos = new FileOutputStream(files);
			int len = 0;
			byte[] data = new byte[1024];
			while ((len = is.read(data)) != -1) {
				fos.write(data, 0, len);
			}

//			System.out.println(files.getPath());
//			return files.getPath();
			return files;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
