/**
 * UserCreate.java
 * Copyright 2015, Nathan S. Brown
 * all rights reserved
 */
package edu.vsc.vtc.se_ui;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import edu.vsc.vtc.se.Session;

/**
 * UserCreate - Draws UI for creating a session.
 * 
 * @author Nathan S. Brown
 *
 */
public class UserCreate extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize the UserCreate UI. Creates all tools needed on page.
	 */
	public UserCreate() {
		super(new BorderLayout());

		_log = new JTextArea(5, 20);
		_log.setMargin(new Insets(5, 5, 5, 5));
		_log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(_log);

		_fc = new JFileChooser();
		_fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		_fc.setMultiSelectionEnabled(true);

		_dc = new JFileChooser();
		_dc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		_fileButton = new JButton("Select Files/Folders for session");
		_fileButton.addActionListener(this);
		_destButton = new JButton("Select a Destination folder");
		_destButton.addActionListener(this);
		_nextButton = new JButton("Next");
		_nextButton.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(_fileButton);
		buttonPanel.add(_destButton);
		buttonPanel.add(_nextButton);

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
		if (e.getSource() == _fileButton) {
			int returnVal = _fc.showOpenDialog(UserCreate.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				_fileSelections = _fc.getSelectedFiles();
				for (int i = 0; i <= _fileSelections.length; i++) {
					_log.append(_fileSelections[i].getPath() + " added to session." + _newline);
				}
			} else {
				_log.append("No files selected." + _newline);
			}
			_log.setCaretPosition(_log.getDocument().getLength());
		} else if (e.getSource() == _nextButton) {
			if (_destination != null || _fileSelections != null) {
				_currentSession = new Session(new ArrayList<File>(Arrays.asList(_fileSelections)), _destination);

				JFrame frame = new JFrame("Compression");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(new UserCompress(_currentSession));

				frame.pack();
				frame.setVisible(true);
			} else {
				_log.append("Please select files for session and/or destination folder");
			}
		} else if (e.getSource() == _destButton) {
			int returnVal = _dc.showOpenDialog(UserCreate.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				_destination = _dc.getSelectedFile();
				_log.append("DESTINATION FOLDER: " + _destination.getPath() + _newline);
			} else {
				_log.append("No destination selected." + _newline);
			}
		}
	}

	/**
	 * Rep variables.
	 */
	static private final String _newline = "\n";
	private JButton _fileButton, _destButton, _nextButton;
	private JTextArea _log;
	private JFileChooser _fc;
	private JFileChooser _dc;
	private File[] _fileSelections;
	private File _destination;
	private Session _currentSession;
}
