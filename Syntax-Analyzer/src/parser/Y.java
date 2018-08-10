package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class Y implements Node{

	ArrayList<String> yContent;
	/**
	 * parse Function 
	 * returns -1 if there is a mismatching
	 * returns 1 if the match is correct 
	 */
	
	/**
	 * Statement Z_ELSE|";"
	 */
	
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		yContent = new ArrayList<>();
		Statement stm = new Statement();
		Z z = new Z();
		Tokens t= new Tokens();
		Main obj = new Main();
		int result = stm.parse(obj.index);
		if(result == 1){
			if(z.parse(obj.index)== -1) return -1;
		}
	
		else if(t.tokensList.get(obj.index).type.equals("SEMICOLON")){
			yContent.add(t.tokensList.get(obj.index).value);
			obj.index++;
		}
		else return -1;
		printNode();
		return 1;
	}
	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(String c:yContent){
			System.out.println(c);
		    if(c.equals(";")) System.out.print("\n");
		}
		
	}
}
