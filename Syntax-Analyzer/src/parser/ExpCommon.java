package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class ExpCommon implements Node{


    static ArrayList<String> commmContent;
    
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
	    Tokens token = new Tokens();
	    Main obj = new Main();
	    Expression exp = new Expression();
	    Identifier id = new Identifier();
	    ExpArray arr = new ExpArray();
	    commmContent = new ArrayList<>();
	    
		/**
		 * ( "&&" | "||" | "==" | "!=" | ">" |"<" |"<=" | ">="| "+" | "-" | "*" | "/" | "!" | | "(" | "[" )
		 */
		if(token.tokensList.get(obj.index).type.equals("AND")|| token.tokensList.get(obj.index).type.equals("OR")||
		   token.tokensList.get(obj.index).type.equals("EQUAL")|| token.tokensList.get(obj.index).type.equals("ASSIGNMENT")||
		   token.tokensList.get(obj.index).type.equals("LESSTHAN")|| token.tokensList.get(obj.index).type.equals("GREATERTHAN")||
		   token.tokensList.get(obj.index).type.equals("LESS_EQ")|| token.tokensList.get(obj.index).type.equals("GREATER_EQ")||
		   token.tokensList.get(obj.index).type.equals("PLUS")|| token.tokensList.get(obj.index).type.equals("MINUS")||
		   token.tokensList.get(obj.index).type.equals("MULTIPLY")|| token.tokensList.get(obj.index).type.equals("DIV")||
		   token.tokensList.get(obj.index).type.equals("NOT"))
		{
			commmContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			commmContent.clear();
			
			
			/* Expression */
			
		    if(exp.parse(obj.index)==-1) return -1;
		   
		    
		    /**
		     * ")" | "]" | Empty
		     */

		}
		
		else if(arr.parse(obj.index) == 1){}
		/**
		 * "." ( Identifier "(" ( Expression ( "," Expression )* )? ")" | length)
		 */
		else if(token.tokensList.get(obj.index).type.equals("DOT")){
			commmContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			if(token.tokensList.get(obj.index).type.equals("LENGTH")){
				commmContent.add(token.tokensList.get(obj.index).value);
				obj.index++;
			}
			
			else if(token.tokensList.get(obj.index).type.equals("ID")){
				printNode();
				commmContent.clear();
				if(id.parse(obj.index) == -1) return -1;
				
				if(!token.tokensList.get(obj.index).type.equals("LEFT_ROUND_B")) return -1;
				commmContent.add(token.tokensList.get(obj.index).value);
				obj.index++;
	            
				printNode();
				commmContent.clear();
				
				if(exp.parse(obj.index)==1){
					if(token.tokensList.get(obj.index).type.equals("COMMA")){
						while(token.tokensList.get(obj.index).type.equals("COMMA")){
							commmContent.add(token.tokensList.get(obj.index).value);
							obj.index++;
							
							printNode();
							commmContent.clear();
							
							if(exp.parse(obj.index)==-1) return -1;

						}
					}
				}
				
				if(!token.tokensList.get(obj.index).type.equals("RIGHT_ROUND_B")) return -1;
				commmContent.add(token.tokensList.get(obj.index).value);
				obj.index++;
			}
			else return -1;
		}
		else return 0;

		printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		   for(int i=0; i<commmContent.size(); i++){
			   System.out.print(commmContent.get(i)+" ");
		   }
		
	}

}
