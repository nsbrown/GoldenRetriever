package edu.vsc.vtc.se;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compress 
{

	public Compress() 
	{
		
	}
	public Compress(File folder, File[] listOfFiles, String targetFile)
	{
		if(listOfFiles != null)
		{
			targetFile = targetFile + folder.getName() + File.separator;
			fileExtentionChanger(listOfFiles, targetFile);
		}
		else
		{
			OutPut(folder.getParent());
			OutPut(targetFile);
			zip(folder, targetFile);
		}
		
	}

	private void fileExtentionChanger(File[] listOfFiles, String directory) 
	 {													 
		 int lastPeriodPos;
		 for(int i = 0; i < listOfFiles.length; i++)
			{
				if(listOfFiles[i].isFile())
				{
					
					String fileDest = directory + File.separator + listOfFiles[i].getName();
					File source = new File(listOfFiles[i].getPath());
					lastPeriodPos = fileDest.lastIndexOf('.');
					if (lastPeriodPos > 0)
					{
		                fileDest = fileDest.substring(0, lastPeriodPos) + ".zip";
					}
					Compress test = new Compress();
					test.zip(source, fileDest);
					
				}
				else if(listOfFiles[i].isDirectory())
				{
					File newSource = new File(listOfFiles[i].getPath());
					File[] listofFiles = newSource.listFiles();
					new Compress(newSource, listofFiles, directory);
		
				}
			}
	        
	    }

	
	private void zip(File aSourceFile, String aTargetFile)//Borrowed Code - modified by Earl
	  {
		    OutPut("Compressing Files.");
		    OutPut("aSourceFile: " + aSourceFile);
		    OutPut("aTargetFile: " + aTargetFile);
		    createPath(new File(aTargetFile).getParentFile());
		    
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

	
	  private void createPath(File targetDir) //If directory does not exist it creates it.
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

