package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class MainClass implements Node{

    ArrayList<String> mainContent;
    int leftCurlyFreq;
    Main obj;
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		/**
		 * "class" Identifier "{" "public" "static" "void" "main" "(" "String" "["
            "]" Identifier ")" "{" Statement "}" "}"

		 */
	    Tokens token = new Tokens();
	    obj = new Main();
	    Identifier id = new Identifier();
	    Statement stm = new Statement();
	    mainContent = new ArrayList<>();
	    leftCurlyFreq = 0;
	    
		if(!token.tokensList.get(obj.index).type.equals("CLASS")){
			while(!token.tokensList.get(obj.index).type.equals("CLASS")){
			if(token.tokensList.get(obj.index).type.equals("M_COMMENTS") || token.tokensList.get(obj.index).type.equals("S_COMMENTS")){
				mainContent.add(token.tokensList.get(obj.index).value);
				obj.index++;
			}
			
			else if(token.tokensList.get(obj.index).type.equals("EOL")){
				mainContent.add("\n");
				obj.index++;
			}
			else if(token.tokensList.get(obj.index).type.equals("CLASS")) break;
			else return -1;
			}
			
		}
		
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
	
		printNode();
		mainContent.clear();
		if(id.parse(obj.index) == -1) return -1;
		
		if(!token.tokensList.get(obj.index).type.equals("LEFT_CURLY_B")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		if(!token.tokensList.get(obj.index).type.equals("PUBLIC")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		if(!token.tokensList.get(obj.index).type.equals("STATIC")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		if(!token.tokensList.get(obj.index).type.equals("VOID")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		if(!token.tokensList.get(obj.index).type.equals("MAIN")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		if(!token.tokensList.get(obj.index).type.equals("LEFT_ROUND_B")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		if(!token.tokensList.get(obj.index).type.equals("STRING")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		if(!token.tokensList.get(obj.index).type.equals("LEFT_SQUARE_B")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		if(!token.tokensList.get(obj.index).type.equals("RIGHT_SQUARE_B")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		printNode();
		mainContent.clear();
		
		if(id.parse(obj.index) == -1) return -1;
		
		if(!token.tokensList.get(obj.index).type.equals("RIGHT_ROUND_B")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		if(!token.tokensList.get(obj.index).type.equals("LEFT_CURLY_B")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		printNode();
		mainContent.clear();
		
		int result = stm.parse(obj.index);
		
		if(result == -1) return -1;
		while(result == 1){
			result = stm.parse(obj.index);
			if(result == -1) return -1;
			else if(result == 0) break;
		}
		
		if(!token.tokensList.get(obj.index).type.equals("RIGHT_CURLY_B")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		if(!token.tokensList.get(obj.index).type.equals("RIGHT_CURLY_B")) return -1;
		mainContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		for(int i=0; i<mainContent.size(); i++){
			if(i!=0 && mainContent.get(i).equals("class")) {
				System.out.print("\n");
				System.out.print("\n");
			}
			System.out.print(mainContent.get(i)+" ");
			if( mainContent.get(i).equals("}"))
				System.out.print("\n");
			if(mainContent.get(i).equals("{")){
				if(leftCurlyFreq == 0)
				  System.out.print("\n"+ "\t");
				else
				  System.out.print("\n"+ "\t\t");
				leftCurlyFreq++;
			}
		}
	}

}
