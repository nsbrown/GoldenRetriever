package edu.vsc.vtc.se_ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import edu.vsc.vtc.se.Session;

public class UserHome extends JPanel implements ActionListener {
	static private final String newline = "\n";
	JButton createButton, loadButton;

	public UserHome() {
		super(new BorderLayout());

		createButton = new JButton("Create a new session");
		createButton.addActionListener(this);
		loadButton = new JButton("Load a session");
		loadButton.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(createButton);
		buttonPanel.add(loadButton);

		add(buttonPanel, BorderLayout.PAGE_START);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createButton) {
			JFrame frame = new JFrame("Create a session");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.add(new UserCreate());

			frame.pack();
			frame.setVisible(true);
		} else if (e.getSource() == loadButton) {
			JFrame frame = new JFrame("Saved sessions");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.add(new UserLoad());

			frame.pack();
			frame.setVisible(true);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);

				JFrame frame = new JFrame("Golden Retriever");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(new UserHome());

				frame.pack();
				frame.setVisible(true);
			}
		});
	}

}
