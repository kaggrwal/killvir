package com.killvir;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.scene.web.HTMLEditor;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLEditorKit;

import java.awt.SystemColor;

public class UpdateInfo extends JFrame {

	private JEditorPane infoPane;

	private JScrollPane scp;

	private JButton ok;

	private JButton cancel;

	private JPanel pan1;

	private JPanel pan2;

	private String information;

	public UpdateInfo(String info) {

		information = "<html>" + info + "</html>";
		System.out.println(information);
		initComponents();
		infoPane.setText(information);
		infoPane.setEditable(false);

		// infoPane.setText("<html>   <title>  New version 2.0 </title>  <body>   <p>1.  Virus definitions added </p> <p>2.  UI improvments </p><p>3.  Minor Bug fixes</p>  </body></html> ");
	}

	private void initComponents() {

		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		this.setTitle("New Update Found");

		pan1 = new JPanel();

		pan1.setLayout(new BorderLayout());

		pan2 = new JPanel();
		pan2.setBackground(SystemColor.controlDkShadow);

		pan2.setLayout(new FlowLayout());

		HTMLEditorKit html_pane = new HTMLEditorKit();

		infoPane = new JEditorPane();
		;
		infoPane.setContentType("text/html");
		infoPane.setBackground(SystemColor.activeCaption);
		infoPane.setEditorKit(html_pane);

		scp = new JScrollPane();

		scp.setViewportView(infoPane);

		ok = new JButton("Update");

		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				update();

			}

		});

		cancel = new JButton("Cancel");

		cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				UpdateInfo.this.dispose();

			}

		});

		pan2.add(ok);

		pan2.add(cancel);

		pan1.add(pan2, BorderLayout.SOUTH);

		pan1.add(scp, BorderLayout.CENTER);

		getContentPane().add(pan1);

		pack();

		show();

		this.setSize(300, 200);

	}

	private void update()

	{

		String[] run = { "java", "-jar", "updater/update.jar" };

		try {

			Runtime.getRuntime().exec(run);

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		System.exit(0);

	}

}
