package edu.vsc.vtc.se;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Data_Mngr - Handles the Non-compressing backup of the given directories/Files
 * 
 * @author Earl Bombard
 *
 */
public class Data_Mngr {

	public Data_Mngr() {

	}
	/**
	 * Initializes the copying of directories/Files using channels(For a backup or restore).
	 * 
	 * @param sourceFile
	 *            The source File or source directory to be copied.
	 * @param listofFiles
	 * 			  The list of files within a directory - if null directory is empty.
	 * @param targetFile
	 * 			  The destination path for the copies to be stored. Will be created if not existing.
	 */
	public Data_Mngr(File sourceFile, File[] listofFiles, String targetFile) {

		if (listofFiles != null) {
			OutPut("targetFile" + targetFile);
			OutPut("new destination: " + targetFile + sourceFile.getName());
			targetFile = targetFile + sourceFile.getName() + File.separator;
			multipleFiles(listofFiles, targetFile);
		}
		if (listofFiles == null) // If a specific file from folder, this will be
									// used to copy
		{
			singleFile(sourceFile, targetFile);
		}

		OutPut("Done.");
	}

	/**
	 * copying of a sing specific files.
	 * 
	 * @param folder
	 *            The source File or source directory to be copied.
	 * @param directory
	 * 			  The list of files within a directory - if null directory is empty.
	 */
	private static void singleFile(File folder, String directory) {
		File dir = new File(directory + folder.getName());
		Data_Mngr test = new Data_Mngr();
		test.copyWithChannels(folder, dir);
	}

	private static void multipleFiles(File[] listofFiles, String directory) {
		for (int i = 0; i < listofFiles.length; i++) {
			if (listofFiles[i].isFile()) {

				String fileDest = directory + File.separator + listofFiles[i].getName();
				File source = new File(listofFiles[i].getPath());
				File destination = new File(fileDest);
				Data_Mngr test = new Data_Mngr();
				test.copyWithChannels(source, destination);

			} else if (listofFiles[i].isDirectory()) {

				File newSource = new File(listofFiles[i].getPath());
				File[] listOfFiles = newSource.listFiles();
				new Data_Mngr(newSource, listOfFiles, directory);

			}
		}
	}

	@SuppressWarnings("resource")
	private void copyWithChannels(File aSourceFile, File aTargetFile)// Borrowed
																		// Code
																		// -
																		// modified
																		// by
																		// Earl
	{
		OutPut("Copying files with channels.");
		creatPath(aTargetFile.getParentFile());
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			try {
				inputChannel = new FileInputStream(aSourceFile).getChannel();
				outputChannel = new FileOutputStream(aTargetFile).getChannel();
				outputChannel.transferFrom(inputChannel, 0, inputChannel.size());

			} finally {
				if (inputChannel != null) {
					inputChannel.close();
				}
				if (outputChannel != null) {
					outputChannel.close();
				}

			}
		} catch (FileNotFoundException ex) {
			OutPut("File not found: " + ex);
		} catch (IOException ex) {
			OutPut(ex.toString());
		}

	}

	@SuppressWarnings("unused")
	private void copyWithStreams(File aSourceFile, File aTargetFile)// Borrowed
																	// Code -
																	// modified
																	// by Earl
	{
		OutPut("Copying files with Streams.");
		creatPath(aTargetFile.getParentFile());
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			try {
				inputStream = new FileInputStream(aSourceFile);
				outputStream = new FileOutputStream(aTargetFile);
				byte[] buffer = new byte[1024];
				int bufferRead;
				while ((bufferRead = inputStream.read(buffer)) > 0) {
					outputStream.write(buffer, 0, bufferRead);
				}

			} finally {

				inputStream.close();
				outputStream.close();
			}
		} catch (FileNotFoundException ex) {
			OutPut("File not found: " + ex);
		} catch (IOException ex) {
			OutPut(ex.toString());
		}

	}

	private void creatPath(File targetDir) {
		if (targetDir.exists() == false) {
			targetDir.mkdirs();
		}
	}

	private static void OutPut(Object outPutInfo) {
		System.out.println(String.valueOf(outPutInfo));
	}
}
