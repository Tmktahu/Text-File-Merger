// Coded by Travis Kehrli
// Last updated 2/7/2018
// The purpose of this program is to take all text files in the given directory
//   and output them into a single text file such that the name of the file is
//   followed by the contents of the file, repeated for each file one after another.
//  It can be run from inside the target folder or a path to the target folder may be given.

package alltogether;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;


public class AllTogether {
	public static void main(String[] args) {
		try {
			
		    File dir; //get the variable ready
			if(args.length != 0) { //if they did give us an arguement
				dir = new File(args[0]); //then use it
				
			} else { //otherwise
				dir = new File(System.getProperty("user.dir")); //just use the current directory
			}
			

			File[] directoryListing = dir.listFiles(); //get the directory listing
			BufferedWriter bw = null;
			if (directoryListing != null) { //if it is a directory
				
				File file = new File("output.txt");
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				
				
				for (File child : directoryListing) { //for each file in the directory

					
					String extension = "";
					int i = child.getAbsolutePath().lastIndexOf('.');
					if (i > 0) {
					    extension = child.getAbsolutePath().substring(i+1);
					}
					
					if(extension.equals("txt")) {
						String fileName = child.getName(); //get the name of the file
						String fileContents = new String(Files.readAllBytes(child.toPath()));; //get the contents of the file

						bw.write(fileName);
						bw.newLine();
						bw.write(fileContents);
						bw.newLine();
						bw.newLine();
					}
						
				}
				
				bw.close();
				
			} else { //if it is not a directory
				//tell them
				System.out.println("That is not a directory.");
				System.out.println("Proper usage is  java -jar allTxtTogether.jar <folder containing txt files>");
				System.out.println("If no directory is specified, the current directory will be used.");
			}
			
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
		}
	}
}
