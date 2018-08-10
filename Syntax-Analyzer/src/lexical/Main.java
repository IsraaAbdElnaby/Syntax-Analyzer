package lexical;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import parser.ExpCommon;
import parser.Goal;
import parser.MainClass;


public class Main {

    public static int index;	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Tokens t = new Tokens();
		t.tokensList.addAll(t.getListofTokens());
		ExpCommon ex = new ExpCommon();
		MainClass m = new MainClass();
		Goal root = new Goal();

		if(root.parse(index) == -1)
			System.out.println("Syntax Error");
		
	}

}
