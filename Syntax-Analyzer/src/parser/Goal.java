package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class Goal implements Node {
	static ArrayList<String> goalContent;
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		MainClass main =new MainClass();
		ClassDeclaration classdecl=new ClassDeclaration();
		goalContent = new ArrayList<>();
		Main m = new Main();
		Tokens token = new Tokens();
		
		if(main.parse(m.index) == -1) return -1;		

		int result =classdecl.parse(m.index);
		if(result == -1)
			return -1;
		else
		{

			while((result !=0) && (result != -1) )
			{
				result=classdecl.parse(m.index);

				if(result == -1) return -1;
			}
			
		}
		
		if(token.tokensList.get(m.index).type.equals("EOF"))
			goalContent.add("\n");
			
        printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<goalContent.size(); i++){
			System.out.print(goalContent.get(i));
		}
	}
	
	
	

}
