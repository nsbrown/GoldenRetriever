package edu.vsc.vtc.se;

import java.io.*;
import java.util.ArrayList;

public class Session {
	public Session(ArrayList<File> sessionFiles, String destination) {
		_sessionFiles = sessionFiles;
		_destination = destination;
		_sessionID++;
		_log = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents"
				+ System.getProperty("file.separator") + "Software" + System.getProperty("file.separator")
				+ "backup_workspace" + System.getProperty("file.separator") + "GoldenRetriever"
				+ System.getProperty("file.separator") + "log" + System.getProperty("file.separator") + "session"
				+ _sessionID + ".txt");
		try {
			_log.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repOK();
	}

	public ArrayList<File> getSessionFiles() {
		return _sessionFiles;
	}

	public File getLog() {
		return _log;
	}
	
	public String getDestination() {
		return _destination;
	}
	
	public void setCompress(boolean isCompress)
	{
		_isCompress = isCompress;
	}
	
	public boolean getCompress()
	{
		return _isCompress;
	}
	
	public void setDecompress(boolean isDecompress)
	{
		_isDecompress = isDecompress;
	}
	
	public boolean getDecompress()
	{
		return _isDecompress;
	}

	private void repOK() {
		for (int i = 0; i < _sessionFiles.size(); i++) {
			assert _sessionFiles.get(i) != null;
		}
		assert _log != null;
		assert _destination != null;
		if (_isCompress) {
			assert _isDecompress == false;
		}
		else if (_isDecompress) {
			assert _isCompress == false;
		}
	}

	private String _destination;
	private boolean _isCompress = false;
	private boolean _isDecompress = false;
	private int _sessionID;
	private File _log; // never null
	private ArrayList<File> _sessionFiles; // never null
}
