package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class Identifier implements Node{

    static ArrayList<String> idContent; 
    /**
     * Parse Function returns 1 if the token of type identifier
     * -1 otherwise
     */
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
	    Tokens t = new Tokens();
	    Main obj = new Main();
	    idContent = new ArrayList<>(); 
	    
		if (!t.tokensList.get(obj.index).type.equals("ID"))
			return -1;
		idContent.add(t.tokensList.get(obj.index).value);
		obj.index++;
		printNode();
		return 1;
	}
	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<idContent.size(); i++){
			System.out.print(idContent.get(i)+" ");
		}
	}

}
