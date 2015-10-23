package net.malangbaram.DevLauncher;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.malangbaram.DevLauncher.Util.CompressionUtil;
import net.malangbaram.DevLauncher.Util.RemoveUtil;
import net.malangbaram.DevLauncher.Util.VersionManagementUtil;

public class MainGUI {

	static String myVersion;
	static String lastVersion;

	public static void main(String[] args) throws Exception {

		myVersion = VersionManagementUtil.checkMyVersion();
		lastVersion = VersionManagementUtil.checkLastVersion();

		mainForm();
	}

	private static void mainForm() {
		JFrame frame = new JFrame();
		frame.setSize(150, 167);
		frame.setTitle("Croatia Installer");

		Container container = frame.getContentPane();
		container.setBackground(Color.white);
		container.setLayout(new FlowLayout());

		Dimension sizeFrame = frame.getSize();
		Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((sizeScreen.width - sizeFrame.width)/2, (sizeScreen.height - sizeFrame.height)/2);
		
		JLabel myVersion = new JLabel("현재버전:");
		container.add(myVersion);

		JLabel myVersionView = new JLabel(MainGUI.myVersion);
		container.add(myVersionView);

		JLabel lastVersion = new JLabel("최신버전:");
		container.add(lastVersion);

		JLabel lastVersionView = new JLabel(MainGUI.lastVersion);
		container.add(lastVersionView);

		JButton Dbtn = new JButton("모드팩 다운로드");
		container.add(Dbtn);
		JButton Lbtn = new JButton("런처 실행");
		container.add(Lbtn);

		final JLabel stat = new JLabel("준비중");
		container.add(stat);

		Dbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				stat.setText("다운로드 중");

				JOptionPane.showConfirmDialog(null, "해당 설치기 실행시 모두 초기화되며 현재 백업기능은 지원하지 않으니 백업 후 설치하시길 권장합니다.", "TeamCroatia Installer",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

				JOptionPane.showConfirmDialog(null, "완료창이 뜰 때 까지 프로그램을 종료하지 말아주세요.", "TeamCroatia Installer",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

				try {

					File remove = new File(System.getenv("APPDATA") + "\\.minecraft");
					RemoveUtil.deleteDirectory(remove);

					URL u = new URL("http://dl.malangbaram.net/modpack.zip");
					File filePath = new File(System.getenv("APPDATA") + "\\.minecraft");
					File fileDir = filePath.getParentFile();
					filePath.mkdirs();
					FileOutputStream fos = new FileOutputStream(System.getenv("APPDATA") + "\\.minecraft\\modpack.zip");
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

					stat.setText("압축해제 중");
					
					File zip = new File(System.getenv("APPDATA") + "\\.minecraft\\modpack.zip");
					
					CompressionUtil cu = new CompressionUtil();
					cu.unzip(zip , new File(System.getenv("APPDATA") + "\\.minecraft") );


					stat.setText("완료");
					JOptionPane.showConfirmDialog(null, "다운로드 완료", "TeamCroatia Installer",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
					zip.delete();
				} catch (Exception ies) {

					JOptionPane.showConfirmDialog(null, "다운로드 오류", "TeamCroatia Installer",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

					stat.setText("실패");
					System.out.println(ies);
				}

			}
		});
		
		Lbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(MainGUI.myVersion.equals("미설치")) {
					JOptionPane.showConfirmDialog(null, "현재 로컬에 모드팩이 설치되어 있지 않습니다. 설치 후 다시 시도 해주세요.", "TeamCroatia Installer",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				}else {
					File file = new File(System.getenv("APPDATA") + "\\.minecraft\\launcher.jar");
					
					Process p;
					try {
						p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + 
						file.getAbsolutePath());
						p.waitFor();
					} catch (Exception e1) {}
				}
			}
		});

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.setVisible(true);
	}
}
