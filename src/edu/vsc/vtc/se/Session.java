package edu.vsc.vtc.se;

import java.io.*;
import java.util.ArrayList;

public class Session {
	public Session(ArrayList<File> sessionFiles) {
		_sessionFiles = sessionFiles;
		repOK();
	}
	
	public ArrayList<File> getSessionFiles() {
		return _sessionFiles;
	}
	
	public File getLog() {
		return _log;
	}
	
	private void repOK() {
		for(int i = 0; i < _sessionFiles.size(); i++) {
			assert _sessionFiles.get(i) != null;
		}
		assert _log != null;
	}
	
	private File _log; // never null
	private ArrayList<File> _sessionFiles; // never null
}
