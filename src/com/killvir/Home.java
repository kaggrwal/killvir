package com.killvir;

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.xml.stream.events.StartDocument;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.prefs.BackingStoreException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class Home extends JFrame {
	private JTextField textField = new JTextField();;
	private JFileChooser path_choser = new JFileChooser();
	static String ERROR_in_update;
	static Thread update_check;
	static JLabel lblCheckingForUpdates;
	static java.util.prefs.Preferences settings;

	public Home() {
		this.setSize(500, 300);
		getContentPane().setBackground(new Color(0, 153, 204));
		getContentPane().setLayout(null);

		textField.setBounds(183, 81, 179, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		final JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				path_choser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int return_value = path_choser.showOpenDialog(btnNewButton);
				if (return_value == JFileChooser.APPROVE_OPTION) {
					textField.setText(path_choser.getSelectedFile().getPath());
				}
			}
		});
		btnNewButton.setBounds(385, 80, 89, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Scan");
		btnNewButton_1.setBounds(270, 128, 115, 47);
		getContentPane().add(btnNewButton_1);

		lblCheckingForUpdates = new JLabel("Checking for Updates ...");
		lblCheckingForUpdates.setBounds(292, 207, 163, 23);
		getContentPane().add(lblCheckingForUpdates);
		lblCheckingForUpdates.setVisible(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Preferences");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new Preferences();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem mntmNewMenuItem = new JMenuItem("Check for Updates");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String update_check_result = update_check();

				if (update_check_result == "NNU") {
					JOptionPane.showMessageDialog(null, "No new updates",
							"No update Found", JOptionPane.INFORMATION_MESSAGE);
				} else if (update_check_result == "NIC") {
					JOptionPane
							.showMessageDialog(
									null,
									"No internet conncectivity available\nTry again later",
									"Error", JOptionPane.ERROR_MESSAGE);
				} else if (update_check_result.contains("Error")) {
					JOptionPane.showMessageDialog(null,
							"Problem in connecting to Servers\n\n"
									+ update_check_result, "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {

				}

			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Help");
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);

		this.setVisible(true);
	}

	public static String update_check() {

		lblCheckingForUpdates.setVisible(true);

		settings = java.util.prefs.Preferences
				.userNodeForPackage(Preferences.class);

		if (settings.getBoolean("Update", true)) {

			update_check = new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub

					try {

						Process inetcheck = Runtime.getRuntime().exec(
								"ping www.google.com");
						if ((inetcheck.waitFor()) == 0) {
							lblCheckingForUpdates
									.setText("Connecting to Server ...");
							// System.out.println(settings.getDouble("VERSION",
							// 0)+"\n"+Updater.getLatestVersion());
							lblCheckingForUpdates
							.setText(String.valueOf(settings
									.getDouble("VERSION",
											Preferences.VERSION_NO)));
							if (Float.parseFloat(Updater.getLatestVersion()) > settings
									.getDouble("VERSION",
											Preferences.VERSION_NO)) {
								lblCheckingForUpdates
										.setText("Looking for Updates ...");
								new UpdateInfo(Updater.getWhatsNew());
								ERROR_in_update = "NUF";
								lblCheckingForUpdates.setText("Updates Found");
							} else {
								ERROR_in_update = "NNU";
								lblCheckingForUpdates.setText("No new Updates");
							}
						} else {

							ERROR_in_update = "NIC";
							lblCheckingForUpdates.setText("Error");
						}

					} catch (Exception ex) {

						ERROR_in_update = "Error: " + ex.getMessage();
						lblCheckingForUpdates.setText("Error");
					}
					try {
						update_check.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lblCheckingForUpdates.setVisible(false);
				}

			});

			update_check.start();

			return ERROR_in_update;
		}

		else {
			System.out.println("false doneeee");

			return null;
		}

	}
	
	
	public static void init_processing(){
		try {
		
		
		
		
		//RandomAccessFile
			File local_db = new File("Resources/db.killvir");
		
		//local_db.setReadOnly();
		//FileLock lock = null;
		//FileChannel channel = local_db.getChannel();
		
			//System.out.println(local_db.getAbsolutePath());
		
			//lock = channel.tryLock();
			Runtime.getRuntime().exec("cmd /c attrib +s +h +a  "+local_db.getAbsolutePath());	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Home();
		init_processing();
		update_check();

		// System.out.println(Updater.getLatestVersion())

	}
}
