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
		unZip(folder, directory);
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
	    			destDirExists(file.getParentFile());
	    			OutPut("The path of file: " + file.getPath());
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
