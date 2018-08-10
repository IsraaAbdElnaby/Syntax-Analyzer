package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class Type implements Node{
	
	
	static ArrayList<String> typeContent;
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Tokens token = new Tokens();
		Main obj = new Main();
		typeContent = new ArrayList<>();
		
		if(token.tokensList.get(obj.index).type.equals("INT")||
		token.tokensList.get(obj.index).type.equals("FLOAT")||
		token.tokensList.get(obj.index).type.equals("STRING")||
		token.tokensList.get(obj.index).type.equals("CHARACTER")||
		token.tokensList.get(obj.index).type.equals("BOOLEAN")){
			
			typeContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			if(token.tokensList.get(obj.index).type.equals("LEFT_SQUARE_B")){
				typeContent.add(token.tokensList.get(obj.index).value);
				obj.index++;
				if(!token.tokensList.get(obj.index).type.equals("RIGHT_SQUARE_B")) return -1;
				typeContent.add(token.tokensList.get(obj.index).value);
				obj.index++;
			}
		}
		else return 0;
		printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<typeContent.size(); i++){
			System.out.print(typeContent.get(i)+ " ");
		}
	}

}
