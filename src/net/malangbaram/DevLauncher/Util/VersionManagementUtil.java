package net.malangbaram.DevLauncher.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VersionManagementUtil {

	public static String checkMyVersion() throws Exception {

		try {
			FileReader mVersionFR = new FileReader("mVersion.txt");
			BufferedReader mVersionBR = new BufferedReader(mVersionFR);
			String buf;
			buf = mVersionBR.readLine();
			
			if(buf.equals("unknown")) {
				return "미설치";
			}else {
				return buf;
			}
			
		}catch(FileNotFoundException e) {
			FileWriter mVersionFW = new FileWriter("mVersion.txt");
			mVersionFW.write("unknown");
			BufferedWriter bw = new BufferedWriter(mVersionFW);
			bw.close();
			mVersionFW.close();
			return "미설치";
		}
		
	}
	
	public static String checkLastVersion() {
		
		try{
		URL lUrl = new URL("http://dl.malangbaram.net/modVersion.txt");
		HttpURLConnection connect;
		BufferedReader rd;
		String result;

		connect = (HttpURLConnection) lUrl.openConnection();
		connect.setRequestMethod("GET");
		rd = new BufferedReader(new InputStreamReader(connect.getInputStream()));
		result = rd.readLine();
		rd.close();
		
		return result;
		
		}catch(Exception e) {}
		return "확인 실패";
	}

}
