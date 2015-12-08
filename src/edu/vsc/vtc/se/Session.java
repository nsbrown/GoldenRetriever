/**
 * Session.java 
 * Copyright 2015, Nathan S. Brown
 * all rights reserved
 */
package edu.vsc.vtc.se;

import java.io.*;
import java.util.ArrayList;

/**
 * Session - A session for backing up or restoring.
 * 
 * @author Nathan S. Brown
 *
 */
public class Session {

	/**
	 * Creates a new session for backing up or restoring.
	 * 
	 * @param sessionFiles
	 *            the files and paths sent in to be worked on.
	 * @param destination
	 *            the folder to be copied to.
	 */
	public Session(ArrayList<File> sessionFiles, File destination) {
		_sessionFiles = sessionFiles;
		_destination = destination.getPath();
		repOK();
	}

	/**
	 * @return the files and paths sent in to be worked on.
	 */
	public ArrayList<File> getSessionFiles() {
		return _sessionFiles;
	}

	/**
	 * @return the folder to be copied to.
	 */
	public String getDestination() {
		return _destination;
	}

	/**
	 * @param isCompress
	 *            sets compression to true or false.
	 */
	public void setCompress(boolean isCompress) {
		_isCompress = isCompress;
	}

	/**
	 * @return the compression flag.
	 */
	public boolean getCompress() {
		return _isCompress;
	}

	/**
	 * @param isDecompress
	 *            sets decompression to true or false.
	 */
	public void setDecompress(boolean isDecompress) {
		_isDecompress = isDecompress;
	}

	/**
	 * @return the decompression flag.
	 */
	public boolean getDecompress() {
		return _isDecompress;
	}

	/**
	 * repOK for object creation validation.
	 */
	private void repOK() {
		assert _sessionFiles != null;
		for (int i = 0; i < _sessionFiles.size(); i++) {
			assert _sessionFiles.get(i) != null;
		}
		assert _destination != null;
		if (_isCompress) {
			assert _isDecompress == false;
		} else if (_isDecompress) {
			assert _isCompress == false;
		}
	}

	private String _destination; // never null
	private boolean _isCompress = false; // Must be false if _isDecompress is
											// true
	private boolean _isDecompress = false; // Must be false if _isCompress is
											// true
	private ArrayList<File> _sessionFiles; // never null
}
