package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class Statement implements Node{
    /*String leftBracket = "{", rightBracket = "}";
    String IF = "if", While = "while", sysOut = "System.out.println";
    String identifier;*/

    static ArrayList<String> stmContent; 
    
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		
		// TODO Auto-generated method stub
		/**
		 *     "{" ( Statement )* "}"
                |("if"|"while"|"System.out.println") X
                | Identifier W_EQU

		 */
		Tokens token = new Tokens();
		Main obj = new Main();
	    X x = new X();
	    W w = new W();
	    Identifier id = new Identifier();
	    Expression exp = new Expression();
	    stmContent = new ArrayList<>();
	    
		if(token.tokensList.get(obj.index).type.equals("LEFT_CURLY_B")){
			stmContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			stmContent.clear();
			
			int result = this.parse(obj.index);
			
			if(result == -1) return -1;
			while(result == 1){
				result = this.parse(obj.index);
				if(result == -1) return -1;
				else if(result == 0) break;
			}
			if(token.tokensList.get(obj.index).type.equals("RIGHT_CURLY_B")){
			stmContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			stmContent.clear();
			}
			else return -1;
			
		}
		else if(token.tokensList.get(obj.index).type.equals("IF")||
				token.tokensList.get(obj.index).type.equals("WHILE")||
				token.tokensList.get(obj.index).type.equals("SYSTEM.OUT.PRINTLN"))
		{	
			stmContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			stmContent.clear();
			
			if(x.parse(obj.index)==-1) return -1;

		}
		else if(token.tokensList.get(obj.index).type.equals("ID")){
			if(id.parse(obj.index) == -1) return -1;
			if(w.parse(obj.index) == -1) return -1;
		}
		else return 0;
		printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<stmContent.size(); i++){
			System.out.print(stmContent.get(i)+" ");
			if(stmContent.get(i).equals("}")||stmContent.get(i).equals(";"))
				System.out.print("\n");
			if(stmContent.get(i).equals("{")) 
				System.out.print("\n"+ "\t");
		}
	}
	 
}
