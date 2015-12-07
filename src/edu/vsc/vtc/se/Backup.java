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
	
	 public Backup(Session session)
	 {
		 for(int i = 0; i < session.getSessionFiles().size(); i++)
		 {
			 OutPut("Backing up");
			 File sourceFile = session.getSessionFiles().get(i);
			 File[] listOfFiles = sourceFile.listFiles();
			 if (session.getCompress()) {
				 new Compress(sourceFile, listOfFiles, session.getDestination());
			 }
			 else if (session.getDecompress()) {
				 new DeCompress(sourceFile, listOfFiles, session.getDestination());
			 }
			 else {
				 new Data_Mngr(sourceFile, listOfFiles, session.getDestination());
			 }
		 }
	 }
	 
	 private static void OutPut(Object outPutInfo)
	 {
		 System.out.println(String.valueOf(outPutInfo));
	 }
	
}

