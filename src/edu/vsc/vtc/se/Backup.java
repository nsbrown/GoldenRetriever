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
			File[] listOfFiles = folder.listFiles();
			String directory = "C:\\"; //Testing" //+ File.separator;
		 	//String directory = fileExtentionChanger(folder);
		 	//OutPut("Directory = " + directory);

		 	//folder = new File("C:\\Test1\\Test1.zip");
			//File[] listofFiles = folder.listFiles();
			//directory = "C:\\Test1\\";
			
			//backupItem newBackup = 
			double start = System.nanoTime();
			//new Compress(folder, directory);
			fileExtentionChanger(folder, listOfFiles, directory );//listOfFiles, directory);
			//new Data_Mngr(folder, listOfFiles, directory);
			
			
			//System.out.println("Time to Copy in seconds = " + ((System.nanoTime() - start))/1000000000);

	  }
	 public static void fileExtentionChanger(File aSource, File[] listOfFiles, String directory) //Borrowed: http://stackoverflow.com/questions/30187581/java-getting-file-name-without-extension-from-a-folder
	 {													  //Modified by Earl
		 int lastPeriodPos;
		 String fileName = null;
	     String newFileName = null;
	     
		 for(int i = 0; i < listOfFiles.length; i++)
			{
				if(listOfFiles[i].isFile())
				{
					//lastPeriodPos = .lastIndexOf('.');
	                File parent = new File(listOfFiles[i].getParent());
					String fileDest = directory + parent.getName() + File.separator + listOfFiles[i].getName();
					OutPut("parent: " + listOfFiles[i].getParent());
					OutPut("parents Name: " + parent.getName());
					
					lastPeriodPos = fileDest.lastIndexOf('.');
					if (lastPeriodPos > 0)
					{
		                fileDest = fileDest.substring(0, lastPeriodPos) + ".zip";
					}
					File source = new File(listOfFiles[i].getPath());
					File destination = new File(fileDest);
					OutPut("source: " + source);
					OutPut("destination: " + destination);
					//OutPut("size of file is: " + source.length());
					//Data_Mngr test = new Data_Mngr();
					//OutPut("This is in the multiple method: " + source.getPath());
					//test.copyWithChannels(source, destination);
				}
				else if(listOfFiles[i].isDirectory())
				{
				
					//File newSource = new File(listOfFiles[i].getPath());
					//File[] listofFiles = newSource.listFiles();
					//String newDest = directory + listOfFiles[i].getName() + File.separator; /*File.pathSeparator*/;
					//new Data_Mngr(newSource, listOfFiles, newDest);
					//OutPut(newSource);
					//OutPut(newDest);
		
				}
			}
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

