package edu.vsc.vtc.se;

import java.io.File;
import java.io.IOException;

public class Backup
{
	 public static void main(String [] aArgs) throws IOException
	  {
		 	File folder = new File("C:\\Test1");
			File[] listofFiles = folder.listFiles();
			String directory = "E:\\Test1\\";
			
			//backupItem newBackup = 
			double start = System.nanoTime();
			new backupItem(folder, listofFiles, directory);
			System.out.println("Time to Copy in seconds = " + ((System.nanoTime() - start))/1000000000);

	  }
}