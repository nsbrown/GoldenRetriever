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
			assert _sessions.get(i) != null;
		}
	}
	private ArrayList<Session> _sessions; // never null
}
