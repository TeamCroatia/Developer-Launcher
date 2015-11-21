package net.malangbaram.DevLauncher.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.malangbaram.DevLauncher.Lang;

public class VersionManagementUtil {

	public static String checkMyVersion(File path, String versionFile) throws Exception {

		try {
			FileReader mVersionFR = new FileReader(path + ".//" + versionFile);
			BufferedReader mVersionBR = new BufferedReader(mVersionFR);
			String buf;
			buf = mVersionBR.readLine();

			if (buf.equals("unknown")) {
				return "미설치";
			} else {
				return buf;
			}

		} catch (FileNotFoundException e) {
			path.mkdirs();
			FileWriter mVersionFW = new FileWriter(path + versionFile);
			mVersionFW.write("unknown");
			BufferedWriter bw = new BufferedWriter(mVersionFW);
			bw.close();
			mVersionFW.close();
			return "미설치";
		}

	}

	public static String checkLastVersion(URL url) {

		try {
			HttpURLConnection connect;
			BufferedReader rd;
			String result;

			connect = (HttpURLConnection) url.openConnection();
			connect.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			result = rd.readLine();
			rd.close();

			return result;

		} catch (Exception e) {
		}
		return "확인 실패";
	}

	public static String checkLastVersion(String url) {

		try {
			URL url2 = new URL(url);
			HttpURLConnection connect;
			BufferedReader rd;
			String result;

			connect = (HttpURLConnection) url2.openConnection();
			connect.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			result = rd.readLine();
			rd.close();

			return result;

		} catch (Exception e) {
		}
		return "확인 실패";
	}

	public static int checkLauncherVersion(String url) {
		try {
			URL url2 = new URL(url);
			HttpURLConnection connect;
			BufferedReader rd;
			int result;

			connect = (HttpURLConnection) url2.openConnection();
			connect.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			result = Integer.parseInt(rd.readLine());
			rd.close();

			return result;

		} catch (Exception e) {}
		return -1;
	}
}