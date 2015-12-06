package edu.vsc.vtc.se;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Compress 
{
	public Compress(File folder, String directory) 
	{
		zip(folder, directory);
	}
	
	public Compress() 
	{
		// TODO Auto-generated constructor stub
	}
	public Compress(File folder, File[] listOfFiles, String directory)
	{
		if(listOfFiles != null)
		{
			fileExtentionChanger(folder, listOfFiles, directory);
		}
		else
		{
			OutPut(folder.getParent());
			OutPut(directory);
			zip(folder, directory);
		}
		
	}

	private void fileExtentionChanger(File folder,File[] listOfFiles, String directory) //Borrowed: http://stackoverflow.com/questions/30187581/java-getting-file-name-without-extension-from-a-folder
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
					String destination = fileDest;
					OutPut("source: " + source);
					OutPut("destination: " + destination);
					Compress test = new Compress();
					OutPut("This is in the multiple method: " + source.getPath());
					test.zip(source, destination);
					//OutPut("size of file is: " + source.length());
					//Data_Mngr test = new Data_Mngr();
					//OutPut("This is in the multiple method: " + source.getPath());
					//test.copyWithChannels(source, destination);
				}
				else if(listOfFiles[i].isDirectory())
				{
					File newSource = new File(listOfFiles[i].getPath());
					File[] listofFiles = newSource.listFiles();
					File parent = new File(listOfFiles[i].getParent());
					OutPut("The Source: " + folder);
					String newDest = directory + parent.getName() + File.separator; /*File.pathSeparator - listOfFiles[i].getName()*/;
					//File newSource = new File(listOfFiles[i].getPath());
					//File[] listofFiles = newSource.listFiles();
					//String newDest = directory + listOfFiles[i].getName() + File.separator; /*File.pathSeparator*/;
					//new Data_Mngr(newSource, listOfFiles, newDest);
					fileExtentionChanger(newSource,listofFiles,newDest);
					OutPut(newSource);
					OutPut(newDest);
		
				}
			}
	        
	    }

	private void unZip(File aZipFile, String aTargetFile)
	{
		OutPut("Decompressing Files.");
		
		File destinationFile = new File(aTargetFile);
	    destDirExists(destinationFile.getParentFile());
	    
	    FileOutputStream fileOutputStream = null;
        ZipInputStream zipInputStream = null;
        FileInputStream fileInputStream = null;
        ZipEntry zipEntry = null;
        
	    try
	    {
	    	try 
	    	{
	    		fileInputStream = new FileInputStream(aZipFile);
	    		zipInputStream = new ZipInputStream(fileInputStream);
	    		zipEntry = zipInputStream.getNextEntry();
	    		
	    		while(zipEntry != null)
	    		{
	    				
	    			
	    			File file = new File(aTargetFile + File.separator + zipEntry.getName());
	    			OutPut("The path of file Before make directory: " + file.getPath());
	    			destDirExists(file.getParentFile());
	    			
	    			fileOutputStream = new FileOutputStream(file);
	    			
	    			
	    			byte[] buffer = new byte[2048];
	    			int bufferRead;
	    			while((bufferRead = zipInputStream.read(buffer)) > 0)
	    			{
	    				fileOutputStream.write(buffer, 0, bufferRead);
	    			}
	    			fileOutputStream.close();
	    			zipEntry = zipInputStream.getNextEntry();
	    		}
	    	}
	    	finally 
	    	{
	    		fileInputStream.close();
	    		zipInputStream.close();
	    	}
	    }
	    
	    
	    
	    catch (FileNotFoundException ex)
	    {
	    	OutPut("File not found: " + ex);
	    }
	    catch (IOException ex)
	    {
	    	OutPut(ex);
	    }
	  

	}
	
	
	
	private void zip(File aSourceFile, String aTargetFile)//Borrowed Code - modified by Earl
	  {
		    OutPut("Compressing Files.");
		    OutPut("aSourceFile: " + aSourceFile);
		    OutPut("aTargetFile: " + aTargetFile);
		    destDirExists(new File(aTargetFile).getParentFile());
		    //destDirExists(aTargetFile.getParentFile());
		    
		    FileOutputStream fileOutputStream = null;
            ZipOutputStream zipOutputStream = null;
            FileInputStream fileInputStream = null;
            ZipEntry zipEntry = null;
            
		    try
		    {
		    	try 
		    	{
		    		fileOutputStream = new FileOutputStream(aTargetFile);
		    		zipOutputStream = new ZipOutputStream(fileOutputStream);
		    		zipEntry = new ZipEntry(aSourceFile.getName());
		    		zipOutputStream.putNextEntry(zipEntry);
		    		fileInputStream = new FileInputStream(aSourceFile);
		    		
			        byte[] buffer = new byte[2048];
			        int bufferRead;
			        while((bufferRead = fileInputStream.read(buffer)) > 0)
			        {
			        	zipOutputStream.write(buffer, 0, bufferRead);
			        }
		    	
		    	}
		    	finally 
		    	{
		    		zipOutputStream.closeEntry();
		   		    zipOutputStream.close();
		            fileInputStream.close();
		            fileOutputStream.close();
		    	}
		    }
		    catch (FileNotFoundException ex)
		    {
		    	OutPut("File not found: " + ex);
		    }
		    catch (IOException ex)
		    {
		    	OutPut(ex);
		    }
		  
	 }

	
	  private void destDirExists(File targetDir) //If directory does not exist it creates it.
	  {
		  if(targetDir.exists() == false)
		  {
			  targetDir.mkdirs();
		  }
	  }
	  
	  private static void OutPut(Object outPutInfo)
	  {
		  System.out.println(String.valueOf(outPutInfo));
	  }
}

