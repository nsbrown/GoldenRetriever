/**
 * UserCompress.java
 * Copyright 2015, Nathan S. Brown
 * all rights reserved
 */
package edu.vsc.vtc.se_ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import edu.vsc.vtc.se.Session;

/**
 * UserCompress - Draws UI for compression actions.
 * 
 * @author Nathan S. Brown
 *
 */
public class UserCompress extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize the UserCompress UI. Creates all tools needed on page.
	 * 
	 * @session session the session being passed along for utilization.
	 */
	public UserCompress(Session thisSession) {
		super(new BorderLayout());

		_currentSession = thisSession;

		_log = new JTextArea(5, 20);
		_log.setMargin(new Insets(5, 5, 5, 5));
		_log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(_log);

		_compressButton = new JButton("Compress Session");
		_compressButton.addActionListener(this);
		_decompressButton = new JButton("Decompress session");
		_decompressButton.addActionListener(this);
		_plainButton = new JButton("No compression");
		_plainButton.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(_compressButton);
		buttonPanel.add(_decompressButton);
		buttonPanel.add(_plainButton);

		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	/**
	 * Event handler function for button presses.
	 * 
	 * @param e
	 *            the triggered event.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == _compressButton) {
			_currentSession.setCompress(true);
		} else if (e.getSource() == _decompressButton) {
			_currentSession.setDecompress(true);
		}
		JFrame frame = new JFrame("Backup & Restore");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new UserBackupRestore(_currentSession));

		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Rep variables.
	 */
	private JButton _compressButton, _decompressButton, _plainButton;
	private JTextArea _log;
	private Session _currentSession;
}
