package edu.vsc.vtc.se;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

//Hey hey hey
/** 
 Using FileChannels is usually faster than using streams.
*/
public final class Data_Mngr 
{
  
	Data_Mngr()
	{
	  
	}
	
	Data_Mngr(File folder, File[] listofFiles, String directory)
	{
		
		if(listofFiles != null)
		{
			multipleFiles(listofFiles, directory);
		}
		if(listofFiles == null) //If a specific file from folder, this will be used to copy
		{
			singleFile(folder, directory);
		}
		
	    OutPut("Done.");
	}
  
  
	  private static void singleFile(File folder, String directory)
	  {
		  	OutPut("size of file is: " + folder.length());
		  	File dir = new File(directory + folder.getName());
			Data_Mngr test = new Data_Mngr();
			test.copyWithChannels(folder, dir);//, false);
			//test.copyWithStreams(folder, dir);
	  }
	  
	  private static void multipleFiles(File[] listofFiles, String directory)
	  {
		  for(int i = 0; i < listofFiles.length; i++)
			{
				if(listofFiles[i].isFile())
				{
					//String fileName = listofFiles[i].getName();
					
					String fileDest = directory + listofFiles[i].getName();
					File source = new File(listofFiles[i].getPath());
					File destination = new File(fileDest);
					OutPut("size of file is: " + source.length());
					//OutPut(destination);
					Data_Mngr test = new Data_Mngr();
					test.copyWithChannels(source, destination);//, false);
					//test.copyWithStreams(source, destination, false);
					//test.copyWithStreams(source, destination);
				}
				else if(listofFiles[i].isDirectory())
				{
				
					File newSource = new File(listofFiles[i].getPath());
					File[] listOfFiles = newSource.listFiles();
					String newDest = directory + listofFiles[i].getName() + "\\";
					new Data_Mngr(newSource, listOfFiles, newDest);
					OutPut(newSource);
					OutPut(newDest);
		
				}
			}
	  }
	  private void copyWithChannels(File aSourceFile, File aTargetFile)//Borrowed Code - modified by Earl
	  {
		  OutPut("Copying files with channels.");
		    destDirExists(aTargetFile.getParentFile());
		    FileChannel inputChannel = null;
		    FileChannel outputChannel = null;
		    try
		    {
		    	try 
		    	{
			        inputChannel = new FileInputStream(aSourceFile).getChannel();
			        OutPut("size of file in bytes is: " + inputChannel.size());
			        outputChannel = new FileOutputStream(aTargetFile).getChannel();
			        outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
			        OutPut("size of file after transfer in byte: " + outputChannel.size());
		    	
		    	}
		    	finally 
		    	{
		    		if(inputChannel != null)
		    		{
		    			inputChannel.close();
		    		}
		    		if(outputChannel != null)
		    		{
		    			outputChannel.close();
		    		}
			    	
			    	
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
	 
	  private void copyWithStreams(File aSourceFile, File aTargetFile)//Borrowed Code - modified by Earl
	  {
		    OutPut("Copying files with Streams.");
		    destDirExists(aTargetFile.getParentFile());
		    InputStream inputStream = null;
		    OutputStream outputStream = null;
		    try
		    {
		    	try 
		    	{
			        inputStream = new FileInputStream(aSourceFile);
			        outputStream = new FileOutputStream(aTargetFile);
			        byte[] buffer = new byte[1024];
			        int bufferRead;
			        while((bufferRead = inputStream.read(buffer)) > 0)
			        {
			        	outputStream.write(buffer, 0, bufferRead);
			        }
		    	
		    	}
		    	finally 
		    	{
			       
			    	inputStream.close();
			    	outputStream.close();
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
