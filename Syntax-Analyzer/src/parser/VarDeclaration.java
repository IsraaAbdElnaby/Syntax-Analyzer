package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class VarDeclaration implements Node{

	/**
	 * VarDeclaration ::= Type Identifier ";"
	 */
	ArrayList<String> varContent;
	Main m;
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Tokens token = new Tokens();
		Type t = new Type();
		m = new Main();
		Identifier id = new Identifier();
		 varContent = new ArrayList<>();
		 int typeResult = t.parse(m.index);
		if(typeResult == -1) return -1;
		else if(typeResult == 0) return 0;

		if(id.parse(m.index) == -1) return -1;
		
		if(!token.tokensList.get(m.index).type.equals("SEMICOLON")) return -1;
		varContent.add(token.tokensList.get(m.index).value);
		m.index++;
		printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<varContent.size(); i++){
			System.out.println(varContent.get(i));
		}
		
	}

}
