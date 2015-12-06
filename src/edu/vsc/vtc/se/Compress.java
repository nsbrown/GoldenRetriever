package edu.vsc.vtc.se;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Compress 
{
	public Compress(File folder, String directory) 
	{
		//zip(folder, directory);
		fileExtentionChanger(folder, directory);
	}
	
	 private void fileExtentionChanger(File folder, String directory) //Borrowed: http://stackoverflow.com/questions/30187581/java-getting-file-name-without-extension-from-a-folder
	 {													  //Modified by Earl
	    
	        File[] files = folder.listFiles();
	        String fileName = null;
	        int lastPeriodPos;
	        if(files == null)
	        {
	        	OutPut("Folder is Empty and will be skipped");
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
		               
		                zip(folder, fileName);
		            }
		            else if(files[i].isDirectory())
		            {
		            	
		            	//fileExtentionChanger(files[i]);
		            }
		        }
	        }
	      //  return fileName;
	        
	    }

private void zip(File aSourceFile, String aTargetFile)//Borrowed Code - modified by Earl
	  {
		    OutPut("Compressing Files.");
		    OutPut("target directory: " + new File(aTargetFile).getPath());
		    File dir = new File(aTargetFile);
		    OutPut("aTarg" + aTargetFile);
		    File Dir = new File(aTargetFile);
		    createPath(Dir);
		    
		    //destDirExists(aTargetFile.getParentFile());
		   // aTargetFile = fileExtentionChanger(aSourceFile);
		   // OutPut("aTargetFile = " + aTargetFile);
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

	
	

	/*
	* This unzips files
	*/
	private void unZip(File aZipFile, String aTargetFile)
	{
		OutPut("Decompressing Files.");
		
		File destinationFile = new File(aTargetFile);
	    createPath(destinationFile.getParentFile());
	    
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
	    			if(zipEntry.isDirectory())
	    			{
	    				OutPut("here");
	    				createPath(new File(aTargetFile + File.separator + zipEntry.getName()));
	    				zipEntry = zipInputStream.getNextEntry();
	    			}
	    			
	    			File file = new File(aTargetFile + File.separator + zipEntry.getName()); //The path for out Directory
	    			OutPut("The path of file Before make directory: " + file.getPath());
	    			createPath(file.getParentFile()); //Creates the desired File 
	    			
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

	
	 public static void testing(String aTarget)
	 {
		  OutPut(aTarget);

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
