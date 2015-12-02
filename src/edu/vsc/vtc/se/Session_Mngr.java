package edu.vsc.vtc.se;

import java.io.File;
import java.util.ArrayList;

public class Session_Mngr {
	public Session_Mngr() {
		_sessions = new ArrayList<Session>();
		repOK();
	}
	
	public ArrayList<Session> getSessions() {
		return _sessions;
	}
	
	private void repOK() {
		for(int i = 0; i < _sessions.size(); i++) {
			ArrayList<Session_File> sessionFiles = _sessions.get(i).getSessionFiles();
			File singleFile = sessionFiles.get(i).getData();
			assert _sessions.get(i) != null;
		}
	}
	private ArrayList<Session> _sessions; // never null
}
