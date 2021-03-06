package edu.vsc.vtc.se;

import java.io.*;
import java.util.zip.*;

/**
 * DeCompress - Handles the Decompressing of the given directories/Files
 * 
 * @author Earl Bombard
 *
 */
public class DeCompress {
	/**
	 * Initializes the restoring of directories/Files that are compressed.
	 * 
	 * @param aZipFile
	 *            The zip source File or source directory to be copied.
	 * @param listOfFiles
	 * 			  The list of files within a directory - if null directory is empty.
	 * @param aTargetFile
	 * 			  The destination path for the copies to be stored. Will be created if not existing.
	 */
	public DeCompress(File aZipFile, File[] listOfFiles, String aTargetFile) {

		if (listOfFiles != null) {
			aTargetFile = aTargetFile + aZipFile.getName() + File.separator;
			multipleFiles(aZipFile, listOfFiles, aTargetFile);
		} else {
			unZip(aZipFile, aTargetFile);
		}

	}

	public DeCompress() {

	}

	private static void multipleFiles(File aZipFile, File[] listofFiles, String directory) {
		for (int i = 0; i < listofFiles.length; i++) {
			if (listofFiles[i].isFile()) {

				String fileDest = directory + File.separator;
				File source = new File(listofFiles[i].getPath());

				DeCompress test = new DeCompress();
				test.unZip(source, fileDest);
			} else if (listofFiles[i].isDirectory()) {

				File newSource = new File(listofFiles[i].getPath());
				File[] listOfFiles = newSource.listFiles();

				new DeCompress(newSource, listOfFiles, directory);

			}
		}
	}

	private void unZip(File aZipFile, String aTargetFile) {
		OutPut("Decompressing Files.");

		File destinationFile = new File(aTargetFile);
		createPath(destinationFile.getParentFile());

		FileOutputStream fileOutputStream = null;
		ZipInputStream zipInputStream = null;
		FileInputStream fileInputStream = null;
		ZipEntry zipEntry = null;

		try {
			try {
				fileInputStream = new FileInputStream(aZipFile);
				zipInputStream = new ZipInputStream(fileInputStream);
				zipEntry = zipInputStream.getNextEntry();

				while (zipEntry != null) {
					if (zipEntry.isDirectory()) {
						OutPut("here");
						createPath(new File(aTargetFile + File.separator + zipEntry.getName()));
						zipEntry = zipInputStream.getNextEntry();
					}

					File file = new File(aTargetFile + File.separator + zipEntry.getName()); // The
																								// path
																								// for
																								// out
																								// Directory
					OutPut("The path of file Before make directory: " + file.getPath());
					createPath(file.getParentFile());

					fileOutputStream = new FileOutputStream(file);

					byte[] buffer = new byte[2048];
					int bufferRead;
					while ((bufferRead = zipInputStream.read(buffer)) > 0) {
						fileOutputStream.write(buffer, 0, bufferRead);
					}
					fileOutputStream.close();
					zipEntry = zipInputStream.getNextEntry();
				}
			} finally {
				fileInputStream.close();
				zipInputStream.close();
			}
		}

		catch (FileNotFoundException ex) {
			OutPut("File not found: " + ex);
		} catch (IOException ex) {
			OutPut(ex.toString());
		}

	}

	private void createPath(File targetDir) {
		if (targetDir.exists() == false) {
			targetDir.mkdirs();
		}
	}

	private static void OutPut(Object outPutInfo) {
		System.out.println(String.valueOf(outPutInfo));
	}
}
