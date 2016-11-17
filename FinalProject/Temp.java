import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Temp {
	
	public static void main(String[] args)
	{
		String fileOutput = "USE [TestDB]\n\n";
		String tempString = "";
		String[] tempArray = new String[]{"teacher", "student"};
		Random random = new Random(System.currentTimeMillis());
		try {
			Scanner fileReader = new Scanner(new File("Dictionary.txt"));
			File file = new File("insertGroupOfUsers2.sql");
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(fw);
			
			while(fileReader.hasNextLine())
			{
				fileOutput += "GO\nINSERT INTO [dbo].[user]\n";
				fileOutput += "\t\t([userName]\n\t\t,[password]\n\t\t,[role]\n\t\t)";
				fileOutput += "\n\tVALUES";
				tempString = fileReader.nextLine();
				
				fileOutput += "\n\t\t('" + tempString + "'\n\t\t,'" + tempString
						+ "'\n\t\t,'" + tempArray[random.nextInt(1000000) % 2] + "'\n\t\t)\n";
				bw.write(fileOutput);
			}
			bw.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
