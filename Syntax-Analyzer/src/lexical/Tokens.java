package lexical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Tokens {
	public String value, type;
	public static ArrayList<Tokens> tokensList = new ArrayList<>();
	static Main obj = new Main();

	public static ArrayList<Tokens> getListofTokens() throws FileNotFoundException
	{
		ArrayList<Tokens> list = new ArrayList<>();
        Scanner reader = new Scanner(new File("TEST1.txt"));
        obj.index = 0;
        while(reader.hasNextLine()){
               Tokens t = new Tokens();
               t.type = reader.next();
              t.value = reader.next();
              list.add(t);
        }
        
	    return list;
	}
	
    

}
