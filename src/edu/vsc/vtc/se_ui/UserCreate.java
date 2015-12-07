/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package edu.vsc.vtc.se_ui;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

import edu.vsc.vtc.se.Session;

public class UserCreate extends JPanel implements ActionListener {
	static private final String newline = "\n";
	JButton fileButton, nextButton;
	JTextArea log;
	JFileChooser fc;
	private File[] FileSelections;
	private Session currentSession;

	public UserCreate() {
		super(new BorderLayout());

		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fc.setMultiSelectionEnabled(true);

		fileButton = new JButton("Select Files/Folders for session");
		fileButton.addActionListener(this);
		nextButton = new JButton("Next");
		nextButton.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(fileButton);
		buttonPanel.add(nextButton);

		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fileButton) {
			int returnVal = fc.showOpenDialog(UserCreate.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				FileSelections = fc.getSelectedFiles();
				for (int i = 0; i <= FileSelections.length; i++) {
					log.append(FileSelections[i].getPath() + " Selected." + newline);
				}
			} else {
				log.append("No files selected." + newline);
			}
			log.setCaretPosition(log.getDocument().getLength());
		} else if (e.getSource() == nextButton) {
			currentSession = new Session(new ArrayList<File>(Arrays.asList(FileSelections)));

			JFrame frame = new JFrame("Backup or Restore");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.add(new UserBackupRestore(currentSession));

			frame.pack();
			frame.setVisible(true);
		}
	}
}
