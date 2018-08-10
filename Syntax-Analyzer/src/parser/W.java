package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class W implements Node{

	static ArrayList<String> wContent;
	
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		/**
		 * W ::= U "=" Expression ";"
		 */
		Tokens token = new Tokens();
		Main obj = new Main();
		U u = new U();
		Expression exp = new Expression();
		wContent = new ArrayList<>();
		
		if(u.parse(obj.index) == -1) return -1;
		if(!token.tokensList.get(obj.index).type.equals("ASSIGNMENT")) return -1;
		wContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
            
		printNode();
		wContent.clear();
		
		int expResult = exp.parse(obj.index);
		if(expResult == -1 || expResult == 0) return -1;
		
		if(!token.tokensList.get(obj.index).type.equals("SEMICOLON")) return -1;
		wContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<wContent.size(); i++){
			System.out.print(wContent.get(i));
			if(wContent.get(i).equals(";")) System.out.print("\n");
		}
	}

}
