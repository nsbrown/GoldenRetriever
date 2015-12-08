/**
 * UserHome.java
 * Copyright 2015, Nathan S. Brown
 * all rights reserved
 */
package edu.vsc.vtc.se_ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

import com.sun.javafx.tk.Toolkit;

/**
 * UserHome - Draws UI for the landing page.
 * 
 * @author Nathan S. Brown
 *
 */
public class UserHome extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize the UserHome UI. Creates all tools needed on page.
	 */
	public UserHome() {
		super(new BorderLayout());

		_createButton = new JButton("Create a new session");
		_createButton.addActionListener(this);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(_createButton);
		add(buttonPanel, BorderLayout.PAGE_START);
	}

	/**
	 * Event handler function for button presses.
	 * 
	 * @param e
	 *            the triggered event.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == _createButton) {
			JFrame frame = new JFrame("Create a session");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.add(new UserCreate());

			frame.pack();
			frame.setVisible(true);
		}
	}

	/**
	 * Main function for UI initialization.
	 * 
	 * @param args
	 *            arguments passed in (if any).
	 */
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

	/**
	 * Rep variables.
	 */
	private JButton _createButton;
}
