package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class MethodDeclaration implements Node{

	ArrayList<String> methodContent;
	
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Tokens token = new Tokens();
		Main obj = new Main();
		Type type=new Type();
		Identifier id = new Identifier();
		VarDeclaration var = new VarDeclaration();
		Statement stm=new Statement();
		Expression exp = new Expression();
		methodContent = new ArrayList<>();
		
		if (token.tokensList.get(obj.index).type.equals("PUBLIC") ||
			token.tokensList.get(obj.index).type.equals("PRIVATE") ||
			token.tokensList.get(obj.index).type.equals("PROTECTED")){
			
			methodContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			printNode();
			methodContent.clear();
			
			if(type.parse(obj.index) == -1) return -1;
			if(id.parse(obj.index) == -1) return -1;
			
			if(!token.tokensList.get(obj.index).type.equals("LEFT_ROUND_B")) return -1;
			methodContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			methodContent.clear();
			int typeResult = type.parse(obj.index);
			if(typeResult == 1)
			{
				if(id.parse(obj.index) == -1) return -1;
				
				while(token.tokensList.get(obj.index).type.equals("COMMA"))
				{
					methodContent.add(token.tokensList.get(obj.index).value);
					obj.index++;
					
					printNode();
					methodContent.clear();
					
					if(type.parse(obj.index) == -1) return -1;

					if(id.parse(obj.index) == -1) return -1;

				}
			}
			else if(typeResult == -1) return -1;
			
			if(!token.tokensList.get(obj.index).type.equals("RIGHT_ROUND_B")) return -1;
			methodContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			if(!token.tokensList.get(obj.index).type.equals("LEFT_CURLY_B")) return -1;
			methodContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			////////////////////////////////////////////////////// 2 loops
			printNode();
			methodContent.clear();
			
			int result =var.parse(obj.index);
			int stmResult =var.parse(obj.index);
			if(result == -1) return -1;
			
			else{

				while((result !=0) && (result != -1))
				{
					result=var.parse(obj.index);

				}
				if(result == -1) return -1;
			}
			//////////////////////////////////// loop for statement 
			stmResult =stm.parse(obj.index);
			if(stmResult == -1) return -1;
			else
			{

				while((stmResult !=0) && (stmResult != -1))
				{
					stmResult=stm.parse(obj.index);

				}
				if(stmResult == -1) return -1;
			}
			if(!token.tokensList.get(obj.index).type.equals("RETURN")) return -1;
			methodContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			printNode();
			methodContent.clear();
			
			if(exp.parse(obj.index) == -1) return -1;
			
			if(!token.tokensList.get(obj.index).type.equals("SEMICOLON")) return -1;
			methodContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
			if(!token.tokensList.get(obj.index).type.equals("RIGHT_CURLY_B")) return -1;
			methodContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
			
		}
		else return 0;
		printNode();	
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<methodContent.size(); i++){
			System.out.print(methodContent.get(i)+ " ");
			if(methodContent.get(i).equals("}") || methodContent.get(i).equals(";"))
				System.out.print("\n");
			else if(methodContent.get(i).equals("{"))
				System.out.print("\n"+"\t");
		}
	}

}
