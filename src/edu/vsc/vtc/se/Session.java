package edu.vsc.vtc.se;

import java.io.*;
import java.util.ArrayList;

public class Session {
	public Session(ArrayList<Session_File> sessionFiles, int sessionID) {
		_sessionFiles = sessionFiles;
		_log = new File("../../../../logs/session" + sessionID + ".log");
		repOK();
	}
	
	public ArrayList<Session_File> getSessionFiles() {
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
	private ArrayList<Session_File> _sessionFiles; // never null
}
