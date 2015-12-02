package edu.vsc.vtc.se;

import java.io.File;

public class Session_File {
	Session_File(File data) {
		_data = data;
		_encryptable = false; // Encryption flag is defaulted to false.
		repOK();
	}
	
	public File getData() {
		return _data;
	}
	
	public boolean isEncryptable() {
		return _encryptable;
	}
	
	public void setEncryptable(boolean encryptable) {
		_encryptable = encryptable;
	}
	
	private void repOK() {
		assert _data != null;
	}
	
	private File _data; // never null
	private boolean _encryptable;
}
