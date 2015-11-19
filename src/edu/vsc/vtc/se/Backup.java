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
			new backupItem(folder, listofFiles, directory);
			
	  }
}