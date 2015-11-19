package edu.vsc.vtc.se;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

//Hey hey hey
/** 
 Using FileChannels is usually faster than using streams.
*/
public final class backupItem 
{
  
	backupItem()
	{
	  
	}
	
	backupItem(File folder, File[] listofFiles, String directory)
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
		  	File dir = new File(directory + folder.getName());
			backupItem test = new backupItem();
			test.copyWithChannels(folder, dir, false);
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
					//OutPut(destination);
					backupItem test = new backupItem();
					test.copyWithChannels(source, destination, false);
					//test.copyWithStreams(source, destination, false);
				}
				else if(listofFiles[i].isDirectory())
				{
				
					File newSource = new File(listofFiles[i].getPath());
					File[] listOfFiles = newSource.listFiles();
					String newDest = directory + listofFiles[i].getName() + "\\";
					new backupItem(newSource, listOfFiles, newDest);
					OutPut(newSource);
					OutPut(newDest);
		
				}
			}
	  }
	  private void copyWithChannels(File aSourceFile, File aTargetFile, boolean aAppend) //Borrowed Code - hit exactly what we needed, and was straight forward.
	  {
		    OutPut("Copying files with channels.");
		    destDirExists(aTargetFile.getParentFile());
		    FileChannel inputChannel = null;
		    FileChannel outputChannel = null;
		    FileInputStream inStream = null;
		    FileOutputStream outStream = null;
		    try
		    {
		    	try 
		    	{
			        inStream = new FileInputStream(aSourceFile);
			        inputChannel = inStream.getChannel();
			        outStream = new  FileOutputStream(aTargetFile, aAppend);        
			        outputChannel = outStream.getChannel();
			        long bytesTransferred = 0;
			        //defensive loop - there's usually only a single iteration :
			        while(bytesTransferred < inputChannel.size())
			        {
			        	bytesTransferred += inputChannel.transferTo(0, inputChannel.size(), outputChannel);
			        }
		    	}
		    	
		    	finally 
		    	{
			        //being defensive about closing all channels and streams 
			    	  if(inputChannel != null)
			    	  {
			    		  inputChannel.close();
			    	  }
			    	  if(outputChannel != null) 
			    	  {
			    		  outputChannel.close();
			    	  }
			    	  if(inStream != null)
			    	  {
			    		  inStream.close();
			    	  }
			    	  if(outStream != null)
			    	  {
			    		  outStream.close();
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