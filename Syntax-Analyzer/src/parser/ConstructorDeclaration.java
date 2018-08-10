package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class ConstructorDeclaration implements Node{
	
	ArrayList<String> constructorContent;
	
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException
	{
		Identifier id =  new Identifier();
		Main m = new Main();
		Tokens token = new Tokens();
		Type type=new Type();
		VarDeclaration var = new VarDeclaration();
		Statement stm=new Statement();
		constructorContent = new ArrayList<>();
		
		/**
		 * ("public" | "private" | "protected") Type Identifier
          "(" ( Type Identifier ( "," Type Identifier )*)? ")"
          "{" ( VarDeclaration )* ( Statement )* "return" Expression ";" "}"

		 */
		if(id.parse(m.index) == -1) return 0;
		
		if(!token.tokensList.get(m.index).type.equals("LEFT_ROUND_B")) return -1;
		constructorContent.add(token.tokensList.get(m.index).value);
		m.index++;
		
		printNode();
		constructorContent.clear();
		
		if(type.parse(m.index) == 1)
		{
			if(id.parse(m.index) == -1) return -1;
			
			while(token.tokensList.get(m.index).type.equals("COMMA"))
			{
				constructorContent.add(token.tokensList.get(m.index).value);
				m.index++;
				
				printNode();
				constructorContent.clear();
				
				if(type.parse(m.index) == -1) return -1;

				if(id.parse(m.index) == -1) return -1;

			}
		}
		
		if(!token.tokensList.get(m.index).type.equals("RIGHT_ROUND_B")) return -1;
		constructorContent.add(token.tokensList.get(m.index).value);
		m.index++;
		
		if(!token.tokensList.get(m.index).type.equals("LEFT_CURLY_B")) return -1;
		constructorContent.add(token.tokensList.get(m.index).value);
		m.index++;
		////////////////////////////////////////////////////// 2 loops 
		
		printNode();
		constructorContent.clear();
		
		int result =var.parse(m.index);
		if(result == -1)
			return -1;
		else
		{

			while((result !=0) && (result != -1))
			{
				
				result = var.parse(m.index);
			}
		}
		//////////////////////////////////// loop for statement 
		int stmResult = stm.parse(m.index);
		if(stmResult == -1) return -1;
		else
		{

			while((stmResult !=0) && (stmResult != -1))
			{
				stmResult=stm.parse(m.index);

			}
		}
		
		///////////////////////////////////////////////////////    right curly bracket 
		
		if(!token.tokensList.get(m.index).type.equals("RIGHT_CURLY_B")){return -1;}
		constructorContent.add(token.tokensList.get(m.index).value);
		m.index++;
		
		
		printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<constructorContent.size(); i++){
			System.out.print(constructorContent.get(i));
			if(constructorContent.get(i).equals("}"))
				System.out.print("\n");
		}
	}

}
