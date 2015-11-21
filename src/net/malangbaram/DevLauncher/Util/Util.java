package net.malangbaram.DevLauncher.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class Util {
	/*
	 * 윈도우 프로그램 실행
	 * 
	 * @author MalangBaram
	 */
	public static void PGAction(File f) {
		Process p;
		try {
			p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + f.getAbsolutePath());
			p.waitFor();
		} catch (Exception e) {
		}
	}
	
	/*
	 * 디렉토리 삭제
	 */
	public static boolean delDir(File path) {
		if (!path.exists()) {
			return false;
		}

		File[] files = path.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				delDir(file);
			} else {
				file.delete();
			}
		}

		return path.delete();
	}

	public static boolean delDir(String path) {
		
		File f = new File(path);
		
		if (!f.exists()) {
			return false;
		}

		File[] files = f.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				delDir(file);
			} else {
				file.delete();
			}
		}

		return f.delete();
	}

	/*
	 * 웹서버에서 파일 다운
	 */
	public static void webDown(File path, URL url, String file) {

		try {
			File fileDir = path.getParentFile();
			path.mkdirs();

			FileOutputStream fos = new FileOutputStream(System.getenv("APPDATA") + "\\.minecraft\\" + file);
			InputStream is = url.openStream();

			byte[] buf = new byte[1024];
			double len = 0;
			double tmp = 0;
			double percent = 0;

			while ((len = is.read(buf)) > 0) {
				tmp += len / 1024 / 1024;
				percent = tmp / 1229 * 100;
				fos.write(buf, 0, (int) len);
			}

			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void webDown(String path, String url, String file) {
		
		try {
			File f = new File(path);
			URL u = new URL(url);
			File fileDir = f.getParentFile();
			f.mkdirs();

			FileOutputStream fos = new FileOutputStream(System.getenv("APPDATA") + "\\.minecraft\\" + file);
			InputStream is = u.openStream();

			byte[] buf = new byte[1024];
			double len = 0;
			double tmp = 0;
			double percent = 0;

			while ((len = is.read(buf)) > 0) {
				tmp += len / 1024 / 1024;
				percent = tmp / 1229 * 100;
				fos.write(buf, 0, (int) len);
			}

			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void webDown(File path, String url, String file) {
		try {
			URL u = new URL(url);
			File fileDir = path.getParentFile();
			path.mkdirs();

			FileOutputStream fos = new FileOutputStream(System.getenv("APPDATA") + "\\.minecraft\\" + file);
			InputStream is = u.openStream();

			byte[] buf = new byte[1024];
			double len = 0;
			double tmp = 0;
			double percent = 0;

			while ((len = is.read(buf)) > 0) {
				tmp += len / 1024 / 1024;
				percent = tmp / 1229 * 100;
				fos.write(buf, 0, (int) len);
			}

			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void webDown(String path, URL url, String file) {
		try {
			File f = new File(path);
			File fileDir = f.getParentFile();
			f.mkdirs();

			FileOutputStream fos = new FileOutputStream(System.getenv("APPDATA") + "\\.minecraft\\" + file);
			InputStream is = url.openStream();

			byte[] buf = new byte[1024];
			double len = 0;
			double tmp = 0;
			double percent = 0;

			while ((len = is.read(buf)) > 0) {
				tmp += len / 1024 / 1024;
				percent = tmp / 1229 * 100;
				fos.write(buf, 0, (int) len);
			}

			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
