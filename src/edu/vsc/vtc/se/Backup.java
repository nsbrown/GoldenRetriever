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
	
	 public Backup(List<File> sources, String Destination, int flag)
	 {
		 for(int i = 0; i < sources.size(); i++)
		 {
			 File sourceFile = sources.get(i);
			 
			 switch(flag)
			 {
				 case 0:
				 {
					 
				 }
				 break;
				 case 1:
				 {
					 
				 }
				 break;
				 case 2:
				 {
					 
				 }
				 break;
				 default:
				 {
					 OutPut("Error");
				 }
				 break;
			 }
		 }
	 }
	 
	 private static void OutPut(Object outPutInfo)
	 {
		 System.out.println(String.valueOf(outPutInfo));
	 }
	
}

