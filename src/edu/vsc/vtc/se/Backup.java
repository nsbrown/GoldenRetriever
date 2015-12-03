package edu.vsc.vtc.se;

<<<<<<< HEAD
import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
=======
import java.io.File;
import java.io.IOException;
>>>>>>> origin/src

public class Backup
{
	 public static void main(String [] aArgs) throws IOException
	  {
<<<<<<< HEAD
		 	/**
		 	 * is going to take an array of file sources to send sources to backupItem class
		 	 * So needs to loop through array. or could be a list which ever is easier
		 	 */
		 /**	int done = 0;
		 	Scanner sourceReader = new Scanner(System.in);
		 	String source;
		 	//File[] arraySources;
		 	List<File> listSource = new ArrayList<File>();
		 	do
		 	{
		 		
			 	System.out.println("choose the file path you are looking for");
			 	source = sourceReader.next();
			 	File newSource = new File(source);
			 	listSource.add(newSource);
			 	System.out.println("are you done?");
		 	}while(!(source.equals("done")));
		 	
		 	*/
		 
		 	File folder = new File("C:\\Test1\\Test1.zip");
			//File[] listofFiles = folder.listFiles();
			String directory = "C:\\Test1\\";
			
			//backupItem newBackup = 
			double start = System.nanoTime();
			//new backupItem(folder, listofFiles, directory);
			new Compress(folder, directory);
			System.out.println("Time to Copy in seconds = " + ((System.nanoTime() - start))/1000000000);

	  }
	 private void listOfSources(List<File> sources)
	 {
		 for(int i = 0; i < sources.size(); i++ )
		 {
			 
		 }
	 }
}
=======
		 	File folder = new File("C:\\Test1");
			File[] listofFiles = folder.listFiles();
			String directory = "E:\\Test1\\";
			
			//backupItem newBackup = 
			new backupItem(folder, listofFiles, directory);
			
	  }
}
>>>>>>> origin/src
