package com.killvir;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Preferences extends JFrame {
	
	java.util.prefs.Preferences settings;
	private boolean STARTUP = true, USB_INSPECTOR = true, UPDATES = true;
	JCheckBox chckbx_RunUsbInspection = new JCheckBox("   Run background USB inspection");
	JCheckBox chckbxNewCheckBox_Update = new JCheckBox("   Automatically Check for Updates");
	JCheckBox chckbxNewCheckBox_Startup = new JCheckBox("   Run at Windows Startup");
	public static double VERSION_NO = 1.0;
	
	public Preferences() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				saveSettings();
				Preferences.this.dispose();
			}
		});
		btnApply.setBounds(190, 158, 89, 23);
		getContentPane().add(btnApply);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Preferences.this.dispose();
			}
		});
		btnCancel.setBounds(307, 158, 89, 23);
		getContentPane().add(btnCancel);
		chckbx_RunUsbInspection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbx_RunUsbInspection.isSelected()){
					USB_INSPECTOR = true;
				}
				else if(!chckbx_RunUsbInspection.isSelected()){
					USB_INSPECTOR = false;
				}
			}
		});
		chckbx_RunUsbInspection.setSelected(true);
		
		
		
		chckbx_RunUsbInspection.setBackground(Color.LIGHT_GRAY);
		chckbx_RunUsbInspection.setBounds(32, 55, 203, 23);
		getContentPane().add(chckbx_RunUsbInspection);
		
		
		chckbxNewCheckBox_Startup.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			
				if(chckbxNewCheckBox_Startup.isSelected()){
					STARTUP = true;
				}
				else if(!chckbx_RunUsbInspection.isSelected()){
					STARTUP = false;
				}
			}
		});
		chckbxNewCheckBox_Startup.setSelected(true);
		chckbxNewCheckBox_Startup.setBackground(Color.LIGHT_GRAY);
		chckbxNewCheckBox_Startup.setBounds(32, 76, 183, 23);
		getContentPane().add(chckbxNewCheckBox_Startup);
		chckbxNewCheckBox_Update.setSelected(true);
		chckbxNewCheckBox_Update.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxNewCheckBox_Update.isSelected()){
					UPDATES = true;
				}
				else if(!chckbxNewCheckBox_Update.isSelected()){
					UPDATES = false;
				}
			}
		});
		
		
		
		chckbxNewCheckBox_Update.setBackground(Color.LIGHT_GRAY);
		chckbxNewCheckBox_Update.setBounds(32, 97, 232, 23);
		getContentPane().add(chckbxNewCheckBox_Update);
		this.setVisible(true);
	}
	
	
	public void saveSettings(){
		
		settings = java.util.prefs.Preferences.userNodeForPackage(this.getClass())
;		
		settings.putBoolean("Startup", STARTUP);
		settings.putBoolean("Update", UPDATES);
		settings.putBoolean("USB", USB_INSPECTOR);
		settings.putDouble("VERSION", VERSION_NO);
		
		
	}
	
}
