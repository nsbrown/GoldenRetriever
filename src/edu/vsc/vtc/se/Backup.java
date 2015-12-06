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

		 	File folder = new File("C:\\Test1\\");
			//File[] listOfFiles = folder.listFiles();
			String directory = "C:\\Testing\\";
		 	//String directory = fileExtentionChanger(folder);
		 	//OutPut("Directory = " + directory);

		 	//folder = new File("C:\\Test1\\Test1.zip");
			//File[] listofFiles = folder.listFiles();
			//directory = "C:\\Test1\\";
			
			//backupItem newBackup = 
			double start = System.nanoTime();
			//new Compress(folder, directory);
			new Compress(folder, directory );//listOfFiles, directory);
			//new Data_Mngr(folder, listOfFiles, directory);
			
			
			//System.out.println("Time to Copy in seconds = " + ((System.nanoTime() - start))/1000000000);

	  }
	 public static String fileExtentionChanger(File folder) //Borrowed: http://stackoverflow.com/questions/30187581/java-getting-file-name-without-extension-from-a-folder
	 {													  //Modified by Earl
	    
	        File[] files = folder.listFiles();
	        String fileName = null;
	        int lastPeriodPos;
	        if(files == null)
	        {
	        	OutPut("Folder is Empty and will be skipped");
	        	fileName = folder.getPath();
	        	lastPeriodPos = fileName.lastIndexOf('.');
	        	if(lastPeriodPos > 0)
	        	{
	        		fileName = fileName.substring(0, lastPeriodPos) + ".zip";
	        		testing(fileName);
	        	}
	        	
	        }
	        if(files != null)
	        {
	        	for (int i = 0; i < files.length; i++) 
	        	{
		            if (files[i].isFile()) 
		            {
		                fileName = files[i].getPath();
		                lastPeriodPos = fileName.lastIndexOf('.');
		                if (lastPeriodPos > 0)
		                fileName = fileName.substring(0, lastPeriodPos) + ".zip";
		                testing(fileName);
		                OutPut("File name Path" + fileName); 
		                return fileName;
		            }
		            else if(files[i].isDirectory())
		            {
		            	fileExtentionChanger(files[i]);
		            }
		        }
	        }
	        
	        return fileName;
	        
	    }
	  public static void testing(String aTarget)
	  {
		  OutPut(aTarget);
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

