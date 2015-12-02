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
import java.util.zip.ZipOutputStream;

public class Compress 
{
	public Compress(File folder, String directory) 
	{
		copyWithZip(folder, directory);
	}
	
	private void comppressFile(File aSourceFile, String aTargetFile)
	{
		FileInputStream sourceFile = null;
		FileOutputStream targetFile = null;
		DeflaterOutputStream fileOut = null;
		
		 try
		    {
		    	try 
		    	{
		    		sourceFile = new FileInputStream(aSourceFile);
		    		targetFile = new FileOutputStream(aTargetFile);
		    		fileOut    = new DeflaterOutputStream(targetFile);
		    		
			        byte[] buffer = new byte[1024];
			        int bufferRead;
			        while((bufferRead = sourceFile.read(buffer)) > 0)
			        {
			        	fileOut.write(buffer, 0, bufferRead);
			        	fileOut.flush();
			        }
		    	
		    	}
		    	finally 
		    	{
		    		sourceFile.close();
		    		//targetFile.close();
		    		fileOut.close();
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
	
	private void decompressFile(File aSourceFile, String aTargetFile)
	{
		FileInputStream sourceFile = null;
		FileOutputStream targetFile = null;
		InflaterInputStream fileIn = null;
		
		 try
		    {
		    	try 
		    	{
		    		sourceFile = new FileInputStream(aSourceFile);
		    		targetFile = new FileOutputStream(aTargetFile);
		    		fileIn    = new InflaterInputStream(sourceFile);
		    		
			        byte[] buffer = new byte[1024];
			        int bufferRead;
			        while((bufferRead = sourceFile.read(buffer)) > 0)
			        {
			        	targetFile.write(buffer, 0, bufferRead);
			        	targetFile.flush();
			        }
		    	
		    	}
		    	finally 
		    	{
		    		sourceFile.close();
		    		fileIn.close();
		    		targetFile.close();
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
	private void copyWithZip(File aSourceFile, String aTargetFile)//Borrowed Code - modified by Earl
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
