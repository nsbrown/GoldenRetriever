/**
 * UserBackupRestore.java 
 * Copyright 2015, Nathan S. Brown
 * all rights reserved
 */
package edu.vsc.vtc.se_ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import edu.vsc.vtc.se.*;

/**
 * UserBackupRestore - Draws UI for backup and restore actions.
 * 
 * @author Nathan S. Brown
 *
 */
public class UserBackupRestore extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize the UserBackupRestore UI. Creates all tools needed on page.
	 * 
	 * @param session
	 *            the session being passed along for utilization.
	 */
	public UserBackupRestore(Session thisSession) {
		super(new BorderLayout());

		_currentSession = thisSession;

		_log = new JTextArea(5, 20);
		_log.setMargin(new Insets(5, 5, 5, 5));
		_log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(_log);

		_backupRestoreButton = new JButton("Backup or restore!");
		_backupRestoreButton.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(_backupRestoreButton);

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
		if (e.getSource() == _backupRestoreButton) {
			new BackupRestore(_currentSession);
			_log.append("Action completed.");
		}
	}

	/**
	 * Rep variables.
	 */
	private JButton _backupRestoreButton;
	private JTextArea _log;
	private Session _currentSession;
}
