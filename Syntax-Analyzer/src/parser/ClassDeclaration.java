package parser;

import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class ClassDeclaration implements Node {
	/**
	 * ClassDeclaration ::= "class" Identifier ( "extends" Identifier )? "{" (
	 * VarDeclaration )* ( ConstructorDeclaration )* ( MethodDeclaration )* "}"
	 * 
	 */

	static ArrayList<String> classContent;

	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Tokens token = new Tokens();
		Main obj = new Main();
		X x = new X();
		Identifier id = new Identifier();
		VarDeclaration var = new VarDeclaration();
		ConstructorDeclaration constructor = new ConstructorDeclaration();
		MethodDeclaration method =  new MethodDeclaration();
		classContent = new ArrayList<>();
		
		if(obj.index == token.tokensList.size()) System.exit(1);
		
		if (!token.tokensList.get(obj.index).type.equals("CLASS"))
			return -1;
		classContent.add(token.tokensList.get(obj.index).value);
		obj.index++;

		printNode();
		classContent.clear();
		
		if (id.parse(obj.index) == -1)
			return -1;
		
		if (token.tokensList.get(obj.index).type.equals("EXTENDS")) {
			classContent.add(token.tokensList.get(obj.index).value);
			obj.index++;

			printNode();
			classContent.clear();

			if (id.parse(obj.index) == -1)
				return -1;
		}
		
		if (!token.tokensList.get(obj.index).type.equals("LEFT_CURLY_B"))
			return -1;
		classContent.add(token.tokensList.get(obj.index).value);
		obj.index++;
		
		printNode();
		classContent.clear();
		
		int varResult = var.parse(obj.index);
		
		if(varResult == -1) return -1;
		else if(varResult == 0){}
		else{
		while(varResult == 1){
			varResult = var.parse(obj.index);
			if(varResult == -1) return -1;
			else if(varResult == 0) break;
		}
		}
		
		int conResult = constructor.parse(obj.index);
		if(conResult == -1) return -1;
		else if(conResult == 0){}
		while(conResult == 1){
			conResult = constructor.parse(obj.index);
			if(conResult == -1) return -1;
			else if(conResult == 0) break;
		}
		
		int methodResult = method.parse(obj.index);
		if(methodResult == -1) return -1;
		else if(methodResult == 0) {}
		else{
		while(methodResult == 1){
			methodResult = method.parse(obj.index);
			if(methodResult == -1) return -1;
			else if(methodResult == 0) break;
		}
		}
	
		if (!token.tokensList.get(obj.index).type.equals("RIGHT_CURLY_B"))
			return -1;
		classContent.add(token.tokensList.get(obj.index).value);
		obj.index++;

		printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<classContent.size(); i++){
			System.out.print(classContent.get(i)+ " ");
			if(classContent.get(i).equals("{"))
				System.out.print("\n"+"\t");
			else if(classContent.get(i).equals("}"))
				System.out.print("\n");
		}
	}

}
