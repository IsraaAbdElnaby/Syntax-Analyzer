package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class newPrime implements Node{
	
	static ArrayList<String> newContent;
	
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Tokens token = new Tokens();
		Main obj = new Main();
		ExpCommon comm = new ExpCommon();
		Identifier id = new Identifier();
		Expression exp = new Expression();
		newContent = new ArrayList<>();
		
		if(token.tokensList.get(obj.index).type.equals("INT")||
		token.tokensList.get(obj.index).type.equals("FLOAT")||
		token.tokensList.get(obj.index).type.equals("STRING_LITERAL")||
		token.tokensList.get(obj.index).type.equals("CHARACTER")||
		token.tokensList.get(obj.index).type.equals("BOOLEAN")){
			
			newContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
            
			printNode();
			newContent.clear();
			
			if(comm.parse(obj.index) == -1) return -1;
		}
		
		else if(id.parse(obj.index) == 1){
			if(!token.tokensList.get(obj.index).type.equals("LEFT_ROUND_B"))
				return -1;
			newContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
            
			printNode();
			newContent.clear();
			
			int expResult = exp.parse(obj.index);
			if(expResult == -1) return -1;
			else if(expResult == 1){
				while(token.tokensList.get(obj.index).type.equals("COMMA"))
				{
					newContent.add(token.tokensList.get(obj.index).value);
					obj.index++;
					
					printNode();
					newContent.clear();
					
					if(exp.parse(obj.index) == -1) return -1;

				}
			}
			
			if(!token.tokensList.get(obj.index).type.equals("RIGHT_ROUND_B"))
				return -1;
			newContent.add(token.tokensList.get(obj.index).value);
			obj.index++;

		}
		
		
		else return -1;
		printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<newContent.size(); i++){
			System.out.print(newContent.get(i)+ " ");
		}
	}

}
