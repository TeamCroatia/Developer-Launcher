package net.malangbaram.DevLauncher;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class MainGUI {

	public static void main(String[] args) {
		mainForm();
	}

	private static void mainForm() {
		JFrame frame = new JFrame();
		frame.setSize(100,100);
		frame.setTitle("Croatia Installer");

		Container container = frame.getContentPane();
		container.setBackground(Color.white);
		container.setLayout(new FlowLayout());

		
		
		JButton btn = new JButton("����� ��ġ");
		container.add(btn);

		final JLabel stat = new JLabel("�غ���");
		container.add(stat);
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				stat.setText("�ٿ�ε� ��");
				
				JOptionPane.showConfirmDialog(null, "�ش� ��ġ�� ����� ��� �ʱ�ȭ�Ǹ� ���� �������� �������� ������ ��� �� ��ġ�Ͻñ� �����մϴ�.", "TeamCroatia Installer",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

				JOptionPane.showConfirmDialog(null, "�Ϸ�â�� �� �� ���� ���α׷��� �������� �����ּ���.", "TeamCroatia Installer",
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

					stat.setText("�������� ��");
					
					File zip = new File(System.getenv("APPDATA") + "\\.minecraft\\modpack.zip");
					
					CompressionUtil cu = new CompressionUtil();
					cu.unzip(zip , new File(System.getenv("APPDATA") + "\\.minecraft") );


					stat.setText("�Ϸ�");
					JOptionPane.showConfirmDialog(null, "�ٿ�ε� �Ϸ�", "TeamCroatia Installer",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
					zip.delete();
				} catch (Exception ies) {

					JOptionPane.showConfirmDialog(null, "�ٿ�ε� ����", "TeamCroatia Installer",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

					stat.setText("����");
					System.out.println(ies);
				}

			}
		});
		frame.setVisible(true);
	}
}
