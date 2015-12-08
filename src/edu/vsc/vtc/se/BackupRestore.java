/**
 * BackupRestore.java  
 * Copyright 2015, Nathan S. Brown & Earl W. Bombard
 * all rights reserved
 */
package edu.vsc.vtc.se;

import java.io.File;

/**
 * BackupRestore - Handles initialization of backing up and restoring.
 * 
 * @author Nathan S. Brown & Earl Bombard
 *
 */
public class BackupRestore {

	/**
	 * Initializes a new backup or restore action.
	 * 
	 * @param session
	 *            the session being passed along for utilization.
	 */
	public BackupRestore(Session session) {
		for (int i = 0; i < session.getSessionFiles().size(); i++) {
			OutPut("Backing up");
			File sourceFile = session.getSessionFiles().get(i);
			File[] listOfFiles = sourceFile.listFiles();
			String destination = session.getDestination() + File.separator;
			if (session.getCompress()) {
				new Compress(sourceFile, listOfFiles, destination);
			} else if (session.getDecompress()) {
				new DeCompress(sourceFile, listOfFiles, destination);
			} else {
				new Data_Mngr(sourceFile, listOfFiles, destination);
			}
		}
	}

	/**
	 * Development output function.
	 * 
	 * @param outPutInfo
	 *            the info to be displayed.
	 */
	private static void OutPut(Object outPutInfo) {
		System.out.println(String.valueOf(outPutInfo));
	}
}
