package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class Expression implements Node{


    static ArrayList<String> expContent;
    
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		/**
		 * <INTEGER_LITERAL> ExpressionDouPrime
           |<FLOAT_LITERAL> ExpressionDouPrime
           |"true" ExpressionDouPrime
           |"false" ExpressionDouPrime
           |"this" ExpressionDouPrime

		 */
		newPrime b = new newPrime();
	    Tokens token = new Tokens();
	    Main obj = new Main();
	    ExpressionDouPrime exp = new ExpressionDouPrime();
	    Identifier id = new Identifier();
	    ExpCommon comm = new ExpCommon();
	    expContent = new ArrayList<>();
	    
		if(token.tokensList.get(obj.index).type.equals("INTEGRAL_LITERAL") || 
		   token.tokensList.get(obj.index).type.equals("FLOAT_LITERAL") ||
		   token.tokensList.get(obj.index).type.equals("STRING_LITERAL") ||
		   token.tokensList.get(obj.index).type.equals("TRUE") ||
		   token.tokensList.get(obj.index).type.equals("FALSE") ||
		   token.tokensList.get(obj.index).type.equals("THIS"))
		{
			expContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			expContent.clear();
			
			if(exp.parse(obj.index)==-1) return -1;
			
		}
		/**
		 * Identifier
		 */
		else if(token.tokensList.get(obj.index).type.equals("ID")){
			
			if(id.parse(obj.index) == -1) return -1;
			if(exp.parse(obj.index) == -1) return -1;
			
		}
		
		/**
		 * "new" B_NEW
		 */
		else if(token.tokensList.get(obj.index).type.equals("NEW")){
			expContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			expContent.clear();
			
			if(b.parse(obj.index) == -1) return -1;
			if(exp.parse(obj.index) == -1) return -1;
			
		}
		
		
       /**
        * �(� Expression �)� Expression_2 
        */
		else if(token.tokensList.get(obj.index).type.equals("LEFT_ROUND_B")){
			expContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			expContent.clear();
			
		   if(this.parse(obj.index) == -1) return -1;
			expContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			expContent.clear();
			
			if(exp.parse(obj.index) == -1) return -1;

		}
		
		/**
		 * "!" Expression_2
		 */
		else if(token.tokensList.get(obj.index).type.equals("NOT")){
			expContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			expContent.clear();
			
			if(exp.parse(obj.index) == -1) return -1;
		}

        printNode();
		return 1;
		
	}
	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<expContent.size(); i++){
			System.out.print(expContent.get(i)+ " ");
		}
	}
}
