package ahocorasick_algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Preprocess {
	
	
	public static String[] preproccesPattern() throws IOException {
		String fileName = "pattern.txt";
		FileReader inputFile = new FileReader(fileName);
        @SuppressWarnings("resource")
		BufferedReader bufferReader = new BufferedReader(inputFile);
        ArrayList<String> dictionaty = new ArrayList<>();
        String line;
        
        while ((line = bufferReader.readLine()) != null)   {
            dictionaty.add(line);
          }
        
        return dictionaty.toArray(new String[dictionaty.size()]);
	}
			
	public static String preprocessText() throws IOException {
		String fileName = "hu-chr1.txt";
		FileReader inputFile = new FileReader(fileName);
        @SuppressWarnings("resource")
		BufferedReader bufferReader = new BufferedReader(inputFile);
        String text = "";
        String line;
        
        while ((line = bufferReader.readLine()) != null)   {
            text += line;
          }
        
        return text;
	}

}
