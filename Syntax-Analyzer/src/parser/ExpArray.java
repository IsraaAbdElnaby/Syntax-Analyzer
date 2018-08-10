package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class ExpArray implements Node{

	 static ArrayList<String> arrContent; 
	 
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Expression exp = new Expression();
		Tokens token = new Tokens();
		Main obj = new Main();
		 arrContent = new ArrayList<>();
		 
		 if(token.tokensList.get(obj.index).type.equals("LEFT_SQUARE_B")){
				arrContent.add(token.tokensList.get(obj.index).value);
				obj.index++;
				
				printNode();
				arrContent.clear();
				
			 if(exp.parse(obj.index) == -1) return -1;
			 
			 if(!token.tokensList.get(obj.index).type.equals("RIGHT_SQUARE_B")) return -1;
			 arrContent.add(token.tokensList.get(obj.index).value);
			 obj.index++;
		 }
		else return 0;
		printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<arrContent.size(); i++){
			System.out.print(arrContent.get(i)+" ");
		}
	}

}
