package edu.vsc.vtc.se;


import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;



public class Backup
{
	 public static void main(String [] aArgs) throws IOException
	  {

		 	File folder = new File("C:\\aTemp1\\");
			File[] listOfFiles = folder.listFiles();
			String directory = "C:\\Test\\"; //Testing" //+ File.separator;
		 	//String directory = fileExtentionChanger(folder);
		 	//OutPut("Directory = " + directory);

		 	//folder = new File("C:\\Test1\\Test1.zip");
			//File[] listofFiles = folder.listFiles();
			//directory = "C:\\Test1\\";
			
			//backupItem newBackup = 
			double start = System.nanoTime();
			//new Compress(folder, listOfFiles, directory);
			//fileExtentionChanger(folder, listOfFiles, directory );//listOfFiles, directory);
			//new Data_Mngr(folder, listOfFiles, directory);
			//new Compress(folder, listOfFiles, directory);
			new DeCompress(folder, listOfFiles, directory);
			
			
			System.out.println("Time to Copy in seconds = " + ((System.nanoTime() - start))/1000000000);

	  }
	 
	  private static void createPath(File targetDir) //If directory does not exist it creates it.
	  {
		  if(targetDir.exists() == false)
		  {
			  targetDir.mkdirs();
			  OutPut(targetDir.getPath());
		  }
	  }
	  private static void OutPut(Object outPutInfo)
	  {
		  System.out.println(String.valueOf(outPutInfo));
	  }
	 private void listOfSources(List<File> sources)
	 {
		 for(int i = 0; i < sources.size(); i++ )
		 {
			 
		 }
	 


		 	File folder = new File("C:\\Test1\\");
			File[] listofFiles = folder.listFiles();
			String directory = "C:\\Testing\\";
			
			//backupItem newBackup = 
			//new backupItem(folder, listofFiles, directory);
			
	  }
}

